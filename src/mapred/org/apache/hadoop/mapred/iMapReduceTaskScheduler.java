package org.apache.hadoop.mapred;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;

public class iMapReduceTaskScheduler extends TaskScheduler {

	  private static final int MIN_CLUSTER_SIZE_FOR_PADDING = 3;
	  public static final Log LOG = LogFactory.getLog(iMapReduceTaskScheduler.class);
	  
	  protected JobQueueJobInProgressListener jobQueueJobInProgressListener;
	  private EagerTaskInitializationListener eagerTaskInitializationListener;
	  private float padFraction;
	  private JobID currFirstJob;
	  
	  //tasktracker <-> taskid1, taskid2 map
	  public Map<JobID, Map<String, ArrayList<Integer>>> taskTrackerMap = new HashMap<JobID, Map<String, ArrayList<Integer>>>();
	  
	  //for assign task, for there are multiple taskpairs in a worker
	  public Map<JobID, Map<String, Map<Integer, Boolean>>> reduceTakenMap = new HashMap<JobID, Map<String, Map<Integer, Boolean>>>();
	  public Map<JobID, Map<String, Map<Integer, Boolean>>> mapTakenMap = new HashMap<JobID, Map<String, Map<Integer, Boolean>>>();
	  
	  //taskid <-> tasktracker map
	  public Map<JobID, Map<Integer, String>> taskidTTMap = new HashMap<JobID, Map<Integer, String>>();
	  
	  public iMapReduceTaskScheduler() {
	    this.jobQueueJobInProgressListener = new JobQueueJobInProgressListener();
	    this.eagerTaskInitializationListener =
	      new EagerTaskInitializationListener();
	  }
	  
	  @Override
	  public synchronized void start() throws IOException {
	    super.start();
	    taskTrackerManager.addJobInProgressListener(jobQueueJobInProgressListener);
	    
	    eagerTaskInitializationListener.start();
	    taskTrackerManager.addJobInProgressListener(
	        eagerTaskInitializationListener);
	  }
	  
	  @Override
	  public synchronized void terminate() throws IOException {
	    if (jobQueueJobInProgressListener != null) {
	      taskTrackerManager.removeJobInProgressListener(
	          jobQueueJobInProgressListener);
	    }
	    if (eagerTaskInitializationListener != null) {
	      taskTrackerManager.removeJobInProgressListener(
	          eagerTaskInitializationListener);
	      eagerTaskInitializationListener.terminate();
	    }
	    super.terminate();
	  }
	  
	  @Override
	  public synchronized void setConf(Configuration conf) {
	    super.setConf(conf);
	    padFraction = conf.getFloat("mapred.jobtracker.taskalloc.capacitypad", 
	                                 0.01f);
	  }

	  @Override
	  public synchronized List<Task> assignTasks(TaskTrackerStatus taskTracker)
	      throws IOException {

	    ClusterStatus clusterStatus = taskTrackerManager.getClusterStatus();
	    int numTaskTrackers = clusterStatus.getTaskTrackers();

	    Collection<JobInProgress> jobQueue =
	      jobQueueJobInProgressListener.getJobQueue();
	    
	    //LOG.info("current job queue is: ");
	    for(JobInProgress tj : jobQueue){
	    	//LOG.info(tj.getJobID());
	    }

	    //
	    // Get map + reduce counts for the current tracker.
	    //
	    int maxCurrentMapTasks = taskTracker.getMaxMapTasks();
	    int maxCurrentReduceTasks = taskTracker.getMaxReduceTasks();
	    int numMaps = taskTracker.countMapTasks();
	    int numReduces = taskTracker.countReduceTasks();

	    //
	    // Compute average map and reduce task numbers across pool
	    //
	    int remainingReduceLoad = 0;
	    int remainingMapLoad = 0;
	    synchronized (jobQueue) {
	      for (JobInProgress job : jobQueue) {
	        if (job.getStatus().getRunState() == JobStatus.RUNNING) {
	        	if(job.getJobConf().getBoolean("mapred.job.iterative", false)){
	  	          if(!taskTrackerMap.containsKey(job.getJobID())){
		        	  Map<String, ArrayList<Integer>> taskmap = new HashMap<String, ArrayList<Integer>>();
		        	  taskTrackerMap.put(job.getJobID(), taskmap);
		          }
		          
		          if(!reduceTakenMap.containsKey(job.getJobID())){
		        	  Map<String, Map<Integer, Boolean>> reduceTaken = new HashMap<String, Map<Integer, Boolean>>();
		        	  reduceTakenMap.put(job.getJobID(), reduceTaken);
		          }
		          
		          if(!mapTakenMap.containsKey(job.getJobID())){
		        	  Map<String, Map<Integer, Boolean>> mapTaken = new HashMap<String, Map<Integer, Boolean>>();
		        	  mapTakenMap.put(job.getJobID(), mapTaken);
		          }
		          
		          if(!taskidTTMap.containsKey(job.getJobID())){
		        	  Map<Integer, String> tidTTMap = new HashMap<Integer, String>();
		        	  taskidTTMap.put(job.getJobID(), tidTTMap);
		          }
	        	}
	          
	          int totalMapTasks = job.desiredMaps();
	          int totalReduceTasks = job.desiredReduces();
	          remainingMapLoad += (totalMapTasks - job.finishedMaps());
	          remainingReduceLoad += (totalReduceTasks - job.finishedReduces());
	        }
	      }
	    }

	    // find out the maximum number of maps or reduces that we are willing
	    // to run on any node.
	    int maxMapLoad = 0;
	    int maxReduceLoad = 0;
	    if (numTaskTrackers > 0) {
	      maxMapLoad = Math.min(maxCurrentMapTasks,
	                            (int) Math.ceil((double) remainingMapLoad / 
	                                            numTaskTrackers));
	      maxReduceLoad = Math.min(maxCurrentReduceTasks,
	                               (int) Math.ceil((double) remainingReduceLoad
	                                               / numTaskTrackers));
	    }
	        
	    int totalMaps = clusterStatus.getMapTasks();
	    int totalMapTaskCapacity = clusterStatus.getMaxMapTasks();
	    int totalReduces = clusterStatus.getReduceTasks();
	    int totalReduceTaskCapacity = clusterStatus.getMaxReduceTasks();

	    //
	    // In the below steps, we allocate first a map task (if appropriate),
	    // and then a reduce task if appropriate.  We go through all jobs
	    // in order of job arrival; jobs only get serviced if their 
	    // predecessors are serviced, too.
	    //

	    //
	    // We hand a task to the current taskTracker if the given machine 
	    // has a workload that's less than the maximum load of that kind of
	    // task.
	    //
	       
	    if (numMaps < maxMapLoad) {
	    	//LOG.info("assigning jobs");
	      int totalNeededMaps = 0;
	      synchronized (jobQueue) {
	        for (JobInProgress job : jobQueue) {
	        	//LOG.info("assign job for " + job.getJobID());
	          if (job.getStatus().getRunState() != JobStatus.RUNNING) {
	        	  //LOG.info(job.getJobID() + " is not running");
	            continue;
	          }

	          Task t = null;
	          if(job.getJobConf().getBoolean("mapred.job.iterative", false)){
	        	  if(job.getJobConf().getBoolean("mapred.iterative.firstjob", true)){
	        		  //LOG.info("assign job for the first job " + job.getJobID());
	        		  currFirstJob = job.getJobID();
	        		  
	        		  t = job.obtainNewMapTask(taskTracker, numTaskTrackers,
	        	              taskTrackerManager.getNumberOfUniqueHosts());
	        		  
	        		  if (t != null) {
			        	  ArrayList<Integer> maps = null;
			        	  if(!taskTrackerMap.get(job.getJobID()).containsKey((taskTracker.trackerName))){		  
			        		  maps = new ArrayList<Integer>();
			        		  taskTrackerMap.get(job.getJobID()).put(taskTracker.trackerName, maps);
			        	  }else{
			        		  maps = taskTrackerMap.get(job.getJobID()).get(taskTracker.trackerName);
			        	  }
			        	  
			        	  Map<Integer, Boolean> reduceTaken = null;
			        	  if(!reduceTakenMap.get(job.getJobID()).containsKey(taskTracker.trackerName)){
			        		  reduceTaken = new HashMap<Integer, Boolean>();
			        		  reduceTakenMap.get(job.getJobID()).put(taskTracker.trackerName, reduceTaken);
			        	  }else{
			        		  reduceTaken = reduceTakenMap.get(job.getJobID()).get(taskTracker.trackerName);
			        	  }
			        	  
			        	  Map<Integer, Boolean> mapTaken = null;
			        	  if(!mapTakenMap.get(job.getJobID()).containsKey(taskTracker.trackerName)){
			        		  mapTaken = new HashMap<Integer, Boolean>();
			        		  mapTakenMap.get(job.getJobID()).put(taskTracker.trackerName,mapTaken);
			        	  }else{
			        		  mapTaken = mapTakenMap.get(job.getJobID()).get(taskTracker.trackerName);
			        	  }
			        	  
			        	  int mapid = t.getTaskID().getTaskID().getId();
			        	  maps.add(mapid);
			        	  reduceTakenMap.get(job.getJobID()).get(taskTracker.trackerName).put(mapid, true);
			        	  mapTakenMap.get(job.getJobID()).get(taskTracker.trackerName).put(mapid, true);
			        	  taskidTTMap.get(job.getJobID()).put(mapid, taskTracker.trackerName);
		        	  
			        	  return Collections.singletonList(t);
	        		  }
		          }else{
		        	  //LOG.info("assign job for the following job");
			          int maptasknum = -1;
			          if(taskTrackerMap.get(currFirstJob) == null) continue;
			          if(taskTrackerMap.get(currFirstJob).get(taskTracker.trackerName) == null) continue;
			          
			          //first receive request from this tasktracker in this job

		        	  //LOG.info("fill the tasks needed to be executed");
		        	  
		        	  if(!mapTakenMap.get(job.getJobID()).containsKey(taskTracker.trackerName)){
		        		  Map<Integer, Boolean> mapTaken = new HashMap<Integer, Boolean>();
		        		  mapTakenMap.get(job.getJobID()).put(taskTracker.trackerName, mapTaken);
		        	  }
		        	  
		        	  if(!reduceTakenMap.get(job.getJobID()).containsKey(taskTracker.trackerName)){
		        		  Map<Integer, Boolean> reduceTaken = new HashMap<Integer, Boolean>();
		        		  reduceTakenMap.get(job.getJobID()).put(taskTracker.trackerName, reduceTaken);
		        	  }
		        	  
		        	  for(int participate : taskTrackerMap.get(currFirstJob).get(taskTracker.trackerName)){
			        	  mapTakenMap.get(job.getJobID()).get(taskTracker.trackerName).put(participate, true);
			        	  reduceTakenMap.get(job.getJobID()).get(taskTracker.trackerName).put(participate, true);
		        	  }
			         
			          
			          //assign map tasks based on the correspongding task id of first job
			          for(int participate : taskTrackerMap.get(currFirstJob).get(taskTracker.trackerName)){
			        	  if(mapTakenMap.get(job.getJobID()).get(taskTracker.trackerName).get(participate)){
			        		  //LOG.info("check task id " + participate);
			        		  maptasknum = participate;
			        		  mapTakenMap.get(job.getJobID()).get(taskTracker.trackerName).put(participate, false);
			        		  break;
			        	  }
			          }
			          
			          if(maptasknum == -1){
			        	  try {
							throw new Exception("next map didn't match to first map");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			          }
			          
	        		  t = job.obtainNewMapTask(taskTracker, numTaskTrackers,
	        	              taskTrackerManager.getNumberOfUniqueHosts(), maptasknum);
	        		  if(t != null) return Collections.singletonList(t);
		          }
	          }else{
	        	  t = job.obtainNewMapTask(taskTracker, numTaskTrackers,
	    	              taskTrackerManager.getNumberOfUniqueHosts());
	        	  if(t != null) return Collections.singletonList(t);
	          }

	          //
	          // Beyond the highest-priority task, reserve a little 
	          // room for failures and speculative executions; don't 
	          // schedule tasks to the hilt.
	          //
	          totalNeededMaps += job.desiredMaps();
	          int padding = 0;
	          if (numTaskTrackers > MIN_CLUSTER_SIZE_FOR_PADDING) {
	            padding = Math.min(maxCurrentMapTasks,
	                               (int)(totalNeededMaps * padFraction));
	          }
	          if (totalMaps + padding >= totalMapTaskCapacity) {
	            break;
	          }
	        }
	      }
	    }else{
	    	LOG.info("numMaps is " + numMaps + " and maxMapLoad is " + maxMapLoad);
	    }

	    //
	    // Same thing, but for reduce tasks
	    //
	    if (numReduces < maxReduceLoad) {

	      int totalNeededReduces = 0;
	      synchronized (jobQueue) {
	        for (JobInProgress job : jobQueue) {
	          if (job.getStatus().getRunState() != JobStatus.RUNNING ||
	              job.numReduceTasks == 0) {
	            continue;
	          }

	          if(job.getJobConf().getBoolean("mapred.job.iterative", false)){
		          int reducetasknum = -1;
		          if(taskTrackerMap.get(currFirstJob) == null) continue;
		          if(taskTrackerMap.get(currFirstJob).get(taskTracker.trackerName) == null) continue;
		          
		          //according to the task id assignment in the same job
		          for(int participate : taskTrackerMap.get(currFirstJob).get(taskTracker.trackerName)){
		        	  if(reduceTakenMap.get(job.getJobID()).get(taskTracker.trackerName).get(participate)){
		        		  reducetasknum = participate;
		        		  reduceTakenMap.get(job.getJobID()).get(taskTracker.trackerName).put(participate, false);
		        		  break;
		        	  }
		          }
		          if(reducetasknum == -1){
		        	  try {
						throw new Exception("reduce didn't match to map");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		          }
		          
		          Task t = job.obtainNewReduceTask(taskTracker, numTaskTrackers, 
			              taskTrackerManager.getNumberOfUniqueHosts(), reducetasknum);
		          
		          if (t != null) {
			            return Collections.singletonList(t);
			          }
	          }else{
		          Task t = job.obtainNewReduceTask(taskTracker, numTaskTrackers, 
			              taskTrackerManager.getNumberOfUniqueHosts());
		          
		          if (t != null) {
			            return Collections.singletonList(t);
		          }
	          }

	          //
	          // Beyond the highest-priority task, reserve a little 
	          // room for failures and speculative executions; don't 
	          // schedule tasks to the hilt.
	          //
	          totalNeededReduces += job.desiredReduces();
	          int padding = 0;
	          if (numTaskTrackers > MIN_CLUSTER_SIZE_FOR_PADDING) {
	            padding = 
	              Math.min(maxCurrentReduceTasks,
	                       (int) (totalNeededReduces * padFraction));
	          }
	          if (totalReduces + padding >= totalReduceTaskCapacity) {
	            break;
	          }
	        }
	      }
	    }else{
	    	LOG.info("numReduces is " + numReduces + " and maxReduceLoad is " + maxReduceLoad);
	    }
	    return null;
	  }

	  @Override
	  public synchronized Collection<JobInProgress> getJobs(String queueName) {
	    return jobQueueJobInProgressListener.getJobQueue();
	  }  
}
