package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreDictionary entity.
 * @Description 业务字典
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_dictionary")
public class TCoreDictionary implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*id*/
	private String id;
	/*字典编号*/
	private String dictCode;
	/*字典代码*/
	private String dictWord;
	/*字典名称*/
	private String dictName;
	/*字典描述*/
	private String dictDescription;
	/*父及Id*/
	private String parentCode;
	/*字典关联实体类名称*/
	private String entityName;
	/*关联实体字段名称*/
	private String entityFieldName;
	/*层次级别*/
	private Integer dictLevel;
	/*是否为父级节点*/
	private String isParent;
	/*字典提示信息*/
	private String dictPrompt;
	/*是否激活*/
	private String isActivate;
	/*创建时间*/
	private String createTime;
	/*创建人*/
	private String createUser;
	/*修改时间*/
	private String updateTime;
	/*修改人*/
	private String updateUser;
	/*属性1*/
	private String attributeOne;
	/*属性2*/
	private String attributeTwo;
	/*属性3*/
	private String attributeThree;


	/**default constructor*/
	public TCoreDictionary(){}

	/** full constructor */
	/**id,字典编号,字典代码,字典名称,字典描述,父及Id,字典关联实体类名称,关联实体字段名称,层次级别,是否为父级节点,字典提示信息,是否激活,创建时间,创建人,修改时间,修改人,属性1,属性2,属性3**/
	public TCoreDictionary(String id,String dictCode,String dictWord,String dictName,String dictDescription,String parentCode,
		String entityName,String entityFieldName,Integer dictLevel,String isParent,String dictPrompt,
		String isActivate,String createTime,String createUser,String updateTime,String updateUser,
		String attributeOne,String attributeTwo,String attributeThree){
		super();
		this.id = id;
		this.dictCode = dictCode;
		this.dictWord = dictWord;
		this.dictName = dictName;
		this.dictDescription = dictDescription;
		this.parentCode = parentCode;
		this.entityName = entityName;
		this.entityFieldName = entityFieldName;
		this.dictLevel = dictLevel;
		this.isParent = isParent;
		this.dictPrompt = dictPrompt;
		this.isActivate = isActivate;
		this.createTime = createTime;
		this.createUser = createUser;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
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

	/** dictCode get、set方法**/
	@Column(name = "DICT_CODE", length = 20, nullable = true)
	public String getDictCode(){
		return dictCode;
	}
	public void setDictCode(String dictCode){
		this.dictCode = dictCode != null ? dictCode.trim() : dictCode;
	}

	/** dictWord get、set方法**/
	@Column(name = "DICT_WORD", length = 20, nullable = true)
	public String getDictWord(){
		return dictWord;
	}
	public void setDictWord(String dictWord){
		this.dictWord = dictWord != null ? dictWord.trim() : dictWord;
	}

	/** dictName get、set方法**/
	@Column(name = "DICT_NAME", length = 32, nullable = true)
	public String getDictName(){
		return dictName;
	}
	public void setDictName(String dictName){
		this.dictName = dictName != null ? dictName.trim() : dictName;
	}

	/** dictDescription get、set方法**/
	@Column(name = "DICT_DESCRIPTION", length = 100, nullable = true)
	public String getDictDescription(){
		return dictDescription;
	}
	public void setDictDescription(String dictDescription){
		this.dictDescription = dictDescription != null ? dictDescription.trim() : dictDescription;
	}

	/** parentCode get、set方法**/
	@Column(name = "PARENT_CODE", length = 32, nullable = true)
	public String getParentCode(){
		return parentCode;
	}
	public void setParentCode(String parentCode){
		this.parentCode = parentCode != null ? parentCode.trim() : parentCode;
	}

	/** entityName get、set方法**/
	@Column(name = "ENTITY_NAME", length = 50, nullable = true)
	public String getEntityName(){
		return entityName;
	}
	public void setEntityName(String entityName){
		this.entityName = entityName != null ? entityName.trim() : entityName;
	}

	/** entityFieldName get、set方法**/
	@Column(name = "ENTITY_FIELD_NAME", length = 50, nullable = true)
	public String getEntityFieldName(){
		return entityFieldName;
	}
	public void setEntityFieldName(String entityFieldName){
		this.entityFieldName = entityFieldName != null ? entityFieldName.trim() : entityFieldName;
	}

	/** dictLevel get、set方法**/
	@Column(name = "DICT_LEVEL", nullable = true)
	public Integer getDictLevel(){
		return dictLevel;
	}
	public void setDictLevel(Integer dictLevel){
		this.dictLevel = dictLevel;
	}

	/** isParent get、set方法**/
	@Column(name = "IS_PARENT", length = 1, nullable = true)
	public String getIsParent(){
		return isParent;
	}
	public void setIsParent(String isParent){
		this.isParent = isParent != null ? isParent.trim() : isParent;
	}

	/** dictPrompt get、set方法**/
	@Column(name = "DICT_PROMPT", length = 100, nullable = true)
	public String getDictPrompt(){
		return dictPrompt;
	}
	public void setDictPrompt(String dictPrompt){
		this.dictPrompt = dictPrompt != null ? dictPrompt.trim() : dictPrompt;
	}

	/** isActivate get、set方法**/
	@Column(name = "IS_ACTIVATE", length = 4, nullable = true)
	public String getIsActivate(){
		return isActivate;
	}
	public void setIsActivate(String isActivate){
		this.isActivate = isActivate != null ? isActivate.trim() : isActivate;
	}

	/** createTime get、set方法**/
	@Column(name = "CREATE_TIME", length = 19, nullable = true)
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime != null ? createTime.trim() : createTime;
	}

	/** createUser get、set方法**/
	@Column(name = "CREATE_USER", length = 32, nullable = true)
	public String getCreateUser(){
		return createUser;
	}
	public void setCreateUser(String createUser){
		this.createUser = createUser != null ? createUser.trim() : createUser;
	}

	/** updateTime get、set方法**/
	@Column(name = "UPDATE_TIME", length = 19, nullable = true)
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime != null ? updateTime.trim() : updateTime;
	}

	/** updateUser get、set方法**/
	@Column(name = "UPDATE_USER", length = 32, nullable = true)
	public String getUpdateUser(){
		return updateUser;
	}
	public void setUpdateUser(String updateUser){
		this.updateUser = updateUser != null ? updateUser.trim() : updateUser;
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
