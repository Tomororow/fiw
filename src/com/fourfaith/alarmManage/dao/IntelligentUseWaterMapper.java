package com.fourfaith.alarmManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.alarmManage.model.IntelligentUseWater;

/**
 * @ClassName: IntelligentUseWaterMapper
 * @Description: 智能用水分析
 * @Author: xiaogaoxiang
 * @Date: 2017年1月10日
 */
public interface IntelligentUseWaterMapper {

	/**
	 * TODO: 新增保存用水量信息
	 * @param intelligentUseWater
	 */
	void add(IntelligentUseWater intelligentUseWater);

	/**
	 * TODO: 根据设备ID查询智能机井用水统计表集合
	 * @param params
	 * 2017年1月10日
	 * Administrator: xiaogaoxiang
	 */
	List<IntelligentUseWater> selectIntelligentUseWaterByDeviceCode(Map<String, Object> params);

	/**
	 * TODO: 删除智能机井统计表中信息根据deviceCode
	 * @param deviceCode
	 * 2017年1月10日
	 * Administrator: xiaogaoxiang
	 */
	void delete(String deviceCode);

}