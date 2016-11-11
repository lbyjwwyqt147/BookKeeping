package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreAttachment;
import pers.liujunyi.bookkeeping.mapper.IAttachmentMapper;
import pers.liujunyi.bookkeeping.service.IAttachmentService;
import pers.liujunyi.bookkeeping.util.Constants;

/***
 * 文件名称: AttachmentServiceImpl.java
 * 文件描述: 附件service接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月26日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class AttachmentServiceImpl implements IAttachmentService {

	@Autowired
	private IAttachmentMapper attMapper;
	
	@Override
	public int addAttachment(TCoreAttachment att) {
		return attMapper.addAttachment(att);
	}

	
	@Override
	public String saveInfo(TCoreAttachment att, String task,String...strings) {
		ConcurrentMap<String, Object> resulMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = "保存附件信息失败.";
		try {
			int count = 0;
			if(task.equals(Constants.ADD)){
				count = attMapper.addAttachment(att);
			}
			if(count > 0){
				success.set(true);
				message = "保存附件信息成功.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resulMap.put("success", success);
		resulMap.put("message", message);
		return new Gson().toJson(resulMap);
	}

	@Override
	public int addAttachmentList(CopyOnWriteArrayList<TCoreAttachment> list) {
		return attMapper.addAttachmentList(list);
	}



	@Override
	public CopyOnWriteArrayList<TCoreAttachment> findAttachmentList(
			ConcurrentMap<String, Object> paramsMap) {
		return attMapper.findAttachmentAllList(paramsMap);
	}


	@Override
	public String saveInfoList(CopyOnWriteArrayList<TCoreAttachment> list,
			String task, String... strings) {
		ConcurrentMap<String, Object> resulMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = "保存附件信息失败.";
		try {
			int count = 0;
			if(task.equals(Constants.ADD)){
				count = attMapper.addAttachmentList(list);
			}
			if(count > 0){
				success.set(true);
				message = "保存附件信息成功.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resulMap.put("success", success);
		resulMap.put("message", message);
		return new Gson().toJson(resulMap);
	}


	@Override
	public int deleteAttachment(ConcurrentMap<String, Object> params) {
		return attMapper.deleteAttachment(params);
	}


	@Override
	public CopyOnWriteArrayList<TCoreAttachment> findAttachmentPortionList(
			ConcurrentMap<String, Object> paramsMap) {
		return attMapper.findAttachmentPortionList(paramsMap);
	}

}
