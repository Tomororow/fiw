package com.fourfaith.paramterManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamMeasureTypePrice;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: ParamMeasureTypePriceService
 * @Description: 水费信息service接口
 * @Author: zhaojx
 * @Date: 2017年5月13日 下午6:05:19
 */
public interface ParamMeasureTypePriceService {

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
	 * @return: List<ParamMeasureTypePrice>
	 */
	List<ParamMeasureTypePrice> getList(Map<String, Object> params);

	/**
	 * 新增计量水费信息
	 * @param paramMeasureTypePrice
	 * 2016年12月7日
	 */
	String add(ParamMeasureTypePrice paramMeasureTypePrice);

	/**
	 * 根据Id删除计量水费信息
	 * @param ids
	 * 2016年12月7日
	 */
	AjaxJson delete(String ids);

	/**
	 * 根据distType获取计量水费信息
	 * @param params
	 * 2016年12月28日
	 */
	ParamMeasureTypePrice getAllList(Map<String, Object> params);
	
	/**
	 * @Title: findMeasureTypePriceById
	 * @Description: 根据ID获取计量水费信息
	 * @param: @param id
	 * @return: ParamMeasureTypePrice
	 * @Author: zhaojinxin
	 */
	ParamMeasureTypePrice findMeasureTypePriceById(String id);
	
	/**
	 * @Title: editMeasureTypePrice
	 * @Description: 修改计量水费信息
	 * @param: @param paramMeasureTypePrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	String editMeasureTypePrice(ParamMeasureTypePrice paramMeasureTypePrice);
	
	/**
	 * @Title: findMeasurePriceByYear
	 * @Description: 根据年份获取计量水费信息
	 * @param: @param inYear
	 * @return: ParamMeasureTypePrice
	 * @Author: zhaojinxin
	 */
	ParamMeasureTypePrice findMeasurePriceByYear(Integer inYear);

}