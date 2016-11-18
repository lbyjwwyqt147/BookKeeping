package pers.liujunyi.bookkeeping.controller;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.liujunyi.bookkeeping.entity.TCoreUser;
import pers.liujunyi.bookkeeping.service.ICoreUserService;
import pers.liujunyi.bookkeeping.util.ControllerUtil;

/***
 * 文件名称: UserController.java
 * 文件描述: 用户控制器Controller
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月19日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Controller
@RequestMapping("/bookkeeping/user")
public class UserController {

	@Autowired
	private ICoreUserService userService; 
	
	/**
	 * 保存用户信息
	 * @param user 用户对象
	 * @param task 标志  add:新增  edit:编辑
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/saveUser")
	public void saveUser(TCoreUser user,String task,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			ConcurrentMap<String, Object> resultMap =  userService.saveUserInfo(user, task.trim());
			ControllerUtil.writeJavaScript(response, resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/userLogin")
	public void userLogin(HttpServletRequest request,HttpServletResponse response){
		//跨域访问
		response.setHeader("Access-Control-Allow-Origin","*");
		String loginJson = "{\"success\":false,\"mssage\":\"登录出现异常.\"}";
		try {
			//获取参数信息
			ConcurrentMap<String,Object> paramMap = ControllerUtil.getFormData(request);
			//登录帐号
			AtomicReference<String> loginUser = new AtomicReference<String>(paramMap.get("login_user").toString());
			//登录密码
			AtomicReference<String> loginPwd = new AtomicReference<String>(paramMap.get("login_pwd").toString());
			//验证码
			//AtomicReference<String> securityCode = new AtomicReference<String>(paramMap.get("securityCode").toString());
			//登录验证
			loginJson = userService.findUserLogin(loginUser, loginPwd, null); 
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		ControllerUtil.writeJsonJavaScript(response, loginJson);
	}
}
