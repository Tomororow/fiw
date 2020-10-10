package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysWellUse;

/**
 * @ClassName: SysWellUseMapper
 * @Description: 机井用途dao接口
 * @Author: zhaojx
 */
public interface SysWellUseMapper {
	
	/**
	 * @Title: getCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 信息列表
	 * @param: @param params
	 * @return: List<SysWellUse>
	 */
	List<SysWellUse> getList(Map<String, Object> params);
	
	/**
	 * @Title: insert
	 * @Description: 添加信息
	 * @param: @param record
	 * @return: int
	 */
	int insert(SysWellUse record);

	/**
	 * @Title: insertSelective
	 * @Description: 非空信息添加
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysWellUse record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询
	 * @param: @param id
	 * @return: SysWellUse
	 */
	SysWellUse selectByPrimaryKey(String id);

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
	 * @param: @param sysWellUse
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysWellUse sysWellUse);

	/**
	 * TODO: 水井用途名唯一性校验（新增）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysWellUse> uniqueAddSelectTive(SysWellUse model);
	
	/**
	 * TODO: 水井用途名唯一性校验（修改）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysWellUse> uniqueUpdateSelectTive(SysWellUse model);

}