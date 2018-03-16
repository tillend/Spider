package com.lin.controller.crawler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.dao.BlogExpertMapper;
import com.lin.model.BlogExpert;
import com.lin.service.BlogListPageProcessor;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.SearchUrlUtil;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.scheduler.QueueScheduler;

@Component
public class BlogListCrawler implements AbstractCrawler {
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void crawl(String[] strings) {
		Spider.create(new BlogListPageProcessor())
			.addUrl(strings)
			.thread(2)
			.addPipeline(new ConsolePipeline())
			.run();
	}
	
	@Override
	public String[] buildUrl() {
		//从数据库中获取页面id列表
		BlogExpertMapper mapper = getBean(BlogExpertMapper.class);
		List<BlogExpert> respList = mapper.selectAllBlogerId();
		
		//拼接为有效的url
		List<String> list = new ArrayList<String>();
		for(BlogExpert b: respList) {
			String url = String.format(SearchUrlUtil.url4blogList, b.getBlogerId());
			list.add(url);
		}
		
		String[] temp = new String[list.size()];
		return list.toArray(temp);
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		final BlogListCrawler jobCrawler = applicationContext.getBean(BlogListCrawler.class);

		String[] strings = jobCrawler.buildUrl();
		
		jobCrawler.crawl(strings);

	}

}