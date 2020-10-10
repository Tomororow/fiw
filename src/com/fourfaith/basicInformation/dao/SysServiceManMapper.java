package com.fourfaith.basicInformation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.basicInformation.model.SysServiceManInfo;

/**
 * @ClassName: SysServiceManMapper
 * @Description: 维修技术员dao接口
 * @Author: zhaojinxin
 * @Date: 2019年3月8日 下午3:02:59
 */
public interface SysServiceManMapper {

	/**
	 * @Title: getCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 获取信息列表(分页)
	 * @param: @param params
	 * @return: List<SysServiceManInfo>
	 */
	List<SysServiceManInfo> getList(Map<String, Object> params);
	
	/**
	 * @Title: getAll
	 * @Description: 获取全部信息
	 * @param: @return
	 * @return: List<SysServiceManInfo>
	 * @Author: zhaojinxin
	 */
	List<SysServiceManInfo> getAssignInfo();
	
	/**
	 * @Title: insertSelective
	 * @Description: 添加非空设备型号信息
	 * @param: @param record
	 * @return: int
	 */
	int insertSelective(SysServiceManInfo record);
	
	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 根据主键查询信息
	 * @param: @param id
	 * @return: SysServiceManInfo
	 */
	SysServiceManInfo selectByPrimaryKey(String id);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据主键删除信息
	 * @param: @param id
	 * @return: int
	 */
	int deleteByPrimaryKey(String id);
	
	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 根据主键有选择的更新信息
	 * @param: @param SysServiceManInfo
	 * @return: int
	 */
	int updateByPrimaryKeySelective(SysServiceManInfo SysServiceManInfo);
	
	List<SysServiceManInfo> findPersonList(Map<String, Object> params);
	
	List<SysServiceManInfo> findMiddlePersonList(@Param("code")String code);
	
	List<SysServiceManInfo> findMiddlePersonListMap(Map<String, Object> params);

}