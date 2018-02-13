package com.lin.controller.crawler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.dao.BangumiMapper;
import com.lin.model.Bangumi;
import com.lin.service.BangumiPageProcessor;
import com.lin.service.pipeline.BangumiListDaoPipeline;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.SearchUrlUtil;

import us.codecraft.webmagic.Spider;

@Component
public class SeasonInfoCrawler implements AbstractCrawler {
	
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
		//从数据库中获取页面id列表
		BangumiMapper bangumiMapper = getBean(BangumiMapper.class);
		List<Bangumi> respList = bangumiMapper.selectAllSeasonId();
		
		//拼接为有效的url
		List<String> list = new ArrayList<String>();
		for(Bangumi b: respList) {
			String url = String.format(SearchUrlUtil.url4seasonInfo, b.getSeasonId());
			list.add(url);
		}
		
		String[] temp = new String[list.size()];
		return list.toArray(temp);
	}
	

	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final SeasonInfoCrawler jobCrawler = applicationContext.getBean(SeasonInfoCrawler.class);
		
		//从数据库中获取页面id列表,拼接为有效的url
		String[] strings = jobCrawler.buildUrl();
		
		//启动爬虫
		jobCrawler.crawl(strings);

	}
}