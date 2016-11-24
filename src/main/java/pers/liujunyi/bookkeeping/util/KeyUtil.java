package pers.liujunyi.bookkeeping.util;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/***
 * 文件名称: KeyUtil.java
 * 文件描述: 生成编号 工具类
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月26日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class KeyUtil {

	/**
	 * 生成随机数
	 * @param number 随机数范围  比如  10  100  1000   
	 * @return  返回随机数
	 */
	public static int randomNumber(int number){
		Random random = new Random();
		String numString = String.valueOf(number);
		StringBuffer digitBuffer =  new StringBuffer("1");
		for(int i = 0;i < numString.length()-1; i++ ){
			digitBuffer.append("0");
		}
		AtomicInteger randomNumber =  new AtomicInteger(random.nextInt(number));
		return randomNumber.addAndGet(Integer.valueOf(digitBuffer.toString()));
	}
	
	/**
	 * 生成时间戳随机数
	 * @param digit 位数   
	 * @return  返回多少位的随机数
	 */
	public static int randomTime(int digit){
		AtomicLong nanotime = new AtomicLong(System.nanoTime());
		Random random = new Random();
		AtomicInteger randomNumber =  new AtomicInteger(random.nextInt(100));
		String nanotimeString =  String.valueOf(nanotime.get());
		return randomNumber.addAndGet(Integer.valueOf(nanotimeString.substring(nanotimeString.length()-digit)));
	}
	
	public static void main(String[] sts){
		System.out.println(randomTime(10));
	}
	
}
