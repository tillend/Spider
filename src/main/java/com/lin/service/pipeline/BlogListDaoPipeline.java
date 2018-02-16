package com.lin.service.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.dao.BlogMapper;
import com.lin.model.Blog;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.DateUtil;
import com.lin.utils.StringUtil;
import com.lin.vo.BlogVO;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component("BlogListDaoPipeline")
public class BlogListDaoPipeline implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {
		List<BlogVO> list = resultItems.get("blogList");
		List<Blog> recordList = new ArrayList<>();
		for(BlogVO t:list) {
			//避免使用beanMapper
			Blog record = new Blog();
			record.setId(null);
			record.setArticleId(StringUtil.string2int(t.getArticleId()));
			record.setBlogerId(t.getBlogerId());
			record.setCommentNum(StringUtil.string2int(t.getCommentNum()));
			record.setDetailurl(t.getDetailUrl());
			record.setLinkTitle(t.getLinkTitle());
			record.setPublishTime(DateUtil.string2Date(t.getPublishDateTime()));
			record.setSummary(t.getSummary());
			record.setType(t.getType());
			record.setViewNum(StringUtil.string2long(t.getViewNum()));

			recordList.add(record);
		}

		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		BlogMapper blogMapper = applicationContext.getBean(BlogMapper.class);
		blogMapper.insertByBatch(recordList);
		
	}

}
