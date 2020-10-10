package com.fourfaith.waterRightManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.utils.AjaxJson;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;

/**
 * @ClassName: BaseDistWaterPlanService
 * @Description: 配水service接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:57:32
 */
public interface BaseDistWaterPlanService{

	int deleteByPrimaryKey(String id);

	int insert(BaseDistWaterPlan record);

	int insertSelective(BaseDistWaterPlan record);

	BaseDistWaterPlan selectByPrimaryKey(String id);

	Integer getCount(Map<String, Object> params);

	List<BaseDistWaterPlan> getList(Map<String, Object> params);

	BaseDistWaterPlan findById(String Id);
	
	String add(BaseDistWaterPlan model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	List<BaseDistWaterPlan> selectiDList(List<String> waterAreaId );
	
	BaseDistWaterPlan selectYR(String distYear,String distRound);

	/**
	 * 配水年份选择完后，筛选系统数据库
	 * 	1、如果当前配水方式，年份不存在（第一次添加），则轮次默认选择1，不能为其他轮次
	 * 	2、如果当前配水方式，年份存在（已经配过一次或多次），则轮次将读取数据库轮次+1
	 * @param plan
	 * 2016年10月26日
	 */
	Map<String, Object> getDistRoundInfo(BaseDistWaterPlan plan,String userName);

	/**
	 * 根据配水Id获取配水机井设备信息
	 * @param plan
	 * 2016年10月27日
	 */
	String showDistWaterDeviceInfo(BaseDistWaterPlan plan);
	
}