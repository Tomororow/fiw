package com.fourfaith.chargeManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.chargeManage.model.WaterPriceAmountVO;
import com.fourfaith.chargeManage.model.WaterSourceCharge;

/**
 * @ClassName: WaterSourceChargeMapper
 * @Description: 水资源费统计Dao接口 
 * @Author: zhaojx
 * @Date: 2017年9月8日
 */
public interface WaterSourceChargeMapper {

	/**
	 * @Title: getCount
	 * @Description: 充值记录表 统计数量
	 * @return: Integer
	 * @Author: zhaojx
	 */
	Integer getCount(Map<String, Object> params);
	
	/**
	 * @Title: getWaterSourceList
	 * @Description: 计算获取水资源费 计量水费统计信息
	 * @return: List<WaterSourceCharge>
	 * @Author: zhaojx
	 */
	List<WaterSourceCharge> getWaterSourceList(Map<String, Object> params);
	
	/**
	 * @Title: getPriceAmount
	 * @Description: 获取各种费用总金额
	 * @param: @param params
	 * @return: List<WaterSourceCharge>
	 * @Author: zhaojinxin
	 */
	List<WaterSourceCharge> getPriceAmount(Map<String, Object> params);
	
	/**
	 * @Title: getWaterAmountExcelList
	 * @Description: 获取其他费用导出报表
	 * @return: List<WaterPriceAmountVO>
	 * @Author: zhaojx
	 * @Date: 2017年9月8日
	 */
	List<WaterPriceAmountVO> getWaterAmountExcelList(Map<String, Object> params);
	
}