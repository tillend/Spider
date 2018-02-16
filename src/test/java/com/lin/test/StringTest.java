package com.lin.test;

import org.junit.Test;

import com.lin.utils.SearchUrlUtil;
import com.lin.utils.StringUtil;

public class StringTest {
	@Test
	public void getIdfromUrlTest() {	
		String url = SearchUrlUtil.getUrlbyId(SearchUrlUtil.url4blogList, "why_still_confused");
		String id = StringUtil.getIdfromUrl(url);
		System.out.println(id);
	}

}
