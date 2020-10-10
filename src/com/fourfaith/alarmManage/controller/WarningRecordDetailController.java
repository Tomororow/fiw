package com.fourfaith.alarmManage.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.alarmManage.model.WarningRecordDetail;
import com.fourfaith.alarmManage.model.WarningRecordDetailExcel;
import com.fourfaith.alarmManage.service.WarningRecordDetailService;
import com.fourfaith.basicInformation.model.SysServiceManInfo;
import com.fourfaith.basicInformation.service.SysServiceManInfoService;
import com.fourfaith.paramterManage.model.Warnerrortype;
import com.fourfaith.paramterManage.service.WarnerrortypeService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.ALiYunSSMUtils;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.EasyPoiUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PersonDateUtils;
@RequestMapping(value = "/warning_Record")
@Controller
public class WarningRecordDetailController {
	
	protected static final String deviceListJsp="/page/history/device/deviceList";//设备故障告警页面
	protected static final String intelligentDealListJsp = "/page/history/intelligentDeal/intelligentDealList";//已处理
	//protected static final String equipmentJSP = "/page/history/equipment/equiPment";//设备警告
	private List<WarningRecordDetail> warnConList = null;
	
	@Autowired
	private WarningRecordDetailService warningRecordDetailService;
	
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	
	@Autowired
	private WarnerrortypeService warnerrortypeService;
	
	@Autowired
	private SysServiceManInfoService sysServiceManInfoService;
	/**
	 * 此处查询预警信息(handleType:预警的状态,0:正在预警；1:已处理的预警)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/warnNoClose", method = RequestMethod.POST)
	public ModelAndView warnHandleNot(HttpServletRequest request){
		ModelAndView mav = null;
		int pageType = Integer.parseInt(request.getParameter("pageType"));
		String handleType = request.getParameter("handleType");
		if(pageType==0){
			mav = new ModelAndView(deviceListJsp);
		}else if(pageType==1){
			mav = new ModelAndView(intelligentDealListJsp);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> paramsAll = new HashMap<String, Object>();
		// 获取选中树菜单ID信息
		String nodeIds = request.getParameter("nodeIds");//行政区的树菜单Id集合
		try {
		List<String> areaList = null;
		if(StringUtils.isNotBlank(nodeIds)){//行政区的id集合
			areaList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}
		String deviceName = request.getParameter("deviceName");//机井名称
		String deviceCode = request.getParameter("deviceCode");//机井编码
		String alarmType = request.getParameter("alarmType");//预警类型
		String startTime = request.getParameter("startTime");//预警开始时间
		String endTime = request.getParameter("endTime");//预警结束时间
		
		SysUser sysUser = (SysUser) CommonUtil.getLoginUserInfo(request);
		if(sysUser!=null){
			if(sysUser.getAreaWay()==0){
				params.put("waterAreaList", areaList);
			}else{
				params.put("areaList", areaList);
			}
		}else{
			params.put("areaList", areaList);
		}
		params.put("deviceName", deviceName);
		params.put("deviceCode", deviceCode);
		params.put("alarmType", alarmType);
		if(StringUtils.isNotBlank(handleType)){
			params.put("handleType", handleType);
		}else{
			params.put("handleType", null);
		}
		if(StringUtils.isNotBlank(startTime)){
			params.put("startTime", startTime);
		}
		if(StringUtils.isNotBlank(endTime)){
			params.put("endTime",endTime);
		}
		paramsAll=params;
		List<WarningRecordDetail> warnListAll = warningRecordDetailService.selectWarnList(paramsAll);
		warnConList = warnListAll;//赋值给全局变量
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = 0;
		count = warningRecordDetailService.selectWarnCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取当前页  用于自增序列号
		int pageCurrent = pagingBean.getPageNo();
		mav.addObject("pagingBean", pagingBean);
		// 每页条数
		int pageSize = PagingBean.DEFAULT_PAGE_SIZE;
		List<WarningRecordDetail> warnList = warningRecordDetailService.selectWarnList(params);
		
		mav.addObject("warnList", warnList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 查询预警状态的总数
	 * @param state
	 * @return
	 */
	@RequestMapping(value="/warn_count",method = RequestMethod.POST)
	@ResponseBody
	public String warnCount(int state,HttpServletRequest request){
		int count = 0;
		String waterAreaId = "";
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		Map<String,Object> pageInfo = new HashMap<>();
		try {
			if(login_user!=null){
				waterAreaId = login_user.getWaterAreaId();
			}
			// 获取选择的水管区域树菜单id 得到所属的子区域并放入查询集合
			List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getCurrAndChildWaterArea(waterAreaId);
			List<String> waterAreaIdsList = new ArrayList<String>();
			for(SysWaterArea sysWaterArea : sysWaterAreaList){
				waterAreaIdsList.add(sysWaterArea.getId());
			}
			waterAreaIdsList.add(waterAreaId);
			count = warningRecordDetailService.selectByWarnCount(state,waterAreaIdsList);//已查询到预警的条数
			Calendar cal=Calendar.getInstance();
			int h=cal.get(Calendar.HOUR_OF_DAY);
			if(count>0){
				pageInfo.put("waterAreaList", sysInfoList(request));
				pageInfo.put("handleType","0");
				 List<WarningRecordDetail> warnList = warningRecordDetailService.selectWarnList(pageInfo);
				 if(warnList.size()>0){
					 for(WarningRecordDetail wrd:warnList){
						 Warnerrortype warnerrortype = warnerrortypeService.findByPrimaryCode(wrd.getWarnType());
						    if(warnerrortype!=null){
						    	if(warnerrortype.getIsmess()==1){//要发短信的
						    		WarningRecordDetail wrdt = warningRecordDetailService.selectMessTime(wrd.getDeviceCode(),wrd.getWarnType());
						    		if(h<=20&&h>=8){
						    		if(wrdt==null){
						    			messSend(request,wrd.getWarnType(),wrd);
						    		}else{
						    			long frt = PersonDateUtils.getDatePoor("hour", new Date(), wrdt.getWarnTime());
						    			if(PersonDateUtils.getDatePoor("hour", new Date(), wrdt.getWarnTime())>=1){
						    				messSend(request,wrdt.getWarnType(),wrdt);
						    				
						    			}
						    		}
						    	}
						    	}
						    }
					 }
					
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(count);
	}
	
	
	public void messSend(HttpServletRequest request,String code,WarningRecordDetail wrdt){
		Map<String,Object> pageInfo = new HashMap<>();
		pageInfo.put("code", code);
		pageInfo.put("waterIdList", sysInfoList(request));
		List<SysServiceManInfo> seList = sysServiceManInfoService.findMiddlePersonListMap(pageInfo);
		if(seList.size()>0){
			for(SysServiceManInfo sys:seList){
				if(StringUtils.isNotBlank(sys.getPhone())){
					JSONObject json = new JSONObject();
					json.put("deviceName", "【"+wrdt.getDeviceName()+"】");
					json.put("content", "【"+wrdt.getWarnDetail()+"】");
					new ALiYunSSMUtils().sendMesg(sys.getPhone(), json, "SMS_193240191");
					wrdt.setBackupOne("1");
					warningRecordDetailService.updateByPrimaryKeySelective(wrdt);
				}
			}
		}
	}
	
	/**
	 * 根据waterAreaId查询相关联系人
	 * @return
	 */
	public List<String> sysInfoList(HttpServletRequest request){
		List<String> sysWaterAreaLists = new ArrayList<>();
		Map<String,Object> pageInfo = new HashMap<>();
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		try {
			
			/*List<SysWaterArea> sysWateLists = sysWaterAreaService.getCurrAndChildWaterArea(login_user.getWaterAreaId());
			List<String> waterAreaIdsLists = new ArrayList<String>();
			for(SysWaterArea sysWaterArea : sysWateLists){
				sysWaterAreaLists.add(sysWaterArea.getId());
			}
			sysWaterAreaLists.add(login_user.getWaterAreaId());*/
			if(login_user!=null){
				List<SysWaterArea> sysWateLists = sysWaterAreaService.getCurrAndChildWaterArea(login_user.getWaterAreaId());
				
				List<String> waterAreaIdsLists = new ArrayList<String>();
				for(SysWaterArea sysWaterArea : sysWateLists){
					sysWaterAreaLists.add(sysWaterArea.getId());
				}
				sysWaterAreaLists.add(login_user.getWaterAreaId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysWaterAreaLists;
	}
	
	/*修改当前告警的状态值*/
	@RequestMapping(value="/handle_warn",method = RequestMethod.POST)
	@ResponseBody
	public String handleWarn(HttpServletRequest request,String id){
		Map<String, Object> params = new HashMap<String, Object>();
		int i  =0;
		try {
			SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
			WarningRecordDetail wrd = new WarningRecordDetail();
			if(StringUtils.isNotBlank(id)){
				wrd.setId(id);
				wrd.setWarnState(1);
				if(login_user!=null){
					wrd.setWarnHandler(login_user.getFullName());
				}
				wrd.setWarnHandleTime(new Date());
				i = warningRecordDetailService.updateByPrimaryKeySelective(wrd);
				int count = warningRecordDetailService.selectByWarnCount(0,null);
				params.put("warnCount", count);
				params.put("updateCount", i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(params);
	}
	/*数据导出*/
	@RequestMapping("/warn_excel")
	@ResponseBody
	public void processExcel(HttpServletResponse response,String handleType){
		List<WarningRecordDetailExcel> warList = new ArrayList<>();
		String warnState = "";
		/*反射机制*/
		EasyPoiUtil<WarningRecordDetailExcel> easyPoiUtil = new EasyPoiUtil<>();
		try {
			if(warnConList!=null){
				if(warnConList.size()>0){
					for(WarningRecordDetail wrd:warnConList){
						WarningRecordDetailExcel wrde = new WarningRecordDetailExcel();
						easyPoiUtil.t = wrde;
						wrde.setDeviceName(wrd.getDeviceName());
						wrde.setDeviceCode(wrd.getDeviceCode());
						wrde.setOwnerName(wrd.getOwnerName());
						wrde.setOwnerTelphone(wrd.getOwnerTelphone());
						wrde.setWabnormaltype(wrd.getWabnormaltype());
						wrde.setWarnDetail(wrd.getWarnDetail());
						wrde.setWarnTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(wrd.getWarnTime()));
						wrde.setWarnState(warnState=wrd.getWarnState()==0?"未处理的预警":"已处理的预警");
						if("0".equals(handleType)){
							easyPoiUtil.hihdColumn("warnHandler", true);
							easyPoiUtil.hihdColumn("warnHandleTime", true);
							easyPoiUtil.hihdColumn("cardCode", true);
						}else if("1".equals(handleType)){
							easyPoiUtil.hihdColumn("cardCode", true);
							wrde.setWarnHandler(wrd.getWarnHandler());
							wrde.setWarnHandleTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(wrd.getWarnHandleTime()));
						}
						warList.add(wrde);
					}
					ExportExcel.exportExcel(warList, "智能分析预警信息数据统计","智能分析预警信息数据统计",WarningRecordDetailExcel.class,"智能分析预警信息数据统计.xls",response );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
}
















