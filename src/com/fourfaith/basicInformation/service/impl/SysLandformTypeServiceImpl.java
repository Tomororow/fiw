package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysLandformTypeMapper;
import com.fourfaith.basicInformation.model.SysLandformType;
import com.fourfaith.basicInformation.service.SysLandformTypeService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysLandformTypeServiceImpl
 * @Description: 地貌类型service实现类
 * @Author: zhaojx
 */
@Service("sysLandformTypeService")
public class SysLandformTypeServiceImpl implements SysLandformTypeService {
	
	protected Logger logger = Logger.getLogger(SysLandformTypeServiceImpl.class);
	
	@Autowired
	private SysLandformTypeMapper sysLandformTypeMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysLandformTypeMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysLandformType> getList(Map<String, Object> params) {
		return sysLandformTypeMapper.getList(params);
	}

	public int deleteByPrimaryKey(String id) {
		int result = sysLandformTypeMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(SysLandformType record) {
		int result = sysLandformTypeMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysLandformTypeMapper.deleteByPrimaryKey(id);
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
	
	public int insertSelective(SysLandformType record) {
		int result = sysLandformTypeMapper.insertSelective(record);
		return result;
	}

	public SysLandformType selectByPrimaryKey(String id) {
		SysLandformType entity = sysLandformTypeMapper.selectByPrimaryKey(id);
		return entity;
	}

	public SysLandformType findById(String Id) {
		return sysLandformTypeMapper.selectByPrimaryKey(Id);
	}

	public String add(SysLandformType model) {
		String msg = null;
		// 增加地貌类型名唯一性校验（新增），只需要根据输入的地貌类型名称来检验是否存在即可
		List<SysLandformType> suwt = sysLandformTypeMapper.uniqueAddSelectTive(model);
		/**
		 *  1、如果查询到的地貌类型名存在，则提示该地貌类型名已存在；
		 *  2、如果查询到的地貌类型名不存在，则执行新增操作
		 *  	A、如果新增成功（无异常），则提示添加成功
		 *  	B、如果新增失败（异常），则提示添加失败
		 */
		if(null==suwt || suwt.size()==0) {
			int result = sysLandformTypeMapper.insertSelective(model);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
		} else {
			msg = "该地貌类型名已存在";
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
	    			SysLandformType plan = findById(id);
	    			logContent = logContent+"删除【"+plan.getLandformType()+"】的数据"+",";
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
	
	public String update(SysLandformType sysLandformType) {
		String msg = null;
		try {
			// 增加地貌类型名唯一性校验（修改），只需要根据输入的地貌类型名称来检验是否存在即可
			List<SysLandformType> suwt = sysLandformTypeMapper.uniqueUpdateSelectTive(sysLandformType);
			/**
			 *  1、如果查询到的地貌类型名存在，则提示该地貌类型名已存在；
			 *  2、如果查询到的地貌类型名不存在，则执行新增操作
			 *  	A、如果新增成功（无异常），则提示添加成功
			 *  	B、如果新增失败（异常），则提示添加失败
			 */
			if(null==suwt || suwt.size()==0) {
				int result = sysLandformTypeMapper.updateByPrimaryKeySelective(sysLandformType);
				if(result>0){
				   msg = "更新成功";
				}else{
				  msg = "更新失败";
				}
			} else {
				msg = "该地貌类型名已存在";
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