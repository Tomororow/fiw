package com.fourfaith.sysManage.service;

import java.util.List;

import com.fourfaith.sysManage.model.Bjextendinfo;

public interface BjextendinfoService {
	  int deleteByPrimaryKey(String id);

	    int insert(Bjextendinfo record);

	    int insertSelective(Bjextendinfo record);

	    Bjextendinfo selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(Bjextendinfo record);

	    int updateByPrimaryKey(Bjextendinfo record);
	    
	    List<Bjextendinfo> selectAll();

}
