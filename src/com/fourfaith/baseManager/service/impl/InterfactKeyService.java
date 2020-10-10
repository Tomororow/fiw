package com.fourfaith.baseManager.service.impl;

import com.fourfaith.baseManager.model.InterfactKey;

public interface InterfactKeyService {

	/**
     * @Title: selectByPrimaryKey
     * @Description:根据id获取秘钥信息
     * @param id
     * @return InterfactKey
     * @author 刘海年
     * @date 2018年10月18日上午9:16:44
     */
    InterfactKey selectByPrimaryKey(String id);

    /**
     * @Title: selectAllRan
     * @Description:查询秘钥
     * @return String
     * @author 刘海年
     * @date 2018年10月18日下午4:56:57
     */
	String selectAllRan();
}