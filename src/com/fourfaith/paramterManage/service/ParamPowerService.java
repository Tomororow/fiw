package com.fourfaith.paramterManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.paramterManage.model.ParamPower;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: ParamPowerService
 * @Description: 功率参数service接口
 * @Author: zhaojx
 * @Date: 2017年5月13日 下午6:06:22
 */
public interface ParamPowerService {

	/**
	 * 获取功率记录条数
	 * @return
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
	String add(ParamPower paramPower);

	/**
	 * 根据id查询功率信息
	 * @param id
	 * 2016年11月26日
	 */
	ParamPower findPowerById(String id);

	/**
	 * 修改功率设置
	 * @param paramPower
	 * 2016年11月26日
	 */
	String edit(ParamPower paramPower);

	/**
	 * 根据Id删除功率信息
	 * @param ids
	 * 2016年11月26日
	 */
	AjaxJson delete(String ids);

}