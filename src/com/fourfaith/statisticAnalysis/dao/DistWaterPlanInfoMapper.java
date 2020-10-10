package com.fourfaith.statisticAnalysis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.statisticAnalysis.model.RptBaseDistWaterDetail;

/**
 * @ClassName: DistWaterPlanInfoMapper
 * @Description: 配水统计信息dao接口
 * @Author: zhaojx
 * @Date: 2018年3月15日 下午4:59:37
 */
public interface DistWaterPlanInfoMapper {

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
	 * @date 2018年9月16日下午4:50:55
	 */
	RptBaseDistWaterDetail determineWater(@Param("deviceCode")String deviceCode, @Param("chargeXl")String chargeXl,
			@Param("chargeSl")String chargeSl ,@Param("type")int type);
	
	/**
	 * 查询当前的机井的配水信息
	 */
	List<RptBaseDistWaterDetail> selectBasePlayId(@Param("deviceId")String deviceId);

}