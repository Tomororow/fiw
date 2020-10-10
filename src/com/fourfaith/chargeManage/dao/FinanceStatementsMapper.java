package com.fourfaith.chargeManage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.chargeManage.model.FinanceStatements;

public interface FinanceStatementsMapper {
    int deleteByPrimaryKey(String id);

    int insert(FinanceStatements record);

    int insertSelective(FinanceStatements record);

    FinanceStatements selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinanceStatements record);

    int updateByPrimaryKey(FinanceStatements record);
    
    List<FinanceStatements> selectAll();
    
    List<FinanceStatements> deviceNameLike(Map<String,Object> pageInfo);    
    
    List<FinanceStatements> monthYearLike(@Param("cretime")String cretime,@Param("userid")String userid);
    
    List<FinanceStatements> getRegistMsgByCondition(@Param("beginTime")String beginTime,@Param("endTime")String endTime);

    List<FinanceStatements> inforList(Map<String,Object> list);
    
    List<FinanceStatements> selectotelMoney(@Param("deviceCode")String deviceCode,@Param("userid")String userid );

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
	List<FinanceStatements> findByCondition(@Param("deviceName")String deviceName,
			@Param("startTime")String startTime, @Param("endTime")String endTime,  @Param("distYear")String distYear,
			@Param("distRound")String distRound, @Param("userId")String userId);
}