package org.apache.hadoop.examples.iterative;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class IntIntPairWritable implements WritableComparable {
	
	private int x;
	private int y;
	
	public IntIntPairWritable() {}
	
	public IntIntPairWritable(int x, int y) 
	{ 
		this.x = x;
		this.y = y;
	}

	public void set(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		this.x = in.readInt();
		this.y = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.x);
		out.writeInt(this.y);
	}

	public boolean equals(Object o) {
		if (!(o instanceof IntIntPairWritable))
		  return false;
		IntIntPairWritable other = (IntIntPairWritable)o;
		if(this.x == other.getX() && this.y == other.getY()){
			return true;
		}else{
			return false;
		}
	}

	public int hashCode() {
		return this.x + this.y;
	}
		  
	public String toString() {
		return "<" + this.x + "," + this.y + ">";
	}
	
	@Override
	public int compareTo(Object o) {
		int thisXValue = this.x;
		int thisYValue = this.y;
	    int thatXValue = ((IntIntPairWritable)o).x;
	    int thatYValue = ((IntIntPairWritable)o).y;
	    
	    if(thisXValue < thatXValue){
	    	return -1;
	    }else if(thisXValue > thatXValue){
	    	return 1;
	    }else{
	    	if(thisYValue < thatYValue){
	    		return -1;
	    	}else if(thisYValue > thatYValue){
	    		return 1;
	    	}else{
	    		return 0;
	    	}
	    }
	}

}
