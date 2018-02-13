package com.lin.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.lin.dao.BangumiMapper;
import com.lin.model.Bangumi;
import com.lin.utils.ApplicationContextUtil;

public class MybatisTest {
	
	@Test
	public void test() {
		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		BangumiMapper bangumiMapper = applicationContext.getBean(BangumiMapper.class);
		List<Bangumi> list = bangumiMapper.selectAllSeasonId();
		
		System.out.println(list.get(0));
	}

}
