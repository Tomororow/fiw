package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysWellUseMapper;
import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysWellUseServiceImpl
 * @Description: 机井用途service实现类
 * @Author: zhaojx
 */
@Service("sysWellUseService")
public class SysWellUseServiceImpl implements SysWellUseService {
	
	protected Logger logger = Logger.getLogger(SysWellUseServiceImpl.class);
	
	@Autowired
	private SysWellUseMapper sysWellUseMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysWellUseMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysWellUse> getList(Map<String, Object> params) {
		return sysWellUseMapper.getList(params);
	}

	public int deleteByPrimaryKey(String id) {
		int result = sysWellUseMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(SysWellUse record) {
		int result = sysWellUseMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysWellUseMapper.deleteByPrimaryKey(id);
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
	
	public int insertSelective(SysWellUse record) {
		int result = sysWellUseMapper.insertSelective(record);
		return result;
	}

	public SysWellUse selectByPrimaryKey(String id) {
		SysWellUse entity = sysWellUseMapper.selectByPrimaryKey(id);
		return entity;
	}

	public SysWellUse findById(String Id) {
		return sysWellUseMapper.selectByPrimaryKey(Id);
	}

	public String add(SysWellUse model) {
		String msg = null;
		// 增加水井用途名唯一性校验（新增），只需要根据输入的水井用途名称来检验是否存在即可
		List<SysWellUse> suwt = sysWellUseMapper.uniqueAddSelectTive(model);
		/**
		 *  1、如果查询到的水井用途名存在，则提示该水井用途名已存在；
		 *  2、如果查询到的水井用途名不存在，则执行新增操作
		 *  	A、如果新增成功（无异常），则提示添加成功
		 *  	B、如果新增失败（异常），则提示添加失败
		 */
		if(null==suwt || suwt.size()==0) {
			int result = sysWellUseMapper.insertSelective(model);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
		} else {
			msg = "该水井用途名已存在";
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
	    			SysWellUse plan = findById(id);
	    			logContent = logContent+"删除【"+plan.getWellUse()+"】的数据"+",";
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
	
	public String update(SysWellUse sysWellUse) {
		String msg = null;
		try {
			// 增加水井用途名唯一性校验（修改），只需要根据输入的水井用途名称来检验是否存在即可
			List<SysWellUse> suwt = sysWellUseMapper.uniqueAddSelectTive(sysWellUse);
			/**
			 *  1、如果查询到的水井用途名存在，则提示该水井用途名已存在；
			 *  2、如果查询到的水井用途名不存在，则执行新增操作
			 *  	A、如果新增成功（无异常），则提示添加成功
			 *  	B、如果新增失败（异常），则提示添加失败
			 */
			if(null==suwt || suwt.size()==0) {
				int result = sysWellUseMapper.updateByPrimaryKeySelective(sysWellUse);
				if(result>0){
				   msg = "更新成功";
				}else{
				  msg = "更新失败";
				}
			} else {
				msg = "该水井用途名已存在";
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