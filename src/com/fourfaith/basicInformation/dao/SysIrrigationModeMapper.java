package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysIrrigationMode;

/**
 * @ClassName: SysIrrigationModeMapper
 * @Description: 灌溉模式dao接口
 * @Author: zhaojx
 */
public interface SysIrrigationModeMapper {

	/**
	 * @Title: getCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 灌溉模式信息列表
	 * @param: @param params
	 * @return: List<SysIrrigationMode>
	 */
	List<SysIrrigationMode> getList(Map<String, Object> params);
	
	/**
	 * @Title: insert
	 * @Description: 新增灌溉模式信息
	 * @param: @param record
	 * @return: int
	 */
	int insert(SysIrrigationMode record);

	/**
	 * @Title: insertSelective
	 * @Description: 有选择的新增信息
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysIrrigationMode record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询信息 
	 * @param: @param id
	 * @return: SysIrrigationMode
	 */
	SysIrrigationMode selectByPrimaryKey(String id);

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
	 * @param: @param sysIrrigationMode
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysIrrigationMode sysIrrigationMode);

	/**
	 * TODO: 灌溉模式命唯一性校验（新增）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysIrrigationMode> uniqueAddSelectTive(SysIrrigationMode model);
	
	/**
	 * TODO: 灌溉模式命唯一性校验（修改）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysIrrigationMode> uniqueUpdateSelectTive(SysIrrigationMode model);
	
}