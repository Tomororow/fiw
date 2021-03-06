package com.fourfaith.statisticAnalysis.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.statisticAnalysis.model.RptChargeDetail;
import com.fourfaith.statisticAnalysis.model.RptChargeDetailVO;

/**
 * @ClassName: RptChargeDetailService
 * @Description: 水卡充值记录service接口
 * @Author: zhaojx
 * @Date: 2018年4月13日 下午3:53:07
 */
public interface RptChargeDetailService {

	/**
	 * @Title: getChargeList
	 * @Description: 水卡充值记录分页查询
	 * @param: @param params
	 * @return: List<RptChargeDetail>
	 */
	List<RptChargeDetail> getChargeList(Map<String, Object> params);
	
	/**
	 * @Title: getChargeCount
	 * @Description: 水卡充值记录条数统计
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getChargeCount(Map<String, Object> params);
	
	/**
	 * @Title: getExcelList
	 * @Description: 充值记录报表导出信息查询
	 * @param: @param params
	 * @return: List<RptChargeDetailVO>
	 */
	List<RptChargeDetailVO> getExcelList(Map<String, Object> params);
	
	/**
	 * @Title: insertSelective
	 * @Description:添加售水记录
	 * @param rptChargeDetail
	 * @return
	 * @return int
	 * @author 刘海年
	 * @date 2018年9月17日上午9:00:28
	 */
	int insertSelective(RptChargeDetail rptChargeDetail);
	
	
	/**
	 * @Title: getChargeAndWaterAmount
	 * @Description: 获取充值水量和总金额
	 * @param: @param params
	 * @return: RptChargeDetail
	 * @Author: zhaojinxin
	 */
	RptChargeDetail getChargeAndWaterAmount(Map<String, Object> params);
	
	/**
	 * @Title: getChargeAndWaterSumByMonth
	 * @Description: 根据月份获取充值水量和总金额
	 * @param: @param params
	 * @return: RptChargeDetail
	 * @Author: zhaojinxin
	 */
	RptChargeDetail getChargeAndWaterSumByMonth(Map<String, Object> params);

	/**
	 * @Title: getCommonWater
	 * @Description:查询已经配过的水量
	 * @param deviceCode
	 * @param chargeXl
	 * @param chargeSl
	 * @return
	 * @return String
	 * @author 刘海年
	 * @date 2018年9月17日上午11:00:54
	 */
	String getCommonWater(String deviceCode, String chargeXl, String chargeSl);

}