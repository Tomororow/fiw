package com.fourfaith.paramterManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamWaterSourcePrice;

/**
 * @ClassName: ParamWaterSourcePriceMapper
 * @Description: 水资源费信息dao接口
 * @Author: zhaojx
 */
public interface ParamWaterSourcePriceMapper {

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
	int add(ParamWaterSourcePrice paramWaterSourcePrice);

	/**
	 * 根据Id删除水资源费信息
	 * @param string
	 */
	void delete(String string);
	
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
	ParamWaterSourcePrice getWaterSourcePriceById(String id);
	
	/**
	 * @Title: editWaterSourcePrice
	 * @Description: 修改水资源费率信息
	 * @param: @param paramWaterSourcePrice
	 * @return: int
	 * @Author: zhaojinxin
	 */
	int editWaterSourcePrice(ParamWaterSourcePrice paramWaterSourcePrice);

}