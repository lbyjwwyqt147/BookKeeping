package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.annotations.Param;

import pers.liujunyi.bookkeeping.entity.TCoreFunction;

/***
 * 文件名称: ICoreFunctionMapper.java
 * 文件描述: 功能dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月18日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreFunctionMapper {

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
	 * 查询数据列表
	 * @param params
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreFunction> findFunsList(ConcurrentMap<String,Object> params);
	
	/**
	 * 根据ID或者代码值查询
	 * @param id   主键
	 * @param code 代码值
	 * @return
	 */
	public TCoreFunction findFunction(@Param("id")String id,@Param("code")String code);
}
