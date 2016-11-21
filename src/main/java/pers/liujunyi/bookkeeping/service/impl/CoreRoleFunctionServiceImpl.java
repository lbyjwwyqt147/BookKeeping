package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreRoleFunction;
import pers.liujunyi.bookkeeping.mapper.ICoreRoleFunctionMapper;
import pers.liujunyi.bookkeeping.service.ICoreRoleFunctionService;
import pers.liujunyi.bookkeeping.util.Constants;

/***
 * 文件名称: CoreRoleFunctionServiceImpl.java
 * 文件描述: 角色权限(功能)service接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月21日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class CoreRoleFunctionServiceImpl implements ICoreRoleFunctionService {

	@Autowired
	private ICoreRoleFunctionMapper roleFunctionMapper;
	
	@Override
	public int addRoleFunction(CopyOnWriteArrayList<TCoreRoleFunction> list) {
		return roleFunctionMapper.addRoleFunction(list);
	}

	@Override
	public String saveInfo(String roleId, String roleCode, String functionId) {
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(true);
		String message = Constants.SAVE_FAIL_MSG;
		try {
			//先删除原来存在的旧数据  然后在重新添加新数据
			AtomicInteger count = new AtomicInteger(roleFunctionMapper.deletesRoleId(roleId.split(",")));
			CopyOnWriteArrayList<TCoreRoleFunction> list = new CopyOnWriteArrayList<TCoreRoleFunction>();
			String[] functionIds = functionId.split(",");
			for(int i = 0; i < functionIds.length; i++){
				TCoreRoleFunction roleFunction = new TCoreRoleFunction();
				roleFunction.setFunctionId(functionIds[i]);
				roleFunction.setRoleCode(roleCode);
				roleFunction.setRoleId(roleId);
				roleFunction.setIsActivate(Constants.DELETE_NONE_STATUS);
				list.add(roleFunction);
			}
			count.set(roleFunctionMapper.addRoleFunction(list));
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
	public int deletesIds(String[] ids) {
		return roleFunctionMapper.deletesIds(ids);
	}

	@Override
	public int deletesRoleId(String[] roleIds) {
		return roleFunctionMapper.deletesRoleId(roleIds);
	}

	@Override
	public CopyOnWriteArrayList<TCoreRoleFunction> findList(
			ConcurrentMap<String, Object> map) {
		return roleFunctionMapper.findList(map);
	}

}
