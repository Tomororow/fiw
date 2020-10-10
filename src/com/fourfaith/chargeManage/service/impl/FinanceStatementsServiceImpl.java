package com.fourfaith.chargeManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.chargeManage.dao.FinanceStatementsMapper;
import com.fourfaith.chargeManage.model.FinanceStatements;
import com.fourfaith.chargeManage.service.FinanceStatementsService;


@Service("financeStatementsService")
public class FinanceStatementsServiceImpl implements FinanceStatementsService {

	@Autowired
	private FinanceStatementsMapper financeStatementsMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		
		return financeStatementsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(FinanceStatements record) {
		
		return financeStatementsMapper.insert(record);
	}

	@Override
	public int insertSelective(FinanceStatements record) {
		
		return financeStatementsMapper.insertSelective(record);
	}

	@Override
	public FinanceStatements selectByPrimaryKey(String id) {
		
		return financeStatementsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FinanceStatements record) {
		
		return financeStatementsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FinanceStatements record) {
		
		return financeStatementsMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<FinanceStatements> selectAll() {
		return financeStatementsMapper.selectAll();
	}

	@Override
	public List<FinanceStatements> deviceNameLike(Map<String,Object> pageInfo) {
		
		return financeStatementsMapper.deviceNameLike(pageInfo);
	}

	@Override
	public List<FinanceStatements> monthYearLike(String cretime, String userid) {
		return financeStatementsMapper.monthYearLike(cretime, userid);
	}

	@Override
	public List<FinanceStatements> getRegistMsgByCondition(String beginTime,String endTime) {
		return financeStatementsMapper.getRegistMsgByCondition(beginTime, endTime);
	}

	@Override
	public List<FinanceStatements> inforList(Map<String,Object> pageInfo) {
		return financeStatementsMapper.inforList(pageInfo);
	}

	@Override
	public List<FinanceStatements> selectotelMoney(String deviceCode,String userid) {
		return financeStatementsMapper.selectotelMoney(deviceCode,userid);
	}

	@Override
	public List<FinanceStatements> findByCondition(String deviceName,
			String startTime, String endTime, String distYear,
			String distRound, String userId) {
		return financeStatementsMapper.findByCondition(deviceName,startTime,endTime,distYear,distRound,userId);
	}



	

	
}
