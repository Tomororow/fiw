package com.fourfaith.statisticAnalysis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 水权交易统计信息控制器
 * @author Administrator
 * 2016年11月20日
 */
@Controller
@RequestMapping(value = "/waterRightInfo")
public class WaterRightInfoController {

	protected static final String waterRightInfoJsp = "/page/statistic/waterRight/waterRight";
	protected static final String waterRightInfoListJsp = "/page/statistic/waterRight/waterRightList";
	
	/**
	 * 水权交易头部
	 * @param request
	 * 2016年11月20日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/waterRightInfo", method = RequestMethod.GET)
	public ModelAndView waterRightInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(waterRightInfoJsp);
		return mav;
	}
	
	/**
	 * 水权交易统计报表页
	 * @param request
	 * 2016年11月20日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/waterRightInfoList", method = RequestMethod.POST)
	public ModelAndView waterRightInfoList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(waterRightInfoListJsp);
		return mav;
	}
	
}