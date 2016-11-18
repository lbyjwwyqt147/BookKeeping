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
import pers.liujunyi.bookkeeping.entity.TCoreRole;
import pers.liujunyi.bookkeeping.service.ICoreRoleService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;
import pers.liujunyi.bookkeeping.util.IServiceUtil;

/***
 * 文件名称: RoleController.java
 * 文件描述: 角色Controller
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月08日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Controller
@RequestMapping("/bookkeeping/role")
public class RoleController {

	@Autowired
	private ICoreRoleService roleService;
	@Autowired
	private IServiceUtil serviceUtil;
	
	/**
	 * 初始化列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="initList")
	public ModelAndView initUpload(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("settings/role/role_list");
	}
	
	/**
	 * 初始化编辑页面
	 * @param id 主键id
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView initEdit(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		TCoreRole role = roleService.findRoleInfo(id, null);
		view.addObject("role", role);
		view.addObject("task", Constants.EDIT);
		return view;
	}
	
	/**
	 * 保存信息
	 * @param role
	 * @param task  add:新增  edit:编辑
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="saveInfo",method=RequestMethod.POST)
	public void saveInfo(TCoreRole role,String task,HttpServletRequest request,HttpServletResponse response){
		String[] userArray = serviceUtil.getUserSession(request);
		String resultJson = roleService.saveInfo(role, task, userArray[0], "");
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	
	/**
	 * 得到角色列表集合数据
	 * @param pageNum    页码
	 * @param limit      每页显示纪录条数      
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="roleList",method=RequestMethod.POST)
	public  void getRoleList(Integer pageNum,Integer limit,HttpServletRequest request,HttpServletResponse response){
		String resultJson = "{\"rows\":[],\"total\":0}";
		try {
			ConcurrentMap<String, Object> paramsMap = ControllerUtil.getFormData(request);
			//设置分页参数 
			Page<?> page = PageHelper.startPage(pageNum, limit, true);
			CopyOnWriteArrayList<TCoreRole> roleList = roleService.findRolesList(paramsMap);
			//获取分页列表
			PageBean<TCoreRole> pageList = new PageBean<TCoreRole>(page.getResult(), pageNum);
			resultJson = "{\"rows\":"+new Gson().toJson(pageList.getList())+",\"total\":"+pageList.getTotal()+"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 根据代码值删除信息
	 * @param code 代码值
	 * @param request
	 * @param response
	 */
	public void deletes(String code,HttpServletRequest request,HttpServletResponse response){
		String[] codes =  code.split(",");
		String resultJson =  roleService.deletesAndrelevance(codes);
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 更新状态
	 * @param id 主键id
	 * @param isActivate  1001:激活  1002:锁定
	 * @param request   
	 * @param response
	 */
	public void updateStatus(String id,HttpServletRequest request,HttpServletResponse response){
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.UPDATE_FAIL_MSG;
		try {
			ConcurrentMap<String, Object> params =  ControllerUtil.getFormData(request);
			params.put("ids", id.split(","));
			AtomicInteger count =  new AtomicInteger(roleService.updateStatus(params));
			if(count.get() > 0){
				success.set(true);
				message = Constants.UPDATE_SUCCESS_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("success", success.get());
		map.put("message", message);
		ControllerUtil.writeJavaScript(response, map);
	}
	
}
