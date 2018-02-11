package com.lin.test;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.lin.utils.Date4SearchUrlUtil;
import com.lin.utils.DateUtil;

public class DateTest {
	@Ignore
	@Test
	public void test() {
		String[] strings = Date4SearchUrlUtil.getUrls();
		System.out.println(strings[0]);
	}
	
	@Test
	public void string2DateTest() {
		Date date = DateUtil.string2Date("2018-01-31 18:23:54");
		System.out.println(date);
	}

}
