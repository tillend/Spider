package com.lin.test;

import org.junit.Test;

import com.lin.utils.Date4SearchUrlUtil;

public class DateTest {
	
	@Test
	public void test() {
		String[] strings = Date4SearchUrlUtil.getUrls();
		System.out.println(strings[0]);
	}

}
