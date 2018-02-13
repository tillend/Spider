package com.lin.utils;

public class JsonpUtil {

	/**
	 * 去除掉function名及(), eg. function({jsonString}) -> jsonString
	 * 
	 * @param jsonpString
	 * @param function
	 * @return
	 */
	public static String jsonp2jsonString(String jsonpString, String function) {
		return jsonpString.substring(function.length() + 1, jsonpString.length() - 2);
	}

}
