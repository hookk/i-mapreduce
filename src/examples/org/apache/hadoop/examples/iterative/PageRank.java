package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class PageRank extends Configured implements Tool {
	private String input;
	private String output;
	private String subGraphDir;
	private String subRankDir;
	
	//damping factor
	public static final double DAMPINGFAC = 0.8;
	public static final double RETAINFAC = 0.2;

	
	private int pagerank() throws IOException{
	    JobConf job = new JobConf(getConf());
	    String jobname = "pagerank";
	    job.setJobName(jobname);
       
	    job.set(MainDriver.SUBGRAPH_DIR, subGraphDir);
	    job.set(MainDriver.SUBRANK_DIR, subRankDir);
    
	    FileInputFormat.addInputPath(job, new Path(input));
	    FileOutputFormat.setOutputPath(job, new Path(output));
	    job.setOutputFormat(TextOutputFormat.class);
	    
	    int ttnum = Util.getTTNum(job);
	        
	    //set for iterative process   
	    job.setBoolean("mapred.job.iterative", true);  
	    job.setLong("mapred.iterative.reduce.window", -1);		//set -1 more accurate, ow more stable
	    job.setBoolean("mapred.iterative.reducesync", true);
	    //job.setBoolean("mapred.iterative.mapsync", true);
	    job.set("mapred.iterative.jointype", "one2one");
	    job.setInt("mapred.iterative.ttnum", ttnum);
	    job.setInt("mapred.iterative.snapshot.interval", 10);
	    job.setInt("mapred.iterative.stop.iteration", 50);
	    
	    job.setJarByClass(PageRank.class);
	    job.setMapperClass(PageRankMap.class);	
	    job.setReducerClass(PageRankReduce.class);
	    job.setDataKeyClass(IntWritable.class);
	    job.setDataValClass(Text.class);			//set priority class
	    job.setMapOutputKeyClass(IntWritable.class);
	    job.setMapOutputValueClass(DoubleWritable.class);
	    job.setOutputKeyClass(IntWritable.class);
	    job.setOutputValueClass(DoubleWritable.class);
	    
	    job.setPartitionerClass(UniDistIntPartitioner.class);

	    job.setNumMapTasks(ttnum);
	    job.setNumReduceTasks(ttnum);
	    
	    JobClient.runJob(job);
	    return 0;
	}
	
	private void printUsage() {
		System.out.println("pagerank [-p partitions] <inStateDir> <inStaticDir> <outDir>");
		System.out.println("\t-p # of parittions");
		ToolRunner.printGenericCommandUsage(System.out);
	}
	
	@Override
	public int run(String[] args) throws Exception {
		if (args.length < 3) {
			printUsage();
			return -1;
		}
		
		List<String> other_args = new ArrayList<String>();
		for(int i=0; i < args.length; ++i) {
		      try {
		          if ("-s".equals(args[i])) {
		        	float freq = Float.parseFloat(args[++i]);
		        	/* Jobs will perform snapshots */
		          	wordcountJob.setFloat("mapred.snapshot.frequency", freq);
		          	topkJob.setFloat("mapred.snapshot.frequency", freq);
		          	topkJob.setBoolean("mapred.job.input.snapshots", true);

		          	/* Wordcount will pipeline. */
		          	wordcountJob.setBoolean("mapred.map.pipeline", true);
		          	wordcountJob.setBoolean("mapred.reduce.pipeline", true);
		          	/* TopK does not pipeline. */
		          	topkJob.setBoolean("mapred.map.pipeline", true);
		          	topkJob.setBoolean("mapred.reduce.pipeline", false);
		        	pipelineJob = true;
		          } else if ("-R".equals(args[i])) {
		        	  reduceOutput = false;
		          } else if ("-c".equals(args[i])) {
		        	  topkJob.setBoolean("mapred.job.comparelists", true);
		          } else if ("-x".equals(args[i])) {
		        	  xmlmapper = true;
		          } else if ("-p".equals(args[i])) {
		          	wordcountJob.setBoolean("mapred.map.pipeline", true);
		          	topkJob.setBoolean("mapred.map.pipeline", true);
		          } else if ("-P".equals(args[i])) {
		    		pipelineJob = true;
		          	wordcountJob.setBoolean("mapred.reduce.pipeline", true);
		    	  } else if ("-m".equals(args[i])) {
		    		  wordcountJob.setNumMapTasks(Integer.parseInt(args[++i]));
		    	  } else if ("-r".equals(args[i])) {
		    		  wordcountJob.setNumReduceTasks(Integer.parseInt(args[++i]));
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
	    
		input = args[0];
	    output = args[1];
	    subRankDir = args[2];
	    subGraphDir = args[3];    
    
	    pagerank();
	    
		return 0;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int res = ToolRunner.run(new Configuration(), new PageRank(), args);
	    System.exit(res);
	}
}
