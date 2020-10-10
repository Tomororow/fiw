var timeDataTicket;
var monitorTreeObj;
var mapObj;
var historyCount = 0;
var iden_flag;			// 水管区域和行政区域切换标识结果
var re_flag = true;		// 远程设参表单校验标识
var btn_flag = 1;		// 设参按钮禁用标识

/**
 * 登陆检测是否有异常参数信息
 * 	1、若是有异常参数信息，则以弹出框形式弹出
 * 	2、若是无异常参数信息，则不弹出信息
 */
$(function(){
	var intelAnalysisCount = $("#intelAnalysisCount").val();
	intelAnalysisCount = 0;
	if(intelAnalysisCount>0) {
		showMark();
		var confirmMsg = {
			title : "提示",
			content : "<center>您有未解决的异常机井信息<br/>是否现在去处理？</center>",
			tbar : [ {
				text : "确定",
				clsName : "homebg popup-confirm",
				handler : function(thisPop) {
					// 如果点击确定，则将页面跳转到告警查询页面，这里onclick*=必须用history，这样才能让头部菜单指向告警查询
					var m = $(".top .top_content li a[onclick*=history]").parent();
					// 设置被选中的菜单，并将class=sell，只有class=sell的菜单，才能显示被选中状态
					if (m != undefined && m.length > 0) {
						var s = m.siblings();
						m.find("a").addClass("sell");
						s.find("a").removeClass("sell");
					}
					// 二级菜单跳转到异常机井智能分析菜单上
					var tag = "intelligentAnalysis";
					showContent("history/index.do?menuId=3&stcd=" + null + "&tag=" + tag, 'contain');
					// 点击确定后，关闭弹出框
					$.Popup.close(thisPop);
					// 关闭背景加载信息
					hideMark();
				}
			}, {
				// 点击取消，则关闭弹出框，并关闭背景加载信息
				text : "取消",
				clsName : "homebg popup-cancel",
				handler : function(thisObj) {
					$.Popup.close(thisObj);
					hideMark();
				}
			}]
		};
		// 加载出弹出面谈
		$.Popup.create(confirmMsg);
	}
	// 加载左侧区域树菜单
	getGroupTree();
});

// 获取行政树或者水管区域树
function getGroupTree() {
	$(".tree").height($(".left").outerHeight() - 10);
	if ($(".button").find("li a:eq(0)").attr("class") == "sell" && $("#two_nav").find("li:eq(0)").attr("class") == "sell") {
		// 电子地图
		$.ajax({
			type:"get",
			async:false,
			cache:false,
			url:"monitor/areaChoose.do",
			success:function(data){
				if(data == "success"){
					iden_flag = data;
					debugger
					// 只配置水管区域   则按照水管区域查询平台机井信息
					monitorTreeObj = ztreeFun($("#ztree"),"sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false",treeOnClick);
				}else if(data == "failed"){
					iden_flag = data;
					// 水管区域和行政区域同时配置  则按照行政区域查询
					monitorTreeObj = ztreeFun($("#ztree"),"sysArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false",treeOnClick);
				}
			}
		});
	} else if ($(".button").find("li a:eq(0)").attr("class") == "sell" && $("#two_nav").find("li:eq(2)").attr("class") == "sell") {
		// 实时数据
		$.ajax({
			type:"get",
			async:false,
			cache:false,
			url:"monitor/areaChoose.do",
			success:function(data){
				if(data == "success"){
					iden_flag = data;
					// 只配置水管区域   则按照水管区域查询平台机井信息
					monitorTreeObj = ztreeFun($("#ztree"),"sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false",dataList);
				}else if(data == "failed"){
					iden_flag = data;
					// 水管区域和行政区域同时配置  则按照行政区域查询
					monitorTreeObj = ztreeFun($("#ztree"),"sysArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false",dataList);
				}
			}
		});
	} else {
		// 远程监控
		$.ajax({
			type:"get",
			async:false,
			cache:false,
			url:"monitor/areaChoose.do",
			success:function(data){
				if(data == "success"){
					iden_flag = data;
					// 只配置水管区域   则按照水管区域查询平台机井信息
					monitorTreeObj = ztreeFun($("#ztree"),"sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false",remoteList);
				}else if(data == "failed"){
					iden_flag = data;
					// 水管区域和行政区域同时配置  则按照行政区域查询
					monitorTreeObj = ztreeFun($("#ztree"),"sysArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false",remoteList);
				}
			}
		});
	}
}

/**
 * 远程监控列表获取左侧行政区域树形栏所有信息
 */
function remoteList(node) {
	// 远程监控查询条件
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
	// 设备版本
	var deviceVersion = $("select[name='deviceVersion'] option:selected").val();
	// 网络状态
	var netState = $("select[name='netState'] option:selected").val();
	// 水泵状态
	var pumpState = $("select[name='pumpState'] option:selected").val();
	
	// 参数集合
	var params = {
		nodeIds : areaIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName,
		deviceVersion : deviceVersion,
		netState : netState,
		pumpState : pumpState
	};
	
	// 加载远程信息列表
	loadRemoteDataInfo(params);
}

/**
 * 远程监控分页查询
 * @param page
 */
function changeRemotePage(page) {
	// 获取远程信息分页条件
	var aryIds = getNodeIdsByTreeId("ztree");
	var deviceCode = $("#deviceCode").val();
	if (deviceCode == '请输入机井编码') {
		deviceCode = "";
	}
	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	// 设备版本
	var deviceVersion = $("select[name='deviceVersion'] option:selected").val();
	// 网络状态
	var netState = $("select[name='netState'] option:selected").val();
	// 水泵状态
	var pumpState = $("select[name='pumpState'] option:selected").val();
	
	// 参数集合
	var params = {
		pageNo : page,
		nodeIds : aryIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName,
		deviceVersion : deviceVersion,
		netState : netState,
		pumpState : pumpState
	};
	// 加载远程信息列表
	loadRemoteDataInfo(params);
}

/**
 * 远程监控 按条件查询
 */
function remoteDeviceInfo() {
	var aryIds = getNodeIdsByTreeId("ztree");
	// 机井编码
	var deviceCode = $("#deviceCode").val();
	if (deviceCode == '请输入机井编码') {
		deviceCode = "";
	}
	// 机井名称
	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	// 设备版本
	var deviceVersion = $("select[name='deviceVersion'] option:selected").val();
	// 网络状态
	var netState = $("select[name='netState'] option:selected").val();
	// 水泵状态
	var pumpState = $("select[name='pumpState'] option:selected").val();
	
	var deviceWellUse = $("select[name='deviceWellUse'] option:selected").val();
	
	var params = {
		nodeIds : aryIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName,
		deviceVersion : deviceVersion,
		netState : netState,
		deviceWellUse:deviceWellUse,
		pumpState : pumpState
	};
	loadRemoteDataInfo(params);
}

/**
 * 远程监控列表查询
 * @param params
 */
function loadRemoteDataInfo(params, flag) {
	debugger
	showMark();
	flag = false;
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.post("monitor/remoteListByWaterArea.do", params, function(data) {
			$("#remoteContent").html(data);
			if (flag == undefined || !flag) {
				hideMark();
			} else {
				hideMarkLoading();
			}
		});
	}else if(iden_flag == "failed"){
		// 行政区域
		$.post("monitor/remoteList.do", params, function(data) {
			$("#remoteContent").html(data);
			if (flag == undefined || !flag) {
				hideMark();
			} else {
				hideMarkLoading();
			}
		});
	}
}

/**
 * 远程监控 重置
 */
function remoteReset() {
	$("#deviceCode").val("请输入机井编码");
	$("#deviceName").val("请输入机井名称");
	var aryIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	loadRemoteDataInfo(params);
}

/**
 * 实时数据分页查询
 * @param page
 */
function changeDataPage(page) {
	var aryIds = getNodeIdsByTreeId("ztree");
	var deviceCode = $("#deviceCode").val();
	if (deviceCode == '请输入机井编码') {
		deviceCode = "";
	}
	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	
	// 参数集合
	var params = {
		pageNo : page,
		nodeIds : aryIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName
	};
	loadDataInfo(params);
}

/**
 * 加载实时数据
 */
function loadDataInfo(params, flag) {
	showMark();
	$.post("monitor/dataList.do", params, function(data) {
		$("#dataContent").html(data);
		if (flag == undefined || !flag) {
			hideMark();
		} else {
			hideMarkLoading();
		}
	});
}

/**
 * 地图
 */
function treeOnClick() {
	loadMapInfo();
}

function changeOnLine() {
	getGroupTree();
}

/**
 * 地图实时数据分页查询
 */
function changeMapDataPage(page) {
	// 获取远程信息分页条件
	var aryIds = getNodeIdsByTreeId("ztree");
	var deviceCode = $("#deviceCode").val();
	if (deviceCode == '请输入机井编码') {
		deviceCode = "";
	}
	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	
	// 参数集合
	var params = {
		pageNo : page,
		nodeIds : aryIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName
	};
	loadFactorList(params);
}

/**
 * 地图实时数据加载
 * @param params
 */
function loadFactorList(params) {
	if (params == undefined)
		params = {};
	var aryIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	/*$.post("monitor/mapData.do", params, function(data) {
		$(".map_right .map_right_content").html(data);
	});*/
}

/**
 * 实时数据重置
 */
function reset() {
	$("#deviceCode").val("请输入机井编码");
	$("#deviceName").val("请输入机井名称");
	var aryIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	loadDataInfo(params);
}

/**
 * 实时数据条件查询
 */
function search() {
	showMark();
	var deviceCode = $("#deviceCode").val();
	if (deviceCode == '请输入机井编码') {
		deviceCode = "";
	}
	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	var aryIds = getNodeIdsByTreeId("ztree");
	var params = {
		nodeIds : aryIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName
	};
	loadDataInfo(params);
}

/**
 * 加载地图信息
 */
function loadMapInfo() {
	debugger
	// 显示加载图案
	showMark();
	params = {};
	var aryIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.post("monitor/mapInfoByWaterArea.do", params, function(data) {
			$("#mapContent").html(data);
			hideMark();
			loadFactorList();
		});
	}else if(iden_flag == "failed"){
		// 行政区域
		$.post("monitor/mapInfo.do", params, function(data) {
			$("#mapContent").html(data);
			hideMark();
			loadFactorList();
		});
	}
}

/**
 * 详细信息
 */
function detailInfo(id,deviceCode) {
	var contentMsg = {
		title : "实时详细数据",   
		url : "monitor/detailInfoPage.do",
		width : "950",
		height : "450",
		paraData:{id:id,deviceCode:deviceCode},
		requestMethod: 'ajax',
		tbar: [{
			text: "关闭",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				closeDetailInfo(thisPop, "monitor/detailInfo.do");
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 关闭详细信息窗口
 * @param thisPop, url
 */
function closeDetailInfo(thisPop, url) {
	$.Popup.close(thisPop);
}

/**
 * 历史数据
 * sign:0:时段报历史数据;1:开关泵历史记录
 */
function historyData(id,sign){
	var contentMsg = {
		title : sign=='0'?"时段历史用水数据":"开关泵历史用水数据",
		url : 'monitor/dataHistoryHead.do',
		width : "1200",
		height : "560",
		paraData:{id:id,sign:sign},
		requestMethod: 'ajax',
		tbar: [{
			text: "关闭",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				closeDetailInfo(thisPop, "monitor/detailInfo.do");
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 控制器遥测下发查询历史数据
 * sign:0:历史记录;1:遥测记录
 */
function requestData(deviceCode,id,eign){
	var contentMsg = {
		title : eign=='1'?"遥测上下卡历史数据":"上下卡历史记录",   
		url : "monitor/requestHistoryDataHead.do",
		width : eign=='0'?"1200":"1000",
		height : "560",
		paraData:{deviceCode:deviceCode,eign:eign,id:id},
		requestMethod: 'ajax',
		tbar: [{
			text: "关闭",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				closeDetailInfo(thisPop, "monitor/detailInfo.do");
				hideMark();
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 设备异常及跳闸记录表
 * @param deviceCode
 * @param id
 * @param disn:0:设备异常记录表;1:设备跳闸记录表
 */
function unusualHstoryData(deviceCode,id,disn){
	var contentMsg = {
			title : disn=='0'?"设备异常历史记录":"主控跳闸历史记录",   
			url : "monitor/unusualHstoryDataPage.do",
			width : "1000",
			height : "560",
			paraData:{deviceCode:deviceCode,disn:disn,id:id},
			requestMethod: 'ajax',
			tbar: [{
				text: "关闭",
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					//closeDetailInfo(thisPop, "monitor/detailInfo.do");
					hideMark();
				}
			}]
		};
	$.Popup.create(contentMsg);
}

function unusualHstoryList(deviceCode,disn){
	var beginTime = $("#query_beginTime").val();
	var endTime = $("#query_endTime").val();
	params['beginTime'] = beginTime;
	params['endTime'] = endTime;
	params['deviceCode'] = deviceCode;
	params['disn'] = disn;
	$.post("monitor/unusualHstoryList.do", params, function(data) {
		$("#unusualHstoryDataContent").html(data);
	});
}

/**
 * 历史数据条件查询
 */
function historyConditionList(id,sign){
	// 参数集合
	params = {};
	// 获取jsp页面属性值
	var beginTime = $("#query_beginTime").val();
	var endTime = $("#query_endTime").val();
	var cardCode = $("select[name='query_cardCode'] option:selected").val();
	// 给集合赋值
	params['id'] = id;
	params['beginTime'] = beginTime;
	params['endTime'] = endTime;
	params['cardCode'] = cardCode;
	params['sign'] = sign;
	// 重新加载历史数据
	loadMonitorHistoryData(params);
}

/**
 * 加载历史数据
 */
function loadMonitorHistoryData(params, flag){
	showMark();
	$.post("monitor/historyPage.do", params, function(data){
		$("#monitorHistoryDataContent").html(data);
		if(flag==undefined || !flag){
			showMark();
		}else{
			hideMarkLoading();
		}
	});
}

/**
 * 控制器遥测下发查询历史数据条件查询
 */
function requestHistoryDataList(deviceCode,eign){
	debugger
	// 参数集合
	params = {};
	// 获取jsp页面属性值
	var beginTime = $("#query_beginTime").val();
	var endTime = $("#query_endTime").val();
	// 给集合赋值
	params['deviceCode'] = deviceCode;
	params['beginTime'] = beginTime;
	params['endTime'] = endTime;
	var cardCode = $("select[name='query_cardCode'] option:selected").val();
	params['cardCode'] = cardCode;
	params['eign'] = eign;
	if(eign=='0'){
		requestHistoryQuery(params);
	}else{
		// 重新加载历史数据
		alert("指令已下发！等待控制器相应，请耐心等待。。。。。");
		loadRequestHistoryData(params);
	}
}
/**
 * 上下卡历史记录查询
 */
function requestHistoryQuery(params){
	debugger
	$.post("monitor/requestHistoryQuery.do", params, function(data){
		$("#requestHistoryDataContent").html(data);
	});
}

/**
 * 加载控制器遥测历史数据
 */
function loadRequestHistoryData(params, flag){
	$('#historyConditionBtn').attr("disabled",true);
	$.post("monitor/requestHistoryPage.do", params, function(data){
		if(data=="1"){
			breakConditionList(params.deviceCode,params.eign);
		}else{
			alert("未得到控制器的回应信息，查看控制器在线后查询！");
		}
		$('#historyConditionBtn').attr("disabled",false);
	});
}

/**
 * 刷新控制器遥测查询历史数据
 */
function breakConditionList(deviceCode,eign){
	params = {};
	
	// 获取jsp页面属性值
	var beginTime = $("#query_beginTime").val();
	var endTime = $("#query_endTime").val();
	
	// 给集合赋值
	params['deviceCode'] = deviceCode;
	params['beginTime'] = beginTime;
	params['endTime'] = endTime;
	params['eign'] = eign;
	$.post("monitor/breakConditionList.do", params, function(data){
		$("#requestHistoryDataContent").html(data);
	});
}

/**
 * 报警数据
 */
function alarmData(stcd) {
	var m = $(".top .top_content li a[onclick*=history]").parent();
	if (m != undefined && m.length > 0) {
		var s = m.siblings();
		m.find("a").addClass("sell");
		s.find("a").removeClass("sell");
	}
	var tag = "alarm";
	showContent("history/index.do?menuId=77331726ba894b1787cf0edc7499b981&stcd="+ stcd + "&tag=" + tag, 'contain');
}

/**
 * 实时数据弹出框信息搜索
 */
function searchDeviceCode() {
	if (params == undefined)
		params = {};
	var aryIds = getNodeIdsByTreeId("ztree");
	var deviceCode = $("#deviceCode").val();
	if(deviceCode=='请输入机井编码' || undefined==deviceCode || ""==deviceCode) {
		deviceCode = null;
	}
	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称' || undefined==deviceName || ""==deviceName) {
		deviceName = null;
	}
	params['nodeIds'] = aryIds.toString();
	params['deviceCode'] = deviceCode;
	params['deviceName'] = deviceName;
	/*$.post("monitor/mapData.do", params, function(data) {
		$(".map_right .map_right_content").html(data);
		
	});*/
}

/**
 * 分页查询
 * @param page
 */
function changePage(page) {
	var params = {
		pageNo : page
	};
	loadFactorList(params);
}

/**
 * 隐藏/显示 列表页面
 */
function changeListPage() {
	$(".map_button").removeAttr("onclick");
	var width = $(".map_right").width();

	// 切换
	$(".map_right").animate({
		width : 'toggle',
		opacity : '1',
		height : 'toggle'
	}, function() {
		$(".map_button").attr("onclick", "changeListPage()");
	});
}

/**
 * 远程操作
 */
function remoteOpenAndPump(deviceCode, flag){
	// ajax请求远程操作控制器
	$.ajax({
		url: "remote/remoteRequestOperate.do",
		type: "GET",
		data: {deviceCode:deviceCode, flag:flag},
		dataType: "JSON",
		error: function() {
			alert("远程操作结果: 由于网络原因，请求超时，指令下发失败！");
	    },
		success: function(data){
			if(data.message === "ok"){
				alert("远程操作结果: 操作成功！");
			}else{
				alert("远程操作结果: 由于网络原因，请求超时，指令下发失败！");
			}
		}
 	});
}

/**
 * 远程升级
 */
function remoteOpenUpdate(deviceCode,deviceName,flag){
	debugger
	// ajax请求远程操作控制器
	var confirmMsg = {
			title :"【"+ deviceName+"】--远程升级",
			url : "remote/addUpdate.do",
			width : "620",
			height : "150",
			requestMethod: 'ajax',
			tbar : [{
				// 点击取消，则关闭弹出框，并关闭背景加载信息
				text : "确定配置",
				clsName : "homebg popup-confirm",
				handler : function(thisObj) {
					var updateFile = $("select[id='updateFile']").val();
					var updateUrl = $("input[id='updateUrl']").val();
					var updatePort = $("input[id='updatePort']").val();
					var chargeFee = updateUrl+","+updatePort+","+updateFile;
					if(vityData()){
						alert("指令已下发，请等待...！");
						$.ajax({
						url: "remote/remoteRequestOperate.do",
						type: "GET",
						data: {deviceCode:deviceCode, flag:'57',chargeFee:chargeFee/*,prepaidNumber:prepaidNumber*/},
						dataType: "JSON",
						error: function() {
							alert("远程操作结果: 由于网络原因，请求超时，指令下发失败！");
					    },
						success: function(data){
							if(data.message === "ok"){
								alert("远程操作结果: 操作成功！");
							}else{
								alert("远程操作结果: 由于网络原因，请求超时，指令下发失败！");
							}
						}
				 	});
					}
					$.Popup.close(thisObj);
					//hideMark();
				}
			}]
		};
	$.Popup.create(confirmMsg);
	
}

function vityData(){
	var vity = false;
	debugger
	var updateFile = $("select[id='updateFile']").val();
	var updateUrl = $("input[id='updateUrl']").val();
	var updatePort = $("input[id='updatePort']").val();
	var webServerIp = $("input[id='webServerIp']").val();
	var webServerPort = $("input[id='webServerPort']").val();
	if(updateFile==null||updateFile==""){
		alert("请选择升级文件...");
		vity = false;
	}else if(updateUrl==null||updateUrl==""){
		alert("请输入升级服务器地址...");
		vity = false;
	}else if(updatePort==null||updatePort==""){
		alert("请输入升级服务器端口号...");
		vity = false;
	}else if(webServerIp==null||webServerIp==""){
		alert("Web Server的ip和端口为空.....");
		vity = false;
	}/*else if(webServerPort==null||webServerPort==""){
		alert("请输入Web Server的端口号...");
		vity = false;
	}*/else{
		vity = true;
	}
	return vity;
}

/**
 * 控制器远程信息设置
 * @param id, deviceName
 */
function setRemoteInfo(deviceCode, deviceName) {
	var contentMsg = {
		title: deviceName + "--控制器参数远程设置",   
		url: "monitor/setRemoteInfoPage.do",
		width: "1006",
		height: "490",
		requestMethod: 'ajax',
		paraData:{deviceCode:deviceCode}
	};
	$.Popup.create(contentMsg);
}

/**
 * 控制器远程设置参数表单检查
 * @returns {Boolean}
 */
function formCheck(){
	re_flag = true;
	// 小数正则
	var xs_reg = /^\d+(\.\d+)?$/;
	// 正整数正则	 /^([1-9]\d*|[0]{1,1})$/  /^[0-9]*[1-9][0-9]*$/
	var zzs_reg = /^([1-9]\d*|[0]{1,1})$/;
	
	/** 水电设置 start */
	// 电量转水量系数
	var DZSXS = $('#DZSXS').val();
	if(!zzs_reg.test(DZSXS)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// 无电监测时间
	var WDJCSJ = $('#WDJCSJ').val();
	if(!zzs_reg.test(WDJCSJ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// 电量系数
	var DLXS = $('#DLXS').val();
	if(!zzs_reg.test(DLXS)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// 电压系数  取值必须大于等于1000
	var DYXS = $('#DYXS').val();
	if(!zzs_reg.test(DYXS)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	if(DYXS < 1000){
		alert("请输入大于等于1000的正整数！")
		re_flag = false;
		return;
	}
	// 时间转水量系数
	var SJZSL = $('#SJZSL').val();
	if(!xs_reg.test(SJZSL)){
		alert("请输入数字！")
		re_flag = false;
		return;
	}
	// 剩余水量报警阀值
	var SYSLFZ = $('#SYSLFZ').val();
	if(!zzs_reg.test(SYSLFZ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// 无水监测时间
	var WSJCSJ = $('#WSJCSJ').val();
	if(!zzs_reg.test(WSJCSJ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// 电流过滤值
	var DBGLDL = $('#DBGLDL').val();
	if(!zzs_reg.test(DBGLDL)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	/** 水电设置 end */
	
	/** 输入接口设置 start */
	// DI0采集间隔
	var DI0CJJG = $('#DI0CJJG').val();
	if(!zzs_reg.test(DI0CJJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// DI0存储间隔
	var DI0CCJG = $('#DI0CCJG').val();
	if(!zzs_reg.test(DI0CCJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	
	// DI1采集间隔
	var DI1CJJG = $('#DI1CJJG').val();
	if(!zzs_reg.test(DI1CJJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// DI1存储间隔
	var DI1CCJG = $('#DI1CCJG').val();
	if(!zzs_reg.test(DI1CCJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	
	// DI2采集间隔
	var DI2CJJG = $('#DI2CJJG').val();
	if(!zzs_reg.test(DI2CJJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// DI2存储间隔
	var DI2CCJG = $('#DI2CCJG').val();
	if(!zzs_reg.test(DI2CCJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	
	// DI3采集间隔
	var DI3CJJG = $('#DI3CJJG').val();
	if(!zzs_reg.test(DI3CJJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// DI3存储间隔
	var DI3CCJG = $('#DI3CCJG').val();
	if(!zzs_reg.test(DI3CCJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	/** 输入接口设置 end */
	
	/** 继电器设置 start */
	// 电磁锁开锁延迟
	var DCSKSYC = $('#DCSKSYC').val();
	if(!zzs_reg.test(DCSKSYC)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	/** 继电器设置 end */
	
	/** 计数器设置 start */
	// 计数器0系数
	var JSQ0XS = $('#JSQ0XS').val();
	if(!zzs_reg.test(JSQ0XS)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// 计数器0存储时间
	var JSQ0CCSJ = $('#JSQ0CCSJ').val();
	if(!zzs_reg.test(JSQ0CCSJ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	
	// 计数器1系数
	var JSQ1XS = $('#JSQ1XS').val();
	if(!zzs_reg.test(JSQ1XS)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// 计数器1存储时间
	var JSQ1CCSJ = $('#JSQ1CCSJ').val();
	if(!zzs_reg.test(JSQ1CCSJ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	
	// 计数器2系数
	var JSQ2XS = $('#JSQ2XS').val();
	if(!zzs_reg.test(JSQ2XS)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// 计数器2存储时间
	var JSQ2CCSJ = $('#JSQ2CCSJ').val();
	if(!zzs_reg.test(JSQ2CCSJ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	
	// 计数器3系数
	var JSQ3XS = $('#JSQ3XS').val();
	if(!zzs_reg.test(JSQ3XS)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// 计数器3存储时间
	var JSQ3CCSJ = $('#JSQ3CCSJ').val();
	if(!zzs_reg.test(JSQ3CCSJ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	/** 计数器设置 end */
	
	/** 串口设置 start */
	// RS2320采集间隔
	var RS2320CJJG = $('#RS2320CJJG').val();
	if(!zzs_reg.test(RS2320CJJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// RS2320存储时间
	var RS2320CCSJ = $('#RS2320CCSJ').val();
	if(!zzs_reg.test(RS2320CCSJ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	
	// RS2321采集间隔
	var RS2321CJJG = $('#RS2321CJJG').val();
	if(!zzs_reg.test(RS2321CJJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// RS2321存储时间
	var RS2321CCSJ = $('#RS2321CCSJ').val();
	if(!zzs_reg.test(RS2321CCSJ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	
	// RS4850采集间隔
	var RS4850CJJG = $('#RS4850CJJG').val();
	if(!zzs_reg.test(RS4850CJJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// RS4850存储时间
	var RS4850CCSJ = $('#RS4850CCSJ').val();
	if(!zzs_reg.test(RS4850CCSJ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	
	// RS4851采集间隔
	var RS4851CJJG = $('#RS4851CJJG').val();
	if(!zzs_reg.test(RS4851CJJG)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	// RS4851存储时间
	var RS4851CCSJ = $('#RS4851CCSJ').val();
	if(!zzs_reg.test(RS4851CCSJ)){
		alert("请输入正整数！")
		re_flag = false;
		return;
	}
	/** 串口设置 end */
	
	return re_flag;
}

/**
 * 提交控制器远程设置参数
 * @param thisPop
 * @param url
 * @param deviceCode
 * @param flag
 */
function commitRemoteInfo(deviceCode) {
	// 按钮状态1 可以操作  请求成功修改状态位  提示不能频繁操作
	if(btn_flag == 1){
		// 远程设参标志位数组
		var remote_flag = new Array();  
		// 设参表单提交检查
		if(re_flag == true){
			/** 水电设置 start */
			// 水量通道1
			var SLTD1 = $("select[name='SLTD1'] option:selected").val()
			if(SLTD1 != null && SLTD1 != 7){
				remote_flag.push(9);
			}
			// 电量通道
			var DLTD = $("select[name='DLTD'] option:selected").val()
			if(DLTD != null && DLTD != 8){
				remote_flag.push(10);
			}
			// 备份通道 
			var BFTD = $("select[name='BFTD'] option:selected").val()
			if(BFTD != null && BFTD != 0){
				remote_flag.push(11);
			}
			// 电量转水量系数
			var DZSXS = $('#DZSXS').val();
			if(DZSXS != null && DZSXS != 1500){
				remote_flag.push(1);
			}
			// 无电监测时间
			var WDJCSJ = $('#WDJCSJ').val();
			if(WDJCSJ != null && WDJCSJ != 300){
				remote_flag.push(2);
			}
			// 电量系数
			var DLXS = $('#DLXS').val();
			if(DLXS != null && DLXS != 2000){
				remote_flag.push(3);
			}
			// 电压系数
			var DYXS = $('#DYXS').val();
			if(DYXS != null && DYXS != 1000){
				remote_flag.push(4);
			}
			// 时间转水量系数
			var SJZSL = $('#SJZSL').val();
			if(SJZSL != null && SJZSL != 1.1){
				remote_flag.push(5);
			}
			// 剩余水量报警阀值
			var SYSLFZ = $('#SYSLFZ').val();
			if(SYSLFZ != null && SYSLFZ != 300){
				remote_flag.push(6);
			}
			// 无水监测时间
			var WSJCSJ = $('#WSJCSJ').val();
			if(WSJCSJ != null && WSJCSJ != 5){
				remote_flag.push(7);
			}
			// 电表过滤电流
			var DBGLDL = $('#DBGLDL').val();
			if(DBGLDL != null && DBGLDL != 6){
				remote_flag.push(8);
			}
			/** 水电设置 end */
			
			/** 输入接口设置 start */
			// DI0采集间隔
			var DI0CJJG = $('#DI0CJJG').val();
			if(DI0CJJG != null && DI0CJJG != 0){
				remote_flag.push(12);
			}
			// DI0存储间隔
			var DI0CCJG = $('#DI0CCJG').val();
			if(DI0CCJG != null && DI0CJJG != 0){
				remote_flag.push(13);
			}
			// DI0报警方式
			var DI0BJFS = $("select[name='DI0BJFS'] option:selected").val()
			if(DI0BJFS != null && DI0BJFS != 0){
				remote_flag.push(14);
			}
			// DI0用途
			var DI0YT = $("select[name='DI0YT'] option:selected").val()
			if(DI0YT != null && DI0YT != 0){
				remote_flag.push(15);
			}
			
			// DI1采集间隔
			var DI1CJJG = $('#DI1CJJG').val();
			if(DI1CJJG != null && DI1CJJG != 0){
				remote_flag.push(16);
			}
			// DI1存储间隔
			var DI1CCJG = $('#DI1CCJG').val();
			if(DI1CCJG != null && DI1CCJG != 0){
				remote_flag.push(17);
			}
			// DI1报警方式
			var DI1BJFS = $("select[name='DI1BJFS'] option:selected").val()
			if(DI1BJFS != null && DI1BJFS != 0){
				remote_flag.push(18);
			}
			// DI1用途
			var DI1YT = $("select[name='DI1YT'] option:selected").val()
			if(DI1YT != null && DI1YT != 1){
				remote_flag.push(19);
			}
			
			// DI2采集间隔
			var DI2CJJG = $('#DI2CJJG').val();
			if(DI2CJJG != null && DI2CJJG != 0){
				remote_flag.push(20);
			}
			// DI2存储间隔
			var DI2CCJG = $('#DI2CCJG').val();
			if(DI2CCJG != null && DI2CCJG != 0){
				remote_flag.push(21);
			}
			// DI2报警方式
			var DI2BJFS = $("select[name='DI2BJFS'] option:selected").val()
			if(DI2BJFS != null && DI2BJFS != 0){
				remote_flag.push(22);
			}
			// DI2用途
			var DI2YT = $("select[name='DI2YT'] option:selected").val()
			if(DI2YT != null && DI2YT != 2){
				remote_flag.push(23);
			}
			
			// DI3采集间隔
			var DI3CJJG = $('#DI3CJJG').val();
			if(DI3CJJG != null && DI3CJJG != 0){
				remote_flag.push(24);
			}
			// DI3存储间隔
			var DI3CCJG = $('#DI3CCJG').val();
			if(DI3CCJG != null && DI3CCJG != 1){
				remote_flag.push(25);
			}
			// DI3报警方式
			var DI3BJFS = $("select[name='DI3BJFS'] option:selected").val()
			if(DI3BJFS != null && DI3BJFS != 0){
				remote_flag.push(26);
			}
			// DI3用途
			var DI3YT = $("select[name='DI3YT'] option:selected").val()
			if(DI3YT != null && DI3YT != 3){
				remote_flag.push(27);
			}
			/** 输入接口设置 end */
			
			/** 继电器设置 start */
			// 继电器0默认输出
			var JDQ0MRSC = $("select[name='JDQ0MRSC'] option:selected").val()
			if(JDQ0MRSC != null && JDQ0MRSC != 0){
				remote_flag.push(28);
			}
			// 继电器0用途
			var JDQ0YT = $("select[name='JDQ0YT'] option:selected").val()
			if(JDQ0YT != null && JDQ0YT != 1){
				remote_flag.push(29);
			}
			
			// 继电器1默认输出
			var JDQ1MRSC = $("select[name='JDQ1MRSC'] option:selected").val()
			if(JDQ1MRSC != null && JDQ1MRSC != 0){
				remote_flag.push(30);
			}
			// 继电器1用途
			var JDQ1YT = $("select[name='JDQ1YT'] option:selected").val()
			if(JDQ1YT != null && JDQ1YT != 2){
				remote_flag.push(31);
			}
			
			// 继电器2默认输出
			var JDQ2MRSC = $("select[name='JDQ2MRSC'] option:selected").val()
			if(JDQ2MRSC != null && JDQ2MRSC != 0){
				remote_flag.push(32);
			}
			// 继电器2用途
			var JDQ2YT = $("select[name='JDQ2YT'] option:selected").val()
			if(JDQ2YT != null && JDQ2YT != 2){
				remote_flag.push(33);
			}
			
			// 继电器3默认输出
			var JDQ3MRSC = $("select[name='JDQ3MRSC'] option:selected").val()
			if(JDQ3MRSC != null && JDQ3MRSC != 0){
				remote_flag.push(34);
			}
			// 继电器3用途
			var JDQ3YT = $("select[name='JDQ3YT'] option:selected").val()
			if(JDQ3YT != null && JDQ3YT != 2){
				remote_flag.push(35);
			}
			
			// 主控类型
			var ZKLX = $("select[name='ZKLX'] option:selected").val()
			if(ZKLX != null && ZKLX != 0){
				remote_flag.push(36);
			}
			// 主控继电器
			var ZKJDQ = $("select[name='ZKJDQ'] option:selected").val()
			if(ZKJDQ != null && ZKJDQ != 1){
				remote_flag.push(37);
			}
			
			// 电磁锁开锁延迟
			var DCSKSYC = $('#DCSKSYC').val();
			if(DCSKSYC != null && DCSKSYC != 5){
				remote_flag.push(38);
			}
			// 开机主控保护
			var KJZKBH = $("select[name='KJZKBH'] option:selected").val()
			if(KJZKBH != null && KJZKBH != 1){
				remote_flag.push(39);
			}
			/** 继电器设置 end */
			
			/** 计数器设置 start */
			// 计数器0方式
			var JSQ0FS = $("select[name='JSQ0FS'] option:selected").val()
			if(JSQ0FS != null && JSQ0FS != 2){
				remote_flag.push(40);
			}
			// 计数器0系数
			var JSQ0XS = $('#JSQ0XS').val();
			if(JSQ0XS != null && JSQ0XS != 1){
				remote_flag.push(41);
			}
			// 计数器0存储时间
			var JSQ0CCSJ = $('#JSQ0CCSJ').val();
			if(JSQ0CCSJ != null && JSQ0CCSJ != 5){
				remote_flag.push(42);
			}
			// 计数器0用途
			var JSQ0YT = $("select[name='JSQ0YT'] option:selected").val()
			if(JSQ0YT != null && JSQ0YT != 1){
				remote_flag.push(43);
			}
			
			// 计数器1方式
			var JSQ1FS = $("select[name='JSQ1FS'] option:selected").val()
			if(JSQ1FS != null && JSQ1FS != 2){
				remote_flag.push(44);
			}
			// 计数器1系数
			var JSQ1XS = $('#JSQ1XS').val();
			if(JSQ1XS != null && JSQ1XS != 1){
				remote_flag.push(45);
			}
			// 计数器1存储时间
			var JSQ1CCSJ = $('#JSQ1CCSJ').val();
			if(JSQ1CCSJ != null && JSQ1CCSJ != 5){
				remote_flag.push(46);
			}
			// 计数器1用途
			var JSQ1YT = $("select[name='JSQ1YT'] option:selected").val()
			if(JSQ1YT != null && JSQ1YT != 1){
				remote_flag.push(47);
			}
			
			// 计数器2方式
			var JSQ2FS = $("select[name='JSQ2FS'] option:selected").val()
			if(JSQ2FS != null && JSQ2FS != 2){
				remote_flag.push(48);
			}
			// 计数器2系数
			var JSQ2XS = $('#JSQ2XS').val();
			if(JSQ2XS != null && JSQ2XS != 1){
				remote_flag.push(49);
			}
			// 计数器2存储时间
			var JSQ2CCSJ = $('#JSQ2CCSJ').val();
			if(JSQ2CCSJ != null && JSQ2CCSJ != 5){
				remote_flag.push(50);
			}
			// 计数器2用途
			var JSQ2YT = $("select[name='JSQ2YT'] option:selected").val()
			if(JSQ2YT != null && JSQ2YT != 2){
				remote_flag.push(51);
			}
			
			// 计数器3方式
			var JSQ3FS = $("select[name='JSQ3FS'] option:selected").val()
			if(JSQ3FS != null && JSQ3FS != 2){
				remote_flag.push(52);
			}
			// 计数器3系数
			var JSQ3XS = $('#JSQ3XS').val();
			if(JSQ3XS != null && JSQ3XS != 1){
				remote_flag.push(53);
			}
			// 计数器3存储时间
			var JSQ3CCSJ = $('#JSQ3CCSJ').val();
			if(JSQ3CCSJ != null && JSQ3CCSJ != 5){
				remote_flag.push(54);
			}
			// 计数器3用途
			var JSQ3YT = $("select[name='JSQ3YT'] option:selected").val()
			if(JSQ3YT != null && JSQ3YT != 2){
				remote_flag.push(55);
			}
			/** 计数器设置 end */
			
			/** 串口设置 start */
			// RS232-0波特率
			var RS2320BTL = $("select[name='RS2320BTL'] option:selected").val()
			if(RS2320BTL != null && RS2320BTL != 11){
				remote_flag.push(56);
			}
			// RS232-0数据位
			var RS2320SJW = $("select[name='RS2320SJW'] option:selected").val()
			if(RS2320SJW != null && RS2320SJW != 0){
				remote_flag.push(57);
			}
			// RS232-0采集间隔
			var RS2320CJJG = $('#RS2320CJJG').val();
			if(RS2320CJJG != null && RS2320CJJG != 0){
				remote_flag.push(58);
			}
			// RS232-0存储时间
			var RS2320CCSJ = $('#RS2320CCSJ').val();
			if(RS2320CCSJ != null && RS2320CCSJ != 0){
				remote_flag.push(59);
			}
			// RS232-0用途
			var RS2320YT = $("select[name='RS2320YT'] option:selected").val()
			if(RS2320YT != null && RS2320YT != 0){
				remote_flag.push(60);
			}
			
			// RS232-1波特率
			var RS2321BTL = $("select[name='RS2321BTL'] option:selected").val()
			if(RS2321BTL != null && RS2321BTL != 11){
				remote_flag.push(61);
			}
			// RS232-1数据位
			var RS2321SJW = $("select[name='RS2321SJW'] option:selected").val()
			if(RS2321SJW != null && RS2321SJW != 0){
				remote_flag.push(62);
			}
			// RS232-1采集间隔
			var RS2321CJJG = $('#RS2321CJJG').val();
			if(RS2321CJJG != null && RS2321CJJG != 0){
				remote_flag.push(63);
			}
			// RS232-1存储时间
			var RS2321CCSJ = $('#RS2321CCSJ').val();
			if(RS2321CCSJ != null && RS2321CCSJ != 0){
				remote_flag.push(64);
			}
			// RS232-1用途
			var RS2321YT = $("select[name='RS2321YT'] option:selected").val()
			if(RS2321YT != null && RS2321YT != 0){
				remote_flag.push(65);
			}
			
			// RS485-0波特率
			var RS4850BTL = $("select[name='RS4850BTL'] option:selected").val()
			if(RS4850BTL != null && RS4850BTL != 5){
				remote_flag.push(66);
			}
			// RS485-0数据位
			var RS4850SJW = $("select[name='RS4850SJW'] option:selected").val()
			if(RS4850SJW != null && RS4850SJW != 0){
				remote_flag.push(67);
			}
			// RS485-0采集间隔
			var RS4850CJJG = $('#RS4850CJJG').val();
			if(RS4850CJJG != null && RS4850CJJG != 5){
				remote_flag.push(68);
			}
			// RS485-0存储时间
			var RS4850CCSJ = $('#RS4850CCSJ').val();
			if(RS4850CCSJ != null && RS4850CCSJ != 5){
				remote_flag.push(69);
			}
			// RS485-0用途
			var RS4850YT = $("select[name='RS4850YT'] option:selected").val()
			if(RS4850YT != null && RS4850YT != 2){
				remote_flag.push(70);
			}
			
			// RS485-1波特率
			var RS4851BTL = $("select[name='RS4851BTL'] option:selected").val()
			if(RS4851BTL != null && RS4851BTL != 5){
				remote_flag.push(71);
			}
			// RS485-1数据位
			var RS4851SJW = $("select[name='RS4851SJW'] option:selected").val()
			if(RS4851SJW != null && RS4851SJW != 0){
				remote_flag.push(72);
			}
			// RS485-1采集间隔
			var RS4851CJJG = $('#RS4851CJJG').val();
			if(RS4851CJJG != null && RS4851CJJG != 5){
				remote_flag.push(73);
			}
			// RS485-1存储时间
			var RS4851CCSJ = $('#RS4851CCSJ').val();
			if(RS4851CCSJ != null && RS4851CCSJ != 5){
				remote_flag.push(74);
			}
			// RS485-1用途
			var RS4851YT = $("select[name='RS4851YT'] option:selected").val()
			if(RS4851YT != null && RS4851YT != 5){
				remote_flag.push(75);
			}
			/** 串口设置 end */
			
			// 判断标识数组   如果为空  就不下发指令
			if(remote_flag.length == 0){
				alert('未改变任何参数，都为默认值！')
			}else{
				// tips
				alert("远程参数设置中...\n点击确定等待操作结果！");
				// 远程设参异步请求
				$.ajax({
					async: false,
					url: "remote/remoteParamOperate.do",
					type: "GET",
					data: {
						deviceCode:deviceCode,
						flags:remote_flag,
						DZSXS:DZSXS,
						WDJCSJ:WDJCSJ,
						DLXS:DLXS,
						DYXS:DYXS,
						SJZSL:SJZSL,
						SYSLFZ:SYSLFZ,
						WSJCSJ:WSJCSJ,
						DBGLDL:DBGLDL,
						SLTD1:SLTD1,
						DLTD:DLTD,
						BFTD:BFTD,
						DI0CJJG:DI0CJJG,
						DI0CCJG:DI0CCJG,
						DI0BJFS:DI0BJFS,
						DI0YT:DI0YT,
						DI1CJJG:DI1CJJG,
						DI1CCJG:DI1CCJG,
						DI1BJFS:DI1BJFS,
						DI1YT:DI1YT,
						DI2CJJG:DI2CJJG,
						DI2CCJG:DI2CCJG,
						DI2BJFS:DI2BJFS,
						DI2YT:DI2YT,
						DI3CJJG:DI3CJJG,
						DI3CCJG:DI3CCJG,
						DI3BJFS:DI3BJFS,
						DI3YT:DI3YT,
						JDQ0MRSC:JDQ0MRSC,
						JDQ0YT:JDQ0YT,
						JDQ1MRSC:JDQ1MRSC,
						JDQ1YT:JDQ1YT,
						JDQ2MRSC:JDQ2MRSC,
						JDQ2YT:JDQ2YT,
						JDQ3MRSC:JDQ3MRSC,
						JDQ3YT:JDQ3YT,
						ZKLX:ZKLX,
						ZKJDQ:ZKJDQ,
						DCSKSYC:DCSKSYC,
						KJZKBH:KJZKBH,
						JSQ0FS:JSQ0FS,
						JSQ0XS:JSQ0XS,
						JSQ0CCSJ:JSQ0CCSJ,
						JSQ0YT:JSQ0YT,
						JSQ1FS:JSQ1FS,
						JSQ1XS:JSQ1XS,
						JSQ1CCSJ:JSQ1CCSJ,
						JSQ1YT:JSQ1YT,
						JSQ2FS:JSQ2FS,
						JSQ2XS:JSQ2XS,
						JSQ2CCSJ:JSQ2CCSJ,
						JSQ2YT:JSQ2YT,
						JSQ3FS:JSQ3FS,
						JSQ3XS:JSQ3XS,
						JSQ3CCSJ:JSQ3CCSJ,
						JSQ3YT:JSQ3YT,
						RS2320BTL:RS2320BTL,
						RS2320SJW:RS2320SJW,
						RS2320CJJG:RS2320CJJG,
						RS2320CCSJ:RS2320CCSJ,
						RS2320YT:RS2320YT,
						RS2321BTL:RS2321BTL,
						RS2321SJW:RS2321SJW,
						RS2321CJJG:RS2321CJJG,
						RS2321CCSJ:RS2321CCSJ,
						RS2321YT:RS2321YT,
						RS4850BTL:RS4850BTL,
						RS4850SJW:RS4850SJW,
						RS4850CJJG:RS4850CJJG,
						RS4850CCSJ:RS4850CCSJ,
						RS4850YT:RS4850YT,
						RS4851BTL:RS4851BTL,
						RS4851SJW:RS4851SJW,
						RS4851CJJG:RS4851CJJG,
						RS4851CCSJ:RS4851CCSJ,
						RS4851YT:RS4851YT
					},
					dataType: "JSON",
					error: function() {
						alert("远程操作结果: 由于网络原因，请求超时，指令下发失败！");
				    },
					success: function(data){
						var result = data.message;
						if(result === "ok"){
							alert("远程操作结果: 参数设置成功！");
							// 设参成功  修改按钮状态位  提示不能频繁操作  防止控制器死机
							btn_flag = 2;
						}else{
							alert("远程操作结果: 由于网络原因，请求超时，指令下发失败！");
						}
					}
			 	});
			}
		}else{
			alert("请按规格填写所设参数！！！");
		}
	}else{
		alert("操作过于频繁，请一分钟后刷新页面再试！！！");
	}
}

/**
 * 定时修改按钮状态位
 */
function enableRemoteBtn(){
	btn_flag = 1;
}