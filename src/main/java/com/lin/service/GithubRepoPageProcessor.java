package com.lin.service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

	public void process(Page page) {
//		page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
//		page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
//		page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//		page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//		if (page.getResultItems().get("name") == null) {
//			// skip this page
//			page.setSkip(true);
//		}
//		page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
//		page.putField("name", page.getHtml().xpath("//span[@class='p-name vcard-fullname d-block']/tidyText()").toString());
		
		page.putField("name", page.getHtml().xpath("//h3[@class='movie-name']/text()").all());
		
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new GithubRepoPageProcessor())
		.addUrl("http://maoyan.com/cinema/142?poi=1436366")
		.addPipeline(new FilePipeline("data/"))
		.thread(1).run();
	}
}