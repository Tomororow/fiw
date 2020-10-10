package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysDeviceModel;

/**
 * @ClassName: SysDeviceModelMapper
 * @Description: 设备型号dao接口
 * @Author: zhaojx
 * @Date: 2018年5月9日 下午2:25:09
 */
public interface SysDeviceModelMapper {

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
	 * @return: List<SysDeviceModel>
	 */
	List<SysDeviceModel> getList(Map<String, Object> params);
	
	/**
	 * @Title: insert
	 * @Description: 添加设备型号
	 * @param: @param record
	 * @return: int
	 */
	int insert(SysDeviceModel record);

	/**
	 * @Title: insertSelective
	 * @Description: 添加非空设备型号信息
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysDeviceModel record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询信息
	 * @param: @param id
	 * @return: SysDeviceModel
	 */
	SysDeviceModel selectByPrimaryKey(String id);

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
	 * @param: @param sysDeviceModel
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysDeviceModel sysDeviceModel);

	/**
	 * 机井设备型号名唯一性校验（新增）
	 * @param model
	 * @return
	 */
	List<SysDeviceModel> uniqueAddSelectTive(SysDeviceModel model);
	
	/**
	 * 机井设备型号名唯一性校验（修改）
	 * @param model
	 * @return
	 */
	List<SysDeviceModel> uniqueUpdateSelectTive(SysDeviceModel model);
	
}