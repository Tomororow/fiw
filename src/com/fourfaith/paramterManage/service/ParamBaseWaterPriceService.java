package com.fourfaith.paramterManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamBaseWaterPrice;
import com.fourfaith.utils.AjaxJson;
 
/**
 * @ClassName: BaseWaterPriceChargeService
 * @Description: 基本水费费率设置
 * @Author: zhaojx
 * @Date: 2018年7月29日 下午6:27:32
 */
public interface ParamBaseWaterPriceService {

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
	String insertBaseWater(ParamBaseWaterPrice paramBaseWaterPrice);
	
	/**
	 * @Title: delBaseWater
	 * @Description: 根据ID删除信息
	 * @param: @param ids
	 * @return: AjaxJson
	 * @Author: zhaojinxin
	 */
	AjaxJson delBaseWater(String ids);
	
	/**
	 * @Title: findBaseWaterPriceById
	 * @Description: 根据ID查询基本水费费率信息
	 * @param: @param id
	 * @return: ParamBaseWaterPrice
	 * @Author: zhaojinxin
	 */
	ParamBaseWaterPrice findBaseWaterPriceById(String id);
	
	/**
	 * @Title: editBaseWaterPrice
	 * @Description: 修改基本水费费率信息
	 * @param: @param paramBaseWaterPrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	String editBaseWaterPrice(ParamBaseWaterPrice paramBaseWaterPrice);
	
	/**
	 * @Title: findBaseWaterPriceByYear
	 * @Description: 根据年份查询基本水费费率信息
	 * @param: @param inYear
	 * @return: ParamBaseWaterPrice
	 * @Author: zhaojinxin
	 */
	ParamBaseWaterPrice findBaseWaterPriceByYear(Integer inYear);
	
}