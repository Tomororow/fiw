package com.fourfaith.baseManager.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.alarmManage.service.IntelligentAnalysisService;
import com.fourfaith.sysManage.model.SysLog;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysPermission;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;
import com.fourfaith.sysManage.service.BaseDeviceDynamicInfoService;
import com.fourfaith.sysManage.service.SysLogService;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysPermissionService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.sysManage.service.SysUserService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PropertiesUtils;
import com.fourfaith.utils.StringUtils;
import com.fourfaith.utils.ip.FileUtil;
import com.fourfaith.utils.ip.IPLocation;
import com.fourfaith.utils.ip.IPSeeker;

/**
 * main控制器
 * @author administrator
 */
@Controller
public class MainController {
	
	protected static final String indexJsp="/index";
	protected static final String loginJsp="/login";
	protected static final String errorJsp = "/errorAuthority";
	protected static final String accreditJsp = "/accredit";
	protected static final String bigDataPage="/bigDataPage";
	
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysPermissionService sysPermissionService;
	@Autowired
	private SysLogService sysLogService;
	@Autowired
	private IntelligentAnalysisService intelligentAnalysisService;
	@Autowired
	private BaseDeviceDynamicInfoService baseDeviceDynamicInfoService;
	
	/**
	 * @Title: accreditPage
	 * @Description: 跳转授权页面 获取本机外网IP
	 * @param: @return
	 * @return: String
	 */
	@RequestMapping(value = "/accredit", method = RequestMethod.GET)
	public ModelAndView accreditPage(){
		ModelAndView modelAndView = new ModelAndView(accreditJsp);
		
		// 获取外网IP
		String internetIP = "";
		try {
			internetIP = sysUserService.getV4IP();
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject("internetIP", internetIP);
		return modelAndView;
	}
	
	/**
	 * 跳转登录页面
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(loginJsp);
		return mav;
	}
	
	/**
	 * 首页
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = null;
		mav = new ModelAndView(indexJsp);
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String headAreaName = PropertiesUtils.getPara("headAreaName");
		StringBuilder strBuild = new StringBuilder();
		if(!StringUtils.isNullOrEmpty(headAreaName)){
			String[]chars= headAreaName.split("");
			for(int i=0;i<chars.length;i++){
		        strBuild.append(chars[i]);
		    }
		}
		if(login_user==null){
			 mav.setViewName(bigDataPage);
			/*mav.setViewName(loginJsp);*/
		}else{
			 mav.setViewName(indexJsp);
			 mav.addObject("headAreaName", strBuild.toString());
			 mav.addObject("login_user", login_user);
			 List<SysMenu> menuList = new ArrayList<SysMenu>();
			 if (login_user.getSysrole() != null) {
				 String roleId = login_user.getSysrole().getId();
				 menuList = sysMenuService.getListByRoleId(roleId);
			 }
			 mav.addObject("menuList", menuList);
			 // 查询所有的异常机井信息数量
			 int intellgentAnalysisCount = intelligentAnalysisService.getSum();
			 HttpSession mHs = request.getSession();
			 mHs.setAttribute("INTELANALYSISCOUNT", intellgentAnalysisCount);
		}
		return mav;
	}
	
	public static void t1(String name){//ASCII转换为字符串
		  String[]chars=name.split("");
		  System.out.println("ASCII 汉字 \n----------------------");
		        for(int i=0;i<chars.length;i++){
		            System.out.println(chars[i]+" "+(char)Integer.parseInt(chars[i]));
		        }
		 }
	
	public static void main(String[] args) {
		String name = "\u7518\u5dde\u533a";
		String[]chars=name.split("");
		  StringBuilder strBuild = new StringBuilder();
		        for(int i=0;i<chars.length;i++){
		        	strBuild.append(chars[i]);
		        }
		        System.out.println(strBuild.toString());
	}
	/**
	 * 用户登录
	 * @param request
	 */
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	@ResponseBody
	public String userLogin(HttpServletRequest request,HttpServletResponse response)
	{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("success", false);
		hm.put("msg", "用户名和密码错误！");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("passWord");
		boolean isSimplePwd = false;
		if(userPassword.equals("123456")){
			isSimplePwd = true;
		}
		hm.put("isSimplePwd", isSimplePwd);
		if(!StringUtils.isNullOrEmpty(userName) && !StringUtils.isNullOrEmpty(userPassword)){
			Map<String,Object> params = new HashMap<String, Object>();
			boolean flag = false;//用户名密码验证是否通过
			SysUser resultUser = new SysUser();	
			//构造查询参数
			params.put("userName", userName);
			SysUser sysUser = this.sysUserService.findByUserName(userName);
			if(sysUser!=null){
				if(StringUtils.encryptMd5(userPassword).equals(sysUser.getUserPassword())){
					//获取角色
					HashMap<String, Object> roleParams = new HashMap<String, Object>();
					roleParams.put("userId", sysUser.getId());
					List<SysUserRole> userRoleList = this.sysUserRoleService.getList(roleParams);
					if(userRoleList!=null && userRoleList.size()>0){
						SysRole role = this.sysRoleService.findById(userRoleList.get(0).getRoleId());
						//判断角色下是否存在应用权限
						HashMap<String, Object> spParams = new HashMap<String, Object>();
						sysUser.setRoleName(role.getRoleName());
						spParams.put("roleId", role.getId());
						List<SysPermission> spList = this.sysPermissionService.getList(spParams);
						if(spList!=null && spList.size()>0){
							flag = true;
							hm.put("success", true);
						}else{
							hm.put("success", false);
							hm.put("msg", "该用户配置的角色未配置权限,请联系管理员!");
						}
						sysUser.setSysrole(role);
					}else{
						hm.put("success", false);
						hm.put("msg", "该用户暂未配置角色,请联系管理员!");
					}
					resultUser = sysUser;
				}
			}			
			if(flag){
				String ip = CommonUtil.getIpAddr(request);
				String area = "";
				if(org.apache.commons.lang.StringUtils.isNotBlank(ip)){
					String[] str = ip.split("\\.");
					IPSeeker seeker = null;
					try {
						seeker = new IPSeeker(FileUtil.classpath("/qqwry.dat"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(str.length>0){
						int i1 = Integer.parseInt(str[0]);
						int i2 = Integer.parseInt(str[1]);
						int i3 = Integer.parseInt(str[2]);
						int i4 = Integer.parseInt(str[3]);
						IPLocation location = seeker.getLocation((byte) i1, (byte) i2, (byte) i3, (byte) i4);
						area = location.getCountry();
					}
				}
				
				resultUser.setLoginIp(ip);
				resultUser.setLoginArea(area);
				resultUser.setLoginTime(new Date());
				//记录登录状态
				CommonUtil.setLoginUserInfo(request, resultUser);
				
				//把登录信息存到日志记录中去
				SysLog log = new SysLog();
				log.setId(CommonUtil.getRandomUUID());
				log.setUserid(resultUser.getId());
				log.setLoginip(ip);
				log.setWaterAreaId(sysUser.getWaterAreaId());
				log.setRoleName(resultUser.getRoleName());
				log.setLoginip(ip);
				log.setLogcontent("登录水价改革");
				log.setLogintime(new Date());
				log.setLogtime(new Date());
				log.setLoginarea(area);
			}
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 明文判断是否为超级管理员
	 * @param userName
	 * @param pwd
	 */
	public boolean checkAdmin(String userName, String pwd){
		boolean flag = false;
		String root_username = PropertiesUtils.getPara("root_username");
		String root_pwd = PropertiesUtils.getPara("root_pwd");
		String rootsalt = "1236547890000";
		String newPwd = StringUtils.encryptMd5(pwd);
		newPwd = StringUtils.encryptMd5(newPwd+rootsalt);
		if(root_username.equals(userName) && root_pwd.equals(newPwd)){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 用户退出
	 * @param request
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request) {
		Map<String, Object> hm = new HashMap<String, Object>();
		//注销
		CommonUtil.setLoginUserInfo(request, null);
		request.getSession().invalidate();
		hm.put("success", true);
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 大数据页面
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/bigDataPage", method = RequestMethod.GET)
	public ModelAndView bigDataPage(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView(bigDataPage);
		return mav;
	}
}







