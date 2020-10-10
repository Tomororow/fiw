package com.fourfaith.waterRightManage.dao;

import com.fourfaith.waterRightManage.model.BaseDistAppendWaterPlan;

/**
 * @ClassName: BaseDistAppendWaterPlanMapper
 * @Description: 阶梯性水量追加dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:50:16
 */
public interface BaseDistAppendWaterPlanMapper {

	void insertSelective(BaseDistAppendWaterPlan bdawp);

}