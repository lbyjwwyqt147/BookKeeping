package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreMailModel entity.
 * @Description 邮箱配置主表
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_mail_model")
public class TCoreMailModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*id*/
	private String id;
	/*发件人邮箱服务器*/
	private String emailHost;
	/*发件人邮箱*/
	private String emailFrom;
	/*发件人用户名*/
	private String emailUserName;
	/*发件人邮箱密码*/
	private String emailPassword;
	/*发送人地址1个*/
	private String fromAddress;
	/*邮件服务端口号*/
	private String emailHostPort;
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
	public TCoreMailModel(){}

	/** full constructor */
	/**id,发件人邮箱服务器,发件人邮箱,发件人用户名,发件人邮箱密码,发送人地址1个,邮件服务端口号,是否有效1001：激活1002 失效,创建人,创建时间,修改者,修改时间,属性1,属性2,属性3**/
	public TCoreMailModel(String id,String emailHost,String emailFrom,String emailUserName,String emailPassword,String fromAddress,
		String emailHostPort,String isActivate,String createUser,String createDate,String updateUser,
		String updateDate,String attributeOne,String attributeTwo,String attributeThree){
		super();
		this.id = id;
		this.emailHost = emailHost;
		this.emailFrom = emailFrom;
		this.emailUserName = emailUserName;
		this.emailPassword = emailPassword;
		this.fromAddress = fromAddress;
		this.emailHostPort = emailHostPort;
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

	/** emailHost get、set方法**/
	@Column(name = "EMAIL_HOST", length = 20, nullable = true)
	public String getEmailHost(){
		return emailHost;
	}
	public void setEmailHost(String emailHost){
		this.emailHost = emailHost != null ? emailHost.trim() : emailHost;
	}

	/** emailFrom get、set方法**/
	@Column(name = "EMAIL_FROM", length = 32, nullable = true)
	public String getEmailFrom(){
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom){
		this.emailFrom = emailFrom != null ? emailFrom.trim() : emailFrom;
	}

	/** emailUserName get、set方法**/
	@Column(name = "EMAIL_USER_NAME", length = 32, nullable = true)
	public String getEmailUserName(){
		return emailUserName;
	}
	public void setEmailUserName(String emailUserName){
		this.emailUserName = emailUserName != null ? emailUserName.trim() : emailUserName;
	}

	/** emailPassword get、set方法**/
	@Column(name = "EMAIL_PASSWORD", length = 32, nullable = true)
	public String getEmailPassword(){
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword){
		this.emailPassword = emailPassword != null ? emailPassword.trim() : emailPassword;
	}

	/** fromAddress get、set方法**/
	@Column(name = "FROM_ADDRESS", length = 32, nullable = true)
	public String getFromAddress(){
		return fromAddress;
	}
	public void setFromAddress(String fromAddress){
		this.fromAddress = fromAddress != null ? fromAddress.trim() : fromAddress;
	}

	/** emailHostPort get、set方法**/
	@Column(name = "EMAIL_HOST_PORT", length = 5, nullable = true)
	public String getEmailHostPort(){
		return emailHostPort;
	}
	public void setEmailHostPort(String emailHostPort){
		this.emailHostPort = emailHostPort != null ? emailHostPort.trim() : emailHostPort;
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
