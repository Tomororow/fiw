package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysApplyStatus;
import com.fourfaith.basicInformation.model.SysUseWaterType;

/**
 * @ClassName: SysApplyStatusMapper
 * @Description: 机井用途dao接口
 * @Author: zhaojx
 * @Date: 2018年5月9日 下午2:19:03
 */
public interface SysApplyStatusMapper {

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
	 * @return: List<SysApplyStatus>
	 */
	List<SysApplyStatus> getList(Map<String, Object> params);
	
	/**
	 * @Title: insert
	 * @Description: 添加机井用途
	 * @param: @param record
	 * @return: int
	 */
	int insert(SysApplyStatus record);

	/**
	 * @Title: insertSelective
	 * @Description: 非空添加信息
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysApplyStatus record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键获取信息
	 * @param: @param id
	 * @return: SysApplyStatus
	 */
	SysApplyStatus selectByPrimaryKey(String id);

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
	 * @param: @param sysApplyStatus
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysApplyStatus sysApplyStatus);

	/**
	 * TODO: 应用状况名唯一性校验（新增）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysUseWaterType> uniqueAddSelectTive(SysApplyStatus model);
	
	/**
	 * TODO: 应用状况名唯一性校验（修改）
	 * @param model
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysUseWaterType> uniqueUpdateSelectTive(SysApplyStatus model);
	
}