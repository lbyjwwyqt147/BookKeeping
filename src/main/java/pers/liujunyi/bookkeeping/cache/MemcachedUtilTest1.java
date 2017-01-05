package pers.liujunyi.bookkeeping.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;


public class MemcachedUtilTest1 {


	private static MemCachedClient memcachedClient;
	
	static{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-memcached.xml");  
		memcachedClient = (MemCachedClient)context.getBean("memcachedClient"); 
	}
	
	public void test(){
		
		//添加  如果存在添加失败
		boolean success =  memcachedClient.add("hello", "word");
		System.out.println( "add ：" + success);
		//如果重复就不添加
		memcachedClient.set("name", "张三");
		System.out.println("add后："+ memcachedClient.get("hello"));
		System.out.println("set后："+ memcachedClient.get("name"));
		//删除
		memcachedClient.delete("hello");
		System.out.println("删除后：" + memcachedClient.get("hello"));
	}
	
	public static void main(String[] args) {
		MemcachedUtilTest1 test1 = new MemcachedUtilTest1();
		test1.test();

	}

}
