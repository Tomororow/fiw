package com.fourfaith.chargeManage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.chargeManage.model.MaccVo;
import com.fourfaith.chargeManage.model.ShuoguanRptchargedetail;
import com.fourfaith.chargeManage.service.ShuoguanRptchargedetailService;

@RequestMapping("/shuoguanRptchargedetail")
@Controller
public class ShuoguanRptchargedetailController {
	
	@Autowired
	private ShuoguanRptchargedetailService shuoguanRptchargedetailService;
	
	/**
	 * @Title: getByDeviceCodeOnDetil
	 * @Description:根据机井编码查询充值返回信息
	 * @param request
	 * @param code
	 * @return
	 * @return String
	 * @author 刘海年
	 * @date 2018年9月4日下午5:28:31
	 */
	@RequestMapping("/getByDeviceCodeOnDetil")
	@ResponseBody
	public String getByDeviceCodeOnDetil(HttpServletRequest request,String code){
		ShuoguanRptchargedetail shuoguanRptchargedetail = shuoguanRptchargedetailService.getByDeviceCodeOnDetil(code);
		if(shuoguanRptchargedetail!=null){
			request.setAttribute("shuoguanRptchargedetail", shuoguanRptchargedetail);
		}
		return JSONObject.toJSONString(shuoguanRptchargedetail);
	}
	/**
	 * @Title: getMacc
	 * @Description:接收通讯中心充值查询返回来的数据
	 * @param request
	 * @return
	 * @return String
	 * @author 刘海年
	 * @date 2018年9月27日上午9:26:35
	 */
	@RequestMapping("/getMacc")
	public String getMacc(HttpServletRequest request){
		MaccVo maccVo=new MaccVo();
		String userCode=request.getParameter("userCode");//机井编码
		String messagePwd= request.getParameter("messagePwd");  //通讯密码
		String serialNumber=request.getParameter("serialNumber");//流水号
		String dateTime=request.getParameter("dateTime");//下发时间
		String himura =request.getParameter("himura");//村号
		String family =request.getParameter("family");//户号
		String createTotalMoney=request.getParameter("createTotalMoney");//总购金额
		String createChargeTimes=request.getParameter("createChargeTimes");//充值次数
		String balanc=request.getParameter("balanc");//剩余金额
		String voltameter=request.getParameter("voltameter");//用户用电量
		String waterYield=request.getParameter("waterYield");//用户用水量
		maccVo.setUserCode(userCode);
		maccVo.setMessagePwd(messagePwd);
		maccVo.setSerialNumber(serialNumber);
		maccVo.setDateTime(dateTime);
		maccVo.setHimura(himura);
		maccVo.setFamily(family);
		maccVo.setCreateTotalMoney(createTotalMoney);
		maccVo.setCreateChargeTimes(createChargeTimes);
		maccVo.setBalanc(balanc);
		maccVo.setVoltameter(voltameter);
		maccVo.setWaterYield(waterYield);
		request.setAttribute("maccVo", maccVo);
		return "/page/chargeManage/distanceChargeIndex/distanceChargeIndex";
	}

}