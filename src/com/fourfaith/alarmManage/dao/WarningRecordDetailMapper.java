package com.fourfaith.alarmManage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.alarmManage.model.WarningRecordDetail;

public interface WarningRecordDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(WarningRecordDetail record);

    int insertSelective(WarningRecordDetail record);

    WarningRecordDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WarningRecordDetail record);

    int updateByPrimaryKey(WarningRecordDetail record);
    
    Integer selectWarnCount(Map<String, Object> params);
    
    List<WarningRecordDetail> selectWarnList(Map<String, Object> params);

    Integer selectByWarnCount(@Param("warnState")int warnState,@Param("waterAreaIdList")List<String> waterAreaIdList);
    
    WarningRecordDetail selectMessTime(@Param("deviceCode")String deviceCode,@Param("warnType")String warnType);
    
    List<WarningRecordDetail> selectcode();
    
    Integer EquipmentListCount(Map<String, Object> params);
    
    List<WarningRecordDetail> EquipmentList(Map<String, Object> params);
    //预警信息查询  跳闸
    List<WarningRecordDetail> warnAll();
   
}