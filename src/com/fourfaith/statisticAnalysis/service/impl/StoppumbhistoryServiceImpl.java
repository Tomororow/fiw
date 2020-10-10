package com.fourfaith.statisticAnalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.statisticAnalysis.dao.StoppumbhistoryMapper;
import com.fourfaith.statisticAnalysis.model.Stoppumbhistory;
import com.fourfaith.statisticAnalysis.service.StoppumbhistoryService;
@Service("stoppumbhistoryService")
public class StoppumbhistoryServiceImpl implements StoppumbhistoryService {

	@Autowired
	StoppumbhistoryMapper stoppumbhistoryMapper;

	@Override
	public List<Stoppumbhistory> selectByTimeList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return stoppumbhistoryMapper.selectByTimeList(params);
	}
	
}
