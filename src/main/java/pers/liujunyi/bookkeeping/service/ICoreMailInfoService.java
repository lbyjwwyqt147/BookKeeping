package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pers.liujunyi.bookkeeping.entity.TCoreMailInfo;

/***
 * 文件名称: ICoreMailInfoService.java
 * 文件描述: 邮件内容信息 service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月31日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreMailInfoService {
	/**
	 * 新增邮件内容
	 * @param mailInfo
	 * @return
	 */
	public int addMailInfo(TCoreMailInfo mailInfo);
	
	/**
	 * 编辑邮件内容
	 * @param mailInfo
	 * @return
	 */
	public int editMailInfo(TCoreMailInfo mailInfo);
	
	/**
	 * 删除
	 * @param ids 主键id
	 * @return
	 */
	public int deletes(String[] ids);
	
	/**
	 * 根据emailId删除信息
	 * @param emailIds 邮件地址id
	 * @return
	 */
	public int deletesEmailId(String[] emailIds);
	
	/**
	 * 更新 isActivate
	 * @param ids   主键id数组
	 * @param status 状态值    1001：激活      1002： 失效
	 * @param params
	 * @return
	 */
	public int updateIsActivate(ConcurrentMap<String, Object> params);
	
	/**
	 * 获取邮件内容列表
	 * @param emailId      邮件地址ID
	 * @param isActivate   1001：激活      1002： 失效
	 * @param bid          1001：注册发送验证码  1002:找回密码发送验证码
	 * @param params
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreMailInfo> findMailInfoList(ConcurrentMap<String, Object> params);
	
	/**
	 * 根据id获取邮件内容信息
	 * @param id  主键id
	 * @return   
	 */
	public TCoreMailInfo findMailInfo(String id);
	
	/**
	 * 根据  emailId  bid   isActivate 获取邮件内容
	 * @param emailId      邮件地址ID
	 * @param isActivate   1001：激活  
	 * @param bid          1001：注册发送验证码  1002:找回密码发送验证码
	 * @param params
	 * @return
	 */
	public TCoreMailInfo findMailInfo(ConcurrentMap<String, Object> params);
}
