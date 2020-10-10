package com.fourfaith.paramterManage.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.paramterManage.dao.ParamPumpingWellMapper;
import com.fourfaith.paramterManage.model.ParamPumpingWell;
import com.fourfaith.paramterManage.service.ParamPumpingWellService;
import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;

/**
 * @ClassName: ParamPumpingWellServiceImpl
 * @Description: 机井不在线天数service实现
 * @Author: zhaojx
 * @Date: 2017年5月13日 下午6:11:07
 */
@Service("ParamPumpingWellService")
public class ParamPumpingWellServiceImpl implements ParamPumpingWellService{
	
	protected Logger logger = Logger.getLogger(ParamPumpingWellServiceImpl.class);

	@Autowired
	private ParamPumpingWellMapper paramPumpingWellMapper;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;

	/**
	 * 获取功率记录条数
	 */
	public Integer getCount(Map<String, Object> params) {
		return paramPumpingWellMapper.getCount(params);
	}

	/**
	 * 获取功率信息集合
	 * @param params
	 */
	public List<ParamPumpingWell> getList(Map<String, Object> params) {
		return paramPumpingWellMapper.getList(params);
	}
	
	/**
	 * 新增异常机井参数设置
	 * @param paramPumpingWell
	 */
	public String add(String deviceWellUse,BigDecimal backone,int onnetstate,int waterlow,String waterAreaId,String baseDeviceId, String startTimes, String endTimes) {
		Map<String,Object> pamas = new HashMap<>();
		Date dateStartTime = null;
		try {
			dateStartTime = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTimes);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Date dateEndTime = null;
		try {
			dateEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimes);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String msg = null;
		int result = 0;
		if(StringUtils.isNotEmpty(waterAreaId)){
			String [] dtr = waterAreaId.split(",");
			waterAreaId = dtr[dtr.length-1];
		}
		try {
			//获取选择的水管区域树菜单id 得到所属的子区域并放入查询集合
			List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getCurrAndChildWaterArea(waterAreaId);
			List<String> waterAreaIdsList = new ArrayList<String>();
			for(SysWaterArea sysWaterArea : sysWaterAreaList){
				waterAreaIdsList.add(sysWaterArea.getId());
			}
			waterAreaIdsList.add(waterAreaId);
			pamas.put("list", waterAreaIdsList);pamas.put("welluse", deviceWellUse);pamas.put("baseDeviceId", baseDeviceId);
			//根据水管区id和水井用途查询符合条件的机井信息
			List<BaseDeviceInfo> baseDeviceList = baseDeviceInfoService.findSelectWaterIdBase(pamas);
			if(baseDeviceList.size()>0){
				for(BaseDeviceInfo base:baseDeviceList){
					ParamPumpingWell ppw = paramPumpingWellMapper.findParamPumpingWellsByDeviceCode(base.getDeviceCode());
					if(ppw==null){
						ParamPumpingWell paramPumpingWell = new ParamPumpingWell();
						paramPumpingWell.setDevicecode(base.getDeviceCode());
						paramPumpingWell.setBackone(backone==null?"0":backone.toString());
						paramPumpingWell.setOnnetstate(onnetstate);
						paramPumpingWell.setWaterlow(waterlow);
						paramPumpingWell.setStartTime(dateStartTime);
						paramPumpingWell.setEndTime(dateEndTime);
						paramPumpingWell.setWarningstate(0);
						paramPumpingWell.setCreatetime(new Date());
						int flag = paramPumpingWellMapper.insertSelective(paramPumpingWell);
						if (flag == 1) {
							result++;
						}
					}else{
						ppw.setOnnetstate(onnetstate);
						ppw.setBackone(backone==null?"0":backone.toString());
						ppw.setWaterlow(waterlow);
						ppw.setStartTime(dateStartTime);
						ppw.setEndTime(dateEndTime);
						ppw.setWarningstate(0);
						ppw.setEdittime(new Date());	
						int flag = paramPumpingWellMapper.updateByPrimaryKeySelective(ppw);
						if (flag == 1) {
							result++;
						}
					}
				}
			}
			msg = "添加成功"+ result +"条";
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}
	

	/**
	 * 根据Id删除异常机井智能参数设置信息
	 * @param ids
	 */
	public AjaxJson delete(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(ids!=null){
			String[] areaIdArray = ids.split(",");
			for (int i = 0; i < areaIdArray.length; i++) {
				logContent = logContent+"删除【异常机井参数】的数据"+",";
				paramPumpingWellMapper.delete(areaIdArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}

	/**
	 * 根据时间查询异常机井配置参数信息
	 * @param params
	 */
	public List<ParamPumpingWell> getAllList(Map<String, Object> params) {
		return paramPumpingWellMapper.getAllList(params);
	}

	/**
	 * 根据当前日期查询异常机井信息
	 * @param nowDate
	 */
	public List<ParamPumpingWell> selectParamPumpingWellInfoByNowDate(String nowDate) {
		return paramPumpingWellMapper.selectParamPumpingWellInfoByNowDate(nowDate);
	}

	@Override
	public ParamPumpingWell findPumpingWellById(String id) {
		// TODO Auto-generated method stub
		return paramPumpingWellMapper.findPumpingWellById(id);
	}

	@Override
	public int edit(ParamPumpingWell paramPumpingWell) {
		// TODO Auto-generated method stub
		return paramPumpingWellMapper.updateByPrimaryKeySelective(paramPumpingWell);
	}

	@Override
	public List<ParamPumpingWell> selectAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return paramPumpingWellMapper.selectAll(params);
	}

	@Override
	public ParamPumpingWell selecTimeMin() {
		// TODO Auto-generated method stub
		return paramPumpingWellMapper.selecTimeMin();
	}

	@Override
	public List<ParamPumpingWell> selectwatertime() {
		// TODO Auto-generated method stub
		return paramPumpingWellMapper.selectwatertime();
	}

	@Override
	public List<ParamPumpingWell> selectwaterpeckwater() {
		// TODO Auto-generated method stub
		return paramPumpingWellMapper.selectwaterpeckwater();
	}






	
	
	
}