package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreLogsDetails entity.
 * @Description 日志明细表(从表)
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_logs_details")
public class TCoreLogsDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*主键ID*/
	private String id;
	/*日志ID*/
	private String logsId;
	/*被修改字段*/
	private String modificationFeild;
	/*字段描述*/
	private String feildDescribe;
	/*旧值*/
	private String oldContent;
	/*新值*/
	private String newContent;
	/*属性1*/
	private String attributeOne;
	/*属性2*/
	private String attributeTwo;
	/*属性3*/
	private String attributeThree;


	/**default constructor*/
	public TCoreLogsDetails(){}

	/** full constructor */
	/**主键ID,日志ID,被修改字段,字段描述,旧值,新值,属性1,属性2,属性3**/
	public TCoreLogsDetails(String id,String logsId,String modificationFeild,String feildDescribe,String oldContent,String newContent,
		String attributeOne,String attributeTwo,String attributeThree){
		super();
		this.id = id;
		this.logsId = logsId;
		this.modificationFeild = modificationFeild;
		this.feildDescribe = feildDescribe;
		this.oldContent = oldContent;
		this.newContent = newContent;
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

	/** logsId get、set方法**/
	@Column(name = "LOGS_ID", length = 32, nullable = true)
	public String getLogsId(){
		return logsId;
	}
	public void setLogsId(String logsId){
		this.logsId = logsId != null ? logsId.trim() : logsId;
	}

	/** modificationFeild get、set方法**/
	@Column(name = "MODIFICATION_FEILD", length = 50, nullable = true)
	public String getModificationFeild(){
		return modificationFeild;
	}
	public void setModificationFeild(String modificationFeild){
		this.modificationFeild = modificationFeild != null ? modificationFeild.trim() : modificationFeild;
	}

	/** feildDescribe get、set方法**/
	@Column(name = "FEILD_DESCRIBE", length = 100, nullable = true)
	public String getFeildDescribe(){
		return feildDescribe;
	}
	public void setFeildDescribe(String feildDescribe){
		this.feildDescribe = feildDescribe != null ? feildDescribe.trim() : feildDescribe;
	}

	/** oldContent get、set方法**/
	@Column(name = "OLD_CONTENT", length = 429496729, nullable = true)
	public String getOldContent(){
		return oldContent;
	}
	public void setOldContent(String oldContent){
		this.oldContent = oldContent != null ? oldContent.trim() : oldContent;
	}

	/** newContent get、set方法**/
	@Column(name = "NEW_CONTENT", length = 429496729, nullable = true)
	public String getNewContent(){
		return newContent;
	}
	public void setNewContent(String newContent){
		this.newContent = newContent != null ? newContent.trim() : newContent;
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
