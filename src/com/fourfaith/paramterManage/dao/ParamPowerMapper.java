package com.fourfaith.paramterManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamPower;

/**
 * @ClassName: ParamPowerMapper
 * @Description: 功率dao接口
 * @Author: zhaojx
 */
public interface ParamPowerMapper {

	/**
	 * 获取功率记录条数
	 * 2016年11月26日
	 */
	Integer getCount();

	/**
	 * 获取功率信息集合
	 * @param params
	 * 2016年11月26日
	 */
	List<ParamPower> getList(Map<String, Object> params);

	/**
	 * 新增功率设置
	 * @param paramPower
	 * 2016年11月26日
	 */
	int add(ParamPower paramPower);

	/**
	 * 根据id查询功率信息
	 * @param id
	 * 2016年11月26日
	 */
	ParamPower findPowerById(String id);

	/**
	 * 修改功率设置
	 * @param paramPower
	 * @return
	 * 2016年11月26日
	 */
	int edit(ParamPower paramPower);

	/**
	 * 根据Id删除功率信息
	 * @param string
	 * 2016年11月26日
	 */
	void delete(String string);

}