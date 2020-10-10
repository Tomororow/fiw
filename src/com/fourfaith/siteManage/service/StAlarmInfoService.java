package com.fourfaith.siteManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.siteManage.model.StAlarmInfo;

/**
 * @ClassName: StAlarmInfoService
 * @Description: 报警记录service接口
 * @Author: zhaojx
 * @Date: 2017年5月14日 上午11:40:40
 */
public interface StAlarmInfoService {
	
	int deleteByPrimaryKey(String id);

    int insert(StAlarmInfo record);

    int insertSelective(StAlarmInfo record);

    StAlarmInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StAlarmInfo record);

    int updateByPrimaryKey(StAlarmInfo record);
    
	Integer getCount(Map<String, Object> params);

	List<StAlarmInfo> getList(Map<String, Object> params);

}