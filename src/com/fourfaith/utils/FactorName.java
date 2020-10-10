package com.fourfaith.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 要素名称枚举
 * @author administrator
 */
public enum FactorName {
	
	water("水位", "water", "m"), flowratepers("每秒流量", "flowratepers", "m3/s"), flowrateperh(
			"每小时流量", "flowrateperh", "m3/h"), flowrateadd("累计流量",
			"flowrateadd", "m3"), voltage("电源电压", "voltage", "V"), signalinten(
			"信号强度", "signalinten", "");

	private String name;

	/** 报送类型标识 */
	private String flag;

	private String unit;

	private FactorName(String name, String flag, String unit) {
		this.name = name;
		this.flag = flag;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * 获取flag map flag:name
	 * @return
	 */
	public static Map<String, String> getFactorMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (FactorName e : FactorName.values()) {
			map.put(e.flag, e.name);
		}
		return map;
	}

	/**
	 * 获取flag map unit
	 * @return
	 */
	public static Map<String, String> getFactorUnitMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (FactorName e : FactorName.values()) {
			map.put(e.flag, e.unit);
		}
		return map;
	}

}