package com.lin.controller.crawler;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.service.BlogExpertPageProcessor;
import com.lin.service.pipeline.BangumiListDaoPipeline;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.SearchUrlUtil;
import com.lin.utils.type.PageNum;

import us.codecraft.webmagic.Spider;

@Component
public class BlogExpertCrawler implements AbstractCrawler {
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void crawl(String[] strings) {
		Spider.create(new BlogExpertPageProcessor())
			.addUrl(strings)
			.thread(1)
			.addPipeline(null)
			.run();
	}
	
	@Override
	public String[] buildUrl() {
		String[] strings = new String[] {SearchUrlUtil.url4blogExpert};
		return strings;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final BlogExpertCrawler jobCrawler = applicationContext.getBean(BlogExpertCrawler.class);

		String[] strings = jobCrawler.buildUrl();
		
		jobCrawler.crawl(strings);

	}

}