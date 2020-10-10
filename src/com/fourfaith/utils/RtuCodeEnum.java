package com.fourfaith.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RtuCodeEnum
 * @Description: RtuCode工具类
 */
public enum RtuCodeEnum {

	UpdateConfig("修改配置", "40"),
	RtuUpgrade("固件升级", "E1"),
	RtuRestart("RTU重启", "E2"),
	// RtuPhoto("人工抓拍", "81"),
	RtuAlarm("报警设定", "42");

	/**
	 * 构造函数
	 * @param text
	 * @param value
	 */
	RtuCodeEnum(String text, String value) {
		this.value = value;
		this.text = text;
	}

	/**
	 * 枚举类型要展示的名称
	 */
	private String text;
	
	/**
	 * 枚举类型真实值
	 */
	private String value;

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}

	/**
	 * 获取map数据源
	 */
	public static Map<String, String> getMapSource() {
		Map<String, String> source = new HashMap<String, String>();
		for (RtuCodeEnum r : RtuCodeEnum.values()) {
			source.put(r.getValue(), r.getText());
		}
		return source;
	}
	
}