package pers.liujunyi.bookkeeping.aop;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

import pers.liujunyi.bookkeeping.datasource.DynamicDataSourceHolder;


/***
 * 文件名称: DataSourceAspect.java
 * 文件描述: 数据源AOP切面,该类控制使用Master主库(写库)还是Slave从库(读库) 
 *         如果事务管理中配置了策略，则采用配置的事物策略中的标记了readOnly的方法是用Slave从库(读库)，其他使用Master主库(写库)
 *         如果没有配置事物管理策略，则采用方法名匹配的原则，以query、find、get、select 开头方法用 Slave从库(读库)，其他使用Master主库(写库)
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月19日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class DataSourceAspect {
	private static final Logger LOGGER = Logger.getLogger(DataSourceAspect.class);  
	//存放使用读库(从库)时的方法名前缀
	private CopyOnWriteArrayList<String> slaveMethodPattern = new CopyOnWriteArrayList<String>(); 
	//初始化默认使用读库(从库)时的方法名前缀
	private static final String[] defaultSlaveMethodStart = new  String[]{ "query", "find", "get","select","load","search" };
	private  String[] slaveMethodStart;
	
	/**
	 * 读取事物管理中的策略
	 * @param txAdvice 事物
	 */
	@SuppressWarnings("unchecked")
	public void setTxAdvice(TransactionInterceptor txAdvice){
		try {
			//没有配置事物策略
			if(txAdvice == null){
				return;
			}
			//从txAdvice中获取事物策略配置信息
			TransactionAttributeSource transactionAttributeSource = txAdvice.getTransactionAttributeSource();
			if(!(transactionAttributeSource instanceof NameMatchTransactionAttributeSource)){
				return;
			}
			//使用反射技术获取到NameMatchTransactionAttributeSource对象中的nameMap属性值  
	        NameMatchTransactionAttributeSource matchTransactionAttributeSource = (NameMatchTransactionAttributeSource) transactionAttributeSource;  
	        Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap"); 
	        //设置该字段可访问
	        nameMapField.setAccessible(true);
	        Map<String, TransactionAttribute> map = (Map<String, TransactionAttribute>) nameMapField.get(matchTransactionAttributeSource);
	        //遍历Map
	        for(Map.Entry<String, TransactionAttribute> entry : map.entrySet()){
	        	//判断之后定义了ReadOnly的策略才加入到slaveMethodPattern  
	        	if (!entry.getValue().isReadOnly()) {
	                continue;  
	            }  
	            slaveMethodPattern.add(entry.getKey());
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 在进入service 方法之前执行
	 * @param point 切面对象
	 */
	public void before(JoinPoint point){
		try {
			String methodName = point.getSignature().getName();
			AtomicBoolean isSlave =  new AtomicBoolean(false);
			
			if(slaveMethodPattern.isEmpty()){
				 // 当前Spring容器中没有配置事务策略，采用方法名匹配方式  
				isSlave.set(isSlave(methodName));
			}else {  
	            // 使用策略规则匹配  
	            for (String mappedName : slaveMethodPattern) {  
	                if (isMatch(methodName, mappedName)) {  
	                    isSlave.set(true);;  
	                    break;  
	                }  
	            }  
	        }  
	  
	        if (isSlave.get()) {  
	        	LOGGER.info("当前执行方法名："+methodName+" 应该操作从库(读库)....");
	            // 标记为读库  
	            DynamicDataSourceHolder.markSlave();  
	        } else {  
	        	LOGGER.info("当前执行方法名："+methodName+" 应该操作主库(写库)....");
	            // 标记为写库  
	            DynamicDataSourceHolder.markMaster();  
	        }  
		} catch (Exception e) {
			LOGGER.error("动态切换(主库、从库)数据源出现异常.");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 判断是否为从库(读库)
	 * @param methodName
	 * @return
	 */
	private boolean isSlave(String methodName){
		// 方法名以query、find、get、select开头的方法名走从库  
        return StringUtils.startsWithAny(methodName, getSlaveMethodStart());  
	}
	
	/**
	 * 匹配通配符
	 * @param methodName
	 * @param mappedName
	 * @return
	 */
	protected boolean isMatch(String methodName, String mappedName) {  
        return PatternMatchUtils.simpleMatch(mappedName, methodName);  
    }  
  
	
	  /** 
     * 用户指定slave的方法名前缀 
     * @param slaveMethodStart 
     */  
    public void setSlaveMethodStart(String[] slaveMethodStart) {  
        this.slaveMethodStart = slaveMethodStart;  
    }  
  
    public String[] getSlaveMethodStart() {  
        if(this.slaveMethodStart == null){  
            // 没有指定，使用默认  
            return defaultSlaveMethodStart;  
        }  
        return slaveMethodStart;  
    } 
}
