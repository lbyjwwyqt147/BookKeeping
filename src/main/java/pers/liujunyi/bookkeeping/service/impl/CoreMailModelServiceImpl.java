package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.liujunyi.bookkeeping.entity.TCoreMailModel;
import pers.liujunyi.bookkeeping.mapper.ICoreMailModelMapper;
import pers.liujunyi.bookkeeping.service.ICoreMailModelService;

/***
 * 文件名称: CoreMailModelServiceImpl.java
 * 文件描述: 邮件地址service接口 实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月19日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreMailModelServiceImpl implements ICoreMailModelService {


	@Autowired
	private ICoreMailModelMapper mailModelMapper; 
	
	@Override
	public int addMail(TCoreMailModel mailModel) {
		return mailModelMapper.addMail(mailModel);
	}

	@Override
	public int editMail(TCoreMailModel mailModel) {
		return mailModelMapper.editMail(mailModel);
	}

	@Override
	public int deleteMail(String[] ids) {
		return mailModelMapper.deleteMail(ids);
	}

	@Override
	public int updateIsActivate(String id, String status) {
		return mailModelMapper.updateIsActivate(id, status);
	}

	@Override
	public CopyOnWriteArrayList<TCoreMailModel> findMailModelsList(
			ConcurrentMap<String, Object> params) {
		return mailModelMapper.findMailModelsList(params);
	}

	@Override
	public TCoreMailModel finMailModel(ConcurrentMap<String, Object> params) {
		TCoreMailModel mailModel = null;
		try {
			CopyOnWriteArrayList<TCoreMailModel> list = mailModelMapper.findMailModelsList(params);
			if(list != null && list.size() > 0){
				mailModel = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailModel;
	}

}
