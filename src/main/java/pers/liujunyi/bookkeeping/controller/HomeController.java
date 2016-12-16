package pers.liujunyi.bookkeeping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pers.liujunyi.bookkeeping.service.ICoreModulesService;
import pers.liujunyi.bookkeeping.util.IServiceUtil;

/***
 * 文件名称: HomeController.java
 * 文件描述: 首页Controller
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月08日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Controller
public class HomeController {
	
	@Autowired
	private ICoreModulesService modulesService;
	@Autowired
	private IServiceUtil serviceUtil;

	/**
	 * 初始化登陆页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="login.html")
	public ModelAndView initLogin(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	/**
	 * 初始化首页页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="index.html")
	public ModelAndView initUpload(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("home/home_none");
		//获取当前登录人信息
		String[] userArray =  serviceUtil.getUserSession(request);
		if(userArray != null && userArray.length > 0){
			String userId = !userArray[2].equals("1") ? userArray[0] : "1";
			String moduleString = modulesService.findModuleStructure(userId);
			mv.addObject("moduleList", moduleString);
		}
		
		return mv;
	}
	
	@RequestMapping(value="defeated.html")
	public ModelAndView initDefeated(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("settings/error/defeated");
	}
	
	@RequestMapping(value="initWelcome")
	public ModelAndView initWelcome(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("settings/error/welcome");
	}
	
}
