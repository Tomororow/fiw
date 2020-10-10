package com.fourfaith.statisticAnalysis.service.impl;

import com.fourfaith.statisticAnalysis.dao.IndustrialdeviceMapper;
import com.fourfaith.statisticAnalysis.model.IndustrialDevice;
import com.fourfaith.statisticAnalysis.model.IndustrialDeviceExvoit;
import com.fourfaith.statisticAnalysis.service.IndustrialdeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("IndustrialdeviceService")
public class IndustrialdeviceServiceImpl implements IndustrialdeviceService {


    @Autowired
    IndustrialdeviceMapper industrialdeviceMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(IndustrialDevice record) {
        return 0;
    }

    @Override
    public int insertSelective(IndustrialDevice record) {
        return 0;
    }

    @Override
    public List<IndustrialDevice> selectByPrimaryKey(Map<String,Object> pageInfo) {
        return industrialdeviceMapper.selectByPrimaryKey(pageInfo);
    }

    @Override
    public int updateByPrimaryKeySelective(IndustrialDevice record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(IndustrialDevice record) {
        return 0;
    }

    @Override
    public int updateByKeyName(String deviceName, String ids) {
        return industrialdeviceMapper.updateByKeyName(deviceName,ids);
    }

    @Override
    public int updateByKeyNameState(String state, String devicePort) {
        return industrialdeviceMapper.updateByKeyNameState(state,devicePort);
    }

    @Override
    public List<IndustrialDevice> selectHistoryList(Map<String, Object> pageInfo) {
        return industrialdeviceMapper.selectHistoryList(pageInfo);
    }

    @Override
    public List<IndustrialDeviceExvoit> selectHistoryListExcel(Map<String, Object> pageInfo) {
        return industrialdeviceMapper.selectHistoryListExcel(pageInfo);
    }

    @Override
    public IndustrialDevice findWaterTotal(Map<String,Object> pageInfo) {
        return industrialdeviceMapper.findWaterTotal(pageInfo);
    }
}
