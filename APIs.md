Since our iMapReduce implementation is based on Hadoop, its provided APIs are following Hadoop style.

# API #

To specify an iMapReduce program, users should implement the following two interfaces instead of Mapper and Reducer:

  * `public interface IterativeMapper<InStateK, InStateV, InStaticK, InStaticV, OutStateK, OutStateV>`

  * `public interface IterativeReducer<InStateK, InStateV, OutStateK, OutStateV>`

IterativeMapper has map function interface:

  * `void map(InStateK key, InStateV value, InStaticK datakey, InStaticV dataval, OutputCollector<OutStateK, OutStateV> output, Reporter report);`

And users should return the DFS path of the initial state data and static data through

  * `Path[] initStateData();`

  * `Path initStaticData();`

The returned state data path is actually a list, users can designate more than one initial state data file

After each iteration, the framework will invoke the following function, where users can write their own codes to do some recording or summarizing work. (**Optional**)

  * `boolean iterate();`

If return false, the iterative process will terminate, otherwise continue.

Correspondingly, IterativeReducer has the following two interfaces without the specification of initial state data path and static data path.

  * `void reduce(InStateK key, InStateV value, OutputCollector<OutStateK, OutStateV> output, Reporter report);`

  * `boolean iterate();`


# System Parameters #

Users also specify their iterative process through setting the following system parameters.

  * `job.setBoolean("mapred.job.iterative", true);`

This submitted job is an iMapReduce job. Otherwise, it is an ordinary Hadoop MapReduce job as usual.

  * `job.setBoolean("mapred.iterative.reducesync", true);`

The reducers are activated when all the mappers are completed. Otherwise, we can execute them asynchronously if possible.

  * `job.setBoolean("mapred.iterative.mapsync", true);`

The mappers are activated synchronously as the normal MapReduce job. This is useful for Kmeans-like algorithm, since we need all the reducers' result (all the updated centroids) to perform map.

  * `job.set("mapred.iterative.jointype", "one2one");`

The state data and the static data are joined in a "one-to-one" manner, say joining on the same key. Otherwise, we can set it "one2all" in order to satisfy some requirements.

  * `job.setInt("mapred.iterative.partitions", partitions);`

The data are split into some number of partitions, which is equal to the number of map-reduce task pairs.

  * `job.setInt("mapred.iterative.snapshot.interval", 5);`

The framework generates a result snapshot after every 5 iterations.

  * `job.setInt("mapred.iterative.stop.iteration", 50);`

The iterative process will terminate after 50 iterations.

  * `job.setBoolean("mapred.iterative.firstjob", true);`

For multiple job case, we should specify the first job, where the initial data is injected.