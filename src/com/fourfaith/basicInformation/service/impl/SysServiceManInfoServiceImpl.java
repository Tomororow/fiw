package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysServiceManMapper;
import com.fourfaith.basicInformation.model.SysServiceManInfo;
import com.fourfaith.basicInformation.service.SysServiceManInfoService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysServiceManInfoServiceImpl
 * @Description: 维修人员信息service实现
 * @Author: zhaojinxin
 * @Date: 2019年3月8日 下午4:21:34
 */
@Service("SysServiceManInfoService")
public class SysServiceManInfoServiceImpl implements SysServiceManInfoService {
	
	protected Logger logger = Logger.getLogger(SysServiceManInfoServiceImpl.class);
	
	@Autowired
	private SysServiceManMapper sysServiceManMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysServiceManMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysServiceManInfo> getList(Map<String, Object> params) {
		return sysServiceManMapper.getList(params);
	}
	
	@Override
	public List<SysServiceManInfo> getAssignInfo() {
		return sysServiceManMapper.getAssignInfo();
	}
	
	public int insertSelective(SysServiceManInfo record) {
		int result = sysServiceManMapper.insertSelective(record);
		return result;
	}
	
	public String add(SysServiceManInfo record) {
		String msg = null;
		int result = sysServiceManMapper.insertSelective(record);
		if(result>0){
			msg = "添加成功";
		}else{
		    msg = "添加失败";
		}
		logger.info(msg);
		return msg;
	}
	
	public SysServiceManInfo selectByPrimaryKey(String id) {
		SysServiceManInfo entity = sysServiceManMapper.selectByPrimaryKey(id);
		return entity;
	}
	
	public int deleteByPrimaryKey(String id) {
		int result = sysServiceManMapper.deleteByPrimaryKey(id);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysServiceManMapper.deleteByPrimaryKey(id);
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
	
	public SysServiceManInfo findById(String Id) {
		return sysServiceManMapper.selectByPrimaryKey(Id);
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
	    			SysServiceManInfo sysServiceManInfo = findById(id);
	    			logContent = logContent+"删除维修技术员【"+sysServiceManInfo.getServiceMan()+"】的数据"+",";
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

	public String update(SysServiceManInfo serviceManInfo) {
		String msg = null;
		try {
			int result = sysServiceManMapper.updateByPrimaryKeySelective(serviceManInfo);
			if(result>0){
			   msg = "更新成功";
			}else{
			   msg = "更新失败";
			}
			logger.info(msg);			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "更新失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	@Override
	public List<SysServiceManInfo> findPersonList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sysServiceManMapper.findPersonList(params);
	}

	@Override
	public List<SysServiceManInfo> findMiddlePersonList(String code) {
		// TODO Auto-generated method stub
		return sysServiceManMapper.findMiddlePersonList(code);
	}

	@Override
	public List<SysServiceManInfo> findMiddlePersonListMap(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sysServiceManMapper.findMiddlePersonListMap(params);
	}
	
}