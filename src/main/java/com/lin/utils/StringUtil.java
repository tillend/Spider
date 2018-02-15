package com.lin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	// 获取一个字符串中的数字部分，剔除掉不是数字的
	public static String getStringPureNumber(String str) {
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(str);
		if (matcher.replaceAll("").equals(""))
			return -1 + "";
		return matcher.replaceAll("");
	}

}
