package com.fourfaith.sysManage.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.SysUserRoleMapper;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.utils.CommonUtil;

/**
 * @ClassName: SysUserRoleServiceImpl
 * @Description: 用户角色service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:43:08
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
	
	protected Logger logger = Logger.getLogger(SysUserRoleServiceImpl.class);
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
  
    @Override
	public int deleteByPrimaryKey(String id) {
		int result = sysUserRoleMapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int insert(SysUserRole record) {
		int result = sysUserRoleMapper.insert(record);
		return result;
	}
	
	@Override
	public int insertSelective(SysUserRole record) {
		int result = sysUserRoleMapper.insertSelective(record);
		return result;
	}

	@Override
	public SysUserRole selectByPrimaryKey(String id) {
		SysUserRole entity = sysUserRoleMapper.selectByPrimaryKey(id);
		return entity;
	}
	
	@Override
	public int updateByPrimaryKeySelective(SysUserRole record) {
		int result = sysUserRoleMapper.updateByPrimaryKeySelective(record);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysUserRole record) {
		int result = sysUserRoleMapper.updateByPrimaryKey(record);
		return result;
	}
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysUserRoleMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysUserRole> getList(Map<String, Object> params) {
		return sysUserRoleMapper.getList(params);
	}

	@Override
	public SysUserRole findById(String Id) {
		return sysUserRoleMapper.findById(Id);
	}

	@Override
	public String add(SysUserRole model) {
		String msg = null;
		model.setId(CommonUtil.getRandomUUID());
		int result = sysUserRoleMapper.insertSelective(model);
		if(result>0){
			msg = "添加成功";
		}else{
		    msg = "添加失败";
		}
		logger.info(msg);
		return msg;
	}

	@Override
	public String update(SysUserRole model) {
		String msg = null;
		try {
			int result = sysUserRoleMapper.updateByPrimaryKeySelective(model);
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
	public String deleteByRoleId(String roleId) {
		String msg = null;
		try {
			int result = sysUserRoleMapper.deleteByRoleId(roleId);
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
	public List<String> getUserRoleId(String userId) {
		return sysUserRoleMapper.getUserRoleId(userId);
	}

	@Override
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysUserRoleMapper.deleteByPrimaryKey(id);
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
	public List<String> getRoleIdByUserId(String userId) {
		return sysUserRoleMapper.getRoleIdByUserId(userId);
	}

	/**
	 * TODO : 根据用户Id，查询用户角色关联表的roleId
	 * @param user
	 * 2016年11月5日
	 * Administrator : xiaogaoxiang
	 */
	public SysUserRole findByUserId(SysUser user) {
		return sysUserRoleMapper.findByUserId(user);
	}
	
}