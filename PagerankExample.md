Before writing the pagerank program, users should use our provided "preprocess" to split the initial state data and the static data to a number of pieces.

`$HADOOP jar example.jar preprocess <in_statedata> <in_staticdata> <out_statedata> <out_staticdata> <statevalueclass> <partitions>`

`<out_statedata>` and `<out_staticdata>` are the DFS paths of the distributed initial state data path and the static data path.

Then, like writing programs in Hadoop, we should write a main program, as well as Mapper class and Reducer class.

Note that, if you would like to use some other datasets, please first transform the input data format to the correct one. iMapReduce requires the input format should be like this:
src1       dest1 dest2 dest3
src2       dest1 dest4
src3       dest2 dest4
src4       dest1

There are two requirement:
  * The src ids should be continuous
  * The src ids should cover all the appeared ids, that is if there are 1000 nodes (including src and dest) there should be 1000 src ids covering all the node ids.
# Main #

```
public class PageRank extends Configured implements Tool {
	private String input;
	private String output;
	private String subGraphDir;
	private String subRankDir;
        private int partitions;
	
	//damping factor
	public static final double DAMPINGFAC = 0.8;
	public static final double RETAINFAC = 0.2;

	private int pagerank() throws IOException{
	        JobConf job = new JobConf(getConf());
	        job.setJobName("pagerank");
       
	        job.set(MainDriver.SUBGRAPH_DIR, subGraphDir);           //static data path
	        job.set(MainDriver.SUBRANK_DIR, subRankDir);             //initial state data path
    
	        FileInputFormat.addInputPath(job, new Path(input));      //input data is fake
	        FileOutputFormat.setOutputPath(job, new Path(output));
	        job.setOutputFormat(TextOutputFormat.class);
	    
	        //set for iterative process   
	        job.setBoolean("mapred.job.iterative", true);  
	        job.setBoolean("mapred.iterative.reducesync", true);
	        job.set("mapred.iterative.jointype", "one2one");
	        job.setInt("mapred.iterative.partitions", partitions);
	        job.setInt("mapred.iterative.snapshot.interval", 10);
	        job.setInt("mapred.iterative.stop.iteration", 50);
	    
	        job.setJarByClass(PageRank.class);
	        job.setMapperClass(PageRankMap.class);	
	        job.setReducerClass(PageRankReduce.class);
	        job.setDataKeyClass(IntWritable.class);                 //set static data key class
	        job.setDataValClass(Text.class);                        //set static data value class
	        job.setMapOutputKeyClass(IntWritable.class);
	        job.setMapOutputValueClass(DoubleWritable.class);
	        job.setOutputKeyClass(IntWritable.class);
	        job.setOutputValueClass(DoubleWritable.class);    
	        job.setPartitionerClass(UniDistIntPartitioner.class);   //a particular system-provided partitioner that corresponds to the partitioner of the preprocess job

	        job.setNumMapTasks(partitions);
	        job.setNumReduceTasks(partitions);
	    
	        JobClient.runJob(job);
	        return 0;
	}

	@Override
	public int run(String[] args) throws Exception {
                if (args.length != 5) {
		      System.err.println("Usage: pagerank <indir> <outdir> <subrank> <subgraph> <partitions>");
		      System.exit(2);
		}
	    
		input = args[0];
	        output = args[1];
	        subRankDir = args[2];
	        subGraphDir = args[3];  
                partitions = Integer.parseInt(args[4]);  
    
	        pagerank();
	    
		return 0;
	}


	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new PageRank(), args);
	        System.exit(res);
	}
}
```


# Map #

```
public class PageRankMap extends MapReduceBase implements IterativeMapper<IntWritable, DoubleWritable, IntWritable, Text, IntWritable, DoubleWritable> {

	private JobConf conf;
	private FileSystem fs;
	private String subGraphsDir;
	private String subRankDir;
	private int taskid;
	 
	@Override
	public void configure(JobConf job) {
		conf = job;
		try {
			fs = FileSystem.get(job);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		subRankDir = job.get(MainDriver.SUBRANK_DIR);
		subGraphsDir = job.get(MainDriver.SUBGRAPH_DIR);
		taskid = Util.getTaskId(conf);
	}
	
	@Override
	public void map(IntWritable key, DoubleWritable value, IntWritable datakey, Text dataval, OutputCollector<IntWritable, DoubleWritable> output, Reporter report) throws IOException {

		report.setStatus(key.toString());

		double rank = value.get();
		String linkstring = dataval.toString();
		double retain = PageRank.RETAINFAC;
		output.collect(key, new DoubleWritable(retain));
		
		String[] links = linkstring.split(" ");	
		double delta = rank * PageRank.DAMPINGFAC / links.length;
		
		for(String link : links){
			if(link.equals("")) continue;
			output.collect(new IntWritable(Integer.parseInt(link)), new DoubleWritable(delta));
		}	
	}

	@Override
	public Path[] initStateData() throws IOException {
		Path remotePath = new Path(this.subRankDir + "/subrank" + taskid);
		Path localPath = new Path("/tmp/iterativehadoop/statedata");
		fs.copyToLocalFile(remotePath, localPath);    //copy to local for fast access
		Path[] paths = new Path[1];
		paths[0] = localPath;
		return paths;
	}
	
	@Override
	public Path initStaticData() throws IOException {
		Path remotePath = new Path(this.subGraphsDir + "/subgraph" + taskid);
		Path localPath = new Path("/tmp/iterativehadoop/staticdata");
		fs.copyToLocalFile(remotePath, localPath);    //copy to local for fast access
		return localPath;
	}
}
```

## Reduce ##

```
public class PageRankReduce extends MapReduceBase implements
		IterativeReducer<IntWritable, DoubleWritable,  IntWritable, DoubleWritable> {

	private Date start;
	private int iteration;
	
	@Override
	public void configure(JobConf conf){
		start = new Date();
		iteration = 0;
	}
	
	@Override
	public void reduce(IntWritable key, Iterator<DoubleWritable> values, OutputCollector<IntWritable, DoubleWritable> output, Reporter report) throws IOException {
		double rank = 0.0;
		while(values.hasNext()){
			double v = values.next().get();
			if(v == -1) continue;
			rank += v;
		}
		
		rank = PageRank.RETAINFAC + rank * PageRank.DAMPINGFAC;
		output.collect(key, new DoubleWritable(rank));
	}
	
	@Override
	public void iterate(){
		iteration++;
		Date current = new Date();
		long passed = (current.getTime() - start.getTime()) / 1000;				
		System.out.println("iteration " + iteration + " timepassed " + passed);	  //we want to record the running time
	}
}
```