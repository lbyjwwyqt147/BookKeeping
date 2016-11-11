package pers.liujunyi.bookkeeping.util.impl;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import pers.liujunyi.bookkeeping.util.Constants;
import pers.liujunyi.bookkeeping.util.IServiceUtil;

/***
 * 文件名称: ServiceUtilImpl.java
 * 文件描述: service工具类接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月20日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */

@Service
public class ServiceUtilImpl implements IServiceUtil {

    private static final Logger LOGGER  = Logger.getLogger(ServiceUtilImpl.class);
	
	@Override
	public String getZTreeItemJSON(String id, String pid, String name,
			String open, String checked, String title, String url,
			String isParent, String target, String icon, String iconOpen,
			String iconClose, String isHidden, String nocheck, String iconSkin,
			HttpServletRequest request, String... str) {
		
	        try {
	        	StringBuffer item = new StringBuffer();
				item.append("{ \"id\": \"").append(id).append("\", \"pId\": \"").append(pid).append("\", \"name\": \"").append(name).append("\", \"isParent\": ").append(isParent);
				item.append(",\"open\":").append(open).append(",\"checked\":").append(checked).append(",\"title\":\"").append(title).append("\",\"url\":\"").append(url).append("\"");
				item.append(",\"icon\":\"").append(icon).append("\",\"iconOpen\":\"").append(iconOpen).append("\",\"iconClose\":\"").append(iconClose).append("\",\"isHidden\":").append(isHidden);
				item.append(",\"nocheck\":").append(nocheck).append(",\"iconSkin\":\"").append(iconSkin).append("\"");
				return item.toString();
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("封装Ztree树结构出现异常.");
			}
			return null;
	}

	@Override
	public String getNewCode(String historyDictCode,String parentCode) {
		String newDictCode =  null;
		try {
			if(historyDictCode != null && !parentCode.trim().equals(Constants.PAERNT)){
				//获取最后四位值
				AtomicInteger lastFour = new AtomicInteger(Integer.valueOf(historyDictCode.substring(historyDictCode.length()-4)));
				//对数据进行+1计算
				newDictCode = historyDictCode.substring(0,historyDictCode.length()-4)+lastFour.addAndGet(1);
			}else if(historyDictCode == null && !parentCode.trim().equals(Constants.PAERNT)){
				newDictCode = parentCode+"1001";
			}else if(historyDictCode != null && parentCode.trim().equals(Constants.PAERNT)){
				//获取最后四位值
				AtomicInteger lastFour = new AtomicInteger(Integer.valueOf(historyDictCode.substring(historyDictCode.length()-4)));
				//对数据进行+1计算
				newDictCode = String.valueOf(lastFour.addAndGet(1));
			}else if(historyDictCode == null && parentCode.trim().equals(Constants.PAERNT)){
				newDictCode = "1001";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取最新编号出现异常.");
		}
		return newDictCode;
	}

	@Override
	public String[] getMemberSession(HttpServletRequest request) {
		return (String[]) request.getSession().getAttribute(Constants.USER_SESSION);
	}

	@Override
	public String getUploadRealpath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath(Constants.SAVE_FILE_PATH);
	}

	@Override
	public String getItemIp(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
