package com.lin.service.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.dao.BlogExpertMapper;
import com.lin.model.BlogExpert;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.StringUtil;
import com.lin.vo.BlogExpertVO;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component("BlogExpertListDaoPipeline")
public class BlogExpertListDaoPipeline implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {
		List<BlogExpertVO> list = resultItems.get("expertList");
		List<BlogExpert> recordList = new ArrayList<>();
		for(BlogExpertVO t:list) {
			//避免使用beanMapper
			BlogExpert record = new BlogExpert();
			record.setId(null);
			record.setArticleNum(StringUtil.string2int(t.getArticleNum()));
			record.setBlogerId(t.getBlogerId());
			record.setJob(t.getJob());
			record.setLocation(t.getLocation());
			record.setName(t.getName());
			record.setReadNum(StringUtil.string2long(t.getReadNum()));
			record.setUrl(t.getUrl());

			recordList.add(record);
		}

		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		BlogExpertMapper blogExpertMapper = applicationContext.getBean(BlogExpertMapper.class);
		blogExpertMapper.insertByBatch(recordList);
		
		
	}

}
