package com.fourfaith.siteManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.siteManage.model.StAlarmInfo;

/**
 * @ClassName: StAlarmInfoMapper
 * @Description: 报警记录dao接口
 * @Author: zhaojx
 */
public interface StAlarmInfoMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(StAlarmInfo record);

    int insertSelective(StAlarmInfo record);

    StAlarmInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StAlarmInfo record);

    int updateByPrimaryKey(StAlarmInfo record);
    
	Integer getCount(Map<String, Object> params);

	List<StAlarmInfo> getList(Map<String, Object> params);
	
}