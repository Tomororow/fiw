package com.fourfaith.statisticAnalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.statisticAnalysis.dao.DistWaterPlanInfoMapper;
import com.fourfaith.statisticAnalysis.model.RptBaseDistWaterDetail;
import com.fourfaith.statisticAnalysis.service.DistWaterPlanInfoService;

/**
 * @ClassName: DistWaterPlanInfoServiceImpl
 * @Description: 配水统计信息service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午6:14:00
 */
@Service("DistWaterPlanInfoService")
public class DistWaterPlanInfoServiceImpl implements DistWaterPlanInfoService {

	// 日志记录
	protected Logger logger = Logger.getLogger(DistWaterPlanInfoServiceImpl.class);
	
	@Autowired
	private DistWaterPlanInfoMapper distWaterPlanInfoMapper;

	/**
	 * 获取分页查询总记录数
	 * @param params
	 * 2016年11月7日
	 */
	public Integer getCount(Map<String, Object> params) {
		return distWaterPlanInfoMapper.getCount(params);
	}

	/**
	 * 获取配水统计信息分页列表
	 * @param params
	 * 2016年11月7日
	 */
	public List<RptBaseDistWaterDetail> getList(Map<String, Object> params) {
		return distWaterPlanInfoMapper.getList(params);
	}

	@Override
	public RptBaseDistWaterDetail determineWater(String deviceCode,
			String chargeXl, String chargeSl,int type) {
		return distWaterPlanInfoMapper.determineWater(deviceCode,chargeXl,chargeSl,type);
	}

	@Override
	public List<RptBaseDistWaterDetail> selectBasePlayId(String deviceId) {
		// TODO Auto-generated method stub
		return distWaterPlanInfoMapper.selectBasePlayId(deviceId);
	}
	
}