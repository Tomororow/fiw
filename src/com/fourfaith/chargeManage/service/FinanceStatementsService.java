package com.fourfaith.chargeManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.chargeManage.model.FinanceStatements;

public interface FinanceStatementsService {
	int deleteByPrimaryKey(String id);

	int insert(FinanceStatements record);
	
	int insertSelective(FinanceStatements record);
	
	FinanceStatements selectByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(FinanceStatements record);
	
	int updateByPrimaryKey(FinanceStatements record);
	
	List<FinanceStatements> selectAll();
	
	List<FinanceStatements> deviceNameLike(Map<String,Object> pageInfo);    
	
	List<FinanceStatements> monthYearLike(String cretime,String userid);
	
	List<FinanceStatements> getRegistMsgByCondition(String beginTime,String endTime);
	
	List<FinanceStatements> inforList(Map<String,Object> pageInfo);
	
	List<FinanceStatements> selectotelMoney(String deviceCode,String userid);

	 /**
	  * @Title: findByCondition
	  * @Description:  条件查询财务报表
	  * @param deviceName
	  * @param startTime
	  * @param endTime
	  * @param distYear
	  * @param distRound
	  * @param userId
	  * @return
	  * @return List<FinanceStatements>
	  * @author 刘海年
	  * @date 2018年10月25日下午5:48:50
	  */
	List<FinanceStatements> findByCondition(String deviceName,
			String startTime, String endTime, String distYear,
			String distRound, String userId);
}