package com.fourfaith.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


/**
 *  阿里云短信网关接口对接工具类
 * @author Admin
 *
 */
public class ALiYunSSMUtils {
	/**
	 * 
	 * @param mobile 电话号码
	 * @param json 发送内容（对应阿里云短信模板的参数形成json格式）
	 * @param templateCode 对应阿里云短信模板的Code
	 * @return
	 */
	public String sendMesg(String mobile,JSONObject json,String templateCode) {
		String message = "";
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAICJcNLGij1ZuR", "sBiMHHYUq67tYGnT5FWY8qfC45U2IP");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "金志技术");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam",json.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            if(response!=null){
            	if(!StringUtils.isNullOrEmpty(response.getData())){//判断返回值是否为空
            		JSONObject jsonObject = JSONObject.parseObject(response.getData());//将返回值转为json
            		message = jsonObject.getString("Message");//取出返回值中的Message判断是否请求成功
            	}
            }
            System.out.println(response.getData());
            System.out.println(message);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return message;
    }
    
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("content", "短信测试是否成功...");
		new ALiYunSSMUtils().sendMesg("15109360147", json, "SMS_166867606");
	}
}
