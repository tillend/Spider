package com.lin.pipeline;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lin.dao.AnimeMapper;
import com.lin.model.Anime;
import com.lin.utils.DateUtil;
import com.lin.vo.AnimeVO;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Component("AnimeListDaoPipeline")
public class AnimeListDaoPipeline implements PageModelPipeline<List<AnimeVO>> {
	
    @Resource
    private AnimeMapper animeMapper;

	@Override
	public void process(List<AnimeVO> list, Task task) {
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
			record.setPlay(t.getPlay());
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

		animeMapper.insertByBatch(recordList);
		
		
	}

}
