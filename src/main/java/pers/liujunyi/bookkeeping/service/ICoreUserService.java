package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;





import java.util.concurrent.atomic.AtomicReference;

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
	public String findUserLogin(AtomicReference<String> loginUser,AtomicReference<String> loginPwd,AtomicReference<String> securityCode);
	
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
	
	
}
