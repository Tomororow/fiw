package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysIrrigationAreaType;

/**
 * @ClassName: SysIrrigationAreaTypeMapper
 * @Description: 灌区类型dao接口
 * @Author: zhaojx
 */
public interface SysIrrigationAreaTypeMapper {

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
	 * @return: List<SysIrrigationAreaType>
	 */
	List<SysIrrigationAreaType> getList(Map<String, Object> params);
	
	/**
	 * @Title: insert
	 * @Description: 新增灌区类型
	 * @param: @param record
	 * @return: int
	 */
	int insert(SysIrrigationAreaType record);

	/**
	 * @Title: insertSelective
	 * @Description: 非空信息新增
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysIrrigationAreaType record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询信息
	 * @param: @param id
	 * @return: SysIrrigationAreaType
	 */
	SysIrrigationAreaType selectByPrimaryKey(String id);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据主键删除信息
	 * @param: @param id
	 * @return: int
	 */
	int deleteByPrimaryKey(String id);
	
	/**
	 * @Title: deleteByDisId
	 * @Description: 根据ID删除信息
	 * @param: @param disId
	 * @return: int
	 */
	int deleteByDisId(String disId);
	
	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 根据主键有选择的更新信息
	 * @param: @param sysIrrigationAreaType
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysIrrigationAreaType sysIrrigationAreaType);

	/**
	 * TODO: 灌区类型名唯一性校验（新增）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysIrrigationAreaType> uniqueAddSelectTive(SysIrrigationAreaType model);
	
	/**
	 * TODO: 灌区类型名唯一性校验（修改）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysIrrigationAreaType> uniqueUpdateSelectTive(SysIrrigationAreaType model);
	
}