package com.fourfaith.sysManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.AbnormalstateMapper;
import com.fourfaith.sysManage.model.Abnormalstate;
import com.fourfaith.sysManage.service.AbnormalstateService;
@Service("abnormalstateService")
public class AbnormalstateServiceImpl implements AbnormalstateService {

	@Autowired
	AbnormalstateMapper abnormalstateMapper;
	
	@Override
	public int deleteByPrimaryKey(String devicecode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Abnormalstate record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Abnormalstate record) {
		// TODO Auto-generated method stub
		return abnormalstateMapper.insertSelective(record);
	}

	@Override
	public Abnormalstate selectByPrimaryKey(String devicecode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Abnormalstate record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Abnormalstate record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Abnormalstate selectByPrimarytuData(String deviceCode) {
		// TODO Auto-generated method stub
		return abnormalstateMapper.selectByPrimarytuData(deviceCode);
	}

}
