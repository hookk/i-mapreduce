package org.apache.hadoop.examples.iterative;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.IterativeMapper;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


//input <node, shortest length and point to list>
//output <node, shortest length>
public class BSearchMap extends MapReduceBase implements IterativeMapper<IntWritable, Text, IntWritable, Text, IntWritable, Text> {
	private FileSystem fs;
	private String subGraphsDir;
	private String subRankDir;
	private int taskid;
	 
	@Override
	public void configure(JobConf job) {
		try {
			fs = FileSystem.get(job);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		subRankDir = job.get(Common.SUBSTATE);
		subGraphsDir = job.get(Common.SUBSTATIC);
		taskid = Util.getTaskId(job);
	}
	
	//format node	f:len
	//       node	v:shortest_length
	@Override
	public void map(IntWritable key, Text value, IntWritable datakey, Text dataval,
			OutputCollector<IntWritable, Text> output, Reporter report)
			throws IOException {

		//System.out.println(datakey + "\t" + dataval);
		
		String frontier = value.toString();
		int index = frontier.indexOf("f");
		
		int nnode = key.get();
		
		//frontier has f
		if(index != -1){	
			int base_len = Integer.parseInt(frontier.substring(index+2));

			String[] links = dataval.toString().split(" ");
			
			for(String linkstr : links){
				int index2 = linkstr.indexOf(",");
				if(index2 == -1) break;
				int node2 = Integer.parseInt(linkstr.substring(0, index2));
				int length = Integer.parseInt(linkstr.substring(index2+1));
				
				String f = "f:" + String.valueOf(base_len+length);
				output.collect(new IntWritable(node2), new Text(f));
			}
			
			String v = "v:" + String.valueOf(base_len);
			output.collect(key, new Text(v));	
		}else{
			output.collect(new IntWritable(nnode), value);
		}
	}

	@Override
	public Path[] initStateData() throws IOException {
		Path remotePath = new Path(this.subRankDir + taskid);
		Path localPath = new Path(Common.LOCAL_STATE + taskid);
		fs.copyToLocalFile(remotePath, localPath);
		Path[] paths = new Path[1];
		paths[0] = localPath;
		return paths;
	}
	
	@Override
	public Path initStaticData() throws IOException {
		Path remotePath = new Path(this.subGraphsDir + taskid);
		Path localPath = new Path(Common.LOCAL_STATIC + taskid);
		fs.copyToLocalFile(remotePath, localPath);
		return localPath;
	}

	@Override
	public void map(IntWritable arg0, Text arg1,
			OutputCollector<IntWritable, Text> arg2, Reporter arg3)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iterate() {

	}
}
