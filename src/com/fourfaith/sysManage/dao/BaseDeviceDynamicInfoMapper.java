package com.fourfaith.sysManage.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.sysManage.model.BaseDeviceDynamicInfo;

/**
 * @ClassName: BaseDeviceDynamicInfoMapper
 * @Description: 动态信息dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午6:54:32
 */
public interface BaseDeviceDynamicInfoMapper {
	
	
	
	/**
	 * @Title: insertBaseDeviceDynamicInfo
	 * @Description: 添加信息
	 * @param: @param baseDeviceDynamicInfo
	 * @return: int
	 */
	int insertBaseDeviceDynamicInfo(BaseDeviceDynamicInfo baseDeviceDynamicInfo);
	
	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 删除信息
	 * @param: @param id
	 * @return: int
	 */
	int deleteByPrimaryKey(String id);
	
	BaseDeviceDynamicInfo findDynamicInfo(String deviceCode);
	
	int updateDynamicWater(@Param("yearUseWater")BigDecimal yearUseWater,@Param("deviceId")String deviceId);

	/**
	 * 根据Id查询详细信息
	 * @param id
	 * 2016年12月6日
	 */
	BaseDeviceDynamicInfo findById(String id);

	/**
	 * 根据水管区域ID查询所有该区域下的机井实时数据
	 * @param params
	 * 2017年1月9日
	 */
	List<BaseDeviceDynamicInfo> selectBaseDeviceDynamicInfo(Map<String, Object> params);
	
	/**
	 * @Title: updateDynamicInfo
	 * @Description: 修改动态用水表信息(仅DeviceCode)
	 * @param baseDeviceDynamicInfo
	 * @return: int
	 * @Author: zhaojx
	 * @Date: 2017年10月31日 下午5:03:28
	 */
	int updateDynamicInfo(BaseDeviceDynamicInfo baseDeviceDynamicInfo);
	/**
	 * @Title: getRealTimeByDeviceCode
	 * @Description:根据机井编码查询机井实时数据
	 * @param deviceCode
	 * @return
	 * @return BaseDeviceDynamicInfo
	 * @author 刘海年
	 * @date 2018年9月9日下午3:38:38
	 */
	BaseDeviceDynamicInfo getRealTimeByDeviceCode(String deviceCode);
	
	/**
	 * 启动项目将所有设备重置成离线状态
	 * @return
	 */
	int updateDeviceType();
}