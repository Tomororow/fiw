package com.fourfaith.factorManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.factorManage.model.IraFAllDetails;

/**
 * IraFAllDetails interface
 * @author 
 */
public interface IraFAllDetailsService{

	int deleteByPrimaryKey(String id);

	int insert(IraFAllDetails record);

	int insertSelective(IraFAllDetails record);

	IraFAllDetails selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(IraFAllDetails record);

	int updateByPrimaryKey(IraFAllDetails record);

	Integer getCount(Map<String, Object> params);

	List<IraFAllDetails> getList(Map<String, Object> params);
	
	/**
	 * 获取统计值数据（最大、最小、平均值）
	 * @param params
	 */
	List<IraFAllDetails> getStatisList(Map<String, Object> params);

	Integer getStatisCount(Map<String, Object> params);
	
	/**
	 * 获取平均值报表数据
	 * @param params
	 */
	List<IraFAllDetails> getAvgList(Map<String, Object> params);
	
	/**
	 * 获取最小累计流量
	 * @param params
	 */
	List<IraFAllDetails> getMinAddList(Map<String, Object> params);
	
	/**
	 * @param params
	 */
	IraFAllDetails getLastest(Map<String, Object> params);
	
}