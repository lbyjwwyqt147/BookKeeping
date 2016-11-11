package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TCoreAttachment entity.
 * @Description 附件表
 * @author liujunyi
 * @date 2016-10-13 10:48
 */
@Entity
@Table(name="t_core_attachment")
public class TCoreAttachment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*id*/
	private String id;
	/*用户ID*/
	private String userId;
	/*工程编号*/
	private String projectId;
	/*编号*/
	private String attachCode;
	/*父级ID*/
	private String attachParent;
	/*层次级别*/
	private Integer attachLevel;
	/*关联项数据ID*/
	private String itemId;
	/*关联的业务ID*/
	private String bid;
	/*文件原始名称*/
	private String attachOriginalName;
	/*上传后文件名称*/
	private String attachNewName;
	/*文件相对路径*/
	private String attachRelativePaths;
	/*文件绝对路径*/
	private String absolutePath;
	/*文件所在文件夹*/
	private String absoluteFolderPath;
	/*文件大小(KB)*/
	private float attachSize;
	/*文件格式*/
	private String attachSuffix;
	/*文件类型(1001:文档 1002：图片 )*/
	private String attachType;
	/*文件流数据*/
	private byte[] attachData;
	/*绑定链接*/
	private String voiceUrl;
	/*文件时长*/
	private float voiceLength;
	/*上传时间*/
	private String createDate;
	/*文件状态*/
	private String attachStartus;
	/*是否为封面*/
	private String isCover;
	/*属性1*/
	private String attributeOne;
	/*属性2*/
	private String attributeTwo;
	/*属性3*/
	private String attributeThree;


	/**default constructor*/
	public TCoreAttachment(){}

	/** full constructor */
	/**id,用户ID,工程编号,编号,父级ID,层次级别,关联项数据ID,关联的业务ID,文件原始名称,上传后文件名称,文件相对路径,文件绝对路径,文件所在文件夹,文件大小(KB),文件格式,文件类型(1001:文档 1002：图片 ),文件流数据,绑定链接,文件时长,上传时间,文件状态,是否为封面,属性1,属性2,属性3**/
	public TCoreAttachment(String id,String userId,String projectId,String attachCode,String attachParent,Integer attachLevel,
		String itemId,String bid,String attachOriginalName,String attachNewName,String attachRelativePaths,
		String absolutePath,String absoluteFolderPath,float attachSize,String attachSuffix,String attachType,
		byte[] attachData,String voiceUrl,float voiceLength,String createDate,String attachStartus,
		String isCover,String attributeOne,String attributeTwo,String attributeThree){
		super();
		this.id = id;
		this.userId = userId;
		this.projectId = projectId;
		this.attachCode = attachCode;
		this.attachParent = attachParent;
		this.attachLevel = attachLevel;
		this.itemId = itemId;
		this.bid = bid;
		this.attachOriginalName = attachOriginalName;
		this.attachNewName = attachNewName;
		this.attachRelativePaths = attachRelativePaths;
		this.absolutePath = absolutePath;
		this.absoluteFolderPath = absoluteFolderPath;
		this.attachSize = attachSize;
		this.attachSuffix = attachSuffix;
		this.attachType = attachType;
		this.attachData = attachData;
		this.voiceUrl = voiceUrl;
		this.voiceLength = voiceLength;
		this.createDate = createDate;
		this.attachStartus = attachStartus;
		this.isCover = isCover;
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

	/** projectId get、set方法**/
	@Column(name = "PROJECT_ID", length = 32, nullable = true)
	public String getProjectId(){
		return projectId;
	}
	public void setProjectId(String projectId){
		this.projectId = projectId != null ? projectId.trim() : projectId;
	}

	/** attachCode get、set方法**/
	@Column(name = "ATTACH_CODE", length = 32, nullable = true)
	public String getAttachCode(){
		return attachCode;
	}
	public void setAttachCode(String attachCode){
		this.attachCode = attachCode != null ? attachCode.trim() : attachCode;
	}

	/** attachParent get、set方法**/
	@Column(name = "ATTACH_PARENT", length = 32, nullable = true)
	public String getAttachParent(){
		return attachParent;
	}
	public void setAttachParent(String attachParent){
		this.attachParent = attachParent != null ? attachParent.trim() : attachParent;
	}

	/** attachLevel get、set方法**/
	@Column(name = "ATTACH_LEVEL", nullable = true)
	public Integer getAttachLevel(){
		return attachLevel;
	}
	public void setAttachLevel(Integer attachLevel){
		this.attachLevel = attachLevel;
	}

	/** itemId get、set方法**/
	@Column(name = "ITEM_ID", length = 32, nullable = true)
	public String getItemId(){
		return itemId;
	}
	public void setItemId(String itemId){
		this.itemId = itemId != null ? itemId.trim() : itemId;
	}

	/** bid get、set方法**/
	@Column(name = "BID", length = 20, nullable = true)
	public String getBid(){
		return bid;
	}
	public void setBid(String bid){
		this.bid = bid != null ? bid.trim() : bid;
	}

	/** attachOriginalName get、set方法**/
	@Column(name = "ATTACH_ORIGINAL_NAME", length = 100, nullable = true)
	public String getAttachOriginalName(){
		return attachOriginalName;
	}
	public void setAttachOriginalName(String attachOriginalName){
		this.attachOriginalName = attachOriginalName != null ? attachOriginalName.trim() : attachOriginalName;
	}

	/** attachNewName get、set方法**/
	@Column(name = "ATTACH_NEW_NAME", length = 100, nullable = true)
	public String getAttachNewName(){
		return attachNewName;
	}
	public void setAttachNewName(String attachNewName){
		this.attachNewName = attachNewName != null ? attachNewName.trim() : attachNewName;
	}

	/** attachRelativePaths get、set方法**/
	@Column(name = "ATTACH_RELATIVE_PATHS", length = 255, nullable = true)
	public String getAttachRelativePaths(){
		return attachRelativePaths;
	}
	public void setAttachRelativePaths(String attachRelativePaths){
		this.attachRelativePaths = attachRelativePaths != null ? attachRelativePaths.trim() : attachRelativePaths;
	}

	/** absolutePath get、set方法**/
	@Column(name = "ABSOLUTE_PATH", length = 255, nullable = true)
	public String getAbsolutePath(){
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath){
		this.absolutePath = absolutePath != null ? absolutePath.trim() : absolutePath;
	}

	/** absoluteFolderPath get、set方法**/
	@Column(name = "ABSOLUTE_FOLDER_PATH", length = 255, nullable = true)
	public String getAbsoluteFolderPath(){
		return absoluteFolderPath;
	}
	public void setAbsoluteFolderPath(String absoluteFolderPath){
		this.absoluteFolderPath = absoluteFolderPath != null ? absoluteFolderPath.trim() : absoluteFolderPath;
	}

	/** attachSize get、set方法**/
	@Column(name = "ATTACH_SIZE", nullable = true)
	public float getAttachSize(){
		return attachSize;
	}
	public void setAttachSize(float attachSize){
		this.attachSize = attachSize;
	}

	/** attachSuffix get、set方法**/
	@Column(name = "ATTACH_SUFFIX", length = 10, nullable = true)
	public String getAttachSuffix(){
		return attachSuffix;
	}
	public void setAttachSuffix(String attachSuffix){
		this.attachSuffix = attachSuffix != null ? attachSuffix.trim() : attachSuffix;
	}

	/** attachType get、set方法**/
	@Column(name = "ATTACH_TYPE", length = 4, nullable = true)
	public String getAttachType(){
		return attachType;
	}
	public void setAttachType(String attachType){
		this.attachType = attachType != null ? attachType.trim() : attachType;
	}

	/** voiceUrl get、set方法**/
	@Column(name = "VOICE_URL", length = 255, nullable = true)
	public String getVoiceUrl(){
		return voiceUrl;
	}
	public void setVoiceUrl(String voiceUrl){
		this.voiceUrl = voiceUrl != null ? voiceUrl.trim() : voiceUrl;
	}

	/** voiceLength get、set方法**/
	@Column(name = "VOICE_LENGTH", nullable = true)
	public float getVoiceLength(){
		return voiceLength;
	}
	public void setVoiceLength(float voiceLength){
		this.voiceLength = voiceLength;
	}

	/** createDate get、set方法**/
	@Column(name = "CREATE_DATE", length = 19, nullable = true)
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate = createDate != null ? createDate.trim() : createDate;
	}

	/** attachStartus get、set方法**/
	@Column(name = "ATTACH_STARTUS", length = 4, nullable = true)
	public String getAttachStartus(){
		return attachStartus;
	}
	public void setAttachStartus(String attachStartus){
		this.attachStartus = attachStartus != null ? attachStartus.trim() : attachStartus;
	}

	/** isCover get、set方法**/
	@Column(name = "IS_COVER", length = 4, nullable = true)
	public String getIsCover(){
		return isCover;
	}
	public void setIsCover(String isCover){
		this.isCover = isCover != null ? isCover.trim() : isCover;
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

	public byte[] getAttachData() {
		return attachData;
	}

	public void setAttachData(byte[] attachData) {
		this.attachData = attachData;
	}

	

}
