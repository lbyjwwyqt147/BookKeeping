package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreRole entity.
 * @Description 角色表
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_role")
public class TCoreRole implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*主键ID*/
	private String id;
	/*角色代码*/
	private String roleCode;
	/*角色名称*/
	private String roleName;
	/*是否激活*/
	private String isActivate;
	/*删除标志*/
	private String deleteFlag;
	/*角色描述*/
	private String roleDescription;
	/*创建者*/
	private String createUser;
	/*创建时间*/
	private String createDate;
	/*修改者*/
	private String updateUser;
	/*修改时间*/
	private String updateDate;


	/**default constructor*/
	public TCoreRole(){}

	/** full constructor */
	/**主键ID,角色代码,角色名称,是否激活,删除标志,角色描述,创建者,创建时间,修改者,修改时间**/
	public TCoreRole(String id,String roleCode,String roleName,String isActivate,String deleteFlag,String roleDescription,
		String createUser,String createDate,String updateUser,String updateDate){
		super();
		this.id = id;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.isActivate = isActivate;
		this.deleteFlag = deleteFlag;
		this.roleDescription = roleDescription;
		this.createUser = createUser;
		this.createDate = createDate;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
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

	/** roleCode get、set方法**/
	@Column(name = "ROLE_CODE", length = 20, nullable = true)
	public String getRoleCode(){
		return roleCode;
	}
	public void setRoleCode(String roleCode){
		this.roleCode = roleCode != null ? roleCode.trim() : roleCode;
	}

	/** roleName get、set方法**/
	@Column(name = "ROLE_NAME", length = 32, nullable = true)
	public String getRoleName(){
		return roleName;
	}
	public void setRoleName(String roleName){
		this.roleName = roleName != null ? roleName.trim() : roleName;
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

	/** roleDescription get、set方法**/
	@Column(name = "ROLE_DESCRIPTION", length = 200, nullable = true)
	public String getRoleDescription(){
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription){
		this.roleDescription = roleDescription != null ? roleDescription.trim() : roleDescription;
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


}
