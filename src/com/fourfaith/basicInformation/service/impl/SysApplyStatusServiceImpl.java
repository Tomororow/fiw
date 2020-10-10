package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysApplyStatusMapper;
import com.fourfaith.basicInformation.model.SysApplyStatus;
import com.fourfaith.basicInformation.model.SysUseWaterType;
import com.fourfaith.basicInformation.service.SysApplyStatusService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysApplyStatusServiceImpl
 * @Description: 应用状况service实现类
 * @Author: zhaojx
 */
@Service("sysApplyStatusService")
public class SysApplyStatusServiceImpl implements SysApplyStatusService {
	
	protected Logger logger = Logger.getLogger(SysApplyStatusServiceImpl.class);
	
	@Autowired
	private SysApplyStatusMapper sysApplyStatusMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysApplyStatusMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysApplyStatus> getList(Map<String, Object> params) {
		return sysApplyStatusMapper.getList(params);
	}

	public int deleteByPrimaryKey(String id) {
		int result = sysApplyStatusMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(SysApplyStatus record) {
		int result = sysApplyStatusMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysApplyStatusMapper.deleteByPrimaryKey(id);
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
	
	public int insertSelective(SysApplyStatus record) {
		int result = sysApplyStatusMapper.insertSelective(record);
		return result;
	}

	public SysApplyStatus selectByPrimaryKey(String id) {
		SysApplyStatus entity = sysApplyStatusMapper.selectByPrimaryKey(id);
		return entity;
	}

	public SysApplyStatus findById(String Id) {
		return sysApplyStatusMapper.selectByPrimaryKey(Id);
	}

	public String add(SysApplyStatus model) {
		String msg = null;
		// 增加应用状况名唯一性校验（新增），只需要根据输入的应用状况名称来检验是否存在即可
		List<SysUseWaterType> suwt = sysApplyStatusMapper.uniqueAddSelectTive(model);
		/**
		 *  1、如果查询到的应用状况名存在，则提示该应用状况名已存在；
		 *  2、如果查询到的应用状况名不存在，则执行新增操作
		 *  	A、如果新增成功（无异常），则提示添加成功
		 *  	B、如果新增失败（异常），则提示添加失败
		 */
		if(null==suwt || suwt.size()==0) {
			int result = sysApplyStatusMapper.insertSelective(model);
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
	    			SysApplyStatus plan = findById(id);
	    			logContent = logContent+"删除【"+plan.getApplyStatus()+"】的数据"+",";
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
	
	public String update(SysApplyStatus sysApplyStatus) {
		String msg = null;
		try {
			// 增加应用状况名唯一性校验（修改），只需要根据输入的应用状况名称来检验是否存在即可
			List<SysUseWaterType> suwt = sysApplyStatusMapper.uniqueUpdateSelectTive(sysApplyStatus);
			/**
			 *  1、如果查询到的应用状况名存在，则提示该应用状况名已存在；
			 *  2、如果查询到的应用状况名不存在，则执行新增操作
			 *  	A、如果新增成功（无异常），则提示添加成功
			 *  	B、如果新增失败（异常），则提示添加失败
			 */
			if(null==suwt || suwt.size()==0) {
				int result = sysApplyStatusMapper.updateByPrimaryKeySelective(sysApplyStatus);
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