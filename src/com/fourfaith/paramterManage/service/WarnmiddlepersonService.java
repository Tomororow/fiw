package com.fourfaith.paramterManage.service;

import com.fourfaith.paramterManage.model.Warnmiddleperson;

public interface WarnmiddlepersonService {
	 int deleteByPrimaryKey(Integer id);

	    int insert(Warnmiddleperson record);

	    int insertSelective(Warnmiddleperson record);

	    Warnmiddleperson selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(Warnmiddleperson record);

	    int updateByPrimaryKey(Warnmiddleperson record);
}
