package pers.liujunyi.bookkeeping.securityFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/***
 * 文件名称: CustomLogoutHandler.java
 * 文件描述: 自定义用户退出登录事件
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class CustomLogoutHandler implements LogoutHandler {

	public CustomLogoutHandler() {

    }
	
	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		System.out.println(" *********************** CustomLogoutHandler  logout (退出登录)  ********************************* ");
        
	}

}
