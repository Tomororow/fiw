package com.fourfaith.sysManage.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.BeanUtils;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;

/**   
 * @Title: Controller
 * @Description: 水管区域代码
 * @author Dan
 * @date 2016-10-08 21:36:40
 * @version V1.0
 */
@Controller
@RequestMapping(value ="/sysWaterArea")
public class SysWaterAreaController {
	
	protected static final String indexJsp="/page/sysmanage/sysWaterArea/sysWaterAreaIndex";
	protected static final String listJsp = "/page/sysmanage/sysWaterArea/list";
	protected static final String addJsp = "/page/sysmanage/sysWaterArea/add";
	protected static final String editJsp = "/page/sysmanage/sysWaterArea/edit";

	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;
	
	//以下参数为添加日志所需
    public String logContent = "";
	
    /**
     * @Title: index
     * @Description: 首页
     * @param: @param request
     * @return: ModelAndView
     */
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(indexJsp);
		return mav;
	}
	
	/**
	 * 新增水管区域页面
	 * @param request
	 */
	@RequestMapping(value="addPage")
	public ModelAndView addPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(addJsp);
		String parentWaterAreaId = request.getParameter("parentWaterAreaId");
		String waterAreaLevel = request.getParameter("waterAreaLevel");
		mav.addObject("parentWaterAreaId", parentWaterAreaId);
		mav.addObject("waterAreaLevel", waterAreaLevel);
		return mav;
	}
	
	/**
	 * 水管区域树
	 * @param request
	 */
	@RequestMapping(value="getAllArea")
	@ResponseBody
	public String getAllArea(HttpServletRequest request, boolean isSearchDevice, boolean showOnLineDevice){
		JSONArray jsonarray = new JSONArray();
		// 参数Map
		List<SysWaterArea> sysWaterAreaList = new ArrayList<SysWaterArea>();
		// 获取登录的用户  用于根据权限获取相应内容
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String waterAreaId = "";
		if(login_user!=null){
			waterAreaId = login_user.getWaterAreaId();
		}
		int count = 0;
		sysWaterAreaList = this.sysWaterAreaService.getCurrAndChildWaterAreas(waterAreaId);
		if(sysWaterAreaList!=null && sysWaterAreaList.size()>0){
			for(SysWaterArea sysWaterArea : sysWaterAreaList){
				JSONObject jsonObject = new JSONObject();
				if(StringUtils.isNullOrEmpty(sysWaterArea.getParentWaterAreaId())){
					//一级机构
					jsonObject.put("id", sysWaterArea.getId());
					jsonObject.put("pId",0);
					jsonObject.put("name", sysWaterArea.getWaterAreaName());
					jsonObject.put("areaLevel", sysWaterArea.getWaterAreaLevel());
					jsonObject.put("open", true);//展开
				}else{
					jsonObject.put("id", sysWaterArea.getId());
					jsonObject.put("pId", sysWaterArea.getParentWaterAreaId());
					jsonObject.put("name", sysWaterArea.getWaterAreaName());
					jsonObject.put("areaLevel", sysWaterArea.getWaterAreaLevel());
					if(count==0){
						jsonObject.put("open", true);//展开
					}
					count+=1;
				}
				jsonarray.add(jsonObject);
			}
		}
		return JSONObject.toJSONString(jsonarray);
	}
	
	/**
	 * 水管区域列表
	 * @param request
	 */
	@RequestMapping(value="list")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(listJsp);
		String id = request.getParameter("id");
		String searchWaterAreaCode = request.getParameter("searchWaterAreaCode");
		SysWaterArea sysWaterArea = sysWaterAreaService.findById(id);
		if(null==sysWaterArea){
			mav.addObject("firstAreaId",id);
			sysWaterArea = sysWaterAreaService.findFirstWaterAreaLevelById(id);
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("waterAreaId", id);
		if(!StringUtils.isNullOrEmpty(searchWaterAreaCode)) {
			params.put("searchWaterAreaCode", searchWaterAreaCode);
		}
		int count = sysWaterAreaService.getCount(params);
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = null;
		pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getList(params);
		sysWaterAreaList.add(0, sysWaterArea);
		mav.addObject("sysWaterAreaList", sysWaterAreaList);
		mav.addObject("waterAreaCode", searchWaterAreaCode);
		mav.addObject("pagingBean", pagingBean);
		return mav;
	}
	
	/**
	 * 保存区域
	 */
	@RequestMapping(value="addSysWaterArea")
	@ResponseBody
	public String addSysWaterArea(HttpServletRequest request,SysWaterArea sysWaterArea){
		sysWaterArea.setId(CommonUtil.getRandomUUID());
		sysWaterArea.setCreateTime(new Date());
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			String msg = sysWaterAreaService.add(sysWaterArea);
			logContent = "添加【"+sysWaterArea.getWaterAreaName()+"】的水管区域数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加水管区域，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加水管区域失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * 删除区域
	 */
	@RequestMapping(value="delSysWaterArea")
	@ResponseBody
	public String delSysWaterArea(HttpServletRequest request){
		logContent = "";
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = this.sysWaterAreaService.delSysWaterArea(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除水管区域，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除水管区域失败，请联系管理人员");
		}
		logContent = ajaxJson.getObj().toString();
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * 编辑区域页面
	 * @param request
	 */
	@RequestMapping(value="editPage")
	public ModelAndView editPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(editJsp);
		String id = request.getParameter("id");
		SysWaterArea sysWaterArea = this.sysWaterAreaService.findById(id);
		if(null==sysWaterArea) {
			sysWaterArea = sysWaterAreaService.findFirstWaterAreaLevelById(id);
		}
		mav.addObject("sysWaterArea", sysWaterArea);
		return mav;
	}
	
	/**
	 * 编辑区域
	 */
	@RequestMapping(value="editSysWaterArea")
	@ResponseBody
	public String editSysWaterArea(HttpServletRequest request,SysWaterArea sysWaterArea){
		AjaxJson ajaxJson = new AjaxJson();
		logContent = "";
		try{
			SysWaterArea newsysWaterArea = sysWaterAreaService.findById(sysWaterArea.getId());
			if(null==newsysWaterArea) 
				newsysWaterArea = sysWaterAreaService.findFirstWaterAreaLevelById(sysWaterArea.getId());
			newsysWaterArea.setEditTime(new Date());
			try {
				BeanUtils.copyPropertiesExcludeNull(sysWaterArea, newsysWaterArea);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			String msg = sysWaterAreaService.update(newsysWaterArea);
			logContent = "编辑【"+newsysWaterArea.getWaterAreaName()+"】的水管区域数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);

		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("更新水管区域，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("编辑水管区域失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * 检查区域下是否有子区域
	 * @return
	 */
	@RequestMapping(value="/checkChildAreaExist")
	@ResponseBody
    public String checkChildAreaExist(HttpServletRequest request, String id){
		AjaxJson ajaxJson = new AjaxJson();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		int count = sysWaterAreaService.getChildAreaList(params).size() - 1;
		if(count > 0){
			ajaxJson.setSuccess(true);
		}else{
			ajaxJson.setSuccess(false);
		}
		return JSONObject.toJSONString(ajaxJson);
    }
	
	/**
	 * 检验编码、名称唯一性
	 * @param request
	 * @param parentWaterAreaId
	 * @param waterAreaId
	 * @param waterAreaCode
	 * @param waterAreaName
	 * 2016年11月16日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/uniqueWaterArea")
	@ResponseBody
	public String uniqueWaterArea(HttpServletRequest request, String parentWaterAreaId, String waterAreaId, String waterAreaCode, String waterAreaName) {
		AjaxJson ajaxJson = new AjaxJson();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentWaterAreaId", parentWaterAreaId);
		params.put("waterAreaId", waterAreaId);
		params.put("waterAreaCode", waterAreaCode);
		params.put("waterAreaName", waterAreaName);
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.uniqueWaterArea(params);
		if(null==sysWaterAreaList || sysWaterAreaList.size()==0) {
			ajaxJson.setSuccess(true);
		} else {
			ajaxJson.setSuccess(false);
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
}






