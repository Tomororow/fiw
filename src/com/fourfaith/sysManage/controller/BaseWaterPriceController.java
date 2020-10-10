package com.fourfaith.sysManage.controller;

import javax.servlet.http.HttpServletRequest;

import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.sysManage.model.BaseWaterPrice;
import com.fourfaith.sysManage.service.BaseWaterPriceService;

/**   
 * @Title: Controller
 * @Description: 水价参数设置
 * @author hong
 * @date 2016-09-21 14:53:52
 * @version V1.0   
 */
@Controller
@RequestMapping(value ="/waterPrive")
public class BaseWaterPriceController {
	
	protected static final String indexJsp="/page/sysmanage/basewaterprice/baseWaterPriceIndex";
	protected static final String addJsp="/page/sysmanage/basewaterprice/settingParam";
	
	@Autowired
	private BaseWaterPriceService baseWaterPriceService;
	
	//以下参数为添加日志所需
    public String logContent = "";
	
    /**
     * @Title: index
     * @Description: 首页
     * @param: @param request
     * @param: @param baseWaterPrice
     * @return: ModelAndView
     */
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request,BaseWaterPrice baseWaterPrice){
		ModelAndView mav = new ModelAndView(indexJsp);
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		mav.addObject("login_user",login_user);
		/*mav*/
		return mav;
	}
	
}