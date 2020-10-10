package com.fourfaith.statisticAnalysis.controller;

import java.math.BigDecimal;
import java.text.ParseException;
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
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.statisticAnalysis.model.PersonalWaterInfoExcel;
import com.fourfaith.statisticAnalysis.model.RptUseWaterDetail;
import com.fourfaith.statisticAnalysis.service.UseWaterService;
import com.fourfaith.utils.EasyPoiUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;

@Controller
@RequestMapping(value="/personalWaterInfo")
public class PersonalWaterInfoController {

	private static final String personalWaterIndex = "/page/statistic/personalWaterInfo/personalWaterInfo";
	private static final String personalWaterList = "/page/statistic/personalWaterInfo/personalWaterInfoList";
	
	
	private static List<RptUseWaterDetail> UseWaterList = new ArrayList<>();
	
	@Autowired
	private SysWellUseService sysWellUseService;
	
	@Autowired
	private UseWaterService useWaterService;
	
	/**
	 * 返回主页面
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/index")
	public ModelAndView index() throws ParseException{
		ModelAndView mav = new ModelAndView(personalWaterIndex);
		Map<String,Object> params = new HashMap<String,Object>();
		List<SysWellUse> list = sysWellUseService.getList(params);
		Calendar cal = Calendar.getInstance();  
		mav.addObject("wellList", list);
		String sTime = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE)+" 00:00:00";
		mav.addObject("sTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(sTime));
		mav.addObject("eTime", new Date());
		return mav;
	}
	
	/**
	 * 返回页面携带数据查询
	 * @return
	 */
	@RequestMapping(value="/waterInfoList", method = RequestMethod.POST)
	public ModelAndView waterInfoList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(personalWaterList);
		Map<String, Object> params = new HashMap<String, Object>();
		String areaList = request.getParameter("nodeIds");
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		String deviceWellUse = request.getParameter("deviceWellUse");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		try {
			// 水管区域节点  用于查询机井信息所归属
			List<String> areaIdsList = null;
			if(StringUtils.isNotBlank(areaList)){
				areaIdsList = new ArrayList<>(Arrays.asList(areaList.split(",")));
			}else{
				//无id，则置空
				areaIdsList = new ArrayList<>(Arrays.asList(""));
			}
			params.put("areaIdsList", areaIdsList);
			params.put("deviceCode",deviceCode);
			params.put("deviceName",deviceName);
			params.put("deviceWellUse", deviceWellUse);
			params.put("startTime", beginTime);
			params.put("endTime", endTime);
			params.put("waterAreaIdsList", null);
			// 分页
		    String pageNo = request.getParameter("pageNo");
		    List<RptUseWaterDetail> waterInfoList = new ArrayList<>();
		    int count = 0;
		    List<RptUseWaterDetail> countList = useWaterService.selectUseWaterInfoCount(params);
		    UseWaterList = countList;
		    count = countList.size();
		    PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
			params.put("pageStart", pagingBean.getStart());
			params.put("pageEnd", pagingBean.getLimit());
			mav.addObject("pagingBean", pagingBean);
			waterInfoList = useWaterService.selectUseWaterInfoList(params);
			mav.addObject("pagingBean", pagingBean);
			mav.addObject("wellUse", deviceWellUse);
			mav.addObject("waterInfoList", waterInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/personalWaterExcel")
	public void personalWaterExcel(HttpServletResponse response,String wellUse){
		List<PersonalWaterInfoExcel> pweList = new ArrayList<>();
		EasyPoiUtil<PersonalWaterInfoExcel> easyPoiUtil = new EasyPoiUtil<>();
		try {
			if(UseWaterList.size()>0){
				for(RptUseWaterDetail rwd:UseWaterList){
					PersonalWaterInfoExcel pwe = new PersonalWaterInfoExcel();
					easyPoiUtil.t = pwe;
					pwe.setDeviceName(rwd.getDeviceName());
					pwe.setDeviceCode(rwd.getDeviceCode());
					pwe.setUseWater(rwd.getUseWater().toString());
					pwe.setRemainWater(rwd.getRemainWater().toString());
					pwe.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rwd.getCreateTime()));
					pwe.setWellUse(rwd.getWellUse());
					pweList.add(pwe);
				}
				ExportExcel.exportExcel(pweList, "个人用水信息记录表", "个人用水信息", PersonalWaterInfoExcel.class, "个人用水信息记录表.xls", response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}













