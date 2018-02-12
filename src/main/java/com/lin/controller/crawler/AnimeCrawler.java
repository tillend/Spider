package com.lin.controller.crawler;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.pipeline.AnimeListDaoPipeline;
import com.lin.service.BiliBili_anime_api;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.Date4SearchUrlUtil;

import us.codecraft.webmagic.Spider;

@Component
public class AnimeCrawler {

	public void crawl() {
		String[] strings = Date4SearchUrlUtil.getUrls();

		Spider.create(new BiliBili_anime_api())
			.addUrl(strings)
			.thread(4)
			.addPipeline(new AnimeListDaoPipeline())
			.run();
	}

	public static void main(String[] args) {
		// ApplicationContext applicationContext = new
		// ClassPathXmlApplicationContext("classpath:applicationContext*.xml");
		// final AnimeCrawler jobCrawler =
		// applicationContext.getBean(AnimeCrawler.class);
		// jobCrawler.crawl();

		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final AnimeCrawler jobCrawler = applicationContext.getBean(AnimeCrawler.class);
		jobCrawler.crawl();

	}
}