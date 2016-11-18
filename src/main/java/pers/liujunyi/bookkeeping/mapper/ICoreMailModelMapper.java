package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.annotations.Param;

import pers.liujunyi.bookkeeping.entity.TCoreMailModel;

/***
 * 文件名称: ICoreMailModelMapper.java
 * 文件描述: 邮件地址 dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月31日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreMailModelMapper {
    
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
	 * @param ids 主键id
	 * @return
	 */
	public int deleteMail(String[] ids);
	
	/**
	 * 修改 isActivate    1001：激活     1002 失效
	 * @param id
	 * @param status
	 * @return
	 */
	public int updateIsActivate(@Param("id")String id,@Param("status")String status);
	
	/**
	 * 获取邮件地址列表
	 * @param isActivate     1001：激活     1002 失效
	 * @param attributeOne   
	 * @param params
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreMailModel> findMailModelsList(ConcurrentMap<String, Object> params);
}
