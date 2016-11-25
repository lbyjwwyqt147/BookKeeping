package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

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
	public TCoreUser getSingleUserIdInfo(@Param("id")String id);
	
	/**
	 * 根据用户编号查询用户信息
	 * @param userCode 用户编号
	 * @return 返回用户信息
	 */
	public TCoreUser getSingleCodeUserInfo(@Param("userCode")String userCode);
	
	/**
	 * 根据 登录名、手机号、邮箱 查询用户信息
	 * @param loginUser 登录名
	 * @param userPhone 手机号
	 * @param userEmail 邮箱
	 * @return 返回用户信息
	 */
	public TCoreUser getSingleUserEInfo(@Param("loginUser")String loginUser,@Param("userPhone")String userPhone,@Param("userEmail")String userEmail);
	
	/**
	 * 根据 登录名、手机号、邮箱、证件类型、身份证号码 查询用户主键ID (判断某个值是否存在 会用到这个方法)
	 * @param loginUser 登录名
	 * @param userPhone 手机号
	 * @param userEmail 邮箱
	 * @return 返回用户主键ID
	 */
	public String getSingleUserMap(ConcurrentMap<String,Object> map);
	
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
	 * 根据ID逻辑删除
	 * @param ids
	 * @return
	 */
	public int deletesFlag(String[] ids);
}
