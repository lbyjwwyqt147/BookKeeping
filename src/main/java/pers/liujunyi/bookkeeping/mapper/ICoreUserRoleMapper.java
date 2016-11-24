package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pers.liujunyi.bookkeeping.entity.TCoreUserRole;

/***
 * 文件名称: ICoreUserMapper.java
 * 文件描述: 用户角色dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月19日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreUserRoleMapper {
    
	/**
	 * 新增
	 * @param list
	 * @return
	 */
	public int addUserRole(CopyOnWriteArrayList<TCoreUserRole> list);
	
	/**
	 * 根据主键ID删除
	 * @param ids
	 * @return
	 */
	public int deletes(String[] ids);
	
	/**
	 * 根据用户ID删除
	 * @param userIds
	 * @return
	 */
	public int deleteUserId(String[] userIds);
	
	/**
	 * 根据角色ID 删除
	 * @param roleIds
	 * @return
	 */
	public int deleteRoleId(String[] roleIds);
	
	/**
	 * 根据角色代码值删除
	 * @param roleCodes
	 * @return
	 */
	public int deleteRoleCode(String[] roleCodes);
	
	/**
	 * 查询列表
	 * @param   userId  人员ID
	 * @param   roleId  角色ID
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreUserRole> findList(ConcurrentMap<String,Object> map);
	
}
