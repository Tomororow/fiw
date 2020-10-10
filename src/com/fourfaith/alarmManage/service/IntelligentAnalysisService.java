package com.fourfaith.alarmManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.alarmManage.model.IntelligentAnalysis;
import com.fourfaith.alarmManage.model.IntelligentDeal;

/**
 * @ClassName: IntelligentAnalysisService
 * @Description: 异常机井智能分析service接口
 * @Author: xiaogaoxiang
 * @Date: 2017年1月10日
 */
public interface IntelligentAnalysisService {

	/**
	 * TODO: 保存异常机井智能分析信息
	 * @param intelligentAnalysis
	 */
	void add(IntelligentAnalysis intelligentAnalysis);

	/**
	 * TODO: 根据设备编码删除之前的信息
	 * @param intelligentAnalysis
	 */
	void delete(IntelligentAnalysis intelligentAnalysis);

	/**
	 * @Title: getCount
	 * @Description: 统计异常机井信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 获取异常机井信息列表
	 * @param: @param params
	 * @return: List<IntelligentAnalysis>
	 */
	List<IntelligentDeal> getList(Map<String, Object> params);

	/**
	 * TODO: 查询所有的异常机井信息数量
	 * 2017年1月11日
	 * Administrator: xiaogaoxiang
	 */
	int getSum();

	/**
	 * TODO: 根据id查询异常机井信息
	 * @param id
	 * 2017年1月11日
	 * Administrator: xiaogaoxiang
	 */
	IntelligentAnalysis selectById(String id);

}