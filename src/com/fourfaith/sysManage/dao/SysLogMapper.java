package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysLog;

/**
 * @ClassName: SysLogMapper
 * @Description: 日志dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:09:49
 */
public interface SysLogMapper {

   	int deleteByPrimaryKey(String id);

	int insert(SysLog record);

	int insertSelective(SysLog record);

	SysLog selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysLog record);

	int updateByPrimaryKey(SysLog record);

	Integer getCount(Map<String, Object> params);

	List<SysLog> getList(Map<String, Object> params);

	SysLog findById(String Id);
	
}