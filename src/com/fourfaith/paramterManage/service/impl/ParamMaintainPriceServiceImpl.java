package com.fourfaith.paramterManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.paramterManage.dao.ParamMaintainPriceMapper;
import com.fourfaith.paramterManage.model.ParamMaintainPrice;
import com.fourfaith.paramterManage.service.ParamMaintainPriceService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: ParamMaintainPriceServiceImpl
 * @Description: 末级渠系(维修养护)费率service实现
 * @Author: zhaojinxin
 * @Date: 2018年9月5日 下午6:01:55
 */
@Service("ParamMaintainPriceService")
public class ParamMaintainPriceServiceImpl implements ParamMaintainPriceService{
	
	@Autowired
	private ParamMaintainPriceMapper paramMaintainPriceMapper;
	
	/**
	 * @Title: getMaintainPriceCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	@Override
	public Integer getMaintainPriceCount(Map<String, Object> params) {
		return paramMaintainPriceMapper.getMaintainPriceCount(params);
	}

	/**
	 * @Title: getMaintainPriceList
	 * @Description: 获取信息列表
	 * @param: @param params
	 * @return: List<ParamMaintainPrice>
	 */
	@Override
	public List<ParamMaintainPrice> getMaintainPriceList(Map<String, Object> params) {
		return paramMaintainPriceMapper.getMaintainPriceList(params);
	}

	/**
	 * @Title: insertMaintainPrice
	 * @Description: 新增费率信息
	 * @param: @param paramMaintainPrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@Override
	public String insertMaintainPrice(ParamMaintainPrice paramMaintainPrice) {
		String msg = null;
		try {
			int result = paramMaintainPriceMapper.insertMaintainPrice(paramMaintainPrice);
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
	public AjaxJson delMaintainPrice(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		if(ids != null){
			String[] areaIdArray = ids.split(",");
			for (int i = 0; i < areaIdArray.length; i++) {
				paramMaintainPriceMapper.delMaintainPrice(areaIdArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj("删除末级渠系费率信息成功");
		}
		return ajaxJson;
	}

	/**
	 * 根据ID查询末级渠系费率信息
	 */
	@Override
	public ParamMaintainPrice findMaintainPriceById(String id) {
		return paramMaintainPriceMapper.getMaintainPriceById(id);
	}

	/**
	 * 修改末级渠系费率信息
	 */
	@Override
	public String editMaintainPrice(ParamMaintainPrice paramMaintainPrice) {
		String msg = null;
		try {
			int result = paramMaintainPriceMapper.editMaintainPrice(paramMaintainPrice);
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
	 * 根据年份查询费率信息
	 */
	@Override
	public ParamMaintainPrice findMaintainPriceByYear(Integer inYear) {
		return paramMaintainPriceMapper.getMaintainPriceByYear(inYear);
	}

}