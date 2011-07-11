package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.Random;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;



public class StaticDistributeMap extends MapReduceBase implements
		Mapper<Text, Text, IntWritable, Text> {
	private int deadendsCounter = 0;
	private int badCounter = 0;
	private int totalCounter = 0;
	private int expect = -1;
	private int totalPages = 0;
	private int ttnum = 0;
	private OutputCollector<IntWritable, Text> output;
	private int taskid = -1;
	
	@Override
	public void configure(JobConf job){
		totalPages = job.getInt(MainDriver.PG_TOTAL_PAGES, -1);
		ttnum = Util.getTTNum(job);
		taskid = Util.getTaskId(job);
	}
	
	@Override
	public void map(Text arg0, Text value,
			OutputCollector<IntWritable, Text> arg2, Reporter arg3)
			throws IOException {
		if(output == null) output = arg2;
		int page = Integer.parseInt(arg0.toString());
		
		//preprocess dead ends
		while(page > expect && expect != -1){
			Random rand = new Random();

			int links = rand.nextInt(10) + 1;
			for(int i=0; i<links; i++){
				int linkTo = rand.nextInt(totalPages-1);
				arg2.collect(new IntWritable(expect), new Text(String.valueOf(linkTo)));
			}
			
			expect++;
			deadendsCounter++;	
			totalCounter++;
			arg3.setStatus(String.valueOf(deadendsCounter) + ":" + String.valueOf(badCounter) + ":"+ String.valueOf(totalCounter));
		}
		
		//normal one			
		arg2.collect(new IntWritable(page), value);
		expect = page + 1;			
		totalCounter++;		
						
		arg3.setStatus(String.valueOf(deadendsCounter) + ":" + String.valueOf(badCounter) + ":"+ String.valueOf(totalCounter));
	}
	
	@Override
	public void close(){
		int expect = totalPages/ttnum;
		if(expect > totalCounter){
			while(totalCounter < expect){
				try {
					output.collect(new IntWritable(totalCounter * ttnum + taskid), new Text(""));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				totalCounter++;
			}
		}
	}
}
