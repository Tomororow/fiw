package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysMenu;

/**
 * @ClassName: SysMenuMapper
 * @Description: 菜单dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:10:26
 */
public interface SysMenuMapper {

   	int deleteByPrimaryKey(String id);

	int insert(SysMenu record);

	int insertSelective(SysMenu record);

	SysMenu selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysMenu record);

	int updateByPrimaryKey(SysMenu record);

	Integer getCount(Map<String, Object> params);

	List<SysMenu> getList(Map<String, Object> params);

	SysMenu getByMenuCode(String menuCode);
	    
	int getMaxMenuOrder();
	
	List<SysMenu> getMenuCodeList(Map<String,Object> params);
	
	/**
	 * 根据角色id获取一级菜单列表
	 * @param roleid
	 */
	List<SysMenu> getListByRoleId(String roleid);
	
}