package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.Random;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class Text2IntMap extends MapReduceBase implements
		Mapper<LongWritable, Text, IntWritable, Text> {
	private int deadendsCounter = 0;
	private int badCounter = 0;
	private int totalCounter = 0;
	private int expect = -1;
	private int totalPages = 0;
	
	@Override
	public void configure(JobConf job){
		totalPages = job.getInt(MainDriver.PG_TOTAL_PAGES, -1);
	}
	
	@Override
	public void map(LongWritable arg0, Text value,
			OutputCollector<IntWritable, Text> arg2, Reporter arg3)
			throws IOException {
		// TODO Auto-generated method stub
		String edge = value.toString();
		String[] temp = edge.split("\t");
		
		if(temp.length == 2){
			int page = Integer.parseInt(temp[0]);
			
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
			arg2.collect(new IntWritable(page), new Text(temp[1]));
			expect = page + 1;			
			totalCounter++;		
		}else{
			badCounter++;
		}
					
		arg3.setStatus(String.valueOf(deadendsCounter) + ":" + String.valueOf(badCounter) + ":"+ String.valueOf(totalCounter));
	}

}
