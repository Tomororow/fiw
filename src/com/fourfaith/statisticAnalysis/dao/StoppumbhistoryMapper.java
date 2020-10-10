package com.fourfaith.statisticAnalysis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.statisticAnalysis.model.Stoppumbhistory;

public interface StoppumbhistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stoppumbhistory record);

    int insertSelective(Stoppumbhistory record);

    Stoppumbhistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stoppumbhistory record);

    int updateByPrimaryKey(Stoppumbhistory record);
    
    List<Stoppumbhistory> selectByTimeList(Map<String, Object> params);
}