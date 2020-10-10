package com.fourfaith.paramterManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.paramterManage.dao.WarnmiddlepersonMapper;
import com.fourfaith.paramterManage.model.Warnmiddleperson;
import com.fourfaith.paramterManage.service.WarnmiddlepersonService;

@Service("warnmiddlepersonService")
public class WarnmiddlepersonServiceImpl implements WarnmiddlepersonService {

	@Autowired
	private WarnmiddlepersonMapper warnmiddlepersonMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return warnmiddlepersonMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Warnmiddleperson record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Warnmiddleperson record) {
		// TODO Auto-generated method stub
		return warnmiddlepersonMapper.insertSelective(record);
	}

	@Override
	public Warnmiddleperson selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Warnmiddleperson record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Warnmiddleperson record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
