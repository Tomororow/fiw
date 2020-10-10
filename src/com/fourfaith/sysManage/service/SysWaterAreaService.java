package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysWaterAreaService
 * @Description: 水管区域dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:35:14
 */
public interface SysWaterAreaService{
	
	List<SysWaterArea> getAllWaterArea();

	List<SysWaterArea> getLists(Map<String, Object> params);
	
	List<SysWaterArea> getList(Map<String, Object> params);
	
	Integer getCount(Map<String, Object> params);
	
	List<SysWaterArea> getChildAreaList(Map<String, Object> params);
	
	String add(SysWaterArea SysWaterArea);
	
	AjaxJson delSysWaterArea(String ids);
	
	SysWaterArea findById(String id);
	
	String delete(String id);
	
	String update(SysWaterArea SysWaterArea);
	
	List<SysWaterArea> selectChildArea(String id);
	
	SysWaterArea selectCode(String waterAreaCode);

	/**
	 * 获取水管区域子区域级联操作
	 * @param sysWaterArea
	 */
	String getChildAreaInfo(SysWaterArea sysWaterArea);

	/**
	 * 获取第一级水管区域信息
	 * @param object
	 * 2016年10月16日
	 */
	List<SysWaterArea> getFirstLevelList(Object object);

	/**
	 * 根据已选择的水管区域，获取到所有的水管区域的waterAreaCode值
	 * @param sysWaterArea
	 * 2016年10月16日
	 */
	String getWaterAreaCode(SysWaterArea sysWaterArea);

	/**
	 * 获取登陆人员所在的区域做级联下拉框操作
	 * @param login_user
	 * 2016年10月25日
	 */
	List<SysWaterArea> getLoginerWaterAreaList(SysUser login_user);

	/**
	 * 查询所有的水管区域
	 * 2016年10月25日
	 */
	List<SysWaterArea> getAllWaterAreaList();

	/**
	 * 根据登陆用户所在的水管区域ID，获取到当前所属水管区域集合
	 * @param waterAreaId
	 * 2016年10月25日
	 */
	List<SysWaterArea> getCurrWaterAreaList(String waterAreaId);

	/**
	 * 根据Id查询第一级水管编码信息
	 * @param id
	 * 2016年11月1日
	 */
	SysWaterArea findFirstWaterAreaLevelById(String id);

	/**
	 * 根据当前所属水管区域查询当前和子节点水管区域信息
	 * @param waterAreaId
	 * 2016年11月4日
	 */
	List<SysWaterArea> getCurrAndChildWaterArea(String waterAreaId);

	/**
	 * 根据用户所属最低一级水管区域查询本区域及同级所有区域
	 * @param sysWaterArea
	 * 2016年11月5日
	 */
	List<SysWaterArea> getWaterAreaByIdAndLevel(SysWaterArea sysWaterArea);

	/**
	 * 根据Id查询水管区域信息
	 * @param sysWaterArea
	 * 2016年11月5日
	 */
	SysWaterArea getWaterAreaById(SysWaterArea sysWaterArea);

	/**
	 * 检验编码、名称唯一性
	 * @param params
	 * 2016年11月16日
	 */
	List<SysWaterArea> uniqueWaterArea(Map<String, Object> params);
	
	/**
	 * @Title: getCurrAndChildWaterAreas
	 * @Description: 根据当前登录用户获取子区域及权限区域
	 * @param waterAreaId
	 * @return: List<SysWaterArea>
	 * @Author: zhaojx
	 * @Date: 2017年11月21日 下午4:36:38
	 */
	List<SysWaterArea> getCurrAndChildWaterAreas(String waterAreaId);
	
	SysWaterArea getWaterAreaCode(String deviceCode);
	
	SysWaterArea selectWaterLevel(String waterId);
	
	/**
	 * 对接大数据页面的需求
	 * @param params
	 * @return
	 */
	List<SysWaterArea> selectWaterAreaList(Map<String, Object> params);
	
}