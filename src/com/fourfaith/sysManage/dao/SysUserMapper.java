package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysUser;

/**
 * @ClassName: SysUserMapper
 * @Description: 用户dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:12:42
 */
public interface SysUserMapper {

   	int deleteByPrimaryKey(String id);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);

	Integer getCount(Map<String, Object> params);

	List<SysUser> getList(Map<String, Object> params);

	SysUser findByUserName(String username);
	
	String getUserCodeByID(String id);
	
	/**
	 * @Title: updateUserAuthority
	 * @Description: 售水权限分配后 修改SysUser表中Authority字段
	 * @param userCode
	 * @return: int
	 * @Author: zhaojx
	 */
	int updateUserAuthority(SysUser userModel);
	
	/**
	 * @Title: uniqueUserCode
	 * @Description: 用户编码 唯一校验
	 * @param userCode
	 * @return: List<SysUser>
	 * @Author: zhaojx
	 */
	List<SysUser> uniqueUserCode(String userCode);
	
	/**
	 * @Title: appAudit
	 * @Description: App用户注册审核
	 * @param userModel
	 * @return: int
	 * @Author: zhaojx
	 */
	int appAudit(SysUser userModel);
	
	/**
	 * @Title: getAppUserCount
	 * @Description: 获取app用户数量
	 * @param params
	 * @return: Integer
	 * @Author: zhaojx
	 */
	Integer getAppUserCount(Map<String, Object> params);
	
	/**
	 * @Title: getAppUserList
	 * @Description: 获取app用户列表
	 * @return: List<SysUser>
	 * @Author: zhaojx
	 */
	List<SysUser> getAppUserList(Map<String, Object> params);
	
}