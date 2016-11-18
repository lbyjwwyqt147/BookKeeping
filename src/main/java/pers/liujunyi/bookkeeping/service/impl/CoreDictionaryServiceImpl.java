package pers.liujunyi.bookkeeping.service.impl;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreDictionary;
import pers.liujunyi.bookkeeping.mapper.ICoreDictionaryMapper;
import pers.liujunyi.bookkeeping.service.ICoreDictionaryService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;
import pers.liujunyi.bookkeeping.util.IServiceUtil;

/***
 * 文件名称: CoreDictionaryServiceImpl.java
 * 文件描述: 业务字典service接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月11日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreDictionaryServiceImpl implements ICoreDictionaryService {

	private static final Logger LOGGER = Logger.getLogger(CoreDictionaryServiceImpl.class);
	@Autowired
	private ICoreDictionaryMapper dictMapper;
	@Autowired
	private IServiceUtil serviceUtil;
	
	@Override
	public String saveDictInfo(TCoreDictionary dict, String task,String userId) {
		 ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		 //是否成功
	     AtomicBoolean success = new AtomicBoolean(false);
	     String mssage = Constants.SAVE_FAIL_MSG;
	     int result = 0;
         try {
	    	if(task.equals(Constants.ADD)){
	    		dict.setCreateUser(userId);
	    		dict.setDictCode(this.settingNewDictCodeValue(dict.getParentCode()));
	    		dict.setCreateTime(DateTimeUtil.getCurrentDateTime());
	        	result = dictMapper.addDict(dict);
	        	//result = dictMapper.updateIsParent(dict.getParentCode(), "1");
	        }else if(task.equals(Constants.EDIT)) {
	        	dict.setUpdateTime(DateTimeUtil.getCurrentDateTime());
	        	dict.setUpdateUser(userId);
	        	result = dictMapper.editDict(dict);;
			}
	    	if(result > 0){
        		success.set(true);
        		mssage = Constants.SAVE_SUCCESS_MSG;
        	}
		 } catch (Exception e) {
			LOGGER.error("保存业务字典信息出现异常.");
			e.printStackTrace();
		 }
	     map.put("success", success.get());
         map.put("mssage", mssage);
	     return new Gson().toJson(map);
	}

	@Override
	public String zTreeJson(String parentCode,ConcurrentMap<String,Object> paramsMap,HttpServletRequest request,String str) {
		try {
			StringBuffer ztreBuffer = new StringBuffer("[");
			//获取所有下级信息
			CopyOnWriteArrayList<TCoreDictionary> dictList = dictMapper.findChlidsDictList(parentCode);
			if(dictList != null && !dictList.isEmpty()){
				int i = 0;
				Iterator<?> iterator = dictList.iterator();
				while(iterator.hasNext()){
					i++;
					TCoreDictionary dictionary = (TCoreDictionary) iterator.next();
					String treeId = dictionary.getDictCode();
					String treePid = dictionary.getParentCode();
					String treeText = dictionary.getDictName();
					String treeTitle = dictionary.getDictName();
					String open = paramsMap.get("open") != null && !paramsMap.get("open").toString().trim().equals("") ? paramsMap.get("open").toString().trim() :"false";
					String isParent = paramsMap.get("isParent") != null && !paramsMap.get("isParent").toString().trim().equals("") ? paramsMap.get("isParent").toString().trim() :"false";
					String nocheck = paramsMap.get("nocheck") != null && !paramsMap.get("nocheck").toString().trim().equals("") ? paramsMap.get("nocheck").toString().trim() :"false";
					String checked = paramsMap.get("checked") != null && !paramsMap.get("checked").toString().trim().equals("") ? paramsMap.get("checked").toString().trim() :"false";
					String iconSkin = paramsMap.get("iconSkin") != null && !paramsMap.get("iconSkin").toString().trim().equals("") ? paramsMap.get("iconSkin").toString().trim() :"";
					String icon = paramsMap.get("icon") != null && !paramsMap.get("icon").toString().trim().equals("") ? request.getContextPath()+paramsMap.get("icon").toString().trim() :request.getContextPath()+"/resources/pages/images/icon/award_star_gold_1.png";;
					String iconOpen = paramsMap.get("iconOpen") != null && !paramsMap.get("iconOpen").toString().trim().equals("") ? request.getContextPath()+paramsMap.get("iconOpen").toString().trim() :request.getContextPath()+"/resources/pages/images/icon/award_star_gold_2.png";;
					String iconClose = paramsMap.get("iconClose") != null && !paramsMap.get("iconClose").toString().trim().equals("") ? request.getContextPath()+paramsMap.get("iconClose").toString().trim() :request.getContextPath()+"/resources/pages/images/icon/award_star_gold_3.png";;
					String isHidden = paramsMap.get("isParent") != null && !paramsMap.get("isParent").toString().trim().equals("") ? paramsMap.get("isParent").toString().trim() :"false";
					
					if(dictionary.getIsParent().trim().equals("1")){
						isParent = "true";
						open = "true";
					}
					
					ztreBuffer.append(serviceUtil.getZTreeItemJSON(treeId, treePid, treeText,open, checked,treeTitle,"",isParent,"",icon,iconOpen,iconClose, isHidden, nocheck,iconSkin,request, str));
					
					ztreBuffer.append("}");
					if (i < dictList.size()) {
						ztreBuffer.append(",");
					}
				
				}
			}
			ztreBuffer.append("]");
			return ztreBuffer.toString();
		} catch (Exception e) {
			LOGGER.error("封装字典树形结构数据出现异常.");
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int addDict(TCoreDictionary dict) {
		return dictMapper.addDict(dict);
	}

	@Override
	public int editDict(TCoreDictionary dict) {
		return dictMapper.editDict(dict);
	}

	@Override
	public CopyOnWriteArrayList<TCoreDictionary> findChlidsDictList(
			String parentCode) {
		return dictMapper.findChlidsDictList(parentCode);
	}

	@Override
	public TCoreDictionary getDictInfo(String dictCode) {
		return dictMapper.getDictInfo(dictCode);
	}

	@Override
	public TCoreDictionary getDictInfoAndId(String id) {
		return dictMapper.getDictInfoAndId(id);
	}

	@Override
	public String getMaxDictCodeValue(String parentCode) {
		return dictMapper.getMaxDictCodeValue(parentCode);
	}

	@Override
	public String getDictWordValue(String parentCode, String dictWrod) {
		return dictMapper.getDictWordValue(parentCode, dictWrod);
	}

	@Override
	public String getDictEntityAndFieldName(String entityName, String fieldName) {
		return dictMapper.getDictEntityAndFieldName(entityName, fieldName);
	}

	@Override
	public String getDictNameValue(String entityName,String entityFieldName,String parentCode,String dictWord) {
		String pid = dictMapper.getDictEntityAndFieldName(entityName, entityFieldName);
		String dictNameString = dictMapper.getDictNameValue(entityName, entityFieldName,pid, dictWord);
		return dictNameString != null ? dictNameString : "";
	}

	@Override
	public int deleteDict(String[] ids) {
		return dictMapper.deleteDict(ids);
	}

	@Override
	public int updateDictState(String id, String stateValue) {
		return dictMapper.updateDictState(id, stateValue);
	}

	@Override
	public int updateIsParent(String parentCode, String stateValue) {
		return dictMapper.updateIsParent(parentCode, stateValue);
	}

	@Override
	public int updateActivateState(String id, String stateValue) {
		AtomicInteger result = new AtomicInteger(0);
		try {
			String[] ids = id.split(",");
			for(String idString : ids){
				result.set(dictMapper.updateDictState(idString, stateValue));
			}
		} catch (Exception e) {
			LOGGER.error("更新业务字典状态出现异常.");
			e.printStackTrace();
		}
		return result.get();
	}

	@Override
	public String settingNewDictCodeValue(String parentCode) {
		String newDictCode = null;
		try {
			String historyDictCode = dictMapper.getMaxDictCodeValue(parentCode);
			newDictCode = serviceUtil.getNewCode(historyDictCode, parentCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDictCode;
	}

	@Override
	public Long getInfoCount(ConcurrentMap<String, Object> paramsMap) {
		return dictMapper.getInfoCount(paramsMap);
	}

	@Override
	public String getDictName(String dictCode) {
		String dictNameString = dictMapper.getDictName(dictCode);
		return dictNameString != null ? dictNameString : "";
	}

	@Override
	public String dictChlidsJson(String entityName, String fieldName,String isEmpty) {
		String json = "[]";
		try {
		    String pid = dictMapper.getDictEntityAndFieldName(entityName, fieldName);
			CopyOnWriteArrayList<ConcurrentMap<String, Object>> results = new CopyOnWriteArrayList<ConcurrentMap<String, Object>>();
			if("true".equals(isEmpty)){
				ConcurrentMap<String, Object>  map = new ConcurrentHashMap<String, Object>();
				map.put("id", "");
				map.put("text", "- 请选择 -");
				results.add(map);
			}
			CopyOnWriteArrayList<TCoreDictionary> list = dictMapper.findChlidsDictList(pid);
			if (list != null && list.size() > 0) {
				for(TCoreDictionary dict : list){
				    // 循环赋值
					ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
					map.put("id", dict.getDictWord());
					map.put("text", dict.getDictName());
					results.add(map);
				}
			}
			json = new Gson().toJson(results);
		} catch (Exception e) {
			LOGGER.error("获取业务字典下拉树出现异常.");
			e.printStackTrace();
		}
		return json;
	}

}
