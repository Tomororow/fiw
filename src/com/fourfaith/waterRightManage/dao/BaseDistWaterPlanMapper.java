package com.fourfaith.waterRightManage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.waterRightManage.model.BaseDistWaterPlanDevice;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;

/**
 * @ClassName: BaseDistWaterPlanMapper
 * @Description: 配水dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:51:20
 */
public interface BaseDistWaterPlanMapper {

	List<BaseDistWaterPlan> getList(Map<String, Object> params);
	
	Integer getCount(Map<String, Object> params);
	
	int insert(BaseDistWaterPlan record);

	int insertSelective(BaseDistWaterPlan record);
	
	BaseDistWaterPlan selectByPrimaryKey(String id);

	int deleteByPrimaryKey(String id);
	
	int deleteByDisId(String disId);
	
	List<BaseDistWaterPlan> selectiDList(List<String> waterAreaId );
	
	BaseDistWaterPlan selectYR(@Param("distYear")String distYear, @Param("distRound")String distRound);

	/**
	 * 配水年份选择完后，筛选系统数据库
	 * 	1、如果当前配水方式，年份不存在（第一次添加），则轮次默认选择1，不能为其他轮次
	 * 	2、如果当前配水方式，年份存在（已经配过一次或多次），则轮次将读取数据库轮次+1
	 * @param plan
	 * 2016年10月26日
	 */
	List<BaseDistWaterPlan> getDistRoundInfo(BaseDistWaterPlan plan);

	/**
	 * 保存配水机井设备信息
	 * @param bdwdi
	 * 2016年10月27日
	 */
	void insertBaseDistWaterPlanDevice(BaseDistWaterPlanDevice bdwdi);

	/**
	 * 根据配水信息ID删除对应的机井设备信息
	 * @param id
	 * 2016年10月27日
	 */
	void deleteBaseDistWaterPlanDeviceById(String id);
	
	//查询该配水计划是否被使用过
	List<BaseDistWaterPlan> selectrptplane(String id);
	
}