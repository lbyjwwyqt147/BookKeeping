package pers.liujunyi.bookkeeping.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisTest {

  static ShardedJedisPool pool;
	
  static{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");  
		pool = (ShardedJedisPool) context.getBean("shardedJedisPool"); 
	}
  
  public void test(){
	  
	  //从池中获取jedis对象
	  ShardedJedis jedis = pool.getResource();
	  String key = "name";
	  String value = "李四";
	  
	  //删除数据
	  Long cLong = jedis.del(key);
	  System.out.println("del ： " + cLong);
	  
	  //存key-value数据
	  jedis.set(key, value);
	  
	  //存key-value数据  有效期为5秒
	  jedis.setex("age", 5, "25");
	  
	  //在后面追加值
	  jedis.append(key, "@163.com");
	  
	  
	  
	  //取数据
	  String vString = jedis.get(key);
	  System.out.println(vString);
	  
	  //给hash添加key-value
	  jedis.hset("url", "taobao", "www.taobao.com");
	  
	  Map<String, String> map = new HashMap<String, String>();
	  map.put("name", "张三");
	  map.put("age", "40");
	  map.put("sex", "男");
	  map.put("title", "存map");
	  //批量设置值hash
	  jedis.hmset("user", map);
	  
	  //获取hash中某个key的值
	  String nameString = jedis.hget("user", "name");
	  System.out.println("name ：" + nameString);
	 
	  //获取hash的多个key值
	  List<String> urlList = jedis.hmget("url", "taobao");
	  System.out.println("urlList : "+urlList.size());
	  
	  //取hash的所有key值
	  Map<String, String> userMap = jedis.hgetAll("user");
	  
	  for(Map.Entry<String, String> entry:userMap.entrySet()){    
		  System.out.println(entry.getKey()+" === "+entry.getValue());    
	  }   
	  
	  
	  
	  
	  //释放对象池
	  
	  
  }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
