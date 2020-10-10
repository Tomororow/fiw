package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysUseWaterType;

/**
 * @ClassName: SysUseWaterTypeMapper
 * @Description: 取水类型dao接口
 * @Author: zhaojx
 */
public interface SysUseWaterTypeMapper {
	
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
	 * @return: List<SysUseWaterType>
	 */
	List<SysUseWaterType> getList(Map<String, Object> params);
	
	/**
	 * @Title: insert
	 * @Description: 添加信息
	 * @param: @param record
	 * @return: int
	 */
	int insert(SysUseWaterType record);

	/**
	 * @Title: insertSelective
	 * @Description: 非空信息添加
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysUseWaterType record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询信息
	 * @param: @param id
	 * @return: SysUseWaterType
	 */
	SysUseWaterType selectByPrimaryKey(String id);

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
	 * @param: @param sysUseWaterType
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysUseWaterType sysUseWaterType);

	/**
	 * TODO: 取水类型名唯一性校验（新增）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysUseWaterType> uniqueAddSelectTive(SysUseWaterType model);
	
	/**
	 * TODO: 取水类型名唯一性校验（修改）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysUseWaterType> uniqueUpdateSelectTive(SysUseWaterType model);
	
}