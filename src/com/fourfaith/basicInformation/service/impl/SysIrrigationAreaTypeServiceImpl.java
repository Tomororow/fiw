package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysIrrigationAreaTypeMapper;
import com.fourfaith.basicInformation.model.SysIrrigationAreaType;
import com.fourfaith.basicInformation.service.SysIrrigationAreaTypeService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysIrrigationAreaTypeServiceImpl
 * @Description: 灌溉区域service实现类
 * @Author: zhaojx
 */
@Service("sysIrrigationAreaTypeService")
public class SysIrrigationAreaTypeServiceImpl implements SysIrrigationAreaTypeService {
	
	protected Logger logger = Logger.getLogger(SysIrrigationAreaTypeServiceImpl.class);
	
	@Autowired
	private SysIrrigationAreaTypeMapper sysIrrigationAreaTypeMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysIrrigationAreaTypeMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysIrrigationAreaType> getList(Map<String, Object> params) {
		return sysIrrigationAreaTypeMapper.getList(params);
	}

	public int deleteByPrimaryKey(String id) {
		int result = sysIrrigationAreaTypeMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(SysIrrigationAreaType record) {
		int result = sysIrrigationAreaTypeMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysIrrigationAreaTypeMapper.deleteByPrimaryKey(id);
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
	
	public int insertSelective(SysIrrigationAreaType record) {
		int result = sysIrrigationAreaTypeMapper.insertSelective(record);
		return result;
	}

	public SysIrrigationAreaType selectByPrimaryKey(String id) {
		SysIrrigationAreaType entity = sysIrrigationAreaTypeMapper.selectByPrimaryKey(id);
		return entity;
	}

	public SysIrrigationAreaType findById(String Id) {
		return sysIrrigationAreaTypeMapper.selectByPrimaryKey(Id);
	}

	public String add(SysIrrigationAreaType model) {
		String msg = null;
		// 增加灌区类型名唯一性校验（新增），只需要根据输入的灌区类型名称来检验是否存在即可
		List<SysIrrigationAreaType> suwt = sysIrrigationAreaTypeMapper.uniqueAddSelectTive(model);
		/**
		 *  1、如果查询到的灌区类型名存在，则提示该灌区类型名已存在；
		 *  2、如果查询到的灌区类型名不存在，则执行新增操作
		 *  	A、如果新增成功（无异常），则提示添加成功
		 *  	B、如果新增失败（异常），则提示添加失败
		 */
		if(null==suwt || suwt.size()==0) {
			int result = sysIrrigationAreaTypeMapper.insertSelective(model);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
		} else {
			msg = "该灌区类型名已存在";
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
	    			SysIrrigationAreaType plan = findById(id);
	    			logContent = logContent+"删除【"+plan.getIrrigationAreaType()+"】的数据"+",";
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
	
	public String update(SysIrrigationAreaType sysIrrigationAreaType) {
		String msg = null;
		try {
			// 增加灌区类型名唯一性校验（修改），只需要根据输入的灌区类型名称来检验是否存在即可
			List<SysIrrigationAreaType> suwt = sysIrrigationAreaTypeMapper.uniqueUpdateSelectTive(sysIrrigationAreaType);
			/**
			 *  1、如果查询到的灌区类型名存在，则提示该灌区类型名已存在；
			 *  2、如果查询到的灌区类型名不存在，则执行新增操作
			 *  	A、如果新增成功（无异常），则提示添加成功
			 *  	B、如果新增失败（异常），则提示添加失败
			 */
			if(null==suwt || suwt.size()==0) {
				int result = sysIrrigationAreaTypeMapper.updateByPrimaryKeySelective(sysIrrigationAreaType);
				if(result>0){
				   msg = "更新成功";
				}else{
				  msg = "更新失败";
				}
			} else {
				msg = "该灌区类型名已存在";
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