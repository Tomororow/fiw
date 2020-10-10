package com.fourfaith.paramterManage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.paramterManage.model.Warnerrortype;

public interface WarnerrortypeService {
	  int deleteByPrimaryKey(Integer id);

	    int insert(Warnerrortype record);

	    int insertSelective(Warnerrortype record);
	    
	    Integer selectByCount();

	    List<Warnerrortype> selectByPrimaryKey(Integer id,String pageStart,String pageEnd);

	    int updateByPrimaryKeySelective(Warnerrortype record);

	    int updateByPrimaryKey(Warnerrortype record);
	    
	    Warnerrortype findByPrimaryCode(String abnormalcode);
}
