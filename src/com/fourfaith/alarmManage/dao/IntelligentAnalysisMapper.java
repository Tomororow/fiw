package com.fourfaith.alarmManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.alarmManage.model.IntelligentAnalysis;
import com.fourfaith.alarmManage.model.IntelligentDeal;

/**
 * @ClassName: IntelligentAnalysisMapper
 * @Description: 异常机井智能分析dao接口
 * @Author: zhaojx
 * @Date: 2018年5月9日 上午10:03:26
 */
public interface IntelligentAnalysisMapper {

	/**
	 * TODO: 保存异常机井智能分析信息
	 * @param intelligentAnalysis
	 * 2017年1月10日
	 * Administrator: xiaogaoxiang
	 */
	void add(IntelligentAnalysis intelligentAnalysis);

	/**
	 * TODO: 根据设备编码删除之前的信息
	 * @param intelligentAnalysis
	 * 2017年1月10日
	 * Administrator: xiaogaoxiang
	 */
	void delete(IntelligentAnalysis intelligentAnalysis);

	/**
	 * @Title: getCount
	 * @Description: 统计异常机井分析信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 异常机井智能分析列表
	 * @param: @param params
	 * @return: List<IntelligentDeal>
	 */
	List<IntelligentDeal> getList(Map<String, Object> params);

	/**
	 * TODO: 查询所有的异常机井信息数量
	 * 2017年1月11日
	 * Administrator : xiaogaoxiang
	 */
	int getSum();

	/**
	 * TODO: 根据id查询异常机井信息
	 * @param id
	 * 2017年1月11日
	 * Administrator : xiaogaoxiang
	 */
	IntelligentAnalysis selectById(String id);

}