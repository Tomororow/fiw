package com.fourfaith.paramterManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamMaintainPrice;
 
/**
 * @ClassName: ParamMaintainPriceMapper
 * @Description: 末级渠系(维修养护)费率dao
 * @Author: zhaojinxin
 * @Date: 2018年9月5日 下午5:51:36
 */
public interface ParamMaintainPriceMapper {

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
	int insertMaintainPrice(ParamMaintainPrice paramMaintainPrice);
	
	/**
	 * @Title: delMaintainPrice
	 * @Description: 根据ID删除信息
	 * @param: @param ids
	 * @return: AjaxJson
	 * @Author: zhaojinxin
	 */
	void delMaintainPrice(String ids);
	
	/**
	 * @Title: findMaintainPriceById
	 * @Description: 根据ID查询费率信息
	 * @param: @param id
	 * @return: ParamMaintainPrice
	 * @Author: zhaojinxin
	 */
	ParamMaintainPrice getMaintainPriceById(String id);
	
	/**
	 * @Title: editMaintainPrice
	 * @Description: 修改费率信息
	 * @param: @param paramMaintainPrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	int editMaintainPrice(ParamMaintainPrice paramMaintainPrice);
	 
	/**
	 * @Title: getMaintainPriceByYear
	 * @Description: 根据年份查询费率信息
	 * @param: @param inYear
	 * @return: ParamMaintainPrice
	 * @Author: zhaojinxin
	 */
	ParamMaintainPrice getMaintainPriceByYear(Integer inYear);
	
}