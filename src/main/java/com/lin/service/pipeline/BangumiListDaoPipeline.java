package com.lin.service.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.dao.BangumiMapper;
import com.lin.model.Bangumi;
import com.lin.utils.ApplicationContextUtil;
import com.lin.vo.BangumiVO;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component("BangumiListDaoPipeline")
public class BangumiListDaoPipeline implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {
		List<BangumiVO> list = resultItems.get("bangumiList");
		List<Bangumi> recordList = new ArrayList<>();
		for(BangumiVO t:list) {
			//避免使用beanMapper
			Bangumi record = new Bangumi();
			record.setId(null);
			/**
			 * 播放数、历史弹幕总数需另一爬虫抓取
			 */
			record.setPlay(null);
			record.setVideoReview(null);
			record.setPubTime(null);
			
			record.setCover(t.getCover());
			record.setFavorites(t.getFavorites());
			record.setIsFinish(t.getIs_finish());
			record.setNewestEpIndex(t.getNewest_ep_index());
			record.setPubString(t.getPub_string());
			record.setSeasonId(t.getSeason_id());
			record.setSeasonStatus(t.getSeason_status());
			record.setTitle(t.getTitle());
			record.setTotalCount(Integer.parseInt(t.getTotal_count()));
			record.setUpdatePattern(t.getUpdate_pattern());
			record.setUpdateTime(t.getUpdate_time());
			record.setUrl(t.getUrl());
			record.setPubString(t.getPub_string());
			record.setWeek(t.getWeek());
			
			recordList.add(record);
		}

		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		BangumiMapper bangumiMapper = applicationContext.getBean(BangumiMapper.class);
		bangumiMapper.insertByBatch(recordList);
		
		
	}

}
