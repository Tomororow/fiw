package com.fourfaith.paramterManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamMeasureTypePrice;

/**
 * @ClassName: ParamMeasureTypePriceMapper
 * @Description: 参数管理dao接口
 * @Author: zhaojx
 */
public interface ParamMeasureTypePriceMapper {

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
	 * TODO: 新增计量水费信息
	 * @param paramMeasureTypePrice
	 * 2016年12月7日
	 * Administrator: xiaogaoxiang
	 */
	int add(ParamMeasureTypePrice paramMeasureTypePrice);

	/**
	 * TODO: 根据Id删除计量水费信息
	 * @param string
	 * 2016年12月7日
	 * Administrator: xiaogaoxiang
	 */
	void delete(String string);

	/**
	 * TODO: 根据distType获取计量水费信息
	 * @param params
	 * 2016年12月28日
	 * Administrator: xiaogaoxiang
	 */
	ParamMeasureTypePrice getAllList(Map<String, Object> params);
	
	/**
	 * @Title: getMeasureTypePriceById
	 * @Description: 根据ID获取计量水费信息
	 * @param: @param id
	 * @return: ParamMeasureTypePrice
	 * @Author: zhaojinxin
	 */
	ParamMeasureTypePrice getMeasureTypePriceById(String id);
	
	/**
	 * @Title: editMeasureTypePrice
	 * @Description: 修改计量水费信息
	 * @param: @param paramMeasureTypePrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	int editMeasureTypePrice(ParamMeasureTypePrice paramMeasureTypePrice);
	
	/**
	 * @Title: getMeasurePriceByYear
	 * @Description: 根据年份获取计量水费信息
	 * @param: @param inYear
	 * @return: ParamMeasureTypePrice
	 * @Author: zhaojinxin
	 */
	ParamMeasureTypePrice getMeasurePriceByYear(Integer inYear);

}