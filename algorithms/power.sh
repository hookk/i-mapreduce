#! bin/bash

# This shell will perform matrix power computation on iMapReduce

HADOOP=../bin/hadoop
JAR=../hadoop-imapreduce-0.1-examples.jar

DATA_DIR=power_dataset
N=$DATA_DIR/iterativestate
M=$DATA_DIR/iterativestatic
NODE=1000

# perform power iteration
IN=temp
OUT=IterativePower
POWER=6
SNAPSHOTINTERVAL=1
PARTITIONS=4

$HADOOP dfs -put temp $IN
$HADOOP dfs -rmr $OUT		
$HADOOP jar iterativeMR.jar matrixpower $IN $M $N $OUT $POWER -p $PARTITION -i 1
