package com.fourfaith.paramterManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamMaintainPrice;
import com.fourfaith.utils.AjaxJson;
 
/**
 * @ClassName: ParamMaintainPriceService
 * @Description: 末级渠系(维修养护)费率service
 * @Author: zhaojinxin
 * @Date: 2018年9月5日 下午5:51:36
 */
public interface ParamMaintainPriceService {

	/**
	 * @Title: getMaintainPriceCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 * @Author: zhaojinxin
	 */
	Integer getMaintainPriceCount(Map<String, Object> params);

	/**
	 * @Title: getMaintainPriceList
	 * @Description: 获取信息列表
	 * @param: @param params
	 * @return: List<ParamMaintainPrice>
	 * @Author: zhaojinxin
	 */
	List<ParamMaintainPrice> getMaintainPriceList(Map<String, Object> params);
	
	/**
	 * @Title: insertMaintainPrice
	 * @Description: 新增费率信息
	 * @param: @param paramMaintainPrice
	 * @return: int
	 * @Author: zhaojinxin
	 */
	String insertMaintainPrice(ParamMaintainPrice paramMaintainPrice);
	
	/**
	 * @Title: delMaintainPrice
	 * @Description: 根据ID删除信息
	 * @param: @param ids
	 * @return: AjaxJson
	 * @Author: zhaojinxin
	 */
	AjaxJson delMaintainPrice(String ids);
	
	/**
	 * @Title: findMaintainPriceById
	 * @Description: 根据ID查询费率信息
	 * @param: @param id
	 * @return: ParamMaintainPrice
	 * @Author: zhaojinxin
	 */
	ParamMaintainPrice findMaintainPriceById(String id);
	
	/**
	 * @Title: editMaintainPrice
	 * @Description: 修改费率信息
	 * @param: @param paramMaintainPrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	String editMaintainPrice(ParamMaintainPrice paramMaintainPrice);
	
	/**
	 * @Title: findMaintainPriceByYear
	 * @Description: 根据年份查询费率信息
	 * @param: @param inYear
	 * @return: ParamMaintainPrice
	 * @Author: zhaojinxin
	 */
	ParamMaintainPrice findMaintainPriceByYear(Integer inYear);
	
}