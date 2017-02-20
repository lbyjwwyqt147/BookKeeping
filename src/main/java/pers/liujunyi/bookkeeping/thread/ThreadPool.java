package pers.liujunyi.bookkeeping.thread;



import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *@function:线程池任务执行框架，如果按照KEY-JOB的方式添加则可以保证同一个KEY的任务是可以顺序执行，
 *如果直接添加任务的方式，则默认按照选择任务最少的队列添加。同时可以设置队列间是否分享任务，前提是所有
 *任务都彼此独立，不受多线程影响。
 *
 */

public class ThreadPool<K>{
	private static final int DEFAULT_THREAD_SIZE = 4;
	private final ConcurrentMap<K,JobQueue> threadMap = new ConcurrentHashMap<K,JobQueue>();
	private int threadSize = DEFAULT_THREAD_SIZE;
	private List<JobQueue> queueList = new CopyOnWriteArrayList<JobQueue>();
	//是否分发任务，在一个队列空闲的时候，可以从别的工作队列获取任务来执行，获取的任务必须是独立不受多线程影响的
	private boolean share = false; 
	
	public ThreadPool(int threadSize){
		this("jobQueue", threadSize);
	}
	
	public ThreadPool(String name,int threadSize){
		for(int i=0;i<threadSize;i++){
			JobQueue queue = new JobQueue(name+i);
			queue.startJob();
			queueList.add(queue);
		}
	}
	
	public ThreadPool(){
		this(DEFAULT_THREAD_SIZE);
	}
	
	
	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}

	/**
	 * 获取工作量最小的队列
	 * @return
	 */
	private JobQueue getMinSizeJobQueue(){
		JobQueue minQueue = queueList.get(0);
		for(int i=1;i<queueList.size();i++){
			JobQueue tempQueue = queueList.get(i);
			if(minQueue.getJobCount() > tempQueue.getJobCount()){
				minQueue = tempQueue;
			}
		}
		return minQueue;
	}
	
	/**
	 * 获取工作量做大的队列
	 * @return
	 */
	private JobQueue getMaxSizeJobQueue(){
		JobQueue maxQueue = queueList.get(0);
		for(int i=1;i<queueList.size();i++){
			JobQueue tempQueue = queueList.get(i);
			if(maxQueue.getJobCount() < tempQueue.getJobCount()){
				maxQueue = tempQueue;
			}
		}
		return maxQueue;
	}
	
	/**
	 * 获取空闲的工作队列
	 * @return
	 */
	private JobQueue getIdleJobQueue(){
		for(JobQueue queue : queueList){
			if(queue.isIdle()){
				return queue;
			}
		}
		return null;
	}
	
	public void addJob(K key,Runnable job){
		JobQueue queue = threadMap.get(key);
		if(queue == null){
			queue = getMinSizeJobQueue();
			threadMap.putIfAbsent(key, queue);
			
		}
		queue.addJob(job);
		if(share){
			//获取一个空闲队列和任务最多的队列，分享一个任务到空闲队列
			JobQueue idleQueue = getIdleJobQueue();
			if(idleQueue != null){
				JobQueue maxSizeQueue = getMaxSizeJobQueue();
				if(maxSizeQueue != null){
					maxSizeQueue.shareJob(idleQueue);
				}
			}
		}
	}
	
	public void addJob(Runnable job){
		JobQueue queue = getMinSizeJobQueue();
		if(queue != null){
			queue.addJob(job);
		}
	}
	
	/**
	 * 移除缓存的KEY，因为在加载的时候为了保证同一个KEY的任务在同一个线程执行
	 * 所以每一个KEY对应了一个线程，在不使用之后需要调用该方法移除这个KEY-线程的缓存
	 * @param key
	 */
	public void removeKeyCache(K key){
		threadMap.remove(key);
	}
	
	public void close(){
		for(JobQueue queue : queueList){
			queue.stopJob();
		}
	}
	
	/**
	 * 获取工作数量
	 * @return
	 */
	public int getJobCount(){
		int count = 0;
		for(JobQueue queue : queueList){
			count += queue.getJobCount();
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		ThreadPool<Integer> pool = new ThreadPool<Integer>(5);
		for(int j=0;j<100;j++){
			for(int i=0;i<10;i++){
				final int m = i;
				final int n = j;
				pool.addJob(i,new Runnable() {
					public void run() {
						System.out.println(Thread.currentThread().getName()+":"+m+","+n);
					}
				});
			}
		}
	}
		
}
