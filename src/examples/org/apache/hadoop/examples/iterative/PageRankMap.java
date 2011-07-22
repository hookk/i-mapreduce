package org.apache.hadoop.examples.iterative;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.IterativeMapper;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class PageRankMap extends MapReduceBase implements
		IterativeMapper<IntWritable, DoubleWritable, IntWritable, Text, IntWritable, DoubleWritable> {

	private FileSystem fs;
	private String subGraphsDir;
	private String subRankDir;
	private int taskid;
	 
	@Override
	public void configure(JobConf job) {
		try {
			fs = FileSystem.get(job);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		subRankDir = job.get(Common.SUBSTATE);
		subGraphsDir = job.get(Common.SUBSTATIC);
		taskid = Util.getTaskId(job);
	}
	
	@Override
	public void map(IntWritable key, DoubleWritable value, IntWritable datakey,
			Text dataval, OutputCollector<IntWritable, DoubleWritable> output, Reporter report)
			throws IOException {
		double rank = value.get();
		//System.out.println("input : " + key + " : " + rank);
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
		Path remotePath = new Path(this.subRankDir + taskid);
		Path localPath = new Path(Common.LOCAL_STATE + taskid);
		fs.copyToLocalFile(remotePath, localPath);
		Path[] paths = new Path[1];
		paths[0] = localPath;
		return paths;
	}
	
	@Override
	public Path initStaticData() throws IOException {
		Path remotePath = new Path(this.subGraphsDir + taskid);
		Path localPath = new Path(Common.LOCAL_STATIC + taskid);
		fs.copyToLocalFile(remotePath, localPath);
		return localPath;
	}

	@Override
	public void map(IntWritable arg0, DoubleWritable arg1,
			OutputCollector<IntWritable, DoubleWritable> arg2, Reporter arg3)
			throws IOException {

	}

	@Override
	public void iterate() {

	}
}
