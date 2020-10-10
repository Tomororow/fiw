var iden_flag;
var paramAll;
//获取行政树
function wetGroupTree() {
	
	debugger
	$(".tree").height($(".left").outerHeight()- 10);
	
	// 根据用户分配水管区域或者行政区域  获取不同树形菜单
	$.ajax({
		type:"get",
		async:false,
		cache:false,
		url:"monitor/areaChoose.do",
		success:function(data){
			if(data == "success"){
				iden_flag = data;
				// 只配置水管区域   则按照水管区域查询平台机井信息
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", deviceList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", deviceList);
			}
		}
	});
}

/**
 * 设备告警信息查询
 */
function deviceList(type){
	debugger
	// 参数信息
	params = {};
	var aryIds = getNodeIdsByTreeId("ztree");
	var startTime = $("#query_startTime").val();
	var endTime = $("#query_endTime").val();
	var alarmType = $("#alarmType").val();
	var warnType = $("select[name='alarmType'] option:selected").val();
	var deviceCode = $("#deviceCode").val();
	if(deviceCode == '请输入机井编码') {
		deviceCode = '';
	}
	
	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	if(type==1){//查询携带时间
		startTime = startTime;
		endTime = endTime;
	}else{//非点击查询按钮
		startTime = null;
		endTime = null;
	}
	params['startTime'] = startTime;
	params['endTime'] = endTime;
	params['warnType'] = warnType;
	params['deviceName'] = deviceName;
	params['deviceCode'] = deviceCode;
	params['nodeIds'] = aryIds.toString();
	params['alarmType'] = alarmType;
	params['pageType'] = 0;
	params['handleType'] = 0;
	// 加载告警信息列表
	loadAlarmInfo(params, false);
}

/**
 * 加载设备告警信息
 */
function loadAlarmInfo(params, flag){
	paramAll = params;
	showMark();
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.post("warning_Record/warnNoClose.do",params,function(data){
			$("#deviceContent").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		});
	}else if(iden_flag == "failed"){
		debugger
		// 行政区域
		$.post("warning_Record/warnNoClose.do",params,function(data){
			$("#deviceContent").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		});
	}
}

/**
 * 设备告警查询重置
 */
function deviceReset(){
	$("#deviceName").val("请输入机井名称");
	$("#deviceCode").val("请输入机井编码");
	$("#startTime").val("");
	$("#endTime").val("");
	$("#alarmType").val("");
	deviceList();
}
function handleWarning(id){
	debugger
	var confirmMsg = {
			title : "关闭预警",
			width:400,
			content : "确定关闭该预警?",
			tbar : [ {
				text : "取 消",
				width:'100px',
				clsName : "cancelWarnBtn",
				handler : function(thisPop) {
					// 点击确定后，关闭弹出框
					$.Popup.close(thisPop);
					// 关闭背景加载信息
					hideMark();
				}
			}, {
				// 点击取消，则关闭弹出框，并关闭背景加载信息
				text : "确定关闭",
				clsName : "confirmWarnBtn",
				handler : function(thisObj) {
					debugger
					$.Popup.close(thisObj);
					$.post("warning_Record/handle_warn.do",{"id":id},function(data){
						debugger
						if(data!=""){
							var map = JSON.parse(data);
							if(map.updateCount==1){
								if(map.warnCount==0){
									vityWarnMethods();
									var m = $(".top .top_content li a[onclick*=history]").parent();
									debugger
									// 设置被选中的菜单，并将class=sell，只有class=sell的菜单，才能显示被选中状态
									if (m != undefined && m.length > 0) {
										var s = m.siblings();
										m.find("a").addClass("sell");
										s.find("a").removeClass("sell");
									}
									// 二级菜单跳转到异常机井智能分析菜单上
									var tag = "intelligentDeal";
									showContent("history/index.do?menuId=3&stcd=" + null + "&tag=" + tag, 'contain');
								}else{
									$.post("warning_Record/warnNoClose.do",params,function(data){
										$("#deviceContent").html(data);
									});
								}
							}
						}
					});
				}
			}]
		};
	$.Popup.create(confirmMsg);

}

/**
 * 设备告警查询分页
 */
function changeDevicePage(page){
	// 获取分页查询信息
	var aryIds = getNodeIdsByTreeId("ztree");
	var alarmType = $("#alarmType").val();
	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称'){
		deviceName = "";
	}
	
	// 参数集合
	var params = {
		pageNo:page,
		nodeIds:aryIds.toString(),
		deviceName:deviceName,
		alarmType:alarmType,
		query_startTime:$("#query_startTime").val(),
		query_endTime:$("#query_endTime").val(),
		pageType:0,
	};
	loadAlarmInfo(params);
}

/**
 * 设备告警信息导出
 */
function alarmThreeVoltageRepore(){
	debugger
	var frt = $("#warnList").val();
	if(frt==0){
		confirmMsg("请先查询相关数据...");
	}else{
		window.location.href="warning_Record/warn_excel.do?handleType=0";
	}
	/*// 获取区域树菜单ID
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
	
  	// 条件查询参数
  	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称') {
		deviceName = '';
	}
	var query_startTime = $("#query_startTime").val();
	var query_endTime = $("#query_endTime").val();
	var alarmType = $("#alarmType").val();
	
	// 导出信息 水管区域方式 传入树菜单水管ID
	if(iden_flag == "success"){
		window.location.href="device/exportAlarmThreeVoltage.do?waterAreaIds="+id+"&deviceName="+deviceName+"&query_startTime="+query_startTime+"&query_endTime="+query_endTime+"&alarmType="+alarmType;
	// 导出信息 行政区域方式 传入树菜单行政ID
	}else{
		window.location.href="device/exportAlarmThreeVoltage.do?areaIds="+id+"&deviceName="+deviceName+"&query_startTime="+query_startTime+"&query_endTime="+query_endTime+"&alarmType="+alarmType;
	}*/
	
}
function confirmMsg(str){
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