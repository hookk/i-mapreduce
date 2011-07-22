package org.apache.hadoop.examples.iterative;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.SequenceFileInputFormat;
import org.apache.hadoop.mapred.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class MatrixPower extends Configured implements Tool {

	private int interval = 5;
	private int partitions = 0;
	
	private void printUsage() {
		System.out.println("matrixpower [-p partitions] <input M> <input N> <output C> <power>");
		System.out.println("\t-p # of parittions\n\t-i snapshot interval");
		ToolRunner.printGenericCommandUsage(System.out);
	}
	
	@Override
	public int run(String[] args) throws Exception {
		if (args.length != 4) {
		      System.err.println("Usage: matrixpower <input M> <input N> <output C> <power>");
		      System.exit(2);
		}
		if (args.length < 4) {
			printUsage();
			return -1;
		}
		
		List<String> other_args = new ArrayList<String>();
		for(int i=0; i < args.length; ++i) {
		      try {
		    	  if ("-p".equals(args[i])) {
			        partitions = Integer.parseInt(args[++i]);
		          } else if ("-i".equals(args[i])) {
		        	interval = Integer.parseInt(args[++i]);
		          } else {
		    		  other_args.add(args[i]);
		    	  }
		      } catch (NumberFormatException except) {
		        System.out.println("ERROR: Integer expected instead of " + args[i]);
		        printUsage();
		        return -1;
		      } catch (ArrayIndexOutOfBoundsException except) {
		        System.out.println("ERROR: Required parameter missing from " +
		                           args[i-1]);
		        printUsage();
		        return -1;
		      }
		}
		
	    if (other_args.size() < 4) {
		      System.out.println("ERROR: Wrong number of parameters: " +
		                         other_args.size() + ".");
		      printUsage(); return -1;
		}
	    
		String inM = other_args.get(0);
		String inN = other_args.get(1);
		String out = other_args.get(2);
		int power = Integer.parseInt(other_args.get(3));

		JobConf job1 = new JobConf(getConf(), MatrixPower.class);
		job1.setJobName("phase 1");

		JobConf job2 = new JobConf(getConf(), MatrixPower.class);
		job2.setJobName("phase 2");
    	 	
		Path tempDir =
				new Path("temp-"+
						Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));
		
	    if(partitions == 0) partitions = Util.getTTNum(job1);
	    
	    //set for iterative process   
	    job1.setBoolean("mapred.job.iterative", true);  
	    job1.setBoolean("mapred.iterative.reducesync", true);
	    job1.set("mapred.iterative.jointype", "one2one");
	    job1.setInt("mapred.iterative.partitions", partitions);
	    job1.setInt("mapred.iterative.snapshot.interval", interval);
	    job1.setInt("mapred.iterative.stop.iteration", power);
	    job1.set("mapred.iterative.successor", job2.getJobName());
	    job1.setBoolean("mapred.iterative.firstjob", true);
	    
	    job2.setBoolean("mapred.job.iterative", true);  
	    job2.setBoolean("mapred.iterative.reducesync", true);
	    job2.set("mapred.iterative.jointype", "one2one");
	    job2.setInt("mapred.iterative.partitions", partitions);
	    job2.setInt("mapred.iterative.stop.iteration", power);
	    job2.set("mapred.iterative.successor", job1.getJobName());
	    job2.setBoolean("mapred.iterative.firstjob", false);
	    
		/** phase 1 **/
	    FileInputFormat.addInputPath(job1, new Path(inN));
	    job1.set(Common.SUBSTATE, inN);
	    FileOutputFormat.setOutputPath(job1, tempDir);

	    job1.setOutputFormat(SequenceFileOutputFormat.class);
	    
	    job1.setMapperClass(MatrixMul1Map.class);
	    job1.setReducerClass(MatrixMul1Reduce.class);
	    job1.setMapOutputKeyClass(IntWritable.class);
	    job1.setMapOutputValueClass(IntFloatPairWritable.class);
	    job1.setOutputKeyClass(IntWritable.class);
	    job1.setOutputValueClass(Text.class);
	    job1.setPartitionerClass(UniDistIntPartitioner.class);
	    
	    job1.setNumMapTasks(partitions);
	    job1.setNumReduceTasks(partitions);

	    /** phase 2 **/
	    FileInputFormat.setInputPaths(job2, inM);
		job2.setInputFormat(SequenceFileInputFormat.class);
		FileOutputFormat.setOutputPath(job2, new Path(out));

	    job2.set(Common.SUBSTATIC, inM);

	    job2.setMapperClass(MatrixMul2Map.class);
	    job2.setReducerClass(MatrixMul2Reduce.class);
	    job2.setDataKeyClass(IntWritable.class);
	    job2.setDataValClass(Text.class);	
	    job2.setMapOutputKeyClass(IntIntPairWritable.class);
	    job2.setMapOutputValueClass(FloatWritable.class);
	    job2.setOutputKeyClass(IntIntPairWritable.class);
	    job2.setOutputValueClass(FloatWritable.class);

	    job2.setNumMapTasks(partitions);
	    job2.setNumReduceTasks(partitions);

		JobClient  client  = new JobClient(job1);
		List<JobConf> jobs = new ArrayList<JobConf>();
		jobs.add(job1);
		jobs.add(job2);
		List<RunningJob> rjobs = client.submitJobs(jobs);
		for (int i = 0; i < rjobs.size(); i++) {
			RunningJob rjob = rjobs.get(i);
			JobConf job = jobs.get(i);
			client.report(rjob, job);
		}
		
		FileSystem.get(job1).delete(tempDir, true);
		return 0;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new MatrixPower(), args);
	    System.exit(res);	
	}
}
