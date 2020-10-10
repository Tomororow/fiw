package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysPumpMaterial;

/**
 * @ClassName: SysPumpMaterialMapper
 * @Description: 泵管材质dao接口
 * @Author: zhaojx
 */
public interface SysPumpMaterialMapper {

	/**
	 * @Title: getCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 泵管材质信息列表
	 * @param: @param params
	 * @return: List<SysPumpMaterial>
	 */
	List<SysPumpMaterial> getList(Map<String, Object> params);
	
	/**
	 * @Title: insert
	 * @Description: 添加信息
	 * @param: @param record
	 * @return: int
	 */
	int insert(SysPumpMaterial record);

	/**
	 * @Title: insertSelective
	 * @Description: 非空信息添加
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysPumpMaterial record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询信息
	 * @param: @param id
	 * @return: SysPumpMaterial
	 */
	SysPumpMaterial selectByPrimaryKey(String id);

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
	 * @param: @param sysPumpMaterial
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysPumpMaterial sysPumpMaterial);

	/**
	 * TODO: 泵管名唯一性校验（新增）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysPumpMaterial> uniqueAddSelective(SysPumpMaterial model);

	/**
	 * TODO: 泵管名唯一性校验（修改）
	 * @param sysPumpMaterial
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysPumpMaterial> uniqueUpdateSelective(SysPumpMaterial sysPumpMaterial);
	
}