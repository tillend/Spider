package com.lin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lin.model.Anime;

@Repository("animeMapper")
public interface AnimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Anime record);

    int insertSelective(Anime record);

    Anime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Anime record);

    int updateByPrimaryKey(Anime record);
    
    int insertByBatch(List<Anime> recordList);
}