package com.fourfaith.waterRightManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.waterRightManage.dao.BaseDistAppendWaterPlanMapper;
import com.fourfaith.waterRightManage.model.BaseDistAppendWaterPlan;
import com.fourfaith.waterRightManage.service.BaseDistAppendWaterPlanService;

import common.Logger;

/**
 * @ClassName: BaseDistAppendWaterPlanServiceImpl
 * @Description: 阶梯性水量追加service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:58:01
 */
@Service("BaseDistAppendWaterPlanService")
public class BaseDistAppendWaterPlanServiceImpl implements BaseDistAppendWaterPlanService{

	protected Logger logger = Logger.getLogger(BaseDistAppendWaterPlanServiceImpl.class);
	
	@Autowired
	private BaseDistAppendWaterPlanMapper baseDistAppendWaterPlanMapper;

	@Override
	public void insert(BaseDistAppendWaterPlan bdawp) {
		baseDistAppendWaterPlanMapper.insertSelective(bdawp);
	}
	
}