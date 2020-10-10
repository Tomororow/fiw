package com.fourfaith.sysManage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.sysManage.model.SysLog;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysLogService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PersonDateUtils;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;

/**   
 * @Title: Controller
 * @Description: 系统日志表
 * @author administrator
 * @date 2016-05-23 10:09:31
 * @version V1.0   
 */
@Controller
@RequestMapping(value ="/sysLog")
@SuppressWarnings("all")
public class SysLogController {

	protected static final String listJsp="/page/sysmanage/log/logIndex";

	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 查询列表用户
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(listJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		
		String query_beginTime = request.getParameter("query_beginTime");
		String query_endTime = request.getParameter("query_endTime");
		String query_keyword = request.getParameter("query_keyword");
		try {
			if(!StringUtils.isNullOrEmpty(query_keyword)){
				mav.addObject("query_keyword", query_keyword);
			}else{
				mav.addObject("query_keyword", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
    	Date beginTime = null;
    	Date endTime = null;
    	if(!StringUtils.isNullOrEmpty(query_beginTime)){
    		beginTime = PersonDateUtils.StringToDate(query_beginTime, "yyyy-MM-dd HH:mm:ss");
    	}
    	if(!StringUtils.isNullOrEmpty(query_endTime)){
    		endTime = PersonDateUtils.StringToDate(query_endTime, "yyyy-MM-dd HH:mm:ss");
    	}
    	if(!StringUtils.isNullOrEmpty(query_keyword)){
    		params.put("logcontent_fuzzy", query_keyword);
    	}
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		
		//分页
		String pageNo = request.getParameter("pageNo");
		   
		int count = this.sysLogService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysLog> logList = sysLogService.getList(params);
		
		query_beginTime = query_beginTime==null?"":query_beginTime;
		query_endTime = query_endTime==null?"":query_endTime;
		query_keyword = query_keyword==null?"":query_keyword;
		
		mav.addObject("query_beginTime", query_beginTime);
		mav.addObject("query_endTime", query_endTime);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("logList",logList);
		return mav;
	}
	
}