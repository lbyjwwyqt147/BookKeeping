package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreFunction;
import pers.liujunyi.bookkeeping.mapper.ICoreFunctionMapper;
import pers.liujunyi.bookkeeping.service.ICoreFunctionService;
import pers.liujunyi.bookkeeping.service.ICoreRoleFunctionService;
import pers.liujunyi.bookkeeping.service.ICoreRoleModuleFunctionService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;

/***
 * 文件名称: CoreFunctionServiceImpl.java
 * 文件描述: 功能service接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月18日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreFunctionServiceImpl implements ICoreFunctionService {

	private static final Logger LOGGER = Logger.getLogger(CoreFunctionServiceImpl.class);
	@Autowired
	private ICoreFunctionMapper functionMapper;
	@Autowired
	private ICoreRoleFunctionService  roleFunctionService;
	@Autowired
	private ICoreRoleModuleFunctionService roleModuleFunctionService;
	
	@Override
	public int addFunction(TCoreFunction function) {
		return functionMapper.addFunction(function);
	}

	@Override
	public int editFunction(TCoreFunction function) {
		return functionMapper.editFunction(function);
	}

	@Override
	public String saveInfo(TCoreFunction function, String task, String userId,
			String... strings) {
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.SAVE_FAIL_MSG;
		try {
			AtomicInteger count = new AtomicInteger();
			if(task != null && task.trim().equals(Constants.ADD)){
				function.setCreateDate(DateTimeUtil.getCurrentDateTime());
				function.setCreateUser(userId);
				function.setDeleteFlag(Constants.DELETE_NONE_STATUS);
				count.set(functionMapper.addFunction(function));
			}else if(task != null && task.trim().equals(Constants.EDIT)){
				function.setUpdateDate(DateTimeUtil.getCurrentDateTime());
				function.setUpdateUser(userId);
				count.set(functionMapper.editFunction(function));
			}
			if(count.get() > 0){
				success.set(true);
				message = Constants.SAVE_SUCCESS_MSG;
			}
		} catch (Exception e) {
			LOGGER.error("保存功能出现异常.");
			e.printStackTrace();
		}
		map.put("success", success.get());
		map.put("message", message);
		return new Gson().toJson(map);
	}

	@Override
	public int updateStatus(ConcurrentMap<String, Object> params) {
		return functionMapper.updateStatus(params);
	}

	@Override
	public int deletes(String[] codes) {
		return functionMapper.deletes(codes);
	}

	@Override
	public String deletesAndRelevance(String[] ids) {
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.DELETE_FAIL_MSG;
		try {
			//删除自身数据
			AtomicInteger count = new AtomicInteger(functionMapper.deletes(ids));
			//删除关联数据(关联表：t_core_role_function)
			roleFunctionService.deletesFunctionId(ids);
			//删除关联数据(关联表：t_core_role_module_function)
			roleModuleFunctionService.deleteFunctionId(ids);
			if(count.get() > 0){
				success.set(true);
				message = Constants.DELETE_SUCCESS_MSG;
			}
		} catch (Exception e) {
			LOGGER.error("删除数据出现异常.");
			e.printStackTrace();
		}
		map.put("success", success.get());
		map.put("message", message);
		return new Gson().toJson(map);
	}

	@Override
	public CopyOnWriteArrayList<TCoreFunction> findFunsList(
			ConcurrentMap<String, Object> params) {
		return functionMapper.findFunsList(params);
	}

	@Override
	public TCoreFunction findFunction(String id, String code) {
		return functionMapper.findFunction(id, code);
	}

}
