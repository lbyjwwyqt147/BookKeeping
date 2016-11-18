package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.annotations.Param;

import pers.liujunyi.bookkeeping.entity.TCoreModules;

/***
 * 文件名称: ICoreModulesMapper.java
 * 文件描述: 资源模块dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreModulesMapper {
   
	/**
	 * 新增资源模块信息
	 * @param modules
	 * @return
	 */
	public int addModules(TCoreModules modules);
	
	/**
	 * 编辑资源模块信息
	 * @param modules
	 * @return
	 */
	public int editModules(TCoreModules modules);
	
	/**
	 * 删除资源模块
	 * @param ids
	 * @return
	 */
	public int deletes(String[] ids);
	
	/**
	 * 更新模块状态
	 * @param isActivate  1001:激活  1002:锁定
	 * @param ids  主键id
	 * @param map
	 * @return
	 */
	public int updateStatus(ConcurrentMap<String,Object> map);
	
	/**
	 * 根据父级编号 获取下级信息
	 * @param modulePid    父级编号
	 * @param isActivate   1001:激活  1002:锁定 
	 * @param map
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreModules> findModulesList(ConcurrentMap<String,Object> map);
	
	/**
	 * 获取父级编号下的最大编号值
	 * @param modulePid  父级编号
	 * @return 返回最大编号值
	 */
	public String findMaxModulesCode(@Param("modulePid")String modulePid);
	
	/**
	 * 获取表中纪录条数
	 * @return
	 */
	public Long getModulesCount();
}
