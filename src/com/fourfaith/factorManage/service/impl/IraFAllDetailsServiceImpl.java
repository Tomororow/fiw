package com.fourfaith.factorManage.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.factorManage.dao.IraFAllDetailsMapper;
import com.fourfaith.factorManage.model.IraFAllDetails;
import com.fourfaith.factorManage.service.IraFAllDetailsService;

@Service("iraFAllDetailsService")
public class IraFAllDetailsServiceImpl implements IraFAllDetailsService {
	
	protected Logger logger = Logger.getLogger(IraFAllDetailsServiceImpl.class);
	
	@Autowired
	private IraFAllDetailsMapper iraFAllDetailsMapper;
  
    @Override
	public int deleteByPrimaryKey(String id) {
		return iraFAllDetailsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(IraFAllDetails record) {
		return iraFAllDetailsMapper.insert(record);
	}
	
	@Override
	public int insertSelective(IraFAllDetails record) {
		return iraFAllDetailsMapper.insertSelective(record);
	}

	@Override
	public IraFAllDetails selectByPrimaryKey(String id) {
		return iraFAllDetailsMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKeySelective(IraFAllDetails record) {
		return iraFAllDetailsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(IraFAllDetails record) {
		return iraFAllDetailsMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		return iraFAllDetailsMapper.getCount(params);
	}

	@Override
	public List<IraFAllDetails> getList(Map<String, Object> params) {
		return iraFAllDetailsMapper.getList(params);
	}

	@Override
	public List<IraFAllDetails> getStatisList(Map<String, Object> params) {
		return iraFAllDetailsMapper.getStatisList(params);
	}

	@Override
	public Integer getStatisCount(Map<String, Object> params) {
		return iraFAllDetailsMapper.getStatisCount(params);
	}

	@Override
	public List<IraFAllDetails> getAvgList(Map<String, Object> params) {
		return iraFAllDetailsMapper.getAvgList(params);
	}

	@Override
	public List<IraFAllDetails> getMinAddList(Map<String, Object> params) {
		return iraFAllDetailsMapper.getMinAddList(params);
	}

	@Override
	public IraFAllDetails getLastest(Map<String, Object> params) {
		return iraFAllDetailsMapper.getLastest(params);
	}

}