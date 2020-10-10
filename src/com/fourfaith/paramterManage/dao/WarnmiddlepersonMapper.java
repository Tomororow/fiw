package com.fourfaith.paramterManage.dao;

import com.fourfaith.paramterManage.model.Warnmiddleperson;

public interface WarnmiddlepersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Warnmiddleperson record);

    int insertSelective(Warnmiddleperson record);

    Warnmiddleperson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Warnmiddleperson record);

    int updateByPrimaryKey(Warnmiddleperson record);
}