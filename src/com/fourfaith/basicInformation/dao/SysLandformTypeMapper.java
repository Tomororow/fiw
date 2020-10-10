package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysLandformType;

/**
 * @ClassName: SysLandformTypeMapper
 * @Description: 地貌类型dao接口
 * @Author: zhaojx
 */
public interface SysLandformTypeMapper {

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
	 * @return: List<SysLandformType>
	 */
	List<SysLandformType> getList(Map<String, Object> params);
	
	/**
	 * @Title: insert
	 * @Description: 添加信息
	 * @param: @param record
	 * @return: int
	 */
	int insert(SysLandformType record);

	/**
	 * @Title: insertSelective
	 * @Description: 添加非空信息
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysLandformType record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询信息
	 * @param: @param id
	 * @return: SysLandformType
	 */
	SysLandformType selectByPrimaryKey(String id);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据主键删除信息
	 * @param: @param id
	 * @return: int
	 */
	int deleteByPrimaryKey(String id);
	
	/**
	 * @Title: deleteByDisId
	 * @Description: 根据id删除信息
	 * @param: @param disId
	 * @return: int
	 */
	int deleteByDisId(String disId);
	
	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 根据主键有选择的更新信息
	 * @param: @param sysLandformType
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysLandformType sysLandformType);

	/**
	 * TODO: 地貌类型名唯一性校验（新增）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysLandformType> uniqueAddSelectTive(SysLandformType model);
	
	/**
	 * TODO: 地貌类型名唯一性校验（修改）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysLandformType> uniqueUpdateSelectTive(SysLandformType model);
	
}