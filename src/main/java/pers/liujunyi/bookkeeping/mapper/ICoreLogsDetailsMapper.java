package pers.liujunyi.bookkeeping.mapper;

import pers.liujunyi.bookkeeping.entity.TCoreLogsDetails;

/***
 * 文件名称: ICoreLogsDetailsMapper.java
 * 文件描述: 日志详细dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月17日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreLogsDetailsMapper {

	/**
	 * 新增日志详情信息
	 * @param logs
	 * @return
	 */
	public int addLogsDetails(TCoreLogsDetails logs);
	
	/**
	 * 修改日志详细信息
	 * @param logs
	 * @return
	 */
	public int updateLogsDetails(TCoreLogsDetails logs);
	
	/**
	 * 删除日志详情
	 * @param ids
	 * @return
	 */
	public int deleteLogsDetails(String[] ids);
}
