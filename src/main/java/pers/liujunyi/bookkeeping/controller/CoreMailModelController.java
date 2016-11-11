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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreMailModel;
import pers.liujunyi.bookkeeping.service.ICoreMailModelService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;
import pers.liujunyi.bookkeeping.util.IServiceUtil;

/***
 * 文件名称: CoreMailModelController.java
 * 文件描述: 邮件地址Controller
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月31日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Controller
@RequestMapping("/bookkeeping/mailModel")
public class CoreMailModelController {

	@Autowired
	private ICoreMailModelService  mailModelService;
	
	@Autowired
	private IServiceUtil serviceUtil;
	
	/**
	 * 保存信息
	 * @param mailModel
	 * @param task   add：新增     edit:编辑修改
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="saveInfo",method=RequestMethod.POST)
	public void saveInfo(TCoreMailModel mailModel,String task,HttpServletRequest request ,HttpServletResponse response){
		  response.setHeader("Access-Control-Allow-Origin","*"); 
		  ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	      AtomicBoolean success = new AtomicBoolean(false);
	      String message  = "保存信息失败.";
	      try {
	    	   AtomicInteger count =  new AtomicInteger(0);
	    	   //获取当前登录信息
	    	   String[] userArray =  serviceUtil.getMemberSession(request);
			   if(task.trim().equals(Constants.ADD)){
				  //新增
				  mailModel.setCreateDate(DateTimeUtil.getCurrentDateTime());
				  mailModel.setCreateUser(userArray[0]);
				  count.set(mailModelService.addMail(mailModel));
			   }else{
				  //修改编辑
				  mailModel.setUpdateDate(DateTimeUtil.getCurrentDateTime());
				  mailModel.setUpdateUser(userArray[0]);
				  count.set(mailModelService.editMail(mailModel));
			   }
			   if(count.get() > 0){
				   success.set(true);
				   message = "保存信息成功.";
			   }
		  } catch (Exception e) {
				e.printStackTrace();
		  }
	      resultMap.put("success", success);
	      resultMap.put("message", message);
	      ControllerUtil.writeJavaScript(response, resultMap);
	}
	
	/**
	 * 获取邮件地址列表
	 * @param isActivate     1001：激活     1002 失效
	 * @param pageNum        当前页码
	 * @param limit          每页显示纪录数
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="findMailModelList",method=RequestMethod.POST)
	public void findMailModelList(HttpServletRequest request ,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		String resultJson = "{\"pageNum\":1,\"pageSize\":0,\"size\":0,\"total\":0}";
		try {
			//获取参数
			ConcurrentMap<String, Object> params = ControllerUtil.getFormData(request);
			AtomicInteger pageNum  = new AtomicInteger(params.get("pageNum") != null && !params.get("pageNum").toString().trim().equals("") ? Integer.valueOf(params.get("pageNum").toString().trim()):1);
			AtomicInteger limit  = new AtomicInteger(params.get("limit") != null && !params.get("limit").toString().trim().equals("") ? Integer.valueOf(params.get("limit").toString().trim()):10);
			/** startPage(当前页码,每页显示的纪录条数) */
			PageHelper.startPage(pageNum.get(),limit.get());
			CopyOnWriteArrayList<TCoreMailModel> list = mailModelService.findMailModelsList(params);
			PageInfo<TCoreMailModel> page =  new PageInfo<TCoreMailModel>(list, pageNum.get());
			resultJson = new Gson().toJson(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 删除
	 * @param id 主键id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public void delete(String id,HttpServletRequest request ,HttpServletResponse response){
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = "删除信息失败.";
		try {
			AtomicInteger count = new AtomicInteger(mailModelService.deleteMail(id));
			if(count.get() > 0){
				success.set(true);
				message = "删除信息成功.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("success", success);
		resultMap.put("message", message);
		ControllerUtil.writeJavaScript(response, resultMap);
	}
	
	/**
	 * 更新 IsActivate
	 * @param id      主键id
	 * @param status  1001：激活     1002 失效
	 * @param request
	 * @param response
	 */
	public void updateIsActivate(String id,String status,HttpServletRequest request ,HttpServletResponse response){
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = "更新信息失败.";
		try {
			AtomicInteger count = new AtomicInteger(mailModelService.updateIsActivate(id, status));
			if(count.get() > 0){
				success.set(true);
				message = "更新信息成功.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("success", success);
		resultMap.put("message", message);
		ControllerUtil.writeJavaScript(response, resultMap);
	}
}
