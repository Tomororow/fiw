package com.fourfaith.statisticAnalysis.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.statisticAnalysis.model.RptBaseDistWaterDetail;

/**
 * @ClassName: DistAppendWaterPlanInfoMapper
 * @Description: 阶梯性配水统计信息dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午4:58:50
 */
public interface DistAppendWaterPlanInfoMapper {
	
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