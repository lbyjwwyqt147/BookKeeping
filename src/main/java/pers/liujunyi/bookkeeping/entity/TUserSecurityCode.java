package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TUserSecurityCode entity.
 * @Description 注册验证码信息
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_user_security_code")
public class TUserSecurityCode implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*id*/
	private String id;
	/*用户ID*/
	private String userId;
	/*接收帐号*/
	private String receivingAccount;
	/*验证码*/
	private String securityCdoe;
	/*帐号类型（1001手机 1002邮箱）*/
	private String securityType;
	/*创建时间*/
	private String createTime;
	/*属性1*/
	private String attributeOne;
	/*属性2*/
	private String attributeTwo;
	/*属性3*/
	private String attributeThree;


	/**default constructor*/
	public TUserSecurityCode(){}

	/** full constructor */
	/**id,用户ID,接收帐号,验证码,帐号类型（1001手机 1002邮箱）,创建时间,属性1,属性2,属性3**/
	public TUserSecurityCode(String id,String userId,String receivingAccount,String securityCdoe,String securityType,String createTime,
		String attributeOne,String attributeTwo,String attributeThree){
		super();
		this.id = id;
		this.userId = userId;
		this.receivingAccount = receivingAccount;
		this.securityCdoe = securityCdoe;
		this.securityType = securityType;
		this.createTime = createTime;
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

	/** userId get、set方法**/
	@Column(name = "USER_ID", length = 32, nullable = true)
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId != null ? userId.trim() : userId;
	}

	/** receivingAccount get、set方法**/
	@Column(name = "RECEIVING_ACCOUNT", length = 32, nullable = true)
	public String getReceivingAccount(){
		return receivingAccount;
	}
	public void setReceivingAccount(String receivingAccount){
		this.receivingAccount = receivingAccount != null ? receivingAccount.trim() : receivingAccount;
	}

	/** securityCdoe get、set方法**/
	@Column(name = "SECURITY_CDOE", length = 10, nullable = true)
	public String getSecurityCdoe(){
		return securityCdoe;
	}
	public void setSecurityCdoe(String securityCdoe){
		this.securityCdoe = securityCdoe != null ? securityCdoe.trim() : securityCdoe;
	}

	/** securityType get、set方法**/
	@Column(name = "SECURITY_TYPE", length = 4, nullable = true)
	public String getSecurityType(){
		return securityType;
	}
	public void setSecurityType(String securityType){
		this.securityType = securityType != null ? securityType.trim() : securityType;
	}

	/** createTime get、set方法**/
	@Column(name = "CREATE_TIME", length = 19, nullable = true)
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime != null ? createTime.trim() : createTime;
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
