// 初始进入设备机井基础信息加载行政树
$(function() {
	industryinfoList();
});

//获取行政树
function getGroupTree() {
	/*$(".leftnav").click(function () {
		if($(".left").css("display") == "none") {
			$(".leftnav").css("left", "237px");
			$(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeLeft.gif");
			$(".right_user").css("left", "275px");
		} else {
			$(".leftnav").css("left", "0px");
			$(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeRight.gif");
			$(".right_user").css("left", "20px");
		}
		$(".left").toggle();
		$(".left-bottom").toggle();
	});
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
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", industryinfoList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", industryinfoList);
			}
		}
	});*/
}
/*
 * 机井设备故障信息查询
 */
function industryinfoList() {
	debugger
	var params = {};
	var aryIds = getNodeIdsByTreeId("ztree");

	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	var startTime = $("#query_startTime").val();

	var endTime = $("#query_endTime").val();

	params['startTime'] = startTime;
	params['endTime'] = endTime;
	params['deviceName'] = deviceName;
	params['pageNo'] = 1;
	params['nodeIds'] = aryIds.toString();

	loadIntelligentDealInfo(params, false);
}

/**
 * 报表导出
 */
function voltageReport(){
	var params = {};
	var aryIds = getNodeIdsByTreeId("ztree");

	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	var startTime = $("#query_startTime").val();

	var endTime = $("#query_endTime").val();

	params['startTime'] = startTime;
	params['endTime'] = endTime;
	params['deviceName'] = deviceName;
	params['nodeIds'] = aryIds.toString();
	window.location.href="industry/voltageReport.do?deviceName="+deviceName+"&startTime="+startTime+"&endTime="+endTime;
}


/**
 * 设备告警信息导出
 */
/*function equipmentexport(){
	debugger
	var frt = $("#warnList").val();
	if(frt==0){
		confirmMsg("请先查询相关数据...");
	}else{
		var params = {};
		var aryIds = getNodeIdsByTreeId("ztree");
		var nodeIds = aryIds.toString();
		var deviceCode = $("#deviceCode").val();
		if(deviceCode == '请输入机井编码') {
			deviceCode = '';
		}
		var deviceName = $("#deviceName").val();
		if (deviceName == '请输入机井名称') {
			deviceName = "";
		}
		var startTime = $("#query_startTime").val();
		var endTime = $("#query_endTime").val();
		var alarmType = $("#alarmType").val();
		if(exporttype==1){//查询携带时间
			startTime = startTime;
			endTime = endTime;
			group = "1";
		}else{//非点击查询按钮
			startTime = "";
			endTime = "";
			group = "";
		}
		window.location.href="intelligentDeal/equipmentexport.do?deviceCode="+deviceCode
			+"&deviceName="+deviceName+"&alarmType="+alarmType+"&startTime="+startTime+"&endTime="+endTime+"&group="+group;

	}
}*/
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
/**
 * 加载设备机井统计信息
 * 点击区域，加载右侧区域列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadIntelligentDealInfo(params, flag) {
	// 显示加载圈圈
	showMark();
	debugger
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.post("industry/industryinfoList.do",params, function(data){
			$("#equiPmentContent").html(data);
			// 如果flag状态没有改变，则表示无异常，将加载圈圈给取消掉
			if(flag==undefined || !flag) {
				hideMark();
			} else {
				// 否则还会继续加载
				hideMarkLoading();
			}
		});
	}else if(iden_flag == "failed"){
		// 行政区域
		$.post("industry/industryinfoList.do",params, function(data){
			$("#IndustryContent").html(data);
			// 如果flag状态没有改变，则表示无异常，将加载圈圈给取消掉
			if(flag==undefined || !flag) {
				hideMark();
			} else {
				// 否则还会继续加载
				hideMarkLoading();
			}
		});
	}
}

/**
 * 分页按钮执行方法
 * @param page
 */
function changeIntelligentDealPage(page) {
	debugger
	var params = {};
	var aryIds = getNodeIdsByTreeId("ztree");

	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	var startTime = $("#query_startTime").val();

	var endTime = $("#query_endTime").val();

	params['startTime'] = startTime;
	params['endTime'] = endTime;
	params['deviceName'] = deviceName;
	params['pageNo'] = page;
	params['nodeIds'] = aryIds.toString();
	loadIntelligentDealInfo(params);
}