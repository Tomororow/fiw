package com.fourfaith.paramterManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.paramterManage.dao.ParamWaterSourcePriceMapper;
import com.fourfaith.paramterManage.model.ParamWaterSourcePrice;
import com.fourfaith.paramterManage.service.ParamWaterSourcePriceService;
import com.fourfaith.utils.AjaxJson;

import common.Logger;

/**
 * @ClassName: ParamWaterSourcePriceServiceImpl
 * @Description: 水资源费信息service实现
 * @Author: zhaojx
 * @Date: 2017年5月13日 下午6:12:59
 */
@Service("ParamWaterSourcePriceService")
public class ParamWaterSourcePriceServiceImpl implements ParamWaterSourcePriceService{
	
	protected Logger logger = Logger.getLogger(ParamWaterSourcePriceServiceImpl.class);

	@Autowired
	private ParamWaterSourcePriceMapper paramWaterSourcePriceMapper;

	/**
	 * 统计信息数量
	 */
	@Override
	public Integer getCount(Map<String, Object> params) {
		return paramWaterSourcePriceMapper.getCount(params);
	}

	/**
	 * 获取信息列表
	 */
	@Override
	public List<ParamWaterSourcePrice> getList(Map<String, Object> params) {
		return paramWaterSourcePriceMapper.getList(params);
	}

	/**
	 * 新增水资源费信息
	 * @param paramWaterSourcePrice
	 * 2016年12月5日
	 */
	public String add(ParamWaterSourcePrice paramWaterSourcePrice) {
		String msg = null;
		try {
			int result = paramWaterSourcePriceMapper.add(paramWaterSourcePrice);
			if(result>0) 
				msg = "添加成功";
			else 
				msg = "添加失败";
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	/**
	 * 根据Id删除水资源费信息
	 * @param ids
	 */
	public AjaxJson delete(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(ids!=null){
			String[] areaIdArray = ids.split(",");
			for (int i = 0; i < areaIdArray.length; i++) {
				logContent = logContent+"删除【水资源费】的数据"+",";
				paramWaterSourcePriceMapper.delete(areaIdArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}

	/**
	 * 获取所有水资源费信息
	 */
	public ParamWaterSourcePrice getAllList(Map<String, Object> params) {
		return paramWaterSourcePriceMapper.getAllList(params);
	}

	/**
	 * 根据id获取水资源费率信息
	 */
	@Override
	public ParamWaterSourcePrice findWaterSourcePriceById(String id) {
		return paramWaterSourcePriceMapper.getWaterSourcePriceById(id);
	}

	/**
	 * 修改水资源费率信息
	 */
	@Override
	public String editWaterSourcePrice(ParamWaterSourcePrice paramWaterSourcePrice) {
		String msg = null;
		try {
			int result = paramWaterSourcePriceMapper.editWaterSourcePrice(paramWaterSourcePrice);
			if(result>0) 
				msg = "修改成功";
			else 
				msg = "修改失败";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
		}
		return msg;
	}
	
}