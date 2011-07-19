package org.apache.hadoop.examples.iterative;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.IterativeMapper;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import util.MainDriver;
import util.Util;

public class MatrixMul2Map extends MapReduceBase implements
		IterativeMapper<IntWritable, Text, IntWritable, Text, IntIntPairWritable, FloatWritable> {

	private int count = 0;
	private JobConf conf;
	private FileSystem fs;
	private String subGraphsDir;
	private int taskid;
	 
	@Override
	public void configure(JobConf job) {
		conf = job;
		try {
			fs = FileSystem.get(job);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		subGraphsDir = job.get(MainDriver.SUBGRAPH_DIR);
		taskid = Util.getTaskId(conf);
	}
	
	@Override
	public void map(IntWritable stateKey, Text stateVal, IntWritable staticKey, Text staticVal,
			OutputCollector<IntIntPairWritable, FloatWritable> output,
			Reporter report) throws IOException {
		count++;
		report.setStatus(String.valueOf(count));
		
		String[] Acol = stateVal.toString().split(" ");
		String[] Brow = staticVal.toString().split(" ");
		HashMap<Integer, Float> col = new HashMap<Integer, Float>();
		HashMap<Integer, Float> row = new HashMap<Integer, Float>();
		
		try{
			for(String item : Acol){
				String[] field = item.split(",", 2);
				col.put(Integer.parseInt(field[0]), Float.parseFloat(field[1]));
			}
			
			for(String item : Brow){
				String[] field = item.split(",", 2);
				row.put(Integer.parseInt(field[0]), Float.parseFloat(field[1]));
			}
		}catch(NumberFormatException e){}
		
		
		for(Map.Entry<Integer, Float> colEntry : col.entrySet()){
			for(Map.Entry<Integer, Float> rowEntry : row.entrySet()){
				output.collect(new IntIntPairWritable(colEntry.getKey(), rowEntry.getKey()),
						new FloatWritable(colEntry.getValue()*rowEntry.getValue()));
				//System.out.println(new IntIntPairWritable(colEntry.getKey(), rowEntry.getKey()) + "\t" +
						//new FloatWritable(colEntry.getValue()*rowEntry.getValue()));
			}
		}
	}

	@Override
	public void map(IntWritable arg0, Text arg1,
			OutputCollector<power.IntIntPairWritable, FloatWritable> arg2,
			Reporter arg3) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Path[] initStateData() throws IOException {
		return null;
	}

	@Override
	public Path initStaticData() throws IOException {
		Path remotePath = new Path(this.subGraphsDir + "/subgraph" + taskid);
		Path localPath = new Path("/tmp/imapreduce/staticdata");
		fs.copyToLocalFile(remotePath, localPath);
		return localPath;
	}

	@Override
	public void iterate() {
		// TODO Auto-generated method stub
		
	}

}
