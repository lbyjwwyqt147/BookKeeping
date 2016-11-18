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

import pers.liujunyi.bookkeeping.entity.TCoreModules;
import pers.liujunyi.bookkeeping.mapper.ICoreModulesMapper;
import pers.liujunyi.bookkeeping.service.ICoreModulesService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;
import pers.liujunyi.bookkeeping.util.IServiceUtil;

/***
 * 文件名称: CoreModulesServiceImpl.java
 * 文件描述: 资源模块service接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreModulesServiceImpl implements ICoreModulesService {

	private static final Logger LOOGER = Logger.getLogger(CoreModulesServiceImpl.class);
	
	@Autowired
	private ICoreModulesMapper modulesMapper;
	@Autowired
	private IServiceUtil serviceUtil;
	
	@Override
	public int addModules(TCoreModules modules) {
		return modulesMapper.addModules(modules);
	}

	@Override
	public int editModules(TCoreModules modules) {
		return modulesMapper.editModules(modules);
	}

	@Override
	public String saveModulesInfo(TCoreModules modules, String task,String userId,
			String... strings) {
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.SAVE_FAIL_MSG;
		try {
			AtomicInteger count = new AtomicInteger(0);
			if(task.trim().equals(Constants.ADD)){
				modules.setCreateDate(DateTimeUtil.getCurrentDateTime());
				modules.setCreateUser(userId);
				modules.setDeleteFlag(Constants.DELETE_NONE_STATUS);
				count.set(modulesMapper.addModules(modules));
			}else if(task.trim().equals(Constants.EDIT)){
				modules.setUpdateDate(DateTimeUtil.getCurrentDateTime());
				modules.setUpdateUser(userId);
				count.set(modulesMapper.editModules(modules));
			}
			if(count.get() > 0){
				success.set(true);
				message = Constants.SAVE_SUCCESS_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOOGER.equals("保存资源模块信息出现异常.");
		}
		resultMap.put("success", success.get());
		resultMap.put("message", message);
		return new Gson().toJson(resultMap);
	}

	@Override
	public int deletes(String[] ids) {
		return modulesMapper.deletes(ids);
	}

	@Override
	public String deletesAndRelevance(String[] ids) {
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.DELETE_FAIL_MSG;
		try {
			AtomicInteger count = new AtomicInteger(modulesMapper.deletes(ids));
			if(count.get() > 0){
				success.set(true);
				message = Constants.DELETE_SUCCESS_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOOGER.equals("删除资源模块信息出现异常.");
		}
		resultMap.put("success", success.get());
		resultMap.put("message", message);
		return new Gson().toJson(resultMap);
	}

	@Override
	public int updateStatus(ConcurrentMap<String, Object> map) {
		return modulesMapper.updateStatus(map);
	}

	@Override
	public CopyOnWriteArrayList<TCoreModules> findModulesList(
			ConcurrentMap<String, Object> map) {
		return modulesMapper.findModulesList(map);
	}

	@Override
	public String findMaxModulesCode(String modulePid) {
		return modulesMapper.findMaxModulesCode(modulePid);
	}

	@Override
	public String zTreeJson(String modulePid,
			ConcurrentMap<String, Object> paramsMap,
			HttpServletRequest request, String str) {
		ConcurrentMap<String, Object> params = new ConcurrentHashMap<String, Object>();
		params.put("modulePid", modulePid);
		try {
			StringBuffer ztreBuffer = new StringBuffer("[");
			//获取所有下级信息
			CopyOnWriteArrayList<TCoreModules> list = modulesMapper.findModulesList(params);
			if(list != null && !list.isEmpty()){
				int i = 0;
				Iterator<?> iterator = list.iterator();
				while(iterator.hasNext()){
					i++;
					TCoreModules modules = (TCoreModules) iterator.next();
					String treeId = modules.getModuleCode();
					String treePid = modules.getModulePid();
					String treeText = modules.getModuleName();
					String treeTitle = modules.getModuleName();
					String open = paramsMap.get("open") != null && !paramsMap.get("open").toString().trim().equals("") ? paramsMap.get("open").toString().trim() :"false";
					String isParent = paramsMap.get("isParent") != null && !paramsMap.get("isParent").toString().trim().equals("") ? paramsMap.get("isParent").toString().trim() :"false";
					String nocheck = paramsMap.get("nocheck") != null && !paramsMap.get("nocheck").toString().trim().equals("") ? paramsMap.get("nocheck").toString().trim() :"false";
					String checked = paramsMap.get("checked") != null && !paramsMap.get("checked").toString().trim().equals("") ? paramsMap.get("checked").toString().trim() :"false";
					String iconSkin = paramsMap.get("iconSkin") != null && !paramsMap.get("iconSkin").toString().trim().equals("") ? paramsMap.get("iconSkin").toString().trim() :"";
					String icon = paramsMap.get("icon") != null && !paramsMap.get("icon").toString().trim().equals("") ? request.getContextPath()+paramsMap.get("icon").toString().trim() :request.getContextPath()+"/resources/pages/images/icon/award_star_gold_1.png";;
					String iconOpen = paramsMap.get("iconOpen") != null && !paramsMap.get("iconOpen").toString().trim().equals("") ? request.getContextPath()+paramsMap.get("iconOpen").toString().trim() :request.getContextPath()+"/resources/pages/images/icon/award_star_gold_2.png";;
					String iconClose = paramsMap.get("iconClose") != null && !paramsMap.get("iconClose").toString().trim().equals("") ? request.getContextPath()+paramsMap.get("iconClose").toString().trim() :request.getContextPath()+"/resources/pages/images/icon/award_star_gold_3.png";;
					String isHidden = paramsMap.get("isParent") != null && !paramsMap.get("isParent").toString().trim().equals("") ? paramsMap.get("isParent").toString().trim() :"false";
					
					if(modules.getModulePid().trim().equals("1")){
						isParent = "true";
						open = "true";
					}
					ztreBuffer.append(serviceUtil.getZTreeItemJSON(treeId, treePid, treeText,open, checked,treeTitle,"",isParent,"",icon,iconOpen,iconClose, isHidden, nocheck,iconSkin,request, str));
					ztreBuffer.append("}");
					if (i < list.size()) {
						ztreBuffer.append(",");
					}
				}
			}
			ztreBuffer.append("]");
			return ztreBuffer.toString();
		} catch (Exception e) {
			LOOGER.error("生成资源模块树形结构数据出现异常.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getNewCodeValue(String modulePid) {
		String newCode = null;
		try {
			String historyDictCode = modulesMapper.findMaxModulesCode(modulePid);
			newCode = serviceUtil.getNewCode(historyDictCode, modulePid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newCode;
	}

	@Override
	public Long getModulesCount() {
		return modulesMapper.getModulesCount();
	}

}
