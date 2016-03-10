
a modified MapReduce Framework for iterative processing

The iMapReduce project started at University of Massachusetts Amherst in 2010. iMapReduce is a modified Hadoop MapReduce Framework for iterative processing. iMapReduce allows users to specify the iterative computation with the separated map and reduce functions, and provides support of automatic iterative processing. More importantly, it improves performance by

reducing the overhead of creating jobs repeatedly
eliminating the shuffling of static data
allowing asynchronous execution of map tasks
Even though it is not true that every iterative algorithm can benefit from all these three features, most of graph-based iterative algorithms are quite suitable in iMapReduce, e.g. shortest path and pagerank. The other iterative algorithms, e.g. NMF, kmeans, at least, can benefit from the first feature. So we can see different performance improvement for different applications.

For more information, please take a look at our iMapReduce paper in DataCloud'2011.

This project is a prototype implementation of the iMapReduce idea. The prototype isbased on Hadoop 0.19.2 and HOP. It is better used for research perspective, but we don't recommend to use it in production. Of course, we welcome any feedback on iMapReduce.

Quick Start

Of course, download iMapReduce hadoop-imapreduce-0.1.tar.gz.
To deploy a cluster environment, you can refer to Hadoop Quick Start instructions, if you've never used Hadoop.
Modify configuration files in conf/ directory according to your cluster environment, hadoop-site.xml (e.g., jobtracker, namenode, ...), slaves, masters, hadoop-env.sh.
The algorithm samples are provided in algorithms directory, you can simply execute shell scripts to run the algorithms including SSSP, PageRank, KMeans, Power of Matrix.
The real data involved in these applications could be found at http://rio.ecs.umass.edu/~yzhang/data/
For more details, you can read our Wikipage.

An brief introduction of iMapReduce

APIs and system parameters

PageRank implementation in iMapReduce

An overview of the iMapReduce implementation
