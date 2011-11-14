#! bin/bash
# This shell will generate synthetic dataset for iterative computations (pagerank, sssp, kmeans, nmf, matrix power) in iMapReduce, including initial state dataset and static dataset

HADOOP=../bin/hadoop
JAR=../hadoop-imapreduce-0.1-examples.jar

OUT=sp_dataset		# output dir on HDFS
NODE=1000		# number of nodes
ARGUMENT=0		# sp:start node|pg:number of init start|kmeans:k, number of clusters|nmf:columns|power:dimensions
PARTITIONS=4		# number of partitions, how many tasks you used to generate the dataset
TYPE=sp			# sp|pg|km|nmf|power

$HADOOP dfs -put temp temp
$HADOOP dfs -rmr $OUT
$HADOOP jar $JAR disgendata temp $OUT $NODE $ARGUMENT $TYPE $PARTITIONS

#$HADOOP jar $JAR transpose $OUT/iterativestatic $OUT/trans $PARTITIONS


