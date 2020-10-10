package com.fourfaith.RemoteTopUp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.RemoteTopUp.dao.FlowMapper;
import com.fourfaith.RemoteTopUp.model.Flow;
import com.fourfaith.RemoteTopUp.service.FlowService;
@Service("flowService")
public class FlowServiceImpl implements FlowService {

	@Autowired
	private FlowMapper flowMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		return flowMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Flow record) {
		return flowMapper.insert(record);
	}

	@Override
	public int insertSelective(Flow record) {
		return flowMapper.insertSelective(record);
	}

	@Override
	public Flow selectByPrimaryKey(String id) {
		return flowMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Flow record) {
		return flowMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Flow record) {
		return flowMapper.updateByPrimaryKey(record);
	}

}
