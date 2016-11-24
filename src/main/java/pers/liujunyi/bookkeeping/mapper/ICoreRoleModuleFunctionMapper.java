package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pers.liujunyi.bookkeeping.entity.TCoreRoleModuleFunction;

/***
 * 文件名称: ICoreRoleModuleFunctionMapper.java
 * 文件描述: 角色资源权限dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月18日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreRoleModuleFunctionMapper {

	/**
	 * 新增数据
	 * @param rmf
	 * @return
	 */
	public int addInfo(CopyOnWriteArrayList<TCoreRoleModuleFunction> rmf);
	
	/**
	 * 根据id删除
	 * @param ids
	 * @return
	 */
	public int deletes(String[] ids);
	
	/**
	 * 根据角色ID删除 
	 * @param roleIds
	 * @return
	 */
	public int deleteRoleId(String[] roleIds);
	
	/**
	 * 根据角色代码删除
	 * @param roleCodes
	 * @return
	 */
	public int deleteRoleCode(String[] roleCodes);
	
	/**
	 * 根据模块编号删除
	 * @param moduleId
	 * @return
	 */
	public int deleteModuleId(String moduleId);
	
	/**
	 * 根据功能ID删除
	 * @param functionId
	 * @return
	 */
	public int deleteFunctionId(String[] functionId);
	
	/**
	 * 查询列表数据
	 * @param roleId 
	 * @param moduleId
	 * @param map
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreRoleModuleFunction> findList(ConcurrentMap<String, Object> map);
	
}
