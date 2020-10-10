package com.fourfaith.sysManage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.BjextendinfoMapper;
import com.fourfaith.sysManage.model.Bjextendinfo;
import com.fourfaith.sysManage.service.BjextendinfoService;
@Service("/bjextendService")
public class BjextendinfoServiceImpl implements BjextendinfoService {
	
	@Autowired
	private BjextendinfoMapper bjextendinfoMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return bjextendinfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Bjextendinfo record) {
		// TODO Auto-generated method stub
		return bjextendinfoMapper.insert(record);
	}

	@Override
	public int insertSelective(Bjextendinfo record) {
		// TODO Auto-generated method stub
		return bjextendinfoMapper.insertSelective(record);
	}

	@Override
	public Bjextendinfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return bjextendinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Bjextendinfo record) {
		// TODO Auto-generated method stub
		return bjextendinfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Bjextendinfo record) {
		// TODO Auto-generated method stub
		return bjextendinfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Bjextendinfo> selectAll() {
		// TODO Auto-generated method stub
		return bjextendinfoMapper.selectAll();
	}

}
