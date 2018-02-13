package com.lin.service.pipeline;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.dao.BangumiMapper;
import com.lin.model.Bangumi;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.DateUtil;
import com.lin.utils.StringUtil;
import com.lin.vo.SeasonInfoVO;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component("SeasonInfoDaoPipeline")
public class SeasonInfoDaoPipeline implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {
		SeasonInfoVO seasonInfo = resultItems.get("seasonInfo");

		// 避免使用beanMapper
		Bangumi record = new Bangumi();
		/**
		 * 根据seasonId更新信息
		 */

		record.setSeasonId(seasonInfo.getSeason_id());
		record.setPlay(StringUtil.long2String(seasonInfo.getPlay_count()));
		record.setVideoReview(StringUtil.long2String(seasonInfo.getDanmaku_count()));
		record.setPubTime(DateUtil.string2Date(seasonInfo.getPub_time()));
		record.setCoins(StringUtil.long2String(seasonInfo.getCoins()));
		record.setEvaluate(seasonInfo.getEvaluate());

		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		BangumiMapper bangumiMapper = applicationContext.getBean(BangumiMapper.class);
		bangumiMapper.updateBySeasonIdSelective(record);

	}

}
