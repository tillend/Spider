package com.lin.utils;

public class StringUtil {
	
	public static Long long2String(String s) {
		try {
			return Long.parseLong(s);
		} catch(NumberFormatException e) {
			return 0l;
		}
	}

}
