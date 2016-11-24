package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreRoleModule;
import pers.liujunyi.bookkeeping.mapper.ICoreRoleModuleMapper;
import pers.liujunyi.bookkeeping.service.ICoreRoleModuleService;
import pers.liujunyi.bookkeeping.util.Constants;

/***
 * 文件名称: CoreRoleModuleServiceImpl.java
 * 文件描述: 角色模块service接口 实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月23日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreRoleModuleServiceImpl implements ICoreRoleModuleService {

	@Autowired
	private  ICoreRoleModuleMapper roleModuleMapper;
	
	@Override
	public int addRoleModule(CopyOnWriteArrayList<TCoreRoleModule> list) {
		return roleModuleMapper.addRoleModule(list);
	}

	@Override
	public String saveInfo(String roleId, String roleCode, String moduleId,
			String moduleCode) {
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.SAVE_FAIL_MSG;
		try {
			//先删除旧数据 然后保存新数据
			AtomicInteger count = new AtomicInteger(roleModuleMapper.deletesRoleId(roleId.split(",")));
			if(moduleCode != null && !moduleCode.trim().equals("")){
				CopyOnWriteArrayList<TCoreRoleModule> list =  new CopyOnWriteArrayList<TCoreRoleModule>();
				String[] moduleCodes = moduleCode.split(",");
				for(int i = 0; i < moduleCodes.length; i++){
					TCoreRoleModule  roleModule = new TCoreRoleModule();
					roleModule.setIsActivate(Constants.DELETE_NONE_STATUS);
					roleModule.setModuleCode(moduleCodes[i]);
					roleModule.setModuleId(moduleId);
					roleModule.setRoleCode(roleCode);
					roleModule.setRoleId(roleId);
					list.add(roleModule);
				}
				count.set(roleModuleMapper.addRoleModule(list));
			}
			if(count.get() > 0){
				success.set(true);
				message = Constants.SAVE_SUCCESS_MSG;
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
		return roleModuleMapper.deletes(ids);
	}

	@Override
	public int deletesRoleId(String[] roleIds) {
		return roleModuleMapper.deletesRoleId(roleIds);
	}

	@Override
	public int deletesRoleCode(String[] roleCodes) {
		return roleModuleMapper.deletesRoleCode(roleCodes);
	}

	@Override
	public int deletesModuleCode(String[] moduleCode) {
		return roleModuleMapper.deletesModuleCode(moduleCode);
	}

	@Override
	public CopyOnWriteArrayList<TCoreRoleModule> findList(
			ConcurrentMap<String, Object> map) {
		return roleModuleMapper.findList(map);
	}

}
