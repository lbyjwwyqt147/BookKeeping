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
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.PageBean;
import pers.liujunyi.bookkeeping.entity.TCoreFunction;
import pers.liujunyi.bookkeeping.service.ICoreFunctionService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;
import pers.liujunyi.bookkeeping.util.IServiceUtil;

/***
 * 文件名称: FunctionController.java
 * 文件描述: 功能Controller
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月18日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Controller
@RequestMapping("/bookkeeping/function")
public class FunctionController {
    
	@Autowired
	private ICoreFunctionService functionService;
	@Autowired
	private IServiceUtil serviceUtil;
	
	/**
	 * 初始化列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="initList")
	public ModelAndView initList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView("settings/function/function_list");
		return view;
	}
	
	/**
	 * 保存信息
	 * @param function
	 * @param task     add:新增  edit：编辑
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="saveInfo",method=RequestMethod.POST)
	public void saveInfo(TCoreFunction function,String task,HttpServletRequest request,HttpServletResponse response){
		String[] userArray = serviceUtil.getUserSession(request);
		String resultJson = functionService.saveInfo(function, task, userArray[0], "");
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 查询列表数据
	 * @param pageNum  页码
	 * @param limit    每页显示数
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="list")
	public void findList(Integer pageNum,Integer limit,HttpServletRequest request,HttpServletResponse response){
		String resultJson = "{\"rows\":[],\"total\":0}";
		try {
			ConcurrentMap<String, Object> paramsMap = ControllerUtil.getFormData(request);
			//设置分页参数 
			Page<?> page = PageHelper.startPage(pageNum, limit, true);
			CopyOnWriteArrayList<TCoreFunction> funsList = functionService.findFunsList(paramsMap);
			//获取分页列表
			PageBean<TCoreFunction> pageList = new PageBean<TCoreFunction>(page.getResult(), pageNum);
			resultJson = "{\"rows\":"+new Gson().toJson(pageList.getList())+",\"total\":"+pageList.getTotal()+"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 更新状态
	 * @param id            主键
	 * @param isActivate    1001:激活  1002:锁定
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="updateStatus",method=RequestMethod.POST)
	public void updateStatus(String id,String isActivate,HttpServletRequest request,HttpServletResponse response){
		ConcurrentMap<String,Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.UPDATE_FAIL_MSG;
		try {
			ConcurrentMap<String,Object> params = new ConcurrentHashMap<String, Object>();
			String[] ids = id.split(",");
			params.put("ids", ids);
			params.put("isActivate", isActivate);
			AtomicInteger count = new AtomicInteger(functionService.updateStatus(params));
			if(count.get() > 0){
				success.set(true);
				message = Constants.UPDATE_SUCCESS_MSG;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		map.put("success", success);
		map.put("message", message);
		ControllerUtil.writeJavaScript(response, map);
	}
	
	/**
	 * 删除
	 * @param id   主键
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="deletes",method=RequestMethod.POST)
	public void deletes(String id,HttpServletRequest request,HttpServletResponse response){
		String[] ids = id.split(",");
		String resultJson = functionService.deletesAndRelevance(ids);
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
}
