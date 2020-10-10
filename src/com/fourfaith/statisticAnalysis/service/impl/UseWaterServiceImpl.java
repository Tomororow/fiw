package com.fourfaith.statisticAnalysis.service.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.statisticAnalysis.dao.UseWaterMapper;
import com.fourfaith.statisticAnalysis.model.RptUseWaterDetail;
import com.fourfaith.statisticAnalysis.model.RptUseWaterDetailVO;
import com.fourfaith.statisticAnalysis.model.UseWaterDataOfDay;
import com.fourfaith.statisticAnalysis.model.UseWaterDataOfMonth;
import com.fourfaith.statisticAnalysis.model.UseWaterDataOfYear;
import com.fourfaith.statisticAnalysis.model.UseWaterResultTemp;
import com.fourfaith.statisticAnalysis.model.useWaterOfDayByExcelVO;
import com.fourfaith.statisticAnalysis.model.useWaterOfMonthByExcelVO;
import com.fourfaith.statisticAnalysis.model.useWaterOfYearByExcelVO;
import com.fourfaith.statisticAnalysis.service.UseWaterService;

/**
 * @ClassName: UseWaterServiceImpl
 * @Description: 用水信息统计service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午6:14:41
 */
@Service("UseWaterService")
public class UseWaterServiceImpl implements UseWaterService {
	
	protected Logger logger = Logger.getLogger(UseWaterServiceImpl.class);
	
	@Autowired
	private UseWaterMapper useWaterMapper;

	@Override
	public List<RptUseWaterDetail> getList(Map<String, Object> params) {
		return useWaterMapper.getList(params);
	}
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		return useWaterMapper.getCount(params);
	}

	@Override
	public Integer getSum(Map<String, Object> params) {
		return useWaterMapper.getSum(params);
	}

	/**
     * 查询出每口井的用水总量（每个月）
     * @param params
     * 2016年11月13日
     */
	public List<RptUseWaterDetail> getSumList(Map<String, Object> params) {
		return useWaterMapper.getSumList(params);
	}

	/**
	 * 查询所有水卡用水信息
	 * @param params
	 * 2016年12月20日
	 */
	public List<RptUseWaterDetail> getTotalWaterList(Map<String, Object> params) {
		return useWaterMapper.getTotalWaterList(params);
	}

	/**
	 * 查询出该时间段内，所有机井用水信息
	 * @param params
	 * 2016年12月29日
	 */
	public List<RptUseWaterDetail> selectUseWaterList(Map<String, Object> params) {
		return useWaterMapper.selectUseWaterList(params);
	}

	/**
	 * 查询出所有机井最新用水信息
	 * 2017年2月24日
	 */
	public List<RptUseWaterDetail> selectNewUseWaterInfo() {
		return useWaterMapper.selectNewUseWaterInfo();
	}

	/**
	 * 获取所有的用水信息
	 * 2017年4月11日
	 */
	public List<RptUseWaterDetail> selectAll(Map<String, Object> params) {
		return useWaterMapper.selectAll(params);
	}

	/**
	 * 根据ID删除重复信息
	 * @param id
	 * 2017年4月11日
	 */
	public void delete(String id) {
		useWaterMapper.delete(id);
	}

	/**
	 * 根据设备id，查询用水记录表中是否有信息
	 * @param wellId
	 * 2017年4月18日
	 */
	public RptUseWaterDetail getUseWaterInfo(String wellId) {
		return useWaterMapper.getUseWaterInfo(wellId);
	}

	/**
	 * 新增用水记录
	 * @param rptUseWaterDetail
	 * 2017年4月18日
	 */
	public void add(RptUseWaterDetail rptUseWaterDetail) {
		useWaterMapper.add(rptUseWaterDetail);
	}

	/**
	 * 修改用水记录
	 * @param ruwd
	 * 2017年4月18日
	 */
	public void edit(RptUseWaterDetail ruwd) {
		useWaterMapper.edit(ruwd);
	}

	/**
	 * 查询所有符合条件的用水信息导出Excel表
	 * @param params
	 * 2017年4月18日
	 */
	public List<RptUseWaterDetailVO> getListExcel(Map<String, Object> params) {
		return useWaterMapper.getListExcel(params);
	}

	@Override
	public String findDeviceCodeByWellID(String wellID) {
		return useWaterMapper.getDeviceCodeByWellID(wellID);
	}

	@Override
	public List<RptUseWaterDetail> getUseWaterAmountList(Map<String, Object> params) {
		return useWaterMapper.getUseWaterAmountList(params);
	}

	@Override
	public void addUseWaterRecord(UseWaterResultTemp useWaterResultTemp) {
		useWaterMapper.addUseWaterRecord(useWaterResultTemp);
	}

	@Override
	public List<UseWaterResultTemp> getWaterDataOfDay() {
		return useWaterMapper.getWaterDataOfDay();
	}

	@Override
	public void insertWaterDataOfDay(UseWaterDataOfDay useWaterDataOfDay) {
		useWaterMapper.addWaterDataOfDay(useWaterDataOfDay);
	}
	
	@Override
	public BigDecimal getUseWaterStatistics(Map<String, Object> params) {
		return useWaterMapper.getUseWaterStatistics(params);
	}

	@Override
	public List<UseWaterDataOfDay> getWaterDataInfo(Map<String, Object> params) {
		return useWaterMapper.getWaterDataInfo(params);
	}
	
	@Override
	public BigDecimal getUseWaterStatisticsOfMonth(Map<String, Object> params) {
		return useWaterMapper.getUseWaterStatisticsOfMonth(params);
	}

	@Override
	public List<UseWaterDataOfDay> getWaterDataOfMonthInfo(
			Map<String, Object> params) {
		return useWaterMapper.getWaterDataOfMonthInfo(params);
	}
	
	@Override
	public BigDecimal getUseWaterStatisticsOfYear(Map<String, Object> params) {
		return useWaterMapper.getUseWaterStatisticsOfYear(params);
	}
	
	@Override
	public List<UseWaterDataOfDay> getWaterDataOfYearInfo(
			Map<String, Object> params) {
		return useWaterMapper.getWaterDataOfYearInfo(params);
	}

	@Override
	public List<useWaterOfDayByExcelVO> getListOfDayByExcel(
			Map<String, Object> params) {
		return useWaterMapper.getListOfDayByExcel(params);
	}

	@Override
	public List<useWaterOfMonthByExcelVO> getListOfMonthByExcel(
			Map<String, Object> params) {
		return useWaterMapper.getListOfMonthByExcel(params);
	}

	@Override
	public List<useWaterOfYearByExcelVO> getListOfYearByExcel(
			Map<String, Object> params) {
		return useWaterMapper.getListOfYearByExcel(params);
	}

	@Override
	public List<RptUseWaterDetail> selectUseWaterInfoList(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return useWaterMapper.selectUseWaterInfoList(params);
	}

	@Override
	public List<RptUseWaterDetail> selectUseWaterInfoCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return useWaterMapper.selectUseWaterInfoCount(params);
	}

	@Override
	public List<RptUseWaterDetail> findDataFlagZero() {
		// TODO Auto-generated method stub
		return useWaterMapper.findDataFlagZero();
	}

	@Override
	public UseWaterDataOfDay findDataOfDay(String deviceCode, String cardCode,
			int year, int month, int day) {
		// TODO Auto-generated method stub
		return useWaterMapper.findDataOfDay(deviceCode, cardCode, year, month, day);
	}

	@Override
	public UseWaterDataOfMonth findDataOfMonth(String deviceCode,
			String cardCode, int year, int month) {
		// TODO Auto-generated method stub
		return useWaterMapper.findDataOfMonth(deviceCode, cardCode, year, month);
	}

	@Override
	public UseWaterDataOfYear findDataOfYear(String deviceCode,
			String cardCode, int year) {
		// TODO Auto-generated method stub
		return useWaterMapper.findDataOfYear(deviceCode, cardCode, year);
	}

	@Override
	public int addWaterDataOfDay(UseWaterDataOfDay useWaterDataOfDay) {
		// TODO Auto-generated method stub
		return useWaterMapper.addWaterDataOfDay(useWaterDataOfDay);
	}

	@Override
	public int addWaterDataOfMonth(UseWaterDataOfMonth useWaterDataOfMonth) {
		// TODO Auto-generated method stub
		return useWaterMapper.addWaterDataOfMonth(useWaterDataOfMonth);
	}

	@Override
	public int addWaterDataOfYear(UseWaterDataOfYear useWaterDataOfYear) {
		// TODO Auto-generated method stub
		return useWaterMapper.addWaterDataOfYear(useWaterDataOfYear);
	}

	@Override
	public int updateWaterDataOfDay(UseWaterDataOfDay useWaterDataOfDay) {
		// TODO Auto-generated method stub
		return useWaterMapper.updateWaterDataOfDay(useWaterDataOfDay);
	}

	@Override
	public int updateWaterDataOfMonth(UseWaterDataOfMonth useWaterDataOfMonth) {
		// TODO Auto-generated method stub
		return useWaterMapper.updateWaterDataOfMonth(useWaterDataOfMonth);
	}

	@Override
	public int updateWaterDataOfYear(UseWaterDataOfYear useWaterDataOfYear) {
		// TODO Auto-generated method stub
		return useWaterMapper.updateWaterDataOfYear(useWaterDataOfYear);
	}

	@Override
	public int updateChilter(String id) {
		// TODO Auto-generated method stub
		return useWaterMapper.updateChilter(id);
	}

	@Override
	public BigDecimal findSumWaterOfYear(String deviceCode) {
		// TODO Auto-generated method stub
		return useWaterMapper.findSumWaterOfYear(deviceCode);
	}

	@Override
	public List<RptUseWaterDetail> selectdayHour(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return useWaterMapper.selectdayHour(params);
	}

	@Override
	public List<RptUseWaterDetail> selectmonthday(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return useWaterMapper.selectmonthday(params);
	}

	@Override
	public List<RptUseWaterDetail> selectyearmonth(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return useWaterMapper.selectyearmonth(params);
	}

	@Override
	public RptUseWaterDetail findWaterSumDMYList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return useWaterMapper.findWaterSumDMYList(params);
	}

	@Override
	public List<RptUseWaterDetail> getListOfMonthBigData(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return useWaterMapper.getListOfMonthBigData(params);
	}

	@Override
	public List<UseWaterDataOfDay> getWaterDayBigData(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return useWaterMapper.getWaterDayBigData(params);
	}

	@Override
	public List<UseWaterDataOfDay> selectpeakwater(int cycle, String deviceCode) {
		// TODO Auto-generated method stub
		return useWaterMapper.selectpeakwater(cycle, deviceCode);
	}

	@Override
	public int delDirty() {
		return useWaterMapper.delDirty();
	}

}