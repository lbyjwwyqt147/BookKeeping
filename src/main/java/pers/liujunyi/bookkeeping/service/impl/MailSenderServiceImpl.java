package pers.liujunyi.bookkeeping.service.impl;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import pers.liujunyi.bookkeeping.entity.TCoreAttachment;
import pers.liujunyi.bookkeeping.entity.TCoreMailInfo;
import pers.liujunyi.bookkeeping.entity.TCoreMailModel;
import pers.liujunyi.bookkeeping.service.IAttachmentService;
import pers.liujunyi.bookkeeping.service.ICoreMailInfoService;
import pers.liujunyi.bookkeeping.service.ICoreMailModelService;
import pers.liujunyi.bookkeeping.service.ISenderService;
import pers.liujunyi.bookkeeping.util.Constants;

/***
 * 文件名称: MailSenderServiceImpl.java
 * 文件描述: 通过邮箱方式发送消息
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月10日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class MailSenderServiceImpl implements ISenderService {
    
	private static final Logger LOGGER = Logger.getLogger(MailSenderServiceImpl.class);
	@Autowired
	private ICoreMailModelService mailModelService; 
	@Autowired
	private ICoreMailInfoService  mailInfoService; 
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public boolean sendMessage(ConcurrentMap<String, Object> params) {
		LOGGER.info("邮件正在发送中...........");
		//获取邮箱地址
		TCoreMailModel mailModel = mailModelService.finMailModel(params);
		AtomicBoolean success =  new AtomicBoolean(false);
		try {
			if(mailModel != null){
				//获取邮箱内容
				params.put("emailId", mailModel.getId());
				TCoreMailInfo mailInfo = mailInfoService.findMailInfo(params);
				//创建邮件消息
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper messageHelper;
				messageHelper = new MimeMessageHelper(message, true,"utf-8");
				//设置发件人邮箱
				if(mailModel.getEmailFrom() != null){
					messageHelper.setFrom(mailModel.getEmailFrom());
				}
				//设置收件人邮箱
				String toEmails = params.get("toEmails") != null ? params.get("toEmails").toString() : null;
				if(toEmails != null){
					//拆分为多个收件人
					String[] toEmailArray =  toEmails.split(";");
					messageHelper.setTo(toEmailArray);
				}else{
					LOGGER.error("收件人邮箱不能为空.");
				}
				
				//设置邮件主题
				if(mailInfo.getSubject() != null){
					messageHelper.setSubject(mailInfo.getSubject());
				}

				//如果有图片
				if(mailInfo.getPictures() != null && mailInfo.getPictures().equals("1001")){
					//获取图片附件查询条件
					params.put("attachType", "1002");
					params.put("itemId", mailInfo.getId());
					params.put("bid", Constants.IMAGE);
					params.put("attachStartus", "1001");
					//获取图片信息
					CopyOnWriteArrayList<TCoreAttachment> imageList = attachmentService.findAttachmentPortionList(params);
					if(imageList != null && imageList.size() > 0){
						Iterator<?> iterator = imageList.iterator();
						while(iterator.hasNext()){
							TCoreAttachment attachment = (TCoreAttachment) iterator.next();
							String filePath = attachment.getAttachRelativePaths();
							File file = new File(filePath);
		                    if (!file.exists()) {
		                    	LOGGER.error("图片不存在.");
		                    }else{
		                    	FileSystemResource img = new FileSystemResource(file);
		                        messageHelper.addInline(attachment.getAttachCode(), img);
		                    }
						}
					}
				}
				
				//如果有附件
				if(mailInfo.getAttachments() != null && mailInfo.getAttachments().equals("1001")){
					//获取文档附件查询条件
					params.put("attachType", "1001");
					params.put("itemId", mailInfo.getId());
					params.put("bid", Constants.DOCUMENT);
					params.put("attachStartus", "1001");
					//获取文档附件信息
					CopyOnWriteArrayList<TCoreAttachment> documentList = attachmentService.findAttachmentPortionList(params);
					if(documentList != null && documentList.size() > 0){
						Iterator<?> iterator = documentList.iterator();
						while(iterator.hasNext()){
							TCoreAttachment attachment = (TCoreAttachment) iterator.next();
							String filePath = attachment.getAttachRelativePaths();
							File file = new File(filePath);
		                    if (!file.exists()) {
		                    	LOGGER.error("附件不存在.");
		                    }else{
		                    	FileSystemResource fileResource = new FileSystemResource(file);
		                    	//key 为文件名称
		                        messageHelper.addAttachment(attachment.getAttachOriginalName(), fileResource);
		                    }
						}
					}
				}
				
				//true 表示启动html格式邮件
				messageHelper.setText(mailInfo.getContent(), true);
				Properties prop = new Properties();
				//将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.timeout", "25000");
				prop.put("mail.smtp.port", "25");
				//设置发送时间
				messageHelper.setSentDate(new Date());
				((JavaMailSenderImpl) javaMailSender).setHost(mailModel.getEmailHost());
				((JavaMailSenderImpl) javaMailSender).setJavaMailProperties(prop);
				((JavaMailSenderImpl) javaMailSender).setUsername(mailModel.getEmailFrom());
				((JavaMailSenderImpl) javaMailSender).setPassword(mailModel.getEmailPassword());
				//发送邮件
				javaMailSender.send(message);
				success.set(true);
				LOGGER.info("邮件发送完毕.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("发送邮件失败.");
		}
		return success.get();

	}

}
