package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.Tripshistory;

public interface TripshistoryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Tripshistory record);

    int insertSelective(Tripshistory record);

    Tripshistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tripshistory record);

    int updateByPrimaryKey(Tripshistory record);
    
    List<Tripshistory> findTripHistory(Map<String,Object> params);
    
    List<Tripshistory> abnormalTrip();
    
    
}