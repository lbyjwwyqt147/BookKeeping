package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.liujunyi.bookkeeping.controller.SenderController;

/***
 * 文件名称: TestSenderController.java
 * 文件描述: 发送邮件信息功能业务单元测试
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月10日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations ={
	"classpath*:applicationContext.xml",
    "classpath*:springmvc-servlet.xml"}) 
public class TestSenderController {

	// 模拟request,response  
    private MockHttpServletRequest request;  
    private MockHttpServletResponse response;   
      
    // 注入UserController  
    @Autowired  
    private SenderController senderController ;  
    
    // 执行测试方法之前初始化模拟request,response  
    @Before   
    public void setUp(){   
        request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse();      
    } 
    
    /**
     * 测试发送验证码信息方法
     */
    @Test
    public void testSendSecurityCode(){
    	String type = "email";
    	String email = "2893162817@qq.com";
    	String loginUser = "admin";
    	request.setParameter("type", type);
    	request.setParameter("email", email);
    	request.setParameter("loginUser", loginUser);
    	senderController.sendSecurityCode(request, response);
    }
}
