package com.fourfaith.statisticAnalysis.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.statisticAnalysis.model.RptBaseDistWaterDetail;

/**
 * @ClassName: DistWaterPlanInfoService
 * @Description: 配水统计信息service接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午6:07:02
 */
public interface DistWaterPlanInfoService {

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

	/**
	 * @Title: determineWater
	 * @Description:判断本次购买的水方量是否超出额定水方量
	 * @param deviceCode
	 * @param chargeXl
	 * @param chargeSl
	 * @return
	 * @return RptBaseDistWaterDetail
	 * @author 刘海年
	 * @date 2018年9月16日下午4:49:25
	 */
	RptBaseDistWaterDetail determineWater(String deviceCode, String chargeXl,String chargeSl,int type);
	
	List<RptBaseDistWaterDetail> selectBasePlayId(String deviceId);

}