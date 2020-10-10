package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.Abnormalstate;

public interface AbnormalstateService {
	 int deleteByPrimaryKey(String devicecode);

	    int insert(Abnormalstate record);

	    int insertSelective(Abnormalstate record);

	    Abnormalstate selectByPrimaryKey(String devicecode);

	    int updateByPrimaryKeySelective(Abnormalstate record);

	    int updateByPrimaryKey(Abnormalstate record);
	    
	    Abnormalstate selectByPrimarytuData(String deviceCode);
}
