package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.WellInfo;

/**
 * @ClassName: WellInfoMapper
 * @Description: 恒泽机井信息dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:14:54
 */
public interface WellInfoMapper {
	
	int insertWellInfo(WellInfo wellInfo);
	
	int updateByPrimaryKeySelective(WellInfo wellInfo);
	
	int deleteByPrimaryKey(String id);
	
}