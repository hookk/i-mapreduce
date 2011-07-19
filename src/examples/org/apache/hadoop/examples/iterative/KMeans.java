package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
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
	private int threshold = 50;
	private int partitions = 0;
	private int interval = 1000;
	private int iterations = 20;
	
	private void iterateKMeans() throws IOException{

	    JobConf job = new JobConf(getConf());
	    String jobname = "kmeans";
	    job.setJobName(jobname);    
	    
	    job.set(MainDriver.KMEANS_CLUSTER_PATH, outDir);
	    job.setInt(MainDriver.KMEANS_CLUSTER_K, k);
	    job.set(MainDriver.SUBRANK_DIR, subRankDir);
	    job.set(MainDriver.SUBGRAPH_DIR, subGraphDir);
	    job.setInt(MainDriver.KMEANS_THRESHOLD, threshold);
	            
	    //set for iterative process
	    job.setBoolean("mapred.job.iterative", true);  
	    job.setBoolean("mapred.iterative.reducesync", true);
	    job.setBoolean("mapred.iterative.mapsync", true);
	    job.set("mapred.iterative.jointype", "one2all");
	    job.setInt("mapred.iterative.ttnum", partitions);
	    job.setInt("mapred.iterative.snapshot.interval", 1000);
	    job.setInt("mapred.iterative.stop.iteration", 20); 	
	    
	    FileInputFormat.addInputPath(job, new Path(inDir));		//no use
	    
	    job.setJarByClass(KMeans.class);
	    job.setOutputFormat(TextOutputFormat.class);
	    job.setMapperClass(KMeansMap.class);
	    job.setReducerClass(KMeansReduce.class);
	    job.setDataValClass(Text.class);			//set priority class
	    job.setMapOutputKeyClass(IntWritable.class);
	    job.setMapOutputValueClass(Text.class);
	    job.setOutputKeyClass(IntWritable.class);
	    job.setOutputValueClass(Text.class);
  
	    if(partitions == 0) partitions = Util.getTTNum(job);
	    job.setNumMapTasks(partitions);
	    job.setNumReduceTasks(partitions);

	    JobClient.runJob(job);
	}
	
	private void printUsage() {
		System.out.println("kmeans [-p partitions] <InTemp> <inStateDir> <inStaticDir> <outDir> <k>");
		System.out.println("\t-p # of parittions\n\t-i snapshot interval\n\t-I # of iterations\n\t -t stop threshold");
		ToolRunner.printGenericCommandUsage(System.out);
	}
	
	@Override
	public int run(String[] args) throws Exception {
		if (args.length < 5) {
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
		          } else if ("-I".equals(args[i])) {
		        	iterations = Integer.parseInt(args[++i]);
		          } else if ("-t".equals(args[i])) {
		        	threshold = Integer.parseInt(args[++i]);
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
		
	    if (other_args.size() < 5) {
		      System.out.println("ERROR: Wrong number of parameters: " +
		                         other_args.size() + ".");
		      printUsage(); return -1;
		}
		
	    inDir = other_args.get(0);
	    subRankDir = other_args.get(1);
	    subGraphDir = other_args.get(2); 
	    outDir = other_args.get(3);
		k = Integer.parseInt(args[4]);
	
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
