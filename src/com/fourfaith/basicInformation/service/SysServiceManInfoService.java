package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.basicInformation.model.SysServiceManInfo;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysServiceManInfoService
 * @Description: 维修技术员service接口
 * @Author: zhaojinxin
 * @Date: 2019年3月8日 下午3:47:11
 */
public interface SysServiceManInfoService{

	Integer getCount(Map<String, Object> params);

	List<SysServiceManInfo> getList(Map<String, Object> params);
	
	List<SysServiceManInfo> getAssignInfo();
	
	int insertSelective(SysServiceManInfo record);
	
	String add(SysServiceManInfo record);
	
	SysServiceManInfo selectByPrimaryKey(String id);
	
	int deleteByPrimaryKey(String id);
	
	SysServiceManInfo findById(String Id);
	
	AjaxJson deleteDis(String items);

	String update(SysServiceManInfo sysServiceManInfo);
	
	List<SysServiceManInfo> findPersonList(Map<String, Object> params);
	
	List<SysServiceManInfo> findMiddlePersonList(String code);
	
	List<SysServiceManInfo> findMiddlePersonListMap(Map<String, Object> params);
	
}