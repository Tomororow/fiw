package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysDeviceModelMapper;
import com.fourfaith.basicInformation.model.SysDeviceModel;
import com.fourfaith.basicInformation.service.SysDeviceModelService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysDeviceModelServiceImpl
 * @Description: 设备型号service实现类
 * @Author: zhaojx
 */
@Service("sysDeviceModelService")
public class SysDeviceModelServiceImpl implements SysDeviceModelService {
	
	protected Logger logger = Logger.getLogger(SysDeviceModelServiceImpl.class);
	
	@Autowired
	private SysDeviceModelMapper sysDeviceModelMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysDeviceModelMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysDeviceModel> getList(Map<String, Object> params) {
		return sysDeviceModelMapper.getList(params);
	}

	public int deleteByPrimaryKey(String id) {
		int result = sysDeviceModelMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(SysDeviceModel record) {
		int result = sysDeviceModelMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysDeviceModelMapper.deleteByPrimaryKey(id);
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
	
	public int insertSelective(SysDeviceModel record) {
		int result = sysDeviceModelMapper.insertSelective(record);
		return result;
	}

	public SysDeviceModel selectByPrimaryKey(String id) {
		SysDeviceModel entity = sysDeviceModelMapper.selectByPrimaryKey(id);
		return entity;
	}

	public SysDeviceModel findById(String Id) {
		return sysDeviceModelMapper.selectByPrimaryKey(Id);
	}

	public String add(SysDeviceModel model) {
		String msg = null;
		// 增加机井设备型号名唯一性校验（新增），只需要根据输入的机井设备型号名称来检验是否存在即可
		List<SysDeviceModel> suwt = sysDeviceModelMapper.uniqueAddSelectTive(model);
		/**
		 *  1、如果查询到的机井设备型号名存在，则提示该机井设备型号名已存在；
		 *  2、如果查询到的机井设备型号名不存在，则执行新增操作
		 *  	A、如果新增成功（无异常），则提示添加成功
		 *  	B、如果新增失败（异常），则提示添加失败
		 */
		if(null==suwt || suwt.size()==0) {
			int result = sysDeviceModelMapper.insertSelective(model);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
		} else {
			msg = "该机井设备型号名已存在";
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
	    			SysDeviceModel plan = findById(id);
	    			logContent = logContent+"删除【"+plan.getDeviceModel()+"】的数据"+",";
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
	
	public String update(SysDeviceModel sysDeviceModel) {
		String msg = null;
		try {
			// 增加机井设备型号名唯一性校验（修改），只需要根据输入的机井设备型号名称来检验是否存在即可
			List<SysDeviceModel> suwt = sysDeviceModelMapper.uniqueAddSelectTive(sysDeviceModel);
			/**
			 *  1、如果查询到的机井设备型号名存在，则提示该机井设备型号名已存在；
			 *  2、如果查询到的机井设备型号名不存在，则执行新增操作
			 *  	A、如果新增成功（无异常），则提示添加成功
			 *  	B、如果新增失败（异常），则提示添加失败
			 */
			if(null==suwt || suwt.size()==0) {
				int result = sysDeviceModelMapper.updateByPrimaryKeySelective(sysDeviceModel);
				if(result>0){
				   msg = "更新成功";
				}else{
				  msg = "更新失败";
				}
			} else {
				msg = "该机井设备型号名已存在";
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