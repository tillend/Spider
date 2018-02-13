package com.lin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lin.model.Bangumi;

@Repository("bangumiMapper")
public interface BangumiMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Bangumi record);

	int insertSelective(Bangumi record);

	Bangumi selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Bangumi record);

	int updateByPrimaryKey(Bangumi record);

	int insertByBatch(List<Bangumi> recordList);
	
	int updateBySeasonIdSelective(Bangumi record);
	
	List<Bangumi> selectAllSeasonId();
}