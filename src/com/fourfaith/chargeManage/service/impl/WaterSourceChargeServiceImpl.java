package com.fourfaith.chargeManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.chargeManage.dao.WaterSourceChargeMapper;
import com.fourfaith.chargeManage.model.WaterPriceAmountVO;
import com.fourfaith.chargeManage.model.WaterSourceCharge;
import com.fourfaith.chargeManage.service.WaterSourceChargeService;

/**
 * @ClassName: WaterSourceChargeServiceImpl
 * @Description: 水资源费统计Service实现类
 * @Author: zhaojx
 * @Date: 2017年9月8日 下午3:42:58
 */
@Service("WaterSourceChargeService")
public class WaterSourceChargeServiceImpl implements WaterSourceChargeService{
	
	@Autowired
	private WaterSourceChargeMapper waterSourceChargeMapper;

	@Override
	public Integer getCount(Map<String, Object> params) {
		return waterSourceChargeMapper.getCount(params);
	}
	
	@Override
	public List<WaterSourceCharge> getWaterSourceList(Map<String, Object> params) {
		return waterSourceChargeMapper.getWaterSourceList(params);
	}

	@Override
	public List<WaterSourceCharge> getPriceAmount(Map<String, Object> params) {
		return waterSourceChargeMapper.getPriceAmount(params);
	}

	@Override
	public List<WaterPriceAmountVO> getWaterAmountExcelList(
			Map<String, Object> params) {
		return waterSourceChargeMapper.getWaterAmountExcelList(params);
	}

}
