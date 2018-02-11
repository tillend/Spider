package com.lin.controller.crawler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.pipeline.AnimeDaoPipeline;
import com.lin.service.BiliBili_anime_api;

import us.codecraft.webmagic.Spider;

@Component
public class AnimeCrawler {

    public void crawl() {
//		String[] strings = Date4SearchUrlUtil.getUrls();
    	String[] strings = new String[] {"https://s.search.bilibili.com/cate/search?main_ver=v3&search_type=video&view_type=hot_rank&cate_id=32&pagesize=20&jsonp=jsonp&time_from=20171201&time_to=20180208"};
		
		Spider.create(new BiliBili_anime_api())
				.addUrl(strings)
				.thread(4)
				.addPipeline(new AnimeDaoPipeline())
				.run();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");
        final AnimeCrawler jobCrawler = applicationContext.getBean(AnimeCrawler.class);
        jobCrawler.crawl();
    }
}