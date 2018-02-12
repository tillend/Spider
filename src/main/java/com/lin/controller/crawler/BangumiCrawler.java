package com.lin.controller.crawler;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.controller.AbstractCrawler;
import com.lin.service.BiliBili_bangumi_api;
import com.lin.service.pipeline.BangumiListDaoPipeline;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.SearchUrlUtil;
import com.lin.utils.type.PageNum;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;

@Component
public class BangumiCrawler extends AbstractCrawler {
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void crawl() {
		String[] strings = SearchUrlUtil.getUrls(SearchUrlUtil.url4bangumi, PageNum.BANGIMI.getPageNum());

		Spider.create(new BiliBili_bangumi_api())
			.addUrl(strings)
			.thread(4)
			.addPipeline(new BangumiListDaoPipeline())
			.run();
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final BangumiCrawler jobCrawler = applicationContext.getBean(BangumiCrawler.class);
		jobCrawler.crawl();

	}
}