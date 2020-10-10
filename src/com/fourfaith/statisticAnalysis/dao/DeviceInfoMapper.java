package com.fourfaith.statisticAnalysis.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.statisticAnalysis.model.RptBaseDeviceDetail;

/**
 * @ClassName: DeviceInfoMapper
 * @Description: 机井基础信息dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午4:58:22
 */
public interface DeviceInfoMapper {

	/**
	 * 分页查询记录条数
	 * @param params
	 * 2016年11月6日
	 * Administrator: xiaogaoxiang
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * 获取设备机井基础信息历史数据
	 * @param params
	 * 2016年11月6日
	 * Administrator: xiaogaoxiang
	 */
	List<RptBaseDeviceDetail> getList(Map<String, Object> params);
	
	/**
	 * @Title: selectDeviceCodeByDetail
	 * @Description:根据机井编查询机井信息
	 * @param deviceCode
	 * @return
	 * @return RptBaseDeviceDetail
	 * @author 刘海年
	 * @date 2018年10月15日上午11:27:42
	 */
	RptBaseDeviceDetail selectDeviceCodeByDetail(String deviceCode);

}