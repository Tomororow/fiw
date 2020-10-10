package com.fourfaith.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fourfaith.statisticAnalysis.model.RptUseWaterDetail;
import com.fourfaith.statisticAnalysis.model.RptUseWaterDetailVO;
import com.fourfaith.sysManage.model.BaseDeviceInfo;

/**
 * @ClassName: UseWaterTotal
 * @Description: 水量计算工具类
 * @Author: zhaojx
 * @Date: 2018年4月12日 下午4:01:06
 */
public class UseWaterTotal {
	
	/**
	 * @Title: historyThisUseWater
	 * @Description: 实时数据-历史数据查询  查询结果重组  计算本次用水量并放入集合
	 * @param: @param dataHistoryList
	 * @param: @return
	 * @return: List<BaseDeviceInfo>
	 */
	public static List<BaseDeviceInfo> historyThisUseWater(List<BaseDeviceInfo> dataHistoryList){
		double useWaterResult = 0;
		try {
			if(dataHistoryList != null && dataHistoryList.size() > 0){
				// 循环遍历获取并计算当前用水量
				for (int i = 0; i < dataHistoryList.size(); i++) {
					// 本次水量
					double thisUseWater = dataHistoryList.get(i).getUseWt().doubleValue();
					if(i != dataHistoryList.size()-1){
						// 判断当前条和下一条开泵时间是否相同  为同一条数据  并判断用水量是否大于下一条
						if(dataHistoryList.get(i).getUpTime().compareTo(dataHistoryList.get(i+1).getUpTime()) == 0
							&& dataHistoryList.get(i).getUseWt().compareTo(dataHistoryList.get(i+1).getUseWt()) == 1){
							useWaterResult = thisUseWater - dataHistoryList.get(i+1).getUseWt().doubleValue();
						}else if(dataHistoryList.get(i).getUpTime().compareTo(dataHistoryList.get(i+1).getUpTime()) == 0
							&& dataHistoryList.get(i).getUseWt().compareTo(dataHistoryList.get(i+1).getUseWt()) == 0){
							useWaterResult = 0.0;
						}else if(!dataHistoryList.get(i).getDeviceCode().equals(dataHistoryList.get(i+1).getDeviceCode())){
							useWaterResult = 0.0;
						}else{
							useWaterResult = thisUseWater;
						}
					}else{
						useWaterResult = thisUseWater;
					}
					
					if(useWaterResult != 0){
						dataHistoryList.get(i).setUseWaterResult(useWaterResult);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataHistoryList;
	}

	/**
	 * @Title: getThisUseWater
	 * @Description: 导出Excel用水信息  查询结果重组  计算本次用水量并放入集合
	 * @param: @param useWaterList
	 * @param: @return
	 * @return: List<RptUseWaterDetailVO>
	 */
	public static List<RptUseWaterDetailVO> getExcelThisUseWater(List<RptUseWaterDetailVO> useWaterList){
		// 本次用水量
		double useWaterResult = 0;
		if(useWaterList != null && useWaterList.size() > 0){
			// 循环遍历获取并计算当前用水量
			for (int i = 0; i < useWaterList.size(); i++) {
				// 本次水量
				double thisUseWater = useWaterList.get(i).getUseWater().doubleValue();
				if(i != useWaterList.size()-1){
					// 判断当前条和下一条开泵时间是否相同  为同一条数据  并判断用水量是否大于下一条
					if(useWaterList.get(i).getDeviceCode().equals(useWaterList.get(i+1).getDeviceCode())
						&& useWaterList.get(i).getOpenPumpTime().compareTo(useWaterList.get(i+1).getOpenPumpTime()) == 0
						&& useWaterList.get(i).getUseWater().compareTo(useWaterList.get(i+1).getUseWater()) == 1){
						useWaterResult = thisUseWater - useWaterList.get(i+1).getUseWater().doubleValue();
					}else if(useWaterList.get(i).getDeviceCode().equals(useWaterList.get(i+1).getDeviceCode())
						&& useWaterList.get(i).getOpenPumpTime().compareTo(useWaterList.get(i+1).getOpenPumpTime()) == 0
						&& useWaterList.get(i).getUseWater().compareTo(useWaterList.get(i+1).getUseWater()) == 0){
						useWaterResult = 0.0;
					}else if(!useWaterList.get(i).getDeviceCode().equals(useWaterList.get(i+1).getDeviceCode())){
						useWaterResult = 0.0;
					}else{
						useWaterResult = thisUseWater;
					}
				}else{
					useWaterResult = thisUseWater;
				}
				
				if(useWaterResult != 0){
					useWaterList.get(i).setUseWaterResult(useWaterResult);
				}
			}
		}
		return useWaterList;
	}
	
	/**
	 * @Title: getThisUseWater
	 * @Description: 用水信息统计列表  查询结果重组  计算本次用水量并放入集合
	 * @param: @param useWaterList
	 * @param: @return
	 * @return: List<RptUseWaterDetail>
	 */
	public static List<RptUseWaterDetail> getThisUseWater(List<RptUseWaterDetail> useWaterList){
		// 本次用水量
		double useWaterResult = 0;
		if(useWaterList != null && useWaterList.size() > 0){
			// 循环遍历获取并计算当前用水量 event_scheduler = 1
			for (int i = 0; i < useWaterList.size(); i++) {
				// 本次水量
				double thisUseWater = useWaterList.get(i).getUseWater().doubleValue();
				if(i != useWaterList.size()-1){
					// 判断当前条和下一条开泵时间是否相同  为同一条数据  并判断用水量是否大于下一条
					if(useWaterList.get(i).getDeviceCode().equals(useWaterList.get(i+1).getDeviceCode())
						&& useWaterList.get(i).getUpTime().compareTo(useWaterList.get(i+1).getUpTime()) == 0
						&& useWaterList.get(i).getUseWater().compareTo(useWaterList.get(i+1).getUseWater()) == 1){
						useWaterResult = thisUseWater - useWaterList.get(i+1).getUseWater().doubleValue();
					}else if(useWaterList.get(i).getDeviceCode().equals(useWaterList.get(i+1).getDeviceCode())
						&& useWaterList.get(i).getUpTime().compareTo(useWaterList.get(i+1).getUpTime()) == 0
						&& useWaterList.get(i).getUseWater().compareTo(useWaterList.get(i+1).getUseWater()) == 0){
						useWaterResult = 0.0;
					}else if(!useWaterList.get(i).getDeviceCode().equals(useWaterList.get(i+1).getDeviceCode())){
						useWaterResult = 0.0;
					}else{
						useWaterResult = thisUseWater;
					}
				}else{
					useWaterResult = 0.0;
				}
				
				if(useWaterResult != 0){
					useWaterList.get(i).setUseWaterResult(useWaterResult);
				}
			}
		}
		return useWaterList;
	}
	
	/**
	 * @Title: getUseWaterSum
	 * @Description: 用水信息列表  计算总用水量
	 * @param: @param getTotalWaterList
	 * @param: @return
	 * @return: double
	 */
	public static double getUseWaterSum(List<RptUseWaterDetail> getTotalWaterList){
		// 总用水量集合
		List<BigDecimal> useWaterSumList = new ArrayList<BigDecimal>();
		for(int i=0; i<getTotalWaterList.size(); i++) {
			// 判断数据是否为最后一条
			if(i!=getTotalWaterList.size()-1) {
				// 设备编码  卡号  开泵时间 是否相等
				if(getTotalWaterList.get(i).getDeviceCode().equals(getTotalWaterList.get(i+1).getDeviceCode())
						&& getTotalWaterList.get(i).getCardCode().equals(getTotalWaterList.get(i+1).getCardCode())
						&& getTotalWaterList.get(i).getUpTime().compareTo(getTotalWaterList.get(i+1).getUpTime()) == 0) {
					// 当前一条大于下一条用水量
					if(getTotalWaterList.get(i).getUseWater().compareTo(getTotalWaterList.get(i+1).getUseWater())==1) {
						useWaterSumList.add(getTotalWaterList.get(i).getUseWater().subtract(getTotalWaterList.get(i+1).getUseWater()));
					}
				}else{
					useWaterSumList.add(getTotalWaterList.get(i).getUseWater());
				}
			// 如果是最后一条用水数据  则添加当条的UseWater
			} else {
				useWaterSumList.add(getTotalWaterList.get(getTotalWaterList.size()-1).getUseWater());
			}
		}
		// 循环获取总用水量集合并累加  得到最终用水量
		double useWaterSum = 0.0;
		for(int i=0; i<useWaterSumList.size(); i++) {
			useWaterSum += useWaterSumList.get(i).doubleValue();
		}
		return useWaterSum;
	}
	
}