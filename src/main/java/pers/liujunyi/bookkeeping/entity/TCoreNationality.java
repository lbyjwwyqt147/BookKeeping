package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreNationality entity.
 * @Description 民族管理
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_nationality")
public class TCoreNationality implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*主键ID*/
	private String id;
	/*民族代码*/
	private String nationalityCode;
	/*民族名称*/
	private String nationalityName;


	/**default constructor*/
	public TCoreNationality(){}

	/** full constructor */
	/**主键ID,民族代码,民族名称**/
	public TCoreNationality(String id,String nationalityCode,String nationalityName){
		super();
		this.id = id;
		this.nationalityCode = nationalityCode;
		this.nationalityName = nationalityName;
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

	/** nationalityCode get、set方法**/
	@Column(name = "NATIONALITY_CODE", length = 10, nullable = true)
	public String getNationalityCode(){
		return nationalityCode;
	}
	public void setNationalityCode(String nationalityCode){
		this.nationalityCode = nationalityCode != null ? nationalityCode.trim() : nationalityCode;
	}

	/** nationalityName get、set方法**/
	@Column(name = "NATIONALITY_NAME", length = 20, nullable = true)
	public String getNationalityName(){
		return nationalityName;
	}
	public void setNationalityName(String nationalityName){
		this.nationalityName = nationalityName != null ? nationalityName.trim() : nationalityName;
	}


}
