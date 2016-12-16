package pers.liujunyi.bookkeeping.securityFilter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pers.liujunyi.bookkeeping.entity.TCoreUser;
import pers.liujunyi.bookkeeping.service.ICoreUserService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;

/***
 * 文件名称: MyAuthenticationFilter.java
 * 文件描述: 自定义用户登录验证
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class MyAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	private static final String USERNAME = "login_user";
	private static final String PASSWORD = "login_pwd";
	
	/**
	 * 登录成功后跳转的地址
	 */
	private String successUrl = "/index.html";
	/**
	 * 登录失败后跳转的地址
	 */
	private String errorUrl = "/login.html";
	
	@Autowired
	private ICoreUserService userService;
	
	/**
	 * 自定义表单参数的name属性，默认是 j_username 和 j_password
	 * 定义登录成功和失败的跳转地址
	 */
	public void init() {
    	System.err.println(" ---------------  MyAuthenticationFilter init--------------- ");
    	this.setUsernameParameter(USERNAME);
		this.setPasswordParameter(PASSWORD);
		// 验证成功，跳转的页面
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl(successUrl);
		this.setAuthenticationSuccessHandler(successHandler);

		// 验证失败，跳转的页面
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl(errorUrl);
		this.setAuthenticationFailureHandler(failureHandler);
	}

	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
        System.err.println(" ---------------  MyAuthenticationFilter attemptAuthentication 自定义登录验证--------------- ");
        
        
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}

		String username = obtainUsername(request).trim();
		String password = obtainPassword(request).trim();

		if (username.equals("") || password.equals("")) {
			BadCredentialsException exception = new BadCredentialsException(
					"用户名或密码不能为空！");// 在界面输出自定义的信息！！
			throw exception;
		}

		// 验证用户账号与密码是否正确
		TCoreUser users = userService.getSingleUserInfo(username, password);
		if (users == null ) {
			BadCredentialsException exception = new BadCredentialsException(
					"用户名或密码不匹配！");// 在界面输出自定义的信息！！
			// request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			// exception);
			throw exception;
		}
		// 当验证都通过后，把用户信息放在session里
		//[0]:用户ID  [1]：用户编号   [2]:是否超级管理员
		String[] userArray = new String[]{users.getId(),users.getUserCode(),users.getIsAdmin()};
		request.getSession().setAttribute(Constants.USER_SESSION, userArray);
		// 记录登录信息
		
		
		// 实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);
		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response,FilterChain chain,Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        System.out.println(" ************** 登录成功！ *******************");
		String json = "{\"message\":\"登录验证成功.\",\"success\":true}";
		ControllerUtil.writeJsonJavaScript(response, json);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException failed)
        throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        System.out.println(" ************** 登录失败！ *******************");
        String json = "{\"message\":\"帐号或者密码不正确.\",\"success\":false}";
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json");  
                
        response.getWriter().println(json);  
        response.getWriter().close();  
		//ControllerUtil.writeJsonJavaScript(response, json);
    }

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
     
	
	
	
}
