package com.fourfaith.sysManage.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.sysManage.dao.SysRoleMapper;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.service.SysPermissionService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysRoleServiceImpl
 * @Description: 角色service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:42:27
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	
	protected Logger logger = Logger.getLogger(SysRoleServiceImpl.class);
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysPermissionService sysPermissionService;
  
    @Override
	public int deleteByPrimaryKey(String id) {
		int result = sysRoleMapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int insert(SysRole record) {
		int result = sysRoleMapper.insert(record);
		return result;
	}
	
	@Override
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysRoleMapper.deleteByPrimaryKey(id);
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
	public int insertSelective(SysRole record) {
		int result = sysRoleMapper.insertSelective(record);
		return result;
	}

	@Override
	public SysRole selectByPrimaryKey(String id) {
		SysRole entity = sysRoleMapper.selectByPrimaryKey(id);
		return entity;
	}
	
	@Override
	public int updateByPrimaryKeySelective(SysRole record) {
		int result = sysRoleMapper.updateByPrimaryKeySelective(record);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysRole record) {
		int result = sysRoleMapper.updateByPrimaryKey(record);
		return result;
	}
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysRoleMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysRole> getList(Map<String, Object> params) {
		return sysRoleMapper.getList(params);
	}

	@Override
	public SysRole findById(String Id) {
		return sysRoleMapper.selectByPrimaryKey(Id);
	}

	@Override
	public String add(SysRole model) {
		String msg = null;
		int result = sysRoleMapper.insertSelective(model);
		if(result>0){
			msg = "添加成功";
		}else{
		    msg = "添加失败";
		}
		logger.info(msg);
		return msg;
	}

	@Override
	public String update(SysRole model) {
		String msg = null;
		try {
			int result = sysRoleMapper.updateByPrimaryKeySelective(model);
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

	@Override
	public SysRole getByRoleId(Map<String, Object> map) {
		return sysRoleMapper.getByRoleId(map);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public AjaxJson deleteRole(String items) {
		AjaxJson  ajaxJson = new AjaxJson();
		String logContent = "";
		try{
			if(items!=null){
				String [] itemArray=items.split(",");
	    		for(String item:itemArray)
	    		{
	    			String id = item;
	    			//直接删除
	    			SysRole role = findById(id);
	    			logContent = logContent+"删除【"+role.getRoleName()+"】的角色数据"+",";
	    			delete(id);
	    			//删除关联数据，用户角色、权限
	    			this.sysUserRoleService.deleteByRoleId(id);
	    			this.sysPermissionService.deleteByRoleId(id);
	    		}
	    		ajaxJson.setMsg("删除成功");
				ajaxJson.setSuccess(true);
				ajaxJson.setObj(logContent);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			ajaxJson.setMsg("操作失败，异常信息："+ex.getMessage());
			ajaxJson.setSuccess(false);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}

	/**
	 * 获取所有角色信息
	 * 2016年11月5日
	 */
	public List<SysRole> getAllRoleInfo() {
		return sysRoleMapper.getAllRoleInfo();
	}

	@Override
	public String checkRoleCodeExist(String roleCode) {
		List<SysRole> roleList = sysRoleMapper.checkRoleCodeExist(roleCode);
		if(null != roleList && roleList.size() > 0){
			return "failed";
		}else{
			return "success";
		}
	}
	
}