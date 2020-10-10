package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.BaseWaterPrice;

/**
 * @ClassName: BaseWaterPriceService
 * @Description: 基础水价service接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:27:58
 */
public interface BaseWaterPriceService{

	BaseWaterPrice findByData();
	
	String add(BaseWaterPrice model);
	
	String update(BaseWaterPrice model);
	
}