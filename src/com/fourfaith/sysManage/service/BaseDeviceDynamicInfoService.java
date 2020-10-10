package com.fourfaith.sysManage.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.sysManage.model.BaseDeviceDynamicInfo;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: BaseDeviceDynamicInfoService
 * @Description: 动态信息service接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:24:49
 */
public interface BaseDeviceDynamicInfoService{

	/**
	 * @Title: add
	 * @Description: 新增动态用水信息
	 * @param baseDeviceDynamicInfo
	 * @return: String
	 * @Author: zhaojx
	 * @Date: 2017年10月31日 下午5:08:52
	 */
    String add(BaseDeviceDynamicInfo baseDeviceDynamicInfo);
    
    BaseDeviceDynamicInfo findDynamicInfo(String deviceCode);
	
	int updateDynamicWater(BigDecimal yearUseWater,String deviceId);
    
    /**
     * @Title: delBaseDeviceDynamicInfo
     * @Description: 删除动态用水信息(批量删除)
     * @param ids
     * @return: AjaxJson
     * @Author: zhaojx
     * @Date: 2017年10月31日 下午5:09:18
     */
    AjaxJson delBaseDeviceDynamicInfo(String ids);
	
    /**
     * @Title: delete
     * @Description: 删除动态用水信息
     * @param id
     * @return: String
     * @Author: zhaojx
     * @Date: 2017年10月31日 下午5:11:12
     */
	String delete(String id);

	/**
	 * 根据Id查询详细信息
	 * TODO :
	 * @param id
	 * @return
	 * 2016年12月6日
	 * Administrator : xiaogaoxiang
	 */
	BaseDeviceDynamicInfo findById(String id);

	/**
	 * 根据水管区域ID查询所有该区域下的机井实时数据
	 * TODO :
	 * @param params
	 * @return
	 * 2017年1月9日
	 * Administrator : xiaogaoxiang
	 */
	List<BaseDeviceDynamicInfo> selectBaseDeviceDynamicInfo(Map<String, Object> params);
	
	/**
	 * @Title: updateDynamicInfo
	 * @Description: 修改动态用水表信息(仅DeviceCode)
	 * @param baseDeviceDynamicInfo
	 * @return: int
	 * @Author: zhaojx
	 * @Date: 2017年10月31日 下午5:05:25
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
	
	int updateDeviceType();
	
}