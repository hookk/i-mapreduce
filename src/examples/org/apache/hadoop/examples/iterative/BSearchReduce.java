package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.IterativeReducer;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class BSearchReduce extends MapReduceBase implements
		IterativeReducer<IntWritable, Text, IntWritable, Text> {
	
	private Date start;
	private int iteration;
	
	@Override
	public void configure(JobConf conf){
		start = new Date();
		iteration = 0;
	}
	
	//format node	f:len
	//       node	v:shortest_length
	@Override
	public void reduce(IntWritable key, Iterator<Text> values,
			OutputCollector<IntWritable, Text> output, Reporter report)
			throws IOException {
		//System.out.println("input: " + key);
		boolean fIsShorter = false;
		int len = -1;
		int min_len = Integer.MAX_VALUE;
		
		while(values.hasNext()){		
			String value = values.next().toString();
			
			//System.out.println("input: " + key + " : " + value + " : " + min_len);
			
			int index = value.indexOf(":");
			if(index == -1){
				System.out.println("some thing wrong, no :");
				continue;
			}
			String indicator = value.substring(0, index);
						
			if(indicator.equals("f")){
				//hasf = true;
				len = Integer.parseInt(value.substring(index+1));
				if(len<min_len){
					min_len = len;
					fIsShorter = true;
				}
			}else if(indicator.equals("v")){
				len = Integer.parseInt(value.substring(index+1));
				if(len<min_len){
					min_len = len;
					fIsShorter = false;
				}
			}
		}
		
		/**
		 * v is value, which has the lowest priority, since we just 
		 * expand frontier, and frontier's priority is inversely proportional
		 * to the length, the shorter the length, the higher priority it has
		 */
		//if node distance is less than the stored distance, than expand it again
		if(fIsShorter){
			String out = "f:" + String.valueOf(min_len);
			//for expr
			//String out = "f:" + String.valueOf(min_len) + "," + links;
			output.collect(new IntWritable(key.get()), new Text(out));
			//System.out.println(key + " : " + out);
		}else{
			String out = "v:" + String.valueOf(min_len);
			//for expr
			//String out = "v:" + String.valueOf(min_len) + "," + links ;
			output.collect(new IntWritable(key.get()), new Text(out));
		}	
	}
	
	@Override
	public void iterate(){
		iteration++;
		Date current = new Date();
		long passed = (current.getTime() - start.getTime()) / 1000;		
		
		System.out.println("iteration " + iteration + " timepassed " + passed);		
	}
}
