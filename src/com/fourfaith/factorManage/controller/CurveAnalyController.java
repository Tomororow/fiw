package com.fourfaith.factorManage.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.factorManage.model.IraFAllDetails;
import com.fourfaith.factorManage.service.IraFAllDetailsService;
import com.fourfaith.utils.PersonDateUtils;
import com.fourfaith.utils.EChart;
import com.fourfaith.utils.FactorName;

/**   
 * @Title: Controller
 * @Description: 曲线分析
 * @author administrator
 * @date 2016-06-14 17:30:31
 * @version V1.0   
 */
@Controller
@RequestMapping(value ="/curveAnaly")
public class CurveAnalyController {

	protected static final String indexJsp="/page/statistic/curveAnaly/index";
	protected static final String curveJsp="/page/statistic/curveAnaly/curve";
	
	@Autowired
	private IraFAllDetailsService iraFAllDetailsService;

	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(indexJsp);
		mav.addObject("defaultTime", new Date());
		mav.addObject("factorMap", FactorName.getFactorMap());
		mav.addObject("factorUnitMap", FactorName.getFactorUnitMap());
		return mav;
	}
	
	/**
	 * 获取报表数据
	 * @param request
	 * @param nodeIds
	 * @param searchType
	 * @param factorType
	 */
	@RequestMapping(value = "/getChartsData")
	@ResponseBody
	public String getChartsData(HttpServletRequest request,String nodeIds, String searchType, String factorType){
		EChart chart = new EChart();
		Map<String, Object> params = new HashMap<String, Object>();
		//设置查询参数
		setParams(params,nodeIds,searchType,request);
		if("1".equals(searchType)) params.put("endTime", PersonDateUtils.add((Date)params.get("endTime"),Calendar.MINUTE,59));
		if(FactorName.flowrateadd.getFlag().equals(factorType) && !"0".equals(searchType)){
			//params.put("endTime", DateUtils.add((Date)params.get("endTime"), Calendar.MINUTE, -1));
			//累加流量
			Map<String, Double> listMap = new HashMap<String, Double>();
			List<Object> valueList = new ArrayList<>();
			//横坐标
			List<String> categories = getCategories((Date)params.get("beginTime"),(Date)params.get("endTime"),searchType);
			
			List<IraFAllDetails> list = new ArrayList<>();
			
			String label = null;
			int CalType = Calendar.HOUR_OF_DAY;
			if("1".equals(searchType) || "2".equals(searchType)){
				//时段数据（小时平均值）
				params.put("groupByCon", "DATE_FORMAT(TM, '%Y-%m-%d %H')");
				
			}else if("3".equals(searchType)){
				//月时段数据（日平均值）
				label="号";
				CalType = Calendar.DATE;
				params.put("groupByCon", "DAYOFMONTH (TM)");
			}else if("4".equals(searchType)){
				//年时段数据（月平均值）
				label="月";
				CalType = Calendar.MONTH;
				params.put("groupByCon", "MONTH (TM)");
			}
			list = this.iraFAllDetailsService.getMinAddList(params);
			
			if(list!=null && list.size()!=0){
				for (int i = 0; i < list.size(); i++) {
					IraFAllDetails model = list.get(i);
					
					if(model.getFlowrateaddmin()!=null){
						Double value = model.getFlowrateaddmin()!=null?model.getFlowrateaddmin().doubleValue():null;
						if(label==null){
							listMap.put(PersonDateUtils.DateToString(model.getTm(), "HH时\nyyyy-MM-dd"), value);
						}else{
							if("4".equals(searchType)){
								listMap.put((PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+1)+label, value);
							}else{
								listMap.put(PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+label, value);
							}
						}
					}
				}
			}
			
			if(listMap.size()>0){
				Double lastValue = null;
				for (int i = 0; i < categories.size(); i++) {
					String key = categories.get(i);
					if(listMap.containsKey(key)){
						if(lastValue!=null)
							valueList.add(new BigDecimal(listMap.get(key).toString()).subtract( new BigDecimal(lastValue.toString())).doubleValue());
						lastValue=listMap.get(key);
					}else{
						lastValue = null;
						valueList.add("-");
					}
				}
			}
			chart.setDataList(valueList);
			Map<String,Object> xAxi = new HashMap<>();
			xAxi.put("data", categories);
			Map<String,Object> axisTick = new HashMap<>();
			axisTick.put("show", false);
			xAxi.put("axisTick", axisTick);
			xAxi.put("boundaryGap", false);
			chart.setxAxi(xAxi);
		}else{
			//其余要素 平均值
			if("0".equals(searchType)){
				//原始数据
				List<IraFAllDetails> detList = this.iraFAllDetailsService.getList(params);
				chart.setDataList(detList);
				
				Map<String,Object> xAxi = new HashMap<>();
				Map<String,Object> axisTick = new HashMap<>();
				axisTick.put("show", false);
				xAxi.put("axisTick", axisTick);
				
				xAxi.put("type", "time");
				xAxi.put("splitNumber", 5);
				xAxi.put("boundaryGap", new Double[]{0.01,0.01});
				chart.setxAxi(xAxi);
			}else{
				Map<String, Double> listMap = new HashMap<String, Double>();
				List<Object> valueList = new ArrayList<>();
				//横坐标
				List<String> categories = getCategories((Date)params.get("beginTime"),(Date)params.get("endTime"),searchType);
				
				List<IraFAllDetails> avgList = new ArrayList<>();
				
				String label = null;
				int CalType = Calendar.HOUR_OF_DAY;
				if("1".equals(searchType) || "2".equals(searchType)){
					//时段数据（小时平均值）
					params.put("groupByCon", "DATE_FORMAT(TM, '%Y-%m-%d %H')");
					avgList = this.iraFAllDetailsService.getAvgList(params);
				}else if("3".equals(searchType)){
					//月时段数据（日平均值）
					label="号";
					CalType = Calendar.DATE;
					params.put("groupByCon", "DAYOFMONTH (TM)");
					avgList = this.iraFAllDetailsService.getAvgList(params);
				}else if("4".equals(searchType)){
					//年时段数据（月平均值）
					label="月";
					CalType = Calendar.MONTH;
					params.put("groupByCon", "MONTH (TM)");
					avgList = this.iraFAllDetailsService.getAvgList(params);
				}
				
				if(avgList!=null && avgList.size()!=0){
					for (int i = 0; i < avgList.size(); i++) {
						IraFAllDetails model = avgList.get(i);
						switch (factorType) {
							case "water":
								if(model.getWateravg()!=null){
									Double value = model.getWateravg()!=null?model.getWateravg().doubleValue():null;
									if(label==null){
										listMap.put(PersonDateUtils.DateToString(model.getTm(), "HH时\nyyyy-MM-dd"), value);
									}else{
										if("4".equals(searchType)){
											listMap.put((PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+1)+label, value);
										}else{
											listMap.put(PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+label, value);
										}
									}
								}
								break;
							case "flowratepers":
								if(model.getFlowratepersavg()!=null){
									Double value = model.getFlowratepersavg()!=null?model.getFlowratepersavg().doubleValue():null;
									if(label==null){
										listMap.put(PersonDateUtils.DateToString(model.getTm(), "HH时\nyyyy-MM-dd"), value);
									}else{
										if("4".equals(searchType)){
											listMap.put((PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+1)+label, value);
										}else{
											listMap.put(PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+label, value);
										}
									}
								}
								break;
							case "flowrateperh":
								if(model.getFlowrateperhavg()!=null){
									Double value = model.getFlowrateperhavg()!=null?model.getFlowrateperhavg().doubleValue():null;
									if(label==null){
										listMap.put(PersonDateUtils.DateToString(model.getTm(), "HH时\nyyyy-MM-dd"), value);
									}else{
										if("4".equals(searchType)){
											listMap.put((PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+1)+label, value);
										}else{
											listMap.put(PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+label, value);
										}
									}
								}
								break;
							case "voltage":
								if(model.getVoltageavg()!=null){
									Double value = model.getVoltageavg()!=null?model.getVoltageavg().doubleValue():null;
									if(label==null){
										listMap.put(PersonDateUtils.DateToString(model.getTm(), "HH时\nyyyy-MM-dd"), value);
									}else{
										if("4".equals(searchType)){
											listMap.put((PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+1)+label, value);
										}else{
											listMap.put(PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+label, value);
										}
									}
								}
								break;
							case "signalinten":
								if(model.getSignalintenavg()!=null){
									Double value = model.getSignalintenavg()!=null?model.getSignalintenavg().doubleValue():null;
									if(label==null){
										listMap.put(PersonDateUtils.DateToString(model.getTm(), "HH时\nyyyy-MM-dd"), value);
									}else{
										if("4".equals(searchType)){
											listMap.put((PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+1)+label, value);
										}else{
											listMap.put(PersonDateUtils.DateToCalendar(model.getTm()).get(CalType)+label, value);
										}
									}
								}
								break;
							default:
								break;
						}
					}
				}
				if(listMap.size()>0){
					for (int i = 0; i < categories.size(); i++) {
						String key = categories.get(i);
						if(listMap.containsKey(key)){
							valueList.add(listMap.get(key));
						}else{
							valueList.add("-");
						}
					}
				}
				chart.setDataList(valueList);
				Map<String,Object> xAxi = new HashMap<>();
				xAxi.put("data", categories);
				Map<String,Object> axisTick = new HashMap<>();
				axisTick.put("show", false);
				xAxi.put("axisTick", axisTick);
				xAxi.put("boundaryGap", false);
				chart.setxAxi(xAxi);
			}
		}
		return JSONObject.toJSONString(chart);
	}

	private void setParams(Map<String, Object> params, String nodeIds, String searchType, HttpServletRequest request) {
		Date beginTime = null;
		Date endTime = null;
		if("0".equals(searchType)||"1".equals(searchType)){
			//时段
			String query_beginTime = request.getParameter("query_beginTime");
			String query_endTime = request.getParameter("query_endTime");
			beginTime = PersonDateUtils.StringToDate(query_beginTime, "yyyy-MM-dd HH:mm");
			endTime = PersonDateUtils.StringToDate(query_endTime, "yyyy-MM-dd HH:mm");
		}else{
			
			String query_Time = request.getParameter("query_Time");
			if("2".equals(searchType)){
				//日
				String pattern = "yyyy-MM-dd";
				Date date = PersonDateUtils.StringToDate(query_Time, pattern);
				beginTime = PersonDateUtils.setMinTimeForDate(date);
				endTime = PersonDateUtils.setMaxTimeForDate(date);
			}else if("3".equals(searchType)){
				//月
				String pattern = "yyyy-MM";
				Date date = PersonDateUtils.StringToDate(query_Time, pattern);
				beginTime = PersonDateUtils.setMinTimeForMonthDate(date);
				endTime = PersonDateUtils.setMaxTimeForMonthDate(date);
			}else if("4".equals(searchType)){
				//年
				String pattern = "yyyy";
				Date date = PersonDateUtils.StringToDate(query_Time, pattern);
				beginTime = PersonDateUtils.setMinTimeForYearDate(date);
				endTime = PersonDateUtils.setMaxTimeForYearDate(date);
			}
		}
		
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		
		String stcd = null;
		if(StringUtils.isNotBlank(nodeIds)){
			stcd = nodeIds.split(",")[0];
		}else{
			stcd = "";
		}
		params.put("stcd", stcd);
		params.put("orderCon", "TM ASC");
	}
	
	/**
	 * 根据开始时间结束时间生成横坐标
	 * @param beginTime
	 * @param endTime
	 * @param searchType 
	 */
	private List<String> getCategories(Date beginTime, Date endTime, String searchType) {
		List<String> categories = new ArrayList<String>();
		int CalType = Calendar.HOUR_OF_DAY;
		String label = "时";
		if (searchType.equals("1") || searchType.equals("2") || PersonDateUtils.in24Hours(beginTime, endTime)){
			Date tmp = beginTime;
			while (!tmp.after(endTime)) {
				categories.add(PersonDateUtils.DateToString(tmp, "HH时\nyyyy-MM-dd"));
				tmp = PersonDateUtils.add(tmp, CalType, 1);
			}
		}else {
			if (searchType.equals("3") || PersonDateUtils.in1Month(beginTime, endTime)){
				CalType = Calendar.DATE;
				label = "号";
				Date tmp = beginTime;
				while (!tmp.after(endTime)) {
					categories.add(PersonDateUtils.DateToCalendar(tmp).get(CalType)+label);
					tmp = PersonDateUtils.add(tmp, CalType, 1);
				}
			} else if(searchType.equals("4")){
				//年报 一个月一段
				CalType = Calendar.MONTH;
				label = "月";
				Date tmp = beginTime;
				while (!tmp.after(endTime)) {
					categories.add((PersonDateUtils.DateToCalendar(tmp).get(CalType)+1)+label);
					tmp = PersonDateUtils.add(tmp, CalType, 1);
				}
			}
		}
		return categories;
	}

}