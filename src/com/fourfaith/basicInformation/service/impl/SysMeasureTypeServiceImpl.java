package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysMeasureTypeMapper;
import com.fourfaith.basicInformation.model.SysMeasureType;
import com.fourfaith.basicInformation.service.SysMeasureTypeService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysMeasureTypeServiceImpl
 * @Description: 计量设施类型service实现类
 * @Author: zhaojx
 */
@Service("sysMeasureTypeService")
public class SysMeasureTypeServiceImpl implements SysMeasureTypeService {
	
	protected Logger logger = Logger.getLogger(SysMeasureTypeServiceImpl.class);
	
	@Autowired
	private SysMeasureTypeMapper sysMeasureTypeMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysMeasureTypeMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysMeasureType> getList(Map<String, Object> params) {
		return sysMeasureTypeMapper.getList(params);
	}

	public int deleteByPrimaryKey(String id) {
		int result = sysMeasureTypeMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(SysMeasureType record) {
		int result = sysMeasureTypeMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysMeasureTypeMapper.deleteByPrimaryKey(id);
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
	
	public int insertSelective(SysMeasureType record) {
		int result = sysMeasureTypeMapper.insertSelective(record);
		return result;
	}

	public SysMeasureType selectByPrimaryKey(String id) {
		SysMeasureType entity = sysMeasureTypeMapper.selectByPrimaryKey(id);
		return entity;
	}

	public SysMeasureType findById(String Id) {
		return sysMeasureTypeMapper.selectByPrimaryKey(Id);
	}

	public String add(SysMeasureType model) {
		String msg = null;
		// 增加计量设施类型名唯一性校验（新增），只需要根据输入的计量设施类型名称来检验是否存在即可
		List<SysMeasureType> suwt = sysMeasureTypeMapper.uniqueAddSelectTive(model);
		/**
		 *  1、如果查询到的计量设施类型名存在，则提示该计量设施类型名已存在；
		 *  2、如果查询到的计量设施类型名不存在，则执行新增操作
		 *  	A、如果新增成功（无异常），则提示添加成功
		 *  	B、如果新增失败（异常），则提示添加失败
		 */
		if(null==suwt || suwt.size()==0) {
			int result = sysMeasureTypeMapper.insertSelective(model);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
		} else {
			msg = "该计量设施类型名已存在";
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
	    			SysMeasureType plan = findById(id);
	    			logContent = logContent+"删除【"+plan.getMeasureType()+"】的数据"+",";
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
	
	public String update(SysMeasureType sysMeasureType) {
		String msg = null;
		try {
			// 增加计量设施类型名唯一性校验（修改），只需要根据输入的计量设施类型名称来检验是否存在即可
			List<SysMeasureType> suwt = sysMeasureTypeMapper.uniqueUpdateSelectTive(sysMeasureType);
			/**
			 *  1、如果查询到的计量设施类型名存在，则提示该计量设施类型名已存在；
			 *  2、如果查询到的计量设施类型名不存在，则执行新增操作
			 *  	A、如果新增成功（无异常），则提示添加成功
			 *  	B、如果新增失败（异常），则提示添加失败
			 */
			if(null==suwt || suwt.size()==0) {
				int result = sysMeasureTypeMapper.updateByPrimaryKeySelective(sysMeasureType);
				if(result>0){
				   msg = "更新成功";
				}else{
				  msg = "更新失败";
				}
			} else {
				msg = "该计量设施类型名已存在";
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