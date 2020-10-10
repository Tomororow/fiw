package com.fourfaith.alarmManage.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.alarmManage.dao.IntelligentUseWaterMapper;
import com.fourfaith.alarmManage.model.IntelligentUseWater;
import com.fourfaith.alarmManage.service.IntelligentUseWaterService;

/**
 * @ClassName: IntelligentUseWaterServiceImpl
 * @Description: 智能用水分析service实现
 * 2017年1月10日
 * Administrator: xiaogaoxiang
 */
@Service("IntellingentUseWaterService")
public class IntelligentUseWaterServiceImpl implements IntelligentUseWaterService{

	protected Logger logger = Logger.getLogger(IntelligentUseWaterServiceImpl.class);
	
	@Autowired
	private IntelligentUseWaterMapper intelligentUseWaterMapper;

	/**
	 * TODO: 新增保存用水量信息
	 * @param intelligentUseWater
	 */
	public void add(IntelligentUseWater intelligentUseWater) {
		intelligentUseWaterMapper.add(intelligentUseWater);
	}

	/**
	 * TODO: 根据设备ID查询智能机井用水统计表集合
	 * @param params
	 */
	public List<IntelligentUseWater> selectIntelligentUseWaterByDeviceCode(Map<String, Object> params) {
		return intelligentUseWaterMapper.selectIntelligentUseWaterByDeviceCode(params);
	}

	/**
	 * TODO: 删除智能机井统计表中信息根据deviceCode
	 * @param deviceCode
	 */
	public void delete(String deviceCode) {
		intelligentUseWaterMapper.delete(deviceCode);
	}
	
}