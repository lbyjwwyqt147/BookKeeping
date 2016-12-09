package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import pers.liujunyi.bookkeeping.entity.TCoreRole;

/***
 * 文件名称: ICoreRoleService.java
 * 文件描述: 角色service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Component
public interface ICoreRoleService {

	/**
	 * 新增
	 * @param role
	 * @return
	 */
	public int addRole(TCoreRole role);
	
	/**
	 * 编辑
	 * @param role
	 * @return
	 */
	public int editRole(TCoreRole role);
	
	/**
	 * 保存信息
	 * @param role
	 * @param task  add:新增   edit：编辑
	 * @param userId 当前登录人id
	 * @param strings
	 * @return
	 */
	public String saveInfo(TCoreRole role,String task,String userId,String...strings);
	
	/**
	 * 更新状态
	 * @param isActivate  1001:激活  1002:锁定
	 * @param ids  主键id
	 * @param map
	 * @return
	 */
	public int updateStatus(ConcurrentMap<String,Object> map);
	
	/**
	 * 删除
	 * @param ids 主键id
	 * @return
	 */
	public int deletes(String[] ids);
	
	/**
	 * 删除角色同时删除关联
	 * @param codes  代码值
	 * @return
	 */
	public String deletesAndrelevance(String[] codes);
	
	/**
	 * 查询角色列表
	 * @param map
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreRole> findRolesList(ConcurrentMap<String,Object> map);

	/**
	 * 根据id或者代码值查询角色信息
	 * @param id
	 * @param roleCode
	 * @return
	 */
	public TCoreRole findRoleInfo(String id,String roleCode);
	
}
