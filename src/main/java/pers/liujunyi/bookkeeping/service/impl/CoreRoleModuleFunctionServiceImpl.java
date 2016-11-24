package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreRoleModuleFunction;
import pers.liujunyi.bookkeeping.mapper.ICoreRoleModuleFunctionMapper;
import pers.liujunyi.bookkeeping.service.ICoreRoleModuleFunctionService;
import pers.liujunyi.bookkeeping.util.Constants;

/***
 * 文件名称: CoreRoleModuleFunctionServiceImpl.java
 * 文件描述: 角色资源权限service接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月23日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreRoleModuleFunctionServiceImpl implements
		ICoreRoleModuleFunctionService {

	@Autowired
	private ICoreRoleModuleFunctionMapper roleModuleFunctionMapper;
	
	@Override
	public int addInfo(CopyOnWriteArrayList<TCoreRoleModuleFunction> rmf) {
		return roleModuleFunctionMapper.addInfo(rmf);
	}

	@Override
	public String saveInfo(String roleId, String roleCode, String moduleId,
			String functionId) {
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(true);
		String message = Constants.SAVE_FAIL_MSG;
		try {
			//先删除原来存在的旧数据  然后在重新添加新数据
			AtomicInteger count = new AtomicInteger(roleModuleFunctionMapper.deleteRoleId(roleId.split(",")));
			CopyOnWriteArrayList<TCoreRoleModuleFunction> list = new CopyOnWriteArrayList<TCoreRoleModuleFunction>();
			String[] functionIds = functionId.split(",");
			for(int i = 0; i < functionIds.length; i++){
				TCoreRoleModuleFunction roleFunction = new TCoreRoleModuleFunction();
				roleFunction.setFunctionId(functionIds[i]);
				roleFunction.setRoleCode(roleCode);
				roleFunction.setRoleId(roleId);
				roleFunction.setIsActivate(Constants.DELETE_NONE_STATUS);
				list.add(roleFunction);
			}
			count.set(roleModuleFunctionMapper.addInfo(list));
			if(count.get() > 0){
				message = Constants.SAVE_SUCCESS_MSG;
				success.set(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("success", success.get());
		map.put("message", message);
		return new Gson().toJson(map);
	}

	@Override
	public int deletes(String[] ids) {
		return roleModuleFunctionMapper.deletes(ids);
	}

	@Override
	public int deleteRoleId(String[] roleIds) {
		return roleModuleFunctionMapper.deleteRoleId(roleIds);
	}

	@Override
	public int deleteRoleCode(String[] roleCodes) {
		return roleModuleFunctionMapper.deleteRoleCode(roleCodes);
	}

	@Override
	public int deleteModuleId(String moduleId) {
		return roleModuleFunctionMapper.deleteModuleId(moduleId);
	}

	@Override
	public int deleteFunctionId(String[] functionId) {
		return roleModuleFunctionMapper.deleteFunctionId(functionId);
	}

	@Override
	public CopyOnWriteArrayList<TCoreRoleModuleFunction> findList(
			ConcurrentMap<String, Object> map) {
		return roleModuleFunctionMapper.findList(map);
	}

}
