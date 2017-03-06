package pers.liujunyi.bookkeeping.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorDemo3 {

    static	ExecutorService excutor =  Executors.newFixedThreadPool(5);
	
	public static void main(String[] args) {
		excutor.execute(new Runnable() {
			
			@Override
			public void run() {
				send("张三");
			
				
				新增数据
				
				又执行查询操作
				
			}
		});
		excutor.shutdown();
	}
	
	public static void send(String string){
		System.out.println("开始给:"+string+" 发送邮件");
	}
}
