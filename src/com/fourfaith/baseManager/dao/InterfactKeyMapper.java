package com.fourfaith.baseManager.dao;

import com.fourfaith.baseManager.model.InterfactKey;

public interface InterfactKeyMapper {
    int deleteByPrimaryKey(String id);

    int insert(InterfactKey record);

    int insertSelective(InterfactKey record);

    /**
     * @Title: selectByPrimaryKey
     * @Description:根据id获取秘钥信息
     * @param id
     * @return
     * @return InterfactKey
     * @author 刘海年
     * @date 2018年10月18日上午9:16:44
     */
    InterfactKey selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InterfactKey record);

    int updateByPrimaryKey(InterfactKey record);

    /**
     * @Title: selectAllRan
     * @Description:查询秘钥
     * @return
     * @return String
     * @author 刘海年
     * @date 2018年10月18日下午4:59:10
     */
	String selectAllRan();
}