package com.fourfaith.RemoteTopUp.service;

import com.fourfaith.RemoteTopUp.model.Flow;

public interface FlowService {

	int deleteByPrimaryKey(String id);

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);
}
