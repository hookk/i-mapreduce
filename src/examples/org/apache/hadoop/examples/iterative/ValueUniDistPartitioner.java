package org.apache.hadoop.examples.iterative;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.HashPartitioner;

public class ValueUniDistPartitioner extends HashPartitioner<IntWritable, Text> {
	  public void configure(JobConf job) {}

	  /** Use {@link Object#hashCode()} to partition. */
	  public int getPartition(IntWritable key, Text value,
	                          int numReduceTasks) {
	    return Integer.parseInt(value.toString()) % numReduceTasks;
	  }

}
