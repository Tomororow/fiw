package com.fourfaith.paramterManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.paramterManage.dao.ParamMeasureTypePriceMapper;
import com.fourfaith.paramterManage.model.ParamMeasureTypePrice;
import com.fourfaith.paramterManage.service.ParamMeasureTypePriceService;
import com.fourfaith.utils.AjaxJson;

import common.Logger;

/**
 * @ClassName: ParamMeasureTypePriceServiceImpl
 * @Description: 水费信息service实现
 * @Author: zhaojx
 * @Date: 2017年5月13日 下午6:09:25
 */
@Service("ParamMeasureTypePriceService")
public class ParamMeasureTypePriceServiceImpl implements ParamMeasureTypePriceService{

	protected Logger logger = Logger.getLogger(ParamMeasureTypePriceServiceImpl.class);
	
	@Autowired
	private ParamMeasureTypePriceMapper paramMeasureTypePriceMapper;

	@Override
	public Integer getCount(Map<String, Object> params) {
		return paramMeasureTypePriceMapper.getCount(params);
	}

	@Override
	public List<ParamMeasureTypePrice> getList(Map<String, Object> params) {
		return paramMeasureTypePriceMapper.getList(params);
	}

	/**
	 * 新增计量水费信息
	 * @param paramMeasureTypePrice
	 * 2016年12月7日
	 */
	public String add(ParamMeasureTypePrice paramMeasureTypePrice) {
		String msg = null;
		try {
			int result = paramMeasureTypePriceMapper.add(paramMeasureTypePrice);
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
	 * 根据Id删除计量水费信息
	 * 2016年12月7日
	 */
	public AjaxJson delete(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(ids!=null){
			String[] areaIdArray = ids.split(",");
			for (int i = 0; i < areaIdArray.length; i++) {
				logContent = logContent+"删除【计量水费】的数据"+",";
				paramMeasureTypePriceMapper.delete(areaIdArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}

	/**
	 * 根据distType获取计量水费信息
	 * @param params
	 * 2016年12月28日
	 */
	public ParamMeasureTypePrice getAllList(Map<String, Object> params) {
		return paramMeasureTypePriceMapper.getAllList(params);
	}

	/**
	 * 根据id获取计量水费信息
	 */
	@Override
	public ParamMeasureTypePrice findMeasureTypePriceById(String id) {
		return paramMeasureTypePriceMapper.getMeasureTypePriceById(id);
	}

	/**
	 * 修改计量水费信息
	 */
	@Override
	public String editMeasureTypePrice(ParamMeasureTypePrice paramMeasureTypePrice) {
		String msg = null;
		try {
			int result = paramMeasureTypePriceMapper.editMeasureTypePrice(paramMeasureTypePrice);
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

	/**
	 * 按年份查询计量水费信息
	 */
	@Override
	public ParamMeasureTypePrice findMeasurePriceByYear(Integer inYear) {
		return paramMeasureTypePriceMapper.getMeasurePriceByYear(inYear);
	}
	
}