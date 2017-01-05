package pers.liujunyi.bookkeeping.securityFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.google.gson.Gson;

/***
 * 文件名称: MyAuthenticationEntryPoint.java
 * 文件描述: 自定义用户登录验证失败处理
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class MyAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

   public  LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint;
	
 
	public MyAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}


	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	static Pattern apiPattern= Pattern.compile("^/[a-z]v[0-1]/(w|r)/");

	
	/**
	 * 当访问的资源没有权限，会调用这里  
	 */
    @Override  
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)  
                throws IOException, ServletException {  
    	
    	String redirectUrl = null;
    	
    	String url = request.getRequestURI();

    	System.out.println(" ******************* " +url +" 没有权限访问   **************  ");
    	Matcher m=apiPattern.matcher(url); 
    	if( m.find()){
    		
    		response.setContentType("application/json;charset=UTF-8");
	        response.setHeader("Cache-Control","no-store, max-age=0, no-cache, must-revalidate");
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	        response.setHeader("Pragma", "no-cache");
	        ConcurrentMap<String,Object> map = new ConcurrentHashMap<String, Object>();
		    map.put("success", false);
		    map.put("message", "你还未登录.");
		    Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(map));
            out.flush();
            out.close();
    		
    	}else{
    		if (this.isUseForward()) { 
    			if (this.isForceHttps() && "http".equals(request.getScheme())) {
    				// First redirect the current request to HTTPS. // When that request is received, the forward to the login page will be used. redirectUrl = buildHttpsRedirectUrlForRequest(request); } 
    				if (redirectUrl == null) { 
    					String loginForm = determineUrlToUseForThisRequest(request, response, authException);
    					RequestDispatcher dispatcher = request.getRequestDispatcher(loginForm);
    					dispatcher.forward(request, response); return; } 
    				} else {
    					// redirect to login page. Use https if forceHttps true 
    					redirectUrl = buildRedirectUrlToLoginPage(request, response, authException);
    				} 
    				redirectStrategy.sendRedirect(request, response, redirectUrl);
    			}
    			
    	}
    	
    		
    		
            //super.commence(request, response, authException);  
          
           //返回json形式的错误信息  
    	/* response.setCharacterEncoding("UTF-8");  
           response.setContentType("application/json");  
                   
           response.getWriter().println("{\"ok\":0,\"msg\":\""+authException.getLocalizedMessage()+"\"}");  
           response.getWriter().flush();   */
        }


	public LoginUrlAuthenticationEntryPoint getLoginUrlAuthenticationEntryPoint() {
		return loginUrlAuthenticationEntryPoint;
	}


	public void setLoginUrlAuthenticationEntryPoint(
			LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint) {
		this.loginUrlAuthenticationEntryPoint = loginUrlAuthenticationEntryPoint;
	}

	
    
    
}
