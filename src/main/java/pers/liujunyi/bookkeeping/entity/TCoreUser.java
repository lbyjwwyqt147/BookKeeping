package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreUser entity.
 * @Description 用户表
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_user")
public class TCoreUser implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*主键ID*/
	private String id;
	/*用户编号*/
	private String userCode;
	/*登录账户*/
	private String loginUser;
	/*登录密码*/
	private String loginPwd;
	/*用户昵称*/
	private String userNickname;
	/*用户真实姓名*/
	private String userRealname;
	/*用户外文名*/
	private String userOriginal;
	/*性别*/
	private String userSex;
	/*名族*/
	private String nationalityId;
	/*出生日期*/
	private String userBirthday;
	/*证件类型*/
	private String idType;
	/*证件号码*/
	private String idNumber;
	/*手机号码*/
	private String userPhone;
	/*QQ号码*/
	private String userQq;
	/*微信号码*/
	private String userWechat;
	/*微博*/
	private String weibo;
	/*用户头像*/
	private String userPortrait;
	/*用户等级*/
	private Integer userLevel;
	/*用户类型*/
	private String userType;
	/*用户邮箱*/
	private String userEmail;
	/*用户职业*/
	private String userJob;
	/*所在省份*/
	private String userProvince;
	/*所在城市*/
	private String userCity;
	/*所在区县*/
	private String userDistrict;
	/*详细地址*/
	private String userAddr;
	/*个人说明*/
	private String userRemarks;
	/*积分*/
	private float userIntegral;
	/*是否激活*/
	private String isActivate;
	/*删除标志*/
	private String deleteFlag;
	/*是否是超级管理员*/
	private String isAdmin;
	/*注册时间*/
	private String createDate;
	/*修改者*/
	private String updateUser;
	/*修改时间*/
	private String updateDate;
	/*最后登录时间*/
	private String loginDate;
	/*最后退出时间*/
	private String outDate;
	/*是否在线*/
	private String isOnline;
	/*版本号*/
	private Integer version;
	/*属性1*/
	private String attributeOne;
	/*属性2*/
	private String attributeTwo;
	/*属性3*/
	private String attributeThree;


	/**default constructor*/
	public TCoreUser(){}

	/** full constructor */
	/**主键ID,用户编号,登录账户,登录密码,用户昵称,用户真实姓名,用户外文名,性别,名族,出生日期,证件类型,证件号码,手机号码,QQ号码,微信号码,微博,用户头像,用户等级,用户类型,用户邮箱,用户职业,所在省份,所在城市,所在区县,详细地址,个人说明,积分,是否激活,删除标志,是否是超级管理员,注册时间,修改者,修改时间,最后登录时间,最后退出时间,是否在线,版本号,属性1,属性2,属性3**/
	public TCoreUser(String id,String userCode,String loginUser,String loginPwd,String userNickname,String userRealname,
		String userOriginal,String userSex,String nationalityId,String userBirthday,String idType,
		String idNumber,String userPhone,String userQq,String userWechat,String weibo,
		String userPortrait,Integer userLevel,String userType,String userEmail,String userJob,
		String userProvince,String userCity,String userDistrict,String userAddr,String userRemarks,
		float userIntegral,String isActivate,String deleteFlag,String isAdmin,String createDate,
		String updateUser,String updateDate,String loginDate,String outDate,String isOnline,
		Integer version,String attributeOne,String attributeTwo,String attributeThree){
		super();
		this.id = id;
		this.userCode = userCode;
		this.loginUser = loginUser;
		this.loginPwd = loginPwd;
		this.userNickname = userNickname;
		this.userRealname = userRealname;
		this.userOriginal = userOriginal;
		this.userSex = userSex;
		this.nationalityId = nationalityId;
		this.userBirthday = userBirthday;
		this.idType = idType;
		this.idNumber = idNumber;
		this.userPhone = userPhone;
		this.userQq = userQq;
		this.userWechat = userWechat;
		this.weibo = weibo;
		this.userPortrait = userPortrait;
		this.userLevel = userLevel;
		this.userType = userType;
		this.userEmail = userEmail;
		this.userJob = userJob;
		this.userProvince = userProvince;
		this.userCity = userCity;
		this.userDistrict = userDistrict;
		this.userAddr = userAddr;
		this.userRemarks = userRemarks;
		this.userIntegral = userIntegral;
		this.isActivate = isActivate;
		this.deleteFlag = deleteFlag;
		this.isAdmin = isAdmin;
		this.createDate = createDate;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
		this.loginDate = loginDate;
		this.outDate = outDate;
		this.isOnline = isOnline;
		this.version = version;
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

	/** userCode get、set方法**/
	@Column(name = "USER_CODE", length = 15, nullable = true)
	public String getUserCode(){
		return userCode;
	}
	public void setUserCode(String userCode){
		this.userCode = userCode != null ? userCode.trim() : userCode;
	}

	/** loginUser get、set方法**/
	@Column(name = "LOGIN_USER", length = 32, nullable = true)
	public String getLoginUser(){
		return loginUser;
	}
	public void setLoginUser(String loginUser){
		this.loginUser = loginUser != null ? loginUser.trim() : loginUser;
	}

	/** loginPwd get、set方法**/
	@Column(name = "LOGIN_PWD", length = 32, nullable = true)
	public String getLoginPwd(){
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd){
		this.loginPwd = loginPwd != null ? loginPwd.trim() : loginPwd;
	}

	/** userNickname get、set方法**/
	@Column(name = "USER_NICKNAME", length = 20, nullable = true)
	public String getUserNickname(){
		return userNickname;
	}
	public void setUserNickname(String userNickname){
		this.userNickname = userNickname != null ? userNickname.trim() : userNickname;
	}

	/** userRealname get、set方法**/
	@Column(name = "USER_REALNAME", length = 10, nullable = true)
	public String getUserRealname(){
		return userRealname;
	}
	public void setUserRealname(String userRealname){
		this.userRealname = userRealname != null ? userRealname.trim() : userRealname;
	}

	/** userOriginal get、set方法**/
	@Column(name = "USER_ORIGINAL", length = 30, nullable = true)
	public String getUserOriginal(){
		return userOriginal;
	}
	public void setUserOriginal(String userOriginal){
		this.userOriginal = userOriginal != null ? userOriginal.trim() : userOriginal;
	}

	/** userSex get、set方法**/
	@Column(name = "USER_SEX", length = 4, nullable = true)
	public String getUserSex(){
		return userSex;
	}
	public void setUserSex(String userSex){
		this.userSex = userSex != null ? userSex.trim() : userSex;
	}

	/** nationalityId get、set方法**/
	@Column(name = "NATIONALITY_ID", length = 32, nullable = true)
	public String getNationalityId(){
		return nationalityId;
	}
	public void setNationalityId(String nationalityId){
		this.nationalityId = nationalityId != null ? nationalityId.trim() : nationalityId;
	}

	/** userBirthday get、set方法**/
	@Column(name = "USER_BIRTHDAY", length = 10, nullable = true)
	public String getUserBirthday(){
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday){
		this.userBirthday = userBirthday != null ? userBirthday.trim() : userBirthday;
	}

	/** idType get、set方法**/
	@Column(name = "ID_TYPE", length = 4, nullable = true)
	public String getIdType(){
		return idType;
	}
	public void setIdType(String idType){
		this.idType = idType != null ? idType.trim() : idType;
	}

	/** idNumber get、set方法**/
	@Column(name = "ID_NUMBER", length = 30, nullable = true)
	public String getIdNumber(){
		return idNumber;
	}
	public void setIdNumber(String idNumber){
		this.idNumber = idNumber != null ? idNumber.trim() : idNumber;
	}

	/** userPhone get、set方法**/
	@Column(name = "USER_PHONE", length = 15, nullable = true)
	public String getUserPhone(){
		return userPhone;
	}
	public void setUserPhone(String userPhone){
		this.userPhone = userPhone != null ? userPhone.trim() : userPhone;
	}

	/** userQq get、set方法**/
	@Column(name = "USER_QQ", length = 20, nullable = true)
	public String getUserQq(){
		return userQq;
	}
	public void setUserQq(String userQq){
		this.userQq = userQq != null ? userQq.trim() : userQq;
	}

	/** userWechat get、set方法**/
	@Column(name = "USER_WECHAT", length = 25, nullable = true)
	public String getUserWechat(){
		return userWechat;
	}
	public void setUserWechat(String userWechat){
		this.userWechat = userWechat != null ? userWechat.trim() : userWechat;
	}

	/** weibo get、set方法**/
	@Column(name = "WEIBO", length = 255, nullable = true)
	public String getWeibo(){
		return weibo;
	}
	public void setWeibo(String weibo){
		this.weibo = weibo != null ? weibo.trim() : weibo;
	}

	/** userPortrait get、set方法**/
	@Column(name = "USER_PORTRAIT", length = 255, nullable = true)
	public String getUserPortrait(){
		return userPortrait;
	}
	public void setUserPortrait(String userPortrait){
		this.userPortrait = userPortrait != null ? userPortrait.trim() : userPortrait;
	}

	/** userLevel get、set方法**/
	@Column(name = "USER_LEVEL", nullable = true)
	public Integer getUserLevel(){
		return userLevel;
	}
	public void setUserLevel(Integer userLevel){
		this.userLevel = userLevel;
	}

	/** userType get、set方法**/
	@Column(name = "USER_TYPE", length = 4, nullable = true)
	public String getUserType(){
		return userType;
	}
	public void setUserType(String userType){
		this.userType = userType != null ? userType.trim() : userType;
	}

	/** userEmail get、set方法**/
	@Column(name = "USER_EMAIL", length = 32, nullable = true)
	public String getUserEmail(){
		return userEmail;
	}
	public void setUserEmail(String userEmail){
		this.userEmail = userEmail != null ? userEmail.trim() : userEmail;
	}

	/** userJob get、set方法**/
	@Column(name = "USER_JOB", length = 32, nullable = true)
	public String getUserJob(){
		return userJob;
	}
	public void setUserJob(String userJob){
		this.userJob = userJob != null ? userJob.trim() : userJob;
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
	@Column(name = "USER_ADDR", length = 150, nullable = true)
	public String getUserAddr(){
		return userAddr;
	}
	public void setUserAddr(String userAddr){
		this.userAddr = userAddr != null ? userAddr.trim() : userAddr;
	}

	/** userRemarks get、set方法**/
	@Column(name = "USER_REMARKS", length = 500, nullable = true)
	public String getUserRemarks(){
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks){
		this.userRemarks = userRemarks != null ? userRemarks.trim() : userRemarks;
	}

	/** userIntegral get、set方法**/
	@Column(name = "USER_INTEGRAL", nullable = true)
	public float getUserIntegral(){
		return userIntegral;
	}
	public void setUserIntegral(float userIntegral){
		this.userIntegral = userIntegral;
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

	/** isAdmin get、set方法**/
	@Column(name = "IS_ADMIN", length = 2, nullable = true)
	public String getIsAdmin(){
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin){
		this.isAdmin = isAdmin != null ? isAdmin.trim() : isAdmin;
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

	/** loginDate get、set方法**/
	@Column(name = "LOGIN_DATE", length = 19, nullable = true)
	public String getLoginDate(){
		return loginDate;
	}
	public void setLoginDate(String loginDate){
		this.loginDate = loginDate != null ? loginDate.trim() : loginDate;
	}

	/** outDate get、set方法**/
	@Column(name = "OUT_DATE", length = 19, nullable = true)
	public String getOutDate(){
		return outDate;
	}
	public void setOutDate(String outDate){
		this.outDate = outDate != null ? outDate.trim() : outDate;
	}

	/** isOnline get、set方法**/
	@Column(name = "IS_ONLINE", length = 4, nullable = true)
	public String getIsOnline(){
		return isOnline;
	}
	public void setIsOnline(String isOnline){
		this.isOnline = isOnline != null ? isOnline.trim() : isOnline;
	}

	/** version get、set方法**/
	@Column(name = "VERSION", nullable = true)
	public Integer getVersion(){
		return version;
	}
	public void setVersion(Integer version){
		this.version = version;
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
