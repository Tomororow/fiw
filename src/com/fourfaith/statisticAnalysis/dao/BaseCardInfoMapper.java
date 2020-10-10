package com.fourfaith.statisticAnalysis.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.statisticAnalysis.model.BaseCardInfo;
import com.fourfaith.statisticAnalysis.model.BaseCardInfoVO;

/**
 * @ClassName: BaseCardInfoMapper
 * @Description: 水卡信息dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午3:46:20
 */
public interface BaseCardInfoMapper {

	/**
	 * @Title: getCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 水卡信息列表 
	 * @param: @param params
	 * @return: List<BaseCardInfo>
	 */
	List<BaseCardInfo> getList(Map<String, Object> params);
	
	/** hainian  liyue */
	BaseCardInfo selectCardCode(String cardCode);

	/**
	 * 根据设备ID查询水卡信息
	 * @param deviceId
	 * 2017年1月10日
	 * Administrator: xiaogaoxiang
	 */
	BaseCardInfo selectByDeviceId(String deviceId);
	
	/** hainian  liyue */
	List<BaseCardInfo> selectByDeviceIds(String deviceId);
	/**
	 * 查询所有水卡中
	 * 	1、deviceId = null
	 * 	2、cardType = 1的信息
	 * 2017年1月17日
	 * Administrator: xiaogaoxiang
	 */
	List<BaseCardInfo> selectAllCardInfo();

	/**
	 * 更新baseCardInfo表中DeviceId信息
	 * @param bci
	 * 2017年1月17日
	 * Administrator: xiaogaoxiang
	 */
	void update(BaseCardInfo bci);
	
	void updateCredWater(BaseCardInfo bci);

	/**
	 * @Title: getListExcel
	 * @Description: 导出水卡信息
	 * @param: @param params
	 * @return: <BaseCardInfoVO>
	 */
	List<BaseCardInfoVO> getListExcel(Map<String, Object> params);
	
	/**
	 * @Title: delCardInfo
	 * @Description: 水卡信息删除
	 * @param: @param id
	 * @return: AjaxJson
	 */
	int delCardInfo(String id);
	
	/**
	 * @Title: getManageCardCount
	 * @Description: 管理卡信息条数统计
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getManageCardCount(Map<String, Object> params);
	
	/**
	 * @Title: getManageCardList
	 * @Description: 管理卡信息分页查询
	 * @param: @param params
	 * @return: List<BaseCardInfo>
	 */
	List<BaseCardInfo> getManageCardList(Map<String, Object> params);

	/**
	 * @Title: getDeviceCodeByDatil
	 * @Description: 根据机井编码查询开卡信息
	 * @param deviceCode
	 * @return
	 * @return BaseCardInfo
	 * @author 刘海年
	 * @date 2018年9月14日下午5:51:42
	 */
	BaseCardInfo getDeviceCodeByDatil(String deviceId);
}