package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import pers.liujunyi.bookkeeping.entity.TCoreUser;

/***
 * 文件名称: ICoreUserService.java
 * 文件描述: 用户service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月19日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreUserService {

	/**
	 * 插入用户信息
	 * @param user 用户对象
	 * @return
	 */
	public int addUser(TCoreUser user);
	
	/**
	 * 保存用户信息
	 * @param user  用户对象
	 * @param task  add：新增  edit：修改编辑
	 * @return
	 */
	public ConcurrentMap<String,Object> saveUserInfo(TCoreUser user,String task);
	
	/**
	 * 修改用户信息
	 * @param user 用户对象
	 * @return
	 */
	public int editUser(TCoreUser user);
	
	/**
	 * 根据登录名和登录密码查询用户信息
	 * @param loginUser  登录名
	 * @param loginPwd   登录密码
	 * @return 返回用户信息
	 */
	public TCoreUser getSingleUserInfo(String loginUser,String loginPwd);
	
	/**
	 * 根据主键ID查询用户信息
	 * @param id 主键ID
	 * @return 返回用户信息
	 */
	public TCoreUser getSingleUserInfo(String id);
	
	/**
	 * 根据用户编号查询用户信息
	 * @param userCode 用户编号
	 * @return 返回用户信息
	 */
	public TCoreUser getSingleCodeUserInfo(String userCode);
	
	/**
	 * 根据 登录名、手机号、邮箱 查询用户信息
	 * @param loginUser 登录名
	 * @param userPhone 手机号
	 * @param userEmail 邮箱
	 * @return 返回用户信息
	 */
	public TCoreUser getSingleUserInfo(String loginUser,String userPhone,String userEmail);
	
	/**
	 * 用户登录
	 * @param loginUser  登录名
	 * @param loginPwd   登录密码
	 * @param securityCode  验证码
	 * @return 返回登录信息json
	 */
	public String findUserLogin(String loginUser,String loginPwd,String securityCode,HttpServletRequest request);
	
	/**
	 * 保存用户信息
	 * @param user  用户对象
	 * @param task  标识 ： add表示新增  edit 表示修改
	 * @param starStrings
	 * @return
	 */
	public String saveUser(TCoreUser user,String task,String... starStrings);
	
	/**
	 * 判断是否有相同的登录名、手机号、邮箱存在
	 * @param loginUser  登录名
	 * @param userPhone  手机号
	 * @param userEmail   邮箱
	 * @return
	 */
	public boolean checkUserIsExist(ConcurrentMap<String,Object> map);
	
	

	/**
	 * 根据 登录名、手机号、邮箱、证件类型、身份证号码 查询用户主键ID (判断某个值是否存在 会用到这个方法)
	 * @param loginUser 登录名
	 * @param userPhone 手机号
	 * @param userEmail 邮箱
	 * @return 返回用户主键ID
	 */
	public String getSingleUserId(ConcurrentMap<String,Object> map);
	
	/**
	 * 查询用户列表
	 * @param    userCode  用户编号
	 * @param    loginUser 帐号
	 * @param    userNickname  昵称
	 * @param    idNumber     证件号码
	 * @param    userPhone    手机号码
	 * @param    userEmail    邮箱
	 * @param    isActivate   是否激活
	 * @param    userType     类型
	 * @param map
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreUser> findArrayList(ConcurrentMap<String,Object> map);
	
	/**
	 * 更新状态
	 * @param   ids  主键
	 * @param   isActivate   1001:激活   1002：锁定
	 * @param map
	 * @return
	 */
	public int updateStatus(ConcurrentMap<String,Object> map);
	
	/**
	 * 根据ID真删除
	 * @param ids
	 * @return
	 */
	public int deletes(String[] ids);
	
	/**
	 * 删除同时删除关联数据
	 * @param ids
	 * @return
	 */
	public String deletesAndRelevance(String[] ids);
	
	/**
	 * 根据ID逻辑删除
	 * @param ids
	 * @return
	 */
	public int deletesFlag(String[] ids);
}
