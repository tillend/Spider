package com.lin.dao;

import java.util.List;

import com.lin.model.BlogExpert;

public interface BlogExpertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogExpert record);

    int insertSelective(BlogExpert record);

    BlogExpert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogExpert record);

    int updateByPrimaryKey(BlogExpert record);
    
    int insertByBatch(List<BlogExpert> recordList);
}