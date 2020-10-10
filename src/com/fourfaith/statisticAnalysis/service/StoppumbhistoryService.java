package com.fourfaith.statisticAnalysis.service;

import java.util.List;


import java.util.Map;

import com.fourfaith.statisticAnalysis.model.Stoppumbhistory;

public interface StoppumbhistoryService {
	
	List<Stoppumbhistory> selectByTimeList(Map<String, Object> params);

}
