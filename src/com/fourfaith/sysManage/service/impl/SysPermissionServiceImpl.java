package com.fourfaith.sysManage.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.SysPermissionMapper;
import com.fourfaith.sysManage.model.SysPermission;
import com.fourfaith.sysManage.service.SysPermissionService;

/**
 * @ClassName: SysPermissionServiceImpl
 * @Description: 权限service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:42:04
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
	
	protected Logger logger = Logger.getLogger(SysPermissionServiceImpl.class);
	
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	
	@Override
	public List<SysPermission> getList(Map<String, Object> params) {
		return sysPermissionMapper.getList(params);
	}
	
	@Override
	public String deleteByRoleId(String roleId) {
		String msg = null;
		int result = sysPermissionMapper.deleteByRoleId(roleId);
		if(result>0){
			msg = "删除成功";
		}else{
		    msg = "删除失败";
		}
		logger.info(msg);			
		return msg;
	}
	
	@Override
	public SysPermission findByMenuIdAndRoleId(String menuId, String roleId) {
		SysPermission sysPermission = new SysPermission();
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("menuId", menuId);
		params.put("roleId", roleId);
		List<SysPermission> sysPermissionList = this.getList(params);
		if(sysPermissionList!=null && sysPermissionList.size()>0){
			sysPermission = sysPermissionList.get(0);
		}
		return sysPermission;
	}
	
	@Override
	public String add(SysPermission model) {
		String msg = null;
		int result = sysPermissionMapper.insertSelective(model);
		if(result>0){
			msg = "添加成功";
		}else{
		    msg = "添加失败";
		}
		logger.info(msg);
		return msg;
	}
	
}