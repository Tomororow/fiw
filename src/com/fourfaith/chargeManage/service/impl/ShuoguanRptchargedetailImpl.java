package com.fourfaith.chargeManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.chargeManage.dao.ShuoguanRptchargedetailMapper;
import com.fourfaith.chargeManage.model.ShuoguanRptchargedetail;
import com.fourfaith.chargeManage.service.ShuoguanRptchargedetailService;


@Service("shuoguanRptchargedetailService")
public class ShuoguanRptchargedetailImpl implements ShuoguanRptchargedetailService {

	@Autowired
	private ShuoguanRptchargedetailMapper shuoguanRptchargedetailMapper;

	@Override
	public ShuoguanRptchargedetail getByDeviceCodeOnDetil(String code) {
		return shuoguanRptchargedetailMapper.getByDeviceCodeOnDetil(code);
	}
	

}
