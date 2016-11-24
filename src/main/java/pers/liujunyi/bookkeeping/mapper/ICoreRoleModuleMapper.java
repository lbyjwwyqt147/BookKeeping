package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pers.liujunyi.bookkeeping.entity.TCoreRoleModule;

/***
 * 文件名称: ICoreRoleModuleMapper.java
 * 文件描述: 角色模块dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月23日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreRoleModuleMapper {

	/**
	 * 新增数据
	 * @param list
	 * @return
	 */
	public int addRoleModule(CopyOnWriteArrayList<TCoreRoleModule> list);
	
	/**
	 * 根据主键ID删除
	 * @param ids
	 * @return
	 */
	public int deletes(String[] ids);
	
	/**
	 * 根据角色ID删除
	 * @param roleIds
	 * @return
	 */
	public int deletesRoleId(String[] roleIds);
	
	/**
	 * 根据角色代码删除
	 * @param roleCodes
	 * @return
	 */
	public int deletesRoleCode(String[] roleCodes);
	
	/**
	 * 根据模块编号删除
	 * @param moduleCode
	 * @return
	 */
	public int deletesModuleCode(String[] moduleCode);
	
	/**
	 * 查询列表数据
	 * @param roleId 角色ID
	 * @param map
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreRoleModule> findList(ConcurrentMap<String,Object> map);
}
