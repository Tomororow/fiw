package com.fourfaith.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: AjaxJson
 * @Description: 操作成功返回提示信息实体工具类
 */
public class AjaxJson {
	
	private boolean success = true;			// 是否成功
	private String msg = "操作成功";			// 提示信息
	private Object obj = null;              // 其他信息
	private Integer count  = 0;				// 总数
	private Map<String, Object> attributes; // 其他参数
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setOptFailed(String msg){
		this.msg = "操作失败";
		if(StringUtils.isNotBlank(msg)) this.msg = msg;
		this.success = false;
	}
	
	public String getJsonStr(){
		JSONObject obj = new JSONObject();
		obj.put("success", this.isSuccess());
		obj.put("msg", this.getMsg());
		obj.put("obj", this.obj);
		obj.put("count", this.count);
		obj.put("attributes", this.attributes);
		return obj.toJSONString();
	}
	
}