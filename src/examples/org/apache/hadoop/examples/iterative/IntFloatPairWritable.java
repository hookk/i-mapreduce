package org.apache.hadoop.examples.iterative;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class IntFloatPairWritable implements WritableComparable {
	
	private int x;
	private float y;
	
	public IntFloatPairWritable() {}
	
	public IntFloatPairWritable(int x, float y) 
	{ 
		this.x = x;
		this.y = y;
	}

	public void set(int x, float y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		this.x = in.readInt();
		this.y = in.readFloat();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.x);
		out.writeFloat(this.y);
	}

	public boolean equals(Object o) {
		if (!(o instanceof IntFloatPairWritable))
		  return false;
		IntFloatPairWritable other = (IntFloatPairWritable)o;
		if(this.x == other.getX() && this.y == other.getY()){
			return true;
		}else{
			return false;
		}
	}

	public int hashCode() {
		return this.x + (int)this.y;
	}
		  
	public String toString() {
		return "<" + this.x + "," + this.y + ">";
	}
	
	@Override
	public int compareTo(Object o) {
		int thisXValue = this.x;
		float thisYValue = this.y;
	    int thatXValue = ((IntFloatPairWritable)o).x;
	    float thatYValue = ((IntFloatPairWritable)o).y;
	    
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
