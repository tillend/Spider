package com.lin.controller.crawler;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.service.BlogExpertPageProcessor;
import com.lin.service.pipeline.BlogExpertListDaoPipeline;
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
			.thread(4)
			.addPipeline(new BlogExpertListDaoPipeline())
			.run();
	}
	
	@Override
	public String[] buildUrl() {
		String[] strings = SearchUrlUtil.getUrls(SearchUrlUtil.url4blogExpert, PageNum.BLOG_EXPERT.getPageNum());
		return strings;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final BlogExpertCrawler jobCrawler = applicationContext.getBean(BlogExpertCrawler.class);

		String[] strings = jobCrawler.buildUrl();
		
		jobCrawler.crawl(strings);

	}

}