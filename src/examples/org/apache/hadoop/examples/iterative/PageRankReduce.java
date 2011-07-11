package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.IterativeReducer;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class PageRankReduce extends MapReduceBase implements
		IterativeReducer<IntWritable, DoubleWritable, IntWritable, DoubleWritable> {

	private Date start;
	private int iteration;
	
	@Override
	public void configure(JobConf conf){
		start = new Date();
		iteration = 0;
	}
	
	@Override
	public void reduce(IntWritable key, Iterator<DoubleWritable> values,
			OutputCollector<IntWritable, DoubleWritable> output, Reporter report)
			throws IOException {
		double rank = 0.0;
		while(values.hasNext()){
			double v = values.next().get();
			if(v == -1) continue;
			rank += v;
			//System.out.println("input:" + key + "\t" + rank);
		}
		
		rank = PageRank.RETAINFAC + rank * PageRank.DAMPINGFAC;
		output.collect(key, new DoubleWritable(rank));
	}
	
	@Override
	public void iterate(){
		iteration++;
		Date current = new Date();
		long passed = (current.getTime() - start.getTime()) / 1000;				
		System.out.println("iteration " + iteration + " timepassed " + passed);	
	}
}
