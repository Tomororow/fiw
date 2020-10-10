package com.fourfaith.alarmManage.service;

import java.util.List;
import java.util.Map;




import com.fourfaith.alarmManage.model.WarningRecordDetail;
import com.fourfaith.paramterManage.model.Warnerrortype;
import com.fourfaith.sysManage.model.Tripshistory;

public interface WarningRecordDetailService {
	int deleteByPrimaryKey(String id);

    int insert(WarningRecordDetail record);

    int insertSelective(WarningRecordDetail record);

    WarningRecordDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WarningRecordDetail record);

    int updateByPrimaryKey(WarningRecordDetail record);
    //智能预警分页
    Integer selectWarnCount(Map<String, Object> params);
    //智能预警查询
    List<WarningRecordDetail> selectWarnList(Map<String, Object> params);

    Integer selectByWarnCount(int warnState,List<String> waterAreaId);
    
    List<WarningRecordDetail> selectcode();
    
    WarningRecordDetail selectMessTime(String deviceCode,String warnType);
    
    //设备故障查询分页
    Integer EquipmentListCount(Map<String, Object> params);
    //设备故障查询
    List<WarningRecordDetail> EquipmentList(Map<String, Object> params);
    //异常类型查询
    List<Warnerrortype> selectAbnormalType();
    //预警信息查询   跳闸
    List<WarningRecordDetail> warnAll();
}
