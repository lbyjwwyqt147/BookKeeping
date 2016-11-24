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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.PageBean;
import pers.liujunyi.bookkeeping.entity.TCoreRoleFunction;
import pers.liujunyi.bookkeeping.service.ICoreRoleFunctionService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;

/***
 * 文件名称: RoleFunctionController.java
 * 文件描述: 角色权限(功能)Controller
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月23日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Controller
@RequestMapping("/bookkeeping/roleFunction")
public class RoleFunctionController {

	@Autowired
	private ICoreRoleFunctionService  roleFunctionService;
	
	/**
	 * 保存信息
	 * @param roleId        角色ID
	 * @param roleCode      角色代码
	 * @param functionId    功能ID 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="saveInfo",method=RequestMethod.POST)
	public void saveInfo(String roleId, String roleCode, String functionId,HttpServletRequest request,HttpServletResponse response){
		String resultJson = roleFunctionService.saveInfo(roleId, roleCode, functionId);
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 删除数据
	 * @param id  主键id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="deletes",method=RequestMethod.POST)
	public void deletes(String id,HttpServletRequest request,HttpServletResponse response){
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.DELETE_FAIL_MSG;
		try {
			String[] ids = id.split(",");
			AtomicInteger count =  new AtomicInteger(roleFunctionService.deletesIds(ids));
		    if(count.get() > 0){
		    	success.set(true);
		    	message = Constants.DELETE_SUCCESS_MSG;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("success", success.get());
		map.put("message", message);
		ControllerUtil.writeJavaScript(response, map);
	}
	
	/**
	 * 列表数据
	 * @param roleId 角色ID
	 * @param pageNum  页码
	 * @param limit    每页显示条数
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="findList",method=RequestMethod.POST)
	public void findList(Integer pageNum,Integer limit,HttpServletRequest request,HttpServletResponse response){
		String resultJson = "{\"rows\":[],\"total\":0}";
		try {
			ConcurrentMap<String, Object> paramsMap = ControllerUtil.getFormData(request);
			//设置分页参数 
			Page<?> page = PageHelper.startPage(pageNum, limit, true);
			CopyOnWriteArrayList<TCoreRoleFunction> roleFunsList = roleFunctionService.findList(paramsMap);
			//获取分页列表
			PageBean<TCoreRoleFunction> pageList = new PageBean<TCoreRoleFunction>(page.getResult(), pageNum);
			resultJson = "{\"rows\":"+new Gson().toJson(pageList.getList())+",\"total\":"+pageList.getTotal()+"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
}
