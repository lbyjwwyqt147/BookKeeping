package pers.liujunyi.bookkeeping.controller;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import pers.liujunyi.bookkeeping.factory.sendFactory.SendMailFactory;
import pers.liujunyi.bookkeeping.service.ISenderProviderService;
import pers.liujunyi.bookkeeping.service.ISenderService;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;

/***
 * 文件名称: SenderController.java
 * 文件描述: 发现邮件Controller
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月10日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Controller
@RequestMapping("/bookkeeping/sender")
public class SenderController {

	/**
	 * 发送验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="sendSecurityCode")
	public void sendSecurityCode(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		ConcurrentMap<String, Object> resultMap =  new ConcurrentHashMap<String, Object>();
		AtomicBoolean success = new AtomicBoolean(false);
		String message = "发送验证码失败.";
		try {
			ConcurrentMap<String, Object> paramsMap = ControllerUtil.getFormData(request);
			//email 表示邮箱发送     sms 表示短信发送
			String type = paramsMap.get("type").toString().trim();
			//接收信息帐号
			String email = paramsMap.get("email") != null ? paramsMap.get("email").toString().trim() : "";
			//用户
			String loginUser = paramsMap.get("loginUser").toString();
			if(!email.equals("")){
				
				ThreadPool<Integer> pool = new ThreadPool<Integer>(5);
				
				pool.addJob(new Runnable() {
					public void run() {
						
						//发送验证码业务逻辑处理
						ISenderProviderService provider = null;
						if(type.equals(Constants.SMS)){
							
						}else{
							provider =  new SendMailFactory();
							ISenderService sender = provider.produce();
							success = sender.sendSecurityCode(email);
							if(success.get()){
								message = "验证码发送成功.";
							}
						}
						
					}
				});
					
				
				
				
			}else{
				if(type.equals(Constants.SMS)){
					message = "手机号码不能为空.";
				}else{
					message = "邮箱地址不能为空.";
				}
			}
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resultMap.put("success", success);
	    resultMap.put("message", message);
	    System.out.println(new Gson().toJson(resultMap));
	    ControllerUtil.writeJavaScript(response, resultMap);
	}
}
