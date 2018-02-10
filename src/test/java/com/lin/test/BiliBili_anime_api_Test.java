package com.lin.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lin.service.BiliBili_anime_api;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;

public class BiliBili_anime_api_Test {

	@Test
	public void test4api() {
		String[] strings = null;
		
		Spider.create(new BiliBili_anime_api())
				.addUrl(strings)
				.addPipeline(new FilePipeline("/data")).thread(1).run();
	}
}
