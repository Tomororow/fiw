package com.fourfaith.paramterManage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.paramterManage.model.ParamPumpingWell;

/**
 * @ClassName: ParamPumpingWellMapper
 * @Description: 水泵参数dao接口
 * @Author: zhaojx
 */
public interface ParamPumpingWellMapper {

	/**
	 * 获取功率记录条数
	 */
	Integer getCount(Map<String, Object> params);
	
	/**
	 * 新增异常机井参数设置
	 * @param paramPumpingWell
	 */
	int insert(ParamPumpingWell paramPumpingWell);
	
	int insertSelective(ParamPumpingWell paramPumpingWell);
	
	int edit(ParamPumpingWell paramPumpingWell);

	/**
	 * 根据Id删除异常机井智能参数设置信息
	 * @param string
	 */
	void delete(String string);

	/**
	 * 获取功率信息集合
	 * @param params
	 */
	List<ParamPumpingWell> getList(Map<String, Object> params);

	/**
	 * 根据时间查询异常机井配置参数信息
	 * @param params
	 * 2016年12月29日
	 */
	List<ParamPumpingWell> getAllList(Map<String, Object> params);

	/**
	 * 根据当前日期查询异常机井信息
	 * @param nowDate
	 * 2017年1月8日
	 */
	List<ParamPumpingWell> selectParamPumpingWellInfoByNowDate(String nowDate);
	
	ParamPumpingWell findPumpingWellById(String id);
	
	ParamPumpingWell findParamPumpingWellsByDeviceCode(String deviceCode);
	
	int updateByDeviceCode(String deviceCode);
	
	int updateByPrimaryKey(ParamPumpingWell paramPumpingWell);
	
	int updateByPrimaryKeySelective(ParamPumpingWell paramPumpingWell);
	
	List<ParamPumpingWell> selectAll(Map<String, Object> params);
	//获取时间段
	ParamPumpingWell selecTimeMin();
	/*//查询机井预警不在线天数，最低用水量，机井不在线实际天数与日用水量
	List<ParamPumpingWell> selectwatertime(@Param("prevDay")String prevDay,@Param("nowDay")String nowDay );*/
	//查询机井预警不在线天数，
		List<ParamPumpingWell> selectwatertime();
		//高峰期用水量查询
		List<ParamPumpingWell> selectwaterpeckwater();
	
	
	
}