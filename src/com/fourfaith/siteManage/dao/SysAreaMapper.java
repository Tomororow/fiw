package com.fourfaith.siteManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.sysManage.model.SysUser;

/**
 * @ClassName: SysAreaMapper
 * @Description: 行政区域dao接口
 * @Author: zhaojx
 * @Date: 2017年5月14日 上午8:51:52
 */
public interface SysAreaMapper {

	/**
	 * @Title: getAllArea
	 * @Description: 获取全部行政区域信息
	 * @return: List<SysArea>
	 */
	List<SysArea> getAllArea();
	
	List<SysArea> selectWaterId(String waterAreaId);
	
	List<SysArea> seelectWaterId(String waterAreaId);
	
	SysArea selectMin();
	
	List<SysArea> selectParentId(String parentAreaId);
	
	/**
	 * @Title: insertArea
	 * @Description: 增加行政区域信息
	 * @param: @param sysArea
	 * @return: int
	 */
	int insertArea(SysArea sysArea);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询
	 * @param: @param areaId
	 * @return: SysArea
	 */
	SysArea selectByPrimaryKey(String areaId);
	
	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据主键删除
	 * @param: @param areaId
	 * @return: int
	 */
	int deleteByPrimaryKey(String areaId);
	
	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 根据主键更新非空信息
	 * @param: @param sysArea
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysArea sysArea);

	/**
	 * 获取第一级的行政区域
	 * 2016年10月17日
	 */
	List<SysArea> getFirstAreaLevelList();

	/**
	 * 获取行政区域子区域级联操作
	 * @param sysArea
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	List<SysArea> getChildDeviceAreaInfo(SysArea sysArea);

	/**
	 * 根据已选择的行政区域，获取到所有的行政区域的areaCode值
	 * @param swa
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	SysArea getAreaCode(SysArea swa);

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
	 * 根据当前节点Id，查询出下一级子节点信息（只查询下一级）
	 * @param areaId
	 * 2016年10月30日
	 * Administrator: xiaogaoxiang
	 */
	List<SysArea> getChildAreaById(String areaId);

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
	
	List<SysArea> seelectWaterOne();
	
	List<SysArea> seelectWater();
	
	SysArea getChildCode(String areaCode);
	
	List<SysArea> getChildCodeInfo(String id);
	
	List<SysArea> findLevelSyaAreaCodeList(String level);

}