package com.lin.service.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.dao.AnimeMapper;
import com.lin.model.Anime;
import com.lin.utils.ApplicationContextUtil;
import com.lin.utils.DateUtil;
import com.lin.utils.StringUtil;
import com.lin.vo.AnimeVO;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component("AnimeListDaoPipeline")
public class AnimeListDaoPipeline implements Pipeline {
	
//    @Resource
//    private AnimeMapper animeMapper;

	@Override
	public void process(ResultItems resultItems, Task task) {
		List<AnimeVO> list = resultItems.get("animeList");
		List<Anime> recordList = new ArrayList<>();
		for(AnimeVO t:list) {
			//避免使用beanMapper
			Anime record = new Anime();
			record.setId(null);
			record.setArcrank(t.getArcrank());
			record.setArcurl(t.getArcurl());
			record.setAuthor(t.getAuthor());
			record.setDescription(t.getDescription());
			record.setDuration(t.getDuration());
			record.setFavorites(t.getFavorites());
			record.setMid(t.getMid());
			record.setPic(t.getPic());
			record.setPlay(StringUtil.string2long(t.getPlay()));
			record.setPubdate(DateUtil.string2Date(t.getPubdate()));
			record.setReview(t.getReview());
			record.setSenddate(t.getSenddate());
			record.setTag(t.getTag());
			record.setTitle(t.getTitle());
			record.setType(t.getType());
			record.setVideoId(t.getId());
			record.setVideoReview(t.getVideo_review());
			
			recordList.add(record);
		}

		ApplicationContext applicationContext = ApplicationContextUtil.getSingleton().getContext();
		AnimeMapper animeMapper = applicationContext.getBean(AnimeMapper.class);
		animeMapper.insertByBatch(recordList);
		
		
	}

}
