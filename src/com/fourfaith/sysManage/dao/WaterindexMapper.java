package com.fourfaith.sysManage.dao;


import java.util.List;

import com.fourfaith.sysManage.model.Waterindex;
import org.apache.ibatis.annotations.Param;

public interface WaterindexMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Waterindex record);

    int insertSelective(Waterindex record);

    Waterindex selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Waterindex record);

    int updateByPrimaryKey(Waterindex record);

    List<Waterindex> selectWaterAll(@Param("waterAreaId") String waterAreaId,@Param("type")String type);
}