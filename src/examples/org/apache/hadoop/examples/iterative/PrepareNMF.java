package org.apache.hadoop.examples.iterative;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapred.lib.NullOutputFormat;



public class PrepareNMF extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		
		if (args.length != 3) {
		      System.err.println("Usage: preparenmf <input M> <out> <partitions>");
		      System.exit(2);
		}
		
		String inM = args[0];
		String out = args[1];
		int partitions = Integer.parseInt(args[2]);
		
		JobConf job1 = new JobConf(getConf());
	    FileInputFormat.addInputPath(job1, new Path(inM));
	    FileOutputFormat.setOutputPath(job1, new Path(out));
	    job1.set(Common.SUBSTATIC, out);
	     
	    job1.setInputFormat(KeyValueTextInputFormat.class);
	    job1.setOutputFormat(NullOutputFormat.class);
	    
	    job1.setJarByClass(PrepareNMF.class);
	    job1.setMapperClass(PrepareMap.class);
	    job1.setReducerClass(PrepareReduce.class);
	    job1.setMapOutputKeyClass(IntWritable.class);
	    job1.setMapOutputValueClass(IntFloatPairWritable.class);
	    job1.setOutputKeyClass(NullWritable.class);
	    job1.setOutputValueClass(NullWritable.class);
	    job1.setNumReduceTasks(partitions);
	    job1.setPartitionerClass(UniDistIntPartitioner.class);
	    
	    JobClient.runJob(job1);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new PrepareNMF(), args);
	    System.exit(res);	
	}
}
