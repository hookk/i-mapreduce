package org.apache.hadoop.examples.iterative;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.TreeMap;


import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.IterativeMapper;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class KMeansMap extends MapReduceBase implements
		IterativeMapper<IntWritable, Text, IntWritable, Text, IntWritable, Text> {


	private JobConf conf;
	private FileSystem fs;
	private String clusterDir;
	private int iteration;
	private BufferedWriter clusterWriter;
	private String subGraphDir;
	private String subRankDir;
	private int taskid;
	private TreeMap<Integer, LastFMUser> outCenters = new TreeMap<Integer, LastFMUser>();
	private ArrayList<LastFMUser> centers = new ArrayList<LastFMUser>();
	private int k = 0;
	private int counter = 0;
	private OutputCollector<IntWritable, Text> outCollector;
	private int threshold = 0;
	
	@Override
	public void configure(JobConf job){
		clusterDir = job.get(MainDriver.KMEANS_CLUSTER_PATH);
		int ttnum = Util.getTTNum(job);
		threshold = job.getInt(MainDriver.KMEANS_THRESHOLD, 0) / (ttnum);
		iteration = 0;
		conf = job;
		
		try {
			fs = FileSystem.get(job);
			//localfs = FileSystem.getLocal(job);
			Path clusterPath = new Path(clusterDir + "/" + iteration + "/part" + Util.getTaskId(conf));
			if(fs.exists(clusterPath)) fs.delete(clusterPath, true);
			FSDataOutputStream clusterOut = fs.create(clusterPath);
			clusterWriter = new BufferedWriter(new OutputStreamWriter(clusterOut));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		k = job.getInt(MainDriver.KMEANS_CLUSTER_K, 0);
		subRankDir = job.get(MainDriver.SUBRANK_DIR);
		subGraphDir = job.get(MainDriver.SUBGRAPH_DIR);
		
		taskid = Util.getTaskId(job);
	}
	
	@Override
	public void map(IntWritable key, Text value, IntWritable datakey, Text dataval,
			OutputCollector<IntWritable, Text> output, Reporter report)
			throws IOException {
		
		//input key: nothing
		//input value: nothing
		//input data: user artist-id,plays tuples
		//output key: cluster id  (whose mean has the nearest measure distance)
		//output value: user-id data
		

		if(datakey == null){	
			if(centers.size() == k) centers.clear();
			
			LastFMUser curr = new LastFMUser(key.get(), value.toString());
			centers.add(curr);
			LastFMUser outCenter = new LastFMUser(key.get(), "");
			outCenters.put(key.get(), outCenter);
			System.out.println("center size " + centers.size() + "\t" + curr);
		
			return;
		}
			
		if(outCollector == null) outCollector = output;
		
		LastFMUser curr = new LastFMUser(datakey.get(), dataval.toString());
		counter++;
		report.setStatus(String.valueOf(counter));
		//System.out.println(curr);
		
		double maxDist = -1;
		LastFMUser maxMean = null;
		for(LastFMUser mean : centers){
			double dist;
			dist = mean.ComplexDistance(curr);
			if(dist > maxDist) {
				maxDist = dist;
				maxMean = mean;
			}
		}
		
		if(KMeans.COMBINE){
			outCenters.get(maxMean.userID).add(curr);
		}else{
			output.collect(new IntWritable(maxMean.userID), new Text(curr.artistsString()));
		}
		
		clusterWriter.write(String.valueOf(maxMean.userID) + "\t" + curr.userID + "\n");
		/*
		if(maxDist > -1) {		
			output.collect(new IntWritable(maxMean.userID), new Text(curr.toString()));
		}
		*/
	}

	@Override
	public Path[] initStateData() throws IOException {
		int num = Util.getTTNum(conf);
		Path[] paths = new Path[num];
		for(int i=0; i<num; i++){
			Path remotePath = new Path(this.subRankDir + "/subrank" + i);
			Path localPath = new Path("/tmp/iterativehadoop/statedata" + i);
			fs.copyToLocalFile(remotePath, localPath);
			paths[i] = localPath;
		}

		return paths;
	}
	
	@Override
	public Path initStaticData() throws IOException {
		Path remotePath = new Path(this.subGraphDir + "/subgraph" + taskid);
		Path localPath = new Path("/tmp/iterativehadoop/staticdata");
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
		iteration++;
		
		try {
			this.clusterWriter.close();
			Path clusterPath = new Path(clusterDir + "/" + iteration + "/part" + Util.getTaskId(conf));
			if(fs.exists(clusterPath)) fs.delete(clusterPath, true);
			FSDataOutputStream clusterOut = fs.create(clusterPath);
			clusterWriter = new BufferedWriter(new OutputStreamWriter(clusterOut));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(KMeans.COMBINE){
			for(int meanID : this.outCenters.keySet()){
				try {
					outCollector.collect(new IntWritable(meanID), 
							new Text(outCenters.get(meanID).getArtists(threshold)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			outCenters.clear();
		}else{
			try {
				outCollector.collect(new IntWritable(0), new Text("0,0,0"));
				outCollector.collect(new IntWritable(1), new Text("0,0,0"));
				outCollector.collect(new IntWritable(2), new Text("0,0,0"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	
}
