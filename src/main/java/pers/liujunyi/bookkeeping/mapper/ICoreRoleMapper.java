package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.annotations.Param;

import pers.liujunyi.bookkeeping.entity.TCoreRole;


/***
 * 文件名称: ICoreRoleMapper.java
 * 文件描述: 角色dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月18日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreRoleMapper {
	
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
	 * 更新状态
	 * @param isActivate  1001:激活  1002:锁定
	 * @param ids  主键id
	 * @param map
	 * @return
	 */
	public int updateStatus(ConcurrentMap<String,Object> map);
	
	/**
	 * 根据主键删除信息
	 * @param ids 主键id
	 * @return
	 */
	public int deletes(String[] ids);
	
	/**
	 * 根据代码值删除信息
	 * @param codes 代码值
	 * @return
	 */
	public int deletesCodes(String[] codes);
	
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
	public TCoreRole findRoleInfo(@Param("id")String id,@Param("roleCode")String roleCode);
}
