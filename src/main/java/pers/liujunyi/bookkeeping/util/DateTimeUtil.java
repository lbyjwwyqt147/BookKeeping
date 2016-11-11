package pers.liujunyi.bookkeeping.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReferenceArray;

import com.google.gson.Gson;

/***
 * 文件名称: DateTimeUtil.java
 * 文件描述: 时间日期转换工具类
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月20日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class DateTimeUtil {
    
	/* 输出格式为 2016-09-18 14:33:30 */
	private static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	/* 输出格式为2016-09-18 */
	private static final String yyyyMMdd = "yyyy-MM-dd";
	/* 输出格式为2016.09.18 */
	public static final String YYYYMMDD = "yyyy.MM.dd";
	/* 输出格式为201609181810 */
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	/* 输出格式为2016年09月18日 */
	public static final String CHINESEYYYYMMdd = "yyyy年MM月dd日";
	
	/**
	 * 获取当前系统时间 格式为：2016-09-18 14:33:30
	 * @return 
	 */
	public static String getCurrentDateTime(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(yyyyMMddHHmmss);
	    return 	dateFormat.format(new Date());
	}
	
	/**
	 * 获取今天的日期 格式为：2016-09-18
	 * @return
	 */
	public static String getTodayDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(yyyyMMdd);
	    return 	dateFormat.format(new Date());
	}
	
	/**
	 * 根据输出格式返回不同格式的日期时间
	 * @param pattern 输出格式
	 * @return
	 */
	public static String getCurrentDateTime(String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	    return 	dateFormat.format(new Date());
	}
	
	/**
	 * 将日期时间字符串转换为日期格式
	 * @param dateString 日期字符串
	 * @param pattern  转换格式
	 * @return
	 */
	public static Date getFormatDateTime(String dateString, String pattern){
		Date dateTimeDate = null;
	    try {
	    	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	    	dateTimeDate =	dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return dateTimeDate;
	}
	
	/**
	 * 获取当前日期的前几天日期
	 * @param day 天数
	 * @return
	 */
	public static AtomicReferenceArray<String> getRecentlyDateArray(int day){
		try {
			AtomicReferenceArray<String> dateArray = new AtomicReferenceArray<String>(day);
			SimpleDateFormat dateFormat = new SimpleDateFormat(yyyyMMdd);
			Calendar calendar = Calendar.getInstance();
			int inputDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
			int j = 0;
			for(int i = day-1; i >= 0;i--){
				calendar.set(Calendar.DAY_OF_YEAR, inputDayOfYear-i);
				dateArray.set(j, dateFormat.format(calendar.getTime()));
				j++;
			}
		    return dateArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取年份中某月的日期
	 * @param year  年份
	 * @param month 月份
	 * @return
	 */
	public static AtomicReferenceArray<String> getMonthDayArray(int year,int month){
		try {
			Calendar calendar = Calendar.getInstance(Locale.CHINA);
			/* 设置年 */
			calendar.set(Calendar.YEAR,year);
			/* 设置月 */
			calendar.set(Calendar.MONTH,month-1);
			/* 获取月份的最大天数 */
			int currentMaxDays = calendar.getActualMaximum(Calendar.DATE);
			String monthString = month > 9 ? String.valueOf(month) : "0"+month;
			AtomicReferenceArray<String> dateArray =  new AtomicReferenceArray<String>(currentMaxDays);
		    int j = 0;
			for(int i = 1; i < currentMaxDays + 1; i++) {
				/* 拼接日期格式字符串 */
				StringBuffer yearMonthBuffer = new StringBuffer(); 
				yearMonthBuffer.append(year).append("-").append(monthString);
				String dayString = i > 9 ? String.valueOf(i) : "0"+i;
				yearMonthBuffer.append("-").append(dayString);
				dateArray.set(j, yearMonthBuffer.toString());
				j++;
			}
			return dateArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] strs){
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(getRecentlyDateArray(10)));
		System.out.println(gson.toJson(getMonthDayArray(2016,9)));
	}
	
}

