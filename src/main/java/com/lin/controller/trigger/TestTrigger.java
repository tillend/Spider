package com.lin.controller.trigger;

import org.springframework.context.ApplicationContext;

import com.lin.controller.crawler.BlogListCrawler;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.SearchUrlUtil;

public class TestTrigger {
	
	public void start() {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final BlogListCrawler jobCrawler = applicationContext.getBean(BlogListCrawler.class);

//		String[] strings = jobCrawler.buildUrl();
		String[] strings = new String[] {String.format(SearchUrlUtil.url4blogList, "112")};
		
		jobCrawler.crawl(strings);
	}

}
