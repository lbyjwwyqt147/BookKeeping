package pers.liujunyi.bookkeeping.thread;

import pers.liujunyi.bookkeeping.thread.struct.MailSendTask;

public class ThreadPoolExecutorDemo2 {

	static ThreadPool<ICommon> pool = new ThreadPool<ICommon>(5);
	
	public static void main(String[] args) {
		pool.addJob(new MailSendTask("a"));
		pool.addJob(new MailSendTask("b"));
		pool.addJob(new MailSendTask("c"));
		pool.addJob(new MailSendTask("d"));
		pool.addJob(new MailSendTask("f"));
		pool.addJob(new MailSendTask("g"));
		pool.addJob(new MailSendTask("h"));

	}

}
