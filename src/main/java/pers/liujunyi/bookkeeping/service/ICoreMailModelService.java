package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;


import pers.liujunyi.bookkeeping.entity.TCoreMailModel;

/***
 * 文件名称: ICoreMailModelService.java
 * 文件描述: 邮件地址service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月19日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreMailModelService {
	
	/**
	 * 新增
	 * @param mailModel
	 * @return
	 */
	public int  addMail(TCoreMailModel mailModel);
	
	/**
	 * 编辑
	 * @param mailModel
	 * @return
	 */
	public int editMail(TCoreMailModel mailModel);
	

	/**
	 * 删除
	 * @param id 主键id
	 * @return
	 */
	public int deleteMail(String id);
	
	/**
	 * 修改 isActivate    
	 * @param id
	 * @param status  1001：激活     1002 失效
	 * @return
	 */
	public int updateIsActivate(String id,String status);
	
	/**
	 * 获取邮件地址列表
	 * @param isActivate     1001：激活     1002 失效
	 * @param attributeOne   
	 * @param params
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreMailModel> findMailModelsList(ConcurrentMap<String, Object> params);
	
	/**
	 * 获取单条邮件地址
	 * @param params
	 * @return
	 */
	public TCoreMailModel finMailModel(ConcurrentMap<String, Object> params);
}
