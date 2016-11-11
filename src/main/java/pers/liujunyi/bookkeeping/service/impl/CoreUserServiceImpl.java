package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreUser;
import pers.liujunyi.bookkeeping.mapper.ICoreUserMapper;
import pers.liujunyi.bookkeeping.service.ICoreUserService;
import pers.liujunyi.bookkeeping.util.Constants;

/***
 * 文件名称: CoreUserServiceImpl.java
 * 文件描述: 用户service接口实现类
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月19日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class CoreUserServiceImpl implements ICoreUserService {

	@Autowired
	private ICoreUserMapper userMapper;
	
	@Override
	public int addUser(TCoreUser user) {
		return userMapper.addUser(user);
	}

	@Override
	public int editUser(TCoreUser user) {
		return userMapper.editUser(user);
	}

	@Override
	public TCoreUser getSingleUserInfo(String loginUser, String loginPwd) {
		return userMapper.getSingleUserInfo(loginUser, loginPwd);
	}

	@Override
	public TCoreUser getSingleUserInfo(String id) {
		return userMapper.getSingleUserInfo(id);
	}

	@Override
	public TCoreUser getSingleCodeUserInfo(String userCode) {
		return userMapper.getSingleCodeUserInfo(userCode);
	}

	@Override
	public TCoreUser getSingleUserInfo(String loginUser, String userPhone,
			String userEmail) {
		return userMapper.getSingleUserInfo(loginUser, userPhone, userEmail);
	}

	@Override
	public String findUserLogin(AtomicReference<String> loginUser, AtomicReference<String> loginPwd,
			AtomicReference<String> securityCode) {
        ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
        //是否成功
        AtomicBoolean success = new AtomicBoolean(false);
        //返回消息信息
        AtomicReference<String> mssage = new AtomicReference<String>("帐号或者密码不正确.");
        try {
			TCoreUser user = userMapper.getSingleUserInfo(loginUser.toString(), loginPwd.toString());
			if(user != null){
				map.put("resultDatas", user);
				mssage.set("登录验证成功.");
				success.set(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        map.put("success", success.get());
        map.put("mssage", mssage.get());
        Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public String saveUser(TCoreUser user, String task, String... starStrings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkUserIsExist(ConcurrentMap<String,Object> map) {
		String userId = userMapper.getSingleUserId(map);
		AtomicBoolean isExist =  new AtomicBoolean(false);
		if(userId != null){
			isExist.set(true);
		}
		return isExist.get();
	}

	@Override
	public String getSingleUserId(ConcurrentMap<String,Object> map) {
		
		return userMapper.getSingleUserId(map);
	}

	@Override
	public ConcurrentMap<String, Object> saveUserInfo(TCoreUser user,
			String task) {
	    ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
	    //是否成功
        AtomicBoolean success = new AtomicBoolean(false);
        //返回消息信息
        AtomicReference<String> mssage = new AtomicReference<String>("保存数据失败.");
	    int result = 0;
        try {
	    	if(task.equals(Constants.ADD)){
	        	mssage.set("注册帐号失败.");
	        	result = userMapper.addUser(user);
	        	if(result > 0){
	        		success.set(true);
	        		mssage.set("注册帐号成功.");
	        	}
	        }else {
	        	result = userMapper.editUser(user);
	        	if(result > 0){
	        		success.set(true);
	        		mssage.set("保存数据成功.");
	        	}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    map.put("success", success.get());
        map.put("mssage", mssage.get());
	    return map;
	}

}
