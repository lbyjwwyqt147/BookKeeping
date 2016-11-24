package pers.liujunyi.bookkeeping.controller;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.PageBean;
import pers.liujunyi.bookkeeping.entity.TCoreUser;
import pers.liujunyi.bookkeeping.service.ICoreUserService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;
import pers.liujunyi.bookkeeping.util.IServiceUtil;

/***
 * 文件名称: UserController.java
 * 文件描述: 用户Controller
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
	@Autowired
	private IServiceUtil serviceUtil;
	
	/**
	 * 保存用户信息
	 * @param user 用户对象
	 * @param task 标志  add:新增  edit:编辑
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public void saveUser(TCoreUser user,String task,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		String[] userArray =  serviceUtil.getUserSession(request);
		if(userArray != null && userArray.length > 0){
			user.setUpdateUser(userArray[0]);
		}
		String resultJson = userService.saveUser(user, task, "");
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 用户登录
	 * @param login_user  登录帐号
	 * @param login_pwd   登录密码
	 * @param securityCode   验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	public void userLogin(String login_user,String login_pwd,String securityCode,HttpServletRequest request,HttpServletResponse response){
		//跨域访问
		response.setHeader("Access-Control-Allow-Origin","*");
		String resultJson = userService.findUserLogin(login_user, login_pwd, securityCode,request); 
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 登出系统
	 * @param userCode   用户编号
	 * @param request
	 * @param response
	 */
	public void logOut(String userCode,HttpServletRequest request,HttpServletResponse response){
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = "退出系统失败.";
		try {
			 request.getSession().removeAttribute(Constants.USER_SESSION);
			 success.set(true);
			 message = "退出系统成功.";
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("success", success.get());
		map.put("message", message);
		ControllerUtil.writeJavaScript(response, map);
	}
	
	/**
	 * 获取用户列表
	 * @param    userCode  用户编号
	 * @param    loginUser 帐号
	 * @param    userNickname  昵称
	 * @param    idNumber     证件号码
	 * @param    userPhone    手机号码
	 * @param    userEmail    邮箱
	 * @param    isActivate   是否激活
	 * @param    userType     类型
	 * @param    pageNum      页码
	 * @param    limit        每页显示数量
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="findUserList",method=RequestMethod.POST)
	public void findUserList(Integer pageNum,Integer limit,HttpServletRequest request,HttpServletResponse response){
		String resultJson = "{\"rows\":[],\"total\":0}";
		try {
			ConcurrentMap<String, Object> paramsMap = ControllerUtil.getFormData(request);
			//设置分页参数 
			Page<?> page = PageHelper.startPage(pageNum, limit, true);
			CopyOnWriteArrayList<TCoreUser> userList = userService.findArrayList(paramsMap);
			//获取分页列表
			PageBean<TCoreUser> pageList = new PageBean<TCoreUser>(page.getResult(), pageNum);
			resultJson = "{\"rows\":"+new Gson().toJson(pageList.getList())+",\"total\":"+pageList.getTotal()+"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 根据ID获取用户信息
	 * @param id   id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="loginUserInfo",method=RequestMethod.POST)
	public void  loginUserInfo(String id,HttpServletRequest request,HttpServletResponse response){
		if(id == null || id.trim().equals("")){
			String[] userArray =  serviceUtil.getUserSession(request);
			id  = userArray[0];
		}
		TCoreUser user = userService.getSingleUserInfo(id);
		ControllerUtil.writeJavaScript(response, user);
	}
	
	/**
	 * 检查帐号是否重复(web后台端)
	 * @param userName
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="checkAccounts",method=RequestMethod.POST)
	@ResponseBody
	public boolean checkAccounts(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		//验证通过
		AtomicBoolean isExist = new AtomicBoolean(true);
		ConcurrentMap<String, Object> params = ControllerUtil.getFormData(request);
       String userString = userService.getSingleUserId(params);
        if(userString != null){
        	//验证不通过
        	isExist.set(false);
        }
        return isExist.get();
	}
	
	/**
	 * 更新状态
	 * @param id   ID
	 * @param isActivate   1001:激活   1002：锁定
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="updateStatus",method=RequestMethod.POST)
	public void  updateStatus(String id,HttpServletRequest request,HttpServletResponse response){
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.UPDATE_FAIL_MSG;
		try {
			ConcurrentMap<String, Object> params = ControllerUtil.getFormData(request);
			params.put("ids", id.split(","));
			AtomicInteger count =  new AtomicInteger(userService.updateStatus(params));
			if(count.get() > 0){
				message = Constants.UPDATE_SUCCESS_MSG;
				success.set(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("success", success.get());
		map.put("message", message);
		ControllerUtil.writeJavaScript(response, map);
	}
	
	/**
	 * 根据ID真删除同时删除关联
	 * @param id   ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="deletes",method=RequestMethod.POST)
	public void  deletes(String id,HttpServletRequest request,HttpServletResponse response){
	    String resultJson = userService.deletesAndRelevance(id.split(","));
	    ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	
	/**
	 * 根据ID 逻辑删除
	 * @param id   ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="deletesFlag",method=RequestMethod.POST)
	public void  deletesFlag(String id,HttpServletRequest request,HttpServletResponse response){
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.DELETE_FAIL_MSG;
		try {
			AtomicInteger count =  new AtomicInteger(userService.deletesFlag(id.split(",")));
			if(count.get() > 0){
				message = Constants.DELETE_SUCCESS_MSG;
				success.set(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("success", success.get());
		map.put("message", message);
		ControllerUtil.writeJavaScript(response, map);
	}
	
}
