package com.fourfaith.chargeManage.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fourfaith.chargeManage.model.FinanceStatements;
import com.fourfaith.chargeManage.model.FinanceStatementsExcel;
import com.fourfaith.chargeManage.service.FinanceStatementsService;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.statisticAnalysis.model.BaseCardInfo;
import com.fourfaith.statisticAnalysis.model.RptBaseDistWaterDetail;
import com.fourfaith.statisticAnalysis.model.RptChargeDetail;
import com.fourfaith.statisticAnalysis.service.BaseCardInfoService;
import com.fourfaith.statisticAnalysis.service.DistWaterPlanInfoService;
import com.fourfaith.statisticAnalysis.service.RptChargeDetailService;
import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.OrderCodeFactory;
import com.fourfaith.utils.ExportExcel;

@SuppressWarnings("all")
@Controller
@RequestMapping(value="/financial_manage")
public class FinancialManageController {
	
	private static final String INDEX = "/page/chargeManage/financialManage/financialManage";
	
	@Autowired
	private FinanceStatementsService financeStatementsService;

	@Autowired
	private BaseCardInfoService baseCardInfoService;
	
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	
	@Autowired
	private SysAreaService sysAreaService;
	
	@Autowired
	private RptChargeDetailService rptChargeDetailService;
	
	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;
	
	@Autowired
	private DistWaterPlanInfoService distWaterPlanInfoService;
	
	
	private List<String> waterAreaIdList = new ArrayList<>();
	
	private List<String> collectList = new ArrayList<>();
	
	private List<FinanceStatements> fsList = new ArrayList<>();
	

	@RequestMapping("/index")
	public ModelAndView index() throws ParseException{
		ModelAndView model = new ModelAndView(INDEX);
		model.addObject("startTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-01-01 00:00:00"));
		model.addObject("endTime", new Date());
		return model;
	}
	
	@RequestMapping("/select_all")
	@ResponseBody
	public String findObjectAll(String code,HttpServletRequest request,String type){
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		Map<String,Object> pageInfo = new HashMap<>();
		fsList.clear();
		SysWaterArea swa = sysWaterAreaService.selectCode(code);
		switch (type) {
		//树结构查询
		case "1":
			//水管区
			if(code.length()==8){
				swaList(swa.getId());
				collectList.add(swa.getId());
				List<BaseDeviceInfo> codeList = baseDeviceInfoService.selectWCode(collectList);
				collectList.clear();
				for(BaseDeviceInfo cl:codeList){
					String device = cl.getDeviceCode();
					if(device!=null&&!device.equals("")){
						List<FinanceStatements> fs = financeStatementsService.selectotelMoney(cl.getDeviceCode(),login_user.getId());
						if(fs!=null){
							for(FinanceStatements fias:fs){
								fsList.add(fias);
							}
						}
					}
				}
			}
			//行政区
			else if(code.length()==10)
			{
				swayaList(code);
				List<BaseDeviceInfo> bdList = baseDeviceInfoService.selectPageAreaList(collectList);
				for(BaseDeviceInfo cl:bdList){
					String device = cl.getDeviceCode();
					if(device!=null&&!device.equals("")){
						List<FinanceStatements> fs = financeStatementsService.selectotelMoney(cl.getDeviceCode(),login_user.getId());
						if(fs!=null){
							for(FinanceStatements fias:fs){
								fsList.add(fias);
							}
						}
					}
				}
				
			}
			/*SysWaterArea swa = sysWaterAreaService.selectCode(code);
			swaList(swa.getId());
			waterAreaIdList.add(code);
			pageInfo.put("list", waterAreaIdList);
			pageInfo.put("userid",login_user.getId());
			fsList = financeStatementsService.inforList(pageInfo);*/
			break;
		//月份查询
		case "2":
			break;
		//年份查询
		case "3":
			break;
		//季度查询
		default:
			break;
		}
		if(code!=null||code!=""){
			
			waterAreaIdList.clear();
		}

		return JSON.toJSONString(fsList);
	}
	
	/**
	 * @Title: intoFinanceStatements
	 * @Description:添加财务报表信息
	 * @param financeStatements
	 * @return void
	 * @author 刘海年
	 * @date 2018年9月14日下午4:05:29
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/intoFinanceStatements")
	public void  intoFinanceStatements(HttpServletRequest request,FinanceStatements financeStatements,String iswin,String cardCode,String chargeTy){
		SysUser	login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String deviceCode =request.getParameter("deviceCode");
		BaseCardInfo baseCardInfo=baseCardInfoService.getDeviceCodeByDatil(deviceCode);
		SysWaterArea sysWaterArea =sysWaterAreaService.getWaterAreaCode(deviceCode);
		BaseDeviceInfo baseDeviceInfo=baseDeviceInfoService.findBaseDeviceInfoByDeviceCode(deviceCode);//根据机井编码查询机井信息
		
		
		//distWaterPlanInfoService.findWellUse(baseDeviceInfo.getId(),request.getParameter("chargeXl"),request.getParameter("chargeSl"),request.getParameter("chargePl"));
		RptChargeDetail rptChargeDetail = new RptChargeDetail();
		RptBaseDistWaterDetail rptBaseDistWaterDetail =null;
		System.out.println("------>"+chargeTy);
		if(iswin.equals("00")){
			if(financeStatements!=null&&sysWaterArea!=null){
				financeStatements.setId(CommonUtil.getRandomUUID());
				financeStatements.setUserid(login_user.getId());//操作员id
				financeStatements.setWaterareacode(sysWaterArea.getWaterAreaCode());
				financeStatements.setUserName(login_user.getFullName());//操作员姓名
				financeStatements.setOrdercode(OrderCodeFactory.getOrderCode(Long.valueOf(request.getParameter("deviceCode")).longValue()));//生成订单编号
				financeStatements.setDevicecode(deviceCode);//机井编码
				if(cardCode!=null&&!cardCode.equals("")){
					financeStatements.setCardcode(cardCode);//卡号
					rptChargeDetail.setCardCode(cardCode);
				}
				financeStatements.setDevicename(request.getParameter("deviceName"));//机井名称
				financeStatements.setDistyear(Integer.parseInt(request.getParameter("chargeXl")));//配水年份
				financeStatements.setDistround(Integer.parseInt(request.getParameter("chargeSl")));//配水轮次
				financeStatements.setDistprice(new BigDecimal(request.getParameter("chargePl")));//水方单价
				financeStatements.setDistwater(new BigDecimal(request.getParameter("chargeWl")));//水方量
				financeStatements.setTotalprice(new BigDecimal(request.getParameter("chargeFee")));//金额
				financeStatements.setCreatetime(new Date());
				financeStatementsService.insertSelective(financeStatements);
				//添加到售水记录表
				rptChargeDetail.setId(CommonUtil.getRandomUUID());
				
				String typh=request.getParameter("chargeSl");
				int type=0;
				if(chargeTy!=null && !chargeTy.equals("")){
					if(chargeTy.equals("灌溉")){
						type=1;
					}else if(chargeTy.equals("工业")){
						type=2;
					}else if(chargeTy.equals("生活")){
						type=3;
					}
					//查出本轮次该机井每亩地配水量
					rptBaseDistWaterDetail =distWaterPlanInfoService.determineWater(deviceCode,request.getParameter("chargeXl"),request.getParameter("chargeSl"),type);
				}
				
				rptChargeDetail.setDeviceCode(deviceCode);
				if(rptBaseDistWaterDetail!=null){
					rptChargeDetail.setDistWaterPlanId(rptBaseDistWaterDetail.getId());//配水计划id
				}
				rptChargeDetail.setDistYear(Integer.parseInt(request.getParameter("chargeXl")));
				rptChargeDetail.setDistRound(Integer.parseInt(request.getParameter("chargeSl")));
				rptChargeDetail.setChargeAmount(new BigDecimal(request.getParameter("chargeFee")));
				rptChargeDetail.setWaterAmount(new BigDecimal(request.getParameter("chargeWl")));
				rptChargeDetail.setUnitPrice(new BigDecimal(request.getParameter("chargePl")));
				rptChargeDetail.setOperator(login_user.getUserName());
				rptChargeDetail.setCreateTime(new Date());
				int count=rptChargeDetailService.insertSelective(rptChargeDetail);
			}
		}
	}

	/**
	 * @Title: deviceNameLike
	 * @Description: 模糊查询机井名称 && 财务报表导出
	 * @param waterAreaCode
	 * @param deviceName
	 * @param startTime
	 * @param endTime
	 * @param distYear
	 * @param distRound
	 * @param request
	 * @return
	 * @return String
	 * @author 刘海年
	 * @date 2018年10月26日上午8:59:49
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/device_name")
	@ResponseBody
	public String deviceNameLike(String waterAreaCode,String deviceName,String startTime,String endTime,String distYear,String distRound,HttpServletRequest request,HttpServletResponse response ,String type){
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String userId=login_user.getId();
		Map<String,Object> pageInfo = new HashMap<>();
		List<FinanceStatements>  FptList  = null;
		try{
			if(deviceName!=null||deviceName!=""){
				pageInfo.put("devicename", deviceName);
			}else if(startTime!=null && !startTime.equals("")){
				pageInfo.put("startTime", startTime);
			}else if(endTime!=null && !endTime.equals("")){
				pageInfo.put("endTime", endTime);
			}else if(distYear!=null && !distYear.equals("")){
				pageInfo.put("distYear", distYear);
			}else if(distRound!=null && !distRound.equals("")){
				pageInfo.put("distRound", distRound);
			}
			pageInfo.put("userId", userId);
			//FptList = financeStatementsService.deviceNameLike(pageInfo);
			FptList =financeStatementsService.findByCondition(deviceName,startTime,endTime,distYear,distRound,userId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(FptList);
	}
	
	//Excel报表导出
	/*@RequestMapping("/repExport")
	public void repExport(HttpServletRequest request,HttpServletResponse response,String dataSum,String type){
		waterAreaIdList.clear();
		Map<String,Object> pageInfo = new HashMap<>();
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String Year = new SimpleDateFormat("yyyy").format(new Date());
		String beginTime = "";
		String endTime = "";
		List<FinanceStatements> fsList = new ArrayList<>();
		switch (type) {
		case "1":
			waterAreaIdList.add(dataSum);
			SysWaterArea swa = sysWaterAreaService.selectCode(dataSum);
			swaList(swa.getId());
			pageInfo.put("list", waterAreaIdList);
			pageInfo.put("userid",login_user.getId());
			fsList = financeStatementsService.inforList(pageInfo);
			waterAreaIdList.clear();
			break;
		case "4":
			switch (dataSum) {
			case "1":
				beginTime =Year+"-01-01 00:00:00";
				endTime =Year+"-03-31 23:59:59";
				break;
			case "2":
				beginTime = Year+"-04-01 00:00:00";
				endTime =Year+"-06-30 23:59:59";
				break;
			case "3":
				beginTime =Year+"-07-01 00:00:00";
				endTime =Year+"-09-30 23:59:59";
				break;

			default:
				beginTime = Year+"-10-01 00:00:00";
				endTime =Year+"-12-31 23:59:59";
				break;
			}
			fsList = financeStatementsService.getRegistMsgByCondition(beginTime,endTime);
			break;
		default:
			//按照月份||年份查询查询
			fsList = financeStatementsService.monthYearLike(dataSum,login_user.getId());
			break;
		}
		List<FinanceStatementsExcel> list = new ArrayList<>();
		for(FinanceStatements fs:fsList){
			FinanceStatementsExcel fse = new FinanceStatementsExcel();
			fse.setCardcode(fs.getCardcode());
			fse.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fs.getCreatetime()));
			fse.setDevicename(fs.getDevicename());
			fse.setDistprice(fs.getDistprice());
			fse.setDistround(fs.getDistround());
			fse.setDistwater(fs.getDistwater());
			fse.setDistyear(fs.getDistyear());
			fse.setOrdercode(fs.getOrdercode());
			fse.setTotalprice(fs.getTotalprice());
			list.add(fse);
		}
		ExportExcel.exportExcel(list, "报表信息", "财务报表信息", FinanceStatementsExcel.class, "财务报表信息.xls", response);
	}*/
	
	@SuppressWarnings("unused")
	@RequestMapping("/repExport")
	public void repExport(String waterAreaCode,String deviceName,String startTime,String endTime,String distYear,String distRound,HttpServletRequest request,HttpServletResponse response ,String type){
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String userId=login_user.getId();
		Map<String,Object> pageInfo = new HashMap<>();
		List<FinanceStatements>  FptList  = null;
			if(deviceName!=null||deviceName!=""){
				pageInfo.put("devicename", deviceName);
			}else if(startTime!=null && !startTime.equals("")){
				pageInfo.put("startTime", startTime);
			}else if(endTime!=null && !endTime.equals("")){
				pageInfo.put("endTime", endTime);
			}else if(distYear!=null && !distYear.equals("")){
				pageInfo.put("distYear", distYear);
			}else if(distRound!=null && !distRound.equals("")){
				pageInfo.put("distRound", distRound);
			}
			pageInfo.put("userId", userId);
			//FptList = financeStatementsService.deviceNameLike(pageInfo);
			FptList =financeStatementsService.findByCondition(deviceName,startTime,endTime,distYear,distRound,userId);
			if(type!=null && !type.equals("")){
				List<FinanceStatementsExcel> list = new ArrayList<>();
				for(FinanceStatements fs:FptList){
					FinanceStatementsExcel fse = new FinanceStatementsExcel();
					fse.setWaterareacode(fs.getWaterareacode());
					fse.setWaterAreaName(fs.getWaterAreaName());
					if(fs.getCardcode()==null ||fs.getCardcode().equals("")){
						fse.setCardcode("北京烁光控制器");
					}else{
						fse.setCardcode(fs.getCardcode());
					}
					fse.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fs.getCreatetime()));
					fse.setDevicename(fs.getDevicename());
					fse.setDistprice(fs.getSumPrice());
					fse.setDistround(fs.getDistround());
					fse.setDistwater(fs.getSumWater());
					fse.setDistyear(fs.getDistyear());
					//fse.setOrdercode(fs.getOrdercode());
					fse.setTotalprice(fs.getTotalprice());
					list.add(fse);
				}
				ExportExcel.exportExcel(list, "报表信息", "财务报表信息", FinanceStatementsExcel.class, "财务报表信息.xls", response);
			}
	}
	
	//水管区递归
	public List<String> swaList(String id){
		List<SysWaterArea> list = sysWaterAreaService.selectChildArea(id);
		for(SysWaterArea sa:list){
			waterAreaIdList.add(sa.getWaterAreaCode());
			collectList.add(sa.getId());
			swaList(sa.getId());
		}
		return waterAreaIdList;
	}
	
	//行政区递归
	public List<String> swayaList(String code){
		SysArea sa = sysAreaService.getChildCode(code);
		collectList.add(sa.getId());
		List<SysArea> saList = sysAreaService.getChildCodeInfo(sa.getId());
		for(SysArea sal:saList){
			collectList.add(sal.getId());
			swaList(sal.getId());
		}
		return collectList;
	}

}