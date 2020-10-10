package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysOperator;

/**
 * @ClassName: SysOperatorMapper
 * @Description: 运营商dao接口
 * @Author: zhaojx
 */
public interface SysOperatorMapper {

	/**
	 * @Title: getCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 获取运营商信息列表
	 * @param: @param params
	 * @return: List<SysOperator>
	 */
	List<SysOperator> getList(Map<String, Object> params);
	
	/**
	 * @Title: insert
	 * @Description: 添加运营商信息
	 * @param: @param record
	 * @return: int
	 */
	int insert(SysOperator record);

	/**
	 * @Title: insertSelective
	 * @Description: 非空信息添加
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysOperator record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询信息
	 * @param: @param id
	 * @return: SysOperator
	 */
	SysOperator selectByPrimaryKey(String id);

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
	 * @param: @param sysOperator
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysOperator sysOperator);

	/**
	 * TODO: 名称唯一性校验（新增）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysOperator> uniqueAddSelective(SysOperator model);

	/**
	 * TODO: 名称唯一性校验（修改）
	 * @param sysOperator
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysOperator> uniqueUpdateSelective(SysOperator sysOperator);
	
}