package pers.liujunyi.bookkeeping.controller;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import pers.liujunyi.bookkeeping.entity.PageBean;
import pers.liujunyi.bookkeeping.entity.TCoreModules;
import pers.liujunyi.bookkeeping.service.ICoreModulesService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;

/***
 * 文件名称: CoreModulesController.java
 * 文件描述: 资源模块Controller
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月17日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Controller
@RequestMapping("/bookkeeping/modules")
public class ModulesController {

	@Autowired
	private ICoreModulesService  modulesService;
	 
	/**
	 * 初始化列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="initList")
	public ModelAndView initList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView("settings/modules/modules_list");
		AtomicLong count = new AtomicLong(modulesService.getModulesCount());
		if(count.get() == 0){
			TCoreModules modules = new TCoreModules();
			modules.setNodeLevel(1);
			modules.setModuleCode(Constants.PAERNT);
			modules.setModuleName("系统资源模块");
			modules.setModulePid("0");
			modules.setModuleIconOne("");
			modules.setIsActivate("1001");
			modules.setModuleUrl("url");
			modules.setModuleDescription("系统自动创建");
			modules.setCreateDate(DateTimeUtil.getCurrentDateTime());
			modules.setCreateUser("系统自动创建");
			modulesService.addModules(modules);
		}
		return model;
	}
	
	/**
	 * 初始化新增页面
	 * @param pid      编号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="initAdd")
	public ModelAndView initAdd(String pid,HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView("settings/modules/modules_add");
		model.addObject("pid", pid);
		return model;
	}
	
	/**
	 * 获取资源模块列表信息
	 * @param modulePid  父级编号
	 * @param pageNum    页码
	 * @param limit      每页显示条数
	 * @param request
	 * @param response
	 */
	@SuppressWarnings({"unused" })
	@RequestMapping(value="getModulesList")
	public void getModulesList(Integer pageNum,Integer limit,HttpServletRequest request,HttpServletResponse response){
		String resultJson = "{\"rows\":[],\"total\":0}";
		try {
			ConcurrentMap<String, Object> paramsMap = ControllerUtil.getFormData(request);
			//设置分页参数 
			Page<?> page = PageHelper.startPage(pageNum, limit, true);
			CopyOnWriteArrayList<TCoreModules> modulesList = modulesService.findModulesList(paramsMap);
			//获取分页列表
			PageBean<TCoreModules> pageList = new PageBean<TCoreModules>(page.getResult(), pageNum);
			resultJson = "{\"rows\":"+new Gson().toJson(pageList.getList())+",\"total\":"+pageList.getTotal()+"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 保存信息
	 * @param modules
	 * @param task     add:新增   edit:编辑
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="saveInfo",method=RequestMethod.POST)
	public void saveInfo(TCoreModules modules,String task,HttpServletRequest request,HttpServletResponse response){
	     String resultJson = modulesService.saveModulesInfo(modules, task, "");
	     ControllerUtil.writeJsonJavaScript(response, resultJson);
	}
	
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="deletes",method=RequestMethod.POST)
	public void deletes(String id,HttpServletRequest request,HttpServletResponse response){
		String[] ids = id.split(",");
		String resultJosn = modulesService.deletesAndRelevance(ids);
		ControllerUtil.writeJsonJavaScript(response, resultJosn);
	}
	
	/**
	 * 更新状态
	 * @param id
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
			AtomicInteger count = new AtomicInteger(modulesService.updateStatus(params));
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
	 * 资源模块树形结构数据
	 * @param nid      节点
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="modulesTree",method=RequestMethod.POST)
	public void modulesTree(HttpServletRequest request,HttpServletResponse response){
		try {
			ConcurrentMap<String, Object> params = ControllerUtil.getFormData(request);
			String nid = params.get("nid").toString();
			String ztreeJson = modulesService.zTreeJson(nid, params, request, "");
			if(nid.trim().equals("0")){
				JsonArray allTree = this.zTreeList(ztreeJson,request,"");
				ControllerUtil.writeJsonJavaScript(response, allTree.toString());
			}else{
				ControllerUtil.writeJsonJavaScript(response, ztreeJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ztree文件树处理
	 * 
	 * @param treeJson
	 * @return
	 */
	private JsonArray zTreeList(String treeJson,HttpServletRequest request, String... strs) {
		ConcurrentMap<String, Object> params =  new ConcurrentHashMap<String, Object>();
		JsonParser parser = new JsonParser();
		//通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
		JsonElement el = parser.parse(treeJson);
		JsonArray result = new JsonArray();
		if(el.isJsonArray()){
			JsonArray jsonArray =  el.getAsJsonArray();
			Iterator<?> it = jsonArray.iterator();
			while(it.hasNext()){
				 JsonObject obj = (JsonObject) it.next();
				//根据key取值
			     String pid = obj.get("id").getAsString();
			     if(pid.trim().equals(Constants.PAERNT)){
			    	 //获取子节点
				     String child =  modulesService.zTreeJson(pid, params, request, null);
				     obj.add("children", parser.parse(child));
			     }
			     result.add(obj);
			}
		}
		return result;
	}
	
}
