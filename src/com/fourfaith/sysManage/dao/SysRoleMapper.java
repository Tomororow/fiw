package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysRole;

/**
 * @ClassName: SysRoleMapper
 * @Description: 角色dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:12:10
 */
public interface SysRoleMapper {

   	int deleteByPrimaryKey(String id);

	int insert(SysRole record);

	int insertSelective(SysRole record);

	SysRole selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysRole record);

	int updateByPrimaryKey(SysRole record);

	Integer getCount(Map<String, Object> params);

	List<SysRole> getList(Map<String, Object> params);
	
    SysRole getByRoleId(Map<String, Object> map);

    /**
     * 获取所有角色信息
     * @return
     * 2016年11月5日
     */
	List<SysRole> getAllRoleInfo();
	
	/**
	 * @Title: checkRoleCodeExist
	 * @Description: 角色编码 唯一检验
	 * @param roleCode
	 * @return: List<SysRole>
	 * @Author: zhaojx
	 */
	List<SysRole> checkRoleCodeExist(String roleCode);

}