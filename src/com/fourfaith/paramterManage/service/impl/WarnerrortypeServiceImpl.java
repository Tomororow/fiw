package com.fourfaith.paramterManage.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.paramterManage.dao.WarnerrortypeMapper;
import com.fourfaith.paramterManage.model.Warnerrortype;
import com.fourfaith.paramterManage.service.WarnerrortypeService;
@Service("warnerrortypeService")
public class WarnerrortypeServiceImpl implements WarnerrortypeService {

	@Autowired
	WarnerrortypeMapper warnerrortypeMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return warnerrortypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Warnerrortype record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Warnerrortype record) {
		// TODO Auto-generated method stub
		return warnerrortypeMapper.insertSelective(record);
	}

	@Override
	public List<Warnerrortype> selectByPrimaryKey(Integer id,String pageStart,String pageEnd) {
		// TODO Auto-generated method stub
		return warnerrortypeMapper.selectByPrimaryKey(id,pageStart,pageEnd);
	}

	@Override
	public int updateByPrimaryKeySelective(Warnerrortype record) {
		// TODO Auto-generated method stub
		return warnerrortypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Warnerrortype record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer selectByCount() {
		// TODO Auto-generated method stub
		return warnerrortypeMapper.selectByCount();
	}

	@Override
	public Warnerrortype findByPrimaryCode(String abnormalcode) {
		// TODO Auto-generated method stub
		return warnerrortypeMapper.findByPrimaryCode(abnormalcode);
	}
	
}
