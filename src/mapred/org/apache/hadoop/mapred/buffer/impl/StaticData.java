package org.apache.hadoop.mapred.buffer.impl;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.SerializationFactory;
import org.apache.hadoop.mapred.IFile;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapTask;
import org.apache.hadoop.util.ReflectionUtils;

public class StaticData<KEY, VALUE> {
	public static enum MatchType{ONE2ONE, ONE2MORE, ONE2ALL};
	
	protected static final Log LOG = LogFactory.getLog(MapTask.class.getName());
	
	protected JobConf conf;
	protected LocalFileSystem localfs;
	protected Path localpath;
	protected CompressionCodec codec = null;
	protected IFile.Reader reader;
	protected Deserializer<KEY> dataKeyDeserializer;
	protected Deserializer<VALUE> dataValDeserializer;
	protected DataInputBuffer key = new DataInputBuffer();
	protected DataInputBuffer value = new DataInputBuffer();
	protected KEY keyObject;
	protected VALUE valObject;
	private IteratorHandler iterhandler;
	
	public StaticData(JobConf conf, Path localpath, StaticData.MatchType inType, 
			Class<KEY> dataKeyClass, Class<VALUE> dataValClass){
		Class<? extends CompressionCodec> codecClass = null;
		
		if (conf.getCompressMapOutput()) {
			codecClass = conf.getMapOutputCompressorClass(DefaultCodec.class);
			codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
		}
		
		this.conf = conf;
		this.localpath = localpath;
		
		try {
			localfs = FileSystem.getLocal(conf);			
			
			reader = new IFile.Reader(conf, localfs, localpath, codec, null);
			while(reader.getLength() <= 0){
				Thread.sleep(500);
				reader = new IFile.Reader(conf, localfs, localpath, codec, null);
			}
			LOG.info("static data path is " + localpath + " length is " + reader.getLength() + " position is " + reader.getPosition());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
	    SerializationFactory serializationFactory = new SerializationFactory(conf);
	    dataKeyDeserializer = serializationFactory.getDeserializer(dataKeyClass);
	    dataValDeserializer = serializationFactory.getDeserializer(dataValClass);
	        
	    if(inType == MatchType.ONE2ONE){
	    	iterhandler = new One2OneIteratorHandler();
	    }else if(inType == MatchType.ONE2ALL){
	    	iterhandler = new One2AllIteratorHandler();
	    } 
	}
	
	public boolean next() throws IOException{
		return iterhandler.next();
	}
	
	public KEY getKey(){
		return keyObject;
	}
	
	public VALUE getValue(){
		return valObject;
	}
	
	abstract class IteratorHandler{
		protected abstract boolean next() throws IOException;
	}
	
	final class One2OneIteratorHandler extends IteratorHandler{

		@Override
		protected boolean next() throws IOException {;
			if (reader.next(key, value)) {
				dataKeyDeserializer.open(key);
				dataValDeserializer.open(value);
				keyObject = dataKeyDeserializer.deserialize(keyObject);
				valObject = dataValDeserializer.deserialize(valObject);			
			}else{
				reader = new IFile.Reader(conf, localfs, localpath, codec, null);
				reader.next(key, value);
				dataKeyDeserializer.open(key);
				dataValDeserializer.open(value);
				keyObject = dataKeyDeserializer.deserialize(keyObject);
				valObject = dataValDeserializer.deserialize(valObject);	
			}
			return true;
		}
		
	}

	final class One2AllIteratorHandler extends IteratorHandler{

		@Override
		protected boolean next() throws IOException {
			if (reader.next(key, value)) {
				dataKeyDeserializer.open(key);
				dataValDeserializer.open(value);
				keyObject = dataKeyDeserializer.deserialize(keyObject);
				valObject = dataValDeserializer.deserialize(valObject);
				return true;
			}else{
				reader = new IFile.Reader(conf, localfs, localpath, codec, null);
				return false;
			}
		}
		
	}
}

	


