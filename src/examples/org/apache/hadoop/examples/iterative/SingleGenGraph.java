package org.apache.hadoop.examples.iterative;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import jsc.distributions.Pareto;

public class SingleGenGraph {
	
	public static final double W_PARETO_SCALE = 50.0;
	public static final double W_PARETO_SHAPE = 3.0;
	
	private static void gen(String path, int capacity, int value_scale) {

		try {
			File f = new File(path);
			if (f.exists()) {
				f.delete();
				f.createNewFile();
				} else {
					f.createNewFile();
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			
			Pareto power = new Pareto(W_PARETO_SCALE, W_PARETO_SHAPE);

			for(int i=0; i<capacity; i++){
				double rand = power.random();
				//System.out.println(rand);
				int num_link = (int)(Math.ceil(rand) - W_PARETO_SCALE);
				while(num_link > capacity){
					num_link = (int)(Math.ceil(rand) - W_PARETO_SCALE);
				}
				//System.out.println(num_link);
				
				output.write(String.valueOf(i)+"\t");

				//System.out.println(prob);
				Random r = new Random();
				
				ArrayList<Integer> links = new ArrayList<Integer>(num_link);
				for(int j=0; j< num_link; j++){
					int link = r.nextInt(capacity);
					while(links.contains(link)){
						link = r.nextInt(capacity);
					}
					links.add(link);
					if(value_scale == 1) {
						//for non-weight graph
						output.write(String.valueOf(link));
					}else {
						//for weighted graph
						int weight = r.nextInt(value_scale) + 1;
						output.write(String.valueOf(link) + "," + String.valueOf(weight));
					}
					
					if(j < num_link-1){
						output.write(" ");
					}
				}
				
				if(i<capacity-1){
					output.write("\n");
				}		
			}
					
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 3){
		      System.err.println("Usage: genGraph <graph_path> <capacity> <weigth_scale>");
		      System.exit(2);
		}
		gen(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));

		System.out.println("word done!");
	}


}
