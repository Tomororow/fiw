package com.fourfaith.sysManage.dao;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.sysManage.model.Abnormalstate;

public interface AbnormalstateMapper {

    int deleteByPrimaryKey(String devicecode);

    int insert(Abnormalstate record);

    int insertSelective(Abnormalstate record);

    Abnormalstate selectByPrimaryKey(String devicecode);

    int updateByPrimaryKeySelective(Abnormalstate record);

    int updateByPrimaryKey(Abnormalstate record);
    
    Abnormalstate selectByPrimarytuData(@Param("deviceCode")String deviceCode);
}