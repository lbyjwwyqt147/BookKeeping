package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.liujunyi.bookkeeping.controller.UserController;
import pers.liujunyi.bookkeeping.entity.TCoreUser;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;


/***
 * 文件名称: TestUserController.java
 * 文件描述: 用户模块功能业务单元测试
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月22日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations ={
	"classpath*:applicationContext.xml",
    "classpath*:springmvc-servlet.xml"}) 
public class TestUserController {

	// 模拟request,response  
    private MockHttpServletRequest request;  
    private MockHttpServletResponse response;   
      
    // 注入UserController  
    @Autowired  
    private UserController userController ;  
    
    // 执行测试方法之前初始化模拟request,response  
    @Before   
    public void setUp(){   
        request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse();      
    } 
    
    /**
     * 测试保存用户信息方法
     */
    @Test
    public void testSaveUser(){
    	TCoreUser user = new TCoreUser();
    	user.setCreateDate(DateTimeUtil.getCurrentDateTime());
    	user.setDeleteFlag("1001");
    	user.setLoginUser("admin");
    	user.setLoginPwd("123456");
    	user.setUserEmail("123456@163.com");
    	user.setUserNickname("管理员");
    	user.setUserCode("123456");
    	user.setUserSex("1");
    	userController.saveUser(user, "add", request, response);
    	
    }
    
    /**
     * 测试登录方法
     */
    @Test
    public void testUserLogin(){
    	String loginUser = "admin";
    	String loginPwd = "123456";
    	//设置参数
    	request.setParameter("login_user", loginUser);
    	request.setParameter("login_pwd", loginPwd);
    	//userController.userLogin(request, response);
    }
}
