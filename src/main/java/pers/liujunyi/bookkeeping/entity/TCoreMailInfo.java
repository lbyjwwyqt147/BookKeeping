package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreMailInfo entity.
 * @Description 邮件内容信息
 * @author liujunyi
 * @date 2016-11-09 10:55
 */
@Entity
@Table(name="t_core_mail_info")
public class TCoreMailInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*id*/
	private String id;
	/*邮件服务配置ID*/
	private String emailId;
	/*收件人邮箱，多个邮箱以“;”分隔*/
	private String toEmails;
	/*邮件主题*/
	private String subject;
	/*邮件类容*/
	private String content;
	/*接收人地址,可以为很多个，每个地址之间用";"分隔*/
	private String toAddresses;
	/*是否有图片(1001:有图片 1002：没有图片)*/
	private String pictures;
	/*是否有附件(1001:无附件 1002：有附件)*/
	private String attachments;
	/*业务类型1001：注册发送验证码*/
	private String bid;
	/*是否有效1001：激活1002 失效*/
	private String isActivate;
	/*创建人*/
	private String createUser;
	/*创建时间*/
	private String createDate;
	/*修改者*/
	private String updateUser;
	/*修改时间*/
	private String updateDate;
	/*属性1*/
	private String attributeOne;
	/*属性2*/
	private String attributeTwo;
	/*属性3*/
	private String attributeThree;


	/**default constructor*/
	public TCoreMailInfo(){}

	/** full constructor */
	/**id,邮件服务配置ID,收件人邮箱，多个邮箱以“;”分隔,邮件主题,邮件类容,接收人地址,可以为很多个，每个地址之间用";"分隔,是否有图片(1001:有图片 1002：没有图片),是否有附件(1001:无附件 1002：有附件),业务类型1001：注册发送验证码,是否有效1001：激活1002 失效,创建人,创建时间,修改者,修改时间,属性1,属性2,属性3**/
	public TCoreMailInfo(String id,String emailId,String toEmails,String subject,String content,String toAddresses,
		String pictures,String attachments,String bid,String isActivate,String createUser,
		String createDate,String updateUser,String updateDate,String attributeOne,String attributeTwo,
		String attributeThree){
		super();
		this.id = id;
		this.emailId = emailId;
		this.toEmails = toEmails;
		this.subject = subject;
		this.content = content;
		this.toAddresses = toAddresses;
		this.pictures = pictures;
		this.attachments = attachments;
		this.bid = bid;
		this.isActivate = isActivate;
		this.createUser = createUser;
		this.createDate = createDate;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
		this.attributeOne = attributeOne;
		this.attributeTwo = attributeTwo;
		this.attributeThree = attributeThree;
	}
	
	/** id get、set方法**/
	@Id
	@Column(name = "ID", length = 32)
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id != null ? id.trim() : id;
	}

	/** emailId get、set方法**/
	@Column(name = "EMAIL_ID", length = 32, nullable = true)
	public String getEmailId(){
		return emailId;
	}
	public void setEmailId(String emailId){
		this.emailId = emailId != null ? emailId.trim() : emailId;
	}

	/** toEmails get、set方法**/
	@Column(name = "TO_EMAILS", length = 5000, nullable = true)
	public String getToEmails(){
		return toEmails;
	}
	public void setToEmails(String toEmails){
		this.toEmails = toEmails != null ? toEmails.trim() : toEmails;
	}

	/** subject get、set方法**/
	@Column(name = "SUBJECT", length = 300, nullable = true)
	public String getSubject(){
		return subject;
	}
	public void setSubject(String subject){
		this.subject = subject != null ? subject.trim() : subject;
	}

	/** content get、set方法**/
	@Column(name = "CONTENT", length = 65535, nullable = true)
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content != null ? content.trim() : content;
	}

	/** toAddresses get、set方法**/
	@Column(name = "TO_ADDRESSES", length = 5000, nullable = true)
	public String getToAddresses(){
		return toAddresses;
	}
	public void setToAddresses(String toAddresses){
		this.toAddresses = toAddresses != null ? toAddresses.trim() : toAddresses;
	}

	/** pictures get、set方法**/
	@Column(name = "PICTURES", length = 4, nullable = true)
	public String getPictures(){
		return pictures;
	}
	public void setPictures(String pictures){
		this.pictures = pictures != null ? pictures.trim() : pictures;
	}

	/** attachments get、set方法**/
	@Column(name = "ATTACHMENTS", length = 4, nullable = true)
	public String getAttachments(){
		return attachments;
	}
	public void setAttachments(String attachments){
		this.attachments = attachments != null ? attachments.trim() : attachments;
	}

	/** bid get、set方法**/
	@Column(name = "BID", length = 4, nullable = true)
	public String getBid(){
		return bid;
	}
	public void setBid(String bid){
		this.bid = bid != null ? bid.trim() : bid;
	}

	/** isActivate get、set方法**/
	@Column(name = "IS_ACTIVATE", length = 4, nullable = true)
	public String getIsActivate(){
		return isActivate;
	}
	public void setIsActivate(String isActivate){
		this.isActivate = isActivate != null ? isActivate.trim() : isActivate;
	}

	/** createUser get、set方法**/
	@Column(name = "CREATE_USER", length = 32, nullable = true)
	public String getCreateUser(){
		return createUser;
	}
	public void setCreateUser(String createUser){
		this.createUser = createUser != null ? createUser.trim() : createUser;
	}

	/** createDate get、set方法**/
	@Column(name = "CREATE_DATE", length = 19, nullable = true)
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate = createDate != null ? createDate.trim() : createDate;
	}

	/** updateUser get、set方法**/
	@Column(name = "UPDATE_USER", length = 32, nullable = true)
	public String getUpdateUser(){
		return updateUser;
	}
	public void setUpdateUser(String updateUser){
		this.updateUser = updateUser != null ? updateUser.trim() : updateUser;
	}

	/** updateDate get、set方法**/
	@Column(name = "UPDATE_DATE", length = 19, nullable = true)
	public String getUpdateDate(){
		return updateDate;
	}
	public void setUpdateDate(String updateDate){
		this.updateDate = updateDate != null ? updateDate.trim() : updateDate;
	}

	/** attributeOne get、set方法**/
	@Column(name = "ATTRIBUTE_ONE", length = 32, nullable = true)
	public String getAttributeOne(){
		return attributeOne;
	}
	public void setAttributeOne(String attributeOne){
		this.attributeOne = attributeOne != null ? attributeOne.trim() : attributeOne;
	}

	/** attributeTwo get、set方法**/
	@Column(name = "ATTRIBUTE_TWO", length = 50, nullable = true)
	public String getAttributeTwo(){
		return attributeTwo;
	}
	public void setAttributeTwo(String attributeTwo){
		this.attributeTwo = attributeTwo != null ? attributeTwo.trim() : attributeTwo;
	}

	/** attributeThree get、set方法**/
	@Column(name = "ATTRIBUTE_THREE", length = 100, nullable = true)
	public String getAttributeThree(){
		return attributeThree;
	}
	public void setAttributeThree(String attributeThree){
		this.attributeThree = attributeThree != null ? attributeThree.trim() : attributeThree;
	}


}
