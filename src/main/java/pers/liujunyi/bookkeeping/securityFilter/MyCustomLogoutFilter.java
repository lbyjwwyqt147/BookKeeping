package pers.liujunyi.bookkeeping.securityFilter;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/***
 * 文件名称: MyCustomLogoutFilter.java
 * 文件描述: 自定义用户退出登录
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class MyCustomLogoutFilter extends LogoutFilter {
    
	/**
	 * 创建一个新实例
	 * @param logoutSuccessHandler
	 * @param handlers
	 */
	public MyCustomLogoutFilter(LogoutSuccessHandler logoutSuccessHandler,
			LogoutHandler[] handlers) {
		super(logoutSuccessHandler, handlers);
		System.out.println(" ************************ MyCustomLogoutFilter MyCustomLogoutFilter ******************************************** ");

	}

}
