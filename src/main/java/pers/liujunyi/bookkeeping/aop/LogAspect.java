package pers.liujunyi.bookkeeping.aop;


import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;

import com.google.gson.Gson;


/***
 * 文件名称: LogAspect.java
 * 文件描述: 日志AOP
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月17日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Aspect
public class LogAspect {

	private final Logger logger = Logger.getLogger(LogAspect.class);
	
	 //请求IP
    private String ip = null;
    //类路径
    private String targetName = null;
    //方法名
    private String methodName = null;
    // 获取输入参数  
    private  ConcurrentMap<String,Object> inputParamMap = null;
    // 获取请求url 
    private String requestPath = null;
	//当前登录用户
	private String userName = "用户未登录";
    // 开始时间  
    private long startTimeMillis = 0; 
    // 结束时间  
    private long endTimeMillis = 0;
    //结果
    private ConcurrentMap<String, Object> outputParamMap = null;
   
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controllerLogsAspect() {
    	
    } 
    
    /**
     * 方法调用前触发  
     * @param joinPoint
     */
    @Before("controllerLogsAspect()")  
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {  
    	// 记录方法开始执行的时间  
        startTimeMillis = System.currentTimeMillis(); 
    }  
  
    /**
     * 方法调用后触发
     * @param joinPoint
     */
    @After("controllerLogsAspect()")  
    public void doAfterInServiceLayer(JoinPoint joinPoint) {  
    	// 记录方法执行完成的时间  
        endTimeMillis = System.currentTimeMillis(); 
        this.printOptLog();  
    }  
  
   
    @Around("controllerLogsAspect()")  
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {   
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;  
        HttpServletRequest request = sra.getRequest();  
        // 从session中获取用户信息  
        String[] loginInfo = (String[]) request.getSession().getAttribute(Constants.USER_SESSION);  
        if(loginInfo != null && loginInfo.length > 0){  
            userName = loginInfo[1];  
        }
        targetName = pjp.getTarget().getClass().getName();  
        methodName = pjp.getSignature().getName();  
        ip = request.getRemoteAddr();
        inputParamMap = ControllerUtil.getFormData(request);
        requestPath = request.getRequestURI();  
        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行  
        outputParamMap = new ConcurrentHashMap<String, Object>();
        // result的值就是被拦截方法的返回值  
        Object result = pjp.proceed();
        if(result != null){
        	outputParamMap.put("result", result);
        }
        return result;  
    }  
  
    /**
     * 控制台输出日志   
     */
    private void printOptLog() {  
        Gson gson = new Gson(); 
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);  
        // 输出字符串
        StringBuffer printBuffer = new StringBuffer();
        printBuffer.append("\n");
        printBuffer.append("\n ********************************** Controller 控制台日志 开始  ********************************** ");
        printBuffer.append("\n");
        printBuffer.append("\n  开始时间 ：").append(optTime);
        printBuffer.append("\n  请求IP ：").append(ip);
        printBuffer.append("\n  请求用户 ：").append(userName);
        printBuffer.append("\n  类名称 ：").append(targetName);
        printBuffer.append("\n  请求url地址 ：").append(requestPath);
        printBuffer.append("\n  请求方法 ：").append(methodName);
        printBuffer.append("\n  请求参数 ：").append(gson.toJson(inputParamMap));
        printBuffer.append("\n  返回值 ：").append(gson.toJson(outputParamMap));
        printBuffer.append("\n  执行总消耗时间 ：").append(endTimeMillis - startTimeMillis).append("毫秒");
        printBuffer.append("\n");
        printBuffer.append("\n ********************************** Controller 控制台日志 结束  ********************************** ");
        printBuffer.append("\n");
        logger.info(printBuffer.toString());  
    }  
	
}
