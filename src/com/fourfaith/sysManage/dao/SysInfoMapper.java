package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysInfo;

/**
 * @ClassName: SysInfoMapper
 * @Description: 平台信息发布dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:09:30
 */
public interface SysInfoMapper {

	SysInfo findByInfoKey(String infoKey);

	int updateByInfoKey(SysInfo sysInfo);

}