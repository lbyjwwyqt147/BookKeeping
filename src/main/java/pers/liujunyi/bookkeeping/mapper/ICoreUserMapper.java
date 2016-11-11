package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;

import org.apache.ibatis.annotations.Param;

import pers.liujunyi.bookkeeping.entity.TCoreUser;

/***
 * 文件名称: ICoreUserMapper.java
 * 文件描述: 用户dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月19日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreUserMapper {

	/**
	 * 插入用户信息
	 * @param user 用户对象
	 * @return
	 */
	public int addUser(TCoreUser user);
	
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
	public TCoreUser getSingleUserInfo(@Param("loginUser") String loginUser,@Param("loginPwd") String loginPwd);
	
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
	 * 根据 登录名、手机号、邮箱、证件类型、身份证号码 查询用户主键ID (判断某个值是否存在 会用到这个方法)
	 * @param loginUser 登录名
	 * @param userPhone 手机号
	 * @param userEmail 邮箱
	 * @return 返回用户主键ID
	 */
	public String getSingleUserId(ConcurrentMap<String,Object> map);
	
	
	
}
