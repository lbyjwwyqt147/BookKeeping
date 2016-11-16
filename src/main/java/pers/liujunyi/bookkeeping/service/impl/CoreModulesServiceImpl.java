package pers.liujunyi.bookkeeping.service.impl;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreModules;
import pers.liujunyi.bookkeeping.mapper.ICoreModulesMapper;
import pers.liujunyi.bookkeeping.service.ICoreModulesService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;

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
	
	@Override
	public int addModules(TCoreModules modules) {
		return modulesMapper.addModules(modules);
	}

	@Override
	public int editModules(TCoreModules modules) {
		return modulesMapper.editModules(modules);
	}

	@Override
	public String saveModulesInfo(TCoreModules modules, String task,
			String... strings) {
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = "保存数据成功.";
		try {
			AtomicInteger count = new AtomicInteger(0);
			if(task.trim().equals(Constants.ADD)){
				modules.setCreateDate(DateTimeUtil.getCurrentDateTime());
				modules.setDeleteFlag("1001");
				count.set(modulesMapper.addModules(modules));
			}else if(task.trim().equals(Constants.EDIT)){
				modules.setUpdateDate(DateTimeUtil.getCurrentDateTime());
				count.set(modulesMapper.editModules(modules));
			}
			if(count.get() > 0){
				success.set(true);
				message = "保存数据成功.";
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String deletesAndRelevance(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateStatus(ConcurrentMap<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CopyOnWriteArrayList<TCoreModules> findModulesList(
			ConcurrentMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
