package com.fourfaith.statisticAnalysis.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.statisticAnalysis.model.RptBaseDistWaterDetail;

/**
 * @ClassName: DistAppendWaterPlanInfoService
 * @Description: 阶梯性配水统计信息service接口
 * @Author: zhaojx
 * @Date: 2018年3月15日 下午6:06:29
 */
public interface DistAppendWaterPlanInfoService {
	
	/**
	 * 获取分页查询总记录数
	 * @param params
	 * 2016年11月7日
	 * Administrator: xiaogaoxiang
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * 获取配水统计信息分页列表
	 * @param params
	 * 2016年11月7日
	 * Administrator: xiaogaoxiang
	 */
	List<RptBaseDistWaterDetail> getList(Map<String, Object> params);

}