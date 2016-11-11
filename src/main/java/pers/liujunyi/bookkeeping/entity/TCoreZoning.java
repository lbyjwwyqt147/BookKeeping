package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreZoning entity.
 * @Description 区划管理
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_zoning")
public class TCoreZoning implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*主键ID*/
	private String id;
	/*区划代码*/
	private String zoningCode;
	/*父ID*/
	private String parentId;
	/*区划名称*/
	private String zoningName;
	/*简称*/
	private String shortName;
	/*经度*/
	private float longitude;
	/*纬度*/
	private float latitudelatitude;
	/*区划等级*/
	private Integer zoningLevel;
	/*顺序*/
	private Integer zoningSort;
	/*状态*/
	private Integer status;
	/*邮编*/
	private Integer postCode;


	/**default constructor*/
	public TCoreZoning(){}

	/** full constructor */
	/**主键ID,区划代码,父ID,区划名称,简称,经度,纬度,区划等级,顺序,状态,邮编**/
	public TCoreZoning(String id,String zoningCode,String parentId,String zoningName,String shortName,float longitude,
		float latitudelatitude,Integer zoningLevel,Integer zoningSort,Integer status,Integer postCode){
		super();
		this.id = id;
		this.zoningCode = zoningCode;
		this.parentId = parentId;
		this.zoningName = zoningName;
		this.shortName = shortName;
		this.longitude = longitude;
		this.latitudelatitude = latitudelatitude;
		this.zoningLevel = zoningLevel;
		this.zoningSort = zoningSort;
		this.status = status;
		this.postCode = postCode;
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

	/** zoningCode get、set方法**/
	@Column(name = "ZONING_CODE", length = 32, nullable = true)
	public String getZoningCode(){
		return zoningCode;
	}
	public void setZoningCode(String zoningCode){
		this.zoningCode = zoningCode != null ? zoningCode.trim() : zoningCode;
	}

	/** parentId get、set方法**/
	@Column(name = "PARENT_ID", length = 32, nullable = true)
	public String getParentId(){
		return parentId;
	}
	public void setParentId(String parentId){
		this.parentId = parentId != null ? parentId.trim() : parentId;
	}

	/** zoningName get、set方法**/
	@Column(name = "ZONING_NAME", length = 50, nullable = true)
	public String getZoningName(){
		return zoningName;
	}
	public void setZoningName(String zoningName){
		this.zoningName = zoningName != null ? zoningName.trim() : zoningName;
	}

	/** shortName get、set方法**/
	@Column(name = "SHORT_NAME", length = 30, nullable = true)
	public String getShortName(){
		return shortName;
	}
	public void setShortName(String shortName){
		this.shortName = shortName != null ? shortName.trim() : shortName;
	}

	/** longitude get、set方法**/
	@Column(name = "LONGITUDE", nullable = true)
	public float getLongitude(){
		return longitude;
	}
	public void setLongitude(float longitude){
		this.longitude = longitude;
	}

	/** latitudelatitude get、set方法**/
	@Column(name = "LATITUDELATITUDE", nullable = true)
	public float getLatitudelatitude(){
		return latitudelatitude;
	}
	public void setLatitudelatitude(float latitudelatitude){
		this.latitudelatitude = latitudelatitude;
	}

	/** zoningLevel get、set方法**/
	@Column(name = "ZONING_LEVEL", nullable = true)
	public Integer getZoningLevel(){
		return zoningLevel;
	}
	public void setZoningLevel(Integer zoningLevel){
		this.zoningLevel = zoningLevel;
	}

	/** zoningSort get、set方法**/
	@Column(name = "ZONING_SORT", nullable = true)
	public Integer getZoningSort(){
		return zoningSort;
	}
	public void setZoningSort(Integer zoningSort){
		this.zoningSort = zoningSort;
	}

	/** status get、set方法**/
	@Column(name = "STATUS", nullable = true)
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}

	/** postCode get、set方法**/
	@Column(name = "POST_CODE", nullable = true)
	public Integer getPostCode(){
		return postCode;
	}
	public void setPostCode(Integer postCode){
		this.postCode = postCode;
	}


}
