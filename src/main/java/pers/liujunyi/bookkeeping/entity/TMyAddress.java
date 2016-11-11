package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TMyAddress entity.
 * @Description 我的地址
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_my_address")
public class TMyAddress implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*主键ID*/
	private String id;
	/*用户ID*/
	private String userId;
	/*联系人*/
	private String userLinkman;
	/*联系人电话*/
	private String linkmanPhone;
	/*备用联系人*/
	private String userResLinkman;
	/*备用联系人电话*/
	private String linkmanResPhone2;
	/*所在省份*/
	private String userProvince;
	/*所在城市*/
	private String userCity;
	/*所在区县*/
	private String userDistrict;
	/*详细地址*/
	private String userAddr;
	/*邮编*/
	private String zipCode;
	/*是否默认地址*/
	private String isDefault;
	/*是否激活*/
	private String isActivate;
	/*删除标志*/
	private String deleteFlag;
	/*备注*/
	private String remarks;
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
	public TMyAddress(){}

	/** full constructor */
	/**主键ID,用户ID,联系人,联系人电话,备用联系人,备用联系人电话,所在省份,所在城市,所在区县,详细地址,邮编,是否默认地址,是否激活,删除标志,备注,创建人,创建时间,修改者,修改时间,属性1,属性2,属性3**/
	public TMyAddress(String id,String userId,String userLinkman,String linkmanPhone,String userResLinkman,String linkmanResPhone2,
		String userProvince,String userCity,String userDistrict,String userAddr,String zipCode,
		String isDefault,String isActivate,String deleteFlag,String remarks,String createUser,
		String createDate,String updateUser,String updateDate,String attributeOne,String attributeTwo,
		String attributeThree){
		super();
		this.id = id;
		this.userId = userId;
		this.userLinkman = userLinkman;
		this.linkmanPhone = linkmanPhone;
		this.userResLinkman = userResLinkman;
		this.linkmanResPhone2 = linkmanResPhone2;
		this.userProvince = userProvince;
		this.userCity = userCity;
		this.userDistrict = userDistrict;
		this.userAddr = userAddr;
		this.zipCode = zipCode;
		this.isDefault = isDefault;
		this.isActivate = isActivate;
		this.deleteFlag = deleteFlag;
		this.remarks = remarks;
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

	/** userId get、set方法**/
	@Column(name = "USER_ID", length = 32, nullable = true)
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId != null ? userId.trim() : userId;
	}

	/** userLinkman get、set方法**/
	@Column(name = "USER_LINKMAN", length = 10, nullable = true)
	public String getUserLinkman(){
		return userLinkman;
	}
	public void setUserLinkman(String userLinkman){
		this.userLinkman = userLinkman != null ? userLinkman.trim() : userLinkman;
	}

	/** linkmanPhone get、set方法**/
	@Column(name = "LINKMAN_PHONE", length = 15, nullable = true)
	public String getLinkmanPhone(){
		return linkmanPhone;
	}
	public void setLinkmanPhone(String linkmanPhone){
		this.linkmanPhone = linkmanPhone != null ? linkmanPhone.trim() : linkmanPhone;
	}

	/** userResLinkman get、set方法**/
	@Column(name = "USER_RES_LINKMAN", length = 10, nullable = true)
	public String getUserResLinkman(){
		return userResLinkman;
	}
	public void setUserResLinkman(String userResLinkman){
		this.userResLinkman = userResLinkman != null ? userResLinkman.trim() : userResLinkman;
	}

	/** linkmanResPhone2 get、set方法**/
	@Column(name = "LINKMAN_RES_PHONE2", length = 15, nullable = true)
	public String getLinkmanResPhone2(){
		return linkmanResPhone2;
	}
	public void setLinkmanResPhone2(String linkmanResPhone2){
		this.linkmanResPhone2 = linkmanResPhone2 != null ? linkmanResPhone2.trim() : linkmanResPhone2;
	}

	/** userProvince get、set方法**/
	@Column(name = "USER_PROVINCE", length = 32, nullable = true)
	public String getUserProvince(){
		return userProvince;
	}
	public void setUserProvince(String userProvince){
		this.userProvince = userProvince != null ? userProvince.trim() : userProvince;
	}

	/** userCity get、set方法**/
	@Column(name = "USER_CITY", length = 32, nullable = true)
	public String getUserCity(){
		return userCity;
	}
	public void setUserCity(String userCity){
		this.userCity = userCity != null ? userCity.trim() : userCity;
	}

	/** userDistrict get、set方法**/
	@Column(name = "USER_DISTRICT", length = 32, nullable = true)
	public String getUserDistrict(){
		return userDistrict;
	}
	public void setUserDistrict(String userDistrict){
		this.userDistrict = userDistrict != null ? userDistrict.trim() : userDistrict;
	}

	/** userAddr get、set方法**/
	@Column(name = "USER_ADDR", length = 200, nullable = true)
	public String getUserAddr(){
		return userAddr;
	}
	public void setUserAddr(String userAddr){
		this.userAddr = userAddr != null ? userAddr.trim() : userAddr;
	}

	/** zipCode get、set方法**/
	@Column(name = "ZIP_CODE", length = 6, nullable = true)
	public String getZipCode(){
		return zipCode;
	}
	public void setZipCode(String zipCode){
		this.zipCode = zipCode != null ? zipCode.trim() : zipCode;
	}

	/** isDefault get、set方法**/
	@Column(name = "IS_DEFAULT", length = 2, nullable = true)
	public String getIsDefault(){
		return isDefault;
	}
	public void setIsDefault(String isDefault){
		this.isDefault = isDefault != null ? isDefault.trim() : isDefault;
	}

	/** isActivate get、set方法**/
	@Column(name = "IS_ACTIVATE", length = 4, nullable = true)
	public String getIsActivate(){
		return isActivate;
	}
	public void setIsActivate(String isActivate){
		this.isActivate = isActivate != null ? isActivate.trim() : isActivate;
	}

	/** deleteFlag get、set方法**/
	@Column(name = "DELETE_FLAG", length = 4, nullable = true)
	public String getDeleteFlag(){
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag){
		this.deleteFlag = deleteFlag != null ? deleteFlag.trim() : deleteFlag;
	}

	/** remarks get、set方法**/
	@Column(name = "REMARKS", length = 200, nullable = true)
	public String getRemarks(){
		return remarks;
	}
	public void setRemarks(String remarks){
		this.remarks = remarks != null ? remarks.trim() : remarks;
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
