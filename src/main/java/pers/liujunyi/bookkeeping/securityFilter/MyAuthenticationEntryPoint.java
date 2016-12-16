package pers.liujunyi.bookkeeping.securityFilter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

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

	private String loginFormUrl;
	
	public MyAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	/**
	 * 当访问的资源没有权限，会调用这里  
	 */
    @Override  
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)  
                throws IOException, ServletException {  
            //super.commence(request, response, authException);  
          
           //返回json形式的错误信息  
           response.setCharacterEncoding("UTF-8");  
           response.setContentType("application/json");  
                   
           response.getWriter().println("{\"ok\":0,\"msg\":\""+authException.getLocalizedMessage()+"\"}");  
           response.getWriter().flush();   
        }

	public String getLoginFormUrl() {
		return loginFormUrl;
	}

	public void setLoginFormUrl(String loginFormUrl) {
		this.loginFormUrl = loginFormUrl;
	}  
    
    
}
