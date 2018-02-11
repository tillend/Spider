package com.lin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	//默认样式
	public static Date string2Date(String dateString) {
		return string2Date(dateString, "yyyy-MM-dd HH:mm:ss");
	}

	private static Date string2Date(String dateString, String pattern) {
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

}
