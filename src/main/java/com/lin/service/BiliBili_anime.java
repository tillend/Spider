package com.lin.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lin.vo.AnimeListVO;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class BiliBili_anime implements PageProcessor {
	private Logger logger = Logger.getLogger(getClass());
	
	private Site site = Site.me()
			.setDomain("bilibili.com")
			.setRetryTimes(1);
			

	@Override
	public void process(Page page) {
		//将其加入到爬虫队列
		//page.addTargetRequests(page.getHtml().links().regex("^https://www.bilibili.com/video/av[0-9]*/$").all());

		String home = page.getHtml().xpath("//li[@class='nav-item home']//a//tidyText()").get();
		
		/**
		 * 此内容块为动态加载,改用api接口爬取信息
		 */
		//从内容块中获取视频对应的信息
		Selectable titleSelectable = page.getHtml().css("div.vd-list-cnt");
		
		List<String> title = page.getHtml().xpath("//div[@class=\"r\"]//a//text()").all();
		List<String> url = page.getHtml().xpath("//div[@class=\"r\"]//a").links().all();
		List<String> desc = page.getHtml().css("div.v-desc").all();
		List<String> up_info = page.getHtml().css("div.v-author").all();
		List<String> watch_count = page.getHtml().xpath("//span[@class='b-icon b-icon-v-play']/tidyText()").all();
		List<String> dm_count = page.getHtml().xpath("//span[@class='b-icon b-icon-v-dm']/tidyText()").all();
		
		AnimeListVO animeListModel = new AnimeListVO();
		animeListModel.setTitle(title);
		animeListModel.setUrl(url);
		animeListModel.setDesc(desc);
		animeListModel.setUp_info(up_info);
		animeListModel.setWatch_count(watch_count);
		animeListModel.setDm_count(dm_count);
		
		page.putField("animeList", animeListModel);

	}

	@Override
	public Site getSite() {
		return site;
	}

}
