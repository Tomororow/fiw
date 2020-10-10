package com.fourfaith.statisticAnalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.statisticAnalysis.dao.CardhistoryMapper;
import com.fourfaith.statisticAnalysis.model.Cardhistory;
import com.fourfaith.statisticAnalysis.service.CardhistoryService;
@Service("cardhistoryService")
public class CardhistoryServiceImpl implements CardhistoryService {

	@Autowired
	CardhistoryMapper cardhistoryMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Cardhistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Cardhistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cardhistory selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Cardhistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Cardhistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cardhistory> requestHistoryQuery(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return cardhistoryMapper.requestHistoryQuery(params);
	}

}
