package com.fourfaith.statisticAnalysis.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.statisticAnalysis.model.Cardhistory;

public interface CardhistoryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Cardhistory record);

    int insertSelective(Cardhistory record);

    Cardhistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cardhistory record);

    int updateByPrimaryKey(Cardhistory record);
    
    List<Cardhistory> requestHistoryQuery(Map<String,Object> params);
}