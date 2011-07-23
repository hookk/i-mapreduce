/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.mapred;

import static org.apache.hadoop.mapred.Task.Counter.MAP_INPUT_BYTES;
import static org.apache.hadoop.mapred.Task.Counter.MAP_INPUT_RECORDS;
import static org.apache.hadoop.mapred.Task.Counter.MAP_OUTPUT_RECORDS;

import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.SerializationFactory;
import org.apache.hadoop.mapred.TaskCompletionEvent.Status;
import org.apache.hadoop.mapred.buffer.BufferUmbilicalProtocol;
import org.apache.hadoop.mapred.buffer.OutputFile;
import org.apache.hadoop.mapred.buffer.OutputFile.Header;
import org.apache.hadoop.mapred.buffer.impl.DataValuesIterator;
import org.apache.hadoop.mapred.buffer.impl.JInputBuffer;
import org.apache.hadoop.mapred.buffer.impl.JOutputBuffer;
import org.apache.hadoop.mapred.buffer.impl.StaticData;
import org.apache.hadoop.mapred.buffer.impl.ValuesIterator;
import org.apache.hadoop.mapred.buffer.net.BufferExchange;
import org.apache.hadoop.mapred.buffer.net.BufferRequest;
import org.apache.hadoop.mapred.buffer.net.BufferExchangeSink;
import org.apache.hadoop.mapred.buffer.net.MapBufferRequest;
import org.apache.hadoop.mapred.buffer.net.ReduceBufferRequest;
import org.apache.hadoop.util.Progress;
import org.apache.hadoop.util.ReflectionUtils;

/** A Map task. */
public class MapTask extends Task implements InputCollector {
	
	private class ReduceOutputFetcher extends Thread {
		private TaskID oneReduceTaskId;

		private TaskUmbilicalProtocol trackerUmbilical;
		
		private BufferUmbilicalProtocol bufferUmbilical;
		
		private BufferExchangeSink sink;
		
		public ReduceOutputFetcher(TaskUmbilicalProtocol trackerUmbilical, 
				BufferUmbilicalProtocol bufferUmbilical, 
				BufferExchangeSink sink,
				TaskID reduceTaskId) {
			this.trackerUmbilical = trackerUmbilical;
			this.bufferUmbilical = bufferUmbilical;
			this.sink = sink;
			this.oneReduceTaskId = reduceTaskId;
		}

		public void run() {
			Set<TaskID> finishedReduceTasks = new HashSet<TaskID>();
			Set<TaskAttemptID>  reduceTasks = new HashSet<TaskAttemptID>();
			int eid = 0;
			
			while (!isInterrupted() && finishedReduceTasks.size() < getNumberOfInputs()+1) {
				try {
					ReduceTaskCompletionEventsUpdate updates = 
						trackerUmbilical.getReduceCompletionEvents(getJobID(), eid, Integer.MAX_VALUE);

					eid += updates.events.length;

					//LOG.info("get reduce task completion events : " + eid);
					// Process the TaskCompletionEvents:
					// 1. Save the SUCCEEDED maps in knownOutputs to fetch the outputs.
					// 2. Save the OBSOLETE/FAILED/KILLED maps in obsoleteOutputs to stop fetching
					//    from those maps.
					// 3. Remove TIPFAILED maps from neededOutputs since we don't need their
					//    outputs at all.
					for (TaskCompletionEvent event : updates.events) {
						//LOG.info("event is " + event + " status is " + event.getTaskStatus());
						switch (event.getTaskStatus()) {
						case FAILED:
						case KILLED:
						case OBSOLETE:
						case TIPFAILED:
						{
							TaskAttemptID reduceTaskId = event.getTaskAttemptId();
							if (!reduceTasks.contains(reduceTaskId)) {
								reduceTasks.remove(reduceTaskId);
							}
						}
						case SUCCEEDED:
						{
							TaskAttemptID mapTaskId = event.getTaskAttemptId();
							finishedReduceTasks.add(mapTaskId.getTaskID());
						}
						case RUNNING:
						{
							URI u = URI.create(event.getTaskTrackerHttp());
							String host = u.getHost();
							TaskAttemptID reduceTasktId = event.getTaskAttemptId();
							
							//LOG.info(reduceAttemptId.getTaskID() + " : " + reduceTaskId);
							if(job.getBoolean("mapred.iterative.mapsync", false)){
								if (!reduceTasks.contains(reduceTasktId)) {
									BufferExchange.BufferType type = BufferExchange.BufferType.ITERATE;
									
									BufferRequest request = 
										new ReduceBufferRequest(host, getTaskID(), sink.getAddress(), type, reduceTasktId.getTaskID());
									try {
										bufferUmbilical.request(request);
										reduceTasks.add(reduceTasktId);
										if (reduceTasks.size() == getNumberOfInputs()) {
											LOG.info("ReduceTask " + getTaskID() + " has requested all reduce buffers. " + 
													reduceTasks.size() + " reduce buffers.");
										}
									} catch (IOException e) {
										LOG.warn("BufferUmbilical problem in taking request " + request + ". " + e);
									}
								}
							}else{
								//LOG.info("I am here for reduce buffer request " + reduceTasktId.getTaskID() + " : " + oneReduceTaskId);
								//wrong
								
								if (reduceTasktId.getTaskID().equals(oneReduceTaskId)) {
									LOG.info("Map " + getTaskID() + " sending buffer request to reducer " + oneReduceTaskId);
									BufferExchange.BufferType type = BufferExchange.BufferType.ITERATE;
									
									BufferRequest request = 
										new ReduceBufferRequest(host, getTaskID(), sink.getAddress(), type, oneReduceTaskId);
									
									try {
										bufferUmbilical.request(request);
										if (event.getTaskStatus() == Status.SUCCEEDED) return;
									} catch (IOException e) {
										LOG.warn("BufferUmbilical problem sending request " + request + ". " + e);
									}
								}
							}					
						}
						break;
						}
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) { }
			}
		}
	}
	/**
	 * The size of each record in the index file for the map-outputs.
	 */
	public static final int MAP_OUTPUT_INDEX_RECORD_LENGTH = 24;

	protected TrackedRecordReader recordReader = null;
	private JobConf job = null;
	
	protected OutputCollector collector = null;	//the original one uses it
	protected JOutputBuffer buffer = null;		//iterative mapreduce uses it

	private BytesWritable split = new BytesWritable();
	private String splitClass;
	private InputSplit instantiatedSplit = null;
	
    protected Class inputKeyClass;
    protected Class inputValClass;
    protected Class outputKeyClass;
    protected Class outputValClass;
    protected Class dataKeyClass;
    protected Class dataValClass;
    
	protected Class<? extends CompressionCodec> mapOutputCodecClass = null;
	protected Class<? extends CompressionCodec> reduceOutputCodecClass = null;
	
	protected Deserializer inputKeyDeserializer;
	protected Deserializer inputValDeserializer;
	
	protected BufferUmbilicalProtocol bufferUmbilical;
	protected Reporter reporter;
	protected IterativeMapper mapper;
	protected StaticData staticData;
	protected int counter = 0;
	private int iteration = 0;
	private int snapshotInterval = 0;
	private int snapshotIndex = 1;
	private int stopIteration = 0;
	private StaticData.MatchType joinType = null;
	private long maptime = 0;
	private long readtime = 0;
	private FileSystem localfs = null;
	private IFile.Writer cachewriter = null;
    
    protected TaskID predecessorReduceTaskId = null;

	private static final Log LOG = LogFactory.getLog(MapTask.class.getName());

	{   // set phase for this task
		setPhase(TaskStatus.Phase.MAP); 
	}

	public MapTask() {
		super();
	}

	public MapTask(String jobFile, TaskAttemptID taskId, 
			int partition, String splitClass, BytesWritable split, boolean iterative) {
		super(jobFile, taskId, partition);
		this.splitClass = splitClass;
		this.split = split;
		this.iterative = iterative;
	}

	@Override
	public boolean isMapTask() {
		return true;
	}
	
	public TaskID predecessorReduceTask(JobConf job) {
		JobID reduceJobId = JobID.forName(job.get("mapred.job.predecessor"));
		return new TaskID(reduceJobId, false, getTaskID().getTaskID().id);
	}
	
	@Override
	public int getNumberOfInputs() { 		
		if(job != null && job.getBoolean("mapred.iterative.mapsync", false)){
			return job.getInt("mapred.iterative.partitions", 0);
		}else if(this.isIterative()){
			return 1;
		}else{
			return super.getNumberOfInputs();
		}	
	}
	
	@Override
	public void localizeConfiguration(JobConf conf) throws IOException {
		super.localizeConfiguration(conf);
		Path localSplit = new Path(new Path(getJobFile()).getParent(), 
				"split.dta");
		LOG.debug("Writing local split to " + localSplit);
		DataOutputStream out = FileSystem.getLocal(conf).create(localSplit);
		Text.writeString(out, splitClass);
		split.write(out);
		out.close();
	}

	@Override
	public TaskRunner createRunner(TaskTracker tracker, TaskTracker.TaskInProgress tip) {
		return new MapTaskRunner(tip, tracker, this.conf);
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		write(out);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		super.write(out);
		Text.writeString(out, splitClass);
		if (split != null) split.write(out);
		else throw new IOException("SPLIT IS NULL");
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		super.readFields(in);
		splitClass = Text.readString(in);
		split.readFields(in);
	}

	@Override
	InputSplit getInputSplit() throws UnsupportedOperationException {
		return instantiatedSplit;
	}

	/**
	 * This class wraps the user's record reader to update the counters and progress
	 * as records are read.
	 * @param <K>
	 * @param <V>
	 */
	class TrackedRecordReader<K, V> 
	implements RecordReader<K,V> {
		private RecordReader<K,V> rawIn;
		private Counters.Counter inputByteCounter;
		private Counters.Counter inputRecordCounter;

		TrackedRecordReader(RecordReader<K,V> raw, Counters counters) {
			rawIn = raw;
			inputRecordCounter = counters.findCounter(MAP_INPUT_RECORDS);
			inputByteCounter = counters.findCounter(MAP_INPUT_BYTES);
		}

		public K createKey() {
			return rawIn.createKey();
		}

		public V createValue() {
			return rawIn.createValue();
		}

		public synchronized boolean next(K key, V value)
		throws IOException {

			setProgress(getProgress());
			long beforePos = getPos();
			boolean ret = rawIn.next(key, value);
			if (ret) {
				inputRecordCounter.increment(1);
				inputByteCounter.increment(Math.abs(getPos() - beforePos));
			}
			return ret;
		}
		public long getPos() throws IOException { return rawIn.getPos(); }
		public void close() throws IOException { rawIn.close(); }
		public float getProgress() throws IOException {
			return rawIn.getProgress();
		}
	}
	
	public void setProgress(float progress) {
		super.setProgress(progress);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void run(final JobConf job, final TaskUmbilicalProtocol umbilical, final BufferUmbilicalProtocol bufferUmbilical)
	throws IOException {
		 reporter = getReporter(umbilical);
		this.job = job;
		
		// start thread that will handle communication with parent
	    startCommunicationThread(umbilical);

		initialize(job, reporter);

	    // check if it is a cleanupJobTask
	    if (jobCleanup) {
	      runJobCleanupTask(umbilical);
	      return;
	    }
	    if (jobSetup) {
	      runJobSetupTask(umbilical);
	      return;
	    }
	    if (taskCleanup) {
	      runTaskCleanupTask(umbilical);
	      return;
	    }
	    
		int numReduceTasks = conf.getNumReduceTasks();
		LOG.info("numReduceTasks: " + numReduceTasks);

		//iterative version
		if(this.iterative){		
			this.bufferUmbilical = bufferUmbilical;
			
			Class mapCombiner = job.getClass("mapred.map.combiner.class", null);
			if (mapCombiner != null) {
				job.setCombinerClass(mapCombiner);
			}

			snapshotInterval = job.getInt("mapred.iterative.snapshot.interval", Integer.MAX_VALUE);
			stopIteration = job.getInt("mapred.iterative.stop.iteration", Integer.MAX_VALUE);
			
			this.outputKeyClass = job.getMapOutputKeyClass();
			this.outputValClass = job.getMapOutputValueClass();
			this.inputKeyClass = job.getInputKeyClass();
			this.inputValClass = job.getInputValueClass();
			this.dataKeyClass = job.getDataKeyClass();
			this.dataValClass = job.getDataValClass();

			//for sync map test
			localfs = FileSystem.getLocal(job);
			Path localpath = new Path("/tmp/cache");
			if(localfs.exists(localpath)){
				localfs.delete(localpath, true);
			};
			cachewriter = new IFile.Writer(conf, localfs, localpath,
					inputKeyClass, inputValClass, null, null);
		    
			
			if (conf.getCompressMapOutput()) {
				mapOutputCodecClass = conf.getMapOutputCompressorClass(DefaultCodec.class);
			}
			if (conf.getCompressReduceOutput()) {
				reduceOutputCodecClass = conf.getMapOutputCompressorClass(DefaultCodec.class);
			}
			 
			this.buffer = new JOutputBuffer(bufferUmbilical, this, job, 
					reporter, getProgress(), false, 
					this.outputKeyClass, this.outputValClass, mapOutputCodecClass);
			this.buffer.setIterative(true);
			
			mapper = (IterativeMapper) ReflectionUtils.newInstance(job.getMapperClass(), job);
			
			//get the predecessor job's corresponding reduce id
			while(this.predecessorReduceTaskId == null){
				JobID predecessorJobID = JobID.forName(conf.get("mapred.job.predecessor"));
				this.predecessorReduceTaskId = new TaskID(predecessorJobID, false, this.getTaskID().getTaskID().getId());
			}
			LOG.info("local reduce task id extracted " + predecessorReduceTaskId);

			BufferExchangeSink sink = new BufferExchangeSink(job, this, this); 
			sink.open();
			LOG.info("iterate sink opened");
			
			ReduceOutputFetcher rof = new ReduceOutputFetcher(umbilical, bufferUmbilical, sink, predecessorReduceTaskId);
			rof.setDaemon(true);
			rof.start();
					
			Path[] stateDataPaths = mapper.initStateData();
			Path staticDataPath = mapper.initStaticData();
			String type = job.get("mapred.iterative.jointype", "one2one");
			
			if(type.equals("one2all")){
				joinType = StaticData.MatchType.ONE2ALL;
			}else {
				joinType = StaticData.MatchType.ONE2ONE;
			}
			
			//if null no static data involved
			if(staticDataPath != null){
				staticData = new StaticData(conf, staticDataPath, joinType, dataKeyClass, dataValClass); 
			}else{
				staticData = null;
			}
		
			SerializationFactory serializationFactory = new SerializationFactory(conf);
			inputKeyDeserializer = serializationFactory.getDeserializer(inputKeyClass);
			inputValDeserializer = serializationFactory.getDeserializer(inputValClass);
			
			if(stateDataPaths !=  null){
				long mapbegin= System.currentTimeMillis();
				LocalFileSystem localFs = FileSystem.getLocal(job);
			    for(Path stateDataPath : stateDataPaths){
			    	long filelen = localFs.getFileStatus(stateDataPath).getLen();
					FSDataInputStream stateIn = localFs.open(stateDataPath);
					
					//initial mapreduce
					IFile.Reader<Object, Object> initReader = new IFile.Reader(job, stateIn, filelen, null, null);

					DataInputBuffer key = new DataInputBuffer();
					DataInputBuffer value = new DataInputBuffer();
					Object keyObject = null;
					Object valObject = null;
					
					if(staticData != null){
						//have static data, perform join operation before map
						if(joinType == StaticData.MatchType.ONE2ONE){
							while(initReader.next(key, value)){
								inputKeyDeserializer.open(key);
								inputValDeserializer.open(value);
								keyObject = inputKeyDeserializer.deserialize(keyObject);
								valObject = inputValDeserializer.deserialize(valObject);
								
								staticData.next();
								mapper.map(keyObject, valObject, staticData.getKey(), staticData.getValue(), this.buffer, reporter);
							}
						}else if(joinType == StaticData.MatchType.ONE2ALL){
							while(initReader.next(key, value)){
								inputKeyDeserializer.open(key);
								inputValDeserializer.open(value);
								keyObject = inputKeyDeserializer.deserialize(keyObject);
								valObject = inputValDeserializer.deserialize(valObject);
								mapper.map(keyObject, valObject, null, null, this.buffer, reporter);
							}
						}
					}else{
						// no static data, parse the state data in a single pass
						while(initReader.next(key, value)){
							inputKeyDeserializer.open(key);
							inputValDeserializer.open(value);
							keyObject = inputKeyDeserializer.deserialize(keyObject);
							valObject = inputValDeserializer.deserialize(valObject);
	
							mapper.map(keyObject, valObject, null, null, this.buffer, reporter);
						}
					}
					
					initReader.close();
					
				    maptime += System.currentTimeMillis() - mapbegin;
					   
				    if(staticData != null && joinType == StaticData.MatchType.ONE2ALL){
				    	while(staticData.next()){
				    		mapper.map(null, null, staticData.getKey(), staticData.getValue(), this.buffer, reporter);
				    	}    	
				    }
				    
				    this.mapper.iterate();
					LOG.info("first round finished!");
					this.buffer.stream(iteration++, true);
			    }
			}
			
			synchronized(this){
				try{
					this.wait();										
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					LOG.info("we can stop");
					rof.interrupt();
					rof = null;
					sink.close();
				}
			}

			getProgress().complete();
			mapper.close();
		}
		else{
			boolean pipeline = job.getBoolean("mapred.map.pipeline", false);
			if (numReduceTasks > 0) {
				Class mapCombiner = job.getClass("mapred.map.combiner.class", null);
				if (mapCombiner != null) {
					job.setCombinerClass(mapCombiner);
				}

				Class keyClass = job.getMapOutputKeyClass();
				Class valClass = job.getMapOutputValueClass();
				Class<? extends CompressionCodec> codecClass = null;
				if (conf.getCompressMapOutput()) {
					codecClass = conf.getMapOutputCompressorClass(DefaultCodec.class);
				}
				JOutputBuffer buffer = new JOutputBuffer(bufferUmbilical, this, job, 
						reporter, getProgress(), pipeline, 
						keyClass, valClass, codecClass);
				collector = buffer;
			} else { 
				collector = new DirectMapOutputCollector(umbilical, job, reporter);
			}

			// reinstantiate the split
			try {
				instantiatedSplit = (InputSplit) 
				ReflectionUtils.newInstance(job.getClassByName(splitClass), job);
			} catch (ClassNotFoundException exp) {
				IOException wrap = new IOException("Split class " + splitClass + 
				" not found");
				wrap.initCause(exp);
				throw wrap;
			}
			DataInputBuffer splitBuffer = new DataInputBuffer();
			splitBuffer.reset(split.get(), 0, split.getSize());
			instantiatedSplit.readFields(splitBuffer);

			// if it is a file split, we can give more details
			if (instantiatedSplit instanceof FileSplit) {
				FileSplit fileSplit = (FileSplit) instantiatedSplit;
				job.set("map.input.file", fileSplit.getPath().toString());
				job.setLong("map.input.start", fileSplit.getStart());
				job.setLong("map.input.length", fileSplit.getLength());
			}


			RecordReader rawIn =                  // open input
				job.getInputFormat().getRecordReader(instantiatedSplit, job, reporter);
			this.recordReader = new TrackedRecordReader(rawIn, getCounters());

			MapRunnable runner =
				(MapRunnable)ReflectionUtils.newInstance(job.getMapRunnerClass(), job);

			try {
				long begin = System.currentTimeMillis();
				runner.run(this.recordReader, collector, reporter);      
				getProgress().complete();
				LOG.info("Map task complete in " + (System.currentTimeMillis() - begin) +" ms");	

				if (collector instanceof JOutputBuffer) {
					JOutputBuffer buffer = (JOutputBuffer) collector;
					OutputFile finalOut = buffer.close();
					buffer.free();
					if (finalOut != null) {
						LOG.debug("Register final output");
						bufferUmbilical.output(finalOut);
					}
				}
				else {
					((DirectMapOutputCollector)collector).close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			} finally {
				//close
				this.recordReader.close();                               // close input
			}
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		done(umbilical);
	}
	

	class DirectMapOutputCollector<K, V>
	implements OutputCollector<K, V> {

		private RecordWriter<K, V> out = null;

		private Reporter reporter = null;

		private final Counters.Counter mapOutputRecordCounter;

		@SuppressWarnings("unchecked")
		public DirectMapOutputCollector(TaskUmbilicalProtocol umbilical,
				JobConf job, Reporter reporter) throws IOException {
			this.reporter = reporter;
			String finalName = getOutputName(getPartition());
			FileSystem fs = FileSystem.get(job);

			out = job.getOutputFormat().getRecordWriter(fs, job, finalName, reporter);

			Counters counters = getCounters();
			mapOutputRecordCounter = counters.findCounter(MAP_OUTPUT_RECORDS);
		}

		public void close() throws IOException {
			if (this.out != null) {
				out.close(this.reporter);
			}
		}

		public void collect(K key, V value) throws IOException {
			reporter.progress();
			out.write(key, value);
			mapOutputRecordCounter.increment(1);
		}

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void free() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean read(DataInputStream istream, Header header)
			throws IOException {
		
		CompressionCodec codec = null;
		Class<? extends CompressionCodec> codecClass = null;
	
		if (conf.getCompressMapOutput()) {
			codecClass = conf.getMapOutputCompressorClass(DefaultCodec.class);
			codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
		}
		
		boolean snapshotGen = false;
		boolean stop = false;
		BufferedWriter writer = null;
		int count = 0;
		
		if(iteration >= this.stopIteration){
			stop = true;
			snapshotGen = true;		
		}
		
		if((iteration % snapshotInterval == 0) || stop){
			//perform snapshot 
			snapshotGen = true;
			String outputDir = conf.get("mapred.output.dir");
			int taskid = getTaskID().getTaskID().getId();
			String snapshot = outputDir + "/snapshot" + snapshotIndex + "/part" + taskid;	
			FileSystem hdfs = FileSystem.get(conf);
			
			FSDataOutputStream ostream = hdfs.create(new Path(snapshot), true);
			writer = new BufferedWriter(new OutputStreamWriter(ostream));
			snapshotIndex++;
		}
			
		IFile.Reader reader = new IFile.Reader(conf, istream, header.compressed(), codec, null);
		
		DataInputBuffer key = new DataInputBuffer();
		DataInputBuffer value = new DataInputBuffer();
		Object keyObject = null;
		Object valObject = null;
		
		long mapbegin = System.currentTimeMillis();
		long readbegin = System.currentTimeMillis();
		
		boolean mapsync = conf.getBoolean("mapred.iterative.mapsync", false);
		int partitions = job.getInt("mapred.iterative.partitions", 0);
		while (reader.next(key, value)) {			
			inputKeyDeserializer.open(key);
			inputValDeserializer.open(value);
			keyObject = inputKeyDeserializer.deserialize(keyObject);
			valObject = inputValDeserializer.deserialize(valObject);
			
			if(!stop){
				//cache the input state data, use iterateOver to parse them together
				if((joinType == StaticData.MatchType.ONE2ONE) && mapsync){	
					
					if(((IntWritable)keyObject).get()%partitions == this.getTaskID().id){
						cachewriter.append(keyObject, valObject);
						continue;
					}
					else{
						//the input is not mine, ignore it
						LOG.info("the input key " + ((IntWritable)keyObject).get() + "is not corresponding to the task");
						while (reader.next(key, value)){}
						break;
					}
				}
							
				if(staticData != null){
					if(joinType == StaticData.MatchType.ONE2ONE){
						staticData.next();
						mapper.map(keyObject, valObject, staticData.getKey(), staticData.getValue(), this.buffer, reporter);
					}else if(joinType == StaticData.MatchType.ONE2ALL){
						mapper.map(keyObject, valObject, null, null, this.buffer, reporter);
					}
				}else{
					mapper.map(keyObject, valObject, null, null, this.buffer, reporter);
				}
			}

			if(snapshotGen){
				writer.write(keyObject + "\t" + valObject + "\n");
				count++;
				if(count % 10000 == 0){
					writer.flush();
				}
			}
		}
		
		readtime += System.currentTimeMillis() - readbegin;
		LOG.info("read use " + readtime + " ms.");
		maptime += System.currentTimeMillis() - mapbegin;
		LOG.info("iterative map use " + maptime + " ms.");
		
		if(snapshotGen){
			writer.close();
		}
			
		return true;
	}

	public void iterateOver() throws IOException {
		LOG.info("iterate over data");
		
		if(joinType == StaticData.MatchType.ONE2ONE){
			cachewriter.close();
			Path localpath = new Path("/tmp/cache");
			IFile.Reader reader = new IFile.Reader(conf, localfs, localpath, null, null);
			DataInputBuffer key = new DataInputBuffer();
			DataInputBuffer value = new DataInputBuffer();
			Object keyObject = null;
			Object valObject = null;
			
			if(staticData != null){
				while (reader.next(key, value)) {
					inputKeyDeserializer.open(key);
					inputValDeserializer.open(value);
					keyObject = inputKeyDeserializer.deserialize(keyObject);
					valObject = inputValDeserializer.deserialize(valObject);
					staticData.next();
					mapper.map(keyObject, valObject, staticData.getKey(), staticData.getValue(), this.buffer, reporter);
				}
			}else{
				while (reader.next(key, value)) {
					inputKeyDeserializer.open(key);
					inputValDeserializer.open(value);
					keyObject = inputKeyDeserializer.deserialize(keyObject);
					valObject = inputValDeserializer.deserialize(valObject);

					mapper.map(keyObject, valObject, null, null, this.buffer, reporter);
				}
			}
			
			
			reader.close();
			if(localfs.exists(localpath)){
				localfs.delete(localpath, true);
			};
			cachewriter = new IFile.Writer(conf, localfs, localpath,
					inputKeyClass, inputValClass, null, null);
		}else if(joinType == StaticData.MatchType.ONE2ALL){
			while(staticData.next()){
				mapper.map(null, null, staticData.getKey(), staticData.getValue(), this.buffer, reporter);
			}
		}

	}
	
	public void stream() throws IOException {
		LOG.info(iteration + " and stop at " + stopIteration);
		if(iteration >= this.stopIteration){
			synchronized(this){
				this.notifyAll();
			}		
		}else{
			this.mapper.iterate();
			this.buffer.stream(iteration++, true);
		}
	}
	
	@Override
	public ValuesIterator valuesIterator() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
