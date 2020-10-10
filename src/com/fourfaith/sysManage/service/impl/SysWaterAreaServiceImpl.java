package com.fourfaith.sysManage.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.sysManage.dao.SysWaterAreaMapper;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.WaterAreaRecursionUtil;
import com.google.gson.Gson;

/**
 * @ClassName: SysWaterAreaServiceImpl
 * @Description: 水管区域service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:44:18
 */
@Service("SysWaterAreaService")
public class SysWaterAreaServiceImpl implements SysWaterAreaService {
	
	protected Logger logger = Logger.getLogger(SysWaterAreaServiceImpl.class);
	
	@Autowired
	private SysWaterAreaMapper sysWaterAreaMapper;

	@Override
	public List<SysWaterArea> getLists(Map<String, Object> params) {
		return sysWaterAreaMapper.getLists(params);
	}
	
	@Override
	public List<SysWaterArea> getList(Map<String, Object> params) {
		return sysWaterAreaMapper.getList(params);
	}
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysWaterAreaMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysWaterArea> getChildAreaList(Map<String, Object> params) {
		List<String> idList = new ArrayList<String>();
		String id = params.get("id").toString();
		idList.add(id);
		List<SysWaterArea> sysAllWaterAreaList =sysWaterAreaMapper.getList(null);
	    List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, id);
		return sysWaterAreaList;
	}

	@Override
	public String add(SysWaterArea SysWaterArea) {
		String msg = null;
		try {
			int result = sysWaterAreaMapper.insertArea(SysWaterArea);
			if(result>0){
				msg = "添加成功";
			}else{
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

	@Override
	@Transactional(rollbackFor=Exception.class)
	public AjaxJson delSysWaterArea(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(ids!=null){
			String[] areaIdArray = ids.split(",");
			for (int i = 0; i < areaIdArray.length; i++) {
				SysWaterArea SysWaterArea = findById(areaIdArray[i]);
				logContent = logContent+"删除【"+SysWaterArea.getWaterAreaName()+"】的水管区域数据"+",";
				delete(areaIdArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}
	
	@Override
	public SysWaterArea findById(String id) {
		return sysWaterAreaMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysWaterAreaMapper.deleteByPrimaryKey(id);
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

	@Override
	public String update(SysWaterArea SysWaterArea) {
		String msg = null;
		try {
			int result = sysWaterAreaMapper.updateByPrimaryKeySelective(SysWaterArea);
			if(result>0){
			   msg = "更新成功";
			}else{
			  msg = "更新失败";
			}
			logger.info(msg);			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "更新失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	/**
	 * 获取水管区域子区域级联操作
	 * @param sysWaterArea
	 * 2016年10月16日
	 */
	public String getChildAreaInfo(SysWaterArea sysWaterArea) {
		String childAreaInfo = null;
		List<SysWaterArea> childAreaList = sysWaterAreaMapper.getChildAreaInfo(sysWaterArea);
		Gson gson = new Gson();
		childAreaInfo = gson.toJson(childAreaList);
		return childAreaInfo;
	}

	/**
	 * 获取第一级水管区域信息
	 * @param object
	 */
	public List<SysWaterArea> getFirstLevelList(Object object) {
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaMapper.getFirstLevelList();
		return sysWaterAreaList;
	}

	/**
	 * 根据已选择的水管区域，获取到所有的水管区域的waterAreaCode值
	 * @param sysWaterArea
	 * 2016年10月16日
	 */
	public String getWaterAreaCode(SysWaterArea sysWaterArea) {
		SysWaterArea swa = new SysWaterArea();
		swa.setId(sysWaterArea.getWaterAreaIds().split(",")[sysWaterArea.getWaterAreaIds().split(",").length-1]);
		swa = sysWaterAreaMapper.getWaterAreaCode(swa);
		return swa.getWaterAreaCode();
	}

	/**
	 * 获取登陆人员所在的区域做级联下拉框操作
	 * @param login_user
	 * 2016年10月25日
	 */
	public List<SysWaterArea> getLoginerWaterAreaList(SysUser login_user) {
		return sysWaterAreaMapper.getLoginerWaterAreaList(login_user);
	}

	/**
	 * 查询所有的水管区域
	 * 2016年10月25日
	 */
	public List<SysWaterArea> getAllWaterAreaList() {
		return sysWaterAreaMapper.getAllWaterAreaList();
	}

	/**
	 * 根据登陆用户所在的水管区域ID，获取到当前所属水管区域集合
	 * @param waterAreaId
	 * 2016年10月25日
	 */
	public List<SysWaterArea> getCurrWaterAreaList(String waterAreaId) {
		return sysWaterAreaMapper.getCurrWaterAreaList(waterAreaId);
	}

	/**
	 * 根据Id查询第一级水管编码信息
	 * @param id
	 * 2016年11月1日
	 */
	public SysWaterArea findFirstWaterAreaLevelById(String id) {
		return sysWaterAreaMapper.findFirstWaterAreaLevelById(id);
	}
	
	/**
	 * 根据当前所属水管区域Id获取当前和子节点水管区域Id
	 * @param waterAreaId
	 * 2016年11月4日
	 */
	public List<SysWaterArea> getCurrAndChildWaterArea(String waterAreaId) {
		// 初始化所属区域权限列表
		// 根据登陆人员所属区域Id，获取到当前所属区域信息
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaMapper.getCurrAndChildWaterArea(waterAreaId);
		// 获取到所有地区信息
		List<SysWaterArea> sysAllWaterAreaList = getAllWaterAreaList();
		// 当所有地区都为空时，则返回null
		if(sysAllWaterAreaList == null && sysWaterAreaList.get(0).getId() == null) return null;
		// 递归获取所属当前区域的子节点区域
		List<SysWaterArea> sysWaterAreaCurrAndChildList = 
				WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, sysWaterAreaList.get(0).getId());
		return sysWaterAreaCurrAndChildList;
	}
	
	/**
	 * 根据用户所属最低一级水管区域查询本区域及同级所有区域
	 * @param sysWaterArea
	 * 2016年11月5日
	 */
	public List<SysWaterArea> getWaterAreaByIdAndLevel(SysWaterArea sysWaterArea) {
		return sysWaterAreaMapper.getWaterAreaByIdAndLevel(sysWaterArea);
	}

	/**
	 * 根据Id查询水管区域信息
	 * @param sysWaterArea
	 * 2016年11月5日
	 */
	public SysWaterArea getWaterAreaById(SysWaterArea sysWaterArea) {
		return sysWaterAreaMapper.getWaterAreaById(sysWaterArea);
	}

	/**
	 * 检验编码、名称唯一性
	 * @param params
	 * 2016年11月16日
	 */
	public List<SysWaterArea> uniqueWaterArea(Map<String, Object> params) {
		return sysWaterAreaMapper.uniqueWaterArea(params);
	}

	/**
	 * 根据当前登录用户获取子区域及权限区域
	 */
	@Override
	public List<SysWaterArea> getCurrAndChildWaterAreas(String waterAreaId) {
		// 根据当前登录用户水管区域id，获取所属区域信息
		List<SysWaterArea> waterAreaList = sysWaterAreaMapper.getCurrWaterAreaList(waterAreaId);
		
		// 获取所有水管区域信息
		List<SysWaterArea> allWaterAreaList = getAllWaterArea();
		// 当所有水管区域信息都为空时，则返回null
		if(allWaterAreaList == null && waterAreaList.get(0).getId() == null) return null;
		// 递归获取所属当前水管区域的子节点区域
		List<SysWaterArea> waterAreaCurrAndChildList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(allWaterAreaList, waterAreaList.get(0).getId());
		return waterAreaCurrAndChildList;
	}

	@Override
	public List<SysWaterArea> getAllWaterArea() {
		return sysWaterAreaMapper.getAllWaterArea();
	}

	@Override
	public List<SysWaterArea> selectChildArea(String id) {
		return sysWaterAreaMapper.selectChildArea(id);
	}

	@Override
	public SysWaterArea getWaterAreaCode(String deviceCode) {
		return sysWaterAreaMapper.getWaterAreaCodes(deviceCode);
	}

	@Override
	public SysWaterArea selectCode(String waterAreaCode) {
		return sysWaterAreaMapper.selectCode(waterAreaCode);
	}
	
	@Override
	public SysWaterArea selectWaterLevel(String waterId) {
		return sysWaterAreaMapper.selectWaterLevel(waterId);
	}

	@Override
	public List<SysWaterArea> selectWaterAreaList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sysWaterAreaMapper.selectWaterAreaList(params);
	}
	
}