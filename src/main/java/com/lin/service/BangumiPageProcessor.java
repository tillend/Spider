package com.lin.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.vo.BangumiVO;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class BangumiPageProcessor implements PageProcessor {
	private Logger logger = Logger.getLogger(getClass());

	private Site site = Site.me().setDomain("bilibili.com").setRetryTimes(3).setCharset("UTF-8");

	@Override
	public void process(Page page) {
		// 从内容块中获取视频对应的信息
		String beforeTran = page.getHtml().getDocument().body().html();

		// 解析jsonString得出视频列表
		JSONObject respJson = JSON.parseObject(beforeTran);
		JSONObject dataJson = (JSONObject) JSON.toJSON(respJson.get("result"));
		// 获取结果的总页面,继续加入待爬取列表


		// 单个结果封装处理

		String video = dataJson.get("list").toString();
		List<BangumiVO> list = JSON.parseArray(video, BangumiVO.class);

		page.putField("bangumiList", list);

	}

	@Override
	public Site getSite() {
		return site;
	}

}
