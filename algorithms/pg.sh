#! bin/bash
# This shell will perform PageRank computation on iMapReduce

HADOOP=../bin/hadoop
JAR=../hadoop-imapreduce-0.1-examples.jar

DATA_DIR=pg_dataset
STATIC=$DATA_DIR/iterativestate
STATE=$DATA_DIR/iterativestatic

# for synthetic data, you should use gendata.sh to generate some synthetic dataset. Note that you should make # of nodes|# of partitions|data dir consistent.
#NODE=1000

# for real data, you should download them and upload them to HDFS first
# google web graph (http://rio.ecs.umass.edu/~yzhang/data/pg_google_graph, http://rio.ecs.umass.edu/~yzhang/data/pg_google_rank)
LOCAL_STATIC=pg_google_rank
LOCAL_STATE=pg_google_rank
NODE=50000
# berkstan web graph (http://rio.ecs.umass.edu/~yzhang/data/pg_berkstan_graph, http://rio.ecs.umass.edu/~yzhang/data/pg_berkstan_rank)
#LOCAL_STATIC=pg_berkstan_graph
#LOCAL_STATE=pg_berkstan_rank
#NODE=685231

# upload the real dataset to HDFS, it is unnecessary for the synthetic dataset
$HADOOP dfs -rmr $DATA_DIR
$HADOOP dfs -put $LOCAL_STATIC $STATIC
$HADOOP dfs -put $LOCAL_STATE $STATE

# perform PageRank iteration
IN=temp
OUT=IterativePageRank
ITERATIONS=5
SNAPSHOTINTERVAL=1
PARTITIONS=8

$HADOOP dfs -put temp $IN
$HADOOP dfs -rmr $OUT	
	
$HADOOP jar $JAR pagerank $IN $STATE $STATIC $OUT -i $SNAPSHOTINTERVAL -I $ITERATIONS -n $NODE -p $PARTITIONS
