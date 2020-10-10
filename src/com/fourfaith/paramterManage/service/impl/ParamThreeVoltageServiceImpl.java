package com.fourfaith.paramterManage.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.paramterManage.dao.ParamThreeVoltageMapper;
import com.fourfaith.paramterManage.model.ParamThreeVoltage;
import com.fourfaith.paramterManage.service.ParamThreeVoltageService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: ParamThreeVoltageServiceImpl
 * @Description: 三相电流电压参数service实现
 * @Author: zhaojx
 * @Date: 2017年5月13日 下午6:12:01
 */
@Service("ParamThreeVoltageService")
public class ParamThreeVoltageServiceImpl implements ParamThreeVoltageService{
	
	protected Logger logger = Logger.getLogger(ParamThreeVoltageServiceImpl.class);

	@Autowired
	private ParamThreeVoltageMapper paramThreeVoltageMapper;
	
	/**
	 * 获取三相电流记录条数
	 */
	public Integer getCount() {
		int result = paramThreeVoltageMapper.getCount();
		return result;
	}

	/**
	 * 获取三相电压信息集合
	 * @param params
	 */
	public List<ParamThreeVoltage> getList(Map<String, Object> params) {
		return paramThreeVoltageMapper.getList(params);
	}

	/**
	 * 新增三相电压设置
	 * @param paramThreeVoltage
	 */
	public String add(ParamThreeVoltage paramThreeVoltage) {
		String msg = null;
		try {
			int result = paramThreeVoltageMapper.add(paramThreeVoltage);
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
	 * 根据id查询三相电压信息
	 * @param id
	 * 2016年11月26日
	 */
	public ParamThreeVoltage findThreeVoltageById(String id) {
		return paramThreeVoltageMapper.findThreeVoltageById(id);
	}

	/**
	 * 修改三相电压设置
	 * @param paramThreeVoltage
	 * 2016年11月26日
	 */
	public String edit(ParamThreeVoltage paramThreeVoltage) {
		String msg = null;
		try {
			int result = paramThreeVoltageMapper.edit(paramThreeVoltage);
			if(result>0) 
				msg = "修改成功";
			else 
				msg = "修改失败";
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	/**
	 * 根据Id删除三项信息
	 * @param ids
	 * 2016年11月26日
	 */
	public AjaxJson delete(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(ids!=null){
			String[] areaIdArray = ids.split(",");
			for (int i = 0; i < areaIdArray.length; i++) {
				logContent = logContent+"删除【三相电压】的数据"+",";
				paramThreeVoltageMapper.delete(areaIdArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}

	/**
	 * 获取所有告警上下限电压
	 */
	@Override
	public List<ParamThreeVoltage> getVoltageList() {
		return paramThreeVoltageMapper.getVoltageList();
	}
	
}