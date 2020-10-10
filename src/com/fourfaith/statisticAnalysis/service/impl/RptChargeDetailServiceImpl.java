package com.fourfaith.statisticAnalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.statisticAnalysis.dao.RptChargeDetailMapper;
import com.fourfaith.statisticAnalysis.model.RptChargeDetail;
import com.fourfaith.statisticAnalysis.model.RptChargeDetailVO;
import com.fourfaith.statisticAnalysis.service.RptChargeDetailService;

import common.Logger;

/**
 * @ClassName: RptChargeDetailServiceImpl
 * @Description: 水卡充值记录service实现
 * @Author: zhaojx
 * @Date: 2018年4月13日 下午3:56:06
 */
@Service("rptChargeDetailService")
public class RptChargeDetailServiceImpl implements RptChargeDetailService{

	protected Logger logger = Logger.getLogger(RptChargeDetailServiceImpl.class);
	
	@Autowired
	private RptChargeDetailMapper rptChargeDetailMapper;

	@Override
	public List<RptChargeDetail> getChargeList(Map<String, Object> params) {
		return rptChargeDetailMapper.getChargeList(params);
	}
	
	@Override
	public Integer getChargeCount(Map<String, Object> params) {
		return rptChargeDetailMapper.getChargeCount(params);
	}

	@Override
	public List<RptChargeDetailVO> getExcelList(Map<String, Object> params) {
		return rptChargeDetailMapper.getExcelList(params);
	}

	@Override
	public int insertSelective(RptChargeDetail rptChargeDetail) {
		return rptChargeDetailMapper.insertSelective(rptChargeDetail);
	}

	@Override
	public String getCommonWater(String deviceCode, String chargeXl,
			String chargeSl) {
		return rptChargeDetailMapper.getCommonWater(deviceCode,chargeXl,chargeSl);
	}

	@Override
	public RptChargeDetail getChargeAndWaterAmount(Map<String, Object> params) {
		return rptChargeDetailMapper.getChargeAndWaterAmount(params);
	}

	@Override
	public RptChargeDetail getChargeAndWaterSumByMonth(Map<String, Object> params) {
		return rptChargeDetailMapper.getChargeAndWaterSumByMonth(params);
	}

}