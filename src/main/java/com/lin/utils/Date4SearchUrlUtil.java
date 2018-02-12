package com.lin.utils;

import java.util.ArrayList;
import java.util.List;

public class Date4SearchUrlUtil {
	
	final static int startYear = 2009;
	final static int endYear = 2018;
	final static int startMonth = 7;
	final static int endMonth = 3;
	final static int monthInterval = 3;
	
	static String originUrl = "https://s.search.bilibili.com/cate/search?"
			+ "main_ver=v3&search_type=video&view_type=hot_rank"
			+ "&cate_id=32&page=1&pagesize=20&jsonp=jsonp"
			+ "&time_from=%s&time_to=%s";
	
	public static String[] getUrls() {
		return getUrls(startYear, endYear);
	}
	
	public static String[] getUrls(int startYear, int endYear) {
		List<String> list = new ArrayList<>();
		
		int year = startYear;
		int sMonth = startMonth;
		int eMonth = getInternalMonth(sMonth);
		String sDate = null, eDate = null;
				
		while(year >= startYear && (year <= endYear || eMonth < endMonth)) {
			//起始日期
			sDate = (null == sDate)? getDateFormat(year, sMonth) : eDate;
			
			//结束日期
			if((eMonth + 3) / 12 == 1) {
				year += 1;
			}
			eMonth = getInternalMonth(eMonth);
			
			eDate = getDateFormat(year, eMonth);
			
			//将拼接后url加入至列表中
			list.add(String.format(originUrl, sDate, eDate));
		}
		
		String[] temp = new String[list.size()];
		return list.toArray(temp);
	}

	
	public static int getInternalMonth(int month) {
		return (month + monthInterval) % 12;
	}
	
	public static String getDateFormat(int year, int month) {
		return String.format("%d%02d01", year, month);
//		return String.format("%d", year) + String.format("%02d", month) + "01";
	}
}
