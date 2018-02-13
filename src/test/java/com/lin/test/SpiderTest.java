package com.lin.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.lin.controller.crawler.SeasonInfoCrawler;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.SearchUrlUtil;

public class SpiderTest {
	
	@Test
	public void test4SeasonInfo() {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final SeasonInfoCrawler jobCrawler = applicationContext.getBean(SeasonInfoCrawler.class);
		
		//从数据库中获取页面id列表,拼接为有效的url
		String[] strings = new String[] {String.format(SearchUrlUtil.url4seasonInfo, 3461)};
		
		//启动爬虫
		jobCrawler.crawl(strings);
	}

}
