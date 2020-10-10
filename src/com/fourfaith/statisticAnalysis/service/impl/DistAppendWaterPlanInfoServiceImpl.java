package com.fourfaith.statisticAnalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.statisticAnalysis.dao.DistAppendWaterPlanInfoMapper;
import com.fourfaith.statisticAnalysis.model.RptBaseDistWaterDetail;
import com.fourfaith.statisticAnalysis.service.DistAppendWaterPlanInfoService;

/**
 * @ClassName: DistAppendWaterPlanInfoServiceImpl
 * @Description: 阶梯性配水统计信息service实现
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午6:13:10
 */
@Service("DistAppendWaterPlanInfoService")
public class DistAppendWaterPlanInfoServiceImpl implements DistAppendWaterPlanInfoService{
	
	// 日志记录
	protected Logger logger = Logger.getLogger(DistAppendWaterPlanInfoServiceImpl.class);
	
	@Autowired
	private DistAppendWaterPlanInfoMapper distAppendWaterPlanInfoMapper;

	/**
	 * 获取分页查询总记录数
	 * @param params
	 * 2016年11月7日
	 */
	public Integer getCount(Map<String, Object> params) {
		return distAppendWaterPlanInfoMapper.getCount(params);
	}

	/**
	 * 获取配水统计信息分页列表
	 * @param params
	 * 2016年11月7日
	 */
	public List<RptBaseDistWaterDetail> getList(Map<String, Object> params) {
		return distAppendWaterPlanInfoMapper.getList(params);
	}
	
}