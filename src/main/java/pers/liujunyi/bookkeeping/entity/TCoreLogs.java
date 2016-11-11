package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreLogs entity.
 * @Description 日志管理(主表)
 * @author liujunyi
 * @date 2016-11-09 10:55
 */
@Entity
@Table(name="t_core_logs")
public class TCoreLogs implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*主键ID*/
	private String id;
	/*用户ID*/
	private String userId;
	/*日志内容*/
	private String logContent;
	/*方法名*/
	private String logMethod;
	/*参数*/
	private String logParams;
	/*所属模块*/
	private String logModule;
	/*所作操作*/
	private String logOperation;
	/*创建日期*/
	private String createDate;
	/*日志类型*/
	private String logType;
	/*登录IP*/
	private String loginIp;
	/*表名代码*/
	private String tableCode;
	/*表名称*/
	private String tableName;
	/*属性1*/
	private String attributeOne;
	/*属性2*/
	private String attributeTwo;
	/*属性3*/
	private String attributeThree;


	/**default constructor*/
	public TCoreLogs(){}

	/** full constructor */
	/**主键ID,用户ID,日志内容,方法名,参数,所属模块,所作操作,创建日期,日志类型,登录IP,表名代码,表名称,属性1,属性2,属性3**/
	public TCoreLogs(String id,String userId,String logContent,String logMethod,String logParams,String logModule,
		String logOperation,String createDate,String logType,String loginIp,String tableCode,
		String tableName,String attributeOne,String attributeTwo,String attributeThree){
		super();
		this.id = id;
		this.userId = userId;
		this.logContent = logContent;
		this.logMethod = logMethod;
		this.logParams = logParams;
		this.logModule = logModule;
		this.logOperation = logOperation;
		this.createDate = createDate;
		this.logType = logType;
		this.loginIp = loginIp;
		this.tableCode = tableCode;
		this.tableName = tableName;
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

	/** logContent get、set方法**/
	@Column(name = "LOG_CONTENT", length = 429496729, nullable = true)
	public String getLogContent(){
		return logContent;
	}
	public void setLogContent(String logContent){
		this.logContent = logContent != null ? logContent.trim() : logContent;
	}

	/** logMethod get、set方法**/
	@Column(name = "LOG_METHOD", length = 600, nullable = true)
	public String getLogMethod(){
		return logMethod;
	}
	public void setLogMethod(String logMethod){
		this.logMethod = logMethod != null ? logMethod.trim() : logMethod;
	}

	/** logParams get、set方法**/
	@Column(name = "LOG_PARAMS", length = 429496729, nullable = true)
	public String getLogParams(){
		return logParams;
	}
	public void setLogParams(String logParams){
		this.logParams = logParams != null ? logParams.trim() : logParams;
	}

	/** logModule get、set方法**/
	@Column(name = "LOG_MODULE", length = 32, nullable = true)
	public String getLogModule(){
		return logModule;
	}
	public void setLogModule(String logModule){
		this.logModule = logModule != null ? logModule.trim() : logModule;
	}

	/** logOperation get、set方法**/
	@Column(name = "LOG_OPERATION", length = 32, nullable = true)
	public String getLogOperation(){
		return logOperation;
	}
	public void setLogOperation(String logOperation){
		this.logOperation = logOperation != null ? logOperation.trim() : logOperation;
	}

	/** createDate get、set方法**/
	@Column(name = "CREATE_DATE", length = 19, nullable = true)
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate = createDate != null ? createDate.trim() : createDate;
	}

	/** logType get、set方法**/
	@Column(name = "LOG_TYPE", length = 4, nullable = true)
	public String getLogType(){
		return logType;
	}
	public void setLogType(String logType){
		this.logType = logType != null ? logType.trim() : logType;
	}

	/** loginIp get、set方法**/
	@Column(name = "LOGIN_IP", length = 20, nullable = true)
	public String getLoginIp(){
		return loginIp;
	}
	public void setLoginIp(String loginIp){
		this.loginIp = loginIp != null ? loginIp.trim() : loginIp;
	}

	/** tableCode get、set方法**/
	@Column(name = "TABLE_CODE", length = 50, nullable = true)
	public String getTableCode(){
		return tableCode;
	}
	public void setTableCode(String tableCode){
		this.tableCode = tableCode != null ? tableCode.trim() : tableCode;
	}

	/** tableName get、set方法**/
	@Column(name = "TABLE_NAME", length = 32, nullable = true)
	public String getTableName(){
		return tableName;
	}
	public void setTableName(String tableName){
		this.tableName = tableName != null ? tableName.trim() : tableName;
	}

	/** attributeOne get、set方法**/
	@Column(name = "ATTRIBUTE_ONE", length = 100, nullable = true)
	public String getAttributeOne(){
		return attributeOne;
	}
	public void setAttributeOne(String attributeOne){
		this.attributeOne = attributeOne != null ? attributeOne.trim() : attributeOne;
	}

	/** attributeTwo get、set方法**/
	@Column(name = "ATTRIBUTE_TWO", length = 200, nullable = true)
	public String getAttributeTwo(){
		return attributeTwo;
	}
	public void setAttributeTwo(String attributeTwo){
		this.attributeTwo = attributeTwo != null ? attributeTwo.trim() : attributeTwo;
	}

	/** attributeThree get、set方法**/
	@Column(name = "ATTRIBUTE_THREE", length = 300, nullable = true)
	public String getAttributeThree(){
		return attributeThree;
	}
	public void setAttributeThree(String attributeThree){
		this.attributeThree = attributeThree != null ? attributeThree.trim() : attributeThree;
	}


}
