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

/**
 * @ClassName: ThreeRedLineAnalysisController
 * @Description: 三条红线
 * @Author: zhaojx
 * @Date: 2016年11月28日
 */
@Controller
@RequestMapping(value="/threeRedLineAnalysis")
public class ThreeRedLineAnalysisController {

	protected static final String threeRedLineJsp = "/page/history/threeRedLine/threeRedLine";
	protected static final String threeRedLineListJsp = "/page/history/threeRedLine/threeRedLineList";

	@Autowired
	private UseWaterService useWaterService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private SysAreaService sysAreaService;
	
	/**
	 * TODO: 三条红线初始页面加载
	 * @param request
	 * @return ModelAndView
	 * @Date 2016年11月6日
	 * @author: xiaogaoxiang
	 */
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(threeRedLineJsp);
		return mav;
	}
	
	/**
	 * TODO: 三条红线列表
	 * @param request
	 * @return ModelAndView
	 * @Date 2016年11月28日
	 * @author: xiaogaoxiang
	 */
	@RequestMapping(value = "/threeRedLineList", method = RequestMethod.POST)
	public ModelAndView threeRedLineList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(threeRedLineListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		double totalWaterSum = 150000000;
		
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
   		
   		// 用水量集合  提取合适的用水量数据放入一个集合  统一处理
   		List<BigDecimal> useWaterSumList = new ArrayList<BigDecimal>();
		List<RptUseWaterDetail> getTotalWaterList = useWaterService.getTotalWaterList(params);
		for(int i=0; i<getTotalWaterList.size(); i++) {
			// 判断数据是否为最后一条
			if(i!=getTotalWaterList.size()-1) {
				// DeviceCode是否相等
				if(getTotalWaterList.get(i).getDeviceCode().equals(getTotalWaterList.get(i+1).getDeviceCode())) {
					// 开泵时间是否相等
					if(getTotalWaterList.get(i).getUpTime().toString().equals(getTotalWaterList.get(i+1).getUpTime().toString())) {
						// 上一条数据剩余水量大于下一条
						if(getTotalWaterList.get(i).getRemainWater().compareTo(getTotalWaterList.get(i+1).getRemainWater())==1) {
							useWaterSumList.add(getTotalWaterList.get(i).getRemainWater().subtract(getTotalWaterList.get(i+1).getRemainWater()));
						}
					}
				}else{
					useWaterSumList.add(getTotalWaterList.get(i).getUseWater());
				}
			} else {
				useWaterSumList.add(getTotalWaterList.get(i).getUseWater());
			}
		}
		double useWaterSum = 0.0;
		for(int i=0;i<useWaterSumList.size();i++) {
			useWaterSum += useWaterSumList.get(i).doubleValue();
		}
		mav.addObject("useWaterSum", useWaterSum);
		mav.addObject("usedWaterNum", totalWaterSum-useWaterSum);
		return mav;
	}
	
}