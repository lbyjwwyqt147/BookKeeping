package pers.liujunyi.bookkeeping.controller;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.util.ControllerUtil;

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
@RequestMapping("/tally/role")
public class RoleController {

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
	 * 得到角色列表集合数据
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="roleList")
	public  void getRoleList(HttpServletRequest request,HttpServletResponse response){
		
		CopyOnWriteArrayList<ConcurrentMap<String, Object>> resultList =  new CopyOnWriteArrayList<ConcurrentMap<String, Object>>();
		ConcurrentMap<String, Object> map1 =  new ConcurrentHashMap<String, Object>();
		map1.put("draw", 1);
		map1.put("recordsTotal", 10);
		map1.put("recordsFiltered", 10);
		for (int i = 0; i < 10; i++) {
			ConcurrentMap<String, Object> map =  new ConcurrentHashMap<String, Object>();
			map.put("id", i);
			map.put("name", "$0"+i);
			map.put("column1", "c10"+i);
			resultList.add(map);
			String[] arrayStrings =  new String[3];
			arrayStrings[0] = String.valueOf(i);
			arrayStrings[1] = "$0"+i;
			arrayStrings[2] = "c100"+i;
			//resultList.add(arrayStrings);
		}
		map1.put("data", new Gson().toJson(resultList));
		System.out.println(new Gson().toJson(map1));
		String resultString = "{\"draw\":1,\"recordsTotal\":10,\"recordsFiltered\":10,\"data\":"+new Gson().toJson(resultList)+"}";
		ControllerUtil.writeJsonJavaScript(response, resultString);
		
	}
}
