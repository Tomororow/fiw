package com.fourfaith.sysManage.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.sysManage.dao.BaseDeviceExpandInfoMapper;
import com.fourfaith.sysManage.model.BaseDeviceExpandInfo;
import com.fourfaith.sysManage.service.BaseDeviceExpandInfoService;
import com.fourfaith.utils.AjaxJson;
import com.google.gson.Gson;

/**
 * @ClassName: BaseDeviceExpandInfoServiceImpl
 * @Description: 机井参数service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:37:12
 */
@Service("BaseDeviceExpandInfoService")
public class BaseDeviceExpandInfoServiceImpl implements BaseDeviceExpandInfoService {
	
	protected Logger logger = Logger.getLogger(BaseDeviceExpandInfoServiceImpl.class);
	
	@Autowired
	private BaseDeviceExpandInfoMapper baseDeviceExpandInfoMapper;

	@Override
	public String add(BaseDeviceExpandInfo baseDeviceExpandInfo) {
		String msg = null;
		try {
			int result = baseDeviceExpandInfoMapper.insertBaseDeviceExpandInfo(baseDeviceExpandInfo);
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
	public BaseDeviceExpandInfo findById(String id) {
		return baseDeviceExpandInfoMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public String update(BaseDeviceExpandInfo baseDeviceExpandInfo) {
		String msg = null;
		try {
			int result = baseDeviceExpandInfoMapper.updateByPrimaryKeySelective(baseDeviceExpandInfo);
			if(result>0){
			   msg = "更新成功";
			}else{
			  msg = "更新失败";
			}
			logger.info(msg);			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "更新失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public AjaxJson delBaseDeviceExpandInfo(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(ids!=null){
			String[] idArray = ids.split(",");
			for (int i = 0; i < idArray.length; i++) {
				logContent = logContent+"删除【"+idArray[i]+"】的机井设备扩展信息数据"+",";
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
			int result = baseDeviceExpandInfoMapper.deleteByPrimaryKey(id);
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
	 * 获取设备井实际灌溉地亩数
	 * @param deviceId
	 * 2016年11月28日
	 */
	public String getSjAreaInfo(String deviceId) {
		BaseDeviceExpandInfo bdei = baseDeviceExpandInfoMapper.selectByPrimaryKey(deviceId);
		List<BaseDeviceExpandInfo> bdeiList = new ArrayList<BaseDeviceExpandInfo>();
		bdeiList.add(bdei);
		return new Gson().toJson(bdeiList);
	}

	/**
	 * 根据设备Id查询出所有的机井实际灌溉面积
	 * @param params
	 * 2016年12月4日
	 */
	public List<BaseDeviceExpandInfo> findByDeviceIds(Map<String, Object> params) {
		return baseDeviceExpandInfoMapper.findByDeviceIds(params);
	}
	
	@Override
	public String uniquePlateCode(String devicePlate) {
		List<BaseDeviceExpandInfo> baseDeviceExpandInfos = baseDeviceExpandInfoMapper.uniquePlateCode(devicePlate);
		if(null != baseDeviceExpandInfos && baseDeviceExpandInfos.size() > 0){
			return "failed";
		}else{
			return "success";
		}
	}

	@Override
	public BaseDeviceExpandInfo getSJArea(String deviceCode) {
		return baseDeviceExpandInfoMapper.getSJArea(deviceCode);
	}

	@Override
	public List<BaseDeviceExpandInfo> findSumWater(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseDeviceExpandInfoMapper.findSumWater(params);
	}

}