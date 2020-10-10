package com.fourfaith.paramterManage.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.paramterManage.dao.ParamPowerMapper;
import com.fourfaith.paramterManage.model.ParamPower;
import com.fourfaith.paramterManage.service.ParamPowerService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: ParamPowerServiceImpl
 * @Description: 功率参数service实现
 * @Author: zhaojx
 * @Date: 2017年5月13日 下午6:10:11
 */
@Service("ParamPowerService")
public class ParamPowerServiceImpl implements ParamPowerService{
	
	protected Logger logger = Logger.getLogger(ParamPowerServiceImpl.class);

	@Autowired
	private ParamPowerMapper paramPowerMapper;

	/**
	 * 获取功率记录条数
	 */
	public Integer getCount() {
		return paramPowerMapper.getCount();
	}

	/**
	 * 获取功率信息集合
	 * @param params
	 */
	public List<ParamPower> getList(Map<String, Object> params) {
		return paramPowerMapper.getList(params);
	}

	/**
	 * 新增功率设置
	 * @param paramPower
	 */
	public String add(ParamPower paramPower) {
		String msg = null;
		try {
			int result = paramPowerMapper.add(paramPower);
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
	 * 根据id查询功率信息
	 * @param id
	 */
	public ParamPower findPowerById(String id) {
		return paramPowerMapper.findPowerById(id);
	}

	/**
	 * 修改功率设置
	 * @param paramPower
	 */
	public String edit(ParamPower paramPower) {
		String msg = null;
		try {
			int result = paramPowerMapper.edit(paramPower);
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
	 * 根据Id删除功率信息
	 * @param ids
	 */
	public AjaxJson delete(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(ids!=null){
			String[] areaIdArray = ids.split(",");
			for (int i = 0; i < areaIdArray.length; i++) {
				logContent = logContent+"删除【功率】的数据"+",";
				paramPowerMapper.delete(areaIdArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}
	
}