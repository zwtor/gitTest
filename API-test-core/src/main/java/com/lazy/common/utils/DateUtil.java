package com.lazy.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	// 根据Date类型获取指定格式的日期
	public static String getRegularDate(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	// 获取指定日期
	@SuppressWarnings("static-access")
	public static String getRegularDate(Integer days) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}
	@SuppressWarnings("static-access")
	public static String getRegularDateForYYDDMM(Integer days) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}
	
	@SuppressWarnings("static-access")
	public static String getRegularDateForHHMM(Integer days) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = formatter.format(date);
		return dateString;
	}
	
	@SuppressWarnings("static-access")
	public static String getRegularDateForHHMMSS(Integer days) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}
	
	/**   
	 * @Title: getOneHourLaterStamp   
	 * @Description: TODO(获取一小时后的时间戳)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getOneHourLaterStamp() {
		long currentTime = System.currentTimeMillis() ;
		currentTime +=60*60*1000;
		Date date=new Date(currentTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String LastDate = dateFormat.format(date);
		String res;// 设置时间格式，将该时间格式的时间转换为时间戳
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		try {
			date1 = simpleDateFormat.parse(LastDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = date1.getTime();
		res = String.valueOf(time);
		return res;
	}

	// 获取指定日期的时间戳
	public static String getTimeStamp(Integer days, String s)   {
		String LastDate = getRegularDate(days) + " " + s;

		String res;// 设置时间格式，将该时间格式的时间转换为时间戳
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(LastDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = date.getTime();
		res = String.valueOf(time);
		return res;
	}
	// 获取当前时间
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(System.currentTimeMillis());
		return time;

	}
	
	public static String getDateTimeStamp(String time) {
	    Long timeLong = Long.parseLong(time);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//要转换的时间格式
	    Date date;
	    try {
	        date = sdf.parse(sdf.format(timeLong));
	        return sdf.format(date);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	} 
	
	

}
