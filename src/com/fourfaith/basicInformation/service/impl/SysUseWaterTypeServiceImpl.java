package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysUseWaterTypeMapper;
import com.fourfaith.basicInformation.model.SysUseWaterType;
import com.fourfaith.basicInformation.service.SysUseWaterTypeService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysUseWaterTypeServiceImpl
 * @Description: 取水类型service实现类
 * @Author: zhaojx
 */
@Service("BaseUseWaterTypeService")
public class SysUseWaterTypeServiceImpl implements SysUseWaterTypeService {
	
	protected Logger logger = Logger.getLogger(SysUseWaterTypeServiceImpl.class);
	
	@Autowired
	private SysUseWaterTypeMapper sysUseWaterTypeMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysUseWaterTypeMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysUseWaterType> getList(Map<String, Object> params) {
		return sysUseWaterTypeMapper.getList(params);
	}
	
	public int deleteByPrimaryKey(String id) {
		int result = sysUseWaterTypeMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(SysUseWaterType record) {
		int result = sysUseWaterTypeMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysUseWaterTypeMapper.deleteByPrimaryKey(id);
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
	
	public int insertSelective(SysUseWaterType record) {
		int result = sysUseWaterTypeMapper.insertSelective(record);
		return result;
	}

	public SysUseWaterType selectByPrimaryKey(String id) {
		SysUseWaterType entity = sysUseWaterTypeMapper.selectByPrimaryKey(id);
		return entity;
	}

	public SysUseWaterType findById(String Id) {
		return sysUseWaterTypeMapper.selectByPrimaryKey(Id);
	}

	public String add(SysUseWaterType model) {
		String msg = null;
		// 增加取水类型名唯一性校验（新增），只需要根据输入的取水类型名称来检验是否存在即可
		List<SysUseWaterType> suwt = sysUseWaterTypeMapper.uniqueAddSelectTive(model);
		/**
		 *  1、如果查询到的取水类型名存在，则提示该取水类型名已存在；
		 *  2、如果查询到的取水类型名不存在，则执行新增操作
		 *  	A、如果新增成功（无异常），则提示添加成功
		 *  	B、如果新增失败（异常），则提示添加失败
		 */
		if(null==suwt || suwt.size()==0) {
			int result = sysUseWaterTypeMapper.insertSelective(model);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
		} else {
			msg = "该取水类型名已存在";
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
	    			SysUseWaterType plan = findById(id);
	    			logContent = logContent+"删除【"+plan.getUseWaterType()+"】的数据"+",";
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
	
	public String update(SysUseWaterType sysUseWaterType) {
		String msg = null;
		try {
			// 增加取水类型名唯一性校验（修改），只需要根据输入的取水类型名称来检验是否存在即可
			List<SysUseWaterType> suwt = sysUseWaterTypeMapper.uniqueUpdateSelectTive(sysUseWaterType);
			/**
			 *  1、如果查询到的取水类型名存在，则提示该取水类型名已存在；
			 *  2、如果查询到的取水类型名不存在，则执行新增操作
			 *  	A、如果新增成功（无异常），则提示添加成功
			 *  	B、如果新增失败（异常），则提示添加失败
			 */
			if(null==suwt || suwt.size()==0) {
				int result = sysUseWaterTypeMapper.updateByPrimaryKeySelective(sysUseWaterType);
				if(result>0){
				   msg = "更新成功";
				}else{
				  msg = "更新失败";
				}
			} else {
				msg = "该取水类型名已存在";
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