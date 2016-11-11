package pers.liujunyi.bookkeeping.datasource;

/***
 * 文件名称: DynamicDataSourceHolder.java
 * 文件描述: 纪录当前数据源的key
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月19日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class DynamicDataSourceHolder {
	//写库对应的数据源key  
    private static final String MASTER = "master";  
  
    //读库对应的数据源key  
    private static final String SLAVE = "slave";  
      
    //使用ThreadLocal记录当前线程的数据源key  
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();  
  
    /** 
     * 设置数据源key 
     * @param key 
     */  
    public static void putDataSourceKey(String key) {  
        holder.set(key);  
    }  
  
    /** 
     * 获取数据源key 
     * @return 
     */  
    public static String getDataSourceKey() {  
        return holder.get();  
    }  
      
    /** 
     * 标记写库 
     */  
    public static void markMaster(){  
        putDataSourceKey(MASTER);  
    }  
      
    /** 
     * 标记读库 
     */  
    public static void markSlave(){  
        putDataSourceKey(SLAVE);  
    }  

}
