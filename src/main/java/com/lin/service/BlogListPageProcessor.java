package com.lin.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.lin.utils.StringUtil;
import com.lin.utils.common.ArticleType;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class BlogListPageProcessor implements PageProcessor {
	private Logger logger = Logger.getLogger(getClass());

	private Site site = Site.me().setDomain("csdn.net").setRetryTimes(3).setCharset("UTF-8");

	@Override
	public void process(Page page) {
		// 根据博客列表页面中博主名的xpath样式来区分新旧背景模板
		String oldFlag = page.getHtml().xpath("//a[@class=\"user_name\"]").toString();
		String newFlag = page.getHtml().xpath("//a[@id=\"uid\"]").toString();

		if (StringUtils.isNotBlank(oldFlag)) {
			// 从分页列表中获取待爬取url,将其加入到爬虫队列
			page.addTargetRequests(
					page.getHtml().xpath("//div[@class=\"list_item_new\"]//div[@class=\"pagelist\"]").links().all());

			// 博主名
			String blogerId = StringUtil.getLastSlantContent(page.getUrl().toString());
			// 若博主名为空,跳过此url的爬取任务
			if (StringUtils.isBlank(blogerId)) {
				return;
			}

			// 获取列表最外层节点的所有子节点。经过分析可以知道子节点有3个，“置顶文章”列表和“普通文章列表”和分页div。
			Selectable out_div = page.getHtml().xpath("//div[@class=\"list_item_new\"]/div");

			// 判断是否存在文章：若div的个数大于0说明存在文章，即可进入文章爬取，否则为页面错误。
			if (out_div.nodes().size() > 0) {
				// 存在
				processOldArticle(out_div, blogerId);
			} else {
				logger.error("wrong page");
			}
		} else if (StringUtils.isNotBlank(newFlag)) {

		} else {
			logger.error("wrong page");
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

	/**
	 * 处理旧界面中所有文章列表
	 */
	private void processOldArticle(Selectable selectable, String blogerId) {
		// 从列表页获取列表信息
		List<String> all, toplist;
		// 获取普通文章列表
		all = selectable.xpath("//div[@id=\"article_list\"]/div").all();
		// 获取置顶文章列表
		toplist = selectable.xpath("//div[@id=\"article_toplist\"]/div").all();
		// 整合所有文章列表
		all.addAll(toplist);

		if (!all.isEmpty())
			for (String string : all) {
				/**
				 * 抽取元素-标题
				 */
				Selectable title = new Html(string).xpath("//div[@class='article_title']");
				// 文章地址
				String detailsUrl = title.xpath("//span[@class='link_title']//a/@href").toString();
				// 文章id
				String id_blog = StringUtil.getLastSlantContent(detailsUrl);
				// 文章标题
				String link_title = title.xpath("//span[@class='link_title']//a/text()").toString();
				// 文章类型
				int type = getArticleType(string);

				// 文章摘要
				String summary = new Html(string).xpath("//div[@class='article_description']//text()").toString();

				/**
				 * 抽取元素-相关细节
				 */
				Selectable detail = new Html(string).xpath("//div[@class='article_manage']");

				String publishDateTime = detail.xpath("//span[@class='link_postdate']//text()").toString();
				// 阅读量
				String viewNums = detail.xpath("//span[@class='link_view']//text()").toString();
				viewNums = StringUtil.getStringPureNumber(viewNums);

				// 评论数
				String commentNums = detail.xpath("//span[@class='link_comments']//text()").toString();
				commentNums = StringUtil.getStringPureNumber(commentNums);
				// 开始组织数据
				System.out.println(":,文章id：" + id_blog + ",文章标头：" + link_title + ",文章类型('0':原创；'1'：转载；'2'：翻译):" + type
						+ ",发表日期：" + publishDateTime + ",阅读量:" + viewNums + ",评论数：" + commentNums + ",文章地址："
						+ detailsUrl + ",文章摘要：" + summary + "");

			}

	}

	/**
	 * 获取旧界面中文章类型
	 * @param string
	 * @see com.lin.utils.common.ArticleType
	 * @return
	 */
	private int getArticleType(String string) {
		Selectable s = new Html(string).xpath("//div[@class='article_title']");
		
		String type;
		// 原创类型
		type = s.xpath("//span[@class='ico ico_type_Original']//text()").get();
		if (type != null)
			return ArticleType.ORIGINAL.getValue();
		// 转载类型
		type = s.xpath("//span[@class='ico ico_type_Repost']//text()").get();
		if (type != null)
			return ArticleType.REPOST.getValue();
		// 翻译类型
		type = s.xpath("//span[@class='ico ico_type_Translated']//text()").get();
		if (type != null)
			return ArticleType.TRANSLATED.getValue();
		return ArticleType.UNKNOWN.getValue();
	}
}
