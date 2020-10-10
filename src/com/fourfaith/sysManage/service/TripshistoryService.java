package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.Tripshistory;

public interface TripshistoryService {
	   int deleteByPrimaryKey(Integer id);

	    int insert(Tripshistory record);

	    int insertSelective(Tripshistory record);

	    Tripshistory selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(Tripshistory record);

	    int updateByPrimaryKey(Tripshistory record);
	    
	    List<Tripshistory> findTripHistory(Map<String,Object> params);
	    
	    List<Tripshistory> abnormalTrip();
}
