package com.fourfaith.sysManage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.sysManage.dao.BaseDeviceInfoMapper;
import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.BaseDeviceModel;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlanDevice;
import com.fourfaith.waterRightManage.service.BaseDistWaterPlanDeviceService;
import com.google.gson.Gson;

/**
 * @ClassName: BaseDeviceInfoServiceImpl
 * @Description: 机井信息service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:37:48
 */
@Service("BaseDeviceInfoService")
public class BaseDeviceInfoServiceImpl implements BaseDeviceInfoService {

	protected Logger logger = Logger.getLogger(BaseDeviceInfoServiceImpl.class);

	@Autowired
	private BaseDeviceInfoMapper baseDeviceInfoMapper;
	@Autowired
	private BaseDistWaterPlanDeviceService BaseDistWaterPlanDeviceService;

	@Override
	public List<BaseDeviceInfo> getList(Map<String, Object> params) {
		return baseDeviceInfoMapper.getList(params);
	}

	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = baseDeviceInfoMapper.getCount(params);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson delBaseDeviceInfo(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		/**
		 * 水权管理中，有引用设备机井信息，因此删除之前，必须检查该设备是否在水权配水管理中引用了该条设备信息，如果删除了，水权管理功能将出现异常
		 * 1、如果引用了，则不能删除 2、如果没有引用，则可以删除
		 */
		// 根据设备机井ID，先检查是否在水权管理中是否包含设备机井引用信息
		if (ids != null) {
			String[] idArray = ids.split(",");
			List<String> deviceIds = new ArrayList<String>();
			for (String id : idArray) {
				deviceIds.add(id);
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("deviceIds", deviceIds);
			// 检查在水权配水管理中，是否包含该设备机井信息
			List<BaseDistWaterPlanDevice> BaseDistWaterPlanDeviceList = BaseDistWaterPlanDeviceService
					.selectBaseDistWaterPlanDeviceList(params);
			if (null == BaseDistWaterPlanDeviceList
					|| BaseDistWaterPlanDeviceList.size() == 0) {
				for (int i = 0; i < idArray.length; i++) {
					BaseDeviceInfo baseDeviceInfo = findById(idArray[i]);
					logContent = logContent + "删除【"
							+ baseDeviceInfo.getDeviceCode() + "】的机井设备信息数据"
							+ ",";
					delete(idArray[i]);
				}
				ajaxJson.setMsg("删除成功");
				ajaxJson.setSuccess(true);
				ajaxJson.setObj(logContent);
			} else {
				ajaxJson.setMsg("删除失败<br/>设备机井在其他功能中有被引用，请检查后再删除！");
				ajaxJson.setSuccess(false);
				logContent = "设备机井在其他功能中有被引用，请检查后再删除！";
				ajaxJson.setObj(logContent);
			}
		}
		return ajaxJson;
	}

	@Override
	public BaseDeviceInfo findById(String id) {
		return baseDeviceInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public String delete(String id) {
		String msg = null;
		try {
			int result = baseDeviceInfoMapper.deleteByPrimaryKey(id);
			if (result > 0) {
				msg = "删除成功";
			} else {
				msg = "删除失败";
			}
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	@Override
	public String update(BaseDeviceInfo baseDeviceInfo) {
		String msg = null;
		try {
			baseDeviceInfo
					.setWaterAreaId(baseDeviceInfo.getWaterAreaId().split(",")[baseDeviceInfo
							.getWaterAreaId().split(",").length - 1]);
			baseDeviceInfo
					.setAreaId(baseDeviceInfo.getAreaId().split(",")[baseDeviceInfo
							.getAreaId().split(",").length - 1]);
			int result = baseDeviceInfoMapper
					.updateByPrimaryKeySelective(baseDeviceInfo);
			if (result > 0) {
				msg = "更新成功";
			} else {
				msg = "更新失败";
			}
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "更新失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	@Override
	public List<BaseDeviceModel> getDeviceModelList() {
		return baseDeviceInfoMapper.getBaseDeviceModelList();
	}

	@Override
	public String add(BaseDeviceInfo baseDeviceInfo) {
		String msg = null;
		try {
			baseDeviceInfo
					.setWaterAreaId(baseDeviceInfo.getWaterAreaId().split(",")[baseDeviceInfo
							.getWaterAreaId().split(",").length - 1]);
			baseDeviceInfo
					.setAreaId(baseDeviceInfo.getAreaId().split(",")[baseDeviceInfo
							.getAreaId().split(",").length - 1]);
			int result = baseDeviceInfoMapper
					.insertBaseDeviceInfo(baseDeviceInfo);
			if (result > 0) {
				msg = "添加成功";
			} else {
				msg = "添加失败";
			}
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	/**
	 * 生成机井管理中同地区最大的机井水管码
	 * @param baseDeviceInfo
	 * 2016年10月17日
	 */
	public String getMaxWaterAreaCode(BaseDeviceInfo baseDeviceInfo) {
		List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoMapper.getMaxWaterAreaCode(baseDeviceInfo);
		if (null != baseDeviceInfoList && baseDeviceInfoList.size() > 0) {
			List<Long> waterAreaCodes = new ArrayList<Long>();
			for (BaseDeviceInfo bdi : baseDeviceInfoList) {
				waterAreaCodes.add(Long.parseLong(bdi.getDeviceWaterAreaCode()));
			}
			Long maxWaterCode = Collections.max(waterAreaCodes) + 1;
			baseDeviceInfo.setDeviceWaterAreaCode(maxWaterCode.toString());
		} else {
			baseDeviceInfo.setDeviceWaterAreaCode(baseDeviceInfo.getDeviceWaterAreaCode() + "001");
		}
		return baseDeviceInfo.getDeviceWaterAreaCode();
	}

	/**
	 * 生成机井管理中同地区最大的机井行政码
	 * @param baseDeviceInfo
	 * 2016年10月17日
	 */
	public String getMaxAreaCode(BaseDeviceInfo baseDeviceInfo) {
		List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoMapper.getMaxAreaCode(baseDeviceInfo);
		if (null != baseDeviceInfoList && baseDeviceInfoList.size() > 0) {
			List<Long> areaCodes = new ArrayList<Long>();
			for (BaseDeviceInfo bdi : baseDeviceInfoList) {
				areaCodes.add(Long.parseLong(bdi.getDeviceAreaCode()));
			}
			Long maxAreaCode = Collections.max(areaCodes) + 1;
			baseDeviceInfo.setDeviceAreaCode(maxAreaCode.toString());
		} else {
			baseDeviceInfo.setDeviceAreaCode(baseDeviceInfo.getDeviceAreaCode() + "01");
		}
		return baseDeviceInfo.getDeviceAreaCode();
	}

	/**
	 * TODO :清空8400之前的查询数据
	 * @param baseDeviceInfo
	 * 2018年06月13日 
	 * Administrator : liuyun
	 */
	public void delete_8400(String deviceCode, String beginTime, String endTime) {
		Map<String, Object> params = new HashMap<>();

		params.put("deviceCode", deviceCode);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		baseDeviceInfoMapper.delete_8400(params);
	}

	/**
	 * 根据最下一级的Id来获取机井基本表中机井信息
	 * @param baseDeviceInfo
	 * 2016年10月25日
	 */
	public String getLastDeviceWaterAreaInfo(BaseDeviceInfo baseDeviceInfo) {
		// 根据下拉框最后一级行政区域，根据areaID查询出baseDeviceInfo表中机井信息
		List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoMapper
				.getLastDeviceWaterAreaInfo(baseDeviceInfo);
		Gson gson = new Gson();
		// 转换成json字符串返回
		return gson.toJson(baseDeviceInfoList);
	}

	/**
	 * 查询所有的机井设备信息 2016年10月25日
	 */
	public List<BaseDeviceInfo> getAllBaseDeviceInfo(BaseDistWaterPlan model) {
		return baseDeviceInfoMapper.getAllBaseDeviceInfo(model);
	}

	/**
	 * 查询分页信息总条数
	 * @param params
	 * 2016年11月4日
	 */
	public Integer getBaseCount(Map<String, Object> params) {
		int result = baseDeviceInfoMapper.getBaseCount(params);
		return result;
	}

	/**
	 * 查询分页信息总信息
	 * @param params
	 * 2016年11月4日
	 */
	public List<BaseDeviceInfo> getBaseList(Map<String, Object> params) {
		return baseDeviceInfoMapper.getBaseList(params);
	}

	/**
	 * 根据选择的设备类型查看不同的设备实时数据数量（金讯润泽）
	 * @param params
	 * 2016年11月18日
	 */
	public Integer getCountOfBase(Map<String, Object> params) {
		return baseDeviceInfoMapper.getCountOfBase(params);
	}

	/**
	 * 根据选择的设备类型查看不同的设备实时数据信息集合（金讯润泽）
	 * @param params
	 * 2016年11月18日
	 */
	public List<BaseDeviceInfo> getListOfBase(Map<String, Object> params) {
		return baseDeviceInfoMapper.getListOfBase(params);
	}

	/**
	 * 根据递归出来的水管区域信息，查询该区域下的所有机井信息
	 * @param params
	 * 2016年12月4日
	 */
	public List<BaseDeviceInfo> findByWaterAreaId(Map<String, Object> params) {
		return baseDeviceInfoMapper.findByWaterAreaId(params);
	}

	/**
	 * 根据Id获取历史数据数量
	 * @param params
	 * 2016年12月12日
	 */
	public Integer getHistoryCount(Map<String, Object> params) {
		return baseDeviceInfoMapper.getHistoryCount(params);
	}

	/**
	 * 根据Id获取历史数据集合 2016年12月12日
	 */
	public List<BaseDeviceInfo> getHistoryList(Map<String, Object> params) {
		return baseDeviceInfoMapper.getHistoryList(params);
	}

	/**
	 * 根据Id获取历史数据集合 TODO :8400
	 * @param params
	 * 2018年06月13日
	 * Administrator : liuyun
	 */
	public List<BaseDeviceInfo> getHistoryList_8400(String deviceCode,
			String beginTime, String endTime) {
		Map<String, Object> params = new HashMap<>();

		params.put("deviceCode", deviceCode);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		return baseDeviceInfoMapper.getHistoryList_8400(params);
	}

	/**
	 * 根据截取的cardCode 查询BaseDeviceInfo（设备机井信息）中数据（唯一）
	 * @param areaCode
	 * 2017年1月17日
	 */
	public BaseDeviceInfo selectByAreaCode(String areaCode) {
		return baseDeviceInfoMapper.selectByAreaCode(areaCode);
	}

	@Override
	public String uniqueDevCode(String deviceCode) {
		List<BaseDeviceInfo> baseDeviceInfos = baseDeviceInfoMapper.uniqueDevCode(deviceCode);
		if (null != baseDeviceInfos && baseDeviceInfos.size() > 0) {
			return "failed";
		} else {
			return "success";
		}
	}

	@Override
	public String uniqueDevno(String deviceNo) {
		List<BaseDeviceInfo> baseDeviceInfos = baseDeviceInfoMapper.uniqueDevno(deviceNo);
		if (null != baseDeviceInfos && baseDeviceInfos.size() > 0) {
			return "failed";
		} else {
			return "success";
		}
	}

	@Override
	public String uniqueDTUNo(String dtuNo) {
		List<BaseDeviceInfo> baseDeviceInfos = baseDeviceInfoMapper
				.uniqueDTUNo(dtuNo);
		if (null != baseDeviceInfos && baseDeviceInfos.size() > 0) {
			return "failed";
		} else {
			return "success";
		}
	}

	/**
	 * TODO :查询需要升级的机井信息
	 * @param params
	 * 2018年儿童节
	 * Administrator : liuyun zhaojx
	 */
	@Override
	public List<String> getHpgradeList(String waterArea, String areaId, String pumpState, String cardState, String verUpgrade) {
		// 参数Map
		Map<String, Object> params = new HashMap<>();
		// 判断水管区域参数是否为空 空则表示获取所有
		if(waterArea.equals("non")){
			params.put("waterAreaList", null);
		}else{
			// 升级水管区域数组
			String[] waterAreas = waterArea.split(",");
			List<String> waterAreaList = Arrays.asList(waterAreas);
			params.put("waterAreaList", waterAreaList);
		}
		// 升级行政区域数组
		if(areaId.equals("non")){
			params.put("areaIdList", null);
		}else{
			String[] areaIds = areaId.split(",");
			List<String> areaIdList = Arrays.asList(areaIds);
			params.put("areaIdList", areaIdList);
		}
		// 水泵状态
		if(pumpState.equals("non")){
			params.put("pumpState", null);
		}else{
			params.put("pumpState", pumpState);
		}
		// 上卡状态
		if(cardState.equals("non")){
			params.put("cardState", null);
		}else{
			params.put("cardState", cardState);
		}
		// 升级版本数组
		String[] verUpgrades = verUpgrade.split(",");
		List<String> verUpgradeList = Arrays.asList(verUpgrades);
		params.put("verUpgradeList", verUpgradeList);
		return baseDeviceInfoMapper.getHpgradeList(params);
	}

	/**
	 * TODO :修改升级后的信息
	 * @param params
	 * 2018年6月3日
	 * Administrator : liuyun
	 */
	@Override
	public void updateByUpgradeType(String deviceCode) {
		baseDeviceInfoMapper.updateByUpgradeType(deviceCode);
	}

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
	@Override
	public List<String> getAutoSetParamList(String waterArea, String areaId, String pumpState, String cardState, String verAutoParam) {
		// 参数Map
		Map<String, Object> params = new HashMap<>();
		// 判断水管区域参数是否为空 空则表示获取所有
		if(waterArea.equals("non")){
			params.put("waterAreaList", null);
		}else{
			// 升级水管区域数组
			String[] waterAreas = waterArea.split(",");
			List<String> waterAreaList = Arrays.asList(waterAreas);
			params.put("waterAreaList", waterAreaList);
		}
		// 升级行政区域数组
		if(areaId.equals("non")){
			params.put("areaIdList", null);
		}else{
			String[] areaIds = areaId.split(",");
			List<String> areaIdList = Arrays.asList(areaIds);
			params.put("areaIdList", areaIdList);
		}
		// 水泵状态
		if(pumpState.equals("non")){
			params.put("pumpState", null);
		}else{
			params.put("pumpState", pumpState);
		}
		// 上卡状态
		if(cardState.equals("non")){
			params.put("cardState", null);
		}else{
			params.put("cardState", cardState);
		}
		// 自动设参版本数组
		String[] verAutoParams = verAutoParam.split(",");
		List<String> verAutoParamsList = Arrays.asList(verAutoParams);
		params.put("verAutoParamsList", verAutoParamsList);
		return baseDeviceInfoMapper.getAutoSetParamList(params);
	}

	/**
	 * @Title: updateByAutoParamType
	 * @Description: 设参成功后修改标识字段 防止重复设参
	 * @param: @param deviceCode
	 * @return: void
	 * @Author: zhaojinxin
	 */
	@Override
	public void updateByAutoParamType(String deviceCode) {
		baseDeviceInfoMapper.updateByAutoParamType(deviceCode);
	}

	@Override
	public List<String> findDeviceVersion() {
		return baseDeviceInfoMapper.getDeviceVersion();
	}
	
	@Override
	public List<BaseDeviceInfo> selectPageList(Map<String, Object> pageInfo) {
		return baseDeviceInfoMapper.selectPageList(pageInfo);
	}

	@Override
	public int selectPageCount(Map<String, Object> pageInfo) {
		return baseDeviceInfoMapper.selectPageCount(pageInfo);
	}

	@Override
	public List<BaseDeviceInfo> getDeviceInfoOnMap(Map<String, Object> params) {
		return baseDeviceInfoMapper.getDeviceInfoOnMap(params);
	}
	
	@Override
	public List<BaseDeviceInfo> selectWCode(List<String> collectList) {
		return baseDeviceInfoMapper.selectWCode(collectList);
	}
	
	@Override
	public List<BaseDeviceInfo> selectPageAreaList(List<String> codeList) {
		return baseDeviceInfoMapper.selectPageAreaList(codeList);
	}

	@Override
	public BaseDeviceInfo findBaseDeviceInfoByDeviceCode(String deviceCode) {
		return  baseDeviceInfoMapper.findBaseDeviceInfoByDeviceCode(deviceCode);
	}

	@Override
	public List<BaseDeviceInfo> findSelectWaterIdBase(Map<String,Object> pageInfo) {
		// TODO Auto-generated method stub
		return baseDeviceInfoMapper.findSelectWaterIdBase(pageInfo);
	}

	@Override
	public BaseDeviceInfo findSelectWaterId(String id) {
		// TODO Auto-generated method stub
		return baseDeviceInfoMapper.findSelectWaterId(id);
	}

	@Override
	public List<BaseDeviceInfo> findBaseDeviceInfoByWaterAreaId(
			String waterAreaId) {
		// TODO Auto-generated method stub
		return baseDeviceInfoMapper.findBaseDeviceInfoByWaterAreaId(waterAreaId);
	}

	@Override
	public List<BaseDeviceInfo> remoteTimeUpdate() {
		// TODO Auto-generated method stub
		return baseDeviceInfoMapper.remoteTimeUpdate();
	}

	@Override
	public List<String> findSelectWaterString(Map<String, Object> pageInfo) {
		// TODO Auto-generated method stub
		return baseDeviceInfoMapper.findSelectWaterString(pageInfo);
	}

	@Override
	public List<BaseDeviceInfo> findDevicBigData(List<String> waterList) {
		// TODO Auto-generated method stub
		return baseDeviceInfoMapper.findDevicBigData(waterList);
	}

	@Override
	public List<BaseDeviceInfo> selectAreaAllCode(List<String> waterAreaIdsList) {
		return baseDeviceInfoMapper.selectAreaAllCode(waterAreaIdsList);
	}

	@Override
	public List<BaseDeviceInfo> selectwaterAreaAllCode(List<String> waterAreaIdsList) {
		return baseDeviceInfoMapper.selectwaterAreaAllCode(waterAreaIdsList);
	}


}