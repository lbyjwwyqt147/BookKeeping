package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreUserRole entity.
 * @Description 用户角色表
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_user_role")
public class TCoreUserRole implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*主键ID*/
	private String id;
	/*用户ID*/
	private String userId;
	/*角色ID*/
	private String roleId;
	/*角色代码*/
	private String roleCode;
	/*是否激活*/
	private String isActivate;


	/**default constructor*/
	public TCoreUserRole(){}

	/** full constructor */
	/**主键ID,用户ID,角色ID,角色代码,是否激活**/
	public TCoreUserRole(String id,String userId,String roleId,String roleCode,String isActivate){
		super();
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
		this.roleCode = roleCode;
		this.isActivate = isActivate;
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

	/** roleId get、set方法**/
	@Column(name = "ROLE_ID", length = 32, nullable = true)
	public String getRoleId(){
		return roleId;
	}
	public void setRoleId(String roleId){
		this.roleId = roleId != null ? roleId.trim() : roleId;
	}

	/** roleCode get、set方法**/
	@Column(name = "ROLE_CODE", length = 20, nullable = true)
	public String getRoleCode(){
		return roleCode;
	}
	public void setRoleCode(String roleCode){
		this.roleCode = roleCode != null ? roleCode.trim() : roleCode;
	}

	/** isActivate get、set方法**/
	@Column(name = "IS_ACTIVATE", length = 4, nullable = true)
	public String getIsActivate(){
		return isActivate;
	}
	public void setIsActivate(String isActivate){
		this.isActivate = isActivate != null ? isActivate.trim() : isActivate;
	}


}
