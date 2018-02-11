package com.lin.test;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lin.dao.AnimeMapper;
import com.lin.model.Anime;
import com.lin.service.BiliBili_anime_api;
import com.lin.utils.Date4SearchUrlUtil;
import com.lin.vo.AnimeVO;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;

public class BiliBili_anime_api_Test {
	
	@Ignore
	@Test
	public void test4all() {
		String[] strings = Date4SearchUrlUtil.getUrls();
		
		Spider.create(new BiliBili_anime_api())
				.addUrl(strings)
				.addPipeline(new FilePipeline("/data"))
				.thread(4).run();
	}
	
	@Ignore
	@Test
	public void test4api() {		
		Spider.create(new BiliBili_anime_api())
				.addUrl("https://s.search.bilibili.com/cate/search?main_ver=v3&search_type=video&view_type=hot_rank&cate_id=32&pagesize=40&jsonp=jsonp&time_from=20171201&time_to=20180208")
				.addPipeline(new FilePipeline("/data"))
				.thread(4).run();
	}
	
	@Ignore
	@Test
	public void testMybatis() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        final AnimeMapper animeMapper = applicationContext.getBean(AnimeMapper.class);

        String jsonString = "{\"badgepay\":false,\"play\":\"528203\",\"description\":\"以19世纪后半，处于维多利亚女王时代的英国为背景，主角是名门贵族法多姆海恩家的管家塞巴斯蒂安。他集知识、教养、品位、料理、武术……等十八般武器于一身，可说是个找不出来缺点的管家。而他所侍奉的主人是个任性的12岁大少爷夏尔。今天塞巴斯蒂安也穿着漆黑的燕尾服在华丽地执行自己的任务……\",\"pubdate\":\"2018-01-31 18:23:54\",\"title\":\"【合集】黑执事【独家正版】\",\"review\":12116,\"pic\":\"\\/\\/i0.hdslb.com\\/bfs\\/archive\\/2982faf11e087c4bdd7b3a13d3faa688badc23bc.jpg\",\"mid\":928123,\"id\":18958999,\"arcurl\":\"http:\\/\\/www.bilibili.com\\/video\\/av18958999\",\"tag\":\"BILIBILI正版\",\"video_review\":62181,\"author\":\"哔哩哔哩番剧\",\"favorites\":9846,\"duration\":35344,\"type\":\"video\",\"arcrank\":\"0\",\"senddate\":1517394622}";
        AnimeVO animeVO = JSON.parseObject(jsonString, AnimeVO.class);
        Anime anime = Anime.turnVO2Model(animeVO);
        
        
        animeMapper.insert(anime);
	}
	
	
}
