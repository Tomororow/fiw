package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysShortMsg;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: ParamShortMsgService
 * @Description: 短信模板Service接口
 * @Author: zhaojx
 * @Date: 2017年4月18日 上午9:12:29
 */
public interface SysShortMsgService {

	/**
	 * @Title: getCount
	 * @Description: 查询信息总条数
	 * @param: @param params
	 * @return: int
	 */
	int getCount(Map<String, Object> params);
	
	/**
	 * @Title: getMsgList
	 * @Description: 短信模板列表
	 * @param: @param params
	 * @return: List<ParamShortMsg>
	 */
	List<SysShortMsg> getMsgList(Map<String, Object> params);
	
	/**
	 * @Title: addMsg
	 * @Description: 新增短信模板
	 * @param: @param paramShortMsg
	 * @return: int
	 */
	String addMsg(SysShortMsg paramShortMsg);
	
	/**
	 * @Title: updateMsg
	 * @Description: 修改短信模板
	 * @param: @param paramShortMsg
	 * @return: int
	 */
	String updateMsg(SysShortMsg paramShortMsg);
	
	/**
	 * @Title: delMsg
	 * @Description: 删除短信模板
	 * @param: @param id
	 * @return: void
	 */
	AjaxJson delMsg(String id);
	
	/**
	 * @Title: getMsgById
	 * @Description: 根据ID查询短信息
	 * @param: @param id
	 * @return: ParamShortMsg
	 */
	SysShortMsg getMsgById(String id);
	
	/**
	 * @Title: getMsgByCode
	 * @Description: 根据编码查询短信
	 * @param: @param msgCode
	 * @return: ParamShortMsg
	 */
	SysShortMsg getMsgByCode(String msgCode);

	/**
	 * @Title: uniqueMsgCode
	 * @Description: 短信编码  唯一校验
	 * @param: @param msgCode
	 * @return: String
	 */
	String uniqueMsgCode(String msgCode);
	
}
