package com.fourfaith.baseManager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.baseManager.dao.InterfactKeyMapper;
import com.fourfaith.baseManager.model.InterfactKey;
@Service("interfactKeyService")
public class InterfactKeyServiceImpl implements InterfactKeyService {

	@Autowired
	private InterfactKeyMapper interfactKeyMapper;
	
	@Override
	public InterfactKey selectByPrimaryKey(String id) {
		return interfactKeyMapper.selectByPrimaryKey(id);
	}

	@Override
	public String selectAllRan() {
		return interfactKeyMapper.selectAllRan();
	}

}
