package com.fourfaith.sysManage.service.impl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.BaseWaterPriceMapper;
import com.fourfaith.sysManage.model.BaseWaterPrice;
import com.fourfaith.sysManage.service.BaseWaterPriceService;

/**
 * @ClassName: BaseWaterPriceServiceImpl
 * @Description: 基础水价service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:39:08
 */
@Service("BaseWaterPriceService")
public class BaseWaterPriceServiceImpl implements BaseWaterPriceService {
	
	protected Logger logger = Logger.getLogger(BaseWaterPriceServiceImpl.class);
	
	@Autowired
	private BaseWaterPriceMapper baseWaterPriceMapper;
	
	@Override
	public BaseWaterPrice findByData() {
		return baseWaterPriceMapper.selectByPrimaryKey();
	}
	
	@Override
	public String add(BaseWaterPrice model) {
		String msg = null;
		int result = baseWaterPriceMapper.insertPrice(model);
		if(result>0){
			msg = "添加成功";
		}else{
		    msg = "添加失败";
		}
		logger.info(msg);
		return msg;
	}
	
	@Override
	public String update(BaseWaterPrice model) {
		String msg = null;
		try {
			int result = baseWaterPriceMapper.updateByPrimaryKeySelective(model);
			if(result>0){
			   msg = "更新成功";
			}else{
			  msg = "更新失败";
			}
			logger.info(msg);			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "更新失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}
	
}