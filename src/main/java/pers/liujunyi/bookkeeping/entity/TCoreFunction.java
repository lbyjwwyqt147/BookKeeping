package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreFunction entity.
 * @Description 功能表（权限表）
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_function")
public class TCoreFunction implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*主键ID*/
	private String id;
	/*功能代码*/
	private String functionCode;
	/*功能名称*/
	private String functionName;
	/*是否激活*/
	private String isActivate;
	/*删除标志*/
	private String deleteFlag;
	/*功能描述*/
	private String functionDescription;
	/*创建者*/
	private String createUser;
	/*创建时间*/
	private String createDate;
	/*修改者*/
	private String updateUser;
	/*修改时间*/
	private String updateDate;


	/**default constructor*/
	public TCoreFunction(){}

	/** full constructor */
	/**主键ID,功能代码,功能名称,是否激活,删除标志,功能描述,创建者,创建时间,修改者,修改时间**/
	public TCoreFunction(String id,String functionCode,String functionName,String isActivate,String deleteFlag,String functionDescription,
		String createUser,String createDate,String updateUser,String updateDate){
		super();
		this.id = id;
		this.functionCode = functionCode;
		this.functionName = functionName;
		this.isActivate = isActivate;
		this.deleteFlag = deleteFlag;
		this.functionDescription = functionDescription;
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

	/** functionCode get、set方法**/
	@Column(name = "FUNCTION_CODE", length = 20, nullable = true)
	public String getFunctionCode(){
		return functionCode;
	}
	public void setFunctionCode(String functionCode){
		this.functionCode = functionCode != null ? functionCode.trim() : functionCode;
	}

	/** functionName get、set方法**/
	@Column(name = "FUNCTION_NAME", length = 32, nullable = true)
	public String getFunctionName(){
		return functionName;
	}
	public void setFunctionName(String functionName){
		this.functionName = functionName != null ? functionName.trim() : functionName;
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

	/** functionDescription get、set方法**/
	@Column(name = "FUNCTION_DESCRIPTION", length = 200, nullable = true)
	public String getFunctionDescription(){
		return functionDescription;
	}
	public void setFunctionDescription(String functionDescription){
		this.functionDescription = functionDescription != null ? functionDescription.trim() : functionDescription;
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
