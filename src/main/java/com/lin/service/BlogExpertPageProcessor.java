package com.lin.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.utils.StringUtil;
import com.lin.vo.BangumiVO;
import com.lin.vo.BlogExpertVO;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class BlogExpertPageProcessor implements PageProcessor {
	private Logger logger = Logger.getLogger(getClass());

	private Site site = Site.me().setDomain("csdn.net").setRetryTimes(3).setCharset("UTF-8");

	public void process(Page page) {
		//TODO Selectable的链式抽取页码中url,加入至爬取列表中
		 
		
		List<BlogExpertVO> list = new ArrayList<>();

		// 获取专家列表元素
		Selectable selectable = page.getHtml().xpath("//dl[@class=\"experts_list\"]//dd");

		for (Selectable s : selectable.nodes()) {
			String url = s.xpath("//a[@class=\"expert_name\"]").links().toString();
			String name = s.xpath("//a[@class=\"expert_name\"]/text()").toString();
			String location = s.xpath("//div[@class=\"address\"]//em/tidyText()").toString();
			String job = s.xpath("//div[@class=\"address\"]//span/tidyText()").toString();
			String articleNum = s.xpath("//div[@class=\"count_l fl\"]//b/tidyText()").toString();
			String readNum = s.xpath("//div[@class=\"count_l fr\"]//b/tidyText()").toString();

			// 解构url获取用户id
			String blogerId = StringUtil.getLastSlantContent(url);

			// 单个结果封装处理
			BlogExpertVO expert = new BlogExpertVO();
			expert.setArticleNum(articleNum);
			expert.setJob(job);
			expert.setBlogerId(blogerId);
			expert.setLocation(location);
			expert.setName(name);
			expert.setReadNum(readNum);
			expert.setUrl(url);

			// 加入列表中
			list.add(expert);
		}

		// 将封装后的结果存入page中
		page.putField("expertList", list);
	}

	@Override
	public Site getSite() {
		return site;
	}

}
