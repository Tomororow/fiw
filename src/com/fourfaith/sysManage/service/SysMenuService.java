package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysMenu;

/**
 * @ClassName: SysMenuService
 * @Description: 菜单service接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:33:12
 */
public interface SysMenuService{

	int deleteByPrimaryKey(String id);

	int insert(SysMenu record);

	int insertSelective(SysMenu record);
	
	String update(SysMenu model);

	SysMenu selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysMenu record);

	int updateByPrimaryKey(SysMenu record);

	Integer getCount(Map<String, Object> params);

	List<SysMenu> getList(Map<String, Object> params);

	SysMenu findById(String Id);
	
    SysMenu getByMenuCode(String menuCode);
    
    String delete(String id);
	
	int getMaxMenuOrder();
	
	String add(SysMenu model);
	
	List<SysMenu> getListByRoleidAndMenuid(String menuid, String roleid);
	
	List<SysMenu> getMenuCodeList(Map<String,Object> params);
	
	/**
	 * 获取系统管理应用下,非超管角色的过滤菜单
	 * @return
	 */
	List<String> getFilterMenus();
	
	/**
	 * 根据角色id获取一级菜单列表
	 * @param roleid
	 * @return
	 */
	List<SysMenu> getListByRoleId(String roleid);
	
}