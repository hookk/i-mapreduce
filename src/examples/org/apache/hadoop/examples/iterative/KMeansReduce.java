package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.IterativeReducer;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;



public class KMeansReduce extends MapReduceBase implements
		IterativeReducer<IntWritable, Text, IntWritable, Text> {
	
	private int iteration;
	private Date start;
	private int threshold;
	
	@Override
	public void configure(JobConf job){
		iteration = 0;
		start = new Date();
		threshold = job.getInt(KMeans.KMEANS_THRESHOLD, 0);
	}

	@Override
	public void reduce(IntWritable key, Iterator<Text> values,
			OutputCollector<IntWritable, Text> output, Reporter report)
			throws IOException {
		//input key: cluster's mean  (whose mean has the nearest measure distance)
		//input value: artid,avg,time artid,avg,time 
		
		LastFMUser base = new LastFMUser(key.get(), "");
		while(values.hasNext()) {		
			String data = values.next().toString();

			if(KMeans.COMBINE){
				LastFMUser curr = new LastFMUser(key.get(), data, true);
				base.addinred(curr);
			}else{
				LastFMUser curr = new LastFMUser(key.get(), data);
				base.add(curr);
			}

		}
		
		output.collect(key, new Text(base.getArtists(threshold)));
		System.out.println(key + "\t" + base.getArtists(threshold));
	}
	
	@Override
	public void iterate() {
		iteration++;
		Date current = new Date();
		long passed = (current.getTime() - start.getTime()) / 1000;		
		
		System.out.println("iteration " + iteration + " timepassed " + passed);
	}

}
