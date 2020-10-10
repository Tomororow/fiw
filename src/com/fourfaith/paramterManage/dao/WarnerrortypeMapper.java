package com.fourfaith.paramterManage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.paramterManage.model.Warnerrortype;

public interface WarnerrortypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Warnerrortype record);

    int insertSelective(Warnerrortype record);
    
    Integer selectByCount();

    List<Warnerrortype> selectByPrimaryKey(@Param("id")Integer id,@Param("pageStart")String pageStart,@Param("pageEnd")String pageEnd);

    int updateByPrimaryKeySelective(Warnerrortype record);

    int updateByPrimaryKey(Warnerrortype record);
    
    Warnerrortype findByPrimaryCode(@Param("abnormalcode")String abnormalcode);
  //异常类型的查询
	List<Warnerrortype> selectAbnormalType();
}