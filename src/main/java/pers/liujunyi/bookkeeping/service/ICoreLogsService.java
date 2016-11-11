package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pers.liujunyi.bookkeeping.entity.TCoreLogs;

/***
 * 文件名称: ICoreLogsService.java
 * 文件描述: 日志service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月17日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreLogsService {

	/**
	 * 新增日志信息
	 * @param logs
	 * @return
	 */
	public int addLogs(TCoreLogs logs);
	
	/**
	 * 保存信息
	 * @param logs
	 * @param strs
	 * @return
	 */
	public String saveLogs(TCoreLogs logs,String... strs);
	
	/**
	 * 删除日志
	 * @param ids
	 * @return
	 */
	public int deleteLogs(String[] ids);
	
	/**
	 * 查询日志
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
	 * @param id
	 * @return
	 */
	public TCoreLogs  getTCoreLogsInfo(String id);
	
}
