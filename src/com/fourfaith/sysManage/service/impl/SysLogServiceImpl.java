package com.fourfaith.sysManage.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.SysLogMapper;
import com.fourfaith.sysManage.model.SysLog;
import com.fourfaith.sysManage.service.SysLogService;

/**
 * @ClassName: SysLogServiceImpl
 * @Description: 日志dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:39:45
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
	
	protected Logger logger = Logger.getLogger(SysLogServiceImpl.class);
	
	@Autowired
	private SysLogMapper sysLogMapper;
  
    @Override
	public int deleteByPrimaryKey(String id) {
		int result = sysLogMapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int insert(SysLog record) {
		int result = sysLogMapper.insert(record);
		return result;
	}
	
	@Override
	public int insertSelective(SysLog record) {
		int result = sysLogMapper.insertSelective(record);
		return result;
	}

	@Override
	public SysLog selectByPrimaryKey(String id) {
		SysLog entity = sysLogMapper.selectByPrimaryKey(id);
		return entity;
	}
	
	@Override
	public int updateByPrimaryKeySelective(SysLog record) {
		int result = sysLogMapper.updateByPrimaryKeySelective(record);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysLog record) {
		int result = sysLogMapper.updateByPrimaryKey(record);
		return result;
	}
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysLogMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysLog> getList(Map<String, Object> params) {
		return sysLogMapper.getList(params);
	}

	@Override
	public SysLog findById(String Id) {
		return sysLogMapper.findById(Id);
	}

	@Override
	public String add(SysLog model) {
		String msg = null;
		try {
			int result = sysLogMapper.insertSelective(model);
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
	
}