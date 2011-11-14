#! bin/bash
# This shell will perform KMeans computation on iMapReduce

HADOOP=../bin/hadoop
JAR=../hadoop-imapreduce-0.1-examples.jar

DATA_DIR=kmeans_dataset
STATE=$DATA_DIR/iterativestate
STATIC=$DATA_DIR/iterativestatic

# for synthetic data, you should use gendata.sh to generate some synthetic dataset. Note that you should make # of nodes|# of partitions|data dir consistent.
#NODE=1000

# for real data, you should download them and upload them to HDFS first
# last.fm dataset (http://rio.ecs.umass.edu/~yzhang/data/km_lastfm_graph, http://rio.ecs.umass.edu/~yzhang/data/km_lastfm_rank)
#for LastFM
#LOCAL_STATIC=km_lastfm_graph
#LOCAL_STATE=km_lastfm_rank
#NODE=359330
#K=16
#THRESHOLD=300
# amazon product dataset (http://rio.ecs.umass.edu/~yzhang/data/km_amazon_graph, http://rio.ecs.umass.edu/~yzhang/data/km_amazon_rank)
LOCAL_STATIC=km_amazon_graph
LOCAL_STATE=km_amazon_rank
NODE=403394
THRESHOLD=50

# upload the real dataset to HDFS, it is unnecessary for the synthetic dataset
$HADOOP dfs -rmr $DATA_DIR
$HADOOP dfs -put $LOCAL_STATIC $STATIC
$HADOOP dfs -put $LOCAL_STATE $STATE

# perform Kmeans iteration
IN=temp
OUT=IterativeKMeans
ITERATIONS=10
PARTITIONS=4
K=16

$HADOOP dfs -put temp $IN
$HADOOP dfs -rmr $OUT	
	
$HADOOP jar $JAR kmeans $IN $STATE $STATIC $OUT $K -I $ITERATIONS -n $NODE -p $PARTITIONS
