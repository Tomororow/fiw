package com.fourfaith.sysManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.AbonmalhistoryMapper;
import com.fourfaith.sysManage.model.Abonmalhistory;
import com.fourfaith.sysManage.service.AbonmalhistoryService;
@Service("abonmalhistoryService")
public class AbonmalhistoryServiceImpl implements AbonmalhistoryService {

	@Autowired
	AbonmalhistoryMapper abonmalhistoryMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Abonmalhistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Abonmalhistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Abonmalhistory selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Abonmalhistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Abonmalhistory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Abonmalhistory> findUnableHistory(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return abonmalhistoryMapper.findUnableHistory(params);
	}

}
