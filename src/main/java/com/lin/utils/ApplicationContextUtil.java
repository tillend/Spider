package com.lin.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {

	private static ApplicationContextUtil singleton;

	private static ApplicationContext applicationContext;

	private ApplicationContextUtil() {
	}

	public static ApplicationContextUtil getSingleton() {
		// 双重加锁模式
		if (null == singleton) {
			synchronized (ApplicationContextUtil.class) {
				if (null == singleton) {
					singleton = new ApplicationContextUtil();
					applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");
				}
			}
		}

		return singleton;
	}

	public ApplicationContext getContext() {
		return applicationContext;
	}

	public <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}
}
