/**
 * 参数回读总方法（根据cursel_0调用参数回读区的方法）
 * cursel_0(1):水电设置;cursel_0(2):输入接口设置;cursel_0(3):继电器设置;cursel_0(4):计数器设置;
 * cursel_0(5):串口设置;cursel_0(6):基础参数;cursel_0(7):网络参数;cursel_0(8):存储设置;
 */
function readSerialData(deviceCode){
	$("#showDiv").css("display","block");
	var param = null;
	switch (cursel_0) {
	case 1://水电设置
		param = readWaterEleInfo(deviceCode);
		break;
	case 2://输入接口设置
		param = readSerialPortInfe(deviceCode);
			break;
	case 3://继电器设置
		param = readSerialPortInfoc(deviceCode);
		break;
	case 4://计数器设置
		param = readSerialPortInfoa(deviceCode);
		break;
	case 5://串口设置
		param = readSerialPortInfo(deviceCode);
		break;
	case 6://基础参数
		param = readSerialPortInfob(deviceCode);
		break;
	case 7://网络参数
		param = readSerialPortInfod(deviceCode);
	break;
	case 8://存储设置
		param = readSerialPortInfog(deviceCode);
		break;
	}
}
//===================================参数回读逻辑模块===========================================


/**
 * 水电设置（参数回读）
 * @param deviceCode
 */
function readWaterEleInfo(deviceCode){
	$.post("remote/readWaterEleInfo.do",{"deviceCode":deviceCode, flag:"1"},function(result){
		$("#showDiv").css("display","none");
		debugger
		var data = JSON.parse(result);
		if(!jQuery.isEmptyObject(data)){
			$("#SLTD1").val(data.WTRCHNNL01);
			$("#DLTD").val(data.ELECHNNL);
			document.getElementById("DZSXS").value = data.ELE2WTR;
			document.getElementById("WDJCSJ").value = data.NOELECLOSE;
			document.getElementById("DLXS").value = data.ELEENERGY;
			document.getElementById("DYXS").value = data.VOLMULTIPLE;
			document.getElementById("SJZSL").value = data.SEC2WTR;
			document.getElementById("SYSLFZ").value = data.LVWTRLRM;
			document.getElementById("WSJCSJ").value = data.CHCKWTRMIN;
			document.getElementById("DBGLDL").value = data.ELEFILTERA;
		}else{
			confirmMsgVity("参数回读失败，请检查设备是否在线...");
		}
		
	});
}

/**
 * 输入接口设置
 * @param deviceCode
 */
function readSerialPortInfe(deviceCode){
	$.post("remote/readWaterEleInfo.do",{"deviceCode":deviceCode, flag:"2"},function(result){
		$("#showDiv").css("display","none");
		//debugger
		var data = JSON.parse(result);
		if(!jQuery.isEmptyObject(data)){
		//采集时间
		document.getElementById("DI0CJJG").value =data.IIOCOLTIME01;
		document.getElementById("DI1CJJG").value =data.IIOCOLTIME02;
		document.getElementById("DI2CJJG").value =data.IIOCOLTIME03;
		document.getElementById("DI3CJJG").value =data.IIOCOLTIME04;
		//存储时间
		document.getElementById("DI0CCJG").value =data.IIOSVTIME01;
		document.getElementById("DI1CCJG").value =data.IIOSVTIME02;
		document.getElementById("DI2CCJG").value =data.IIOSVTIME03;
		document.getElementById("DI3CCJG").value =data.IIOSVTIME04;
		//报警方式
		var baojing1 = document.getElementById("DI0BJFS");
		　for(var i = 0; i < 6; i++){
			//alert(baojing1.options[i].text);
			if(baojing1.options[i].text == data.IIOLRMOP01+""){
			  $("#DI0BJFS").val(i);
			}
		}
		var baojing2 = document.getElementById("DI1BJFS");
		　for(var i = 0; i < 6; i++){
			if(baojing2.options[i].text == data.IIOLRMOP02+""){
			  $("#DI1BJFS").val(i);
			}
		}
		var baojing3 = document.getElementById("DI2BJFS");
		　for(var i = 0; i < 6; i++){
			if(baojing3.options[i].text == data.IIOLRMOP03+""){
			  $("#DI2BJFS").val(i);
			}
		}
		var baojing4 = document.getElementById("DI3BJFS");
		　for(var i = 0; i < 6; i++){
			if(baojing4.options[i].text == data.IIOLRMOP04+""){
			  $("#DI3BJFS").val(i);
			}
		}
		//用途
		var yontu1 = document.getElementById("DI3YT");
		　for(var i = 0; i < 4; i++){
			if(yontu1.options[i].text == data.IIOTYPE04+""){
			  $("#DI3YT").val(i);
			}
		}	
		var yontu2 = document.getElementById("DI2YT");
		　for(var i = 0; i < 4; i++){
			if(yontu2.options[i].text == data.IIOTYPE03+""){
			  $("#DI2YT").val(i);
			}
		}	
		var yontu3 = document.getElementById("DI1YT");
		　for(var i = 0; i < 4; i++){
			if(yontu3.options[i].text == data.IIOTYPE02+""){
			  $("#DI1YT").val(i);
			}
		}	
		var yontu4 = document.getElementById("DI0YT");
		　for(var i = 0; i < 4; i++){
			if(yontu4.options[i].text == data.IIOTYPE01+""){
			  $("#DI0YT").val(i);
			}
		}	
		}else{
			confirmMsgVity("参数回读失败，请检查设备是否在线...");
		}
	});
}
/**
 * 继电器设置
 * @param deviceCode
 */
function readSerialPortInfoc(deviceCode){
	$.post("remote/readWaterEleInfo.do",{"deviceCode":deviceCode, flag:"3"},function(result){
		$("#showDiv").css("display","none");
		//debugger
		var data = JSON.parse(result);
		if(!jQuery.isEmptyObject(data)){
		//继电器0默认输出
		var qidianqi0 = document.getElementById("JDQ0MRSC");
		　for(var i = 0; i < 1; i++){
			//alert(qidianqi0.options[i].text);
			if(qidianqi0.options[i].text == data.RELAYDEF01+""){
			  $("#JDQ0MRSC").val(i);
			}
		}
		//用途
		var yontu0 = document.getElementById("JDQ0YT");
		　for(var i = 0; i < 2; i++){
			if(yontu0.options[i].text == data.RELAYTYPE01+""){
			  $("#JDQ0YT").val(i);
			}
		}
		//继电器1默认输出
		var qidianqi1 = document.getElementById("JDQ1MRSC");
		　for(var i = 0; i < 1; i++){
			if(qidianqi1.options[i].text == data.RELAYDEF02+""){
			  $("#JDQ1MRSC").val(i);
			}
		}
		//用途
		var yontu1 = document.getElementById("JDQ1YT");
		　for(var i = 0; i < 2; i++){
			if(yontu1.options[i].text == data.RELAYTYPE02+""){
			  $("#JDQ1YT").val(i);
			}
		}
		//继电器2默认输出
		var qidianqi2 = document.getElementById("JDQ2MRSC");
		　for(var i = 0; i < 1; i++){
			if(qidianqi2.options[i].text == data.RELAYDEF03+""){
			  $("#JDQ2MRSC").val(i);
			}
		}
		//用途
		var yontu2 = document.getElementById("JDQ2YT");
		　for(var i = 0; i < 2; i++){
			if(yontu2.options[i].text == data.RELAYTYPE03+""){
			  $("#JDQ2YT").val(i);
			}
		}
		//继电器3默认输出
		var qidianqi3 = document.getElementById("JDQ3MRSC");
		　for(var i = 0; i < 1; i++){
			if(qidianqi3.options[i].text == data.RELAYDEF04+""){
			  $("#JDQ3MRSC").val(i);
			}
		}
		//用途
		var yontu3 = document.getElementById("JDQ3YT");
		　for(var i = 0; i < 2; i++){
			if(yontu3.options[i].text == data.RELAYTYPE04+""){
			  $("#JDQ3YT").val(i);
			}
		}
		//主控类型
		var zhuk = document.getElementById("ZKLX");
		　for(var i = 0; i < 1; i++){
			if(zhuk.options[i].text == data.RELAYMAN+""){
			  $("#ZKLX").val(i);
			}
		}
		//主控继电器
		var zhukj = document.getElementById("ZKJDQ");
		　for(var i = 0; i < 1; i++){
			if(zhukj.options[i].text == data.RLYTYPE+""){
			  $("#ZKJDQ").val(i);
			}
		}
		//电磁锁开锁延迟
		document.getElementById("DCSKSYC").value =data.LOCKDLY;
		//开机主控保护
		var kaij = document.getElementById("KJZKBH");
		　for(var i = 0; i < 1; i++){
			if(kaij.options[i].text == data.RLYMANPROT+""){
			  $("#KJZKBH").val(i);
			}
		}
		}else{
			confirmMsgVity("参数回读失败，请检查设备是否在线...");
		}
	});
}
//计数器设置
function readSerialPortInfoa(deviceCode){
	$.post("remote/readWaterEleInfo.do",{"deviceCode":deviceCode, flag:"4"},function(result){
		$("#showDiv").css("display","none");
		debugger
		var data = JSON.parse(result);
		if(!jQuery.isEmptyObject(data)){
		//计数器0方式
		var jishu0 = document.getElementById("JSQ0FS");
		//alert(jishu0.options[i].text);
		　for(var i = 0; i < 4; i++){
			if(jishu0.options[i].text == data.CNTROP01+""){
			  $("#JSQ0FS").val(i);
			}
		}
		//系数
		document.getElementById("JSQ0XS").value =data.CNTRUNIT01;
		//存储时间
		document.getElementById("JSQ0CCSJ").value =data.CNTRSVTIME01;
		//用途
		var yont0 = document.getElementById("JSQ0YT");
		　for(var i = 0; i < 2; i++){
			if(yont0.options[i].text == data.CNTRTYPE01+""){
			  $("#JSQ0YT").val(i);
			}
		}
		//计数器1方式
		var jishu1 = document.getElementById("JSQ1FS");
		　for(var i = 0; i < 3; i++){
			if(jishu1.options[i].text == data.CNTROP02+""){
			  $("#JSQ1FS").val(i);
			}
		}
		//系数
		document.getElementById("JSQ1XS").value =data.CNTRUNIT02;
		//存储时间
		document.getElementById("JSQ1CCSJ").value =data.CNTRSVTIME02;
		//用途
		var yont1 = document.getElementById("JSQ1YT");
		　for(var i = 0; i < 2; i++){
			if(yont1.options[i].text == data.CNTRTYPE02+""){
			  $("#JSQ1YT").val(i);
			}
		}
		//计数器2方式
		var jishu2 = document.getElementById("JSQ2FS");
		　for(var i = 0; i < 3; i++){
			if(jishu2.options[i].text == data.CNTROP03+""){
			  $("#JSQ2FS").val(i);
			}
		}
		//系数
		document.getElementById("JSQ2XS").value =data.CNTRUNIT03;
		//存储时间
		document.getElementById("JSQ2CCSJ").value =data.CNTRSVTIME03;
		//用途
		var yont2 = document.getElementById("JSQ2YT");
		　for(var i = 0; i < 2; i++){
			if(yont2.options[i].text == data.CNTRTYPE03+""){
			  $("#JSQ2YT").val(i);
			}
		}
		//计数器3方式
		var jishu3 = document.getElementById("JSQ3FS");
		　for(var i = 0; i < 3; i++){
			if(jishu3.options[i].text == data.CNTROP04+""){
			  $("#JSQ3FS").val(i);
			}
		}
		//系数
		document.getElementById("JSQ3XS").value =data.CNTRUNIT04;
		//存储时间
		document.getElementById("JSQ3CCSJ").value =data.CNTRSVTIME04;
		//用途
		var yont3 = document.getElementById("JSQ3YT");
		　for(var i = 0; i < 2; i++){
			if(yont3.options[i].text == data.CNTRTYPE04+""){
			  $("#JSQ3YT").val(i);
			}
		}
		}else{
			confirmMsgVity("参数回读失败，请检查设备是否在线...");
		}
	});
}
/**
 * 串口参数回读
 */
function readSerialPortInfo(deviceCode){
	//debugger
	$.post("remote/readWaterEleInfo.do",{"deviceCode":deviceCode, flag:"5"},function(result){
		$("#showDiv").css("display","none");
		//debugger
		var data = JSON.parse(result);
		if(!jQuery.isEmptyObject(data)){
			//=============	2320===========================	
			var RsBtl0 = document.getElementById("RS2320BTL");　
			for(var i = 0; i < 12; i++){
				//alert(myselect.options[i].text);
				if(RsBtl0.options[i].text == data.COMSPEED01+""){
				  $("#RS2320BTL").val(i);
				}
			}
			
			var RsSjw0 = document.getElementById("RS2320SJW");　
			for(var i = 0; i < 6; i++){
				//alert(myselect.options[i].text);
				if(RsSjw0.options[i].text == data.COMPARITY01+""){
				  $("#RS2320SJW").val(i);
				}
			}
			
			document.getElementById("RS2320CJJG").value = data.COMCOLTIME01;
			document.getElementById("RS2320CCSJ").value = data.COMSVTIME01;
			
			/*var RsYt0 = document.getElementById("RS2320YT");　
			for(var i = 0; i < 11; i++){
				//alert(myselect.options[i].text);
				if(RsYt0.options[i].text == data.COMTYPE01+""){
					alert(123);
				  $("#RS2320YT").val(i);
				}
			}*/
			
			$("#RS2320YT").val(data.COMTYPE01);
			//=============	2321===========================	
			
			var RsBtl1 = document.getElementById("RS2321BTL");　
			for(var i = 0; i < 12; i++){
				//alert(myselect.options[i].text);
				if(RsBtl1.options[i].text == data.COMSPEED02+""){
				  $("#RS2321BTL").val(i);
				}
			}
			
			var RsSjw1 = document.getElementById("RS2321SJW");　
			for(var i = 0; i < 6; i++){
				//alert(myselect.options[i].text);
				if(RsSjw1.options[i].text == data.COMPARITY02+""){
				  $("#RS2321SJW").val(i);
				}
			}
			
			document.getElementById("RS2321CJJG").value = data.COMCOLTIME02;
			document.getElementById("RS2321CCSJ").value = data.COMSVTIME02;
			
			/*var RsYt1 = document.getElementById("RS2321YT");　
			for(var i = 0; i < 11; i++){
				//alert(myselect.options[i].text);
				if(RsYt1.options[i].text == data.COMTYPE02+""){
				  $("#RS2321YT").val(i);
				}
			}*/
			$("#RS2321YT").val(data.COMTYPE02);
			//=============	4851===========================	
			
			var Rs485Btl0 = document.getElementById("RS4850BTL");　
			for(var i = 0; i < 12; i++){
				//alert(myselect.options[i].text);
				if(Rs485Btl0.options[i].text == data.COMSPEED03+""){
				  $("#RS4850BTL").val(i);
				}
			}
			
			var Rs485Sjw0 = document.getElementById("RS4850SJW");　
			for(var i = 0; i < 6; i++){
				//alert(myselect.options[i].text);
				if(Rs485Sjw0.options[i].text == data.COMPARITY03+""){
				  $("#RS4850SJW").val(i);
				}
			}
			document.getElementById("RS4850CJJG").value = data.COMCOLTIME03;
			document.getElementById("RS4850CCSJ").value = data.COMSVTIME03;
			
			/*var Rs485Yt0 = document.getElementById("RS4850YT");　
			for(var i = 0; i < 11; i++){
				//alert(myselect.options[i].text);
				if(Rs485Yt0.options[i].text == data.COMTYPE03+""){
				  $("#RS4850YT").val(i);
				}
			}*/
			$("#RS4850YT").val(data.COMTYPE03);
			//=============	4852===========================	
			

			var Rs485Btl1 = document.getElementById("RS4851BTL");　
			for(var i = 0; i < 12; i++){
				//alert(myselect.options[i].text);
				if(Rs485Btl1.options[i].text == data.COMSPEED04+""){
				  $("#RS4851BTL").val(i);
				}
			}
			
			var Rs485Sjw1 = document.getElementById("RS4851SJW");　
			for(var i = 0; i < 6; i++){
				//alert(myselect.options[i].text);
				if(Rs485Sjw1.options[i].text == data.COMPARITY04+""){
				  $("#RS4851SJW").val(i);
				}
			}
			document.getElementById("RS4851CJJG").value = data.COMCOLTIME04;
			document.getElementById("RS4851CCSJ").value = data.COMSVTIME04;
			
			/*var Rs485Yt1 = document.getElementById("RS4851YT");　
			for(var i = 0; i < 11; i++){
				//alert(myselect.options[i].text);
				if(Rs485Yt1.options[i].text == data.COMTYPE04+""){
				  $("#RS4851YT").val(i);
				}
			}*/
			
			$("#RS4851YT").val(data.COMTYPE04);
		}else{
			confirmMsgVity("参数回读失败，请检查设备是否在线...");
		}
 	});
}
/**
 * 基础参数
 * @param deviceCode
 */
function readSerialPortInfob(deviceCode){
	$.post("remote/readWaterEleInfo.do",{"deviceCode":deviceCode, flag:"6"},function(result){
		$("#showDiv").css("display","none");
		//debugger
		var data = JSON.parse(result);
		if(!jQuery.isEmptyObject(data)){
		
		document.getElementById("SBID").value = data.IDNT;
		document.getElementById("JJXZM").value = data.NFCUSERID01;
		document.getElementById("DHHM").value = data.PHON;
		document.getElementById("JJSGM").value = data.NFCUSERID02;
		}else{
			confirmMsgVity("参数回读失败，请检查设备是否在线...");
		}
	});
}
/**
 * 网络参数
 * @param deviceCode
 */
function readSerialPortInfod(deviceCode){
	$.post("remote/readWaterEleInfo.do",{"deviceCode":deviceCode, flag:"7"},function(result){
		$("#showDiv").css("display","none");
		//debugger
		var data = JSON.parse(result);
		if(!jQuery.isEmptyObject(data)){
		//GPRS工作模式
		var gzmo = document.getElementById("GPRSG");
		　for(var i = 0; i < 1; i++){
			if(gzmo.options[i].text == data.NETMODE01+""){
			  $("#GPRSG").val(i);
			}
		}
		//主中心地址+端口
		document.getElementById("ZXDZ").value = data.MULTISER01;
		document.getElementById("DKH").value = data.MULTIPORT01;
		//备主中心地址+端口
		document.getElementById("BZXDZ").value = data.BACKSER;
		document.getElementById("BDKH").value = data.BACKPORT;
		//域名解析DNS参数
		document.getElementById("ZYM").value = data.MULTIDNS01;
		document.getElementById("BYM").value = data.BACKDNS;
		//
		document.getElementById("XTSJ").value = data.HRTTIME;
		document.getElementById("SDB").value = data.UPNTRVL;
		document.getElementById("ZLB").value = data.UPINCVAL;
		}else{
			confirmMsgVity("参数回读失败，请检查设备是否在线...");
		}
	});
}
/**
 * 存储设置
 * @param deviceCode
 */
function readSerialPortInfog(deviceCode){
	$.post("remote/readWaterEleInfo.do",{"deviceCode":deviceCode, flag:"8"},function(result){
		$("#showDiv").css("display","none");
		//debugger
		var data = JSON.parse(result);
		if(!jQuery.isEmptyObject(data)){
		//存储配置   计数器
		document.getElementById("JSQ0CD").value = data.CNTSVLEN01;
		document.getElementById("JSQ1CD").value = data.CNTSVLEN02;
		document.getElementById("JSQ2CD").value = data.CNTSVLEN03;
		document.getElementById("JSQ3CD").value = data.CNTSVLEN04;
		//RS232
		document.getElementById("RS2320").value = data.COMSVLEN01;
		document.getElementById("RS2321").value = data.COMSVLEN02;
		//RS485
		document.getElementById("RS4850").value = data.COMSVLEN03;
		document.getElementById("RS4851").value = data.COMSVLEN04;
		//刷卡记录存储长度
		document.getElementById("SKJL").value = data.NFCSVLEN;
		//擦除记录
		var ccju = document.getElementById("CCJU");
		　for(var i = 0; i < 1; i++){
			if(ccju.options[i].text == data.IFERASESV+""){
			  $("#CCJU").val(i);
			}
		}
		}else{
			confirmMsgVity("参数回读失败，请检查设备是否在线...");
		}
	});
}

/**
 * 弹出框的调用方法
 * @param str
 */
function confirmMsgVity(str){
	$(".popup_0").css("display","none");
	var confirmMsg = {
			title : "提示",
			content : "<center>"+str+"</center>",
			tbar : [{
				// 点击取消，则关闭弹出框，并关闭背景加载信息
				text : "取消",
				clsName : "homebg popup-confirm",
				handler : function(thisObj) {
					$.Popup.close(thisObj);
					hideMark();
				}
			}]
		};
	$.Popup.create(confirmMsg);
}