package org.apache.hadoop.mapred;

import java.io.IOException;

import org.apache.hadoop.fs.Path;

public interface IterativeMapper<K1, V1, DK, DV, K2, V2> extends Mapper<K1, V1, K2, V2> {
	/**
	 * for loading the initial vector to priority-key-value buffer, user should
	 * use pkvBuffer.collect(priority, K, V) to initialize the priorityKVBuffer
	 * @param pkvBuffer
	 * @throws IOException
	 */
	void map(K1 key, V1 value, DK dataKey, DV dataVal, OutputCollector<K2, V2> output, Reporter reporter)
	  throws IOException;
	  
	Path[] initStateData() throws IOException;
	Path initStaticData() throws IOException;
	
	void iterate();
}
