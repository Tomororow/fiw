package com.fourfaith.statisticAnalysis.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.statisticAnalysis.model.RptBaseDistWaterDetail;
import com.fourfaith.statisticAnalysis.service.DistAppendWaterPlanInfoService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: DistAppendWaterPlanInfoController
 * @Description: 阶梯水量分配控制器
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午3:21:47
 */
@Controller
@RequestMapping(value = "/distAppendWaterInfo")
public class DistAppendWaterPlanInfoController {

	protected static final String distAppendWaterInfoJsp = "/page/statistic/distWaterInfo/distAppendWater";
	protected static final String distAppendWaterInfoListJsp = "/page/statistic/distWaterInfo/distAppendWaterList";
	@Autowired
	private SysWellUseService sysWellUseService;
	@Autowired
	private DistAppendWaterPlanInfoService distAppendWaterPlanInfoService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;

	/**
	 * 配水计划信息统计查询
	 * @param request
	 * @return 2016年11月7日 
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "/distAppendWaterInfo", method = RequestMethod.GET)
	public ModelAndView distAppendWaterInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(distAppendWaterInfoJsp);
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
	@RequestMapping(value = "/distAppendWaterList", method = RequestMethod.POST)
	public ModelAndView distAppendWaterList(HttpServletRequest request) {
		// 定位到配水计划信息列表页
		ModelAndView mav = new ModelAndView(distAppendWaterInfoListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		/**
		 * 查询条件
		 * 	wellUse : 用水类型
		 * 	distYearStart : 所属年份（查询开始时间）
		 * 	distYearEnd : 所属年份（查询结束时间）
		 */
		String wellUse = request.getParameter("wellUse");
		String distYearStart = request.getParameter("distYearStart");
		String distYearEnd = request.getParameter("distYearEnd");
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
		// 分页操作
		String pageNo = request.getParameter("pageNo");
		// 获取分页查询总记录数
		int count = distAppendWaterPlanInfoService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取配水统计信息分页列表
		List<RptBaseDistWaterDetail> distAppendWaterInfoList = distAppendWaterPlanInfoService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("distAppendWaterInfoList", distAppendWaterInfoList);
		mav.addObject("wellUse", wellUse);
		mav.addObject("distYearStart", distYearStart);
		mav.addObject("distYearEnd", distYearEnd);
		return mav;
	}
	
}