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
	private int partitions = 0;
	private int interval = 5;
	private int iterations = 20;
	private int nodes = 1000000;
	
	private void preprocess(String instate, String instatic, String outstate, String outstatic) throws Exception {
		String[] args = new String[6];
		args[0] = instate;
		args[1] = instatic;
		args[2] = outstate;
		args[3] = outstatic;
		args[4] = "Text";
		args[5] = String.valueOf(nodes);
		
		PreProcess.main(args);
	}
	
	private int bsearch() throws IOException{
	    JobConf job = new JobConf(getConf());
	    String jobname = "shortest path";
	    job.setJobName(jobname);
       
	    job.set(MainDriver.SUBRANK_DIR, subRankDir);
	    job.set(MainDriver.SUBGRAPH_DIR, subGraphDir);
	    
	    FileInputFormat.addInputPath(job, new Path(input));
	    FileOutputFormat.setOutputPath(job, new Path(output));
	    job.setOutputFormat(TextOutputFormat.class);
	    
	    //set for iterative process
	    job.setBoolean("mapred.job.iterative", true);  
	    job.setBoolean("mapred.iterative.reducesync", true);
	    job.set("mapred.iterative.jointype", "one2one");
	    job.setInt("mapred.iterative.ttnum", partitions);
	    job.setInt("mapred.iterative.snapshot.interval", interval);
	    job.setInt("mapred.iterative.stop.iteration", iterations); 
	    
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

	    if(partitions == 0) partitions = Util.getTTNum(job);
	    job.setNumMapTasks(partitions);
	    job.setNumReduceTasks(partitions);
	    
	    JobClient.runJob(job);
	    return 0;
	}
	
	private void printUsage() {
		System.out.println("bsearch [-p partitions] <InTemp> <inStateDir> <inStaticDir> <outDir>");
		System.out.println("\t-p # of parittions\n\t-i snapshot interval\n\t-I # of iterations\n\t-n # of nodes");
		ToolRunner.printGenericCommandUsage(System.out);
	}
	
	@Override
	public int run(String[] args) throws Exception {
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
		          } else if ("-I".equals(args[i])) {
		        	iterations = Integer.parseInt(args[++i]);
		          } else if ("-n".equals(args[i])) {
		        	nodes = Integer.parseInt(args[++i]);
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
	    
		input = other_args.get(0);
	    String instate = other_args.get(1);
	    String instatic = other_args.get(2); 
	    output = other_args.get(3);
	    
	    preprocess(instate, instatic, subRankDir, subGraphDir);
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
