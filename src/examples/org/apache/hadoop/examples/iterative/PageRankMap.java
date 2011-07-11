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
		
		String temp = "";
		for(String link : links){
			//int linkTo = Integer.parseInt(link);
			output.collect(new IntWritable(Integer.parseInt(link)), new DoubleWritable(delta));
			//System.out.println("output: " + link + " : " + delta);
			temp = link;
		}	
		
		for(int i=0; i<6; i++){
			output.collect(new IntWritable(Integer.parseInt(temp)), new DoubleWritable(-1));
		}
	}

	@Override
	public Path[] initStateData() throws IOException {
		Path remotePath = new Path(this.subRankDir + "/subrank" + taskid);
		Path localPath = new Path("/tmp/iterativehadoop/statedata");
		fs.copyToLocalFile(remotePath, localPath);
		Path[] paths = new Path[1];
		paths[0] = localPath;
		return paths;
	}
	
	@Override
	public Path initStaticData() throws IOException {
		Path remotePath = new Path(this.subGraphsDir + "/subgraph" + taskid);
		Path localPath = new Path("/tmp/iterativehadoop/staticdata");
		fs.copyToLocalFile(remotePath, localPath);
		return localPath;
	}

	@Override
	public void map(IntWritable arg0, DoubleWritable arg1,
			OutputCollector<IntWritable, DoubleWritable> arg2, Reporter arg3)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iterate() {
		// TODO Auto-generated method stub
		
	}


}
