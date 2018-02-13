package com.lin.test;

import org.junit.Ignore;
import org.junit.Test;

import com.lin.service.AnimePageProcessor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;

public class BiliBili_anime_Test {

	@Ignore
	@Test
	public void test4process() {
		Spider.create(new AnimePageProcessor())
				.addUrl("https://www.bilibili.com/v/anime/finish/#/all/scores/0/1/2017-12-01,2018-02-08")
				.addPipeline(new FilePipeline("/data")).thread(1).run();
	}

	@Test
	public void test4api() {
		Spider.create(new AnimePageProcessor())
				.addUrl("https://s.search.bilibili.com/cate/search?main_ver=v3&search_type=video&view_type=hot_rank"
						+ "&cate_id=32&page=1&pagesize=20&jsonp=jsonp"
						+ "&time_from=20171201&time_to=20180208")
				.addPipeline(new FilePipeline("/data")).thread(1).run();
	}
}
