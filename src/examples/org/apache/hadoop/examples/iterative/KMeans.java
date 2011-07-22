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
	public static final String KMEANS_INITCENTERS_DIR = "kmeans.initcenters.dir";
	public static final String KMEANS_CLUSTER_PATH = "kmeans.cluster.path";
	public static final String KMEANS_CLUSTER_K = "kmeans.cluster.k";
	public static final String KMEANS_DATA_DIR = "kmeans.data.dir";
	public static final String KMEANS_TIME_DIR = "kmeans.time.dir";
	public static final String KMEANS_THRESHOLD = "kmeans.threshold";
	
	private int k;
	private int threshold = 50;
	private int partitions = 0;
	private int interval = 1;
	private int iterations = 20;
	
	private void preprocess(String instate, String instatic) throws Exception {
		String[] args = new String[5];
		args[0] = instate;
		args[1] = instatic;
		args[2] = "Text";
		args[3] = String.valueOf(0);
		args[4] = String.valueOf(partitions);
		
		ToolRunner.run(new Configuration(), new PreProcess(), args);
	}
	
	private void iterateKMeans(String input, String output) throws IOException{

	    JobConf job = new JobConf(getConf());
	    String jobname = "kmeans";
	    job.setJobName(jobname);    
	    
	    job.set(KMEANS_CLUSTER_PATH, output);
	    job.setInt(KMEANS_CLUSTER_K, k);
	    job.setInt(KMEANS_THRESHOLD, threshold);
	    
	    job.set(Common.SUBSTATE, Common.SUBSTATE_DIR);
	    job.set(Common.SUBSTATIC, Common.SUBSTATIC_DIR);
	            
	    if(partitions == 0) partitions = Util.getTTNum(job);
	    
	    //set for iterative process
	    job.setBoolean("mapred.job.iterative", true);  
	    job.setBoolean("mapred.iterative.reducesync", true);
	    job.setBoolean("mapred.iterative.mapsync", true);
	    job.set("mapred.iterative.jointype", "one2all");
	    job.setInt("mapred.iterative.partitions", partitions);
	    job.setInt("mapred.iterative.snapshot.interval", interval);
	    job.setInt("mapred.iterative.stop.iteration", iterations); 	
	    
	    FileInputFormat.addInputPath(job, new Path(input));		//no use
	    
	    job.setJarByClass(KMeans.class);
	    job.setOutputFormat(TextOutputFormat.class);
	    job.setMapperClass(KMeansMap.class);
	    job.setReducerClass(KMeansReduce.class);
	    job.setDataValClass(Text.class);			//set priority class
	    job.setMapOutputKeyClass(IntWritable.class);
	    job.setMapOutputValueClass(Text.class);
	    job.setOutputKeyClass(IntWritable.class);
	    job.setOutputValueClass(Text.class);
  
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
		
	    String input = other_args.get(0);
	    String instate = other_args.get(1);
	    String instatic = other_args.get(2); 
	    String output = other_args.get(3);
		k = Integer.parseInt(args[4]);
	
		preprocess(instate, instatic);
	    iterateKMeans(input, output);
		   		
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
