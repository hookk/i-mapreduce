package org.apache.hadoop.examples.iterative;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;



public class GraphDistributeReduce extends MapReduceBase implements
		Reducer<IntWritable, Text, Text, Text>  {
	
	private FileSystem hdfs;
	private String subGraphsDir;
	private FSDataOutputStream linkFile;
	private BufferedWriter linkWriter;
	private FSDataOutputStream indexFile;
	private BufferedWriter indexWriter;
	private long offset;
	private int count;				//for record when to generate index, every INDEX_BLOCK_SIZE
	private JobConf job;
	
	@Override
	public void configure(JobConf arg){    
	    job = arg;
	    try {
			hdfs = FileSystem.get(job);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		subGraphsDir = job.get(MainDriver.SUBGRAPH_DIR);
		offset = 0;
		count = 0;
	}
	
	@Override
	public void reduce(IntWritable key, Iterator<Text> values,
			OutputCollector<Text, Text> output, Reporter report)
			throws IOException {	
		if(linkWriter == null){	    
			try {				
				int n = key.get() % job.getNumReduceTasks();
				
				String linkfilename = subGraphsDir + "/" + n +"-linklist";
				linkFile = hdfs.create(new Path(linkfilename), true);
				linkWriter = new BufferedWriter(new OutputStreamWriter(linkFile));
				
				String indexfilename = subGraphsDir + "/" + n +"-index";
				indexFile = hdfs.create(new Path(indexfilename), true);
				indexWriter = new BufferedWriter(new OutputStreamWriter(indexFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String links = new String();
		while(values.hasNext()){
			links += values.next().toString() + " ";	
		}
		linkWriter.write(key + "\t" + links + "\n");
		
		//System.out.println(key + "\t" + links + "\n");
		
		if(count % MainDriver.INDEX_BLOCK_SIZE == 0){
			int index = count / MainDriver.INDEX_BLOCK_SIZE;
			indexWriter.write(String.valueOf(index) + "\t" + offset + "\n");
		}
		
		
		offset += key.toString().length() + links.length() + 2;
		count++;
	}
	
	@Override
	public void close(){
		try {		
			linkWriter.close();
			linkFile.close();
			indexWriter.close();
			indexFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
