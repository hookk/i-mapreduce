The iMapReduce project started at University of Massachusetts Amherst in 2010. iMapReduce is a modified Hadoop MapReduce Framework for iterative processing. iMapReduce allows users to specify the iterative computation with the separated map and reduce functions, and provides support of automatic iterative processing. More importantly, it improves performance by

  * reducing the overhead of creating jobs repeatedly
  * eliminating the shuffling of static data
  * allowing asynchronous execution of map tasks

Even though it is not true that every iterative algorithm can benefit from all these three features, most of graph-based iterative algorithms are quite suitable in iMapReduce, e.g. shortest path and pagerank. The other iterative algorithms, e.g. NMF, kmeans, at least, can benefit from the first feature. So we can see different performance improvement for different applications.

For more information, please take a look at our [iMapReduce paper](http://rio.ecs.umass.edu/~yzhang/papers/DataCloud2011_iMapReduce.pdf) in DataCloud'2011.

This project is a prototype implementation of the iMapReduce idea. The prototype isbased on [Hadoop 0.19.2](http://hadoop.apache.org/) and [HOP](http://code.google.com/p/hop). It is better used for research perspective, but we don't recommend to use it in production. Of course, we welcome any feedback on iMapReduce.

## Quick Start ##

  1. Of course, download iMapReduce [hadoop-imapreduce-0.1.tar.gz](http://i-mapreduce.googlecode.com/files/hadoop-imapreduce-0.1.tar.gz).
  1. To deploy a cluster environment, you can refer to [Hadoop Quick Start instructions](http://hadoop.apache.org/common/docs/current/), if you've never used Hadoop.
  1. Modify configuration files in conf/ directory according to your cluster environment, hadoop-site.xml (e.g., jobtracker, namenode, ...), slaves, masters, hadoop-env.sh.
  1. The algorithm samples are provided in _algorithms_ directory, you can simply execute shell scripts to run the algorithms including SSSP, PageRank, KMeans, Power of Matrix.
  1. The real data involved in these applications could be found at [http://rio.ecs.umass.edu/~yzhang/data/](http://rio.ecs.umass.edu/~yzhang/data/)
  1. For more details, you can read our [Wikipage](http://code.google.com/p/i-mapreduce/w/list).


---


### [An brief introduction of iMapReduce](iMapReduceIntroduction.md) ###
### [APIs and system parameters](APIs.md) ###
### [PageRank implementation in iMapReduce](PagerankExample.md) ###
### [An overview of the iMapReduce implementation](iMapReduceImplementation.md) ###