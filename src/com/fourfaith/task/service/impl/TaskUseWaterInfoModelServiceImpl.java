package com.fourfaith.task.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.task.dao.TaskUseWaterInfoModelMapper;
import com.fourfaith.task.model.TaskUseWaterInfoModel;
import com.fourfaith.task.service.TaskUseWaterInfoModelService;

/**
 * @ClassName: TaskUseWaterInfoModelServiceImpl
 * @Description: 最新用水service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:48:20
 */
@Service("TaskUseWaterInfoModelService")
public class TaskUseWaterInfoModelServiceImpl implements TaskUseWaterInfoModelService {

	protected Logger logger = Logger.getLogger(TaskUseWaterInfoModelServiceImpl.class);
	
	@Autowired
	private TaskUseWaterInfoModelMapper taskUseWaterInfoModelMapper;

	/**
	 * 查询是否有该最新一次用水信息表
	 * @param deviceCode
	 * 2017年2月24日
	 */
	public TaskUseWaterInfoModel selectByDeviceCode(String deviceCode) {
		return taskUseWaterInfoModelMapper.selectByDeviceCode(deviceCode);
	}

	/**
	 * 新增最新一次用水信息
	 * @param tuim
	 * 2017年2月24日
	 */
	public void add(TaskUseWaterInfoModel tuim) {
		taskUseWaterInfoModelMapper.add(tuim);
	}

	/**
	 * 修改最新一次用水信息
	 * @param tuim
	 * 2017年2月24日
	 */
	public void update(TaskUseWaterInfoModel tuim) {
		taskUseWaterInfoModelMapper.update(tuim);
	}

}