package pers.liujunyi.bookkeeping.entity;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Gson序列化对象排除属性 
 * 
 * 调用方法： 
 * String[] keys = { "id" }; 
 * Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExcludeKit(keys)).create(); 
 * @author ljy
 */
public class GsonExcludeKit implements ExclusionStrategy {

	String[] keys;  
	  
    public GsonExcludeKit(String[] keys) {  
        this.keys = keys;  
    }  
  
    @Override  
    public boolean shouldSkipClass(Class<?> arg0) {  
        return false;  
    }  
  
    @Override  
    public boolean shouldSkipField(FieldAttributes arg0) {  
        for (String key : keys) {  
            if (key.equals(arg0.getName())) {  
                return true;  
            }  
        }  
        return false;  
    }  

}
