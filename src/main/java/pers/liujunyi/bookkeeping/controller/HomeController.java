package pers.liujunyi.bookkeeping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/bookkeeping/home")
public class HomeController {

	/**
	 * 初始化首页页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="initHome")
	public ModelAndView initUpload(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("home/home_none");
	}
	
}
