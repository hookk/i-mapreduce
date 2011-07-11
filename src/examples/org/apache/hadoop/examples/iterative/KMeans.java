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


public class KMeans extends Configured implements Tool {

	public static boolean COMBINE = false;
	
	private String inDir;
	private String outDir;
	private String subRankDir;
	private String subGraphDir;
	private int k;
	private int threshold;
	
	private void iterateKMeans() throws IOException{

	    JobConf job = new JobConf(getConf());
	    String jobname = "kmeans";
	    job.setJobName(jobname);    
	    
	    int ttnum = Util.getTTNum(job);
	    
	    job.set(MainDriver.KMEANS_CLUSTER_PATH, outDir);
	    job.setInt(MainDriver.KMEANS_CLUSTER_K, k);
	    job.set(MainDriver.SUBRANK_DIR, subRankDir);
	    job.set(MainDriver.SUBGRAPH_DIR, subGraphDir);
	    job.setInt(MainDriver.KMEANS_THRESHOLD, threshold);
	    
	    
	            
	    //set for iterative process
	    job.setBoolean("mapred.job.iterative", true);  
	    job.setLong("mapred.iterative.reduce.window", -1);	
	    job.setBoolean("mapred.iterative.reducesync", true);
	    job.setBoolean("mapred.iterative.mapsync", true);
	    job.set("mapred.iterative.jointype", "one2all");
	    job.setInt("mapred.iterative.ttnum", ttnum);
	    job.setInt("mapred.iterative.snapshot.interval", 1000);
	    job.setInt("mapred.iterative.stop.iteration", 20); 	
	    
	    FileInputFormat.addInputPath(job, new Path(inDir));		//no use
	    FileOutputFormat.setOutputPath(job, new Path(outDir));	//no use
	    
	    job.setJarByClass(KMeans.class);

	    job.setOutputFormat(TextOutputFormat.class);
	    
	    job.setMapperClass(KMeansMap.class);
	    job.setReducerClass(KMeansReduce.class);
	    job.setDataValClass(Text.class);			//set priority class
	    job.setMapOutputKeyClass(IntWritable.class);
	    job.setMapOutputValueClass(Text.class);
	    job.setOutputKeyClass(IntWritable.class);
	    job.setOutputValueClass(Text.class);
  
	    job.setNumMapTasks(ttnum);
	    job.setNumReduceTasks(ttnum);

	    JobClient.runJob(job);
	}
	
	@Override
	public int run(String[] args) throws Exception {
		if (args.length != 6) {
		      System.err.println("Usage: kmeans <indir> <outdir> <subrank> <subgraph> <k> <threshold>");
		      System.exit(2);
		}
		
		inDir = args[0];
		outDir = args[1];
	    subRankDir = args[2];
	    subGraphDir = args[3];
		k = Integer.parseInt(args[4]);
		threshold = Integer.parseInt(args[5]);
	
	    iterateKMeans();
		   		
	    return 0;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int res = ToolRunner.run(new Configuration(), new KMeans(), args);
	    System.exit(res);
	}

}
