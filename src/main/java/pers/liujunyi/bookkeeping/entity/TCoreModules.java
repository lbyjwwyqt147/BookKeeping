package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreModules entity.
 * @Description 资源模块表
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_modules")
public class TCoreModules implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*模块ID*/
	private String id;
	/*节点层次*/
	private Integer nodeLevel;
	/*节点状态*/
	private String nodeStatus;
	/*模块名称*/
	private String moduleName;
	/*模块编号*/
	private String moduleCode;
	/*模块类型*/
	private String moduleType;
	/*模块URL*/
	private String moduleUrl;
	/*模块父ID编号*/
	private String modulePid;
	/*模块图标1*/
	private String moduleIconOne;
	/*模块图标2*/
	private String moduleIconTwo;
	/*模块描述*/
	private String moduleDescription;
	/*是否激活*/
	private String isActivate;
	/*删除标志*/
	private String deleteFlag;
	/*创建者*/
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
	public TCoreModules(){}

	/** full constructor */
	/**模块ID,节点层次,节点状态,模块名称,模块编号,模块类型,模块URL,模块父ID编号,模块图标1,模块图标2,模块描述,是否激活,删除标志,创建者,创建时间,修改者,修改时间,属性1,属性2,属性3**/
	public TCoreModules(String id,Integer nodeLevel,String nodeStatus,String moduleName,String moduleCode,String moduleType,
		String moduleUrl,String modulePid,String moduleIconOne,String moduleIconTwo,String moduleDescription,
		String isActivate,String deleteFlag,String createUser,String createDate,String updateUser,
		String updateDate,String attributeOne,String attributeTwo,String attributeThree){
		super();
		this.id = id;
		this.nodeLevel = nodeLevel;
		this.nodeStatus = nodeStatus;
		this.moduleName = moduleName;
		this.moduleCode = moduleCode;
		this.moduleType = moduleType;
		this.moduleUrl = moduleUrl;
		this.modulePid = modulePid;
		this.moduleIconOne = moduleIconOne;
		this.moduleIconTwo = moduleIconTwo;
		this.moduleDescription = moduleDescription;
		this.isActivate = isActivate;
		this.deleteFlag = deleteFlag;
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

	/** nodeLevel get、set方法**/
	@Column(name = "NODE_LEVEL", nullable = true)
	public Integer getNodeLevel(){
		return nodeLevel;
	}
	public void setNodeLevel(Integer nodeLevel){
		this.nodeLevel = nodeLevel;
	}

	/** nodeStatus get、set方法**/
	@Column(name = "NODE_STATUS", length = 4, nullable = true)
	public String getNodeStatus(){
		return nodeStatus;
	}
	public void setNodeStatus(String nodeStatus){
		this.nodeStatus = nodeStatus != null ? nodeStatus.trim() : nodeStatus;
	}

	/** moduleName get、set方法**/
	@Column(name = "MODULE_NAME", length = 50, nullable = true)
	public String getModuleName(){
		return moduleName;
	}
	public void setModuleName(String moduleName){
		this.moduleName = moduleName != null ? moduleName.trim() : moduleName;
	}

	/** moduleCode get、set方法**/
	@Column(name = "MODULE_CODE", length = 30, nullable = true)
	public String getModuleCode(){
		return moduleCode;
	}
	public void setModuleCode(String moduleCode){
		this.moduleCode = moduleCode != null ? moduleCode.trim() : moduleCode;
	}

	/** moduleType get、set方法**/
	@Column(name = "MODULE_TYPE", length = 10, nullable = true)
	public String getModuleType(){
		return moduleType;
	}
	public void setModuleType(String moduleType){
		this.moduleType = moduleType != null ? moduleType.trim() : moduleType;
	}

	/** moduleUrl get、set方法**/
	@Column(name = "MODULE_URL", length = 200, nullable = true)
	public String getModuleUrl(){
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl){
		this.moduleUrl = moduleUrl != null ? moduleUrl.trim() : moduleUrl;
	}

	/** modulePid get、set方法**/
	@Column(name = "MODULE_PID", length = 30, nullable = true)
	public String getModulePid(){
		return modulePid;
	}
	public void setModulePid(String modulePid){
		this.modulePid = modulePid != null ? modulePid.trim() : modulePid;
	}

	/** moduleIconOne get、set方法**/
	@Column(name = "MODULE_ICON_ONE", length = 20, nullable = true)
	public String getModuleIconOne(){
		return moduleIconOne;
	}
	public void setModuleIconOne(String moduleIconOne){
		this.moduleIconOne = moduleIconOne != null ? moduleIconOne.trim() : moduleIconOne;
	}

	/** moduleIconTwo get、set方法**/
	@Column(name = "MODULE_ICON_TWO", length = 20, nullable = true)
	public String getModuleIconTwo(){
		return moduleIconTwo;
	}
	public void setModuleIconTwo(String moduleIconTwo){
		this.moduleIconTwo = moduleIconTwo != null ? moduleIconTwo.trim() : moduleIconTwo;
	}

	/** moduleDescription get、set方法**/
	@Column(name = "MODULE_DESCRIPTION", length = 200, nullable = true)
	public String getModuleDescription(){
		return moduleDescription;
	}
	public void setModuleDescription(String moduleDescription){
		this.moduleDescription = moduleDescription != null ? moduleDescription.trim() : moduleDescription;
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
