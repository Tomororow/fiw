package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysUserWater;

/**
 * @ClassName: SysUserWaterMapper
 * @Description: 用户售水权限dao接口
 * @Author: zhaojx
 * @Date: 2017年4月24日 上午11:52:27
 */
public interface SysUserWaterMapper {
	
	/**
	 * @Title: findUserWaterByCode
	 * @Description: 根据用户编码 获取用户售水对象
	 * @param: @param userCode
	 * @return: List<SysUserWater>
	 */
	SysUserWater getUserWaterByCode(String userCode);
	
	/**
	 * @Title: addUserWater
	 * @Description: 保存用户售水信息
	 * @param: @param sysUserWater
	 * @return: int
	 */
	int addUserWater(SysUserWater sysUserWater);
	
	/**
	 * @Title: updateUserWater
	 * @Description: 修改用户售水信息
	 * @param: @param sysUserWater
	 * @return: int
	 */
	int updateUserWater(SysUserWater sysUserWater);
	
}