package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pers.liujunyi.bookkeeping.entity.TCoreFunction;

/***
 * 文件名称: ICoreFunctionService.java
 * 文件描述: 功能service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月18日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreFunctionService {
	/**
	 * 新增
	 * @param function
	 * @return
	 */
	public int addFunction(TCoreFunction function);
	
	/**
	 * 编辑
	 * @param function
	 * @return
	 */
	public int editFunction(TCoreFunction function);
	
	/**
	 * 保存信息
	 * @param function
	 * @param task
	 * @param userId
	 * @param strings
	 * @return
	 */
	public String saveInfo(TCoreFunction function,String task,String userId,String...strings);
	
	/**
	 * 更新状态
	 * @param  ids  主键id
	 * @param  isActivate  1001:激活  1002:锁定
	 * @param params
	 * @return
	 */
	public int updateStatus(ConcurrentMap<String,Object> params);
	
	/**
	 * 根据主键 删除信息
	 * @param ids  主键
	 * @return
	 */
	public int deletes(String[] ids);
	
	/**
	 * 删除同时删除关联
	 * @param ids  主键
	 * @return
	 */
	public String deletesAndRelevance(String[] ids);
	
	/**
	 * 查询数据列表
	 * @param params
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreFunction> findFunsList(ConcurrentMap<String,Object> params);

	/**
	 * 根据ID或者代码值查询
	 * @param id
	 * @param code
	 * @return
	 */
	public TCoreFunction findFunction(String id,String code);
}
