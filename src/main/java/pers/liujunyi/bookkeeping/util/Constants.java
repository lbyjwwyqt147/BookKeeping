package pers.liujunyi.bookkeeping.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/***
 * 文件名称: Constants.java
 * 文件描述: 系统中配置常量类
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月22日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class Constants {

	/* 表示新增数据 */
	public static final String ADD = "add";
	public static final String SAVE_SUCCESS_MSG = "保存数据成功.";
	public static final String SAVE_FAIL_MSG = "保存数据失败.";
	public static final String DELETE_FAIL_MSG = "删除数据失败.";
	public static final String DELETE_SUCCESS_MSG = "删除数据成功.";
	public static final String SELECT_SUCCESS_MSG = "查询数据成功.";
	public static final String SELECT_FAIL_MSG = "查询数据失败.";
	public static final String SELECT_NONE_MSG = "查询无数据.";
	public static final String ACTIVE_FAIL_MSG = "激活数据失败.";
	public static final String ACTIVE_SUCCESS_MSG = "激活数据成功.";
	public static final String LOCK_FAIL_MSG = "锁定数据失败.";
	public static final String LOCK_SUCCESS_MSG = "锁定数据成功.";
	public static final String UPDATE_FAIL_MSG = "更新数据状态失败.";
	public static final String UPDATE_SUCCESS_MSG = "更新数据状态成功.";
	public static final String SAVE_CODE_MSG  = "代码值重复,请重新输入.";
	/* 表示编辑数据 */
	public static final String EDIT = "edit";
	/* 手机发送短信信息 */
	public static final String SMS = "sms";
	/* 邮箱发送邮件 */
	public static final String EMAIL = "email";
	/* 图片 */
	public static final String IMAGE = "image";
	/* 文档 */
	public static final String DOCUMENT = " document";
	/* 上传PPT */
	public static final String PPT_FILE = "PPT";
	/* 上传PPT转为图片 */
	public static final String PPT_IAMGE_FILE = "PPTTOIMAGE";
	/* 上传文件存放位置 */
	public static final String SAVE_FILE_PATH = "/resources/upload";
	/* 系统中树形顶级节点编号 */
	public static final String PAERNT = "1";
	/* 用户session 的key */
	public static final String USER_SESSION = "user_session";
	/* 系统接口访问IP地址 */
	public static  String ITEM_FILE_IP = "";
	/* 用户默认头像  */
	public static  String USER_HEADPHOTO = "";
	/* 项目名称  */
	public static  String ITEM_NAME = ":8080/TeachingMoral";
	/* 删除状态  1001 未删除  */
	public static final String DELETE_NONE_STATUS = "1001";
	/* 删除状态  1002 已删除  */
	public static final String DELETE_SUCCESS_STATUS = "1002";
	/*  1001 激活状态  */
	public static final String ACTIVE_STATUS = "1001";
	/*  1002 锁定状态  */
	public static final String LOCK_STATUS = "1002";
	
	static {
		try {
			ITEM_FILE_IP = "http://"+InetAddress.getLocalHost().getHostAddress()+ITEM_NAME;
			USER_HEADPHOTO = ITEM_FILE_IP+ITEM_NAME+"/resources/pages/images/avatar1.jpg";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
         System.out.println(ITEM_FILE_IP);
     }

}
