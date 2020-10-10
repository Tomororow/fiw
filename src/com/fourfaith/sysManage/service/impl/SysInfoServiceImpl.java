package com.fourfaith.sysManage.service.impl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.SysInfoMapper;
import com.fourfaith.sysManage.model.SysInfo;
import com.fourfaith.sysManage.service.SysInfoService;

/**
 * @ClassName: SysInfoServiceImpl
 * @Description: 信息发布dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:39:29
 */
@Service("SysInfoService")
public class SysInfoServiceImpl implements SysInfoService {
	
	protected Logger logger = Logger.getLogger(SysInfoServiceImpl.class);
	
	@Autowired
	private SysInfoMapper sysInfoMapper;
	
	@Override
	public SysInfo findByData(String infoKey) {
		return sysInfoMapper.findByInfoKey(infoKey);
	}
	
	@Override
	public String update(SysInfo model) {
		String msg = null;
		try {
			int result = sysInfoMapper.updateByInfoKey(model);
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
	
}