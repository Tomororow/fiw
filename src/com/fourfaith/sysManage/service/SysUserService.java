package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserWater;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysUserService
 * @Description: 用户dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:34:54
 */
public interface SysUserService{

	int deleteByPrimaryKey(String id);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);

	Integer getCount(Map<String, Object> params);

	List<SysUser> getList(Map<String, Object> params);

	SysUser findById(String Id);
	
	SysUser findByUserName(String username);
	
	String add(SysUser model);
	
	String update(SysUser model);
	
	AjaxJson deleteUser(String items);
	
	String delete(String id);
	
	String moveUser(SysUser model);
	
	/**
	 * @Title: findUserCodeByID
	 * @Description: 根据用户ID获取用户编码
	 * @param: @param id
	 * @return: String
	 */
	String findUserCodeByID(String id);
	
	/**
	 * @Title: findUserWaterByCode
	 * @Description: 根据用户编码  获取用户售水对象
	 * @param: @param userCode
	 * @return: String
	 */
	String findUserWaterByCode(String userCode);
	
	/**
	 * @Title: saveUserWater
	 * @Description: 保存用户售水对象
	 * @param: @param sysUserWater
	 * @return: int
	 */
	String saveUserWater(SysUserWater sysUserWater);
	
	/**
	 * @Title: editUserWater
	 * @Description: 修改用户售水对象
	 * @param: @param sysUserWater
	 * @return: String
	 */
	String editUserWater(SysUserWater sysUserWater);
	
	/**
	 * @Title: findUserWaterByUserCode
	 * @Description: 
	 * @param: @param sysUserWater
	 * @return: SysUserWater
	 */
	SysUserWater findUserWaterByUserCode(String userCode);
	
	/**
	 * @Title: getV4IP
	 * @Description: 获取外网IP
	 * @return: void
	 */
	String getV4IP();
	
	/**
	 * @Title: getValidateCode
	 * @Description: 生成验证码 并发送
	 * @return: void
	 */
	String getValidateCode(String phone);
	
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
	 * @Description: 用户编码  唯一检查
	 * @param userCode
	 * @return: String
	 * @Author: zhaojx
	 */
	String uniqueUserCode(String userCode);
	
	/**
	 * @Title: appAudit
	 * @Description: app用户注册审核
	 * @param id
	 * @return: String
	 * @Author: zhaojx
	 */
	String appAudit(SysUser userModel);
	
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
	
	/**
	 * @Title: initPwd
	 * @Description: 初始化密码
	 * @param userModel
	 * @return: String
	 * @Author: zhaojx
	 */
	String initPwd(SysUser userModel);

}