package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.BaseDeviceExpandInfo;

/**
 * @ClassName: BaseDeviceExpandInfoMapper
 * @Description: 机井参数dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午6:58:31
 */
public interface BaseDeviceExpandInfoMapper {
	
	/**
	 * @Title: insertBaseDeviceExpandInfo
	 * @Description: 新增信息
	 * @param: @param baseDeviceExpandInfo
	 * @return: int
	 */
	int insertBaseDeviceExpandInfo(BaseDeviceExpandInfo baseDeviceExpandInfo);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询信息
	 * @param: @param id
	 * @return: BaseDeviceExpandInfo
	 */
	BaseDeviceExpandInfo selectByPrimaryKey(String id);
	
	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 更新信息
	 * @param: @param baseDeviceExpandInfo
	 * @return: int
	 */
	int updateByPrimaryKeySelective(BaseDeviceExpandInfo baseDeviceExpandInfo);
	
	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 删除信息
	 * @param: @param id
	 * @return: int
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 根据设备Id查询出所有的机井实际灌溉面积
	 * @param params
	 * 2016年12月4日
	 * Administrator: xiaogaoxiang
	 */
	List<BaseDeviceExpandInfo> findByDeviceIds(Map<String, Object> params);
	
	/**
	 * @Title: uniquePlateCode
	 * @Description: 机井编号  唯一校验
	 * @param: @param devicePlate
	 * @return: String
	 */
	List<BaseDeviceExpandInfo> uniquePlateCode(String devicePlate);

	/**
	 * @Title: getSJArea
	 * @Description:根据机井编号查询机井实际灌溉面积
	 * @param deviceCode
	 * @return
	 * @return BaseDeviceExpandInfo
	 * @author 刘海年
	 * @date 2018年9月16日下午5:28:35
	 */
	BaseDeviceExpandInfo getSJArea(String deviceCode);
	
	List<BaseDeviceExpandInfo> findSumWater(Map<String, Object> params);
	
}