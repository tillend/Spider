package com.lin.controller.crawler;

import org.springframework.context.ApplicationContext;

import com.lin.utils.ApplicationContextUtil;

public interface AbstractCrawler {
	
	public final ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
	
	/**
	 * 默认方法,子类无需重载(获取BeanFactory中的bean)
	 * @param type
	 * @return
	 */
	public default <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}

	/**
	 * 爬虫启动
	 * @param strings	初始爬取url
	 */
	public abstract void crawl(String[] strings);
	
	/**
	 * 爬虫初始爬取url获取及拼接
	 * @return
	 */
	public abstract String[] buildUrl();
}
