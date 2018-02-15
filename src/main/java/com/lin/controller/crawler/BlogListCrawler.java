package com.lin.controller.crawler;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.service.BlogListPageProcessor;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.SearchUrlUtil;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

@Component
public class BlogListCrawler implements AbstractCrawler {
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void crawl(String[] strings) {
		Spider.create(new BlogListPageProcessor())
			.addUrl(strings)
			.thread(1)
			.addPipeline(new ConsolePipeline())
			.run();
	}
	
	@Override
	public String[] buildUrl() {
		String[] strings = new String[] {SearchUrlUtil.getUrlbyId(SearchUrlUtil.url4blogList, "wgyscsf")};
		return strings;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final BlogListCrawler jobCrawler = applicationContext.getBean(BlogListCrawler.class);

		String[] strings = jobCrawler.buildUrl();
		
		jobCrawler.crawl(strings);

	}

}