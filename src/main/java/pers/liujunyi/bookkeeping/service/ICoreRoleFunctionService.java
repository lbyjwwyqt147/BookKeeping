package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pers.liujunyi.bookkeeping.entity.TCoreRoleFunction;

/***
 * 文件名称: ICoreRoleFunctionService.java
 * 文件描述: 角色权限(功能)service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月21日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreRoleFunctionService {

	/**
	 * 新增信息
	 * @param list
	 * @return
	 */
	public int addRoleFunction(CopyOnWriteArrayList<TCoreRoleFunction> list);
	
	/**
	 * 保存信息
	 * @param roleId      角色ID
	 * @param roleCode    角色代码
	 * @param functionId  功能ID
	 * @return
	 */
	public String saveInfo(String roleId,String roleCode,String functionId);
	
	/**
	 * 根据id删除
	 * @param ids
	 * @return
	 */
	public int deletesIds(String[] ids);
	
	/**
	 * 根据角色Id删除
	 * @param roleIds
	 * @return
	 */
	public int deletesRoleId(String[] roleIds);
	
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreRoleFunction> findList(ConcurrentMap<String,Object> map);
}
