package com.fourfaith.utils;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.fourfaith.sysManage.model.SysUser;

/**
 * 公共工具类
 */
public class CommonUtil {

	/**
	 * 登录用户Session名称
	 */
	public static String mSessionLoginUser = "LOGINUSERINFO";

	/**
	 * 生成32位id
	 * @return
	 */
	public static String getRandomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 获取登录注册用户的信息
	 * @return
	 */
	public static Object getLoginUserInfo(HttpServletRequest request) {
		HttpSession mHs = request.getSession();
		return mHs.getAttribute(mSessionLoginUser);
	}

	/**
	 * 保存登录用户的信息
	 * @param mResult
	 */
	public static void setLoginUserInfo(HttpServletRequest request, SysUser user) {
		HttpSession mHs = request.getSession();
		if (user != null) {
			mHs.setAttribute(mSessionLoginUser, user);
		} else {
			mHs.setAttribute(mSessionLoginUser, null);
		}
	}

	/**
	 * 通过HttpServletRequest返回IP地址
	 * @param request HttpServletRequest
	 * @return ip String
	 * @throws Exception
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	/***
	 * 通过ip获取区域
	 */
	public static String getIpToArea(String ip) {
		String ip_taobao = PropertiesUtils.getPara("ip_taobao");
		String url = ip_taobao + ip;
		String reslut = httpUtils.get(url);
		// 淘宝ip 格式
		// {"code":0,"data":{"country":"\u4e2d\u56fd","country_id":"CN","area":"\u534e\u4e1c","area_id":"300000","region":"\u798f\u5efa\u7701","region_id":"350000","city":"\u53a6\u95e8\u5e02","city_id":"350200","county":"","county_id":"-1","isp":"\u7535\u4fe1","isp_id":"100017","ip":"120.42.46.98"}}
		// 其中code的值的含义为，0：成功，1：失败。
		net.sf.json.JSONObject attr = net.sf.json.JSONObject.fromObject(reslut);// 先转化为json数组
		String code = attr.get("code").toString();
		String str = "查询失败";
		if (code.equals("0")) {
			Object payload = attr.get("data");// 取第一层
			net.sf.json.JSONArray array = net.sf.json.JSONArray
					.fromObject(payload);
			net.sf.json.JSONObject obj = net.sf.json.JSONObject
					.fromObject(array.get(0));
			String region = obj.getString("region");// 取第二层
			String city = obj.getString("city");// 取第二层
			if (StringUtils.isNotBlank(region)) {
				str = region + city;
			} else {
				str = "内网";
			}
		}
		return str;
	}

	public static void main(String[] args) {
		getIpToArea("120.42.46.98");
		getIpToArea("127.0.0.1 ");
	}

}