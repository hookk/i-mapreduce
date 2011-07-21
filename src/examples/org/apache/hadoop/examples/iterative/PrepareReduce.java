package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.IFile;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class PrepareReduce extends MapReduceBase implements
		Reducer<IntWritable, IntFloatPairWritable, NullWritable, NullWritable> {

	private int count = 0;

	private FSDataOutputStream out;
	private IFile.Writer<IntWritable, Text> writer;
	
	@Override
	public void configure(JobConf job){
		String outDir = job.get(Common.SUBSTATIC);
		FileSystem fs;
		try {
			fs = FileSystem.get(job);
			int taskid = Util.getTaskId(job);
			Path outPath = new Path(outDir + taskid);
			out = fs.create(outPath);
			writer = new IFile.Writer<IntWritable, Text>(job, out, 
					IntWritable.class, Text.class, null, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Override
	public void reduce(IntWritable key, Iterator<IntFloatPairWritable> values,
			OutputCollector<NullWritable, NullWritable> output, Reporter report)
			throws IOException {
		count++;
		report.setStatus(String.valueOf(count));
		
		HashMap<Integer, Float> A = new HashMap<Integer, Float>();
		
		while(values.hasNext()){
			IntFloatPairWritable value = values.next();
			A.put(value.getX(), value.getY());
		}
		
		String out = "";
		for(Map.Entry<Integer, Float> entry : A.entrySet()){
			out += entry.getKey() + "," + entry.getValue() + " ";
		}

		writer.append(key, new Text(out));
	}

	@Override
	public void close(){
		try {
			writer.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
