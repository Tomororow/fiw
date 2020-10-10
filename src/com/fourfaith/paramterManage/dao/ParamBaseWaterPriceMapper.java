package com.fourfaith.paramterManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamBaseWaterPrice;

/**
 * @ClassName: BaseWaterPriceChargeMapper
 * @Description: 基本水费费率设置
 * @Author: zhaojx
 * @Date: 2018年7月30日 上午11:10:17
 */
public interface ParamBaseWaterPriceMapper {
	
	/**
	 * @Title: getBaseWaterCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getBaseWaterCount(Map<String, Object> params);

	/**
	 * @Title: getBaseWaterList
	 * @Description: 获取信息列表
	 * @param: @param params
	 * @return: List<ParamBaseWaterPrice>
	 */
	List<ParamBaseWaterPrice> getBaseWaterList(Map<String, Object> params);
	
	/**
	 * @Title: insertBaseWater
	 * @Description: 新增费率信息
	 * @param: @param paramBaseWaterPrice
	 * @return: int
	 * @Author: zhaojinxin
	 */
	int insertBaseWater(ParamBaseWaterPrice paramBaseWaterPrice);
	
	/**
	 * @Title: deleteBaseWater
	 * @Description: 删除费率信息
	 * @param: @param string
	 * @return: void
	 * @Author: zhaojinxin
	 */
	void deleteBaseWater(String string);
	
	/**
	 * @Title: getBaseWaterPriceById
	 * @Description: 根据ID查询基本水费费率信息
	 * @param: @param id
	 * @return: ParamBaseWaterPrice
	 * @Author: zhaojinxin
	 */
	ParamBaseWaterPrice getBaseWaterPriceById(String id);
	
	/**
	 * @Title: editBaseWaterPrice
	 * @Description: 修改基本水费费率信息
	 * @param: @param paramBaseWaterPrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	int editBaseWaterPrice(ParamBaseWaterPrice paramBaseWaterPrice);
	
	/**
	 * @Title: getBaseWaterPriceByYear
	 * @Description: 根据年份查询基本水费费率信息
	 * @param: @param inYear
	 * @return: ParamBaseWaterPrice
	 * @Author: zhaojinxin
	 */
	ParamBaseWaterPrice getBaseWaterPriceByYear(Integer inYear);
	
}