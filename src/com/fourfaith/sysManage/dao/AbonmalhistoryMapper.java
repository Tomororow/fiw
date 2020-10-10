package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.Abonmalhistory;

public interface AbonmalhistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Abonmalhistory record);

    int insertSelective(Abonmalhistory record);

    Abonmalhistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Abonmalhistory record);

    int updateByPrimaryKey(Abonmalhistory record);
    
    List<Abonmalhistory> findUnableHistory(Map<String,Object> params);
}