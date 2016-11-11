package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreRoleModule entity.
 * @Description 角色资源模块表
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_role_module")
public class TCoreRoleModule implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*主键ID*/
	private String id;
	/*角色ID*/
	private String roleId;
	/*角色代码*/
	private String roleCode;
	/*模块ID*/
	private String moduleId;
	/*模块编号*/
	private String moduleCode;
	/*是否激活*/
	private String isActivate;


	/**default constructor*/
	public TCoreRoleModule(){}

	/** full constructor */
	/**主键ID,角色ID,角色代码,模块ID,模块编号,是否激活**/
	public TCoreRoleModule(String id,String roleId,String roleCode,String moduleId,String moduleCode,String isActivate){
		super();
		this.id = id;
		this.roleId = roleId;
		this.roleCode = roleCode;
		this.moduleId = moduleId;
		this.moduleCode = moduleCode;
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

	/** moduleId get、set方法**/
	@Column(name = "MODULE_ID", length = 32, nullable = true)
	public String getModuleId(){
		return moduleId;
	}
	public void setModuleId(String moduleId){
		this.moduleId = moduleId != null ? moduleId.trim() : moduleId;
	}

	/** moduleCode get、set方法**/
	@Column(name = "MODULE_CODE", length = 30, nullable = true)
	public String getModuleCode(){
		return moduleCode;
	}
	public void setModuleCode(String moduleCode){
		this.moduleCode = moduleCode != null ? moduleCode.trim() : moduleCode;
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
