package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.BaseWaterPrice;

/**
 * @ClassName: BaseWaterPriceMapper
 * @Description: 基础水价dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:08:24
 */
public interface BaseWaterPriceMapper {

	int insertPrice(BaseWaterPrice price);

	BaseWaterPrice selectByPrimaryKey();

	int updateByPrimaryKeySelective(BaseWaterPrice price);

}