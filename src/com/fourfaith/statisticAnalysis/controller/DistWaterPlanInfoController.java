package com.fourfaith.statisticAnalysis.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.alarmManage.model.WarningRecordDetailExcel;
import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.statisticAnalysis.model.RptBaseDistWaterDetail;
import com.fourfaith.statisticAnalysis.service.DistWaterPlanInfoService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.EasyPoiUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: DistWaterPlanInfoController
 * @Description: 配水信息控制器
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午3:22:59
 */
@Controller
@RequestMapping(value = "/distWaterInfo")
public class DistWaterPlanInfoController {

	protected static final String distWaterInfoJsp = "/page/statistic/distWaterInfo/distWater";
	protected static final String distWaterInfoListJsp = "/page/statistic/distWaterInfo/distWaterList";
	@Autowired
	private SysWellUseService sysWellUseService;
	@Autowired
	private DistWaterPlanInfoService distWaterPlanInfoService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	
	//定义机井导出的list
	List<RptBaseDistWaterDetail> exportlist = new ArrayList();

	/**
	 * 配水计划信息统计查询
	 * @param request
	 * @return 2016年11月7日
	 */
	@RequestMapping(value = "/distWaterInfo", method = RequestMethod.GET)
	public ModelAndView distWaterInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(distWaterInfoJsp);
		Map<String,Object> params = new HashMap<String,Object>();
		List<SysWellUse> list = sysWellUseService.getList(params);
		Date distYearStart = new Date();
		Calendar c = new GregorianCalendar(); 
		c.setTime(distYearStart); 
		c.add(Calendar.YEAR,-1);
		distYearStart=c.getTime();
		mav.addObject("distYearStart", distYearStart);
		mav.addObject("distYearEnd", new Date());
		mav.addObject("wellList", list);
		return mav;
	}

	/**
	 * 跳转到配水计划信息查询列表
	 * @param request
	 * 2016年11月7日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "/distWaterList", method = RequestMethod.POST)
	public ModelAndView distWaterList(HttpServletRequest request) {
		// 定位到配水计划信息列表页
		ModelAndView mav = new ModelAndView(distWaterInfoListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> paramsAll = new HashMap<String, Object>();
		
		/**
		 * 查询条件
		 * 	wellUse : 用水类型
		 * 	distYearStart : 所属年份（查询开始时间）
		 * 	distYearEnd : 所属年份（查询结束时间）
		 */
		String wellUse = request.getParameter("wellUse");
		String distYearStart = request.getParameter("distYearStart");
		String distYearEnd = request.getParameter("distYearEnd");
		//配水总量
		
		//操作员
		
		// 获取左边树形菜单栏的节点，并搜索该节点以及所有子节点信息
		String waterAreaId = request.getParameter("id");
		if(null==waterAreaId || "".equals(waterAreaId)) {
			SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
			waterAreaId = login_user.getWaterAreaId();
		}
		// 获取所有水管区域信息集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 调用递归方法，获取当前水管区域和子节点区域的所有信息集合
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
		// 将递归得到的所有水管区域的id保存到集合中
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		// 用水类型
		if(null!=wellUse && !"".equals(wellUse)) {
			params.put("wellUse", wellUse);
		} else {
			params.put("wellUse", null);
		}
		// 所属年份(查询开始时间)
		if(null!=distYearStart && !"".equals(distYearStart)) {
			params.put("distYearStart", distYearStart);
		} else {
			params.put("distYearStart", null);
		}
		// 所属年份（查询结束时间）
		if(null!=distYearEnd && !"".equals(distYearEnd)) {
			params.put("distYearEnd", distYearEnd);
		} else {
			params.put("distYearEnd", null);
		}
		//配水信息的导出
		paramsAll=params;
		exportlist=distWaterPlanInfoService.getList(paramsAll);
		// 分页操作
		String pageNo = request.getParameter("pageNo");
		// 获取分页查询总记录数
		int count = distWaterPlanInfoService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取配水统计信息分页列表
		List<RptBaseDistWaterDetail> distWaterInfoList = distWaterPlanInfoService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("distWaterInfoList", distWaterInfoList);
		mav.addObject("wellUse", wellUse);
		mav.addObject("distYearStart", distYearStart);
		mav.addObject("distYearEnd", distYearEnd);
		return mav;
	}
	/*配水信息导出的方法
	 * 
	 */
	@RequestMapping(value="/exportdistribution")
	public void exportSaleWater(HttpServletResponse response,HttpServletRequest request,String wellUse){
		/*反射机制*/
		EasyPoiUtil<RptBaseDistWaterDetail> easyPoiUtil = new EasyPoiUtil<>();
		//接受获取的数据、列（将要导出list）
		List<RptBaseDistWaterDetail> waterplaneList = new ArrayList<>();
		try{
			if(exportlist!=null){
				if(exportlist.size()>0){
					for (RptBaseDistWaterDetail waterplane : exportlist) {
						RptBaseDistWaterDetail rpt = new RptBaseDistWaterDetail();
						easyPoiUtil.t = rpt;
						rpt.setWaterAreaName(waterplane.getWaterAreaName());//配水区域
						//rpt.setDistMode(waterplane.getDistMode()); //配水名称
						rpt.setDistModename(waterplane.getDistMode()==1?"实际灌溉面积配水":waterplane.getDistMode()==2?"额定灌溉面积配水":waterplane.getDistMode()==3?"人口配水":"--");
						rpt.setDistYear(waterplane.getDistYear());//配水年份
						rpt.setDistRound(waterplane.getDistRound());//配水轮次
						
						//rpt.setDistType(waterplane.getDistType()==1?"农业灌溉用水":waterplane.getDistType()==2?"工业用水":waterplane.getDistType()==3?"生活用水":"--");//用水类型
						rpt.setDistTypename(waterplane.getDistType()==1?"农业灌溉用水":waterplane.getDistType()==2?"工业用水":waterplane.getDistType()==3?"生活用水":"--");
						rpt.setsJArea(waterplane.getsJArea());//实际灌溉面积
						rpt.setDistWater(waterplane.getDistWater());//每亩地分配水量
						rpt.setSumWater((waterplane.getsJArea()!=null?waterplane.getsJArea():new BigDecimal(0)).multiply(waterplane.getDistWater())); //灌溉配水总量
						rpt.setDistPrice(waterplane.getDistPrice());//配水价格
						rpt.setCreateTime(waterplane.getCreateTime());//配水时间
						rpt.setUserName(waterplane.getUserName()==null?"--":waterplane.getUserName()) ;//操作人员
						rpt.setsJSupplyWaterPeople(waterplane.getsJSupplyWaterPeople());//人口
						BigDecimal big = new BigDecimal(waterplane.getsJSupplyWaterPeople()!=null?waterplane.getsJSupplyWaterPeople():0);
						rpt.setIndustryApprovedWater(waterplane.getIndustryApprovedWater()); //年核定水量
						rpt.setPeopleWater(waterplane.getDistWater());//人均配水量
						rpt.setPeoplesumWater(big.multiply(waterplane.getDistWater()));//生活总水量
						if(wellUse.equals("灌溉")||wellUse.equals("绿化")){
							easyPoiUtil.hihdColumn("sJSupplyWaterPeople", true);
							easyPoiUtil.hihdColumn("industryApprovedWater", true);
							easyPoiUtil.hihdColumn("peoplesumWater", true);
							easyPoiUtil.hihdColumn("peopleWater", true);
							easyPoiUtil.hihdColumn("sJArea", false);
							easyPoiUtil.hihdColumn("distWater", false);
							easyPoiUtil.hihdColumn("sumWater", false);
							
							easyPoiUtil.hihdColumn("waterAreaName", false);
							easyPoiUtil.hihdColumn("distModename", false);
							easyPoiUtil.hihdColumn("distYear", false);
							easyPoiUtil.hihdColumn("distRound", false);
							easyPoiUtil.hihdColumn("distTypename", false);
							easyPoiUtil.hihdColumn("distPrice", false);
							easyPoiUtil.hihdColumn("createTime", false);
							easyPoiUtil.hihdColumn("userName", false);
							
							
						}else if(wellUse.equals("生活")){
							easyPoiUtil.hihdColumn("sJSupplyWaterPeople", false);
							easyPoiUtil.hihdColumn("industryApprovedWater", false);
							easyPoiUtil.hihdColumn("peoplesumWater", false);
							easyPoiUtil.hihdColumn("waterAreaName", false);
							easyPoiUtil.hihdColumn("distModename", false);
							easyPoiUtil.hihdColumn("distYear", false);
							easyPoiUtil.hihdColumn("distRound", false);
							easyPoiUtil.hihdColumn("distTypename", false);
							easyPoiUtil.hihdColumn("distPrice", false);
							easyPoiUtil.hihdColumn("createTime", false);
							easyPoiUtil.hihdColumn("userName", false);
							
							easyPoiUtil.hihdColumn("sJArea", true);
							easyPoiUtil.hihdColumn("industryApprovedWater", true);
							easyPoiUtil.hihdColumn("sumWater", true);
							easyPoiUtil.hihdColumn("distWater", true);
							
						}else if(wellUse.equals("工业")){
							easyPoiUtil.hihdColumn("sJArea", true);
							easyPoiUtil.hihdColumn("sumWater", true);
							easyPoiUtil.hihdColumn("distWater", true);
							easyPoiUtil.hihdColumn("peoplesumWater", true);
							easyPoiUtil.hihdColumn("peopleWater", true);
							easyPoiUtil.hihdColumn("waterAreaName", false);
							easyPoiUtil.hihdColumn("distModename", false);
							easyPoiUtil.hihdColumn("distYear", false);
							easyPoiUtil.hihdColumn("distRound", false);
							easyPoiUtil.hihdColumn("distTypename", false);
							easyPoiUtil.hihdColumn("distPrice", false);
							easyPoiUtil.hihdColumn("createTime", false);
							easyPoiUtil.hihdColumn("userName", false);
							easyPoiUtil.hihdColumn("industryApprovedWater", false);
						}else{
							easyPoiUtil.hihdColumn("sJSupplyWaterPeople", true);
							easyPoiUtil.hihdColumn("industryApprovedWater", true);
							easyPoiUtil.hihdColumn("peoplesumWater", true);
							easyPoiUtil.hihdColumn("peopleWater", true);
						}
						waterplaneList.add(rpt);
					}
				}}
		ExportExcel.exportExcel(waterplaneList, "配水信息表", "配水信息表", RptBaseDistWaterDetail.class, "配水信息表.xls", response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}