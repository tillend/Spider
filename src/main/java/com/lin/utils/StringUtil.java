package com.lin.utils;

public class StringUtil {
	
	public static Long string2long(String s) {
		try {
			return Long.parseLong(s);
		} catch(NumberFormatException e) {
			return 0l;
		}
	}
	
	public static int string2int(String s) {
		try {
			return Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * 获取最后“/”后面的内容 
	 * @param fullPath
	 * @return
	 */
	public static String getLastSlantContent(String fullPath) {
		int pos = fullPath.lastIndexOf("/");
		if (pos != -1) {
			return fullPath.substring(pos + 1);
		} else {
			return null;
		}
	}

}
