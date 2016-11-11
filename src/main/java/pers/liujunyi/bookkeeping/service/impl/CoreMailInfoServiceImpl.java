package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.liujunyi.bookkeeping.entity.TCoreMailInfo;
import pers.liujunyi.bookkeeping.mapper.ICoreMailInfoMapper;
import pers.liujunyi.bookkeeping.service.ICoreMailInfoService;

/***
 * 文件名称: CoreMailInfoServiceImpl.java
 * 文件描述: 邮件内容信息 service接口 实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月31日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreMailInfoServiceImpl implements ICoreMailInfoService {

	@Autowired
	private ICoreMailInfoMapper mailInfoMapper;
	
	@Override
	public int addMailInfo(TCoreMailInfo mailInfo) {
		return mailInfoMapper.addMailInfo(mailInfo);
	}

	@Override
	public int editMailInfo(TCoreMailInfo mailInfo) {
		return mailInfoMapper.editMailInfo(mailInfo);
	}

	@Override
	public int deletes(String[] ids) {
		return mailInfoMapper.deletes(ids);
	}

	@Override
	public int updateIsActivate(ConcurrentMap<String, Object> params) {
		return mailInfoMapper.updateIsActivate(params);
	}

	@Override
	public CopyOnWriteArrayList<TCoreMailInfo> findMailInfoList(
			ConcurrentMap<String, Object> params) {
		return mailInfoMapper.findMailInfoList(params);
	}

	@Override
	public TCoreMailInfo findMailInfo(String id) {
		return mailInfoMapper.findMailInfo(id);
	}

	@Override
	public TCoreMailInfo findMailInfo(ConcurrentMap<String, Object> params) {
		TCoreMailInfo mailInfo = null;
		try {
			CopyOnWriteArrayList<TCoreMailInfo> list = mailInfoMapper.findMailInfoList(params);
			if(list != null && list.size() > 0){
				mailInfo = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailInfo;
	}

}
