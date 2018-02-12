package com.lin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String defaultPattern =  "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 模式字符串转换为java.util.Date
	 * @param dateString
	 * @return
	 */
	public static Date string2Date(String dateString) {
		return string2Date(dateString, defaultPattern);
	}

	public static Date string2Date(String dateString, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);     
		
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
		
	}

	/**
	 * unixtime(毫秒数)转换为java.util.Date
	 * @param time
	 * @return
	 */
	public static Date unixtime2Date(Long time) {
		return unixtime2Date(time, defaultPattern);
	}

	public static Date unixtime2Date(Long time, String pattern) {
		return new Date(time);
	}
	
	/**
	 * unixtime(毫秒数)转换为时间串,eg. yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return
	 */
	public static String unixtime2FormatString(Long time) {
		return unixtime2FormatString(time, defaultPattern);
	}
	
	public static String unixtime2FormatString(Long time, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);	
		Date date = new Date(time);
		
		return format.format(date);
	}

}
