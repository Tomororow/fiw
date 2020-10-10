package com.fourfaith.siteManage.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.BeanUtils;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;

/**   
 * @Title: Controller
 * @Description: 区域代码
 * @author Dan
 * @date 2016-08-02 22:52:40
 * @version V1.0
 */
@Controller
@RequestMapping(value ="/sysArea")
public class SysAreaController {
	
	protected static final String indexJsp="/page/sysmanage/sysarea/sysAreaIndex";
	protected static final String listJsp = "/page/sysmanage/sysarea/list";
	protected static final String addJsp = "/page/sysmanage/sysarea/add";
	protected static final String editJsp = "/page/sysmanage/sysarea/edit";

	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;
	//或水管区
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	//以下参数为添加日志所需
    public String logContent = "";
    
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(indexJsp);
		return mav;
	}
	
	/**
	 * 新增区域页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addPage")
	public ModelAndView addPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(addJsp);
		
		//获取所有的水管区
		List<SysWaterArea> allWaterAreaList = sysWaterAreaService.getFirstLevelList(new Object());
		
		String parentAreaId = request.getParameter("parentAreaId");
		String areaLevel = request.getParameter("areaLevel");
		mav.addObject("parentAreaId", parentAreaId);
		mav.addObject("areaLevel", areaLevel);
		mav.addObject("sysWaterAreaList", allWaterAreaList);
		return mav;
	}
	
	/**
	 * 区域树
	 * @param request
	 * @param isSearchDevice 是否需要查询设备子节点
	 * @param showOnLineDevice 是否查询在线设备
	 */
	/*List<SysArea> sysAreaList = new ArrayList<SysArea>();
	@RequestMapping(value="getAllArea")
	@ResponseBody
	public String getAllArea(HttpServletRequest request,boolean isSearchDevice, boolean showOnLineDevice){
		// 获取登陆用户
		SysUser sysUser = (SysUser) CommonUtil.getLoginUserInfo(request);
		JSONArray jsonarray = new JSONArray();
		if(sysUser!=null){
			String areaId = sysUser.getAreaId();
			// 根据用户所在的areaId获取当前及自权限区域
			if(sysAreaList.size()==0){
				sysAreaList = sysAreaService.getCurrAndChildArea(areaId);
			}
			if(sysAreaList!=null){
				if(sysAreaList.size()>0){
					for(SysArea sysArea:sysAreaList){
						JSONObject jsonObject = new JSONObject();
						if(StringUtils.isEmpty(sysArea.getParentAreaId())){
							//一级机构
							jsonObject.put("id", sysArea.getId());
							jsonObject.put("pId", 0);
							jsonObject.put("name", sysArea.getAreaName());
							jsonObject.put("areaLevel", sysArea.getAreaLevel());
							jsonObject.put("open", true);//展开
						}else{
							jsonObject.put("id", sysArea.getId());
							jsonObject.put("pId", sysArea.getParentAreaId());
							jsonObject.put("name", sysArea.getAreaName());
							jsonObject.put("areaLevel", sysArea.getAreaLevel());
						}
						jsonarray.add(jsonObject);
					}
				}
		}
		return JSONObject.toJSONString(jsonarray);
	}*/
	/**
	 * 行政区域树结构
	 * @param request
	 * @param isSearchDevice 是否需要查询设备子节点
	 * @param showOnLineDevice 是否查询在线设备
	 */
	@RequestMapping(value="getAllArea")
	@ResponseBody
	public String getAllArea(HttpServletRequest request,boolean isSearchDevice, boolean showOnLineDevice){
		List<SysArea> sysAreaList = new ArrayList<SysArea>();
		List<SysArea> sysAreaListAll = new ArrayList<SysArea>();
		HttpSession session = request.getSession();
		try {
			// 获取登陆用户
			SysUser sysUser = (SysUser) CommonUtil.getLoginUserInfo(request);
				if(sysUser!=null){
					SysArea sysArea = sysAreaService.findFirstAreaLevelById(sysUser.getAreaId());
					if(sysArea!=null){
						sysArea.setOpen(true);
						sysAreaList.add(sysArea);
					}
					if(sysAreaListAll.size()==0){
						sysAreaListAll = sysAreaService.findLevelSyaAreaCodeList(sysArea.getAreaLevel());
						if(sysAreaListAll.size()>0){
							session.setAttribute("areaList", sysAreaListAll);
						}
					}
					sysAreaListAll = (List<SysArea>)session.getAttribute("areaList");
					for(SysArea sa:sysAreaList){
						sa.setName(sa.getAreaName());
						//sa.setOpen(true);
						sa.setChildren(waterAreaResource(sa.getId(),sysAreaListAll));
					}
					
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(sysAreaList);
	}
	/**
	 * 行政区域树结构的调用方法
	 * @param id
	 * @param menuList
	 * @return
	 */
	public List<SysArea> waterAreaResource(String id,List<SysArea> menuList){
		List<SysArea> meyList = new ArrayList<>();
		if(menuList.size()>0){
			for(SysArea sw:menuList){
				if(id.equals(sw.getParentAreaId())){
					sw.setName(sw.getAreaName());
					meyList.add(sw);
				}
			}
		}
		if(meyList.size()>0){
			for(SysArea sw:meyList){
				sw.setChildren(waterAreaResource(sw.getId(),menuList));
			}
		}
		return meyList;
	}
	/**
	 * 区域列表
	 * @param request
	 */
	@RequestMapping(value="list")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(listJsp);
		String areaId = request.getParameter("id");
		SysArea sysArea = sysAreaService.findById(areaId);
		if(null==sysArea){
			mav.addObject("firstAreaId",areaId);
			sysArea = sysAreaService.findFirstAreaLevelById(areaId);
		}
		/**
		 * date：2016-10-30做出如下修改：
		 * 	1、查询所有的子节点 ---> 改为查询当前节点下的下一级子节点即可
		 * 	2、将方法中的递归方法改为查询当前节点下的下一节点即可
		 */
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("areaId", areaId);
		int count = sysAreaService.getCount(params);
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = null;
		pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count,PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 分页查询，查询当前节点的下一级子节点信息
		List<SysArea> sysAreaListInfo = sysAreaService.getList(params);
		sysAreaListInfo.add(0, sysArea);
		mav.addObject("sysAreaList", sysAreaListInfo);
		mav.addObject("pagingBean", pagingBean);
		return mav;
	}
	
	/**
	 * @Title: addSysArea
	 * @Description: 添加保存区域信息
	 * @param: @param request
	 * @param: @param sysArea
	 * @return: String
	 */
	@RequestMapping(value="addSysArea")
	@ResponseBody
	public String addSysArea(HttpServletRequest request,SysArea sysArea){
		//获取水管区的id
		sysArea.setId(CommonUtil.getRandomUUID());
		sysArea.setCreateTime(new Date());
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			String msg = sysAreaService.add(sysArea);
			logContent = "添加【"+sysArea.getAreaName()+"】的区域数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
			
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加区域，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加区域失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * 删除区域
	 */
	@RequestMapping(value="delSysArea")
	@ResponseBody
	public String delSysArea(HttpServletRequest request){
		logContent = "";
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = sysAreaService.delSysArea(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除区域，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除区域失败，请联系管理人员");
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
		SysArea sysArea = sysAreaService.findById(id);
		if(null==sysArea || null==sysArea.getId()) {
			sysArea = sysAreaService.getCurrAndChildArea(id).get(0);
		}
		mav.addObject("sysArea", sysArea);
		mav.addObject("parentAreaId", sysArea.getParentAreaId());
		mav.addObject("areaLevel", sysArea.getAreaLevel());
		return mav;
	}
	
	/**
	 * 编辑区域
	 */
	@RequestMapping(value="editSysArea")
	@ResponseBody
	public String editSysArea(HttpServletRequest request,SysArea sysArea){
		AjaxJson ajaxJson = new AjaxJson();
		logContent = "";
		try{
			SysArea newSysArea = sysAreaService.findById(sysArea.getId());
			if(null==newSysArea || null==newSysArea.getId())
				newSysArea = sysAreaService.getCurrAndChildArea(sysArea.getId()).get(0);
			newSysArea.setEditTime(new Date());
			try {
				BeanUtils.copyPropertiesExcludeNull(sysArea, newSysArea);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			String msg = sysAreaService.update(newSysArea);
			logContent = "编辑【"+newSysArea.getAreaName()+"】的区域数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("更新区域，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("编辑区域失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * 检查区域下是否有机井设备
	 * @return
	 */
	@RequestMapping(value="/checkDeviceExist")
	@ResponseBody
    public String checkDeviceExist(HttpServletRequest request,String areaId){
		AjaxJson ajaxJson = new AjaxJson();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("areaId", areaId);
		int count = baseDeviceInfoService.getCount(params);
		if(count > 0){
			ajaxJson.setSuccess(true);
		}else{
			ajaxJson.setSuccess(false);
		}
		return JSONObject.toJSONString(ajaxJson);
    }
	
	/**
	 * 检查区域下是否有子区域
	 * @return
	 */
	@RequestMapping(value="/checkChildAreaExist")
	@ResponseBody
    public String checkChildAreaExist(HttpServletRequest request,String areaId){
		AjaxJson ajaxJson = new AjaxJson();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("areaId", areaId);
		int count = sysAreaService.getChildArea(areaId).size();
		if(count > 0){
			ajaxJson.setSuccess(true);
		}else{
			ajaxJson.setSuccess(false);
		}
		return JSONObject.toJSONString(ajaxJson);
    }
	
	/**
	 * TODO: 检查名称唯一性
	 * @param request
	 * @param areaCode
	 * @param areaName
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/uniqueArea")
	@ResponseBody
	public String uniqueArea(HttpServletRequest request,String parentAreaId, String areaId, String areaCode, String areaName) {
		AjaxJson ajaxJson = new AjaxJson();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentAreaId", parentAreaId);
		params.put("areaId", areaId);
		params.put("areaCode", areaCode);
		params.put("areaName", areaName);
		List<SysArea> sysAreaList = sysAreaService.uniqueArea(params);
		if(null==sysAreaList || sysAreaList.size()==0) {
			ajaxJson.setSuccess(true);
		} else {
			ajaxJson.setSuccess(false);
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
}