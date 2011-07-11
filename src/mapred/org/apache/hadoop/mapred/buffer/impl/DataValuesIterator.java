package org.apache.hadoop.mapred.buffer.impl;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.mapred.IFile;

public class DataValuesIterator<VALUE> implements Iterator<VALUE> {

	private IFile.Reader reader;
	private Deserializer<VALUE> dataValDeserializer;
	private StaticData.MatchType type;
	private boolean hasNext = true;
	private DataInputBuffer key = new DataInputBuffer();
	private DataInputBuffer value = new DataInputBuffer();
	
	public DataValuesIterator(IFile.Reader inReader, Deserializer<VALUE> inDeserializer,
			StaticData.MatchType inType){
		type = inType;
		reader = inReader;
		dataValDeserializer = inDeserializer;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return hasNext;
	}

	@Override
	public VALUE next() {
		if(type == StaticData.MatchType.ONE2ONE){
			VALUE valObject = null;
			try {
				if (reader.next(key, value)) {
					dataValDeserializer.open(value);
					valObject = dataValDeserializer.deserialize(valObject);
					return valObject;
				}else{
					return null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hasNext = false;
		}else if(type == StaticData.MatchType.ONE2ALL){
			VALUE valObject = null;
			try {
				if (reader.next(key, value)) {
					dataValDeserializer.open(value);
					valObject = dataValDeserializer.deserialize(valObject);
					return valObject;
				}else{
					hasNext = false;
					return null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
