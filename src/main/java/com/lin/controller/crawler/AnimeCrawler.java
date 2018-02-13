package com.lin.controller.crawler;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.service.AnimeAPIPageProcessor;
import com.lin.service.pipeline.AnimeListDaoPipeline;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.Date4SearchUrlUtil;

import us.codecraft.webmagic.Spider;

@Component
public class AnimeCrawler implements AbstractCrawler {
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void crawl(String[] strings) {
		Spider.create(new AnimeAPIPageProcessor())
			.addUrl(strings)
			.thread(4)
			.addPipeline(new AnimeListDaoPipeline())
			.run();
	}
	
	@Override
	public String[] buildUrl() {
		return Date4SearchUrlUtil.getUrls();
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final AnimeCrawler jobCrawler = applicationContext.getBean(AnimeCrawler.class);
		
		String[] strings = jobCrawler.buildUrl();
		
		jobCrawler.crawl(strings);

	}


}