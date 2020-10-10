package com.fourfaith.paramterManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.paramterManage.dao.ParamBaseWaterPriceMapper;
import com.fourfaith.paramterManage.model.ParamBaseWaterPrice;
import com.fourfaith.paramterManage.service.ParamBaseWaterPriceService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: BaseWaterPriceChargeServiceImpl
 * @Description: 基本水费收缴实现类
 * @Author: zhaojx
 * @Date: 2018年7月31日 下午6:00:49
 */
@Service("ParamBaseWaterPriceService")
public class ParamBaseWaterPriceServiceImpl implements ParamBaseWaterPriceService{
	
	@Autowired
	private ParamBaseWaterPriceMapper paramBaseWaterPriceMapper;
	
	/**
	 * @Title: getBaseWaterCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	@Override
	public Integer getBaseWaterCount(Map<String, Object> params) {
		return paramBaseWaterPriceMapper.getBaseWaterCount(params);
	}

	/**
	 * @Title: getBaseWaterList
	 * @Description: 获取信息列表
	 * @param: @param params
	 * @return: List<ParamBaseWaterPrice>
	 */
	@Override
	public List<ParamBaseWaterPrice> getBaseWaterList(Map<String, Object> params) {
		return paramBaseWaterPriceMapper.getBaseWaterList(params);
	}

	/**
	 * @Title: insertBaseWater
	 * @Description: 新增费率信息
	 * @param: @param paramBaseWaterPrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@Override
	public String insertBaseWater(ParamBaseWaterPrice paramBaseWaterPrice) {
		String msg = null;
		try {
			int result = paramBaseWaterPriceMapper.insertBaseWater(paramBaseWaterPrice);
			if(result>0){
				msg = "添加成功";
			}else{ 
				msg = "添加失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	/**
	 * 删除费率信息
	 */
	@Override
	public AjaxJson delBaseWater(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		if(ids != null){
			String[] areaIdArray = ids.split(",");
			for (int i = 0; i < areaIdArray.length; i++) {
				paramBaseWaterPriceMapper.deleteBaseWater(areaIdArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj("删除基本水费费率信息成功");
		}
		return ajaxJson;
	}

	/**
	 * 根据ID查询基本水费费率信息
	 */
	@Override
	public ParamBaseWaterPrice findBaseWaterPriceById(String id) {
		return paramBaseWaterPriceMapper.getBaseWaterPriceById(id);
	}

	/**
	 * 修改基本水费费率信息
	 */
	@Override
	public String editBaseWaterPrice(ParamBaseWaterPrice paramBaseWaterPrice) {
		String msg = null;
		try {
			int result = paramBaseWaterPriceMapper.editBaseWaterPrice(paramBaseWaterPrice);
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
	 * 根据年份获取基本水费费率信息
	 */
	@Override
	public ParamBaseWaterPrice findBaseWaterPriceByYear(Integer inYear) {
		return paramBaseWaterPriceMapper.getBaseWaterPriceByYear(inYear);
	}

}