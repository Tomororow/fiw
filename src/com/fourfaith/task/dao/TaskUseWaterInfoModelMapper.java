package com.fourfaith.task.dao;

import com.fourfaith.task.model.TaskUseWaterInfoModel;

/**
 * @ClassName: TaskUseWaterInfoModelMapper
 * @Description: 最新用水dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:47:38
 */
public interface TaskUseWaterInfoModelMapper {

	/**
	 * 查询是否有该最新一次用水信息表
	 * @param deviceCode
	 * 2017年2月24日
	 */
	TaskUseWaterInfoModel selectByDeviceCode(String deviceCode);

	/**
	 * 新增最新一次用水信息
	 * @param tuim
	 * 2017年2月24日
	 */
	void add(TaskUseWaterInfoModel tuim);

	/**
	 * 修改最新一次用水信息
	 * @param tuim
	 * 2017年2月24日
	 */
	void update(TaskUseWaterInfoModel tuim);

}