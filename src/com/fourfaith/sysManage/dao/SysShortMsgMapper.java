package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysShortMsg;

/**
 * @ClassName: ParamShortMsgMapper
 * @Description: 短信模板dao层接口
 * @Author: zhaojx
 * @Date: 2017年4月18日 上午8:42:23
 */
public interface SysShortMsgMapper {
	
	/**
	 * @Title: getCount
	 * @Description: 查询总条数
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
	int addMsg(SysShortMsg paramShortMsg);
	
	/**
	 * @Title: updateMsg
	 * @Description: 修改短信模板
	 * @param: @param paramShortMsg
	 * @return: int
	 */
	int updateMsg(SysShortMsg paramShortMsg);
	
	/**
	 * @Title: delMsg
	 * @Description: 删除短信模板
	 * @param: @param id
	 * @return: void
	 */
	void delMsg(String id);
	
	/**
	 * @Title: getMsgById
	 * @Description: 根据ID查询短信
	 * @param: @param id
	 * @return: ParamShortMsg
	 */
	SysShortMsg getMsgById(String id);
	
	/**
	 * @Title: getMsgByCode
	 * @Description: 根据短信编码查询短信
	 * @param: @param msgCode
	 * @return: ParamShortMsg
	 */
	SysShortMsg getMsgByCode(String msgCode);
	
	/**
	 * @Title: uniqueMsgCode
	 * @Description: 短信编码  唯一校验
	 * @param: @param msgCode
	 * @return: List<ParamShortMsg>
	 */
	List<SysShortMsg> uniqueMsgCode(String msgCode);
	
}