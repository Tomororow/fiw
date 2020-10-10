package com.fourfaith.alarmManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





import com.fourfaith.alarmManage.dao.WarningRecordDetailMapper;
import com.fourfaith.alarmManage.model.WarningRecordDetail;
import com.fourfaith.alarmManage.service.WarningRecordDetailService;
import com.fourfaith.paramterManage.dao.WarnerrortypeMapper;
import com.fourfaith.paramterManage.model.Warnerrortype;

@Service
public class WarningRecordDetailServiceImpl implements WarningRecordDetailService {

	@Autowired
	private WarningRecordDetailMapper warningRecordDetailMapper;
	@Autowired
	private WarnerrortypeMapper warnerrortypemapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(WarningRecordDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(WarningRecordDetail record) {
		// TODO Auto-generated method stub
		return warningRecordDetailMapper.insertSelective(record);
	}

	@Override
	public WarningRecordDetail selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(WarningRecordDetail record) {
		// TODO Auto-generated method stub
		return warningRecordDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WarningRecordDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer selectWarnCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return warningRecordDetailMapper.selectWarnCount(params);
	}

	@Override
	public List<WarningRecordDetail> selectWarnList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return warningRecordDetailMapper.selectWarnList(params);
	}

	@Override
	public Integer selectByWarnCount(int warnState,List<String> waterAreaId) {
		// TODO Auto-generated method stub
		return warningRecordDetailMapper.selectByWarnCount(warnState,waterAreaId);
	}

	@Override
	public List<WarningRecordDetail> selectcode() {
		// TODO Auto-generated method stub
		return warningRecordDetailMapper.selectcode();
	}

	@Override
	public Integer EquipmentListCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return warningRecordDetailMapper.EquipmentListCount(params);
	}

	@Override
	public List<WarningRecordDetail> EquipmentList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return warningRecordDetailMapper.EquipmentList(params);
	}

	@Override
	public WarningRecordDetail selectMessTime(String deviceCode,String warnType) {
		// TODO Auto-generated method stub
		return warningRecordDetailMapper.selectMessTime(deviceCode,warnType);
	}

	@Override
	public List<Warnerrortype> selectAbnormalType() {
		// TODO Auto-generated method stub
		return warnerrortypemapper.selectAbnormalType();
	}

	@Override
	public List<WarningRecordDetail> warnAll() {
		// TODO Auto-generated method stub
		return warningRecordDetailMapper.warnAll();
	}

	

}
