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

import pers.liujunyi.bookkeeping.entity.PageBean;
import pers.liujunyi.bookkeeping.entity.TCoreUserRole;
import pers.liujunyi.bookkeeping.service.ICoreUserRoleService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

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
@RequestMapping("/bookkeeping/userRole")
public class UserRoleController {

	@Autowired
	private ICoreUserRoleService   userRoleService;
	
	/**
	 * 保存数据
	 * @param roleId  角色ID
	 * @param roleCode 角色代码
	 * @param userId 用户ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="saveInfo",method=RequestMethod.POST)
	public void saveInfo(String roleId, String roleCode, String userId,
			String moduleCode,HttpServletRequest request,HttpServletResponse response){
		String resultJson = userRoleService.saveInfo(userId,roleId, roleCode);
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 删除数据
	 * @param id  主键
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
			AtomicInteger count =  new AtomicInteger(userRoleService.deletes(ids));
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
			CopyOnWriteArrayList<TCoreUserRole> roleFunsList = userRoleService.findList(paramsMap);
			//获取分页列表
			PageBean<TCoreUserRole> pageList = new PageBean<TCoreUserRole>(page.getResult(), pageNum);
			resultJson = "{\"rows\":"+new Gson().toJson(pageList.getList())+",\"total\":"+pageList.getTotal()+"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
}
