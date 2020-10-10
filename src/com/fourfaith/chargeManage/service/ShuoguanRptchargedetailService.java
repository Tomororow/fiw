package com.fourfaith.chargeManage.service;

import com.fourfaith.chargeManage.model.ShuoguanRptchargedetail;

public interface ShuoguanRptchargedetailService {

	/**
     * @Title: getByDeviceCodeOnDetil
     * @Description: 根据机井编码查询充值返回信息
     * @param code
     * @return ShuoguanRptchargedetail
     * @author 刘海年
     * @date 2018年9月4日下午5:14:49
     */
    ShuoguanRptchargedetail getByDeviceCodeOnDetil(String code);
}
