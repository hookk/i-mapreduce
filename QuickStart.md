  1. Of course, download iMapReduce [hadoop-imapreduce-0.1.tar.gz](http://i-mapreduce.googlecode.com/files/hadoop-imapreduce-0.1.tar.gz).
  1. To deploy a cluster environment, you can refer to [Hadoop Quick Start instructions](http://hadoop.apache.org/common/docs/current/), if you've never used Hadoop.
  1. Modify configuration files in conf/ directory according to your cluster environment, hadoop-site.xml (e.g., jobtracker, namenode, ...), slaves, masters, hadoop-env.sh.
  1. The algorithm samples are provided in _algorithms_ directory, you can simply execute shell scripts to run the algorithms including SSSP, PageRank, KMeans, Power of Matrix.
  1. The real data involved in these applications could be found at [http://rio.ecs.umass.edu/~yzhang/data/](http://rio.ecs.umass.edu/~yzhang/data/)
  1. For more details, you can read our [Wikipage](http://code.google.com/p/i-mapreduce/w/list).

For PageRank example, if you would like to use some other datasets, please first transform the input data format to the correct one. iMapReduce requires the input format should be like this:
src1       dest1 dest2 dest3
src2       dest1 dest4
src3       dest2 dest4
src4       dest1

There are two requirement:
  * The src ids should be continuous
  * The src ids should cover all the appeared ids, that is if there are 1000 nodes (including src and dest) there should be 1000 src ids covering all the node ids.

