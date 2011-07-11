package org.apache.hadoop.examples.iterative;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;


public class Test extends Configured implements Tool{

	private static int matchCount(ArrayList<Integer> a, ArrayList<Integer> b){
		int match = 0;
		for(int i : a){
			if(b.contains(i)) match++;
		}
		return match;
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String authors = args[0];
		String docs = args[1];
		String relations = args[2];
		String out = args[3];
		
		HashMap<String, Integer> authorMap = new HashMap<String, Integer>();
		HashMap<String, Integer> docMap = new HashMap<String, Integer>();
		HashMap<Integer, ArrayList<Integer>> authorDocMap = new HashMap<Integer, ArrayList<Integer>>();

		
		try{
			BufferedReader authorReader = new BufferedReader(new FileReader(authors));
			BufferedReader docReader = new BufferedReader(new FileReader(docs));
			BufferedReader relationReader = new BufferedReader(new FileReader(relations));
			
			BufferedWriter wr = new BufferedWriter(new FileWriter(out));
		
			String strLine;
			//Read File Line By Line
			while ((strLine = authorReader.readLine()) != null)   {
				int index = strLine.indexOf(" ");
				
				int id = Integer.parseInt(strLine.substring(0, index));
				String name = strLine.substring(index+1);
				
				authorMap.put(name, id);
			}
			//Close the input stream
			authorReader.close();
			
			while ((strLine = docReader.readLine()) != null)   {
				int index = strLine.indexOf(" ");
				
				int id = Integer.parseInt(strLine.substring(0, index));
				String name = strLine.substring(index+1);
				
				
				docMap.put(name, id);
			}
			//Close the input stream
			docReader.close();
			
			System.out.println("author load finished!");
			
			while ((strLine = docReader.readLine()) != null)   {
				int index = strLine.indexOf(" ");
				
				int id = Integer.parseInt(strLine.substring(0, index));
				String name = strLine.substring(index+1);
				
				
				String temp = name;
				
				int index2 = 0;
				while(temp.indexOf("/") != -1){
					index2 = temp.indexOf("/");
					temp = temp.substring(index2+1);
				}
				name = name.substring(index+1, index2);
				
				docMap.put(name, id);
			}
			//Close the input stream
			docReader.close();
			
			System.out.println("doc load finished!");
			
			while ((strLine = relationReader.readLine()) != null)   {
				String[] part = strLine.split("\t");
				
				String docname = part[1];
								
				String temp = docname;
				
				int index2 = 0;
				while(temp.indexOf("/") != -1){
					index2 = temp.indexOf("/");
					temp = temp.substring(index2+1);
				}
				docname = docname.substring(0, index2);				
				
				int doc = docMap.get(part[0]);
				int author = authorMap.get(part[1]);
				
				if(!authorDocMap.containsKey(author)){
					ArrayList<Integer> docList = new ArrayList<Integer>();
					authorDocMap.put(author, docList);
				}
				
				if(!authorDocMap.get(author).contains(doc)){
					authorDocMap.get(author).add(doc);
				}
			}
			//Close the input stream
			relationReader.close();
			
			authorMap.clear();
			docMap.clear();
			
			System.out.println("matrix load finished!");
			
			for(int authorA : authorDocMap.keySet()){
				wr.write(authorA + "\t");
				for(int authorB : authorDocMap.keySet()){
					int match = matchCount(authorDocMap.get(authorA), authorDocMap.get(authorB));
					if(match > 0){
						wr.write(authorB + "," + match + " ");
					}
				}
				wr.flush();
			}
			
			wr.close();
	    }catch (Exception e){//Catch exception if any
	    	System.err.println("Error: " + e.getMessage());
	    }
	    
	}

	@Override
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		/*
		JobConf job = new JobConf(getConf());
		String localHostname =
		      DNS.getDefaultHost
		      (job.get("mapred.tasktracker.dns.interface","default"),
		    		  job.get("mapred.tasktracker.dns.nameserver","default"));
		
		System.out.println(localHostname);
		
		NetworkInterface netIF = NetworkInterface.getByName("default");
		System.out.println(netIF);
		
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		*/
		

		return 0;
	}

}
