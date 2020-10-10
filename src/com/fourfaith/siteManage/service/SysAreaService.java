package com.fourfaith.siteManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.WaterTree;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysAreaService
 * @Description: 行政区域service接口
 * @Author: zhaojx
 * @Date: 2017年5月14日 上午11:40:59
 */
public interface SysAreaService{

	List<SysArea> getAllArea();
	
	List<SysArea> getChildArea(String areaId);
	
	String add(SysArea sysArea);
	
	AjaxJson delSysArea(String areaIds);
	
	SysArea findById(String areaId);
	
	String delete(String areaId);
	
	String update(SysArea sysArea);
	
	List<SysArea> selectWaterId(String waterAreaId);
	
	List<SysArea> seelectWaterId(String waterAreaId);
	
	SysArea selectMin();
	
	List<SysArea> selectParentId(String parentAreaId);
	
	List<WaterTree> recursion(String id,List<WaterTree> menus);
	
	List<WaterTree> recursionFour(String id,List<WaterTree> menus);
	
	List<WaterTree> treeList();

	/**
	 * 获取第一级的行政区域
	 * @param object
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysArea> getFirstAreaLevelList(Object object);

	/**
	 * 获取行政区域子区域级联操作
	 * @param sysArea
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	String getChildDeviceAreaInfo(SysArea sysArea);

	/**
	 * 根据已选择的行政区域，获取到所有的行政区域的areaCode值
	 * @param sysArea
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	String getAreaCode(SysArea sysArea);

	/**
	 * 根据用户所在的areaId获取当前及自权限区域
	 * @param areaId
	 * 2016年10月23日
	 * Administrator: xiaogaoxiang
	 */
	List<SysArea> getCurrAndChildArea(String areaId);

	/**
	 * 获取登陆人员所在的区域做级联下拉框操作
	 * @param login_user
	 * 2016年10月25日
	 * Administrator: xiaogaoxiang
	 */
	List<SysArea> getLoginerAreaList(SysUser login_user);

	/**
	 * 分页操作记录查询总条数
	 * @param params
	 * 2016年10月30日
	 * Administrator: xiaogaoxiang
	 */
	int getCount(Map<String, Object> params);

	/**
	 * 分页查询，查询当前节点的下一级子节点信息
	 * @param params
	 * 2016年10月30日
	 * Administrator: xiaogaoxiang
	 */
	List<SysArea> getList(Map<String, Object> params);

	/**
	 * 根据Id查询第一级行政编码信息
	 * @param areaId
	 * 2016年10月30日
	 * Administrator: xiaogaoxiang
	 */
	SysArea findFirstAreaLevelById(String areaId);

	/**
	 * 根据用户所属最低一级行政区域查询本区域及同级所有区域
	 * @param sysArea
	 * 2016年11月5日
	 * Administrator: xiaogaoxiang
	 */
	List<SysArea> getAreaByIdAndLevel(SysArea sysArea);

	/**
	 * 根据Id查询行政区域信息
	 * @param sysArea
	 * 2016年11月5日
	 * Administrator: xiaogaoxiang
	 */
	SysArea getAreaById(SysArea sysArea);

	/**
	 * 检查名称唯一性
	 * @param params
	 * 2016年11月15日
	 * Administrator: xiaogaoxiang
	 */
	List<SysArea> uniqueArea(Map<String, Object> params);
	
	List<WaterTree> waterTree(int i);
	
	List<WaterTree> recursionArea(String id, List<WaterTree> menus);
	
	SysArea getChildCode(String areaCode);
	
	List<SysArea> getChildCodeInfo(String id);
	
	List<SysArea> findLevelSyaAreaCodeList(String level);

	List<SysArea> seelectWater();
	
}