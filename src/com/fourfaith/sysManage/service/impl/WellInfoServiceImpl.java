package com.fourfaith.sysManage.service.impl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.sysManage.dao.WellInfoMapper;
import com.fourfaith.sysManage.model.WellInfo;
import com.fourfaith.sysManage.service.WellInfoService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: WellInfoServiceImpl
 * @Description: 恒泽机井信息service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:45:19
 */
@Service("WellInfoService")
public class WellInfoServiceImpl implements WellInfoService {
	
	protected Logger logger = Logger.getLogger(WellInfoServiceImpl.class);
	
	@Autowired
	private WellInfoMapper wellInfoMapper;
	
	@Override
	public String add(WellInfo wellInfo) {
		String msg = null;
		try {
			int result = wellInfoMapper.insertWellInfo(wellInfo);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}
	
	@Override
	public String update(WellInfo wellInfo) {
		String msg = null;
		try {
			int result = wellInfoMapper.updateByPrimaryKeySelective(wellInfo);
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
	@Transactional(rollbackFor=Exception.class)
	public AjaxJson delWellInfo(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(ids!=null){
			String[] idArray = ids.split(",");
			for (int i = 0; i < idArray.length; i++) {
				logContent = logContent+"删除机井编码【"+idArray[i]+"】的机井信息表数据"+",";
				delete(idArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}
	
	@Override
	public String delete(String id) {
		String msg = null;
		try {
			int result = wellInfoMapper.deleteByPrimaryKey(id);
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
	
}