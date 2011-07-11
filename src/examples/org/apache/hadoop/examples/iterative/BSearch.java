package org.apache.hadoop.examples.iterative;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class BSearch extends Configured implements Tool {
	private String input;
	private String output;
	private String subRankDir;
	private String subGraphDir;
	
	private int bsearch() throws IOException{
	    JobConf job = new JobConf(getConf());
	    String jobname = "shortest path";
	    job.setJobName(jobname);
       
	    job.set(MainDriver.SUBRANK_DIR, subRankDir);
	    job.set(MainDriver.SUBGRAPH_DIR, subGraphDir);
	    
	    FileInputFormat.addInputPath(job, new Path(input));
	    FileOutputFormat.setOutputPath(job, new Path(output));
	    job.setOutputFormat(TextOutputFormat.class);
	    
	    int ttnum = Util.getTTNum(job);

	    //set for iterative process
	    job.setBoolean("mapred.job.iterative", true);  
	    job.setLong("mapred.iterative.reduce.window", -1);		//set -1 more accurate, ow more stable
	    job.setBoolean("mapred.iterative.reducesync", true);
	    //job.setBoolean("mapred.iterative.simsync", true);
	    job.set("mapred.iterative.jointype", "one2one");
	    job.setInt("mapred.iterative.ttnum", ttnum);
	    job.setInt("mapred.iterative.snapshot.interval", 5);
	    job.setInt("mapred.iterative.stop.iteration", 20); 
	    
	    job.setJarByClass(BSearch.class);
	    job.setMapperClass(BSearchMap.class);	
	    job.setReducerClass(BSearchReduce.class);
	    job.setDataKeyClass(IntWritable.class);
	    job.setDataValClass(Text.class);	
	    job.setMapOutputKeyClass(IntWritable.class);
	    job.setMapOutputValueClass(Text.class);
	    job.setOutputKeyClass(IntWritable.class);
	    job.setOutputValueClass(Text.class);
	    job.setPartitionerClass(UniDistIntPartitioner.class);

	    job.setNumMapTasks(ttnum);
	    job.setNumReduceTasks(ttnum);
	    
	    JobClient.runJob(job);
	    return 0;
	}
	@Override
	public int run(String[] args) throws Exception {
		if (args.length != 4) {
		      System.err.println("Usage: bsearch <indir> <outdir> <subrank> <subgraph>");
		      System.exit(2);
		}
	    
		input = args[0];
	    output = args[1];
	    subRankDir = args[2];
	    subGraphDir = args[3];
    
	    bsearch();
	    
		return 0;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int res = ToolRunner.run(new Configuration(), new BSearch(), args);
	    System.exit(res);
	}

}
