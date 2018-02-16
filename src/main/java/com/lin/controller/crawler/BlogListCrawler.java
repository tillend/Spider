package com.lin.controller.crawler;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.service.BlogListPageProcessor;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.SearchUrlUtil;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.component.HashSetDuplicateRemover;

@Component
public class BlogListCrawler implements AbstractCrawler {
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void crawl(String[] strings) {
		Spider.create(new BlogListPageProcessor())
			.addUrl(strings)
			.thread(1)
			.addPipeline(new ConsolePipeline())
			.setScheduler(new QueueScheduler())
			.run();
	}
	
	@Override
	public String[] buildUrl() {
		String[] strings = new String[] {SearchUrlUtil.getUrlbyId(SearchUrlUtil.url4blogList, "why_still_confused")};
		return strings;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final BlogListCrawler jobCrawler = applicationContext.getBean(BlogListCrawler.class);

		String[] strings = jobCrawler.buildUrl();
		
		jobCrawler.crawl(strings);

	}

}