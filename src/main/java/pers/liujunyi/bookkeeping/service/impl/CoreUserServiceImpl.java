package pers.liujunyi.bookkeeping.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.entity.TCoreUser;
import pers.liujunyi.bookkeeping.mapper.ICoreUserMapper;
import pers.liujunyi.bookkeeping.service.ICoreUserService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;
import pers.liujunyi.bookkeeping.util.KeyUtil;

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
	
	@Autowired
	private AuthenticationManager myAuthenticationManager;
	
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
	public TCoreUser getSingleUserIdInfo(String id) {
		return userMapper.getSingleUserIdInfo(id);
	}

	@Override
	public TCoreUser getSingleCodeUserInfo(String userCode) {
		return userMapper.getSingleCodeUserInfo(userCode);
	}

	@Override
	public TCoreUser getSingleUserEInfo(String loginUser, String userPhone,
			String userEmail) {
		return userMapper.getSingleUserEInfo(loginUser, userPhone, userEmail);
	}

	@Override
	public String findUserLogin(String loginUser, String loginPwd,
			String securityCode,HttpServletRequest request) {
        ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
        //是否成功
        AtomicBoolean success = new AtomicBoolean(false);
        //返回消息信息
        String message = "帐号或者密码不正确.";
        try {
        	
        	
        	
        	
        	
			TCoreUser user = userMapper.getSingleUserInfo(loginUser, loginPwd);
			if(user != null){
				
				//会进入MyUserDetailServiceImpl 类中验证权限
				Authentication authentication = myAuthenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(user.getLoginUser(),user.getLoginPwd()));
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);
				
				map.put("userCode", user.getUserCode());
				map.put("id", user.getId());
				String[] userArray = new String[]{user.getId(),user.getUserCode()};
				request.getSession().setAttribute(Constants.USER_SESSION, userArray);
				message  = "登录验证成功.";
				success.set(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        map.put("success", success.get());
        map.put("message", message);
        Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public String saveUser(TCoreUser user, String task, String... starStrings) {
		    ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
		    //是否成功
	        AtomicBoolean success = new AtomicBoolean(false);
	        //返回消息信息
	        String message = "保存数据失败.";
	        AtomicInteger count = new AtomicInteger();
	        try {
		    	if(task != null && task.trim().equals(Constants.ADD)){
		    		map.put("loginUser", user.getLoginUser());
		    		message = "注册帐号失败.";
		    		//检查帐号是否存在
		    		String loginUser = userMapper.getSingleUserMap(map);
		    		if(loginUser != null){
		    			success.set(false);
		        		message = "帐号已经存在.";
		    		}else{
		    			user.setDeleteFlag(Constants.DELETE_NONE_STATUS);
		    			//默认激活
		    			user.setIsActivate(Constants.DELETE_NONE_STATUS);
		    			user.setCreateDate(DateTimeUtil.getCurrentDateTime());
		    			user.setUserPortrait(Constants.USER_HEADPHOTO);
		    			String cdoe = String.valueOf(KeyUtil.randomTime(8));
		    			user.setUserCode(cdoe);
		    			user.setUserSex("1002");
		    			count.set(userMapper.addUser(user));
			        	if(count.get() > 0){
			        		success.set(true);
			        		message = "注册帐号成功.";
			        	}
		    		}
		        }else if(task.trim().equals(Constants.EDIT)) {
		        	user.setUpdateDate(DateTimeUtil.getCurrentDateTime());
		        	count.set(userMapper.editUser(user));
		        	if(count.get() > 0){
		        		success.set(true);
		        		message = "保存数据成功.";
		        	}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	        map.clear();
		    map.put("success", success.get());
	        map.put("message", message);
		    return new Gson().toJson(map);
	}

	@Override
	public boolean checkUserIsExist(ConcurrentMap<String,Object> map) {
		String userId = userMapper.getSingleUserMap(map);
		AtomicBoolean isExist =  new AtomicBoolean(false);
		if(userId != null){
			isExist.set(true);
		}
		return isExist.get();
	}

	@Override
	public String getSingleUserId(ConcurrentMap<String,Object> map) {
		
		return userMapper.getSingleUserMap(map);
	}

	@Override
	public ConcurrentMap<String, Object> saveUserInfo(TCoreUser user,
			String task) {
	    ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
	    //是否成功
        AtomicBoolean success = new AtomicBoolean(false);
        //返回消息信息
        String message = "保存数据失败.";
        AtomicInteger count = new AtomicInteger();
        try {
	    	if(task != null && task.trim().equals(Constants.ADD)){
	    		message = "注册帐号失败.";
	    		count.set(userMapper.addUser(user));
	        	if(count.get() > 0){
	        		success.set(true);
	        		message = "注册帐号成功.";
	        	}
	        }else if(task.trim().equals(Constants.EDIT)) {
	        	count.set(userMapper.editUser(user));
	        	if(count.get() > 0){
	        		success.set(true);
	        		message = "保存数据成功.";
	        	}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    map.put("success", success.get());
        map.put("message", message);
	    return map;
	}

	@Override
	public CopyOnWriteArrayList<TCoreUser> findArrayList(
			ConcurrentMap<String, Object> map) {
		return userMapper.findArrayList(map);
	}

	@Override
	public int updateStatus(ConcurrentMap<String, Object> map) {
		return userMapper.updateStatus(map);
	}

	@Override
	public int deletes(String[] ids) {
		return userMapper.deletes(ids);
	}

	@Override
	public int deletesFlag(String[] ids) {
		return userMapper.deletesFlag(ids);
	}

	@Override
	public String deletesAndRelevance(String[] ids) {
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();
	    //是否成功
        AtomicBoolean success = new AtomicBoolean(false);
        //返回消息信息
        String message = Constants.DELETE_FAIL_MSG;
       
        try {
        	 AtomicInteger count = new AtomicInteger(userMapper.deletes(ids));
        	 if(count.get() > 0){
        		 success.set(true);
        		 message = Constants.DELETE_SUCCESS_MSG;
        	 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    map.put("success", success.get());
        map.put("message", message);
        return new Gson().toJson(map);
	}

}
