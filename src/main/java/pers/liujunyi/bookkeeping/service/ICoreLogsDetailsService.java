package pers.liujunyi.bookkeeping.service;

import pers.liujunyi.bookkeeping.entity.TCoreLogsDetails;

/***
 * 文件名称: ICoreLogsDetailsService.java
 * 文件描述: 日志详细service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月17日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreLogsDetailsService {

	/**
	 * 新增日志详情信息
	 * @param logs
	 * @return
	 */
	public int addLogsDetails(TCoreLogsDetails logs);
	
	/**
	 * 保存信息
	 * @param logs
	 * @param strs
	 * @return
	 */
	public String saveLogsDetails(TCoreLogsDetails logs,String... strs);
	
	/**
	 * 删除日志详情
	 * @param ids
	 * @return
	 */
	public int deleteLogsDetails(String[] ids);
}
