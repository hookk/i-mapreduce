package org.apache.hadoop.mapred;




import org.apache.hadoop.io.Writable;

public interface IterativeReducer<K2, V2, K3 extends Writable, V3 extends Writable> extends Reducer<K2, V2, K3, V3> {
	
	void iterate();
}
