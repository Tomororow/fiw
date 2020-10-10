package com.fourfaith.paramterManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamThreeVoltage;

/**
 * @ClassName: ParamThreeVoltageMapper
 * @Description: 三相电流dao接口
 * @Author: zhaojx
 */
public interface ParamThreeVoltageMapper {

	/**
	 * 获取三相电流记录条数
	 * 2016年11月26日
	 */
	Integer getCount();

	/**
	 * 获取三相电压信息集合
	 * @param params
	 * 2016年11月26日
	 */
	List<ParamThreeVoltage> getList(Map<String, Object> params);

	/**
	 * 新增三相电压设置
	 * @param paramThreeVoltage
	 * 2016年11月26日
	 */
	int add(ParamThreeVoltage paramThreeVoltage);

	/**
	 * 根据id查询三相电压信息
	 * @param id
	 * 2016年11月26日
	 */
	ParamThreeVoltage findThreeVoltageById(String id);

	/**
	 * 修改三相电压设置
	 * @param paramThreeVoltage
	 * 2016年11月26日
	 */
	int edit(ParamThreeVoltage paramThreeVoltage);

	/**
	 * 根据Id删除三项信息
	 * @param string
	 * 2016年11月26日
	 */
	void delete(String string);

	/**
	 * @Title: getVoltageList
	 * @Description: 获取所有告警上下限电压
	 * @return: List<ParamThreeVoltage>
	 */
	List<ParamThreeVoltage> getVoltageList();
}