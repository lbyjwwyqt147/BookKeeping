package pers.liujunyi.bookkeeping.thread;



import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class JobQueue extends Thread {

	private BlockingDeque<Runnable> queue = null;
	private volatile boolean running = false;
	private static final int DEFAULT_SIZE = 100000;
	private int jobSize = DEFAULT_SIZE;
	
	public JobQueue(){
		this(DEFAULT_SIZE);
	}
	
	public JobQueue(int size){
		this(size,"default_job_queue");
	}
	
	public JobQueue(String name){
		this(DEFAULT_SIZE,name);
	}
	
	public JobQueue(int size,String name){
		this.jobSize = size;
		queue = new LinkedBlockingDeque<Runnable>();
		setName(name);
	}
		
	public int getJobSize() {
		return jobSize;
	}

	public void startJob(){
		if(running)
			return;
		running = true;
		start();
	}
	
	public void stopJob(){
		running = false;
		queue.clear();
	}
		
	public boolean isRunning(){
		return running;
	}
	
	public boolean isIdle(){
		return running && queue.isEmpty();
	}
	
	public void addJob(Runnable job){
		try {
			queue.put(job);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	public int getJobCount(){
		return queue.size();
	}
	
	public void clearJob(){
		queue.clear();
	}
	
	/**
	 * 鍒嗕韩涓�釜宸ヤ綔鍒板彟澶栦竴涓伐浣滈槦鍒楋紝榛樿鏄粠闃熷垪鏈熬鍙栦竴涓换鍔�
	 * @param queue
	 */
	public void shareJob(JobQueue queue){
		Runnable job = this.queue.removeLast();
		if(job != null){
			queue.addJob(job);
		}
	}
		
	
	@Override
	public void run() {
		while(running){
			try {
				Runnable job = queue.takeFirst();
				if(job != null){
					long beginTime = System.currentTimeMillis();
					job.run();
					long endTime = System.currentTimeMillis();
					long runTime = endTime - beginTime;
					if(runTime > 5000){
						System.out.println("job run long time "+runTime+" "+job.getClass());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
