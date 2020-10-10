package com.fourfaith.utils;

import net.sf.json.JSONObject;

/**
 * @ClassName: JSONPacketUtils
 * @Description: JSON数据封包信息类
 * @Author: zhaojinxin
 * @Date: 2018年8月14日 下午3:53:43
 */
public class JSONPacketUtils {
	
	/**
	 * @Title: JSONLoginPacket
	 * @Description: 请求WebServer获取Token
	 * @param: @return
	 * @return: String
	 * @Author: zhaojinxin
	 */
	public static String JSONLoginPacket(){
		// 内层嵌套对象
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("Identity", "WebServer");
		
		// 大对象
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", 1001);
		jsonObject.put("seq", 1);
		jsonObject.put("token", "null");
		jsonObject.put("payload", jsonObject2);
		return jsonObject.toString();
	}

	/**
	 * @Title: JSONRemotePacket
	 * @Description: 远程设置josn数据封包
	 * @param: @param type
	 * @param: @param seq
	 * @param: @param token
	 * @param: @param deviceCode
	 * @param: @param operType
	 * @param: @param strToACSII
	 * @return: String
	 * @Author: zhaojinxin
	 */
	public static String JSONRemotePacket(String token, String deviceCode, String operType, String strToACSII){
		// 远程设置请求体大对象
		JSONObject jsonObject = null;
		
		if((token != null && !token.equals("")) && (deviceCode!= null && !deviceCode.equals("")) && (operType!= null && !operType.equals("")) && (strToACSII!= null && !strToACSII.equals(""))){
			// 内层嵌套对象
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("DeviceCode", deviceCode);
			jsonObject2.put("OperType", operType);
			jsonObject2.put("OperContent", strToACSII);
			
			// 大对象
			jsonObject = new JSONObject();
			jsonObject.put("type", 2001);
			jsonObject.put("seq", 1);
			jsonObject.put("token", token);
			jsonObject.put("payload", jsonObject2);
		}
		return jsonObject.toString();
	}
	
}