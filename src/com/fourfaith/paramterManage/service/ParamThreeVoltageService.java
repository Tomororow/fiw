package com.fourfaith.paramterManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamThreeVoltage;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: ParamThreeVoltageService
 * @Description: 三相电压参数service接口
 * @Author: zhaojx
 * @Date: 2017年5月13日 下午6:07:47
 */
public interface ParamThreeVoltageService {

	/**
	 * 获取三相电压记录条数
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
	String add(ParamThreeVoltage paramThreeVoltage);

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
	String edit(ParamThreeVoltage paramThreeVoltage);

	/**
	 * 根据Id删除三项信息
	 * @param ids
	 * 2016年11月26日
	 */
	AjaxJson delete(String ids);

	/**
	 * @Title: getVoltageList
	 * @Description: 获取所有告警上下限电压
	 * @return: List<ParamThreeVoltage>
	 */
	List<ParamThreeVoltage> getVoltageList();
}