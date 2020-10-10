package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysIrrigationModeMapper;
import com.fourfaith.basicInformation.model.SysIrrigationMode;
import com.fourfaith.basicInformation.service.SysIrrigationModeService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysIrrigationModeServiceImpl
 * @Description: 灌溉类型service接口
 * @Author: zhaojx
 */
@Service("sysIrrigationModeService")
public class SysIrrigationModeServiceImpl implements SysIrrigationModeService {
	
	protected Logger logger = Logger.getLogger(SysIrrigationModeServiceImpl.class);
	
	@Autowired
	private SysIrrigationModeMapper sysIrrigationModeMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysIrrigationModeMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysIrrigationMode> getList(Map<String, Object> params) {
		return sysIrrigationModeMapper.getList(params);
	}

	public int deleteByPrimaryKey(String id) {
		int result = sysIrrigationModeMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(SysIrrigationMode record) {
		int result = sysIrrigationModeMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysIrrigationModeMapper.deleteByPrimaryKey(id);
			if(result>0){
				msg = "删除成功";
			}else{
			    msg = "删除失败";
			}
			logger.info(msg);			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}
	
	public int insertSelective(SysIrrigationMode record) {
		int result = sysIrrigationModeMapper.insertSelective(record);
		return result;
	}

	public SysIrrigationMode selectByPrimaryKey(String id) {
		SysIrrigationMode entity = sysIrrigationModeMapper.selectByPrimaryKey(id);
		return entity;
	}

	public SysIrrigationMode findById(String Id) {
		return sysIrrigationModeMapper.selectByPrimaryKey(Id);
	}

	public String add(SysIrrigationMode model) {
		String msg = null;
		// 增加灌溉模式名唯一性校验（新增），只需要根据输入的灌溉模式名称来检验是否存在即可
		List<SysIrrigationMode> suwt = sysIrrigationModeMapper.uniqueAddSelectTive(model);
		/**
		 *  1、如果查询到的灌溉模式名存在，则提示该灌溉模式名已存在；
		 *  2、如果查询到的灌溉模式名不存在，则执行新增操作
		 *  	A、如果新增成功（无异常），则提示添加成功
		 *  	B、如果新增失败（异常），则提示添加失败
		 */
		if(null==suwt || suwt.size()==0) {
			int result = sysIrrigationModeMapper.insertSelective(model);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
		} else {
			msg = "该灌溉模式名已存在";
		}
		logger.info(msg);
		return msg;
	}

	@Transactional(rollbackFor=Exception.class)
	public AjaxJson deleteDis(String items) {
		AjaxJson  ajaxJson = new AjaxJson();
		String logContent = "";
		try{
			if(items!=null){
				String [] itemArray=items.split(",");
	    		for(String item:itemArray)
	    		{
	    			String id = item;
	    			//直接删除
	    			SysIrrigationMode plan = findById(id);
	    			logContent = logContent+"删除【"+plan.getIrrigationMode()+"】的数据"+",";
	    			delete(id);
	    		}
	    		ajaxJson.setMsg("删除成功");
				ajaxJson.setSuccess(true);
				ajaxJson.setObj(logContent);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			ajaxJson.setMsg("操作失败，异常信息："+ex.getMessage());
			ajaxJson.setSuccess(false);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}
	
	public String update(SysIrrigationMode sysIrrigationMode) {
		String msg = null;
		try {
			// 增加灌溉模式名唯一性校验（修改），只需要根据输入的灌溉模式名称来检验是否存在即可
			List<SysIrrigationMode> suwt = sysIrrigationModeMapper.uniqueUpdateSelectTive(sysIrrigationMode);
			/**
			 *  1、如果查询到的灌溉模式名存在，则提示该灌溉模式名已存在；
			 *  2、如果查询到的灌溉模式名不存在，则执行新增操作
			 *  	A、如果新增成功（无异常），则提示添加成功
			 *  	B、如果新增失败（异常），则提示添加失败
			 */
			if(null==suwt || suwt.size()==0) {
				int result = sysIrrigationModeMapper.updateByPrimaryKeySelective(sysIrrigationMode);
				if(result>0){
				   msg = "更新成功";
				}else{
				  msg = "更新失败";
				}
			} else {
				msg = "该灌溉模式名已存在";
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