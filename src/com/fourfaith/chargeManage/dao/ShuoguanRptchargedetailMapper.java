package com.fourfaith.chargeManage.dao;

import com.fourfaith.chargeManage.model.ShuoguanRptchargedetail;

public interface ShuoguanRptchargedetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShuoguanRptchargedetail record);

    int insertSelective(ShuoguanRptchargedetail record);

    ShuoguanRptchargedetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShuoguanRptchargedetail record);

    int updateByPrimaryKey(ShuoguanRptchargedetail record);
    
    /**
     * @Title: getByDeviceCodeOnDetil
     * @Description: 根据机井编码查询充值返回信息
     * @param code
     * @return
     * @return ShuoguanRptchargedetail
     * @author 刘海年
     * @date 2018年9月4日下午5:14:49
     */
    ShuoguanRptchargedetail getByDeviceCodeOnDetil(String code);
}