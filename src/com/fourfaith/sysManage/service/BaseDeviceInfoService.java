package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.BaseDeviceModel;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;

/**
 * @ClassName: BaseDeviceInfoService
 * @Description: 机井信息service接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:26:46
 */
public interface BaseDeviceInfoService{

	List<BaseDeviceInfo> getList(Map<String, Object> params);
	
    Integer getCount(Map<String, Object> params);
    
    List<BaseDeviceModel> getDeviceModelList();
    
    String add(BaseDeviceInfo baseDeviceInfo);
    
    BaseDeviceInfo findById(String id);
    
    String update(BaseDeviceInfo baseDeviceInfo);
	
	AjaxJson delBaseDeviceInfo(String ids);
	
	String delete(String id);
	
	List<BaseDeviceInfo> selectPageList(Map<String,Object> pageInfo);
	
	int selectPageCount(Map<String,Object> pageInfo);
	
	/**
	 * TODO :清空8400之前的查询数据
	 * @param baseDeviceInfo
	 * 2018年06月13日
	 * Administrator : liuyun
	 */
	void delete_8400(String deviceCode,String beginTime, String endTime);

	/**
	 * TODO :生成机井管理中同地区最大的机井水管码
	 * @param baseDeviceInfo
	 * 2016年10月17日
	 */
	String getMaxWaterAreaCode(BaseDeviceInfo baseDeviceInfo);

	/**
	 * TODO :生成机井管理中同地区最大的机井行政码
	 * @param baseDeviceInfo
	 * 2016年10月17日
	 */
	String getMaxAreaCode(BaseDeviceInfo baseDeviceInfo);

	/**
	 * TODO :根据最下一级的Id来获取机井基本表中机井信息
	 * @param baseDeviceInfo
	 * 2016年10月25日
	 */
	String getLastDeviceWaterAreaInfo(BaseDeviceInfo baseDeviceInfo);

	/**
	 * TODO :查询所有的机井设备信息
	 * 2016年10月25日
	 */
	List<BaseDeviceInfo> getAllBaseDeviceInfo(BaseDistWaterPlan model);

	/**
	 * TODO :查询分页信息总条数
	 * @param params
	 * 2016年11月4日
	 */
	Integer getBaseCount(Map<String, Object> params);

	/**
	 * TODO :查询分页信息总信息
	 * @param params
	 * 2016年11月4日
	 */
	List<BaseDeviceInfo> getBaseList(Map<String, Object> params);

	/**
	 * TODO :根据选择的设备类型查看不同的设备实时数据（金讯润泽）
	 * @param params
	 * 2016年11月18日
	 */
	Integer getCountOfBase(Map<String, Object> params);

	/**
	 * TODO :根据选择的设备类型查看不同的设备实时数据信息集合（金讯润泽）
	 * @param params
	 * 2016年11月18日
	 */
	List<BaseDeviceInfo> getListOfBase(Map<String, Object> params);

	/**
	 * TODO :根据递归出来的水管区域信息，查询该区域下的所有机井信息
	 * @param params
	 * 2016年12月4日
	 */
	List<BaseDeviceInfo> findByWaterAreaId(Map<String, Object> params);

	/**
	 * TODO :根据Id获取历史数据数量
	 * @param params
	 * 2016年12月12日
	 */
	Integer getHistoryCount(Map<String, Object> params);

	/**
	 * TODO :根据Id获取历史数据集合
	 * @param params
	 * 2016年12月12日
	 */
	List<BaseDeviceInfo> getHistoryList(Map<String, Object> params);
	
	/**
	 * TODO :根据Id获取历史数据集合
	 * @param params
	 * 2018年06月13日
	 * Administrator : liuyun
	 */
	List<BaseDeviceInfo> getHistoryList_8400(String deviceCode,String beginTime, String endTime);

	/**
	 * TODO :根据截取的cardCode 查询BaseDeviceInfo（设备机井信息）中数据（唯一）
	 * @param areaCode
	 * 2017年1月17日
	 */
	BaseDeviceInfo selectByAreaCode(String areaCode);
	
	/**
	 * @Title: uniqueDevCode
	 * @Description: 设备编码  唯一检查
	 * @param: @param deviceCode
	 * @return: String
	 */
	String uniqueDevCode(String deviceCode);
	
	/**
	 * @Title: uniqueDevno
	 * @Description: 设备号  唯一校验
	 * @param: @param deviceNo
	 * @return: String
	 */
	String uniqueDevno(String deviceNo);
	
	/**
	 * @Title: uniqueDTUNo
	 * @Description: DTU号码  唯一校验
	 * @param: @param DTUNo
	 * @return: String
	 */
	String uniqueDTUNo(String dtuNo);
	
	/**
	 * TODO :查询需要升级的机井信息
	 * @param params
	 * 2018年儿童节
	 * Administrator : liuyun
	 */
	List<String> getHpgradeList(String waterArea, String areaId, String pumpState, String cardState, String verUpgrade);
	
	/**
	 * TODO :修改升级后的信息
	 * @param params
	 * 2018年6月3日
	 * Administrator : liuyun
	 */
	void updateByUpgradeType(String deviceCode);
	
	/**
	 * @Title: getautoSetParamList
	 * @Description: 查询自动设参机井信息
	 * @param: @param areaId
	 * @param: @param pumpState
	 * @param: @param cardState
	 * @param: @param VerAutoParam
	 * @return: List<String>
	 * @Author: zhaojinxin
	 */
	List<String> getAutoSetParamList(String waterArea, String areaId, String pumpState, String cardState, String verAutoParam);
	
	/**
	 * @Title: updateByAutoParamType
	 * @Description: 设参成功后修改标识字段 防止重复设参
	 * @param: @param deviceCode
	 * @return: void
	 * @Author: zhaojinxin
	 */
	void updateByAutoParamType(String deviceCode);
	
	/**
	 * @Title: findDeviceVersion
	 * @Description: 获取全部控制器程序版本
	 * @param: @return
	 * @return: List<String>
	 * @Author: zhaojinxin
	 */
	List<String> findDeviceVersion();
	
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