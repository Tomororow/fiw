package com.fourfaith.sysManage.service.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.sysManage.dao.BaseDeviceDynamicInfoMapper;
import com.fourfaith.sysManage.model.BaseDeviceDynamicInfo;
import com.fourfaith.sysManage.service.BaseDeviceDynamicInfoService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: BaseDeviceDynamicInfoServiceImpl
 * @Description: 动态信息service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:36:32
 */
@Service("BaseDeviceDynamicInfoService")
public class BaseDeviceDynamicInfoServiceImpl implements BaseDeviceDynamicInfoService {
	
	protected Logger logger = Logger.getLogger(BaseDeviceDynamicInfoServiceImpl.class);
	
	@Autowired
	private BaseDeviceDynamicInfoMapper baseDeviceDynamicInfoMapper;

	@Override
	public String add(BaseDeviceDynamicInfo baseDeviceDynamicInfo) {
		String msg = null;
		try {
			int result = baseDeviceDynamicInfoMapper.insertBaseDeviceDynamicInfo(baseDeviceDynamicInfo);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public AjaxJson delBaseDeviceDynamicInfo(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(ids!=null){
			String[] idArray = ids.split(",");
			for (int i = 0; i < idArray.length; i++) {
				logContent = logContent+"删除【"+idArray[i]+"】的机井设备动态信息数据"+",";
				delete(idArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}
	
	@Override
	public String delete(String id) {
		String msg = null;
		try {
			int result = baseDeviceDynamicInfoMapper.deleteByPrimaryKey(id);
			if(result>0){
				msg = "删除成功";
			}else{
			    msg = "删除失败";
			}
			logger.info(msg);			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	/**
	 * 根据Id查询详细信息
	 * @param id
	 * 2016年12月6日
	 */
	public BaseDeviceDynamicInfo findById(String id) {
		return baseDeviceDynamicInfoMapper.findById(id);
	}

	/**
	 * 根据水管区域ID查询所有该区域下的机井实时数据
	 * @param params
	 * 2017年1月9日
	 */
	public List<BaseDeviceDynamicInfo> selectBaseDeviceDynamicInfo(
			Map<String, Object> params) {
		return baseDeviceDynamicInfoMapper.selectBaseDeviceDynamicInfo(params);
	}

	/**
	 * TODO :修改动态用水表信息(仅DeviceCode)
	 * @param params
	 * 2017年10月31日
	 * Administrator : zhaojinxin
	 */
	@Override
	public int updateDynamicInfo(BaseDeviceDynamicInfo baseDeviceDynamicInfo) {
		return baseDeviceDynamicInfoMapper.updateDynamicInfo(baseDeviceDynamicInfo);
	}

	/**
	 * @Title: getRealTimeByDeviceCode
	 * @Description:根据机井编码查询机井实时数据
	 * @param deviceCode
	 * @return
	 * @return BaseDeviceDynamicInfo
	 * @author 刘海年
	 * @date 2018年9月9日下午3:38:38
	 */
	@Override
	public BaseDeviceDynamicInfo getRealTimeByDeviceCode(String deviceCode) {
		return baseDeviceDynamicInfoMapper.getRealTimeByDeviceCode(deviceCode);
	}

	@Override
	public int updateDeviceType() {
		// TODO Auto-generated method stub
		return baseDeviceDynamicInfoMapper.updateDeviceType();
	}

	@Override
	public BaseDeviceDynamicInfo findDynamicInfo(String deviceCode) {
		// TODO Auto-generated method stub
		return baseDeviceDynamicInfoMapper.findDynamicInfo(deviceCode);
	}

	@Override
	public int updateDynamicWater(BigDecimal yearUseWater, String deviceId) {
		// TODO Auto-generated method stub
		return baseDeviceDynamicInfoMapper.updateDynamicWater(yearUseWater, deviceId);
	}

}