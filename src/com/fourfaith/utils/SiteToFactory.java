package com.fourfaith.utils;

/**
 * 站点对应下的要素
 * @author administrator
 */
public enum SiteToFactory {
	
	rain("20", "当前降水量"), water("39", "水位");

	/**
	 * 要素标识符
	 */
	private String code;

	/**
	 * 要素名称
	 */
	private String factoryName;

	/**
	 * 构造函数
	 * @param text
	 * @param value
	 */
	SiteToFactory(String code, String factoryName) {
		this.code = code;
		this.factoryName = factoryName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
}