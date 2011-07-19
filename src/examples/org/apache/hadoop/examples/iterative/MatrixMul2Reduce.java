package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapred.IterativeReducer;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MatrixMul2Reduce extends MapReduceBase implements
		IterativeReducer<IntIntPairWritable, FloatWritable, IntIntPairWritable, FloatWritable> {
	
	private Date start;
	private int iteration;
	
	@Override
	public void configure(JobConf conf){
		start = new Date();
		iteration = 0;
	}
	
	@Override
	public void reduce(IntIntPairWritable key, Iterator<FloatWritable> values,
			OutputCollector<IntIntPairWritable, FloatWritable> output, Reporter report)
			throws IOException {
		report.setStatus(key.toString());
		
		float val = 0;
		while(values.hasNext()){
			val += values.next().get();
		}
		
		if(val > 0.1)
		output.collect(new IntIntPairWritable(key.getX(), key.getY()), new FloatWritable(val));
		//System.out.println(new IntIntPairWritable(key.getX(), key.getY()) + "\t" + val);
	}

	@Override
	public void iterate(){
		iteration++;
		Date current = new Date();
		long passed = (current.getTime() - start.getTime()) / 1000;				
		System.out.println("iteration " + iteration + " timepassed " + passed);	
	}
}
