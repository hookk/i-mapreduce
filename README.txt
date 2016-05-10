
A modified MapReduce Framework for iterative processing and incremental processing.

The iMapReduce project started at University of Massachusetts Amherst in 2010. iMapReduce is a modified Hadoop MapReduce Framework for iterative processing. iMapReduce allows users to specify the iterative computation with the separated map and reduce functions, and provides support of automatic iterative processing. More importantly, it improves performance by reducing the overhead of creating jobs repeatedly eliminating the shuffling of static data allowing asynchronous execution of map tasks Even though it is not true that every iterative algorithm can benefit from all these three features, most of graph-based iterative algorithms are quite suitable in iMapReduce, e.g. shortest path and pagerank. The other iterative algorithms, e.g. NMF, kmeans, at least, can benefit from the first feature. So we can see different performance improvement for different applications.

For more information, please take a look at our iMapReduce paper in DataCloud'2011.

* Yanfeng Zhang, Qixin Gao, Lixin Gao, Cuirong Wang. iMapReduce: A Distributed Computing Framework for Iterative Computation [C]. Proceedings of IEEE International Symposium on Parallel & Distributed Processing, Workshops and Phd Forum 2011, Anchorage, USA, 2011, pp. 1112—1121.

Later, we have extended iMapReduce and developed i2MapReduce, which supports fine-grain incremental processing (kv-pair level rather than task level in Incoop), general-purpose iterative processing (that extends the original iMapReduce and supports more iterative algorithms), and incremental processing for iterative computation.

For more infomation, please refer to our i2MapReduce paper in TKDE 2015.

* Yanfeng Zhang, Shimin Chen, Qiang Wang, Ge Yu. i2MapReduce: Incremental MapReduce for Mining Evolving Big Data [J]. IEEE Transactions on Knowledge and Data Engineering (TKDE), 27(7), July, 2015, pp. 1906-1919. 

This project is a prototype implementation of the iMapReduce idea. The prototype is based on Hadoop 1.0.3. It is better used for research perspective, but we don't recommend to use it in production. Of course, we welcome any feedback on i2MapReduce.
