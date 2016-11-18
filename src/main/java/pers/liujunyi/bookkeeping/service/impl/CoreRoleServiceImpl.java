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

import pers.liujunyi.bookkeeping.entity.TCoreRole;
import pers.liujunyi.bookkeeping.mapper.ICoreRoleMapper;
import pers.liujunyi.bookkeeping.service.ICoreRoleService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;
import pers.liujunyi.bookkeeping.util.IServiceUtil;

/***
 * 文件名称: CoreRoleServiceImpl.java
 * 文件描述: 角色service接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreRoleServiceImpl implements ICoreRoleService {

	private static final Logger LOGGER = Logger.getLogger(CoreRoleServiceImpl.class);
	@Autowired
	private ICoreRoleMapper roleMapper;
	@Autowired
	private IServiceUtil serviceUtil;
	
	@Override
	public int addRole(TCoreRole role) {
		return roleMapper.addRole(role);
	}

	@Override
	public int editRole(TCoreRole role) {
		return roleMapper.editRole(role);
	}

	@Override
	public String saveInfo(TCoreRole role, String task,String userId,String... strings) {
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.SAVE_FAIL_MSG;
		try {
			AtomicInteger count = new AtomicInteger(0);
			if(task != null && task.trim().equals(Constants.ADD)){
				role.setCreateDate(DateTimeUtil.getCurrentDateTime());
				role.setCreateUser(userId);
				role.setDeleteFlag(Constants.DELETE_NONE_STATUS);
				count.set(roleMapper.addRole(role));
			}else if(task != null && task.trim().equals(Constants.EDIT)){
				role.setUpdateDate(DateTimeUtil.getCurrentDateTime());
				role.setUpdateUser(userId);
				count.set(roleMapper.editRole(role));
			}
			if(count.get() > 0){
				success.set(true);
				message = Constants.SAVE_SUCCESS_MSG;
			}
		} catch (Exception e) {
			LOGGER.error("保存角色信息出现异常.");
			e.printStackTrace();
		}
		map.put("success", success.get());
		map.put("message", message);
		return new Gson().toJson(map);
	}

	@Override
	public int updateStatus(ConcurrentMap<String, Object> map) {
		return roleMapper.updateStatus(map);
	}

	@Override
	public int deletes(String[] ids) {
		return roleMapper.deletes(ids);
	}

	@Override
	public String deletesAndrelevance(String[] codes) {
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.DELETE_FAIL_MSG;
		try {
			AtomicInteger count = new AtomicInteger(roleMapper.deletesCodes(codes));
			if(count.get() > 0){
				success.set(true);
				message = Constants.DELETE_SUCCESS_MSG;
			}
		} catch (Exception e) {
			LOGGER.error("删除角色及其关联数据出现异常.");
			e.printStackTrace();
		}
		map.put("success", success.get());
		map.put("message", message);
		return new Gson().toJson(map);
	}

	@Override
	public CopyOnWriteArrayList<TCoreRole> findRolesList(
			ConcurrentMap<String, Object> map) {
		return roleMapper.findRolesList(map);
	}

	@Override
	public TCoreRole findRoleInfo(String id, String roleCode) {
		return roleMapper.findRoleInfo(id, roleCode);
	}

}
