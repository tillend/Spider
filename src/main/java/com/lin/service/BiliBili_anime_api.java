package com.lin.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.model.AnimeModel;
import com.lin.utils.TranscodeUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class BiliBili_anime_api implements PageProcessor {
	private Logger logger = Logger.getLogger(getClass());

	private Site site = Site.me().setDomain("bilibili.com").setRetryTimes(2);

	@Override
	public void process(Page page) {
		// 从内容块中获取视频对应的信息
		String beforeTran = page.getHtml().getDocument().body().html();
		// 将json数据从iso-8859-1编码转为UTF-8
		String jsonString = TranscodeUtil.transcode(beforeTran);

		//解析jsonString得出视频列表
		JSONObject dataJson = JSON.parseObject(jsonString);
		String numPages = dataJson.getString("numPages").toString();
		String video = dataJson.get("result").toString();
		List<AnimeModel> list = JSON.parseArray(video, AnimeModel.class);

		page.putField("animeModel", list);

	}

	@Override
	public Site getSite() {
		return site;
	}

}
