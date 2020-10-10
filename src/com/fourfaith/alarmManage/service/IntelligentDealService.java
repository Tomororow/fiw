package com.fourfaith.alarmManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.alarmManage.model.IntelligentDeal;

/**
 * @ClassName: IntelligentDealService
 * @Description: 异常机井处理结果service接口
 * @Author: xiaogaoxiang
 * @Date: 2017年1月12日
 */
public interface IntelligentDealService {

	/**
	 * TODO: 新增异常机井处理结果信息
	 * @param intelligentDeal
	 */
	String add(IntelligentDeal intelligentDeal);

	/**
	 * TODO: 根据异常机井表ID查询异常机井处理结果
	 * @param intelligentAnalysisId
	 * 2017年1月12日
	 * Administrator: xiaogaoxiang
	 */
	IntelligentDeal selectByIntelligentAnalysisId(String intelligentAnalysisId);

	/**
	 * TODO: 如果处理结果为未解决，则删除之前信息，重新添加
	 * @param ids
	 * 2017年1月12日
	 * Administrator: xiaogaoxiang
	 */
	void delete(IntelligentDeal ids);

	/**
	 * TODO: 如果处理结果为待解决，则修改其时间
	 * @param intelligentDeal
	 * 2017年1月12日
	 * Administrator: xiaogaoxiang
	 */
	String update(IntelligentDeal intelligentDeal);

	/**
	 * @Title: getCount
	 * @Description: 统计异常机井处理结果数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 获取异常机井处理结果列表
	 * @param: @param params
	 * @return: List<IntelligentDeal>
	 */
	List<IntelligentDeal> getList(Map<String, Object> params);

}