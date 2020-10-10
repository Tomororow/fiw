package com.fourfaith.sysManage.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.SysMenuMapper;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysPermission;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysPermissionService;

/**
 * @ClassName: SysMenuServiceImpl
 * @Description: 菜单service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:40:17
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	
	protected Logger logger = Logger.getLogger(SysMenuServiceImpl.class);
	
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysPermissionService sysPermissionService;
  
    @Override
	public int deleteByPrimaryKey(String id) {
		int result = sysMenuMapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int insert(SysMenu record) {
		int result = sysMenuMapper.insert(record);
		return result;
	}
	
	@Override
	public int insertSelective(SysMenu record) {
		int result = sysMenuMapper.insertSelective(record);
		return result;
	}

	@Override
	public SysMenu selectByPrimaryKey(String id) {
		SysMenu entity = sysMenuMapper.selectByPrimaryKey(id);
		return entity;
	}
	
	@Override
	public int updateByPrimaryKeySelective(SysMenu record) {
		int result = sysMenuMapper.updateByPrimaryKeySelective(record);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysMenu record) {
		int result = sysMenuMapper.updateByPrimaryKey(record);
		return result;
	}
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysMenuMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysMenu> getList(Map<String, Object> params) {
		return sysMenuMapper.getList(params);
	}

	@Override
	public SysMenu findById(String Id) {
		return sysMenuMapper.selectByPrimaryKey(Id);
	}

	@Override
	public SysMenu getByMenuCode(String menuCode) {
		return sysMenuMapper.getByMenuCode(menuCode);
	}

	@Override
	public int getMaxMenuOrder() {
		//菜单管理，企业管理——超管专属
		return sysMenuMapper.getMaxMenuOrder();
	}

	@Override
	public List<SysMenu> getListByRoleidAndMenuid(String menuId, String roleid) {
		List<SysMenu> menuList = new ArrayList<SysMenu>();
		Map<String,Object> params = new HashMap<String,Object>();
		// 之前put里面的别名写错，写成了roleid，导致在xml文件的sql不识别。修改成roleId
		params.put("roleId", roleid);
		List<String> menuIdList = new ArrayList<String>();
		List<SysPermission> spList = this.sysPermissionService.getList(params);
		if(spList!=null && spList.size()>0){
			for (int i = 0; i < spList.size(); i++) {
				menuIdList.add(spList.get(i).getMenuId());
			}
		}
		Map<String,Object> menuParams = new HashMap<String,Object>();
		menuParams.put("parentMenuId", menuId);
		List<SysMenu> allMenuList = getList(menuParams);
		if(allMenuList!=null && allMenuList.size()>0){
			for (int i = 0; i < allMenuList.size(); i++) {
				SysMenu menu = allMenuList.get(i);
				if(menuIdList.contains(menu.getId())){
					menuList.add(menu);
				}
			}
		}
		return menuList;
	}

	@Override
	public List<SysMenu> getMenuCodeList(Map<String, Object> params) {
		return sysMenuMapper.getMenuCodeList(params);
	}

	@Override
	public List<String> getFilterMenus() {
		//菜单管理，企业管理——超管专属
		return new ArrayList<String>(Arrays.asList("menuManage","enterpriseManage"));
	}

	@Override
	public List<SysMenu> getListByRoleId(String roleid) {
		return sysMenuMapper.getListByRoleId(roleid);
	}

	@Override
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysMenuMapper.deleteByPrimaryKey(id);
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

	@Override
	public String add(SysMenu model) {
		String msg = null;
		try {
			int result = sysMenuMapper.insertSelective(model);
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
	public String update(SysMenu model) {
		String msg = null;
		try {
			int result = sysMenuMapper.updateByPrimaryKeySelective(model);
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