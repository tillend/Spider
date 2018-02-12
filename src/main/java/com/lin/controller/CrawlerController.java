package com.lin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lin.pipeline.AnimeDaoPipeline;
import com.lin.service.BiliBili_anime_api;

import us.codecraft.webmagic.Spider;

@Controller
@RequestMapping("/crawler")
public class CrawlerController {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getAnimeInfo() {
		crawl();
		
		return "index";
	}
	
    public void crawl() {
//		String[] strings = Date4SearchUrlUtil.getUrls();
    	String[] strings = new String[] {"https://s.search.bilibili.com/cate/search?main_ver=v3&search_type=video&view_type=hot_rank&cate_id=32&pagesize=20&jsonp=jsonp&time_from=20171201&time_to=20180208"};
		
		Spider.create(new BiliBili_anime_api())
				.addUrl(strings)
				.thread(4)
				.addPipeline(new AnimeDaoPipeline())
				.run();
    }
}
