package com.fourfaith.sysManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.service.SysShortMsgService;
import com.fourfaith.sysManage.dao.SysShortMsgMapper;
import com.fourfaith.sysManage.model.SysShortMsg;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;

/**
 * @ClassName: ParamShortMsgServiceImpl
 * @Description: 短信模板service实现类
 * @Author: zhaojx
 * @Date: 2017年4月18日 上午9:14:40
 */
@Service
public class SysShortMsgServiceImpl implements SysShortMsgService{

	@Autowired
	private SysShortMsgMapper sysShortMsgMapper;

	@Override
	public int getCount(Map<String, Object> params) {
		return sysShortMsgMapper.getCount(params);
	}
	
	@Override
	public List<SysShortMsg> getMsgList(Map<String, Object> params) {
		return sysShortMsgMapper.getMsgList(params);
	}

	@Override
	public String addMsg(SysShortMsg paramShortMsg) {
		String msg = null;
		// 主键为MySQL UUID自生成
		paramShortMsg.setId(CommonUtil.getRandomUUID());
		int result = sysShortMsgMapper.addMsg(paramShortMsg);
		if(result > 0){
			msg = "添加成功";
		}else{
			msg = "添加失败";
		}
		return msg;
	}

	@Override
	public String updateMsg(SysShortMsg paramShortMsg) {
		String msg = null;
		int result = sysShortMsgMapper.updateMsg(paramShortMsg);
		if(result > 0){
			msg = "修改成功";
		}else{
			msg = "修改失败";
		}
		return msg;
	}

	@Override
	public AjaxJson delMsg(String id) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(id != null){
			String[] idsArray = id.split(",");
			for (int i = 0; i < idsArray.length; i++) {
				logContent = logContent+"删除【短信模板】的数据"+",";
				sysShortMsgMapper.delMsg(idsArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}
	
	@Override
	public SysShortMsg getMsgById(String id) {
		return sysShortMsgMapper.getMsgById(id);
	}
	
	@Override
	public String uniqueMsgCode(String msgCode) {
		List<SysShortMsg> msgList = sysShortMsgMapper.uniqueMsgCode(msgCode);
		if(null != msgList && msgList.size() > 0){
			return "failed";
		}else{
			return "success";
		}
	}

	@Override
	public SysShortMsg getMsgByCode(String msgCode) {
		return sysShortMsgMapper.getMsgByCode(msgCode);
	}

}