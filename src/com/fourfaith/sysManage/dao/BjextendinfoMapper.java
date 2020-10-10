package com.fourfaith.sysManage.dao;

import java.util.List;

import com.fourfaith.sysManage.model.Bjextendinfo;

public interface BjextendinfoMapper {

    int deleteByPrimaryKey(String id);

    int insert(Bjextendinfo record);

    int insertSelective(Bjextendinfo record);

    Bjextendinfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Bjextendinfo record);

    int updateByPrimaryKey(Bjextendinfo record);
    
    List<Bjextendinfo> selectAll();
}