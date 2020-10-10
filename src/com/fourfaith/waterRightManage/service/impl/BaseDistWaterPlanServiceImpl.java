package com.fourfaith.waterRightManage.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.WaterAreaRecursionUtil;
import com.fourfaith.waterRightManage.dao.BaseDistWaterPlanMapper;
import com.fourfaith.waterRightManage.model.BaseDistAppendWaterPlan;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlanDevice;
import com.fourfaith.waterRightManage.service.BaseDistAppendWaterPlanService;
import com.fourfaith.waterRightManage.service.BaseDistWaterPlanDeviceService;
import com.fourfaith.waterRightManage.service.BaseDistWaterPlanService;
import com.google.gson.Gson;

/**
 * @ClassName: BaseDistWaterPlanServiceImpl
 * @Description: 配水计划service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:58:35
 */
@Service("baseDistWaterPlanService")
public class BaseDistWaterPlanServiceImpl implements BaseDistWaterPlanService {
	
	protected Logger logger = Logger.getLogger(BaseDistWaterPlanServiceImpl.class);
	
	@Autowired
	private BaseDistWaterPlanMapper baseDistWaterPlanMapper;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;
	@Autowired
	private BaseDistWaterPlanDeviceService BaseDistWaterPlanDeviceService;
	@Autowired
	private BaseDistAppendWaterPlanService baseDistAppendWaterPlanService;
	
    public int deleteByPrimaryKey(String id) {
		int result = baseDistWaterPlanMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(BaseDistWaterPlan record) {
		int result = baseDistWaterPlanMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			// 根据配水Id，删除对应的配水机井设备关联表中信息
			baseDistWaterPlanMapper.deleteBaseDistWaterPlanDeviceById(id);
			int result = baseDistWaterPlanMapper.deleteByPrimaryKey(id);
			if(result>0){
				msg = "删除成功";
			}else{
			    msg = "删除失败";
			}
			logger.info(msg);			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}
	
	public int insertSelective(BaseDistWaterPlan record) {
		int result = baseDistWaterPlanMapper.insertSelective(record);
		return result;
	}

	public BaseDistWaterPlan selectByPrimaryKey(String id) {
		BaseDistWaterPlan entity = baseDistWaterPlanMapper.selectByPrimaryKey(id);
		return entity;
	}
	
	public Integer getCount(Map<String, Object> params) {
		// 查询所有的水管区域集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 根据登陆用户所在的水管区域ID，获取到当前所属水管区域集合
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, params.get("waterAreaId").toString());
		params.put("waterAreaIds", sysWaterAreaList);
		int result = baseDistWaterPlanMapper.getCount(params)==null?0:baseDistWaterPlanMapper.getCount(params);
		return result;
	}

	public List<BaseDistWaterPlan> getList(Map<String, Object> params) {
		// 查询所有的水管区域集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 根据登陆用户所在的水管区域ID，获取到当前所属水管区域集合
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, params.get("waterAreaId").toString());
		params.put("waterAreaIds", sysWaterAreaList);
		return baseDistWaterPlanMapper.getList(params);
	}

	public BaseDistWaterPlan findById(String Id) {
		return baseDistWaterPlanMapper.selectByPrimaryKey(Id);
	}

	public String add(BaseDistWaterPlan model) {
		String msg = null;
		int result = 0;
		/**
		 * 	1、如果判断选择的没有到井，则将已选择的所有子区域的水管机井信息按照分配的规则进行分配
		 * 	2、如果判断选择的到井，则直接给该井分配配水计划
		 */
		if(null==model.getBaseDeviceId() || ""==model.getBaseDeviceId()) {
			if(model.getIsAppendWater()==0) {
				// 查询所有的水管区域集合
				List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
				// 查询所有的机井设备信息集合(区分水井用途)
				List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoService.getAllBaseDeviceInfo(model);
				if(null!=baseDeviceInfoList && baseDeviceInfoList.size()>0) {
					// 根据登陆用户所在的水管区域ID，获取到当前所属水管区域集合
					String waterAreaId = model.getWaterAreaId().split(",")[model.getWaterAreaId().split(",").length-1];
					List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
					if(null==sysWaterAreaList || sysWaterAreaList.size()==0) {
						msg = "请选择水管区域";
						logger.info(msg);
						return msg;
					}
					/**
					 *  1、sysWaterAreaCurrAndChildList为获取到的所有过滤后的水管区域信息集合
					 *  2、利用sysWaterAreaCurrAndChildList集合与所有的设备机井信息集合做比较，将符合条件的设备机井信息获取出来
					 */
					List<String> baseDeviceIds = new ArrayList<String>();
					for(int i=0;i<sysWaterAreaList.size();i++) {
						for(int j=0;j<baseDeviceInfoList.size();j++) {
							if(sysWaterAreaList.get(i).getId().equals(baseDeviceInfoList.get(j).getWaterAreaId()))
								baseDeviceIds.add(baseDeviceInfoList.get(j).getId());
						}
					}
					BaseDistWaterPlanDevice bdwdi = null;
					// 保存配水机井设备信息
					for(String deviceId : baseDeviceIds) {
						bdwdi = new BaseDistWaterPlanDevice();
						bdwdi.setId(CommonUtil.getRandomUUID());
						bdwdi.setBaseDistWaterId(model.getId());
						bdwdi.setUserId(model.getOperator());
						bdwdi.setBaseDeviceId(deviceId);
						bdwdi.setCreateTime(new Date());
						bdwdi.setEditTime(new Date());
						baseDistWaterPlanMapper.insertBaseDistWaterPlanDevice(bdwdi);
					}
					model.setWaterAreaId(model.getWaterAreaId().split(",")[model.getWaterAreaId().split(",").length-1]);
					result = baseDistWaterPlanMapper.insertSelective(model);
				} else {
					msg = "该水管区域下无任何机井信息，请重新添加！";
					logger.info(msg);
					return msg;
				}
			} else {
				// 查询所有的水管区域集合
				List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
				// 查询所有的机井设备信息集合(区分水井用途)
				List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoService.getAllBaseDeviceInfo(model);
				if(null!=baseDeviceInfoList && baseDeviceInfoList.size()>0) {
					// 根据登陆用户所在的水管区域ID，获取到当前所属水管区域集合
					String waterAreaId = model.getWaterAreaId().split(",")[model.getWaterAreaId().split(",").length-1];
					List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
					if(null==sysWaterAreaList || sysWaterAreaList.size()==0) {
						msg = "请选择水管区域";
						logger.info(msg);
						return msg;
					}
					/**
					 *  1、sysWaterAreaCurrAndChildList为获取到的所有过滤后的水管区域信息集合
					 *  2、利用sysWaterAreaCurrAndChildList集合与所有的设备机井信息集合做比较，将符合条件的设备机井信息获取出来
					 */
					BaseDistAppendWaterPlan bdawp = null;
					List<String> baseDeviceIds = new ArrayList<String>();
					for(int i=0;i<sysWaterAreaList.size();i++) {
						for(int j=0;j<baseDeviceInfoList.size();j++) {
							if(sysWaterAreaList.get(i).getId().equals(baseDeviceInfoList.get(j).getWaterAreaId())) {
								baseDeviceIds.add(baseDeviceInfoList.get(j).getId());
								bdawp = new BaseDistAppendWaterPlan();
								bdawp.setId(CommonUtil.getRandomUUID());
								bdawp.setWaterAreaId(sysWaterAreaList.get(i).getId());
								bdawp.setDistType(model.getDistType());
								bdawp.setDistMode(model.getDistMode());
								bdawp.setDistYear(model.getDistYear());
								bdawp.setDistRound(model.getDistRound());
								bdawp.setDistWater(model.getDistWater());
								bdawp.setDistRatio(model.getDistRatio());
								bdawp.setDistPrice(model.getDistPrice());
								bdawp.setIsAppendWater(model.getIsAppendWater());
								bdawp.setWellUse(model.getWellUse());
								bdawp.setDeviceId(baseDeviceInfoList.get(j).getId());
								bdawp.setUseId(model.getOperator());
								baseDistAppendWaterPlanService.insert(bdawp);
							}
						}
					}
					result = 1;
				} else {
					msg = "该水管区域下无任何机井信息，请重新添加！";
					logger.info(msg);
					return msg;
				}
			}
		} else {
			BaseDeviceInfo bdi = baseDeviceInfoService.findById(model.getBaseDeviceId());
			if(model.getIsAppendWater()==0) {
				// 保存单个机井配水信息
				BaseDistWaterPlanDevice bdwdi = null;
				bdwdi = new BaseDistWaterPlanDevice();
				bdwdi.setId(CommonUtil.getRandomUUID());
				bdwdi.setBaseDistWaterId(model.getId());
				bdwdi.setUserId(model.getOperator());
				bdwdi.setBaseDeviceId(model.getBaseDeviceId());
				bdwdi.setCreateTime(new Date());
				bdwdi.setEditTime(new Date());
				baseDistWaterPlanMapper.insertBaseDistWaterPlanDevice(bdwdi);
				model.setWaterAreaId(bdi.getWaterAreaId());
				result = baseDistWaterPlanMapper.insertSelective(model);
			} else {
				BaseDistAppendWaterPlan bdawp = new BaseDistAppendWaterPlan();
				bdawp.setId(CommonUtil.getRandomUUID());
				bdawp.setWaterAreaId(bdi.getWaterAreaId());
				bdawp.setDistType(model.getDistType());
				bdawp.setDistMode(model.getDistMode());
				bdawp.setDistYear(model.getDistYear());
				bdawp.setDistRound(model.getDistRound());
				bdawp.setDistWater(model.getDistWater());
				bdawp.setDistRatio(model.getDistRatio());
				bdawp.setDistPrice(model.getDistPrice());
				bdawp.setIsAppendWater(model.getIsAppendWater());
				bdawp.setWellUse(model.getWellUse());
				bdawp.setDeviceId(bdi.getId());
				bdawp.setUseId(model.getOperator());
				baseDistAppendWaterPlanService.insert(bdawp);
				result = 1;
			}
		}
		if(result>0){
			msg = "添加成功";
		}else{
		    msg = "添加失败";
		}
		logger.info(msg);
		return msg;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public AjaxJson deleteDis(String items) {
		AjaxJson  ajaxJson = new AjaxJson();
		String logContent = "";
		try{
			if(items!=null){
				String [] itemArray=items.split(",");
	    		for(String item:itemArray)
	    		{
	    			String id = item;
	    			//直接删除
	    			BaseDistWaterPlan plan = findById(id);
	    			List<BaseDistWaterPlan> rptlist = baseDistWaterPlanMapper.selectrptplane(id);
	    			if(rptlist.size() == 0){
	    				logContent = logContent+"删除【"+plan.getDistMode()+"】的数据"+",";
	    				delete(id);
	    	    		ajaxJson.setMsg("删除成功");
	    				ajaxJson.setSuccess(true);
	    				ajaxJson.setObj(logContent);
	    			}else{
	    				ajaxJson.setMsg("计划已使用，不能删除");
	    				ajaxJson.setSuccess(false);//TODO
	    				ajaxJson.setObj(new String());
	    			}
	    		}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			ajaxJson.setMsg("操作失败，异常信息："+ex.getMessage());
			ajaxJson.setSuccess(false);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}

	/**
	 * 配水年份选择完后，筛选系统数据库
	 * 	1、如果当前配水方式，年份不存在（第一次添加），则轮次默认选择1，不能为其他轮次
	 * 	2、如果当前配水方式，年份存在（已经配过一次或多次），则轮次将读取数据库轮次+1
	 * @param plan
	 * 2016年10月26日
	 */
	public Map<String, Object> getDistRoundInfo(BaseDistWaterPlan plan,String userName) {
		List<BaseDistWaterPlan> baseDistWaterPlanList = baseDistWaterPlanMapper.getDistRoundInfo(plan);
		Map<String, Object> hm = new HashMap<String, Object>();
		if(null==baseDistWaterPlanList || baseDistWaterPlanList.size()==0) {
			return null;
		} else {
			hm.put("userName", userName);
			hm.put("distMode", baseDistWaterPlanList.get(0).getDistMode());
			hm.put("distRound", baseDistWaterPlanList.get(0).getDistRound().toString());
			return hm;
		}
	}

	/**
	 * 根据配水Id获取配水机井设备信息
	 * @param plan
	 * 2016年10月27日
	 */
	public String showDistWaterDeviceInfo(BaseDistWaterPlan plan) {
		List<BaseDistWaterPlanDevice> BaseDistWaterPlanDeviceList = BaseDistWaterPlanDeviceService.showDistWaterDeviceInfo(plan);
		return new Gson().toJson(BaseDistWaterPlanDeviceList);
	}

	@Override
	public List<BaseDistWaterPlan> selectiDList(List<String> waterAreaId) {
		return baseDistWaterPlanMapper.selectiDList(waterAreaId);
	}

	@Override
	public BaseDistWaterPlan selectYR(String distYear, String distRound) {
		// TODO Auto-generated method stub
		return baseDistWaterPlanMapper.selectYR(distYear, distRound);
	}

}