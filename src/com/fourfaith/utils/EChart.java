package com.fourfaith.utils;

import java.util.List;
import java.util.Map;

/**
 * 统计报表的模型
 */
public class EChart {

	/**
	 * x轴设置
	 */
	private Map<String, Object> xAxi;

	/**
	 * 数据
	 */
	private List<?> dataList;

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public Map<String, Object> getxAxi() {
		return xAxi;
	}

	public void setxAxi(Map<String, Object> xAxi) {
		this.xAxi = xAxi;
	}
	
}