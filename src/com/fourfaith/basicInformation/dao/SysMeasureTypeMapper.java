package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysMeasureType;

/**
 * @ClassName: SysMeasureTypeMapper
 * @Description: 计量设施dao接口
 * @Author: zhaojx
 */
public interface SysMeasureTypeMapper {

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
	 * @return: List<SysMeasureType>
	 */
	List<SysMeasureType> getList(Map<String, Object> params);
	
	/**
	 * @Title: insert
	 * @Description: 添加计量设施信息
	 * @param: @param record
	 * @return: int
	 */
	int insert(SysMeasureType record);

	/**
	 * @Title: insertSelective
	 * @Description: 非空信息增加
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysMeasureType record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询计量设施信息
	 * @param: @param id
	 * @return: SysMeasureType
	 */
	SysMeasureType selectByPrimaryKey(String id);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据主键删除计量设施类型
	 * @param: @param id
	 * @return: int
	 */
	int deleteByPrimaryKey(String id);
	
	/**
	 * @Title: deleteByDisId
	 * @Description: 根据ID删除计量设施类型信息
	 * @param: @param disId
	 * @return: int
	 */
	int deleteByDisId(String disId);
	
	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 根据主键有选择的更新信息
	 * @param: @param sysMeasureType
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysMeasureType sysMeasureType);

	/**
	 * TODO: 计量设施类型名唯一性校验（新增）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysMeasureType> uniqueAddSelectTive(SysMeasureType model);
	
	/**
	 * TODO: 计量设施类型名唯一性校验（修改）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysMeasureType> uniqueUpdateSelectTive(SysMeasureType model);

}