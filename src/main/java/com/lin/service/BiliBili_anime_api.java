package com.lin.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.vo.AnimeVO;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class BiliBili_anime_api implements PageProcessor {
	private Logger logger = Logger.getLogger(getClass());

	private Site site = Site.me().setDomain("bilibili.com").setRetryTimes(3);

	@Override
	public void process(Page page) {
		// 从内容块中获取视频对应的信息
		String beforeTran = page.getHtml().getDocument().body().html();
		// 将json数据从iso-8859-1编码转为UTF-8
//		String jsonString = TranscodeUtil.transcode(beforeTran);

		//解析jsonString得出视频列表
		JSONObject dataJson = JSON.parseObject(beforeTran);
		
		//获取结果的总页面,继续加入待爬取列表
		if("-1".equals(dataJson.getString("page"))) {
			int numPages = Integer.parseInt(dataJson.getString("numPages"));
			for(int i = 2; i <= numPages; i++) {
				page.addTargetRequest(String.format(page.getUrl() + "&page=%d", i));
			}
			
		}
		
		
		//单个结果封装处理
		String video = dataJson.get("result").toString();
		List<AnimeVO> list = JSON.parseArray(video, AnimeVO.class);

		page.putField("animeList", list.get(0));

	}

	@Override
	public Site getSite() {
		return site;
	}

}
