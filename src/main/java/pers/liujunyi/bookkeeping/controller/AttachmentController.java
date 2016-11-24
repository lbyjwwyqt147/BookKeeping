package pers.liujunyi.bookkeeping.controller;



import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pers.liujunyi.bookkeeping.entity.TCoreAttachment;
import pers.liujunyi.bookkeeping.service.IAttachmentService;
import pers.liujunyi.bookkeeping.util.AttachmentFileUtil;
import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.ControllerUtil;
import pers.liujunyi.bookkeeping.util.DateTimeUtil;

@Controller
@RequestMapping("/bookkeeping/attachment")
public class AttachmentController {
	
	@Autowired
	private IAttachmentService attachmentService;
	
	@RequestMapping(value="initUpload")
	public ModelAndView initUpload(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("business/user/ppt_upload");
	}
	
	
	/**
	 * 上传文件
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="uploadFile")
	public void uploadFile(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			//获取upload文件夹得真实路径  
		    String realpath = request.getSession().getServletContext().getRealPath(Constants.SAVE_FILE_PATH); 
		    ConcurrentMap<String, Object> resultMap =  AttachmentFileUtil.uploadFile(request, realpath,null);
		    ControllerUtil.writeJavaScript(response, resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 上传PPT文件并且转换为图片保存到附件表中
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="uploadPPTFileToImage")
	public void uploadPPTFileToImage(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success =  new AtomicBoolean(false);
		String messageString = "PPT转换图片失败.";
		try {
			//获取upload文件夹得真实路径  
		    String realpath = request.getSession().getServletContext().getRealPath(Constants.SAVE_FILE_PATH); 
		    //创建自定义文件夹路径
		    StringBuffer folderName = new StringBuffer("ppt");
		    folderName.append("\\").append(DateTimeUtil.getCurrentDateTime("yyyyMMdd"));
		    CopyOnWriteArrayList<TCoreAttachment> list = AttachmentFileUtil.uploadPPTToImage(request, realpath,folderName.toString());
		    if(list.isEmpty()){
		    	messageString = "文件格式必须是ppt文件.";
		    }else{
		    	 int count = attachmentService.addAttachmentList(list);
			     if(count > 0){
			    	success.set(true);
			    	messageString = "PPT转换图片完成.";
			    	resultMap.put("list", list);
			     }else{
			    	 //入库失败后删除上传的文件
			    	AttachmentFileUtil.deleteDiskFileAll(list);
			     }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("success", success.get());
		resultMap.put("message", messageString);
		ControllerUtil.writeJavaScript(response, resultMap);
	}
	
	/**
	 * 上传语音文件同时和ppt图片绑定
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="uploadVoiceBoundImage")
	public void uploadVoiceBoundImage(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success =  new AtomicBoolean(false);
		String messageString = "上传语音文件失败.";
		try {
			ConcurrentMap<String, Object> paramsMap = ControllerUtil.getFormData(request);
			//图片ID
			String imageId = paramsMap.get("imageId").toString().trim();
			//语音时长
			Float voiceLength = Float.valueOf(paramsMap.get("voiceLength") != null && paramsMap.get("voiceLength").toString().trim().equals("")? paramsMap.get("voiceLength").toString().trim() : "5");
			//语音文件路径
			String voiceUrl = null;
			//获取upload文件夹得真实路径  
		    String realpath = request.getSession().getServletContext().getRealPath(Constants.SAVE_FILE_PATH); 
		    //创建自定义文件夹路径
		    StringBuffer folderName = new StringBuffer("voice");
		    folderName.append("\\").append(DateTimeUtil.getCurrentDateTime("yyyyMMdd"));
			CopyOnWriteArrayList<String> voiceUrlList = AttachmentFileUtil.uploadVoiceBoundImage(request, realpath, folderName.toString());
            if(!voiceUrlList.isEmpty()){
            	Iterator<?> iterator = voiceUrlList.iterator();
            	while(iterator.hasNext()){
            		voiceUrl  =  (String) iterator.next();
            	}
            	//修改数据
            	/*int count = attachmentService.updateAttachmentVoiceUrl(imageId, voiceUrl,voiceLength);
            	if(count > 0){
            		messageString = "上传语音文件成功.";
            		success.set(true);
            	}*/
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("success", success.get());
		resultMap.put("message", messageString);
		ControllerUtil.writeJavaScript(response, resultMap);
	}
	
	
	/**
	 * 查询附件列表集合
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="findAttachmentList")
	public void findAttachmentList(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean success =  new AtomicBoolean(false);
		String messageString = "查询数据失败.";
		try {
			ConcurrentMap<String, Object> paramsMap = ControllerUtil.getFormData(request);
			CopyOnWriteArrayList<TCoreAttachment> list = attachmentService.findAttachmentList(paramsMap);
			if(!list.isEmpty()){
				messageString = "查询数据成功.";
				success.set(true);
				resultMap.put("list", list);
			}else{
				messageString = "查询无数据.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("success", success.get());
		resultMap.put("message", messageString);
		ControllerUtil.writeJavaScript(response, resultMap);
	}

}
