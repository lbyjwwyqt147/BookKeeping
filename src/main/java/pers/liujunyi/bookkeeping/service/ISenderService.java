package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;


/***
 * 文件名称: ISenderService.java
 * 文件描述: 发送邮件(短信)service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月10日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ISenderService {

	/**
	 * 发送邮件(短信)
	 * @param  isActivate  传 1001
	 * @param  bid         邮件内容类型：  1001：注册发送验证码     1002 找回密码
	 * @param  toEmails    收件人   多个用;隔开
	 * @param 
	 * @param 
	 * @return  返回发送是否成功
	 */
	public boolean sendMessage(ConcurrentMap<String, Object> params);
}
