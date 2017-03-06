package pers.liujunyi.bookkeeping.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;

public class ThreadPoolExecutorDemo1 {

	public static void main(String[] args) {
		
		//获取cpu核数
		int cpuNumber = Runtime.getRuntime().availableProcessors();
		
		System.out.println("当前cpu核数是：" + cpuNumber);
		
	    //构建线程池
		//初始化一个线程池 默认情况线程池中是没有线程的   需要提交任务后才会创建线程
		//第一个参数：线程池大小(获取当前cpu核数)
		//第二个参数:最大线程数(表示线程池中最大能创建多个线程)
		//第三个参数：没有任务执行时最多保持多久时间终止线程 当池子的线程数大于corePoolSize的时候，多余的线程会等待keepAliveTime长的时间，如果无请求可处理就自行销毁
		//第四个参数:时间单位：TimeUnit.DAYS 天;TimeUnit.HOURS 小时;TimeUnit.MINUTES 分钟; TimeUnit.SECONDS 秒; TimeUnit.MILLISECONDS 毫秒;TimeUnit.MICROSECONDS 微妙;  TimeUnit.NANOSECONDS  纳秒;             
		//第五个参数：一个阻塞队列，用来存储等待执行的任务 ArrayBlockingQueue 基于数组的先进先出队列，此队列创建时必须指定大小；;LinkedBlockingQueue 基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE ;SynchronousQueue 不会保存提交的任务，而是将直接新建一个线程来执行新来的任务;
		ThreadPoolExecutor executor = new ThreadPoolExecutor(cpuNumber, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(cpuNumber));
		
		ExecutorService executor2 = Executors.newFixedThreadPool(cpuNumber);
		String a = "abc";
		//新增操作任务
		executor2.execute(new Runnable() {
			@Override
			public void run() {
			    System.out.println("这里调用新增数据方法："+a);
				
			}
		});
		
		//修改操作任务
		executor2.execute(new Runnable() {
			
			@Override
			public void run() {
			    System.out.println("这里调用修改数据方法");
				
			}
		});
				
		executor2.shutdown();
		
		
		
		
		
		
		
		
		
		
		
		
		for(int i=0;i<15;i++){
            MyTask myTask = new MyTask();
            //向线程池中提交任务  返回值为void 由线程池去执行
            //1.如果当前线程池中的线程数量小于corePoolSize(线程池大小)，则每来一个任务，就会创建一个线程去执行这个任务
            //2.如果当前线程池中的线程数量大于corePoolSize(线程池大小)，则每来一个任务，会尝试添加到任务缓存队列中，若添加成功，该任务会等待空闲线程将其取出去执行；若添加失败（一般来说就是队列已满），则会创建新的线程去执行这个任务
            //3.如果当前线程池中的线程数达到了maximumPoolSize(最大线程数)并且任务缓存队列已满，则会采取任务拒绝策略进行处理
            //4.如果线程池中的线程数量大于corePoolSize(线程池大小)时，如果某线程空闲时间超过了keepAliveTime 线程将会终止，直到线程池中的线程数量不大于corePoolSize(线程池大小)时
            //当向线程池中提交的任务数量大于指定线程池大小5时，将线程放在队列中，当队列中满后（这里设置的是5个）会创建新的线程
            executor.execute(myTask);
            
            //executor2.execute(myTask);
            
            //线程池中的线程数量
            int poolSize = executor.getPoolSize();
            
            //队列中的线程数量
            int queueSize = executor.getQueue().size();
            
            System.out.println("线程池中线程数目："+poolSize+"，队列中等待执行的任务数目："+
            		queueSize+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
            
            if(poolSize == 5){
            	System.out.println("线程池中的任务已经达到了指定的线程数5，现在开始向队列中添加任务");
            }
            if(queueSize == 5){
            	System.out.println("队列的任务已经达到了指定的线程数5，现在开始创建新的线程");
            }
        }
		
		//不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
        executor.shutdown();

	}

}

class MyTask implements Runnable {
   
    @Override
    public void run() {
    	MyTask task =  new MyTask();
    	task.findInfo();
    	task.saveInfo();
    }
    
    public void saveInfo(){
    	System.err.println("我在进行保存数据...........");
    }
    
    public void findInfo(){
    	System.out.println("我在进行查询数据...........");
    }
    
    
    
    
    
}
