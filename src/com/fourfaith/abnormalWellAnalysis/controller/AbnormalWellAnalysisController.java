package com.fourfaith.abnormalWellAnalysis.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.statisticAnalysis.model.RptUseWaterDetail;
import com.fourfaith.statisticAnalysis.service.UseWaterService;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.WaterAreaRecursionUtil;
import com.google.gson.Gson;

/**
 * @ClassName: AbnormalWellAnalysisController
 * @Description: 用水指标图形分析
 * @Author: zhaojx
 * @Date: 2018年5月7日 下午3:44:50
 */
@Controller
@RequestMapping(value="/abnormalWellAnalysis")
public class AbnormalWellAnalysisController {

	protected static final String abnormalWellJsp = "/page/history/abnormalWell/abnormalWell";
	protected static final String abnormalWellListJsp = "/page/history/abnormalWell/abnormalWellList";

	@Autowired
	private UseWaterService useWaterService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private SysAreaService sysAreaService;
	
	/**
	 * @Title: index
	 * @Description: 用水指标图形分析初始化页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @throws
	 */
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(abnormalWellJsp);
		return mav;
	}
	
	/**
	 * @Title: abnormalWellList
	 * @Description: 用水指标图形分析 行政区域方式
	 * @param: @param request
	 * @return: ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "/abnormalWellList", method = RequestMethod.POST)
	public ModelAndView abnormalWellList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(abnormalWellListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 递归获取行政区域及其子区域
		String areaId = "100c690948969d1316b377bdf0e73068";
		List<SysArea> sysAllAreaList = sysAreaService.getAllArea();
		List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, areaId);
		List<String> useWaterList = new ArrayList<String>();
		for(SysArea sa : sysAreaList) {
			useWaterList.add(sa.getId());
		}
		params.put("useWaterList", useWaterList);
		
		// 递归获取水管区域及其子区域
		String waterAreaId = "100c690948969d1316b377bdf0e73068";
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		// 获取当前所在年份  查询本年度总用水量
		Calendar nowCalendar = Calendar.getInstance();
		int nowYear = nowCalendar.get(Calendar.YEAR);
		
		params.put("startTime", nowYear+"-01-01 00:00:00");
   		params.put("endTime", nowYear+"-12-31 23:59:59");
   		
		List<RptUseWaterDetail> getTotalWaterList = useWaterService.getTotalWaterList(params);
		
		// 获取总用水量
		double useWaterSum = getSum(getTotalWaterList, "100c690948969d1316b377bdf0e73068", sysAllAreaList);
		// 获取城区总用水量
		double gzqUseWaterSum = getSum(getTotalWaterList, "2f6bdaed8d56484e82a49d20fb21c15e", sysAllAreaList);
		// 获取沙井镇总用水量
		double sjzUseWaterSum = getSum(getTotalWaterList, "aee4e09f381a4287910a158eee99e5a8", sysAllAreaList);
		// 获取明永乡总用水量
		double myxUseWaterSum = getSum(getTotalWaterList, "53e49ebb19ee40499112949bad30f2e4", sysAllAreaList);
		// 获取乌江镇总用水量
		double wjzUseWaterSum = getSum(getTotalWaterList, "c802a6ec48cf49f0a05caba723b73996", sysAllAreaList);
		// 合计
		// double totalSum = gzqUseWaterSum + sjzUseWaterSum + myxUseWaterSum + wjzUseWaterSum;
		List<String> useWaterMonthList = getUseWaterByMonth(getTotalWaterList);
		mav.addObject("useWaterSum", useWaterSum);
		mav.addObject("gzqUseWaterSum", gzqUseWaterSum);
		mav.addObject("sjzUseWaterSum", sjzUseWaterSum);
		mav.addObject("myxUseWaterSum", myxUseWaterSum);
		mav.addObject("wjzUseWaterSum", wjzUseWaterSum);
		// mav.addObject("totalSum", totalSum);
		mav.addObject("useWaterMonthList", new Gson().toJson(useWaterMonthList));
		return mav;
	}
	
	/**
	 * @Title: getSum
	 * @Description: 获取用水量方法
	 * @param getTotalWaterList
	 * @param areaId
	 * @param sysAllAreaList
	 * @return: double
	 * @Author: zhaojx
	 * @Date: 2016年11月28日
	 */
	@SuppressWarnings("null")
	private double getSum(List<RptUseWaterDetail> getTotalWaterList, String areaId, List<SysArea> sysAllAreaList) {
		double sum = 0.0;
		if(getTotalWaterList != null || getTotalWaterList.size() > 0){
			List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, areaId);
			List<String> useWaterList = new ArrayList<String>();
			for(SysArea sa : sysAreaList) {
				useWaterList.add(sa.getId());
			}
			List<RptUseWaterDetail> childrenTotalWaterList = new ArrayList<RptUseWaterDetail>();
			for(int i=0;i<useWaterList.size();i++) {
				for(int j=0;j<getTotalWaterList.size();j++) {
					if(useWaterList.get(i).equals(getTotalWaterList.get(j).getAreaId())) {
						childrenTotalWaterList.add(getTotalWaterList.get(j));
					}
				}
			}
			
			List<BigDecimal> useWaterSumList = new ArrayList<BigDecimal>();
			for(int i=0;i<childrenTotalWaterList.size();i++) {
				if(i!=childrenTotalWaterList.size()-1) {
					if(childrenTotalWaterList.get(i).getDeviceCode().equals(childrenTotalWaterList.get(i+1).getDeviceCode())){
						if(childrenTotalWaterList.get(i).getUpTime().toString().equals(childrenTotalWaterList.get(i+1).getUpTime().toString())) {
							if(childrenTotalWaterList.get(i).getRemainWater().compareTo(childrenTotalWaterList.get(i+1).getRemainWater())==1) {
								useWaterSumList.add(childrenTotalWaterList.get(i).getRemainWater().subtract(childrenTotalWaterList.get(i+1).getRemainWater()));
							}
						}
					} else {
						useWaterSumList.add(childrenTotalWaterList.get(i).getUseWater());
					}
				} else {
					useWaterSumList.add(childrenTotalWaterList.get(i).getUseWater());
				}
			}
			for(int i=0;i<useWaterSumList.size();i++) {
				sum += useWaterSumList.get(i).doubleValue();
			}
		}
		return sum;
	}
	
	/**
	 * @Title: getUseWaterByMonth
	 * @Description: 根据月份获取用水量
	 * @param getTotalWaterList
	 * @return: List<String>
	 * @Author: zhaojx
	 * @Date: 2016年11月28日
	 */
	private List<String> getUseWaterByMonth(List<RptUseWaterDetail> getTotalWaterList) {
		List<String> useWaterMonthList = new ArrayList<String>();
		for(Integer i=1;i<13;i++) {
			List<RptUseWaterDetail> totalList = new ArrayList<RptUseWaterDetail>();
			for(int j=0;j<getTotalWaterList.size();j++) {
				if(i.equals(getTotalWaterList.get(j).getInMonth())) {
					totalList.add(getTotalWaterList.get(j));
				}
			}
			
			List<BigDecimal> useWaterSumList = new ArrayList<BigDecimal>();
			for(int k=0;k<totalList.size();k++) {
				if(k!=totalList.size()-1) {
					if(totalList.get(k).getDeviceCode().equals(totalList.get(k+1).getDeviceCode())){
						if(totalList.get(k).getUpTime().toString().equals(totalList.get(k+1).getUpTime().toString())) {
							if(totalList.get(k).getRemainWater().compareTo(totalList.get(k+1).getRemainWater())==1) {
								useWaterSumList.add(totalList.get(k).getRemainWater().subtract(totalList.get(k+1).getRemainWater()));
							}
						}
					} else {
						useWaterSumList.add(totalList.get(k).getUseWater());
					}
				} else {
					useWaterSumList.add(totalList.get(k).getUseWater());
				}
			}
			Double sum = 0.0;
			for(int x=0;x<useWaterSumList.size();x++) {
				sum += useWaterSumList.get(x).doubleValue();
			}
			useWaterMonthList.add(sum.toString());
		}
		return useWaterMonthList;
	}
	
}