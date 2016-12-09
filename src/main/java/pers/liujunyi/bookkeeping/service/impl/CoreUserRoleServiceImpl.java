package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreUserRole;
import pers.liujunyi.bookkeeping.mapper.ICoreUserRoleMapper;
import pers.liujunyi.bookkeeping.service.ICoreUserRoleService;
import pers.liujunyi.bookkeeping.util.AttachmentFileUtil;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.KeyUtil;

/***
 * 文件名称: CoreUserRoleServiceImpl.java
 * 文件描述: 用户角色service接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月23日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreUserRoleServiceImpl implements ICoreUserRoleService {

	@Autowired
	private ICoreUserRoleMapper userRoleMapper;
	
	@Override
	public int addUserRole(CopyOnWriteArrayList<TCoreUserRole> list) {
		return userRoleMapper.addUserRole(list);
	}

	@Override
	public String saveInfo(String userId, String roleId, String roleCode) {
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = Constants.SAVE_FAIL_MSG;
		try {
			//先删除旧数据 然后保存新数据
			AtomicInteger count = new AtomicInteger(userRoleMapper.deleteRoleId(roleId.split(",")));
			if(roleCode != null && !roleCode.trim().equals("")){
				CopyOnWriteArrayList<TCoreUserRole> list =  new CopyOnWriteArrayList<TCoreUserRole>();
				String[] roleCodes = roleCode.split(",");
			    String[] roleIds = roleId.split(",");
				for(int i = 0; i < roleCodes.length; i++){
					TCoreUserRole  userRole = new TCoreUserRole();
					userRole.setId(KeyUtil.uuid());
					userRole.setIsActivate(Constants.DELETE_NONE_STATUS);
					userRole.setRoleCode(roleCodes[i]);
					userRole.setRoleId(roleIds[i]);
					userRole.setUserId(userId);
					list.add(userRole);
				}
				count.set(userRoleMapper.addUserRole(list));
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
		return userRoleMapper.deletes(ids);
	}

	@Override
	public int deleteUserId(String[] userIds) {
		return userRoleMapper.deleteUserId(userIds);
	}

	@Override
	public int deleteRoleId(String[] roleIds) {
		return userRoleMapper.deleteRoleId(roleIds);
	}

	@Override
	public int deleteRoleCode(String[] roleCodes) {
		return userRoleMapper.deleteRoleCode(roleCodes);
	}

	@Override
	public CopyOnWriteArrayList<TCoreUserRole> findList(
			ConcurrentMap<String, Object> map) {
		return userRoleMapper.findList(map);
	}

}
