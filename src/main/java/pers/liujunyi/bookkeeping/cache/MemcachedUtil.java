package pers.liujunyi.bookkeeping.cache;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/***
 * memcached工具类
 * @author ljy
 *
 */
public class MemcachedUtil {

	/** 
     * memcached客户端单
     */  
    private static MemCachedClient cachedClient = new MemCachedClient(); 
	
    /**
     * 初始化连接池
     */
	static{
		
		//缓存服务器列表，当使用分布式缓存时，可以指定多个缓存服务器，这里应该设置多个不同的服务器，
	      String[] servers = {"101.200.75.226:11211"};
	      // 服务器权重
	      Integer[] weights = {3, 2};
	      
	      //创建一个Socked连接池实例
	      SockIOPool pool =  SockIOPool.getInstance();
	       
	      //向连接池设置服务器
	      pool.setServers(servers);
	      //向连接池设置服务器权重
	      pool.setWeights(weights);
	      //初始化连接数
	      pool.setInitConn(10);
	      //初始化最小连接数
	      pool.setMinConn(10);
	      //初始化最大链接数
	      pool.setMaxConn(1000);
	      //初始化最大处理时间
	      pool.setMaxIdle(1000*60*60);
	      
	      //设置连接池守护线程的睡眠时间     设置为0，维护线程不启动
	      pool.setMaintSleep(60);
	      
	      //设置tcp参数,链接超时
	      pool.setNagle(false);
	      // 设置socket的读取等待超时值
	      pool.setSocketTO(60);
	      //设置socket的连接等待超时值
	      pool.setSocketConnectTO(0);
	      
	      //初始并启动连接池
	      pool.initialize();
	}
	
	/**
	 * 构造函数
	 */
    private MemcachedUtil(){
    	
    }  
      
    public static boolean add(String key, Object value) {  
        return cachedClient.add(key, value);  
    }  
      
    public static boolean add(String key, Object value, Integer expire) {  
        return cachedClient.add(key, value, expire);  
    }  
      
    public static boolean put(String key, Object value) {  
        return cachedClient.set(key, value);  
    }  
      
    public static boolean put(String key, Object value, Integer expire) {  
        return cachedClient.set(key, value, expire);  
    }  
      
    public static boolean replace(String key, Object value) {  
        return cachedClient.replace(key, value);  
    }  
      
    public static boolean replace(String key, Object value, Integer expire) {  
        return cachedClient.replace(key, value, expire);  
    }  
      
    public static Object get(String key) {  
        return cachedClient.get(key);  
    } 
	
	public static void main(String[] star) {
			      
	      
	      
	}
}
