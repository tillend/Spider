package com.lin.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.lin.utils.SearchUrlUtil;
import com.lin.utils.StringUtil;
import com.lin.utils.common.ArticleType;
import com.lin.vo.BlogVO;

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
			processOldPage(page);
		} else if (StringUtils.isNotBlank(newFlag)) {
			processNewPage(page);
		} else {
			logger.error("wrong page");
		}
	}

	/**
	 * 筛选出新界面文章列表
	 * 
	 * @param page
	 */
	private void processNewPage(Page page) {
		// 从分页列表中获取待爬取url,将其加入到爬虫队列
		page.addTargetRequests(
				page.getHtml().xpath("//ul[@class=\"pagination justify-content-center\"]").links().all());

		// TODO 从爬虫队列中去除当前url

		// 获取blogerId
		String blogerId = StringUtil.getIdfromUrl(page.getUrl().toString());

		List<BlogVO> list = new ArrayList<>();

		// 获取博客列表的抽取元素
		Selectable selectable = page.getHtml().xpath("//ul[@class=\"blog-units blog-units-box\"]/li");

		for (Selectable s : selectable.nodes()) {
			// 文章地址
			String detailUrl = s.links().toString();
			// 文章id
			String articleId = StringUtil.getLastSlantContent(detailUrl);
			// 文章标题
			String linkTitle = s.xpath("//h3[@class='blog-title odd-overhidden bottom-dis-8']/tidyText()")
					.replace("置顶", "").toString().trim();
			// 文章类型
			String type = s.xpath("//div[@class='floatL tag']/tidyText()").toString();

			// 文章摘要
			String summary = s.xpath("//p[@class='text bottom-dis-8']//text()").toString();

			/**
			 * 抽取元素:文章详情
			 */
			Selectable detail = s.xpath("//div[@class='floatL left-dis-24']");
			String publishDateTime = detail.nodes().get(0).xpath("//tidyText()").toString();
			// 阅读量
			String viewNum = detail.nodes().get(1).xpath("//span/tidyText()").toString();

			// 评论数
			String commentNum = detail.nodes().get(2).xpath("//span/tidyText()").toString();

			// 单个结果封装处理
			BlogVO blog = setBlogVO(articleId, linkTitle,type,publishDateTime,viewNum,commentNum,detailUrl,summary, blogerId);
			list.add(blog);
		}
		
		page.putField("blogList", list);

	}

	@Override
	public Site getSite() {
		return site;
	}

	/**
	 * 处理旧界面中所有文章列表
	 */
	private void processOldPage(Page page) {
		// 从分页列表中获取待爬取url,将其加入到爬虫队列
		page.addTargetRequests(
				page.getHtml().xpath("//div[@class=\"list_item_new\"]//div[@class=\"pagelist\"]").links().all());

		// 获取blogerId
		String blogerId = StringUtil.getIdfromUrl(page.getUrl().toString());

		// 获取列表最外层节点的所有子节点。经过分析可以知道子节点有3个，“置顶文章”列表和“普通文章列表”和分页div。
		Selectable selectable = page.getHtml().xpath("//div[@class=\"list_item_new\"]/div");

		// 判断是否存在文章：若div的个数大于0说明存在文章，即可进入文章爬取，否则为页面错误。
		if (selectable.nodes().size() == 0) {
			logger.error("wrong page");
		}

		// 从列表页获取列表信息
		List<String> all, toplist;
		// 获取普通文章列表
		all = selectable.xpath("//div[@id=\"article_list\"]/div").all();
		// 获取置顶文章列表
		toplist = selectable.xpath("//div[@id=\"article_toplist\"]/div").all();
		// 整合所有文章列表
		all.addAll(toplist);
		
		//爬取结果
		List<BlogVO> list = new ArrayList<>();

		if (!all.isEmpty())
			for (String string : all) {
				/**
				 * 抽取元素-标题
				 */
				Selectable title = new Html(string).xpath("//div[@class='article_title']");
				// 文章地址
				String rawUrl = title.xpath("//span[@class='link_title']//a/@href").toString();
				String detailUrl = String.format(SearchUrlUtil.url4csdn, rawUrl);
				// 文章id
				String articleId = StringUtil.getLastSlantContent(detailUrl);
				// 文章标题
				String linkTitle = title.xpath("//span[@class='link_title']//a/text()").toString();
				// 文章类型
				String type = getArticleType(string);

				// 文章摘要
				String summary = new Html(string).xpath("//div[@class='article_description']//text()").toString();

				/**
				 * 抽取元素-相关细节
				 */
				Selectable detail = new Html(string).xpath("//div[@class='article_manage']");

				String publishDateTime = detail.xpath("//span[@class='link_postdate']//text()").toString();
				// 阅读量
				String viewNum = detail.xpath("//span[@class='link_view']//text()").toString();
				viewNum = StringUtil.getStringPureNumber(viewNum);

				// 评论数
				String commentNum = detail.xpath("//span[@class='link_comments']//text()").toString();
				commentNum = StringUtil.getStringPureNumber(commentNum);

				// 单个结果封装处理
				BlogVO blog = setBlogVO(articleId, linkTitle,type,publishDateTime,viewNum,commentNum,detailUrl,summary, blogerId);
				list.add(blog);

			}
		//直入结果集中
		page.putField("blogList", list);

	}

	/**
	 * 获取旧界面中文章类型
	 * 
	 * @param string
	 * @see com.lin.utils.common.ArticleType
	 * @return
	 */
	private String getArticleType(String string) {
		Selectable s = new Html(string).xpath("//div[@class='article_title']");

		String type;
		// 原创类型
		type = s.xpath("//span[@class='ico ico_type_Original']//text()").get();
		if (type != null)
			return ArticleType.ORIGINAL.getType();
		// 转载类型
		type = s.xpath("//span[@class='ico ico_type_Repost']//text()").get();
		if (type != null)
			return ArticleType.REPOST.getType();
		// 翻译类型
		type = s.xpath("//span[@class='ico ico_type_Translated']//text()").get();
		if (type != null)
			return ArticleType.TRANSLATED.getType();
		return ArticleType.UNKNOWN.getType();
	}
	
	/**
	 * 设置blog属性
	 * @param articleId
	 * @param linkTitle
	 * @param type
	 * @param publishDateTime
	 * @param viewNum
	 * @param commentNum
	 * @param detailUrl
	 * @param summary
	 * @param blogerId
	 * @return
	 */
	private BlogVO setBlogVO(String articleId, String linkTitle, String type, String publishDateTime, String viewNum,
			String commentNum, String detailUrl, String summary, String blogerId) {
		BlogVO blog = new BlogVO();
		blog.setArticleId(articleId);
		blog.setBlogerId(blogerId);
		blog.setCommentNum(commentNum);
		blog.setDetailUrl(detailUrl);
		blog.setLinkTitle(linkTitle);
		blog.setPublishDateTime(publishDateTime);
		blog.setSummary(summary);
		blog.setType(type);

		return blog;
	}
}
