package pers.liujunyi.bookkeeping.controller;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import pers.liujunyi.bookkeeping.entity.TCoreDictionary;
import pers.liujunyi.bookkeeping.service.ICoreDictionaryService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;

/***
 * 文件名称: CoreDictionaryController.java
 * 文件描述: 业务字典Controller
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月11日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Controller
@RequestMapping("/tally/dict")
public class DictionaryController {
	
	@Autowired
	private ICoreDictionaryService dictService;
	
	/**
	 * 初始化列表页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="initList")
	public ModelAndView initList(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		Long count =  dictService.getInfoCount(null);
		if(count != null && count == 0){
			//初始化默认数据
			TCoreDictionary dictionary = new TCoreDictionary();
			dictionary.setDictCode(Constants.PAERNT);
			dictionary.setDictLevel(0);
			dictionary.setDictName("业务字典");
			dictionary.setDictPrompt("业务字典");
			dictionary.setDictWord("9999");
			dictionary.setEntityFieldName("");
			dictionary.setEntityName("");
			dictionary.setIsParent("1");
			dictionary.setParentCode("0");
			dictionary.setDictDescription("系统自动创建顶级节点");
			dictionary.setCreateTime(DateTimeUtil.getCurrentDateTime());
			dictionary.setCreateUser("系统自动创建");
			dictService.addDict(dictionary);
		}
		ModelAndView mv =  new ModelAndView("settings/dict/dict_list");
	    return mv;
	}
	
	/**
	 * 保存数据
	 * @param dict
	 * @param task add：新增  edit：修改编辑
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="saveInfo")
	public void saveDictInfo(TCoreDictionary dict, String task,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			ConcurrentMap<String, Object> resultMap =  dictService.saveDictInfo(dict, task.trim());
			ControllerUtil.writeJavaScript(response, resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 检测字典代码是否重复
	 * @param parentCode  父级编号
	 * @param dictWord    字典代码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="checkDictWord")
	@ResponseBody
	public boolean checkDictWord(String parentCode,String dictWord,String oldDictWord,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		AtomicBoolean success =  new AtomicBoolean(false);
		try {
			String temp = null;
			if(oldDictWord != null && !oldDictWord.equals(dictWord)){
				temp = dictService.getDictWordValue(parentCode, dictWord);
			}
			if(temp != null && !temp.trim().equals("")){
				success.set(false);
			}else{
				success.set(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success.get();
	}
	
	/**
	 * 检测字典关联的实体和字段是否存在
	 * @param entityName    实体名称
	 * @param fieldName     字段名称
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="checkDictEntityAndFieldName")
	@ResponseBody
	public boolean checkDictEntityAndFieldName(String entityName,String fieldName,String oldEntityName,String oldFieldName,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		AtomicBoolean success =  new AtomicBoolean(false);
		try {
			String temp = null;
			if(!entityName.equals(oldEntityName) && !fieldName.equals(oldFieldName)){
				temp = dictService.getDictEntityAndFieldName(entityName, fieldName);
			}
			if(temp != null && !temp.trim().equals("")){
				success.set(false);
			}else{
				success.set(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success.get();
	}
	
	/**
	 * 删除数据
	 * @param id 主键ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="deleteDicts")
	public void deleteDicts(String id,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		ConcurrentMap<String, Object> resultMap =  new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = "删除数据失败.";
		try {
			String[] ids = id.split(",");
			int result = dictService.deleteDict(ids);
			if(result > 0){
				success.set(true);
				message = "删除数据成功.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("success", success.get());
		resultMap.put("message", message);
		ControllerUtil.writeJavaScript(response, resultMap);
	}
	
	/**
	 * 更新业务字典状态
	 * @param id 主键ID
	 * @param stateValue 状态值
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="updateDictState")
	public void updateDictState(String id,String stateValue,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		ConcurrentMap<String, Object> resultMap =  new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = stateValue.trim().equals("1002")?"锁定数据失败.":"激活数据失败.";
		try {
			int result = dictService.updateActivateState(id,stateValue.trim());
			if(result > 0){
				success.set(true);
				message = stateValue.trim().equals("1002")?"锁定数据成功.":"激活数据成功.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("success", success.get());
		resultMap.put("message", message);
		ControllerUtil.writeJavaScript(response, resultMap);
	}
	
	/**
	 * 根据字典编号获取字典详细信息
	 * @param dictCode 字典编号
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="getDictInfo")
	public void getDictInfo(String dictCode,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		ConcurrentMap<String, Object> resultMap =  new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = "获取数据失败.";
		try {
			TCoreDictionary dictionary = dictService.getDictInfo(dictCode);
			if(dictionary != null){
				message = "获取数据成功.";
				resultMap.put("data", new Gson().toJson(dictionary));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("success", success.get());
		resultMap.put("message", message);
		ControllerUtil.writeJavaScript(response, resultMap);
	}
	
	/**
	 * 根据主键ID获取字典详细信息
	 * @param id  主键ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="getDictInfoAndId")
	public void getDictInfoAndId(String id,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		ConcurrentMap<String, Object> resultMap =  new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = "获取数据失败.";
		try {
			TCoreDictionary dictionary = dictService.getDictInfoAndId(id);
			if(dictionary != null){
				message = "获取数据成功.";
				resultMap.put("data", new Gson().toJson(dictionary));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("success", success.get());
		resultMap.put("message", message);
		ControllerUtil.writeJavaScript(response, resultMap);
	}
	
	/**
	 * 字典集合列表
	 * @param pid     父级编号
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="dictList")
    public void dictList(String pid,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		String resultString = "{\"rows\":[],\"total\":1}";
		CopyOnWriteArrayList<ConcurrentMap<String, Object>> resultList =  new CopyOnWriteArrayList<ConcurrentMap<String, Object>>();
		try {
			ConcurrentMap<String, Object> params = ControllerUtil.getFormData(request);
			//每页显示纪录条数
			Integer limit = Integer.valueOf(params.get("pageSize") != null ? params.get("pageSize").toString().trim() : "10");
			//页码
			Integer offset = Integer.valueOf(params.get("pageNumber") != null ? params.get("pageNumber").toString().trim() : "1");
			String[] strings = null;
			CopyOnWriteArrayList<TCoreDictionary> list = dictService.findChlidsDictList(pid,offset,limit);
    		if(list != null && !list.isEmpty()){
    			Iterator<?> iterator = list.iterator();
    			while(iterator.hasNext()){
    				TCoreDictionary dictionary = (TCoreDictionary) iterator.next();
    				ConcurrentMap<String, Object> map = ControllerUtil.objectToMap(dictionary, strings);
    				//父级名称
    				String parentText =  dictService.getDictName(dictionary.getDictCode()); 
    				map.put("parentText", parentText);
    				//字典状态转换
    				map.put("stateText", dictionary.getIsActivate().trim().equals("1001")?"激活" :"锁定");
    				resultList.add(map);
    			}
    		}
			//计算总纪录数
    		params.put("parentCode", pid);
    		Long totalLong =  dictService.getInfoCount(params);
    		resultString = "{\"total\":"+totalLong+",\"rows\":"+new Gson().toJson(resultList)+"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		ControllerUtil.writeJsonJavaScript(response, resultString);
    }
	
	/**
	 * 字典树
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="dictTree")
	public void dictTree(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			//获取参数
			ConcurrentMap<String, Object> params =  ControllerUtil.getFormData(request);
			//节点编号
			String nid = params.get("nid").toString();
			String ztreeJson = dictService.zTreeJson(nid, params, request, null);
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
				     String child =  dictService.zTreeJson(pid, params, request, null);
				     obj.add("children", parser.parse(child));
			     }
			     result.add(obj);
			}
		}
		return result;
	}
	
	/**
	 * 字典下拉框
	 * @param entityName  实体名称
	 * @param fieldName   字段名称
	 * @param request  
	 * @param response
	 */
	public void dictComboxList(String entityName,String fieldName,String isEmpty,HttpServletRequest request,HttpServletResponse response){
		try {
			ControllerUtil.writeJsonJavaScript(response, dictService.dictChlidsJson(entityName, fieldName, isEmpty));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
