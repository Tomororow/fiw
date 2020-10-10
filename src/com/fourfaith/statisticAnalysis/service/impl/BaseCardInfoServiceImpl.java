package com.fourfaith.statisticAnalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.statisticAnalysis.dao.BaseCardInfoMapper;
import com.fourfaith.statisticAnalysis.model.BaseCardInfo;
import com.fourfaith.statisticAnalysis.model.BaseCardInfoVO;
import com.fourfaith.statisticAnalysis.service.BaseCardInfoService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: BaseCardInfoServiceImpl
 * @Description: 水卡信息service实现
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午6:11:12
 */
@Service("BaseCardInfoService")
public class BaseCardInfoServiceImpl implements BaseCardInfoService{

	protected Logger logger = Logger.getLogger(BaseCardInfoServiceImpl.class);
	
	@Autowired
	private BaseCardInfoMapper baseCardInfoMapper;

	@Override
	public Integer getCount(Map<String, Object> params) {
		return baseCardInfoMapper.getCount(params);
	}

	@Override
	public List<BaseCardInfo> getList(Map<String, Object> params) {
		return baseCardInfoMapper.getList(params);
	}

	/**
	 * 根据设备ID查询水卡信息
	 * @param deviceId
	 * 2017年1月10日
	 */
	public BaseCardInfo selectByDeviceId(String deviceId) {
		return baseCardInfoMapper.selectByDeviceId(deviceId);
	}

	/**
	 * 查询所有水卡中
	 * 	1、deviceId = null
	 * 	2、cardType = 1的信息
	 * 2017年1月17日
	 */
	public List<BaseCardInfo> selectAllCardInfo() {
		return baseCardInfoMapper.selectAllCardInfo();
	}

	/**
	 * 更新baseCardInfo表中DeviceId信息
	 * @param bci
	 * 2017年1月17日
	 */
	public void update(BaseCardInfo bci) {
		baseCardInfoMapper.update(bci);
	}

	/**
	 *  导出水卡信息
	 */
	@Override
	public List<BaseCardInfoVO> getListExcel(Map<String, Object> params) {
		return baseCardInfoMapper.getListExcel(params);
	}

	/**
	 * 水卡信息删除
	 */
	@Override
	public AjaxJson delCardInfo(String id) {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			int result = baseCardInfoMapper.delCardInfo(id);
			if(result > 0){
				ajaxJson.setMsg("删除成功");
			}else{
				ajaxJson.setMsg("删除失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("异常信息：" + e.getMessage());
			ajaxJson.setMsg("删除失败");
		}
		return ajaxJson;
	}

	@Override
	public Integer getManageCardCount(Map<String, Object> params) {
		return baseCardInfoMapper.getManageCardCount(params);
	}

	@Override
	public List<BaseCardInfo> getManageCardList(Map<String, Object> params) {
		return baseCardInfoMapper.getManageCardList(params);
	}

	@Override
	public BaseCardInfo getDeviceCodeByDatil(String deviceId) {
		return baseCardInfoMapper.getDeviceCodeByDatil(deviceId);
	}

	@Override
	public List<BaseCardInfo> selectByDeviceIds(String deviceId) {
		return baseCardInfoMapper.selectByDeviceIds(deviceId);
	}

	@Override
	public BaseCardInfo selectCardCode(String cardCode) {
		// TODO Auto-generated method stub
		return baseCardInfoMapper.selectCardCode(cardCode);
	}

	@Override
	public void updateCredWater(BaseCardInfo bci) {
		baseCardInfoMapper.updateCredWater(bci);
	}

}