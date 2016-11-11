package pers.liujunyi.bookkeeping.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import pers.liujunyi.bookkeeping.entity.TCoreAttachment;

/***
 * 文件名称: AttachmentFileUtil.java
 * 文件描述: 附件文件操作工具类
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月26日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class AttachmentFileUtil {
	
	private static final Logger LOGGER = Logger.getLogger(ControllerUtil.class);
	
	/**
	 * 创建UUID
	 * @return
	 */
	public static AtomicReference<String> createUuid(){
		 String uuidString = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		 AtomicReference<String> uuid = new AtomicReference<String>(uuidString);
		 return uuid;
	}
	
	/**
	 * 检查上传文件格式是否正确
	 * @param file 文件
	 * @param formatArray  格式数组
	 * @return
	 */
	public static AtomicBoolean checkFileFormat(File file,String[] formatArray){
	    AtomicBoolean isTrue = new AtomicBoolean(false);
		try {
			//获取文件名称
			String fileName = file.getName();
			//文件后缀名
			String suffixName =  null;
			if(fileName != null  && fileName.lastIndexOf(".") != -1){
				suffixName = fileName.substring(fileName.lastIndexOf("."));
				isTrue.set(Arrays.asList(formatArray).contains(suffixName));
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("检查上传文件格式出现异常.");
		}
		return isTrue;
	}
	
	/**
	 * 获取文件后缀名称
	 * @param file 文件
	 * @return
	 */
	public static String getFileSuffixFormat(File file){
		try {
			//获取文件名称
			String fileName = file.getName();
			//文件后缀名
			String suffixName =  null;
			if(fileName != null  && fileName.lastIndexOf(".") != -1){
				suffixName = fileName.substring(fileName.lastIndexOf("."));
			}
			return suffixName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	
	/**
	 * byte(字节)根据长度转成kb(千字节)和mb(兆字节)
	 * 
	 * @param bytes
	 * @param type   MB:返回mb(兆字节)  KB：返回kb(千字节)
	 * @return
	 */
	public static float bytes2kb(long bytes,String type) {
		BigDecimal filesize = new BigDecimal(bytes);
		float returnValue = 0f;
		if(type.trim().equals("MB")){
			BigDecimal megabyte = new BigDecimal(1024 * 1024);
			returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
		}else{
			BigDecimal kilobyte = new BigDecimal(1024);
			returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
		}
		return returnValue;	
		
	}
	
	/**
	 * 文件上传
	 * @param request
	 * @param filePath    文件上传路径
	 * @param folderName  文件夹名称
	 * @return
	 */
	public static ConcurrentMap<String, Object> uploadFile(HttpServletRequest request,String filePath,String folderName){
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		//上传后的文件路径
		String newFilePath = null;
		AtomicBoolean isFolder =  new AtomicBoolean(false);
		//拼接访问文件的url
		StringBuffer absolutePath = new StringBuffer();
		absolutePath.append(Constants.ITEM_FILE_IP).append(Constants.SAVE_FILE_PATH);
		if(folderName != null && !folderName.trim().equals("")){
			absolutePath.append("/").append(folderName);
			isFolder.set(true);
		}
		String urlPathString = absolutePath.toString().replace("\\", "/");
		//存放上传后的文件名称
        CopyOnWriteArrayList<String> fileList = new CopyOnWriteArrayList<String>();
		try {
			//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	        	//转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() != ""){  
	                        //重命名上传后的文件名  
	                        String fileName = System.nanoTime()+"_"+file.getOriginalFilename();  
	                        if(isFolder.get()){
	                        	filePath = filePath+"\\"+folderName;
	                        }
	                        //创建文件夹
	                        File uploadFilePath = new File(filePath);
	                        if (!uploadFilePath.exists()){
	                        	uploadFilePath.mkdirs();
	                        }
	                        //定义上传路径  
	                        String path = filePath+"\\" + fileName;  
	                        File localFile = new File(path); 
							file.transferTo(localFile);
							newFilePath = path;
							fileList.add(urlPathString+"/" + fileName);
	                    }  
	                }   
	            }  
	        }
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("上传文件出现异常.");
		}  
		resultMap.put("newFilePath", newFilePath);
		resultMap.put("fileList", fileList);
        return resultMap;
	}
	
	
	
	/**
	 * 上传PPT并且转为图片保存到附件表中
	 * @param request
	 * @param filePath  文件上传路径
	 * @param folderName 文件夹名称
	 * @return
	 * @throws XmlException 
	 */
	@SuppressWarnings("unchecked")
	public static CopyOnWriteArrayList<TCoreAttachment> uploadPPTToImage(HttpServletRequest request,String filePath,String folderName) throws XmlException{
		CopyOnWriteArrayList<TCoreAttachment> attachmentList = new CopyOnWriteArrayList<TCoreAttachment>();
		AtomicBoolean isFolder =  new AtomicBoolean(false);
		//拼接访问文件的url
		StringBuffer absolutePath = new StringBuffer();
		absolutePath.append(Constants.ITEM_FILE_IP).append(Constants.SAVE_FILE_PATH);
		if(folderName != null && !folderName.trim().equals("")){
			absolutePath.append("/").append(folderName);
			isFolder.set(true);
		}
		String urlPathString = absolutePath.toString().replace("\\", "/");
		try {
			//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	        	//转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	                        TCoreAttachment attachment = new TCoreAttachment();
	                        //文件后缀名
	            			String suffixName =  null;
	            			if(myFileName.lastIndexOf(".") != -1){
	            				suffixName = myFileName.substring(myFileName.lastIndexOf("."));
	            			}
	            			if(suffixName.trim().equals(".ppt") || suffixName.trim().equals(".pptx")){
	            				 //上传后的文件名称
		                		String newFileNameString = null;
		                        //重命名上传后的文件名  
		                        String fileName = System.nanoTime()+"_"+file.getOriginalFilename();  
		                        if(isFolder.get()){
		                        	filePath = filePath+"\\"+folderName;
		                        }
		                        //创建文件夹	       
		                        File uploadFilePath = new File(filePath);
		                        if (!uploadFilePath.exists()){
		                        	uploadFilePath.mkdirs();
		                        }
		                        //定义上传路径  
		                        String path = filePath+"\\" + fileName;  
		                        File localFile = new File(path);  
								file.transferTo(localFile);
								newFileNameString = fileName;
								attachment.setId(createUuid().toString());
								attachment.setAttachType("1001");
								attachment.setAttachCode("1000");
								attachment.setAttachLevel(1);
								attachment.setAttachNewName(newFileNameString);
								attachment.setAttachOriginalName(myFileName);
								attachment.setBid("PPT");
								attachment.setAttachSuffix(suffixName);
								attachment.setAttachSize(bytes2kb(file.getSize(),"KB"));
								//获取当前项目访问地址
								attachment.setAbsolutePath(urlPathString+"/" + fileName);
								attachment.setAttachRelativePaths(path);
								attachment.setAbsoluteFolderPath(urlPathString);
								attachment.setIsCover("1002");
								attachment.setAttachStartus("1001");
								attachment.setAttributeOne("");
								attachment.setAttributeTwo("");
								attachment.setAttributeThree("");
								attachment.setCreateDate(DateTimeUtil.getCurrentDateTime());
								attachmentList.add(attachment);
								if(localFile.exists()){
									CopyOnWriteArrayList<TCoreAttachment> attachmentImageList = new CopyOnWriteArrayList<TCoreAttachment>();
									String imagePath = filePath+"\\ppttoimage";
									/* 上传的PPT文件转化为图片 */
									ConcurrentMap<String, Object> map = PPTParseUtil.pptToImage(localFile, imagePath);
									//是否转换图片成功
									AtomicBoolean success = (AtomicBoolean) map.get("success");
									if(success.get()){
										CopyOnWriteArrayList<String> imageArrayList = (CopyOnWriteArrayList<String>) map.get("list");
										Iterator<?> imageIterator = imageArrayList.iterator();
										while(imageIterator.hasNext()){
											 String iamgePathString = (String) imageIterator.next();
											 TCoreAttachment imageAttachment = new TCoreAttachment();
											 imageAttachment.setId(createUuid().toString());
											 imageAttachment.setAttachType("1002");
											 imageAttachment.setAttachCode("10000000");
											 imageAttachment.setAttachParent(attachment.getId());
											 imageAttachment.setAttachLevel(2);
											 imageAttachment.setAttachNewName(iamgePathString);
											 imageAttachment.setAttachOriginalName(iamgePathString);
											 imageAttachment.setBid("PPTTOIMAGE");
											 imageAttachment.setAttachSuffix(".jpeg");
											 imageAttachment.setAbsolutePath(urlPathString+"/ppttoimage/"+iamgePathString);
											 imageAttachment.setAttachRelativePaths(imagePath+"\\"+iamgePathString);
											 imageAttachment.setAbsoluteFolderPath(urlPathString);
											 imageAttachment.setIsCover("1002");
											 imageAttachment.setAttachStartus("1001");
											 imageAttachment.setAttributeOne("");
											 imageAttachment.setAttributeTwo("");
											 imageAttachment.setAttributeThree("");
											 imageAttachment.setCreateDate(DateTimeUtil.getCurrentDateTime());
											 attachmentImageList.add(imageAttachment);
										}
										attachmentList.addAll(attachmentImageList);
									}
								}
	            			}
	                    }  
	                }   
	            }  
	        }
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("上传文件出现异常.");
		}  
        return attachmentList;
	}
	
	/**
	 * 上传语音并且和图片绑定
	 * @param request
	 * @param paraMap
	 * @return 返回上传后文件路径
	 */
	@SuppressWarnings("unchecked")
	public static CopyOnWriteArrayList<String> uploadVoiceBoundImage(HttpServletRequest request,String filePath,String folderName){
		try {
			//上传文件
			ConcurrentMap<String, Object> uploadVoiceMap = uploadFile(request, filePath, folderName);
			//得到上传后的文件路径
			return (CopyOnWriteArrayList<String>) uploadVoiceMap.get("fileList");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("上传语音文件出现异常.");
			return null;
		}
	
	}
	
	
	
	
	/**
	 * 批量删除磁盘上的文件
	 * @param list
	 */
	public static void deleteDiskFileAll(CopyOnWriteArrayList<TCoreAttachment> list) throws IOException{
		for (TCoreAttachment tCoreAttachment : list) {
			deleteDiskFile(tCoreAttachment.getAttachRelativePaths());
		}
	}
	
	/**
	 * 删除磁盘上单个文件
	 * @param relativePath  文件路径
	 */
	public static void deleteDiskFile(String relativePath){
		File file = new File(relativePath);    
        if(file.isFile() && file.exists()){    
            file.delete();         
        }   
	}
	
	/**
	 * 删除文件，可以是单个文件或文件夹  
	 * @param relativePath 文件路径
	 */
	public static void deleteDisk(String relativePath){
		File file = new File(relativePath);    
        if(!file.exists()){    
        }else{    
            if(file.isFile()){     
            	deleteDiskFile(relativePath);    
            }else{    
                deleteDirectory(relativePath);    
            }    
        }    
	}
	
	/**
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param dir   被删除目录的文件路径   
	 */
	public static void deleteDirectory(String dir){
		//如果dir不以文件分隔符结尾，自动添加文件分隔符  
		if(!dir.endsWith(File.separator)){
		    dir = dir+File.separator;
		}
		File dirFile = new File(dir);
		//如果dir对应的文件不存在，或者不是一个目录，则退出 
		if(!dirFile.exists() || !dirFile.isDirectory()){
			//目录不存在
		}else{
			File[] files = dirFile.listFiles();
			for(int i = 0; i < files.length; i++){
				if(files[i].isFile()){
					//删除子文件
					deleteDiskFile(files[i].getAbsolutePath());
				}else{
					dirFile.delete();
					//删除子目录
					deleteDirectory(files[i].getAbsolutePath());
				}
			}
		}
	}
	
	/**
	 * 删除文件夹
	 * @param folderPath
	 */
	public static void delFolder(String folderPath){
		try {
			delAllFile(folderPath);
			File myFilePath = new File(folderPath);
			myFilePath.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除指定文件夹下所有文件
	 * @param path
	 */
	@SuppressWarnings("static-access")
	public static  void delAllFile(String path){
		try {
			File file =  new File(path);
			if(file.exists()){
				if(file.isDirectory()){
					String[] tempList = file.list();
					File temp = null;
					for(int i = 0; i < tempList.length; i++){
						if(path.endsWith(file.separator)){
							temp = new File(path+tempList[i]);
						}else{
							temp = new File(path+file.separator+tempList[i]);
						}
						if(temp.isFile()){
							temp.delete();
						}
						if(temp.isDirectory()){
							delAllFile(path+"/"+tempList[i]);
							delFolder(path+"/"+tempList[i]);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] ars){
		
		String filePath = "E:\\apache-tomcat-8.0.37\\webapps\\DurisdictionManage\\resources\\upload\\ppt\\20160927\\ppttoimage";
		delFolder(filePath);
		
		
	}

}
