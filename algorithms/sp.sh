#! bin/bash
# This shell will perform Single Source Shortest Path computation on iMapReduce

HADOOP=../bin/hadoop
JAR=../hadoop-imapreduce-0.1-examples.jar

DATA_DIR=sp_dataset
STATE=$DATA_DIR/iterativestate
STATIC=$DATA_DIR/iterativestatic

# for synthetic data, you should use gendata.sh to generate some synthetic dataset. Note that you should make # of nodes|# of partitions|data dir consistent.
#NODE=1000

# for real data, you should download them and upload them to HDFS first
# DBLP author cooperation graph (http://rio.ecs.umass.edu/~yzhang/data/sp_dblp_graph, http://rio.ecs.umass.edu/~yzhang/data/sp_dblp_rank)
LOCAL_STATIC=sp_dblp_graph
LOCAL_STATE=sp_dblp_rank
NODE=310555
# Facebook user interaction graph (http://rio.ecs.umass.edu/~yzhang/data/sp_facebook_graph, http://rio.ecs.umass.edu/~yzhang/data/sp_facebook_rank)
#LOCAL_STATIC=sp_facebook_graph
#LOCAL_STATE=sp_facebook_rank
#NODE=1204004

# upload the real dataset to HDFS, it is unnecessary for the synthetic dataset
$HADOOP dfs -rmr $DATA_DIR
$HADOOP dfs -put $LOCAL_STATIC $STATIC
$HADOOP dfs -put $LOCAL_STATE $STATE

# perform SSSP iteration
IN=temp
OUT=IterativeSSSP
ITERATIONS=10
SNAPSHOTINTERVAL=1
PARTITIONS=4

$HADOOP dfs -put temp $IN
$HADOOP dfs -rmr $OUT	
	
$HADOOP jar $JAR bsearch $IN $STATE $STATIC $OUT -i $SNAPSHOTINTERVAL -I $ITERATIONS -n $NODE -p $PARTITIONS


