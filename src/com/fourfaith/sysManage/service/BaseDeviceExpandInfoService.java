package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.BaseDeviceExpandInfo;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: BaseDeviceExpandInfoService
 * @Description: 机井参数service接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:26:16
 */
public interface BaseDeviceExpandInfoService{

    String add(BaseDeviceExpandInfo baseDeviceExpandInfo);
    
    BaseDeviceExpandInfo findById(String id);
    
    String update(BaseDeviceExpandInfo baseDeviceExpandInfo);
	
	AjaxJson delBaseDeviceExpandInfo(String ids);
	
	String delete(String id);

	/**
	 * 获取设备井实际灌溉地亩数
	 * @param deviceId
	 * 2016年11月28日
	 */
	String getSjAreaInfo(String deviceId);

	/**
	 * 根据设备Id查询出所有的机井实际灌溉面积
	 * @param params
	 * 2016年12月4日
	 */
	List<BaseDeviceExpandInfo> findByDeviceIds(Map<String, Object> params);
	
	/**
	 * @Title: uniquePlateCode
	 * @Description: 机井号牌  唯一校验
	 * @param: @param devicePlate
	 * @return: String
	 */
	String uniquePlateCode(String devicePlate);

	/**
	 * @Title: getSJArea
	 * @Description:查询实际灌溉面积
	 * @param deviceCode
	 * @return
	 * @return BaseDeviceExpandInfo
	 * @author 刘海年
	 * @date 2018年9月16日下午5:27:34
	 */
	BaseDeviceExpandInfo getSJArea(String deviceCode);
	
	List<BaseDeviceExpandInfo> findSumWater(Map<String, Object> params);
	
}