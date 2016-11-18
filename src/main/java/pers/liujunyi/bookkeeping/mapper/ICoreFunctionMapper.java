package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pers.liujunyi.bookkeeping.entity.TCoreFunction;

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
	 * 根据代码值 删除信息
	 * @param codes  代码值
	 * @return
	 */
	public int deletes(String[] codes);
	
	/**
	 * 查询数据列表
	 * @param params
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreFunction> findFunsList(ConcurrentMap<String,Object> params);
}
