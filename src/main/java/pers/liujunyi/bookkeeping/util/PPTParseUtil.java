package pers.liujunyi.bookkeeping.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger; 
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.presentationml.x2006.main.*;


/***
 * 文件名称: PPTParseUtil.java
 * 文件描述: PPT转为图片、html工具类
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月26日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class PPTParseUtil {
	
	private static final Logger LOGGER = Logger.getLogger(ControllerUtil.class);
	
	/**
     * 将上传的PPT文件转为图片
     * @param file  PPT文件
     * @param newPath  图片存放路径
     * @return
	 * @throws XmlException 
     */
	public static ConcurrentMap<String, Object> pptToImage(File file,String newPath) throws XmlException{
		//文件后缀名
		String suffixName =  AttachmentFileUtil.getFileSuffixFormat(file);
		if(suffixName.trim().equals(".ppt")){
			return ppt2003ToImage(file,newPath);
		}else{
			return ppt2007ToImage(file,newPath);
		}
		
	}
	
    /**
     * 将上传的2003版本PPT文件转为图片
     * @param file  PPT文件
     * @param newPath  图片存放路径
     * @return
     */
	public static ConcurrentMap<String, Object> ppt2003ToImage(File file,String newPath){
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean  success =  new AtomicBoolean(false);
		String messageString = "2003版本PPT转换为图片格式失败.";
		try {
			FileInputStream is = new FileInputStream(file);
			SlideShow ppt = new SlideShow(is) ;
			is.close();
			Dimension pageSize = ppt.getPageSize();
			Slide[] slides = ppt.getSlides();
			CopyOnWriteArrayList<String> al = new CopyOnWriteArrayList<String>();
			LOGGER.info("2003版本ppt正在转换为图片中.................");
			for(int i = 0; i < slides.length; i++){
				//设置字体 以防乱码
				TextRun[] truns  = slides[i].getTextRuns();
				for(int j = 0; j < truns.length; j++){
					RichTextRun[] rtruns = truns[j].getRichTextRuns();
					for (int l = 0; l < rtruns.length; l++) {
					      rtruns[l].setFontIndex(1);
					      rtruns[l].setFontName("宋体");
					}
				}
				BufferedImage img = new BufferedImage(pageSize.width,pageSize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = img.createGraphics();
				graphics.setPaint(Color.white);
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width,pageSize.height));
				slides[i].draw(graphics);
				/*img.getGraphics().drawImage(  
						img.getScaledInstance(pageSize.width, pageSize.height,  
		                        Image.SCALE_SMOOTH), 0, 0, null);*/
				 // 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
			    File f=new File(newPath);
			    //判断该文件夹是否存在，如果不存在，这创建一个新的文件夹
			    if(!f.isDirectory()){
			      f.mkdirs();
			    }
			    String pictName = "pict_"+System.nanoTime()+(i + 1)+".jpeg";
			    FileOutputStream out = new FileOutputStream(newPath+"\\" +pictName);
			    ImageIO.write(img, "jpeg", out);
			    al.add(pictName);
			    out.close();
			}
			LOGGER.info("2003版本ppt转换为图片完毕.");
			resultMap.put("list", al);
			success.set(true);
			
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("2003版本PPT转换为图片格式出现异常.");
		}
		resultMap.put("success", success);
		resultMap.put("message", messageString);
		return resultMap;
	}
	
	/**
     * 将上传的2007版本PPT文件转为图片
     * @param file  PPT文件
     * @param newPath  图片存放路径
     * @return
	 * @throws XmlException 
     */
	@SuppressWarnings("deprecation")
	public static ConcurrentMap<String, Object> ppt2007ToImage(File file,String newPath) throws XmlException{
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		AtomicBoolean  success =  new AtomicBoolean(false);
		String messageString = "2007版本PPT转换为图片格式失败.";
		try {
			FileInputStream is = new FileInputStream(file);
			XMLSlideShow ppt = new XMLSlideShow(is);
			is.close();
			Dimension pageSize = ppt.getPageSize();
			XSLFSlide[] slides = ppt.getSlides();
			CopyOnWriteArrayList<String> al = new CopyOnWriteArrayList<String>();
			LOGGER.info("2007版本ppt正在转换为图片中.................");
			for(int i = 0; i < slides.length; i++){
				XSLFSlide slide = slides[i];
				 //设置字体为宋体，解决中文乱码问题
	            CTSlide rawSlide=slide.getXmlObject();
	            CTGroupShape gs = rawSlide.getCSld().getSpTree();
	            CTShape[] shapes = gs.getSpArray();
	            for (CTShape shape : shapes) {
	                CTTextBody tb = shape.getTxBody();
	                if (null == tb)
	                    continue;
	                CTTextParagraph[] paras = tb.getPArray();
	                CTTextFont font=CTTextFont.Factory.parse(
	                        "<xml-fragment xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:p=\"http://schemas.openxmlformats.org/presentationml/2006/main\">"+
	                        "<a:rPr lang=\"zh-CN\" altLang=\"en-US\" dirty=\"0\" smtClean=\"0\"> "+
	                            "<a:latin typeface=\"+mj-ea\"/> "+
	                          "</a:rPr>"+
	                        "</xml-fragment>");
	                for (CTTextParagraph textParagraph : paras) {
	                    CTRegularTextRun[] textRuns = textParagraph.getRArray();
	                    for (CTRegularTextRun textRun : textRuns) {
	                        CTTextCharacterProperties properties=textRun.getRPr();
	                        properties.setLatin(font);
	                    }
	                }
	            }
			
				BufferedImage img = new BufferedImage(pageSize.width,pageSize.height, BufferedImage.TYPE_INT_RGB);
				  
				Graphics2D graphics = img.createGraphics();
				graphics.setPaint(Color.white);
				//抗锯齿
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width,pageSize.height));
				slides[i].draw(graphics);
				/*img.getGraphics().drawImage(  
				img.getScaledInstance(pageSize.width, pageSize.height,  
                        Image.SCALE_SMOOTH), 0, 0, null);*/
				//这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
			    File f=new File(newPath);
			    //判断该文件夹是否存在，如果不存在，这创建一个新的文件夹
			    if(!f.isDirectory()){
			      f.mkdirs();
			    }
			    String pictName = "pict_"+System.nanoTime()+(i + 1)+".jpeg";
			    FileOutputStream out = new FileOutputStream(newPath+"\\" +pictName);
			    ImageIO.write(img, "jpeg", out);
			    al.add(pictName);
			    out.close();
			}
			LOGGER.info("2007版本ppt转换为图片完毕.");
			resultMap.put("list", al);
			success.set(true);
			
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("2007版本PPT转换为图片格式出现异常.");
		}
		resultMap.put("success", success);
		resultMap.put("message", messageString);
		return resultMap;
	}
	
	public static void main(String[] ars) throws XmlException{
		
		String pptFilePath = "C:\\Users\\ljy\\Desktop\\“师道” 老师手机端APP 商业计划书 .pptx";
		String imgFilePath = "G:\\ppt_image";
		File file =  new File(pptFilePath);
		ppt2007ToImage(file, imgFilePath);
		
	}

}
