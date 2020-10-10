package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysInfo;

/**
 * @ClassName: SysInfoService
 * @Description: 平台信息发布service接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:32:30
 */
public interface SysInfoService{

	SysInfo findByData(String infoKey);
	
	String update(SysInfo model);
	
}