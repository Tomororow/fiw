package com.fourfaith.alarmManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.alarmManage.dao.IntelligentAnalysisMapper;
import com.fourfaith.alarmManage.model.IntelligentAnalysis;
import com.fourfaith.alarmManage.model.IntelligentDeal;
import com.fourfaith.alarmManage.service.IntelligentAnalysisService;

import common.Logger;

/**
 * @ClassName: IntelligentAnalysisServiceImpl
 * @Description: 异常机井智能分析service实现
 * 2017年1月10日
 * Administrator: xiaogaoxiang
 */
@Service("IntelligentAnalysisService")
public class IntelligentAnalysisServiceImpl implements IntelligentAnalysisService{
	
	protected Logger logger = Logger.getLogger(IntelligentAnalysisServiceImpl.class);

	@Autowired
	private IntelligentAnalysisMapper intelligentAnalysisMapper;

	/**
	 * TODO: 保存异常机井智能分析信息
	 * @param intelligentAnalysis
	 */
	public void add(IntelligentAnalysis intelligentAnalysis) {
		intelligentAnalysisMapper.add(intelligentAnalysis);
	}

	/**
	 * TODO: 根据设备编码删除之前的信息
	 * @param intelligentAnalysis
	 */
	public void delete(IntelligentAnalysis intelligentAnalysis) {
		intelligentAnalysisMapper.delete(intelligentAnalysis);
	}
	
	/**
	 * 统计异常机井信息数量
	 */
	public Integer getCount(Map<String, Object> params) {
		return intelligentAnalysisMapper.getCount(params);
	}

	/**
	 * 获取异常机井信息列表
	 */
	@Override
	public List<IntelligentDeal> getList(Map<String, Object> params) {
		return intelligentAnalysisMapper.getList(params);
	}

	/**
	 * TODO: 查询所有的异常机井信息数量
	 */
	public int getSum() {
		return intelligentAnalysisMapper.getSum();
	}

	/**
	 * TODO: 根据id查询异常机井信息
	 * @param id
	 */
	public IntelligentAnalysis selectById(String id) {
		return intelligentAnalysisMapper.selectById(id);
	}
	
}