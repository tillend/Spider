package com.lin.utils;

import java.util.ArrayList;
import java.util.List;

public class SearchUrlUtil {
	
	public static String url4bangumi = "https://bangumi.bilibili.com/web_api/season/index_global?"
			+ "page_size=20&version=0"
			+ "&is_finish=0&start_year=0&tag_id="
			+ "&index_type=1&index_sort=0&quarter=0"
			+ "&page=%d";
	
	public static String url4seasonInfo = "https://bangumi.bilibili.com/jsonp/seasoninfo/%d.ver?jsonp=jsonp";
	
	/**
	 * 已知需爬取页数时的url拼接
	 * @param originUrl
	 * @param pageNum
	 * @return
	 */
	public static String[] getUrls(String originUrl, int pageNum) {
		List<String> list = new ArrayList<>();
		
		for(int i = 1; i <= pageNum; i++) {
			String url = String.format(originUrl, i);
			list.add(url);
		}
		
		String[] temp = new String[list.size()];
		return list.toArray(temp);
	}
	
	/**
	 * 已知特定id时的url拼接
	 * @param originUrl
	 * @param pageNum
	 * @return
	 */
	public static String getUrlbyId(String originUrl, int id) {
		return String.format(originUrl, id);
	}
	
	
}
