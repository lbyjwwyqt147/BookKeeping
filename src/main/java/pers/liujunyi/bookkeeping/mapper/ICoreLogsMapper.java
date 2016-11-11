package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.annotations.Param;

import pers.liujunyi.bookkeeping.entity.TCoreLogs;

/***
 * 文件名称: ICoreLogsMapper.java
 * 文件描述: 日志dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月17日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreLogsMapper {

	/**
	 * 新增日志信息
	 * @param logs
	 * @return
	 */
	public int addLogs(TCoreLogs logs);
	
	/**
	 * 修改日志信息
	 * @param logs
	 * @return
	 */
	public int updateLogs(TCoreLogs logs);
	
	/**
	 * 删除日志
	 * @param ids 主键ID
	 * @return
	 */
	public int deleteLogs(String[] ids);
	
	/**
	 * 查询日志列表
	 * @param userId     用户
	 * @param logType    日志类型
	 * @param logModule  模块
	 * @param logOperation
	 * @param params
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreLogs> findLogsList(ConcurrentMap<String, Object> params);
	
	
	/**
	 * 根据Id查询日志详情
	 * @param id  主键ID
	 * @return
	 */
	public TCoreLogs  getTCoreLogsInfo(@Param("id") String id);
	
}
