package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysLog;

/**
 * @ClassName: SysLogService
 * @Description: 日志service接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:32:51
 */
public interface SysLogService{

	int deleteByPrimaryKey(String id);

	int insert(SysLog record);

	int insertSelective(SysLog record);

	SysLog selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysLog record);

	int updateByPrimaryKey(SysLog record);

	Integer getCount(Map<String, Object> params);

	List<SysLog> getList(Map<String, Object> params);

	SysLog findById(String Id);
	
	String add(SysLog model);
	
}