package org.apache.hadoop.examples.iterative;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.IterativeMapper;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import util.MainDriver;
import util.Util;

public class MatrixMul1Map extends MapReduceBase implements
		IterativeMapper<IntIntPairWritable, FloatWritable, NullWritable, NullWritable, IntWritable, IntFloatPairWritable> {

	private int count = 0;
	private JobConf conf;
	private FileSystem fs;
	private String subRankDir;
	private int taskid;
	private int iteration;
	
	@Override
	public void configure(JobConf job){
		conf = job;
		try {
			fs = FileSystem.get(job);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		subRankDir = job.get(MainDriver.SUBRANK_DIR);

		taskid = Util.getTaskId(conf);
	}
	
	@Override
	public void map(IntIntPairWritable key, FloatWritable value, NullWritable nokey, NullWritable noval,
			OutputCollector<IntWritable, IntFloatPairWritable> output, Reporter report)
			throws IOException {
		count++;
		report.setStatus(String.valueOf(count));
		
		output.collect(new IntWritable(key.getY()), new IntFloatPairWritable(key.getX(), value.get()));
		//System.out.println(key.getY() + "\t" + new IntFloatPairWritable(key.getX(), value.get()));
	}

	@Override
	public Path[] initStateData() throws IOException {
		Path remotePath = new Path(this.subRankDir + "/subrank" + taskid);
		Path localPath = new Path("/tmp/imapreduce/statedata");
		fs.copyToLocalFile(remotePath, localPath);
		Path[] paths = new Path[1];
		paths[0] = localPath;
		return paths;
	}

	@Override
	public Path initStaticData() throws IOException {
		return null;
	}

	@Override
	public void iterate() {
		System.out.println("iteration " + (iteration++));
	}

	@Override
	public void map(IntIntPairWritable arg0, FloatWritable arg1,
			OutputCollector<IntWritable, IntFloatPairWritable> arg2,
			Reporter arg3) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
