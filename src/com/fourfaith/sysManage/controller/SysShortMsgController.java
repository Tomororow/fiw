package com.fourfaith.sysManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.sysManage.model.SysShortMsg;
import com.fourfaith.sysManage.service.SysShortMsgService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;

/**
 * @ClassName: ParamShortMsgController
 * @Description: 短信模板控制器
 * @Author: zhaojx
 * @Date: 2017年4月18日 上午11:02:46
 */
@Controller
@RequestMapping(value = "/sysShortMsg")
public class SysShortMsgController {

	// 页面静态参数
	private static final String LIST_JSP = "/page/sysmanage/sysShortMsg/list";
	private static final String ADD_JSP = "/page/sysmanage/sysShortMsg/add";
	private static final String EDIT_JSP = "/page/sysmanage/sysShortMsg/edit";
	
	@Autowired
	private SysShortMsgService sysShortMsgService;
	
	// 添加日志需要
	public String logContent = "";
	
	/**
	 * @Title: getMsgList
	 * @Description: 短信模板列表
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getMsgList(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView(LIST_JSP);
		Map<String, Object> params = new HashMap<String, Object>();
		// 获取当前页
		String pageNo = request.getParameter("pageNo");
		// 查询总记录数
		int count = sysShortMsgService.getCount(params);
		// 获取分页参数
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 查询总记录集合
		List<SysShortMsg> msgList = sysShortMsgService.getMsgList(params);
		modelAndView.addObject("pagingBean", pagingBean);
		modelAndView.addObject("msgList", msgList);
		return modelAndView;
	}
	
	/**
	 * @Title: addPage
	 * @Description: 跳转短信增加页面
	 * @param: @return
	 * @return: String
	 */
	@RequestMapping(value = "/addPage")
	public String addPage(){
		return ADD_JSP;
	}
	
	/**
	 * @Title: add
	 * @Description: 保存短信模板
	 * @param: @param request
	 * @param: @param SysShortMsg
	 * @return: String
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, SysShortMsg SysShortMsg){
		Map<String, Object> params = new HashMap<String, Object>();
		logContent = "";
		
	    try {
			String msg = sysShortMsgService.addMsg(SysShortMsg);
			params.put("success", true);
			params.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			params.put("success", false);
			params.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
	    return JSONObject.toJSONString(params);
	}
	
	/**
	 * @Title: delMsg
	 * @Description: 短信模板删除
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping(value="delMsg")
	@ResponseBody
	public String delMsg(HttpServletRequest request) {
		logContent = "";
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = sysShortMsgService.delMsg(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除短信模板，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除短信模板失败，请联系管理人员");
		}
		logContent = ajaxJson.getObj().toString();
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: editPage
	 * @Description: 跳转修改短信页面
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/editPage")
	public ModelAndView editPage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView(EDIT_JSP);
		String id = request.getParameter("id");
		
		SysShortMsg SysShortMsg = sysShortMsgService.getMsgById(id);
		if(SysShortMsg != null){
			modelAndView.addObject("SysShortMsg", SysShortMsg);
		}
		return modelAndView;
	}
	
	/**
	 * @Title: edit
	 * @Description: 修改短信模板
	 * @param: @param SysShortMsg
	 * @return: String
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(SysShortMsg SysShortMsg){
		Map<String, Object> params = new HashMap<String, Object>();
		logContent = "";
		
		try {
			String msg = sysShortMsgService.updateMsg(SysShortMsg);
			logContent = "修改【短信模板】的数据";
			params.put("success", true);
			params.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			params.put("success", false);
			params.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(params);
	}
	
	/**
	 * @Title: uniqueMsgCode
	 * @Description: 短信编码  唯一校验
	 * @param: @param msgCode
	 * @return: String
	 */
	@RequestMapping(value = "/uniqueMsgCode", method = RequestMethod.POST)
	@ResponseBody
	public String uniqueMsgCode(String msgCode){
		String msg = null;
		try {
			msg = sysShortMsgService.uniqueMsgCode(msgCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(msg);
	}
	
}