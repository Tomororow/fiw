package com.fourfaith.paramterManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamWaterSourcePrice;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: ParamWaterSourcePriceService
 * @Description: 水资源费信息service接口
 * @Author: zhaojx
 * @Date: 2017年5月13日 下午6:08:28
 */
public interface ParamWaterSourcePriceService {

	/**
	 * @Title: getCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 获取信息列表
	 * @param: @param params
	 * @return: List<ParamWaterSourcePrice>
	 */
	List<ParamWaterSourcePrice> getList(Map<String, Object> params);

	/**
	 * 新增水资源费信息
	 * @param paramWaterSourcePrice
	 * 2016年12月5日
	 */
	String add(ParamWaterSourcePrice paramWaterSourcePrice);

	/**
	 * 根据Id删除水资源费信息
	 * @param ids
	 * 2016年12月5日
	 */
	AjaxJson delete(String ids);

	/**
	 * 获取所有水资源费信息
	 * 2016年12月28日
	 */
	ParamWaterSourcePrice getAllList(Map<String, Object> params);
	
	/**
	 * @Title: getWaterSourcePriceById
	 * @Description: 根据id获取水资源费率信息
	 * @param: @param id
	 * @return: ParamWaterSourcePrice
	 * @Author: zhaojinxin
	 */
	ParamWaterSourcePrice findWaterSourcePriceById(String id);
	
	/**
	 * @Title: editWaterSourcePrice
	 * @Description: 修改水资源费率信息
	 * @param: @param paramWaterSourcePrice
	 * @return: int
	 * @Author: zhaojinxin
	 */
	String editWaterSourcePrice(ParamWaterSourcePrice paramWaterSourcePrice);

}