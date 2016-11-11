package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentMap;

import pers.liujunyi.bookkeeping.service.ISenderService;

/***
 * 文件名称: SmsSenderServiceImpl.java
 * 文件描述: 通过短信方式发送消息
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月10日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class SmsSenderServiceImpl implements ISenderService {

	@Override
	public boolean sendMessage(ConcurrentMap<String, Object> params) {
        return false;
	}

}
