package pers.liujunyi.bookkeeping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.liujunyi.bookkeeping.entity.TCoreLogsDetails;
import pers.liujunyi.bookkeeping.mapper.ICoreLogsDetailsMapper;
import pers.liujunyi.bookkeeping.service.ICoreLogsDetailsService;

/***
 * 文件名称: CoreLogsDetailsServiceImpl.java
 * 文件描述: 日志详细service接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月17日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreLogsDetailsServiceImpl implements ICoreLogsDetailsService {

	@Autowired
	private ICoreLogsDetailsMapper logsDetailsMapper;
	
	@Override
	public int addLogsDetails(TCoreLogsDetails logs) {
		return logsDetailsMapper.addLogsDetails(logs);
	}

	@Override
	public String saveLogsDetails(TCoreLogsDetails logs, String... strs) {
		int count = logsDetailsMapper.addLogsDetails(logs);
		String result = null;
		if(count > 0){
			result = "保存日志详细成功.";
		}
		return result;
	}

	@Override
	public int deleteLogsDetails(String[] ids) {
		return logsDetailsMapper.deleteLogsDetails(ids);
	}

}
