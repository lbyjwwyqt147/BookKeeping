package pers.liujunyi.bookkeeping.securityFilter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 过滤用户请求
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。
 * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中
 * 供Spring Security使用，用于权限校验。
 * @author ljy
 *
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter{

	/** 与applicationContext-security.xml里的myFilter的属性securityMetadataSource对应，  
                   其他的两个组件，已经在AbstractSecurityInterceptor定义  */
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	/** 实现AbstractSecurityInterceptor父类中的方法 */
	@Override
	public Class<?> getSecureObjectClass() {
		/** 下面的MyAccessDecisionManager的supports方面必须放回true,否则会提醒类型错误    */
        return FilterInvocation.class;  
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		 return this.securityMetadataSource;  
	}
	/** 实现AbstractSecurityInterceptor父类中的方法 结束 */
	
	

	/** 以下是实现Filter接口中的方法 */
	@Override
	public void destroy() {
	
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		/** 就是把doFilter传进来的request,response和FilterChain对象保存起来，供FilterSecurityInterceptor的处理代码调用。 */
		FilterInvocation fi = new FilterInvocation(arg0, arg1, arg2);
		invoke(fi);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
	/** 以下是实现Filter接口中的方法 结束 */
   
	
	
	/**
	 * 
	 * @param fi
	 * @throws IOException
	 * @throws ServletException
	 */
	 public void invoke( FilterInvocation fi ) throws IOException, ServletException{
		/** object为FilterInvocation对象 */ 
	    /** super.beforeInvocation(fi); 源码 */  
	    /** 1.获取请求资源的权限 
	                   执行 Collection<ConfigAttribute> attributes =   securityMetadataSource.getAttributes(fi);   */
	    /** 2.是否拥有权限  
	        this.accessDecisionManager.decide(authenticated, fi, attributes);  
	       this.accessDecisionManager.decide(authenticated, fi, attributes); */
		  InterceptorStatusToken  token = super.beforeInvocation(fi);
		  try{
		   fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		  }finally{
		   super.afterInvocation(token, null);
		  }
		  
	 }

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}
	 
	 
}
