var monitorTreeObj;
var iden_flag;
var RegPrice = /^[1-9]+\d*$/;
var allList = [];
var mayAmout = "";
var cardCode = "";
var year = "";
var round = "";
var price = "";
var waterNum = "";
var money = "";
var chargeWl = "";
var distWaterId = "";
$(function(){
	
	getGroupTree();
});
function getGroupTree(){
	$.ajax({
		type:"get",
		async:false,
		cache:false,
		url:"monitor/areaChoose.do",
		success:function(data){
			if(data == "success"){
				iden_flag = data;
				// 只配置水管区域   则按照水管区域查询平台机井信息
				monitorTreeObj = ztreeFun($("#ztree"),"sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false",distanData);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				monitorTreeObj = ztreeFun($("#ztree"),"sysArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false",distanData);
			}
		}
	});
}

/**
 * 远程充值数据查询方法
 * @param item
 */
function distanData(){
	debugger
	var areaIds = getNodeIdsByTreeId("ztree");
	var deviceCode = $("#deviceCode").val();
	if (deviceCode == '请输入机井编码') {
		deviceCode = "";
	}
	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	var deviceWellUse = $("select[name='deviceWellUse'] option:selected").val();
	// 参数集合
	var params = {
		nodeId : areaIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName,
		deviceWellUse:deviceWellUse,
	};
	// 加载远程信息列表
	loadRemoteDataInfo(params);
}

/**
 * 查询数据公共方法
 * @param params
 */
function loadRemoteDataInfo(params){
	debugger
	showMark();
	$.post("distance_charge/distanceDataList.do",params, function(data) {
		$("#baseWaterChargeContent").html(data);
		hideMark();
	});
	/*$.post("distance_charge/distanceDataList.do",params, function(data) {
		debugger
		console.log("12123");
		$("#baseWaterChargeContent").html(data);
		hideMark();
	});*/
}

/**
 * 分页方法
 */
function changeDataPage(node){
	debugger
	debugger
	var areaIds = getNodeIdsByTreeId("ztree");
	var deviceCode = $("#deviceCode").val();
	if (deviceCode == '请输入机井编码') {
		deviceCode = "";
	}
	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	var deviceWellUse = $("select[name='deviceWellUse'] option:selected").val();
	// 参数集合
	var params = {
		nodeId : areaIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName,
		pageNo:node,
		deviceWellUse:deviceWellUse,
	};
	loadRemoteDataInfo(params);
}

/**
 * input框的输入值实时更新
 * @param even
 */

function handleChangeWater(even){
	debugger
	price = $("td[name='price']").text();
	waterNum = $("input[id='waterIpu']").val();
	if(price!=""||price!=null){
		money = (Number(waterNum)*Number(price)).toFixed(2);
		$("td[name='payMoney']").text(money);
	}
}

/**
 * 远程充值单个设备方法
 * @param item
 */
var contentMsg;
function distanPay(id,deviceCode,deviceName,netState,pumpState,wellUse,sJArea,sJSupplyWaterPeople,industryApprovedWater){
	mayAmout = "";
	cardCode = "";
	year = "";
	round = "";
	price = "";
	waterNum = "";
	money = "";
	chargeWl = "";
	distWaterId = "";
	if(netState==0){//设备离线
		alert("当前机井为离线状态，无法进行远程充值...");
	}else{//设备在线
		debugger
		hideMark();
		contentMsg = {
				title : deviceName+"【--远程充值】",
				url : "distance_charge/distanPay.do",
				width : "950",
				height : "420",
				left:"25%",
				paraData:{id:id,deviceCode:deviceCode,netState:netState,pumpState:pumpState,wellUse:wellUse,sJArea:sJArea,sJSupplyWaterPeople:sJSupplyWaterPeople,industryApprovedWater:industryApprovedWater},
				requestMethod: 'ajax',
				tbar: [{
					text: "确定充值",
					clsName: "com_btn",
					handler: function (thisPop) {
						if(vityData()){
							$("#showDiv").css("display","block");
							$.post("remote/remoteRequestOperate.do",{"deviceCode":deviceCode,"flag":"213","command":cardCode},function(data){// 充值查询
								debugger
								if(data!=""&&data!=null){
									if(JSON.parse(data).message=="ok"){
										var sbuf=JSON.parse(data).payload.Result;
										if(sbuf!="null"){
											 var pNum = querycard(sbuf);
											 $("#loadText").text("充值查询成功，请等待...");
											 $("div[role='progressbar']")['0'].style.width="60%";
											 $.post("remote/remoteRequestOperate.do",{"deviceCode":deviceCode,"flag":"232","command":cardCode,"prepaidNumber":pNum,"chargePl":price,"chargeWl":waterNum},function(data){
												 debugger
												 if(data!=""&&data!=null){
													 if(JSON.parse(data).message=="ok"){
														 var sbuf=JSON.parse(data).payload.Result;
														 if(sbuf!="null"){
															 $("#loadText").text("充值成功，还有最后一步...");
															 $("div[role='progressbar']")['0'].style.width="80%";
															 $.post("remote/remoteRequestOperate.do",{"deviceCode":deviceCode,"flag":"213","command":cardCode},function(result){// 充值查询
																 if(data!=""&&data!=null){
																		debugger
																		if(JSON.parse(result).message=="ok"){
																			var sbuf=JSON.parse(result).payload.Result;
																			if(sbuf!="null"){
																				 var pNum1 = querycard(sbuf);
																				 if((pNum+1)==pNum1){
																					 $("div[role='progressbar']")['0'].style.width="100%";
																					 $("#loadText").text("恭喜，充值成功...");
																					 $("#myAlert").css("display","block");
																					 setTimeout(function(){$.Popup.close(thisPop);},1300);
																					 distanceVityDataSource(id,deviceCode);//入库操作
																				 }else{
																					 $.Popup.close(thisPop);
																					 confirmMsgVity("充值查询失败，请查看设备是否在线...");	 
																				 }
																			}else{
																				 $.Popup.close(thisPop);
																				 confirmMsgVity("充值查询失败，请查看设备是否在线...");	 
																			 }
																		}
																 }else{
																	 $.Popup.close(thisPop);
																	 confirmMsgVity("充值查询失败，请查看设备是否在线...");	 
																 }
															 });
														 }else{
															 $.Popup.close(thisPop);
															 confirmMsgVity("充值查询失败，请查看设备是否在线...");	 
														 }
													 }
												 }else{
													 $.Popup.close(thisPop);
													 confirmMsgVity("充值查询失败，请查看设备是否在线...");	 
												 }
											});
										}else{
											$.Popup.close(thisPop);
											confirmMsgVity("充值查询失败，请查看设备是否在线...");	
										}
									}else{
										$.Popup.close(thisPop);
										confirmMsgVity("当前设备不在线，请检查设备");
									}
								}else{
									$.Popup.close(thisPop);
									confirmMsgVity("当前设备不在线，请检查设备");
								}
							});
						}
					}
				}]
			};
			$.Popup.create(contentMsg);
	}
}

function distanceVityDataSource(id,deviceCode){
	var param = {
			deviceCode:deviceCode,
			cardCode:cardCode,
			distWaterPlanId:distWaterId,
			distYear:year,
			distRound:round,
			chargeAmount:money,
			waterAmount:waterNum,
			unitPrice:price,
			remark:''
	};
	debugger
	$.post("/distance_charge/addRptCharge.do",{vityAret:JSON.stringify(param),id:id},function(data){});
}

/**
 * 充值次数返回结果
 */
function querycard(sbuf){
	var NN=sbuf.substring(28, 36);// 解析出充值次数
	 var N1=NN.substring(6,8);
	 var N2=NN.substring(4,6);
	 var N3=NN.substring(2,4);
	 var N4=NN.substring(0,2);
	 var pNum=parseInt(N1+N2+N3+N4,16);
	 return pNum;
}

/**
 * 弹出框的调用方法
 * @param str
 */
function confirmMsgVity(str){
	$("#showDiv").css("display","none");
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
/**
 * 下拉框的选择事件
 */
function handleChange(sign,event){
	debugger
	switch (sign) {
	case 'card'://选择卡号
		cardCode = event.value;
		$("td[name='cardCode']").text(cardCode);
		break;
	case 'year'://选择年份
		formClear('year');
		year = event.value;
		$("td[name='year']").text(year);
		selectAddOptionYear(year,"");
		break;
	case 'round'://选择伦次
		formClear('round');
		round = event.value;
		$("td[name='round']").text(round);
		selectAddOptionYearOrRound(year,round);
		break;
	default:
		break;
	}
}

/**
 * 选择年份进行相关操作
 * @param year
 * @param round
 */
function selectAddOptionYear(year){
	debugger
	if(year!=null&&year!=''&&allList!=""&&allList!=null){
		for(let index in allList) {  
	        if(allList[index].distYear==Number(year)){
	        	for(var i = 0;i<allList[index].children.length;i++){
	        		var round = allList[index].children[i].distRound;
	        			$("#query_round").append('<option value="'+round+'" name="tem">'+round+'</option>');
	        	}
	        	
	        }
	    }
	}
}

/**
 * 选择轮次进行相关操作
 * @param year
 * @param round
 */
function selectAddOptionYearOrRound(year,round){
	debugger
	if(year!=null&&year!=''&&allList!=""&&allList!=null&&round!=""&&round!=null){
		for(let index in allList) {
	        if(allList[index].distYear==Number(year)){
	        	for(let indt in allList[index].children){
	        		if(allList[index].children[indt].distRound == Number(round)){
	        			debugger
	        			distWaterId = allList[index].children[indt].id;
	        			$("td[name='price']").text(allList[index].children[indt].distPrice);
	        			$("td[name='sumWaterAmout']").text(allList[index].children[indt].sumWaterAmout);
	        			$("td[name='waterAmout']").text(allList[index].children[indt].waterAmout);
	        			mayAmout = allList[index].children[indt].mayAmout;
	        			$("td[name='mayAmout']").text(mayAmout);
	        		}
	        	}
	        }
	    }
	}
}

/**
 * 选择年份和轮次先清空表格数据
 * @param sign
 */
function formClear(sign){
	switch(sign){
	case 'year':
		$("#query_round").find("option[name='tem']").remove(); 
		$("td[name='price']").text('');
		$("td[name='sumWaterAmout']").text('');
		$("td[name='waterAmout']").text('');
		$("td[name='mayAmout']").text('');
		$("td[name='year']").text('');
		$("td[name='round']").text('');
		break;
	case 'round':
		//$("#query_round").find("option[name='tem']").remove(); 
		$("td[name='price']").text('');
		$("td[name='sumWaterAmout']").text('');
		$("td[name='waterAmout']").text('');
		$("td[name='mayAmout']").text('');
		break;
	}
}



/**
 * 表单验证方法
 */
function vityData(){
	var vity = false;
	debugger
	waterNum = $("input[id='waterIpu']").val();
	if(cardCode==null||cardCode==""){
		alert("请选择需要充值的卡号...");
		vity = false;
	}else if(year==null||year==""){
		alert("请选择配水年份...");
		vity = false;
	}else if(round==null||round==""){
		alert("请选择配水轮次...");
		vity = false;
	}else if(waterNum==null||waterNum==""){
		alert("请输入充值水量...");
		vity = false;
	}else if(!RegPrice.test(Number(waterNum))){
		alert("水量数据格式错误，请输入大于0的正整数...");
		vity = false;
	}else if(Number(waterNum)<0||Number(waterNum)==0){
		alert("水量数据格式错误，请正确填写...");
		vity = false;
	}else if(Number(mayAmout)<Number(waterNum)){
		alert("当前购买水量不能超过可充值水量...");
		vity = false;
	}else{
		vity = true;
	}
	return vity;
}
