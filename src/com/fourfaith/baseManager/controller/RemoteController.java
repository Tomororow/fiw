package com.fourfaith.baseManager.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.baseManager.service.impl.InterfactKeyService;
import com.fourfaith.utils.ConfUtils;
import com.fourfaith.utils.JSONPacketUtils;
import com.fourfaith.utils.PropertiesUtils;
import com.fourfaith.utils.URLEncodeUtils;
import com.fourfaith.utils.httpUtils;
import com.fourfaith.utils.DesData;

/**
 * @ClassName: RemoteController
 * @Description: 远程操作控制器
 * @Author: zhaojx
 * @Date: 2017年8月24日 下午3:16:15
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/remote")
public class RemoteController {

	protected Logger logger = Logger.getLogger(RemoteController.class);
	
	private final static String addUpdate = "/page/monitor/addUpdate";

	@Autowired
	private InterfactKeyService interfactKeyService;

	// 登录验证通过 获取token令牌
	private static String TOKEN;
	
	/**
	 * @Title: loginVerify
	 * @Description: web通过WebServer访问BusServer进行登录验证
	 * @return: String
	 * @Author: zhaojx
	 * @Date: 2017年8月24日 下午6:05:56
	 */
	@RequestMapping(value = "/loginVerify", method = RequestMethod.GET)
	public void loginVerify(HttpServletRequest request) {
		// 登录验证请求体
		String urlBodyStr = JSONPacketUtils.JSONLoginPacket();

		// 将请求体进行编码处理
		String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
		String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
		// 将真实请求的url装入get请求类 得到响应结果
		String result = httpUtils.get(requestURL);
		// json解析
		if(result != null && !result.equals("")){
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONObject payload = (JSONObject) jsonObject.get("payload");
			String token = payload.getString("Token");
			if(token != null && !token.equals("")){
				TOKEN = token;
				logger.info("token:" + token);
			}
		}
	}
	
	/**
	 * 远程升级配置页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/addUpdate")
	public ModelAndView remoteOpenUpdate() throws IOException{
		ModelAndView mav = new ModelAndView(addUpdate);
		String path = "http://61.178.178.92:8002//uploadV1.3";
		List<String> list = new ArrayList<>();
		URL getUrl = new URL(path);
		HttpURLConnection connection = (HttpURLConnection)getUrl.openConnection();
		connection.connect();
	    String lines = "";
	    String readCont = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
        while((lines =reader.readLine())!=null){
        	lines = new String(lines.getBytes(), "utf-8");
        	readCont = lines;
        }
        if(readCont.contains("<A HREF=")){
        	 String str [] = readCont.split(".bin");
        	 for(int i=0;i<str.length;i++){
        		 if(i%2!=0){
        			 list.add(str[i].substring(2));
        		 }
        	 }
        }
        String updateUrl = "";
        String updatePort = "";
        String updateFile = "";
        String webServerIp = "";
        String webServerPort = "";
        String UpgradeVersions = PropertiesUtils.getPara("UpgradeVersions");
        String REMOTE_URL = PropertiesUtils.getPara("REMOTE_URL");
        if(StringUtils.isNotBlank(UpgradeVersions)){
        	updateUrl = UpgradeVersions.split(",")[0];
        	updatePort = UpgradeVersions.split(",")[1];
        	updateFile = UpgradeVersions.split(",")[2];
        }
        if(StringUtils.isNotBlank(REMOTE_URL)){
        	webServerIp = REMOTE_URL.substring(7, REMOTE_URL.lastIndexOf("/")).split(":")[0];
        	webServerPort = REMOTE_URL.substring(7, REMOTE_URL.lastIndexOf("/")).split(":")[1];
        }
        mav.addObject("updateFileList", list);
        mav.addObject("updateFile", updateFile);
        mav.addObject("updateUrl", updateUrl);
        mav.addObject("updatePort", updatePort);
        mav.addObject("webServerIp", webServerIp);
        mav.addObject("webServerPort", webServerPort);
		return mav;
	}
	/**
	 * 回读参数
	 * @param request
	 * @param deviceCode
	 * @param flag
	 * @return
	 */
    @RequestMapping("/readWaterEleInfo")
    @ResponseBody
    public String readWaterEleInfo(HttpServletRequest request, String deviceCode, String flag) {
        String AtReadReturn = "";
        switch(flag.hashCode()) {
        case 49:
            if (flag.equals("1")) {
                String[] strData1 = new String[]{"AT+WTRCHNNL01?", "AT+WTRCHNNL02?", "AT+WTRCHNNL03?", "AT+WTRCHNNL04?", "AT+WTRCHNNL05?", "AT+WTRCHNNL06?", "AT+ELECHNNL?", "AT+LVWTRLRM?", "AT+CHCKWTRMIN?", "AT+ELE2WTR?", "AT+NOELECLOSE?", "AT+ELEENERGY?", "AT+VOLMULTIPLE?", "AT+SEC2WTR?", "AT+ELEFILTERA?"};
                AtReadReturn = this.AtReadReturn(deviceCode, strData1);
            }
            break;
        case 50:
            if (flag.equals("2")) {
                String[] strData2 = new String[]{"AT+IIOLRMOP01?", "AT+IIOTYPE01?", "AT+IIOCOLTIME01?", "AT+IIOSVTIME01?", "AT+IIOLRMOP02?", "AT+IIOTYPE02?", "AT+IIOCOLTIME02?", "AT+IIOSVTIME02?", "AT+IIOLRMOP03?", "AT+IIOTYPE03?", "AT+IIOCOLTIME03?", "AT+IIOSVTIME03?", "AT+IIOLRMOP04?", "AT+IIOTYPE04?", "AT+IIOCOLTIME04?", "AT+IIOSVTIME04?"};
                AtReadReturn = this.AtReadReturn(deviceCode, strData2);
            }
            break;
        case 51:
            if (flag.equals("3")) {
                String[] strData3 = new String[]{"AT+RELAYDEF01?", "AT+RELAYTYPE01?", "AT+RELAYMAN?", "AT+RLYTYPE?", "AT+RELAYDEF02?", "AT+RELAYTYPE02?", "AT+LOCKDLY?", "AT+RLYMANPROT?", "AT+RELAYDEF03?", "AT+RLYINTTYPE?", "AT+RELAYDEF04?", "AT+RELAYTYPE04?"};
                AtReadReturn = this.AtReadReturn(deviceCode, strData3);
            }
            break;
        case 52:
            if (flag.equals("4")) {
                String[] strData4 = new String[]{"AT+CNTROP01?", "AT+CNTRUNIT01?", "AT+CNTRSVTIME01?", "AT+CNTRTYPE01?", "AT+CNTROP02?", "AT+CNTRUNIT02?", "AT+CNTRSVTIME02?", "AT+CNTRTYPE02?", "AT+CNTROP03?", "AT+CNTRUNIT03?", "AT+CNTRSVTIME03?", "AT+CNTRTYPE03?", "AT+CNTROP04?", "AT+CNTRUNIT04?", "AT+CNTRSVTIME04?", "AT+CNTRTYPE04?"};
                AtReadReturn = this.AtReadReturn(deviceCode, strData4);
            }
            break;
        case 53:
            if (flag.equals("5")) {
                String[] strData5 = new String[]{"AT+COMSPEED01?", "AT+COMPARITY01?", "AT+COMCOLTIME01?", "AT+COMSVTIME01?", "AT+COMTYPE01?", "AT+COMSPEED02?", "AT+COMPARITY02?", "AT+COMCOLTIME02?", "AT+COMSVTIME02?", "AT+COMTYPE02?", "AT+COMSPEED03?", "AT+COMPARITY03?", "AT+COMCOLTIME03?", "AT+COMSVTIME03?", "AT+COMTYPE03?", "AT+COMSPEED04?", "AT+COMPARITY04?", "AT+COMCOLTIME04?", "AT+COMSVTIME04?", "AT+COMTYPE04?"};
                AtReadReturn = this.AtReadReturn(deviceCode, strData5);
            }
            break;
        case 54:
            if (flag.equals("6")) {
                String[] strData6 = new String[]{"AT+IDNT?", "AT+NFCUSERID01?", "AT+PHON?", "AT+NFCUSERID02?"};
                AtReadReturn = this.AtReadReturn(deviceCode, strData6);
            }
            break;
        case 55:
            if (flag.equals("7")) {
                String[] strData7 = new String[]{"AT+NETMODE01?", "AT+MULTISER01?", "AT+MULTIPORT01?", "AT+BACKSER?", "AT+BACKPORT?", "AT+MULTIDNS01?", "AT+BACKDNS?", "AT+HRTTIME?", "AT+UPNTRVL?", "AT+UPINCVAL?"};
                AtReadReturn = this.AtReadReturn(deviceCode, strData7);
            }
            break;
        case 56:
            if (flag.equals("8")) {
                String[] strData8 = new String[]{"AT+CNTSVLEN01?", "AT+COMSVLEN01?", "AT+CNTSVLEN02?", "AT+COMSVLEN02?", "AT+CNTSVLEN03?", "AT+COMSVLEN03?", "AT+CNTSVLEN04?", "AT+COMSVLEN04?", "AT+NFCSVLEN?", "AT+IFERASESV?"};
                AtReadReturn = this.AtReadReturn(deviceCode, strData8);
            }
        }

        return AtReadReturn;
    }
    
    public String AtReadReturn(String deviceCode, String[] strData) {
        Map<String, String> pageInfo = new HashMap();
        if (strData.length > 0) {
            for(int i = 0; i < strData.length; ++i) {
                System.out.println(strData[i]);
                try {
                	String result = this.webServieHttp(deviceCode, "5010", strData[i]);
                	if (StringUtils.isNotBlank(result)) {
                        JSONObject jsonObject = JSONObject.fromObject(result);
                        JSONObject payload = (JSONObject)jsonObject.get("payload");
                        Object tert = payload.get("Result");
                        System.out.println(tert.toString());
                        if(tert.toString()!=null){
                        	String test = (String)payload.get("Result");
                        	String[] a = test.split("\\r\\n");
                            String[] b = a[1].split(":");
                            this.logger.info("截取到输数据为b0:" + b[0]);
                            this.logger.info("截取到输数据为b1:" + b[1]);
                            pageInfo.put(b[0], b[1]);
                        }
                    }
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
                
            }
        }
        return com.alibaba.fastjson.JSONObject.toJSONString(pageInfo);
    }
    
    public String webServieHttp(String deviceCode, String operType, String strToACSII) {
        String result = "";
        String urlBodyStr = "";
        urlBodyStr = JSONPacketUtils.JSONRemotePacket(this.getToken(), deviceCode, operType, strToACSII);
        this.logger.info("远程请求信息:" + urlBodyStr);
        if(StringUtils.isNotBlank(urlBodyStr)){
        	String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
            String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
            if(StringUtils.isNotBlank(requestURL)){
            	 try {
                     result = httpUtils.get(requestURL);
                 } catch (Exception var9) {
                     var9.printStackTrace();
                 }

            }
        }
        return result.toString() != null ? result.toString() : "由于网络原因，指令下发失败！";
    }
	/**
	 * @Title: getToken
	 * @Description: 通过访问WebServer进行登录验证
	 * @return: String
	 */
	public String getToken(){
		// 登录验证请求体
		String urlBodyStr = JSONPacketUtils.JSONLoginPacket();
	
		// 将请求体进行编码处理
		String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
		String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
		// 将真实请求的url装入get请求类 得到响应结果
		String result = httpUtils.get(requestURL);
		String token = "";
		// json解析
		if(result != null && !result.equals("")){
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONObject payload = (JSONObject) jsonObject.get("payload");
			token = payload.getString("Token");
		}
		return token;
	}
	
	/**
	 * @Title: remoteRequestOperate
	 * @Description: 远程Http请求操作(控制器)
	 * @Author: zhaojx
	 * @param deviceCode 机井编码
	 * @param flag 请求表示
	 * @param chargeFee 充值金额
	 * @param prepaidNumber 充值次数
	 * @param createRuralNum （镇）村号
	 * @param createTotalMoney 总购金额(元)
	 * @param createThisUseElec 本次用电量
	 * @param createThisUseWater 本次用水量
	 * @param isType 请求类型
	 * @param command 卡号
	 * @param operType
	 * @param strToACSII
	 * @param chargeWl 购水量
	 * @param chargePl 购水单价
	 * @param pNum 充值次数
	 * @return String
	 * @author 刘海年
	 * @date 2018年10月10日 上午11:19:13
	 */
	@RequestMapping(value = "/remoteRequestOperate")
	@ResponseBody
	public String remoteRequestOperate(HttpServletRequest request, String deviceCode, String flag, String chargeFee, String prepaidNumber, 
			String createRuralNum, String createTotalMoney, String createThisUseElec, String createThisUseWater, String isType, 
			String command, String operType, String strToACSII, String chargeWl, String chargePl, String fDate, String desData, String serialNumber) {
		/* String http=request.getScheme();
		String ip=request.getServerName();
		int port=request.getServerPort();
		String ctxPath=request.getContextPath();
		String serPath=request.getServletPath(); */
		String updateWen = chargeFee;//升级文件
		String key="";
		String query=request.getQueryString();
		if(desData!=null&&!desData.equals("")){
			if(query!=null&&!query.equals("")){
				query=query.substring(query.indexOf("=")+1);
			}
			String queryData="";
			try {
				queryData=DesData.DecodeDES(query);
			} catch (Exception e1) {
				return "error";
			}
			
			if(queryData!=null && !queryData.equals("")){//
				String[] strArry= queryData.split("&");
				for(int i=0;i<strArry.length;i++){
					if(strArry[i].indexOf("deviceCode")!=-1){
						deviceCode=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("flag")!=-1){
						flag=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("chargeFee")!=-1){
						chargeFee=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("prepaidNumber")!=-1){
						prepaidNumber=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("createRuralNum")!=-1){
						createRuralNum=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("createTotalMoney")!=-1){
						createTotalMoney=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("createThisUseElec")!=-1){
						createThisUseElec=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("createThisUseWater")!=-1){
						createThisUseWater=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("command")!=-1){
						command=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("operType")!=-1){
						operType=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("strToACSII")!=-1){
						strToACSII=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("chargeWl")!=-1){
						chargeWl=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("chargePl")!=-1){
						chargePl=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("fDate")!=-1){
						fDate=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("isType")!=-1){
						isType=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("serialNumber")!=-1){//烁光流水号
						serialNumber=strArry[i].substring(strArry[i].indexOf("=")+1);
					}else if(strArry[i].indexOf("key")!=-1){//烁光流水号
						key=strArry[i].substring(strArry[i].indexOf("=")+1);
					}
				}
			}
		}
		
		if(serialNumber!=null && !serialNumber.equals("")){//烁光流水号处理
			//Integer iserialNumber=Integer.parseInt(serialNumber)+1;
			Integer iserialNumber=Integer.parseInt(serialNumber);
			serialNumber=Integer.toHexString(iserialNumber);
		}else{
			serialNumber="f4e";
		}
		
		String iTotalMoney ="";
		if(chargeFee!=null && createTotalMoney!=null){
			iTotalMoney = Integer.parseInt(chargeFee)+Integer.parseInt(createTotalMoney)+"";
			if(iTotalMoney!="" && iTotalMoney!=null){//给总金额补位
				if(iTotalMoney.length()>0&&iTotalMoney.length()==1){
					iTotalMoney="00000"+iTotalMoney+"00";
				}else if(iTotalMoney.length()>0&&iTotalMoney.length()==2){
					iTotalMoney="0000"+iTotalMoney+"00";
				}else if(iTotalMoney.length()>0&&iTotalMoney.length()==3){
					iTotalMoney="000"+iTotalMoney+"00";
				}else if(iTotalMoney.length()>0&&iTotalMoney.length()==4){
					iTotalMoney="00"+iTotalMoney+"00";
				}else if(iTotalMoney.length()>0&&iTotalMoney.length()==5){
					iTotalMoney="0"+iTotalMoney+"00";
				}else if(iTotalMoney.length()>0&&iTotalMoney.length()==6){
					iTotalMoney=iTotalMoney+"00";
				}
			}
		}
		
		// 请求结果
		String result = "";
		String preNum="";
		if(prepaidNumber!=null){
			preNum=(Integer.parseInt(prepaidNumber)+1)+"";
			if(preNum.length()>0&&preNum.length()==1){//给充值次数补位
				preNum="000"+preNum;
			}else if(preNum.length()>0&&preNum.length()==2){
				preNum="00"+preNum;
			}else if(preNum.length()>0&&preNum.length()==3){
				preNum="0"+preNum;
			}
		}
		
		// 操作类型 协议文档获取 8888为参数设置类型
		// AT指令信息
		String createFarmerNum="";//用户编号
		if(deviceCode!=null&&deviceCode.length()==11){
			if(!deviceCode.equals("")&&deviceCode!=null){
				createFarmerNum=deviceCode.substring(7, 11);
			}
		}
		if(chargeFee!="" && chargeFee!=null){//给充值金额补位
			if(chargeFee.length()>0&&chargeFee.length()==1){
				chargeFee="000"+chargeFee;
			}else if(chargeFee.length()>0&&chargeFee.length()==2){
				chargeFee="00"+chargeFee;
			}else if(chargeFee.length()>0&&chargeFee.length()==3){
				chargeFee="0"+chargeFee;
			}
			chargeFee=chargeFee+"00";
		}
		
		if(createThisUseElec!="" && createThisUseElec!=null){//给充用电量补位
			if(createThisUseElec.length()>0&&createThisUseElec.length()==1){
				createThisUseElec="000"+createThisUseElec;
			}else if(createThisUseElec.length()>0&&createThisUseElec.length()==2){
				createThisUseElec="00"+createThisUseElec;
			}else if(createThisUseElec.length()>0&&createThisUseElec.length()==3){
				createThisUseElec="0"+createThisUseElec;
			}
			createThisUseElec=createThisUseElec+"00";
		}
		
		if(createThisUseWater!="" && createThisUseWater!=null){//给充用水量补位
			if(createThisUseWater.length()>0&&createThisUseWater.length()==1){
				createThisUseWater="000"+createThisUseWater;
			}else if(createThisUseWater.length()>0&&createThisUseWater.length()==2){
				createThisUseWater="00"+createThisUseWater;
			}else if(createThisUseWater.length()>0&&createThisUseWater.length()==3){
				createThisUseWater="0"+createThisUseWater;
			}
			createThisUseWater=createThisUseWater+"00";
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		if(fDate==null || fDate.equals("")){
			fDate=df.format(new Date());
		}
		// 非远程设参远程操作 标志位非空判断
		if(flag != null && !flag.equals("") && deviceCode.length() == 10){
			int cpl = 0;
			if(chargePl != null && !chargePl.equals("")){
				double dp = Double.valueOf(chargePl)*100;
				cpl = (new Double(dp)).intValue();
			}
			switch (Integer.parseInt(flag)) {
				// 重启
				case 1:
					operType = "5001";
					strToACSII = "llll";
					break;
				// 清空控制器
				case 2:
					operType = "5005";
					strToACSII = "llll";
					break;
				// 清空水表
				case 3:
					operType = "8310";
					strToACSII = "llll";
					break;
				// 开启锁用户卡
				case 4:
					operType = "5010";
					strToACSII = "AT+LOCKUSERCARD@1";
					break;
				// 关闭锁用户卡
				case 5:
					operType = "5010";
					strToACSII = "AT+LOCKUSERCARD@0";
					break;
				// 开启电表通讯异常跳闸
				case 6:
					operType = "5010";
					strToACSII = "AT+ELEERRPRT@1";
					break;
				// 关闭电表通讯异常跳闸
				case 7:
					operType = "5010";
					strToACSII = "AT+ELEERRPRT@0";
					break;
				// 开启水表通讯异常跳闸
				case 8:
					operType = "5010";
					strToACSII = "AT+WTRERRPRT@1";
					break;
				// 关闭水表通讯异常跳闸 
				case 9:
					operType = "5010";
					strToACSII = "AT+WTRERRPRT@0";
					break;
				// 远程开启阀门			
				case 10:
					operType = "8220";
					strToACSII = "6010@1";
					break;
				// 远程关闭阀门
				case 11:
					operType = "8220";
					strToACSII = "6010@0";
					break;
				// 远程升级	
				case 57:
					operType = "5021";
					strToACSII = updateWen;//平台修改的缘故
					//strToACSII = ConfUtils.getInstance().getUpgradeVersions();
					break;	
				// 充值查询  
				case 213:
					operType = "5031";
					strToACSII = command;
					break;
				// 远程充值
				case 232:
					operType = "5030";
					/*1：卡号8字节BCD码，大端字节序.
					2:充值水量unsigned int 4字节
					3:单价 unsigned int 4字节
					4:充值序号unsigned int 4字节
					5:确认标识char 1字节 0x01
					或者 0x00默认 0x01
					6:充值下发时间unsigned int
					4字节 距离2010年1月1日多少秒*/
					strToACSII=command+"/"+chargeWl+"/"+cpl+"/"+prepaidNumber+"/"+0x01+"/"+fDate;
					break;
				default:
					return "未知的指令信息！";
			}
		// 远程Http请求操山丹(控制器)
		}else if(flag != null && !flag.equals("")&&deviceCode.length()==11){
			switch (Integer.parseInt(flag)) {
				// 充值查询
				case 213: 
					operType = "5031";
					strToACSII = "e7";
					break;
				// 远程充值
				case 232:
					operType = "5030";
					strToACSII = createRuralNum+createFarmerNum+preNum+chargeFee+iTotalMoney+createThisUseElec+createThisUseWater+deviceCode+serialNumber;
					break;
				// 实时数据
				case 55:
					operType = "55";
					strToACSII = "37";
					break;
				default:
					return "未知的指令信息！";
			}
		}
		
		// 远程操作请求体
		String urlBodyStr ="";
		if(getToken() != null && deviceCode != null && operType != null && strToACSII != null && isType != null && isType.equals("cc")){
			if(key.equals("Zyjz_*_8")){
				urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
			}else{
				return "error";
			}
		}else{
			urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
		}
		
		// 将请求体进行编码处理
		logger.info("远程请求信息:" + urlBodyStr);
		String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
		String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
		try {
			result = httpUtils.get(requestURL);
			if(StringUtils.isNotBlank(result)){
				logger.info("远程操作执行结果:" + JSONObject.fromObject(result).toString());
			}
			logger.info("远程操作执行结果:" +"null");
		} catch (Exception e) {
			/*e.printStackTrace();*/
			logger.error("远程操作执行异常:" + e.getMessage());
		}
		return (result.toString() != null) ? result.toString():"由于网络原因，指令下发失败！";
	}
	
	/**
	 * @Title: remoteParamOperate
	 * @Description: 远程设置参数
	 * @param: @param request
	 * @param: @param flags
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/remoteParamOperate")
	@ResponseBody
	public String remoteParamOperate(HttpServletRequest request, @RequestParam(value="flags[]") Integer[] flags) {
		// 远程操作请求体
		String urlBodyStr = "";
		// 总体 请求结果json字符串
		JSONObject result = new JSONObject();
		// 操作类型 协议文档获取 5010 为参数设置类型
		String operType = "5010";
		// AT指令信息
		String strToACSII = "";
		// 设参的机井编码
		String deviceCode = request.getParameter("deviceCode");
		
		/** 水电设置 */
		// 电量转水量系数  请求结果
		String re_dzsxs = "";
		// 无电监测时间  请求结果
		String re_wdjcsj = "";
		// 电量系数 请求结果
		String re_dlxs = "";
		// 电压系数 请求结果
		String re_dyxs = "";
		// 时间转水量系数 请求结果
		String re_sjzsl = "";
		// 剩余水量报警阀值 请求结果
		String re_syslfz = "";
		// 无水监测时间 请求结果
		String re_wsjcsj = "";
		// 电流过滤值 请求结果
		String re_dbgldl = "";
		// 水量通道1 请求结果
		String re_sltd1 = "";
		// 电量通道 请求结果
		String re_dltd = "";
		// 备份通道 请求结果
		String re_bftd = "";
		
		/** 输入接口设置 */
		// DI0采集间隔 请求结果
		String re_DI0CJJG= "";
		// DI0存储间隔 请求结果
	    String re_DI0CCJG = "";
		// DI0报警方式 请求结果
		String re_DI0BJFS = "";
		// DI0用途 请求结果
	    String re_DI0YT = "";
		// DI1采集间隔 请求结果
		String re_DI1CJJG = "";
		// DI1存储间隔 请求结果
	    String re_DI1CCJG = "";
		// DI1报警方式 请求结果
		String re_DI1BJFS = "";
		// DI1用途 请求结果
	    String re_DI1YT = "";
		// DI2采集间隔  请求结果
		String re_DI2CJJG = "";
		// DI2存储间隔 请求结果
	    String re_DI2CCJG = "";
		// DI2报警方式 请求结果
		String re_DI2BJFS = "";
		// DI2用途 请求结果
	    String re_DI2YT = "";
		// DI3采集间隔 请求结果
		String re_DI3CJJG = "";
		// DI3存储间隔 请求结果
	    String re_DI3CCJG = "";
		// DI3报警方式 请求结果
		String re_DI3BJFS = "";
		// DI3用途 请求结果
	    String re_DI3YT = "";
	   
	    /** 继电器设置 */
	    // 继电器0默认输出
	    String re_JDQ0MRSC = "";
	    // 继电器0用途
	    String re_JDQ0YT = "";
	    // 继电器1默认输出
	    String re_JDQ1MRSC = "";
	    // 继电器1用途
	    String re_JDQ1YT = "";
	    // 继电器2默认输出
	    String re_JDQ2MRSC = "";
	    // 继电器2用途
	    String re_JDQ2YT = "";
	    // 继电器3默认输出
	    String re_JDQ3MRSC = "";
	    // 继电器3用途
	    String re_JDQ3YT = "";
	    // 主控类型
	    String re_ZKLX = "";
	    // 主控继电器
	    String re_ZKJDQ = "";
	    // 电磁锁开锁延迟
	    String re_DCSKSYC = "";
	    // 开机主控保护
	    String re_KJZKBH = "";
	    
	    /** 计数器设置 */
	    String re_JSQ0FS = "";
		String re_JSQ0XS = "";	    		
	    String re_JSQ0CCSJ = "";
	    String re_JSQ0YT = "";
	    
	    String re_JSQ1FS = "";
		String re_JSQ1XS = "";	    		
	    String re_JSQ1CCSJ = "";
	    String re_JSQ1YT = "";
	    
	    String re_JSQ2FS = "";
		String re_JSQ2XS = "";	    		
	    String re_JSQ2CCSJ = "";
	    String re_JSQ2YT = "";
	    
	    String re_JSQ3FS = "";
		String re_JSQ3XS = "";	    		
	    String re_JSQ3CCSJ = "";
	    String re_JSQ3YT = "";
	    
	    /** 串口设置 */
	    String re_RS2320BTL = "";
	    String re_RS2320SJW = "";
	    String re_RS2320CJJG = "";
	    String re_RS2320CCSJ = "";
	    String re_RS2320YT = "";
	    
	    String re_RS2321BTL = "";
	    String re_RS2321SJW = "";
	    String re_RS2321CJJG = "";
	    String re_RS2321CCSJ = "";
	    String re_RS2321YT = "";
	    
	    String re_RS4850BTL = "";
	    String re_RS4850SJW = "";
	    String re_RS4850CJJG = "";
	    String re_RS4850CCSJ = "";
	    String re_RS4850YT = "";
	    
	    String re_RS4851BTL = "";
	    String re_RS4851SJW = "";
	    String re_RS4851CJJG = "";
	    String re_RS4851CCSJ = "";
	    String re_RS4851YT = "";
		
		// 远程设参操作 获取标志位数组
		if(flags != null && flags.length > 0){
			for (Integer int_flag : flags) {
				// 电量转水量系数
				if(int_flag == 1){
					strToACSII = "AT+ELE2WTR@" + ((request.getParameter("DZSXS") != null) ? request.getParameter("DZSXS"):1500);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-电量转水量系数-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_dzsxs = httpUtils.get(requestURL);
						re_dzsxs = JSONObject.fromObject(result_dzsxs).get("message").toString();
						logger.info("远程设参-电量转水量系数-执行结果:" + result_dzsxs);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-电量转水量系数-执行异常:" + e.getMessage());
					}
				// 无电监测时间
				}else if(int_flag == 2){
					strToACSII = "AT+NOELECLOSE@" + ((request.getParameter("WDJCSJ") != null) ? request.getParameter("WDJCSJ"):300);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-无电监测时间-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_wdjcsj = httpUtils.get(requestURL);
						re_wdjcsj = JSONObject.fromObject(result_wdjcsj).get("message").toString();
						logger.info("远程设参-无电监测时间-执行结果:" + result_wdjcsj);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-无电监测时间-执行异常:" + e.getMessage());
					}
				// 电量系数
				}else if(int_flag == 3){
					strToACSII = "AT+ELEENERGY@" + ((request.getParameter("DLXS") != null) ? request.getParameter("DLXS"):2000);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-电量系数-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_dlxs = httpUtils.get(requestURL);
						re_dlxs = JSONObject.fromObject(result_dlxs).get("message").toString();
						logger.info("远程设参-电量系数-执行结果:" + result_dlxs);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-电量系数-执行异常:" + e.getMessage());
					}
				// 电压系数
				}else if(int_flag == 4){
					strToACSII = "AT+VOLMULTIPLE@" + ((request.getParameter("DYXS") != null) ? request.getParameter("DYXS"):1000);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-电压系数-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_dyxs = httpUtils.get(requestURL);
						re_dyxs = JSONObject.fromObject(result_dyxs).get("message").toString();
						logger.info("远程设参-电压系数-执行结果:" + result_dyxs);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-电压系数-执行异常:" + e.getMessage());
					}
				// 时间转水量系数
				}else if(int_flag == 5){
					strToACSII = "AT+SEC2WTR@" + ((request.getParameter("SJZSL") != null) ? request.getParameter("SJZSL"):1.1);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-时间转水量系数-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_sjzsl = httpUtils.get(requestURL);
						re_sjzsl = JSONObject.fromObject(result_sjzsl).get("message").toString();
						logger.info("远程设参-时间转水量系数-执行结果:" + result_sjzsl);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-时间转水量系数-执行异常:" + e.getMessage());
					}
				// 剩余水量报警阀值
				}else if(int_flag == 6){
					strToACSII = "AT+LVWTRLRM@" + ((request.getParameter("SYSLFZ") != null) ? request.getParameter("SYSLFZ"):300);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-剩余水量报警阀值-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_syslfz = httpUtils.get(requestURL);
						re_syslfz = JSONObject.fromObject(result_syslfz).get("message").toString();
						logger.info("远程设参-剩余水量报警阀值-执行结果:" + result_syslfz);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-剩余水量报警阀值-执行异常:" + e.getMessage());
					}
				// 无水监测时间
				}else if(int_flag == 7){
					strToACSII = "AT+CHCKWTRMIN@" + ((request.getParameter("WSJCSJ") != null) ? request.getParameter("WSJCSJ"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-无水监测时间-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_wsjcsj = httpUtils.get(requestURL);
						re_wsjcsj = JSONObject.fromObject(result_wsjcsj).get("message").toString();
						logger.info("远程设参-无水监测时间-执行结果:" + result_wsjcsj);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-无水监测时间-执行异常:" + e.getMessage());
					}
				// 电表过滤电流
				}else if(int_flag == 8){
					strToACSII = "AT+ELEFILTERA@" + ((request.getParameter("DBGLDL") != null) ? request.getParameter("DBGLDL"):6);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-电表过滤电流-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_dbgldl = httpUtils.get(requestURL);
						re_dbgldl = JSONObject.fromObject(result_dbgldl).get("message").toString();
						logger.info("远程设参-电表过滤电流-执行结果:" + result_dbgldl);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-电表过滤电流-执行异常:" + e.getMessage());
					}
				// 水量通道1
				}else if(int_flag == 9){
					strToACSII = "AT+WTRCHNNL01@" + ((request.getParameter("SLTD1") != null) ? request.getParameter("SLTD1"):7);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-水量通道1-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_sltd1 = httpUtils.get(requestURL);
						re_sltd1 = JSONObject.fromObject(result_sltd1).get("message").toString();
						logger.info("远程设参-水量通道1-执行结果:" + result_sltd1);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-水量通道1-执行异常:" + e.getMessage());
					}
				// 电量通道
				}else if(int_flag == 10){
					strToACSII = "AT+ELECHNNL@" + ((request.getParameter("DLTD") != null) ? request.getParameter("DLTD"):8);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-电量通道-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_dltd = httpUtils.get(requestURL);
						re_dltd = JSONObject.fromObject(result_dltd).get("message").toString();
						logger.info("远程设参-电量通道-执行结果:" + result_dltd);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-电量通道-执行异常:" + e.getMessage());
					}
				// 备份通道
				}else if(int_flag == 11){
					strToACSII = "AT+BCKCHNNL@" + ((request.getParameter("BFTD") != null) ? request.getParameter("BFTD"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-备份通道-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_bftd = httpUtils.get(requestURL);
						re_bftd = JSONObject.fromObject(result_bftd).get("message").toString();
						logger.info("远程设参-备份通道-执行结果:" + result_bftd);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-备份通道-执行异常:" + e.getMessage());
					}
				// DI0采集间隔
				}else if(int_flag == 12){
					strToACSII = "AT+IIOCOLTIME01@" + ((request.getParameter("DI0CJJG") != null) ? request.getParameter("DI0CJJG"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI0采集间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI0CJJG = httpUtils.get(requestURL);
						re_DI0CJJG = JSONObject.fromObject(result_DI0CJJG).get("message").toString();
						logger.info("远程设参-DI0采集间隔-执行结果:" + re_DI0CJJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI0采集间隔-执行异常:" + e.getMessage());
					}
				// DI0存储间隔
				}else if(int_flag == 13){
					strToACSII = "AT+IIOSVTIME01@" + ((request.getParameter("DI0CCJG") != null) ? request.getParameter("DI0CCJG"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI0存储间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI0CCJG = httpUtils.get(requestURL);
						re_DI0CCJG = JSONObject.fromObject(result_DI0CCJG).get("message").toString();
						logger.info("远程设参-DI0存储间隔-执行结果:" + re_DI0CCJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI0存储间隔-执行异常:" + e.getMessage());
					}
				// DI0报警方式
				}else if(int_flag == 14){
					strToACSII = "AT+IIOLRMOP01@" + ((request.getParameter("DI0BJFS") != null) ? request.getParameter("DI0BJFS"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI0报警方式-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI0BJFS = httpUtils.get(requestURL);
						re_DI0BJFS = JSONObject.fromObject(result_DI0BJFS).get("message").toString();
						logger.info("远程设参-DI0报警方式-执行结果:" + re_DI0BJFS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI0报警方式-执行异常:" + e.getMessage());
					}
				// DI0用途
				}else if(int_flag == 15){
					strToACSII = "AT+IIOTYPE01@" + ((request.getParameter("DI0YT") != null) ? request.getParameter("DI0YT"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI0用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI0YT = httpUtils.get(requestURL);
						re_DI0YT = JSONObject.fromObject(result_DI0YT).get("message").toString();
						logger.info("远程设参-DI0用途-执行结果:" + re_DI0YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI0用途-执行异常:" + e.getMessage());
					}
				// DI1采集间隔
				}else if(int_flag == 16){
					strToACSII = "AT+IIOCOLTIME02@" + ((request.getParameter("DI1CJJG") != null) ? request.getParameter("DI1CJJG"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI1采集间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI1CJJG = httpUtils.get(requestURL);
						re_DI1CJJG = JSONObject.fromObject(result_DI1CJJG).get("message").toString();
						logger.info("远程设参-DI1采集间隔-执行结果:" + re_DI1CJJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI1采集间隔-执行异常:" + e.getMessage());
					}
				// DI1存储间隔
				}else if(int_flag == 17){
					strToACSII = "AT+IIOSVTIME02@" + ((request.getParameter("DI1CCJG") != null) ? request.getParameter("DI1CCJG"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI1存储间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI1CCJG = httpUtils.get(requestURL);
						re_DI1CCJG = JSONObject.fromObject(result_DI1CCJG).get("message").toString();
						logger.info("远程设参-DI1存储间隔-执行结果:" + re_DI1CCJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI1存储间隔-执行异常:" + e.getMessage());
					}
				// DI1报警方式
				}else if(int_flag == 18){
					strToACSII = "AT+IIOLRMOP02@" + ((request.getParameter("DI1BJFS") != null) ? request.getParameter("DI1BJFS"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI1报警方式-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI1BJFS = httpUtils.get(requestURL);
						re_DI1BJFS = JSONObject.fromObject(result_DI1BJFS).get("message").toString();
						logger.info("远程设参-DI1报警方式-执行结果:" + re_DI1BJFS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI1报警方式-执行异常:" + e.getMessage());
					}
				// DI1用途
				}else if(int_flag == 19){
					strToACSII = "AT+IIOTYPE02@" + ((request.getParameter("DI1YT") != null) ? request.getParameter("DI1YT"):1);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI1用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI1YT = httpUtils.get(requestURL);
						re_DI1YT = JSONObject.fromObject(result_DI1YT).get("message").toString();
						logger.info("远程设参-DI1用途-执行结果:" + re_DI1YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI1用途-执行异常:" + e.getMessage());
					}
				// DI2采集间隔
				}else if(int_flag == 20){
					strToACSII = "AT+IIOCOLTIME03@" + ((request.getParameter("DI2CJJG") != null) ? request.getParameter("DI2CJJG"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI2采集间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI2CJJG = httpUtils.get(requestURL);
						re_DI2CJJG = JSONObject.fromObject(result_DI2CJJG).get("message").toString();
						logger.info("远程设参-DI2采集间隔-执行结果:" + re_DI2CJJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI2采集间隔-执行异常:" + e.getMessage());
					}
				// DI2存储间隔
				}else if(int_flag == 21){
					strToACSII = "AT+IIOSVTIME03@" + ((request.getParameter("DI2CCJG") != null) ? request.getParameter("DI2CCJG"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI2存储间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI2CCJG = httpUtils.get(requestURL);
						re_DI2CCJG = JSONObject.fromObject(result_DI2CCJG).get("message").toString();
						logger.info("远程设参-DI2存储间隔-执行结果:" + re_DI2CCJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI2存储间隔-执行异常:" + e.getMessage());
					}	
				// DI2报警方式
				}else if(int_flag == 22){
					strToACSII = "AT+IIOLRMOP03@" + ((request.getParameter("DI2BJFS") != null) ? request.getParameter("DI2BJFS"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI2报警方式-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI2BJFS = httpUtils.get(requestURL);
						re_DI2BJFS = JSONObject.fromObject(result_DI2BJFS).get("message").toString();
						logger.info("远程设参-DI2报警方式-执行结果:" + re_DI2BJFS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI2报警方式-执行异常:" + e.getMessage());
					}
				// DI2用途
				}else if(int_flag == 23){
					strToACSII = "AT+IIOTYPE03@" + ((request.getParameter("DI2YT") != null) ? request.getParameter("DI2YT"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI2用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI2YT = httpUtils.get(requestURL);
						re_DI2YT = JSONObject.fromObject(result_DI2YT).get("message").toString();
						logger.info("远程设参-DI2用途-执行结果:" + re_DI2YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI2用途-执行异常:" + e.getMessage());
					}
				// DI3采集间隔
				}else if(int_flag == 24){
					strToACSII = "AT+IIOCOLTIME04@" + ((request.getParameter("DI3CJJG") != null) ? request.getParameter("DI3CJJG"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI3采集间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI3CJJG = httpUtils.get(requestURL);
						re_DI3CJJG = JSONObject.fromObject(result_DI3CJJG).get("message").toString();
						logger.info("远程设参-DI3采集间隔-执行结果:" + re_DI3CJJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI3采集间隔-执行异常:" + e.getMessage());
					}
				// DI3存储间隔
				}else if(int_flag == 25){
					strToACSII = "AT+IIOSVTIME04@" + ((request.getParameter("DI3CCJG") != null) ? request.getParameter("DI3CCJG"):1);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI3存储间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI3CCJG = httpUtils.get(requestURL);
						re_DI3CCJG = JSONObject.fromObject(result_DI3CCJG).get("message").toString();
						logger.info("远程设参-DI3存储间隔-执行结果:" + re_DI3CCJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI3存储间隔-执行异常:" + e.getMessage());
					}
				// DI3报警方式
				}else if(int_flag == 26){
					strToACSII = "AT+IIOLRMOP04@" + ((request.getParameter("DI3BJFS") != null) ? request.getParameter("DI3BJFS"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI3报警方式-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI3BJFS = httpUtils.get(requestURL);
						re_DI3BJFS = JSONObject.fromObject(result_DI3BJFS).get("message").toString();
						logger.info("远程设参-DI3报警方式-执行结果:" + re_DI3BJFS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI3报警方式-执行异常:" + e.getMessage());
					}
				// DI3用途
				}else if(int_flag == 27){
					strToACSII = "AT+IIOTYPE04@" + ((request.getParameter("DI3YT") != null) ? request.getParameter("DI3YT"):3);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-DI3用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DI3YT = httpUtils.get(requestURL);
						re_DI3YT = JSONObject.fromObject(result_DI3YT).get("message").toString();
						logger.info("远程设参-DI3用途-执行结果:" + re_DI3YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-DI3用途-执行异常:" + e.getMessage());
					}
				// 继电器0默认输出
				}else if(int_flag == 28){
					strToACSII = "AT+RELAYDEF01@" + ((request.getParameter("JDQ0MRSC") != null) ? request.getParameter("JDQ0MRSC"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-继电器0默认输出-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JDQ0MRSC = httpUtils.get(requestURL);
						re_JDQ0MRSC = JSONObject.fromObject(result_JDQ0MRSC).get("message").toString();
						logger.info("远程设参-继电器0默认输出-执行结果:" + re_JDQ0MRSC);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-继电器0默认输出-执行异常:" + e.getMessage());
					}
				// 继电器0用途
				}else if(int_flag == 29){
					strToACSII = "AT+RELAYTYPE01@" + ((request.getParameter("JDQ0YT") != null) ? request.getParameter("JDQ0YT"):1);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-继电器0用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JDQ0YT = httpUtils.get(requestURL);
						re_JDQ0YT = JSONObject.fromObject(result_JDQ0YT).get("message").toString();
						logger.info("远程设参-继电器0用途-执行结果:" + re_JDQ0YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-继电器0用途-执行异常:" + e.getMessage());
					}
				// 继电器1默认输出
				}else if(int_flag == 30){
					strToACSII = "AT+RELAYDEF02@" + ((request.getParameter("JDQ1MRSC") != null) ? request.getParameter("JDQ1MRSC"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-继电器1默认输出-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JDQ1MRSC = httpUtils.get(requestURL);
						re_JDQ1MRSC = JSONObject.fromObject(result_JDQ1MRSC).get("message").toString();
						logger.info("远程设参-继电器1默认输出-执行结果:" + re_JDQ1MRSC);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-继电器1默认输出-执行异常:" + e.getMessage());
					}
				// 继电器1用途
				}else if(int_flag == 31){
					strToACSII = "AT+RELAYTYPE02@" + ((request.getParameter("JDQ1YT") != null) ? request.getParameter("JDQ1YT"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-继电器1用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JDQ1YT = httpUtils.get(requestURL);
						re_JDQ1YT = JSONObject.fromObject(result_JDQ1YT).get("message").toString();
						logger.info("远程设参-继电器1用途-执行结果:" + re_JDQ1YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-继电器1用途-执行异常:" + e.getMessage());
					}
				// 继电器2默认输出
				}else if(int_flag == 32){
					strToACSII = "AT+RELAYDEF03@" + ((request.getParameter("JDQ2MRSC") != null) ? request.getParameter("JDQ2MRSC"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-继电器2默认输出-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JDQ2MRSC = httpUtils.get(requestURL);
						re_JDQ2MRSC = JSONObject.fromObject(result_JDQ2MRSC).get("message").toString();
						logger.info("远程设参-继电器2默认输出-执行结果:" + re_JDQ2MRSC);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-继电器2默认输出-执行异常:" + e.getMessage());
					}
				// 继电器2用途
				}else if(int_flag == 33){
					strToACSII = "AT+RELAYTYPE03@" + ((request.getParameter("JDQ2YT") != null) ? request.getParameter("JDQ2YT"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-继电器2用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JDQ2YT = httpUtils.get(requestURL);
						re_JDQ2YT = JSONObject.fromObject(result_JDQ2YT).get("message").toString();
						logger.info("远程设参-继电器2用途-执行结果:" + re_JDQ2YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-继电器2用途-执行异常:" + e.getMessage());
					}
				// 继电器3默认输出
				}else if(int_flag == 34){
					strToACSII = "AT+RELAYDEF04@" + ((request.getParameter("JDQ3MRSC") != null) ? request.getParameter("JDQ3MRSC"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-继电器3默认输出-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JDQ3MRSC = httpUtils.get(requestURL);
						re_JDQ3MRSC = JSONObject.fromObject(result_JDQ3MRSC).get("message").toString();
						logger.info("远程设参-继电器3默认输出-执行结果:" + re_JDQ3MRSC);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-继电器3默认输出-执行异常:" + e.getMessage());
					}
				// 继电器3用途
				}else if(int_flag == 35){
					strToACSII = "AT+RELAYTYPE04@" + ((request.getParameter("JDQ3YT") != null) ? request.getParameter("JDQ3YT"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-继电器3用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JDQ3YT = httpUtils.get(requestURL);
						re_JDQ3YT = JSONObject.fromObject(result_JDQ3YT).get("message").toString();
						logger.info("远程设参-继电器3用途-执行结果:" + re_JDQ3YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-继电器3用途-执行异常:" + e.getMessage());
					}
				// 主控类型
				}else if(int_flag == 36){
					strToACSII = "AT+RELAYMAN@" + ((request.getParameter("ZKLX") != null) ? request.getParameter("ZKLX"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-主控类型-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_ZKLX = httpUtils.get(requestURL);
						re_ZKLX = JSONObject.fromObject(result_ZKLX).get("message").toString();
						logger.info("远程设参-主控类型-执行结果:" + re_ZKLX);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-主控类型-执行异常:" + e.getMessage());
					}
				// 主控继电器
				}else if(int_flag == 37){
					strToACSII = "AT+RLYTYPE@" + ((request.getParameter("ZKJDQ") != null) ? request.getParameter("ZKJDQ"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-主控继电器-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_ZKJDQ = httpUtils.get(requestURL);
						re_ZKJDQ = JSONObject.fromObject(result_ZKJDQ).get("message").toString();
						logger.info("远程设参-主控继电器-执行结果:" + re_ZKJDQ);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-主控继电器-执行异常:" + e.getMessage());
					}
				// 电磁锁开锁延迟
				}else if(int_flag == 38){
					strToACSII = "AT+LOCKDLY@" + ((request.getParameter("DCSKSYC") != null) ? request.getParameter("DCSKSYC"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-电磁锁开锁延迟-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_DCSKSYC = httpUtils.get(requestURL);
						re_DCSKSYC = JSONObject.fromObject(result_DCSKSYC).get("message").toString();
						logger.info("远程设参-电磁锁开锁延迟-执行结果:" + re_DCSKSYC);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-电磁锁开锁延迟-执行异常:" + e.getMessage());
					}
				// 开机主控保护
				}else if(int_flag == 39){
					strToACSII = "AT+RLYMANPROT@" + ((request.getParameter("KJZKBH") != null) ? request.getParameter("KJZKBH"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-开机主控保护-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_KJZKBH = httpUtils.get(requestURL);
						re_KJZKBH = JSONObject.fromObject(result_KJZKBH).get("message").toString();
						logger.info("远程设参-开机主控保护-执行结果:" + re_KJZKBH);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-开机主控保护-执行异常:" + e.getMessage());
					}
				// 计数器0方式
				}else if(int_flag == 40){
					strToACSII = "AT+CNTROP01@" + ((request.getParameter("JSQ0FS") != null) ? request.getParameter("JSQ0FS"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器0方式-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ0FS = httpUtils.get(requestURL);
						re_JSQ0FS = JSONObject.fromObject(result_JSQ0FS).get("message").toString();
						logger.info("远程设参-计数器0方式-执行结果:" + re_JSQ0FS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器0方式-执行异常:" + e.getMessage());
					}
				// 计数器0系数
				}else if(int_flag == 41){
					strToACSII = "AT+CNTRUNIT01@" + ((request.getParameter("JSQ0XS") != null) ? request.getParameter("JSQ0XS"):1);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器0系数-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ0XS = httpUtils.get(requestURL);
						re_JSQ0XS = JSONObject.fromObject(result_JSQ0XS).get("message").toString();
						logger.info("远程设参-计数器0系数-执行结果:" + re_JSQ0XS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器0系数-执行异常:" + e.getMessage());
					}
				// 计数器0存储时间
				}else if(int_flag == 42){
					strToACSII = "AT+CNTRSVTIME01@" + ((request.getParameter("JSQ0CCSJ") != null) ? request.getParameter("JSQ0CCSJ"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器0存储时间-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ0CCSJ = httpUtils.get(requestURL);
						re_JSQ0CCSJ = JSONObject.fromObject(result_JSQ0CCSJ).get("message").toString();
						logger.info("远程设参-计数器0存储时间-执行结果:" + re_JSQ0CCSJ);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器0存储时间-执行异常:" + e.getMessage());
					}
				// 计数器0用途
				}else if(int_flag == 43){
					strToACSII = "AT+CNTRTYPE01@" + ((request.getParameter("JSQ0YT") != null) ? request.getParameter("JSQ0YT"):1);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器0用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ0YT = httpUtils.get(requestURL);
						re_JSQ0YT = JSONObject.fromObject(result_JSQ0YT).get("message").toString();
						logger.info("远程设参-计数器0用途-执行结果:" + re_JSQ0YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器0用途-执行异常:" + e.getMessage());
					}
				// 计数器1方式
				}else if(int_flag == 44){
					strToACSII = "AT+CNTROP02@" + ((request.getParameter("JSQ1FS") != null) ? request.getParameter("JSQ1FS"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器1方式-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ1FS = httpUtils.get(requestURL);
						re_JSQ1FS = JSONObject.fromObject(result_JSQ1FS).get("message").toString();
						logger.info("远程设参-计数器1方式-执行结果:" + re_JSQ1FS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器1方式-执行异常:" + e.getMessage());
					}
				// 计数器1系数
				}else if(int_flag == 45){
					strToACSII = "AT+CNTRUNIT02@" + ((request.getParameter("JSQ1XS") != null) ? request.getParameter("JSQ1XS"):1);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器1系数-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ1XS = httpUtils.get(requestURL);
						re_JSQ1XS = JSONObject.fromObject(result_JSQ1XS).get("message").toString();
						logger.info("远程设参-计数器1系数-执行结果:" + re_JSQ1XS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器1系数-执行异常:" + e.getMessage());
					}
				// 计数器1存储时间
				}else if(int_flag == 46){
					strToACSII = "AT+CNTRSVTIME02@" + ((request.getParameter("JSQ1CCSJ") != null) ? request.getParameter("JSQ1CCSJ"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器1存储时间-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ1CCSJ = httpUtils.get(requestURL);
						re_JSQ1CCSJ = JSONObject.fromObject(result_JSQ1CCSJ).get("message").toString();
						logger.info("远程设参-计数器1存储时间-执行结果:" + re_JSQ1CCSJ);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器1存储时间-执行异常:" + e.getMessage());
					}
				// 计数器1用途
				}else if(int_flag == 47){
					strToACSII = "AT+CNTRTYPE02@" + ((request.getParameter("JSQ1YT") != null) ? request.getParameter("JSQ1YT"):1);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器1用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ1YT = httpUtils.get(requestURL);
						re_JSQ1YT = JSONObject.fromObject(result_JSQ1YT).get("message").toString();
						logger.info("远程设参-计数器1用途-执行结果:" + re_JSQ1YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器1用途-执行异常:" + e.getMessage());
					}
				// 计数器2方式
				}else if(int_flag == 48){
					strToACSII = "AT+CNTROP03@" + ((request.getParameter("JSQ2FS") != null) ? request.getParameter("JSQ2FS"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器2方式-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ2FS = httpUtils.get(requestURL);
						re_JSQ2FS = JSONObject.fromObject(result_JSQ2FS).get("message").toString();
						logger.info("远程设参-计数器2方式-执行结果:" + re_JSQ2FS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器2方式-执行异常:" + e.getMessage());
					}
				// 计数器2系数
				}else if(int_flag == 49){
					strToACSII = "AT+CNTRUNIT03@" + ((request.getParameter("JSQ2XS") != null) ? request.getParameter("JSQ2XS"):1);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器2系数-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ2XS = httpUtils.get(requestURL);
						re_JSQ2XS = JSONObject.fromObject(result_JSQ2XS).get("message").toString();
						logger.info("远程设参-计数器2系数-执行结果:" + re_JSQ2XS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器2系数-执行异常:" + e.getMessage());
					}
				// 计数器2存储时间
				}else if(int_flag == 50){
					strToACSII = "AT+CNTRSVTIME03@" + ((request.getParameter("JSQ2CCSJ") != null) ? request.getParameter("JSQ2CCSJ"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器2存储时间-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ2CCSJ = httpUtils.get(requestURL);
						re_JSQ2CCSJ = JSONObject.fromObject(result_JSQ2CCSJ).get("message").toString();
						logger.info("远程设参-计数器2存储时间-执行结果:" + re_JSQ2CCSJ);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器2存储时间-执行异常:" + e.getMessage());
					}
				// 计数器2用途
				}else if(int_flag == 51){
					strToACSII = "AT+CNTRTYPE03@" + ((request.getParameter("JSQ2YT") != null) ? request.getParameter("JSQ2YT"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器2用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ2YT = httpUtils.get(requestURL);
						re_JSQ2YT = JSONObject.fromObject(result_JSQ2YT).get("message").toString();
						logger.info("远程设参-计数器2用途-执行结果:" + re_JSQ2YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器2用途-执行异常:" + e.getMessage());
					}
				
				// 计数器3方式
				}else if(int_flag == 52){
					strToACSII = "AT+CNTROP04@" + ((request.getParameter("JSQ3FS") != null) ? request.getParameter("JSQ3FS"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器3方式-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ3FS = httpUtils.get(requestURL);
						re_JSQ3FS = JSONObject.fromObject(result_JSQ3FS).get("message").toString();
						logger.info("远程设参-计数器3方式-执行结果:" + re_JSQ3FS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器3方式-执行异常:" + e.getMessage());
					}
				// 计数器3系数
				}else if(int_flag == 53){
					strToACSII = "AT+CNTRUNIT04@" + ((request.getParameter("JSQ3XS") != null) ? request.getParameter("JSQ3XS"):1);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器3系数-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ3XS = httpUtils.get(requestURL);
						re_JSQ3XS = JSONObject.fromObject(result_JSQ3XS).get("message").toString();
						logger.info("远程设参-计数器3系数-执行结果:" + re_JSQ3XS);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器3系数-执行异常:" + e.getMessage());
					}
				// 计数器3存储时间
				}else if(int_flag == 54){
					strToACSII = "AT+CNTRSVTIME04@" + ((request.getParameter("JSQ3CCSJ") != null) ? request.getParameter("JSQ3CCSJ"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器3存储时间-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ3CCSJ = httpUtils.get(requestURL);
						re_JSQ3CCSJ = JSONObject.fromObject(result_JSQ3CCSJ).get("message").toString();
						logger.info("远程设参-计数器3存储时间-执行结果:" + re_JSQ3CCSJ);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器3存储时间-执行异常:" + e.getMessage());
					}
				// 计数器3用途
				}else if(int_flag == 55){
					strToACSII = "AT+CNTRTYPE04@" + ((request.getParameter("JSQ3YT") != null) ? request.getParameter("JSQ3YT"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-计数器3用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_JSQ3YT = httpUtils.get(requestURL);
						re_JSQ3YT = JSONObject.fromObject(result_JSQ3YT).get("message").toString();
						logger.info("远程设参-计数器3用途-执行结果:" + re_JSQ3YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-计数器3用途-执行异常:" + e.getMessage());
					}
				// RS2320波特率
				}else if(int_flag == 56){
					strToACSII = "AT+COMSPEED01@" + ((request.getParameter("RS2320BTL") != null) ? request.getParameter("RS2320BTL"):11);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS2320波特率-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS2320BTL = httpUtils.get(requestURL);
						re_RS2320BTL = JSONObject.fromObject(result_RS2320BTL).get("message").toString();
						logger.info("远程设参-RS2320波特率-执行结果:" + re_RS2320BTL);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS2320波特率-执行异常:" + e.getMessage());
					}
				// RS2320数据位
				}else if(int_flag == 57){
					strToACSII = "AT+COMPARITY01@" + ((request.getParameter("RS2320SJW") != null) ? request.getParameter("RS2320SJW"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS2320数据位-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS2320SJW = httpUtils.get(requestURL);
						re_RS2320SJW = JSONObject.fromObject(result_RS2320SJW).get("message").toString();
						logger.info("远程设参-RS2320数据位-执行结果:" + re_RS2320SJW);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS2320数据位-执行异常:" + e.getMessage());
					}
				// RS2320采集间隔
				}else if(int_flag == 58){
					strToACSII = "AT+COMCOLTIME01@" + ((request.getParameter("RS2320CJJG") != null) ? request.getParameter("RS2320CJJG"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS2320采集间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS2320CJJG = httpUtils.get(requestURL);
						re_RS2320CJJG = JSONObject.fromObject(result_RS2320CJJG).get("message").toString();
						logger.info("远程设参-RS2320采集间隔-执行结果:" + re_RS2320CJJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS2320采集间隔-执行异常:" + e.getMessage());
					}
				// RS2320存储时间
				}else if(int_flag == 59){
					strToACSII = "AT+COMSVTIME01@" + ((request.getParameter("RS2320CCSJ") != null) ? request.getParameter("RS2320CCSJ"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS2320存储时间-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS2320CCSJ = httpUtils.get(requestURL);
						re_RS2320CCSJ = JSONObject.fromObject(result_RS2320CCSJ).get("message").toString();
						logger.info("远程设参-RS2320存储时间-执行结果:" + re_RS2320CCSJ);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS2320存储时间-执行异常:" + e.getMessage());
					}
				// RS2320用途
				}else if(int_flag == 60){
					strToACSII = "AT+COMTYPE01@" + ((request.getParameter("RS2320YT") != null) ? request.getParameter("RS2320YT"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS2320用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS2320YT = httpUtils.get(requestURL);
						re_RS2320YT = JSONObject.fromObject(result_RS2320YT).get("message").toString();
						logger.info("远程设参-RS2320用途-执行结果:" + re_RS2320YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS2320用途-执行异常:" + e.getMessage());
					}
				// RS2321波特率
				}else if(int_flag == 61){
					strToACSII = "AT+COMSPEED02@" + ((request.getParameter("RS2321BTL") != null) ? request.getParameter("RS2321BTL"):11);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS2321波特率-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS2321BTL = httpUtils.get(requestURL);
						re_RS2321BTL = JSONObject.fromObject(result_RS2321BTL).get("message").toString();
						logger.info("远程设参-RS2321波特率-执行结果:" + re_RS2321BTL);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS2321波特率-执行异常:" + e.getMessage());
					}
				// RS2321数据位
				}else if(int_flag == 62){
					strToACSII = "AT+COMPARITY02@" + ((request.getParameter("RS2321SJW") != null) ? request.getParameter("RS2321SJW"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS2321数据位-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS2321SJW = httpUtils.get(requestURL);
						re_RS2321SJW = JSONObject.fromObject(result_RS2321SJW).get("message").toString();
						logger.info("远程设参-RS2321数据位-执行结果:" + re_RS2321SJW);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS2321数据位-执行异常:" + e.getMessage());
					}
				// RS2321采集间隔
				}else if(int_flag == 63){
					strToACSII = "AT+COMCOLTIME02@" + ((request.getParameter("RS2321CJJG") != null) ? request.getParameter("RS2321CJJG"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS2321采集间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS2321CJJG = httpUtils.get(requestURL);
						re_RS2321CJJG = JSONObject.fromObject(result_RS2321CJJG).get("message").toString();
						logger.info("远程设参-RS2321采集间隔-执行结果:" + re_RS2321CJJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS2321采集间隔-执行异常:" + e.getMessage());
					}
				// RS2321存储时间
				}else if(int_flag == 64){
					strToACSII = "AT+COMSVTIME02@" + ((request.getParameter("RS2321CCSJ") != null) ? request.getParameter("RS2321CCSJ"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS2321存储时间-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS2321CCSJ = httpUtils.get(requestURL);
						re_RS2321CCSJ = JSONObject.fromObject(result_RS2321CCSJ).get("message").toString();
						logger.info("远程设参-RS2321存储时间-执行结果:" + re_RS2321CCSJ);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS2321存储时间-执行异常:" + e.getMessage());
					}
				// RS2321用途
				}else if(int_flag == 65){
					strToACSII = "AT+COMTYPE02@" + ((request.getParameter("RS2321YT") != null) ? request.getParameter("RS2321YT"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS2321用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS2321YT = httpUtils.get(requestURL);
						re_RS2321YT = JSONObject.fromObject(result_RS2321YT).get("message").toString();
						logger.info("远程设参-RS2321用途-执行结果:" + re_RS2321YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS2321用途-执行异常:" + e.getMessage());
					}
				// RS4850波特率
				}else if(int_flag == 66){
					strToACSII = "AT+COMSPEED03@" + ((request.getParameter("RS4850BTL") != null) ? request.getParameter("RS4850BTL"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS4850波特率-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS4850BTL = httpUtils.get(requestURL);
						re_RS4850BTL = JSONObject.fromObject(result_RS4850BTL).get("message").toString();
						logger.info("远程设参-RS4850波特率-执行结果:" + re_RS4850BTL);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS4850波特率-执行异常:" + e.getMessage());
					}
				// RS4850数据位
				}else if(int_flag == 67){
					strToACSII = "AT+COMPARITY03@" + ((request.getParameter("RS4850SJW") != null) ? request.getParameter("RS4850SJW"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS4850数据位-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS4850SJW = httpUtils.get(requestURL);
						re_RS4850SJW = JSONObject.fromObject(result_RS4850SJW).get("message").toString();
						logger.info("远程设参-RS4850数据位-执行结果:" + re_RS4850SJW);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS4850数据位-执行异常:" + e.getMessage());
					}
				// RS4850采集间隔
				}else if(int_flag == 68){
					strToACSII = "AT+COMCOLTIME03@" + ((request.getParameter("RS4850CJJG") != null) ? request.getParameter("RS4850CJJG"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS4850采集间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS4850CJJG = httpUtils.get(requestURL);
						re_RS4850CJJG = JSONObject.fromObject(result_RS4850CJJG).get("message").toString();
						logger.info("远程设参-RS4850采集间隔-执行结果:" + re_RS4850CJJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS4850采集间隔-执行异常:" + e.getMessage());
					}
				// RS4850存储时间
				}else if(int_flag == 69){
					strToACSII = "AT+COMSVTIME03@" + ((request.getParameter("RS4850CCSJ") != null) ? request.getParameter("RS4850CCSJ"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS4850存储时间-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS4850CCSJ = httpUtils.get(requestURL);
						re_RS4850CCSJ = JSONObject.fromObject(result_RS4850CCSJ).get("message").toString();
						logger.info("远程设参-RS4850存储时间-执行结果:" + re_RS4850CCSJ);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS4850存储时间-执行异常:" + e.getMessage());
					}
				// RS4850用途
				}else if(int_flag == 70){
					strToACSII = "AT+COMTYPE03@" + ((request.getParameter("RS4850YT") != null) ? request.getParameter("RS4850YT"):2);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS4850用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS4850YT = httpUtils.get(requestURL);
						re_RS4850YT = JSONObject.fromObject(result_RS4850YT).get("message").toString();
						logger.info("远程设参-RS4850用途-执行结果:" + re_RS4850YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS4850用途-执行异常:" + e.getMessage());
					}
				// RS4851波特率
				}else if(int_flag == 71){
					strToACSII = "AT+COMSPEED04@" + ((request.getParameter("RS4851BTL") != null) ? request.getParameter("RS4851BTL"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS4851波特率-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS4851BTL = httpUtils.get(requestURL);
						re_RS4851BTL = JSONObject.fromObject(result_RS4851BTL).get("message").toString();
						logger.info("远程设参-RS4851波特率-执行结果:" + re_RS4851BTL);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS4851波特率-执行异常:" + e.getMessage());
					}
				// RS4851数据位
				}else if(int_flag == 72){
					strToACSII = "AT+COMPARITY04@" + ((request.getParameter("RS4851SJW") != null) ? request.getParameter("RS4851SJW"):0);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS4851数据位-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS4851SJW = httpUtils.get(requestURL);
						re_RS4851SJW = JSONObject.fromObject(result_RS4851SJW).get("message").toString();
						logger.info("远程设参-RS4851数据位-执行结果:" + re_RS4851SJW);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS4851数据位-执行异常:" + e.getMessage());
					}
				// RS4851采集间隔
				}else if(int_flag == 73){
					strToACSII = "AT+COMCOLTIME04@" + ((request.getParameter("RS4851CJJG") != null) ? request.getParameter("RS4851CJJG"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS4851采集间隔-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS4851CJJG = httpUtils.get(requestURL);
						re_RS4851CJJG = JSONObject.fromObject(result_RS4851CJJG).get("message").toString();
						logger.info("远程设参-RS4851采集间隔-执行结果:" + re_RS4851CJJG);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS4851采集间隔-执行异常:" + e.getMessage());
					}
				// RS4851存储时间
				}else if(int_flag == 74){
					strToACSII = "AT+COMSVTIME04@" + ((request.getParameter("RS4851CCSJ") != null) ? request.getParameter("RS4851CCSJ"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS4851存储时间-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS4851CCSJ = httpUtils.get(requestURL);
						re_RS4851CCSJ = JSONObject.fromObject(result_RS4851CCSJ).get("message").toString();
						logger.info("远程设参-RS4851存储时间-执行结果:" + re_RS4851CCSJ);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS4851存储时间-执行异常:" + e.getMessage());
					}
				// RS4851用途
				}else if(int_flag == 75){
					strToACSII = "AT+COMTYPE04@" + ((request.getParameter("RS4851YT") != null) ? request.getParameter("RS4851YT"):5);
					// 远程请求WebServer请求体
					urlBodyStr = JSONPacketUtils.JSONRemotePacket(getToken(), deviceCode, operType, strToACSII);
					// 设参请求体 编码处理
					logger.info("远程设参-RS4851用途-请求信息:" + urlBodyStr);
					String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
					String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
					try {
						String result_RS4851YT = httpUtils.get(requestURL);
						re_RS4851YT = JSONObject.fromObject(result_RS4851YT).get("message").toString();
						logger.info("远程设参-RS4851用途-执行结果:" + re_RS4851YT);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("远程设参-RS4851用途-执行异常:" + e.getMessage());
					}
				}
			}
		}
		
		// 全局返回结果判断
		if(re_dzsxs.equals("ok") || re_wdjcsj.equals("ok") || re_dlxs.equals("ok") || re_dyxs.equals("ok") || 
			re_sjzsl.equals("ok") || re_syslfz.equals("ok") || re_wsjcsj.equals("ok") || re_dbgldl.equals("ok") ||
			re_sltd1.equals("ok") || re_dltd.equals("ok") || re_bftd.equals("ok") ||
			
			re_DI0CJJG.equals("ok") ||  re_DI0CCJG.equals("ok") || re_DI0BJFS.equals("ok") || re_DI0YT.equals("ok") ||
			re_DI1CJJG.equals("ok") ||  re_DI1CCJG.equals("ok") || re_DI1BJFS.equals("ok") || re_DI1YT.equals("ok") ||
			re_DI2CJJG.equals("ok") ||  re_DI2CCJG.equals("ok") || re_DI2BJFS.equals("ok") || re_DI2YT.equals("ok") ||
			re_DI3CJJG.equals("ok") ||  re_DI3CCJG.equals("ok") || re_DI3BJFS.equals("ok") || re_DI3YT.equals("ok") ||
			
			re_JDQ0MRSC.equals("ok") || re_JDQ0YT.equals("ok") || re_JDQ1MRSC.equals("ok") || re_JDQ1YT.equals("ok") ||
			re_JDQ2MRSC.equals("ok") || re_JDQ2YT.equals("ok") || re_JDQ3MRSC.equals("ok") || re_JDQ3YT.equals("ok") ||
			re_ZKLX.equals("ok") || re_ZKJDQ.equals("ok") || re_DCSKSYC.equals("ok") || re_KJZKBH.equals("ok") ||
			
			re_JSQ0FS.equals("ok") || re_JSQ0XS.equals("ok") || re_JSQ0CCSJ.equals("ok") || re_JSQ0YT.equals("ok") ||
			re_JSQ1FS.equals("ok") || re_JSQ1XS.equals("ok") || re_JSQ1CCSJ.equals("ok") || re_JSQ1YT.equals("ok") ||
			re_JSQ2FS.equals("ok") || re_JSQ2XS.equals("ok") || re_JSQ2CCSJ.equals("ok") || re_JSQ2YT.equals("ok") ||
			re_JSQ3FS.equals("ok") || re_JSQ3XS.equals("ok") || re_JSQ3CCSJ.equals("ok") || re_JSQ3YT.equals("ok") ||
			
			re_RS2320BTL.equals("ok") || re_RS2320SJW.equals("ok") || re_RS2320CJJG.equals("ok") || re_RS2320CCSJ.equals("ok") || re_RS2320YT.equals("ok") ||
			re_RS2321BTL.equals("ok") || re_RS2321SJW.equals("ok") || re_RS2321CJJG.equals("ok") || re_RS2321CCSJ.equals("ok") || re_RS2321YT.equals("ok") ||
			re_RS4850BTL.equals("ok") || re_RS4850SJW.equals("ok") || re_RS4850CJJG.equals("ok") || re_RS4850CCSJ.equals("ok") || re_RS4850YT.equals("ok") ||
			re_RS4851BTL.equals("ok") || re_RS4851SJW.equals("ok") || re_RS4851CJJG.equals("ok") || re_RS4851CCSJ.equals("ok") || re_RS4851YT.equals("ok") 
		){
			result.put("message", "ok");
		}else{
			result.put("message", "error");
		}
		return result.toString();
	}
	
}