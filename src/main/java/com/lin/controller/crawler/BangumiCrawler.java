package com.lin.controller.crawler;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.service.BangumiPageProcessor;
import com.lin.service.pipeline.BangumiListDaoPipeline;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.SearchUrlUtil;
import com.lin.utils.common.PageNum;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;

@Component
public class BangumiCrawler implements AbstractCrawler {
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void crawl(String[] strings) {
		Spider.create(new BangumiPageProcessor())
			.addUrl(strings)
			.thread(4)
			.addPipeline(new BangumiListDaoPipeline())
			.run();
	}
	
	@Override
	public String[] buildUrl() {
		String[] strings = SearchUrlUtil.getUrls(SearchUrlUtil.url4bangumi, PageNum.BANGIMI.getPageNum());
		return strings;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final BangumiCrawler jobCrawler = applicationContext.getBean(BangumiCrawler.class);

		String[] strings = jobCrawler.buildUrl();
		
		jobCrawler.crawl(strings);

	}

}