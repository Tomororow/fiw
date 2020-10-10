package com.fourfaith.sysManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.TripshistoryMapper;
import com.fourfaith.sysManage.model.Tripshistory;
import com.fourfaith.sysManage.service.TripshistoryService;
@Service("tripshistoryService")
public class TripshistoryServiceImpl implements TripshistoryService {

	@Autowired
	TripshistoryMapper tripshistoryMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Tripshistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Tripshistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tripshistory selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Tripshistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Tripshistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Tripshistory> findTripHistory(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return tripshistoryMapper.findTripHistory(params);
	}

	@Override
	public List<Tripshistory> abnormalTrip() {
		// TODO Auto-generated method stub
		return tripshistoryMapper.abnormalTrip();
	}

}
