package com.fourfaith.waterRightManage.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.waterRightManage.dao.BaseDistWaterPlanDeviceMapper;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlanDevice;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;
import com.fourfaith.waterRightManage.service.BaseDistWaterPlanDeviceService;

/**
 * @ClassName: BaseDistWaterPlanDeviceServiceImpl
 * @Description: 配水service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:58:14
 */
@Service("BaseDistWaterPlanDeviceService")
public class BaseDistWaterPlanDeviceServiceImpl implements BaseDistWaterPlanDeviceService{

	protected Logger logger = Logger.getLogger(BaseDistWaterPlanDeviceServiceImpl.class);
	
	@Autowired
	private BaseDistWaterPlanDeviceMapper BaseDistWaterPlanDeviceMapper;

	/**
	 * 根据配水Id获取配水机井设备信息
	 * @param plan
	 * 2016年10月28日
	 */
	public List<BaseDistWaterPlanDevice> showDistWaterDeviceInfo(
			BaseDistWaterPlan plan) {
		return BaseDistWaterPlanDeviceMapper.showDistWaterDeviceInfo(plan);
	}

	/**
	 * 检查在水权配水管理中，是否包含该设备机井信息
	 * @param params
	 * 2016年11月7日
	 */
	public List<BaseDistWaterPlanDevice> selectBaseDistWaterPlanDeviceList(
			Map<String, Object> params) {
		return BaseDistWaterPlanDeviceMapper.selectBaseDistWaterPlanDeviceList(params);
	}

	@Override
	public List<BaseDistWaterPlanDevice> selectBaseDistList(String userId,
			String baseDeviceId) {
		// TODO Auto-generated method stub
		return BaseDistWaterPlanDeviceMapper.selectBaseDistList(userId, baseDeviceId);
	}
	
}