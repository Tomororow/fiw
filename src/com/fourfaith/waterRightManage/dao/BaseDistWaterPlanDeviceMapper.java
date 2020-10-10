package com.fourfaith.waterRightManage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.waterRightManage.model.BaseDistWaterPlanDevice;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;

/**
 * @ClassName: BaseDistWaterPlanDeviceMapper
 * @Description: 配水dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:51:00
 */
public interface BaseDistWaterPlanDeviceMapper {

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
	
	List<BaseDistWaterPlanDevice> selectBaseDistList(@Param("userId")String userId,@Param("baseDeviceId")String baseDeviceId );
}