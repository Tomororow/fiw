package com.fourfaith.waterRightManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.waterRightManage.model.BaseDistWaterPlanDevice;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;

/**
 * @ClassName: BaseDistWaterPlanDeviceService
 * @Description: 配水service接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:57:10
 */
public interface BaseDistWaterPlanDeviceService {

	/**
	 * 根据配水Id获取配水机井设备信息
	 * @param plan
	 * 2016年10月28日
	 */
	List<BaseDistWaterPlanDevice> showDistWaterDeviceInfo(BaseDistWaterPlan plan);

	/**
	 * 检查在水权配水管理中，是否包含该设备机井信息
	 * @param params
	 * 2016年11月7日
	 */
	List<BaseDistWaterPlanDevice> selectBaseDistWaterPlanDeviceList(
			Map<String, Object> params);
	List<BaseDistWaterPlanDevice> selectBaseDistList(String userId,String baseDeviceId);
}