package com.fourfaith.factorManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.factorManage.model.IraFAllDetails;

public interface IraFAllDetailsMapper {
    
	int deleteByPrimaryKey(String id);

    int insert(IraFAllDetails record);

    int insertSelective(IraFAllDetails record);

    IraFAllDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(IraFAllDetails record);

    int updateByPrimaryKey(IraFAllDetails record);

	List<IraFAllDetails> getList(Map<String, Object> params);

	int getCount(Map<String, Object> params);

	List<IraFAllDetails> getStatisList(Map<String, Object> params);

	Integer getStatisCount(Map<String, Object> params);

	List<IraFAllDetails> getAvgList(Map<String, Object> params);

	List<IraFAllDetails> getMinAddList(Map<String, Object> params);

	IraFAllDetails getLastest(Map<String, Object> params);

}