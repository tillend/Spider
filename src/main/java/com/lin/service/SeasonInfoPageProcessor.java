package com.lin.service;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.utils.JsonpUtil;
import com.lin.vo.SeasonInfoVO;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class SeasonInfoPageProcessor implements PageProcessor {
	private Logger logger = Logger.getLogger(getClass());

	private Site site = Site.me().setDomain("bilibili.com").setRetryTimes(3).setCharset("UTF-8");

	@Override
	public void process(Page page) {
		// 从内容块中获取视频对应的信息
		//此API获取的数据为jsonp样式
		String beforeTran = page.getHtml().getDocument().body().html();
		//转换为jsonString
		String afterTran = JsonpUtil.jsonp2jsonString(beforeTran, "seasonListCallback");

		// 解析jsonString得出视频列表
		JSONObject respJson = JSON.parseObject(afterTran);
		JSONObject dataJson = (JSONObject) JSON.toJSON(respJson.get("result"));
		// 获取结果的总页面,继续加入待爬取列表

		// 单个结果封装处理
		String coins = dataJson.get("coins").toString();
		String danmaku_count = dataJson.get("danmaku_count").toString();
		String evaluate = dataJson.get("evaluate").toString();
		String play_count = dataJson.get("play_count").toString();
		String pub_time = dataJson.get("pub_time").toString();
		String season_id = dataJson.get("season_id").toString();
		
		//对域属性赋值
		SeasonInfoVO seasonInfo = new SeasonInfoVO();
		seasonInfo.setCoins(coins);
		seasonInfo.setDanmaku_count(danmaku_count);
		seasonInfo.setEvaluate(evaluate);
		seasonInfo.setPlay_count(play_count);
		seasonInfo.setPub_time(pub_time);
		seasonInfo.setSeason_id(season_id);

		page.putField("seasonInfo", seasonInfo);

	}

	@Override
	public Site getSite() {
		return site;
	}

}
