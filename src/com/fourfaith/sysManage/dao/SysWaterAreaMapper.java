package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;

/**
 * @ClassName: SysWaterAreaMapper
 * @Description: 水管区域dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:13:39
 */
public interface SysWaterAreaMapper {

	List<SysWaterArea> getList(Map<String, Object> params);
	
	int insertArea(SysWaterArea SysWaterArea);
	
	SysWaterArea selectByPrimaryKey(String areaId);
	
	int deleteByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(SysWaterArea SysWaterArea);
	
	SysWaterArea selectCode(String waterAreaCode);

	/**
	 * 获取水管区域子区域级联操作
	 * @param sysWaterArea
	 * 2016年10月16日
	 */
	List<SysWaterArea> getChildAreaInfo(SysWaterArea sysWaterArea);
	
	List<SysWaterArea> selectChildArea(String id);

	/**
	 * 获取第一级水管区域信息
	 * @return
	 * 2016年10月16日
	 */
	List<SysWaterArea> getFirstLevelList();

	/**
	 * 根据已选择的水管区域，获取到所有的水管区域的waterAreaCode值
	 * @param sysWaterArea
	 */
	SysWaterArea getWaterAreaCode(SysWaterArea sysWaterArea);

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
	 * 分页记录查询出的总条数
	 * @param params
	 * 2016年11月1日
	 */
	Integer getCount(Map<String, Object> params);

	List<SysWaterArea> getLists(Map<String, Object> params);

	/**
	 * 根据Id查询第一级水管编码信息
	 * @param id
	 * 2016年11月1日
	 */
	SysWaterArea findFirstWaterAreaLevelById(String id);

	/**
	 * 查询所有的所属水管区域信息
	 * 2016年11月4日
	 */
	List<SysWaterArea> getAllWaterArea();

	/**
	 * 根据登陆人员所属区域Id，获取到当前所属区域信息
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
	
	
	SysWaterArea getWaterAreaCodes(String deviceCode);
	
	SysWaterArea selectWaterLevel(String waterId);
	
	/**
	 * 对接大数据页面的需求
	 * @param params
	 * @return
	 */
	List<SysWaterArea> selectWaterAreaList(Map<String, Object> params);
	
}