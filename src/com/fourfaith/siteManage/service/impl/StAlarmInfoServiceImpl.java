package com.fourfaith.siteManage.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.siteManage.dao.StAlarmInfoMapper;
import com.fourfaith.siteManage.model.StAlarmInfo;
import com.fourfaith.siteManage.service.StAlarmInfoService;

/**
 * @ClassName: StAlarmInfoServiceImpl
 * @Description: 报警记录service实现
 * @Author: zhaojx
 * @Date: 2017年5月14日 上午11:31:53
 */
@Service("stAlarmInfoDService")
public class StAlarmInfoServiceImpl implements StAlarmInfoService{

	protected Logger logger = Logger.getLogger(StAlarmInfoServiceImpl.class);
	
	@Autowired
	private StAlarmInfoMapper stAlarmInfoMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return stAlarmInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(StAlarmInfo record) {
		return stAlarmInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(StAlarmInfo record) {
		return stAlarmInfoMapper.insertSelective(record);
	}

	@Override
	public StAlarmInfo selectByPrimaryKey(String id) {
		return stAlarmInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(StAlarmInfo record) {
		return stAlarmInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(StAlarmInfo record) {
		return stAlarmInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer getCount(Map<String, Object> params) {
		return stAlarmInfoMapper.getCount(params);
	}

	@Override
	public List<StAlarmInfo> getList(Map<String, Object> params) {
		return stAlarmInfoMapper.getList(params);
	}

}