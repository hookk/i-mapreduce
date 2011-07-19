package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.IterativeReducer;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MatrixMul1Reduce extends MapReduceBase implements
		IterativeReducer<IntWritable, IntFloatPairWritable, IntWritable, Text> {

	private int count = 0;
	private Date start;
	private int iteration;
	
	@Override
	public void configure(JobConf conf){
		start = new Date();
		iteration = 0;
	}
	
	@Override
	public void reduce(IntWritable key, Iterator<IntFloatPairWritable> values,
			OutputCollector<IntWritable, Text> output, Reporter report)
			throws IOException {
		count++;
		report.setStatus(String.valueOf(count));
		
		HashMap<Integer, Float> B = new HashMap<Integer, Float>();
		
		while(values.hasNext()){
			IntFloatPairWritable value = values.next();

			B.put(value.getX(), value.getY());
		}
		
		String out = "";
		for(Map.Entry<Integer, Float> entry : B.entrySet()){
			out += entry.getKey() + "," + entry.getValue() + " ";
		}
		
		output.collect(key, new Text(out));
		//System.out.println(key + "\t" + out);
	}

	@Override
	public void iterate() {
		iteration++;
		Date current = new Date();
		long passed = (current.getTime() - start.getTime()) / 1000;				
		System.out.println("iteration " + iteration + " timepassed " + passed);	
	}

}
