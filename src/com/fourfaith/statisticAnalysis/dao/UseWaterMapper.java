package com.fourfaith.statisticAnalysis.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.statisticAnalysis.model.RptUseWaterDetail;
import com.fourfaith.statisticAnalysis.model.RptUseWaterDetailVO;
import com.fourfaith.statisticAnalysis.model.UseWaterDataOfDay;
import com.fourfaith.statisticAnalysis.model.UseWaterDataOfMonth;
import com.fourfaith.statisticAnalysis.model.UseWaterDataOfYear;
import com.fourfaith.statisticAnalysis.model.UseWaterResultTemp;
import com.fourfaith.statisticAnalysis.model.useWaterOfDayByExcelVO;
import com.fourfaith.statisticAnalysis.model.useWaterOfMonthByExcelVO;
import com.fourfaith.statisticAnalysis.model.useWaterOfYearByExcelVO;

/**
 * @ClassName: UseWaterMapper
 * @Description: 用水信息统计dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午5:02:23
 */
public interface UseWaterMapper {
	
	/**
	 * 查询flag为0的数据（没有计算的水量数据）
	 * @return liuhainian
	 */
	List<RptUseWaterDetail> findDataFlagZero();
	
	int updateChilter(String id);
	/**
	 * 日统计查询表
	 * @param deviceCode
	 * @param cardCode
	 * @param year
	 * @param month
	 * @param day
	 * @return liuhainian
	 */
	UseWaterDataOfDay findDataOfDay(@Param("deviceCode") String deviceCode,@Param("cardCode") String cardCode,@Param("year") int year,@Param("month")int month,@Param("day") int day);
	/**
	 * 月统计查询表
	 * @param deviceCode
	 * @param cardCode
	 * @param year
	 * @param month
	 * @return liuhainian
	 */
	UseWaterDataOfMonth findDataOfMonth(@Param("deviceCode") String deviceCode,@Param("cardCode") String cardCode,@Param("year") int year,@Param("month")int month);
	/**
	 * 年统计查询表
	 * @param deviceCode
	 * @param cardCode
	 * @param year
	 * @return liuhainian
	 */
	UseWaterDataOfYear findDataOfYear(@Param("deviceCode") String deviceCode,@Param("cardCode") String cardCode,@Param("year") int year);
	
	/**
	 * @Title: addWaterDataOfDay
	 * @Description: 增加每天用水记录
	 * @param: @param useWaterDataOfDay
	 * @return: void
	 * @Author: zhaojinxin
	 */
	int addWaterDataOfDay(UseWaterDataOfDay useWaterDataOfDay);
	
	/**
	 * 增加月用水量数据
	 * @param useWaterDataOfMonth
	 */
	int addWaterDataOfMonth(UseWaterDataOfMonth useWaterDataOfMonth);
	/**
	 * 增加年用水量数据
	 * @param useWaterDataOfYear
	 */
	int addWaterDataOfYear(UseWaterDataOfYear useWaterDataOfYear);
	/**
	 * 修改日用水量数据
	 * @param useWaterDataOfMonth
	 */
	int updateWaterDataOfDay(UseWaterDataOfDay useWaterDataOfDay);
	/**
	 * 修改月用水量数据
	 * @param useWaterDataOfMonth
	 */
	int updateWaterDataOfMonth(UseWaterDataOfMonth useWaterDataOfMonth);
	/**
	 * 修改年用水量数据
	 * @param useWaterDataOfYear
	 */
	int updateWaterDataOfYear(UseWaterDataOfYear useWaterDataOfYear);
	
	BigDecimal findSumWaterOfYear(String deviceCode);
	
	/**
	 * @Title: getList
	 * @Description: 用水信息列表
	 * @param: @param params
	 * @return: List<RptUseWaterDetail>
	 */
	List<RptUseWaterDetail> getList(Map<String, Object> params);
	
	/**
	 * @Title: getCount
	 * @Description: 统计信息条数
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);
	
	/**
	 * @Title: getSum
	 * @Description: 获取数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getSum(Map<String, Object> params);

	/**
	 * 查询出每口井的用水总量（每个月）
	 * @param params
	 * 2016年11月13日
	 * Administrator: xiaogaoxiang
	 */
	List<RptUseWaterDetail> getSumList(Map<String, Object> params);

	/**
	 * 查询所有水卡用水信息
	 * @param params
	 * 2016年12月20日
	 * Administrator: xiaogaoxiang
	 */
	List<RptUseWaterDetail> getTotalWaterList(Map<String, Object> params);

	/**
	 * 查询出该时间段内，所有机井用水信息
	 * @param params
	 * 2016年12月29日
	 * Administrator: xiaogaoxiang
	 */
	List<RptUseWaterDetail> selectUseWaterList(Map<String, Object> params);

	/**
	 * 查询出所有机井最新用水信息
	 * 2017年2月24日
	 * Administrator: xiaogaoxiang
	 */
	List<RptUseWaterDetail> selectNewUseWaterInfo();

	/**
	 * 获取所有的用水信息
	 * 2017年4月11日
	 * Administrator: xiaogaoxiang
	 */
	List<RptUseWaterDetail> selectAll(Map<String, Object> params);

	/**
	 * 根据ID删除重复信息
	 * @param id
	 * 2017年4月11日
	 * Administrator: xiaogaoxiang
	 */
	void delete(String id);

	/**
	 * 根据设备id，查询用水记录表中是否有信息
	 * @param wellId
	 * 2017年4月18日
	 * Administrator: xiaogaoxiang
	 */
	RptUseWaterDetail getUseWaterInfo(String wellId);

	/**
	 * 新增用水记录
	 * @param rptUseWaterDetail
	 * 2017年4月18日
	 * Administrator: xiaogaoxiang
	 */
	void add(RptUseWaterDetail rptUseWaterDetail);

	/**
	 * 修改用水记录
	 * @param ruwd
	 * 2017年4月18日
	 * Administrator: xiaogaoxiang
	 */
	void edit(RptUseWaterDetail ruwd);

	/**
	 * 查询所有符合条件的用水信息导出Excel表
	 * @param params
	 * 2017年4月18日
	 * Administrator: xiaogaoxiang
	 */
	List<RptUseWaterDetailVO> getListExcel(Map<String, Object> params);
	
	/**
	 * @Title: getDeviceCodeByWellID
	 * @Description: 根据恒泽WellID获取设备编码
	 * @param wellID
	 * @return: String
	 * @Author: zhaojx
	 */
	String getDeviceCodeByWellID (String wellID);
	
	/**
	 * @Title: getUseWaterAmountList
	 * @Description: 获取计算用水量列表
	 * @return: List<RptUseWaterDetail>
	 * @Author: zhaojinxin
	 */
	List<RptUseWaterDetail> getUseWaterAmountList(Map<String, Object> params);
	
	/**
	 * @Title: addUseWaterRecord
	 * @Description: 定时增加用水统计记录
	 * @param: @param useWaterResultAmount
	 * @return: void
	 * @Author: zhaojinxin
	 */
	void addUseWaterRecord(UseWaterResultTemp useWaterResultTemp);
	
	/**
	 * @Title: getWaterDataOfDay
	 * @Description: 按天统计用水量
	 * @return: List<UseWaterResultTemp>
	 * @Author: zhaojinxin
	 */
	List<UseWaterResultTemp> getWaterDataOfDay();
	

	
	/**
	 * @Title: getUseWaterStatistics
	 * @Description: 日用水量统计
	 * @param: @param params
	 * @return: BigDecimal
	 * @Author: zhaojinxin
	 */
	BigDecimal getUseWaterStatistics(Map<String, Object> params);
	
	/**
	 * @Title: getWaterDataInfo
	 * @Description: 当日用水列表
	 * @param: @param params
	 * @return: List<UseWaterDataOfDay>
	 * @Author: zhaojinxin
	 */
	List<UseWaterDataOfDay> getWaterDataInfo(Map<String, Object> params);
	
	/**
	 * @Title: getUseWaterStatisticsOfMonth
	 * @Description: 月用水量统计
	 * @param: @param params
	 * @return: BigDecimal
	 * @Author: zhaojinxin
	 */
	BigDecimal getUseWaterStatisticsOfMonth(Map<String, Object> params);
	
	/**
	 * @Title: getWaterDataOfMonthInfo
	 * @Description: 月用水列表
	 * @param: @param params
	 * @return: List<UseWaterDataOfDay>
	 * @Author: zhaojinxin
	 */
	List<UseWaterDataOfDay> getWaterDataOfMonthInfo(Map<String, Object> params);
	
	/**
	 * @Title: getUseWaterStatisticsOfYear
	 * @Description: 年用水量统计
	 * @param: @param params
	 * @return: BigDecimal
	 * @Author: zhaojinxin
	 */
	BigDecimal getUseWaterStatisticsOfYear(Map<String, Object> params);
	
	/**
	 * @Title: getWaterDataOfYearInfo
	 * @Description: 年用水列表
	 * @param: @param params
	 * @return: List<UseWaterDataOfDay>
	 * @Author: zhaojinxin
	 */
	List<UseWaterDataOfDay> getWaterDataOfYearInfo(Map<String, Object> params);
	
	/**
	 * @Title: getListOfDayByExcel
	 * @Description: 日用水报表导出
	 * @param: @param params
	 * @return: List<useWaterOfDayByExcelVO>
	 * @Author: zhaojinxin
	 */
	List<useWaterOfDayByExcelVO> getListOfDayByExcel(Map<String, Object> params);
	
	/**
	 * @Title: getListOfMonthByExcel
	 * @Description: 月用水报表导出
	 * @param: @param params
	 * @return: List<useWaterOfMonthByExcelVO>
	 * @Author: zhaojinxin
	 */
	List<useWaterOfMonthByExcelVO> getListOfMonthByExcel(Map<String, Object> params);
	
	/**
	 * @Title: getListOfYearByExcel
	 * @Description: 年用水报表导出
	 * @param: @param params
	 * @return: List<useWaterOfYearByExcelVO>
	 * @Author: zhaojinxin
	 */
	List<useWaterOfYearByExcelVO> getListOfYearByExcel(Map<String, Object> params);
	
	/**
	 * 个人用水记录查询
	 * @param params
	 * @return
	 */
	List<RptUseWaterDetail>  selectUseWaterInfoList(Map<String, Object> params);
	/**
	 * 个人用水记录总数查询
	 * @param params
	 * @return
	 */
	List<RptUseWaterDetail> selectUseWaterInfoCount(Map<String, Object> params);
	//日累计中按小时求和用水量（日累计柱状图）
	List<RptUseWaterDetail> selectdayHour(Map<String, Object> params);
	//月累计柱状图
	List<RptUseWaterDetail> selectmonthday(Map<String, Object> params);
	//年累计柱状图
	List<RptUseWaterDetail> selectyearmonth(Map<String, Object> params);
	
	RptUseWaterDetail findWaterSumDMYList(Map<String, Object> params);
	
	List<RptUseWaterDetail> getListOfMonthBigData(Map<String, Object> params);
	
	List<UseWaterDataOfDay> getWaterDayBigData(Map<String, Object> params);
	
	//高峰期用水量异常
		List<UseWaterDataOfDay> selectpeakwater(@Param("cycle")int cycle,@Param("deviceCode")String deviceCode);
	//删除2010年脏数据
	int delDirty();
}