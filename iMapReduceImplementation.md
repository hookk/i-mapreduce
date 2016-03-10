iMapReduce is implemented based on Hadoop 0.19.2 and HOP. According to the iMapReduce idea, what we need to do is

  1. Make task persistent, so that the map/reduce task are always switching its status between working or suspending. As long as there is input coming, the map/reduce tasks start processing them. After finishing processing, they are suspended for the new coming input.
  1. Within the same job or crossing multiple jobs, connect the reduce tasks to the map tasks based on some mapping rule (one-to-one or one-to-all). The reduce tasks' output can be sent to a single map task or can be broadcast to all map tasks.
  1. Schedule the map task with taskid _t_ to the worker where its static data _i_ locate, at meantime, shuffle the state data _i_ to the reduce task with taskid _t_. We connect the reduce task to the map task with the same taskid _t_, so that the state data will always go to the map task where its corresponding static data locate.


To achieve the above goals, we mainly modified the following classes in Hadoop and HOP.

  * [org.apache.hadoop.mapred.MapTask](http://code.google.com/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java)
  * [org.apache.hadoop.mapred.ReduceTask](http://code.google.com/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/ReduceTask.java)

The map/reduce task is modified as persistent task. It enters an internal loop after the initial data are processed, so that it keeps alive and will not terminate after finishing processing the assigned data. In the map task, according to one-to-one join or one-to-all join, it will join the state data record and the static data records before providing them to the map operation.

  * [org.apache.hadoop.mapred.buffer.net.BufferExchangeSink](http://code.google.com/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/buffer/net/BufferExchangeSink.java)

It contains a data receiving thread. The thread will notify the suspended map/reduce task to process the input data after some data are received.

  * [org.apache.hadoop.mapred.buffer.impl.StaticData](http://code.google.com/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/buffer/impl/StaticData.java)

The static data are managed by this class.

  * [org.apache.hadoop.mapred.TaskTracker](http://code.google.com/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/TaskTracker.java)

It runs an data fetching mechanism, so that it determines which reduce task should connect to which map task.

  * [org.apache.hadoop.mapred.iMapReduceTaskScheduler](http://code.google.com/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/iMapReduceTaskScheduler.java)

It always schedules the tasks on the same worker with the same task ids. Since reduce task _i_ -> map task _i_, so that this can ensure local reduce-to-map connection.