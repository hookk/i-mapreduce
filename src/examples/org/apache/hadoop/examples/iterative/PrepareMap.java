package org.apache.hadoop.examples.iterative;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class PrepareMap extends MapReduceBase implements
		Mapper<Text, Text, IntWritable, IntFloatPairWritable> {

	private int count = 0;

	@Override
	public void map(Text key, Text value,
			OutputCollector<IntWritable, IntFloatPairWritable> output, Reporter report)
			throws IOException {
		count++;
		report.setStatus(String.valueOf(count));
		
		String[] field = key.toString().split(",", 2);
		int rowid = Integer.parseInt(field[0]);
		int colid = Integer.parseInt(field[1]);
		
		float val = Float.parseFloat(value.toString());
			
		output.collect(new IntWritable(colid), new IntFloatPairWritable(rowid, val));
	}

}
