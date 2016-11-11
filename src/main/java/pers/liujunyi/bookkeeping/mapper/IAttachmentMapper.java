package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.annotations.Param;

import pers.liujunyi.bookkeeping.entity.TCoreAttachment;

/***
 * 文件名称: IAttachmentMapper.java
 * 文件描述: 附件dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月26日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface IAttachmentMapper {
	
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
	 * @param attachType  (1001:文档 1002：图片 )
	 * @param paramsMap
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreAttachment> findAttachmentAllList(ConcurrentMap<String, Object> paramsMap);
	
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
