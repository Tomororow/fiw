package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.BaseDeviceModel;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;

/**
 * @ClassName: BaseDeviceInfoMapper
 * @Description: 机井信息dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:06:36
 */
public interface BaseDeviceInfoMapper {
	
	List<BaseDeviceInfo> getList(Map<String, Object> params);
	
	Integer getCount(Map<String, Object> params);
	
	List<BaseDeviceModel> getBaseDeviceModelList();
	
	int insertBaseDeviceInfo(BaseDeviceInfo baseDeviceInfo);
	
	BaseDeviceInfo selectByPrimaryKey(String id);
	
	int deleteByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(BaseDeviceInfo baseDeviceInfo);
	
	List<BaseDeviceInfo> selectPageList(Map<String,Object> pageInfo);
	
	int selectPageCount(Map<String,Object> pageInfo);
	
	/**
	 * TODO :清空8400之前的查询数据
	 * @param baseDeviceInfo
	 * 2018年06月13日
	 * Administrator : liuyun
	 */
	 void delete_8400(Map<String, Object> params);

	/**
	 * 生成机井管理中同地区最大的机井水管码
	 * @param baseDeviceInfo
	 * 2016年10月17日
	 */
	List<BaseDeviceInfo> getMaxWaterAreaCode(BaseDeviceInfo baseDeviceInfo);

	/**
	 * 生成机井管理中同地区最大的机井行政码
	 * @param baseDeviceInfo
	 * 2016年10月17日
	 */
	List<BaseDeviceInfo> getMaxAreaCode(BaseDeviceInfo baseDeviceInfo);

	/**
	 * 根据最下一级的Id来获取机井基本表中机井信息
	 * @param baseDeviceInfo
	 * 2016年10月25日
	 */
	List<BaseDeviceInfo> getLastDeviceWaterAreaInfo(BaseDeviceInfo baseDeviceInfo);

	/**
	 * 查询所有的机井设备信息
	 * 2016年10月25日
	 */
	List<BaseDeviceInfo> getAllBaseDeviceInfo(BaseDistWaterPlan model);

	/**
	 * 查询分页信息总条数
	 * @param params
	 * 2016年11月4日
	 */
	int getBaseCount(Map<String, Object> params);

	/**
	 * 查询分页信息总信息
	 * @param params
	 * 2016年11月4日
	 */
	List<BaseDeviceInfo> getBaseList(Map<String, Object> params);

	/**
	 * 根据选择的设备类型查看不同的设备实时数据数量（金讯润泽）
	 * @param params
	 * 2016年11月18日
	 */
	Integer getCountOfBase(Map<String, Object> params);

	/**
	 * 根据选择的设备类型查看不同的设备实时数据信息集合（金讯润泽）
	 * @param params
	 * 2016年11月18日
	 */
	List<BaseDeviceInfo> getListOfBase(Map<String, Object> params);

	/**
	 * 根据递归出来的水管区域信息，查询该区域下的所有机井信息
	 * @param params
	 * 2016年12月4日
	 */
	List<BaseDeviceInfo> findByWaterAreaId(Map<String, Object> params);

	/**
	 * 根据Id获取历史数据数量
	 * @param params
	 * 2016年12月12日
	 */
	Integer getHistoryCount(Map<String, Object> params);

	/**
	 * 根据Id获取历史数据集合
	 * @param params
	 * 2016年12月12日
	 */
	List<BaseDeviceInfo> getHistoryList(Map<String, Object> params);
	
	/**
	 * 根据Id获取历史数据集合
	 * TODO :
	 * @param params
	 * 2018年06月13日
	 * Administrator : liuyun
	 */
	List<BaseDeviceInfo> getHistoryList_8400(Map<String, Object> params);

	/**
	 * 根据截取的cardCode 查询BaseDeviceInfo（设备机井信息）中数据（唯一）
	 * @param areaCode
	 * 2017年1月17日
	 */
	BaseDeviceInfo selectByAreaCode(String areaCode);
	
	/**
	 * @Title: uniqueDevCode
	 * @Description: 设备编码 唯一检查
	 * @param: @param deviceCode
	 * @return: List<BaseDeviceInfo>
	 */
	List<BaseDeviceInfo> uniqueDevCode(String deviceCode);
	
	/**
	 * @Title: uniqueDevno
	 * @Description: 设备号  唯一校验
	 * @param: @param deviceNo
	 * @return: List<BaseDeviceInfo>
	 */
	List<BaseDeviceInfo> uniqueDevno(String deviceNo);
	
	/**
	 * @Title: uniqueDTUNo
	 * @Description: DTU号码  唯一校验
	 * @param: @param DTUNo
	 * @return: List<BaseDeviceInfo>
	 */
	List<BaseDeviceInfo> uniqueDTUNo(String dtuNo);

	/**
	 * TODO :查询需要升级的机井信息
	 * @param params
	 * 2018年儿童节
	 * Administrator : liuyun
	 */
	List<String> getHpgradeList(Map<String, Object> params);
	
	/**
	 * TODO :修改升级后的信息
	 * @param params
	 * 2018年6月3日
	 * Administrator : liuyun
	 */
	void updateByUpgradeType(String deviceCode);
	
	/**
	 * @Title: getAutoSetParamList
	 * @Description: 查询自动设参机井信息
	 * @param: @param params
	 * @param: @return
	 * @return: List<String>
	 * @Author: zhaojinxin
	 */
	List<String> getAutoSetParamList(Map<String, Object> params);
	
	/**
	 * @Title: updateByAutoParamType
	 * @Description: 设参成功后修改标识字段 防止重复设参
	 * @param: @param deviceCode
	 * @return: void
	 * @Author: zhaojinxin
	 */
	void updateByAutoParamType(String deviceCode);
	
	/**
	 * @Title: getDeviceVersion
	 * @Description: 获取全部控制器程序版本
	 * @param: @return
	 * @return: List<String>
	 * @Author: zhaojinxin
	 */
	List<String> getDeviceVersion();
	
	/**
	 * @Title: getDeviceMapInfo
	 * @Description: 获取电子地图加载所需信息
	 * @param: @param params
	 * @return: List<BaseDeviceInfo>
	 * @Author: zhaojinxin
	 */
	List<BaseDeviceInfo> getDeviceInfoOnMap(Map<String, Object> params);
	
	List<BaseDeviceInfo> selectWCode(List<String> collectList);
	
	List<BaseDeviceInfo> selectPageAreaList(List<String> codeList);

	/**
	* @Title:        findBaseDeviceInfoByDeviceCode  
	* @Description:  TODO(这里用一句话描述这个方法的作用)  
	* @param:        @param deviceCode
	* @param:        @return     
	* @return:       BaseDeviceInfo     
	* @throws  
	* @author        刘海年
	* @Date          2019年2月17日 下午4:10:39
	 */
	BaseDeviceInfo findBaseDeviceInfoByDeviceCode(String deviceCode);
	
	List<BaseDeviceInfo> findBaseDeviceInfoByWaterAreaId(String waterAreaId);
	
	List<BaseDeviceInfo> findSelectWaterIdBase(Map<String,Object> pageInfo);
	
	BaseDeviceInfo findSelectWaterId(String id);
	
	List<BaseDeviceInfo> remoteTimeUpdate();
	
	List<String> findSelectWaterString(Map<String,Object> pageInfo);
	
	List<BaseDeviceInfo> findDevicBigData(List<String> waterList);
	//查询所选行政区域所对应的所有devicecode
	List<BaseDeviceInfo> selectAreaAllCode(List<String> waterAreaIdsList);
	List<BaseDeviceInfo> selectwaterAreaAllCode(List<String> waterAreaIdsList);


}