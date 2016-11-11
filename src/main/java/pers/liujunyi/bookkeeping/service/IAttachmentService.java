package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pers.liujunyi.bookkeeping.entity.TCoreAttachment;

/***
 * 文件名称: IAttachmentService.java
 * 文件描述: 附件service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月26日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface IAttachmentService {

	/**
	 * 新增附件信息
	 * @param att
	 * @return
	 */
	public int addAttachment(TCoreAttachment att);
	
	/**
	 * 批量新增附件信息
	 * @param list
	 * @return
	 */
	public int addAttachmentList(CopyOnWriteArrayList<TCoreAttachment> list);
	
	/**
	 * 保存信息
	 * @param att
	 * @param task   add：新增  edit：编辑 
	 * @return
	 */
	public String saveInfo(TCoreAttachment att,String task,String...strings);
	
	/**
	 * 批量保存附件
	 * @param list
	 * @param task   add：新增  edit：编辑 
	 * @param strings
	 * @return
	 */
	public String saveInfoList(CopyOnWriteArrayList<TCoreAttachment> list,String task,String...strings);
	
	
	/**
	 * 删除附件信息
	 * @param id      主键id
	 * @param userId  用户Id
	 * @param itemId  关联项ID
	 * @param bid     业务类型
	 * @param projectId
	 * @param att 
	 * @return
	 */
	public int deleteAttachment(ConcurrentMap<String, Object> params);
	
	
	/**
	 * 获取附件全部字段集合
	 * @param userId  用户Id
	 * @param itemId  关联项ID
	 * @param bid     业务类型
	 * @param projectId
	 * @param attachStartus  状态
	 * @param paramsMap
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreAttachment> findAttachmentList(ConcurrentMap<String, Object> paramsMap);
	
	
	/**
	 * 获取附件部分字段集合
	 * @param userId  用户Id
	 * @param itemId  关联项ID
	 * @param bid     业务类型
	 * @param projectId
	 * @param attachStartus  状态
	 * @param attachType  (1001:文档 1002：图片 )
	 * @param paramsMap
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreAttachment> findAttachmentPortionList(ConcurrentMap<String, Object> paramsMap);
	


	
}
