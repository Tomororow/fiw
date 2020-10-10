var params = {};
$(function(){
	waterInfoLists();
});

/**
 * 查询按钮
 */
function waterInfoLists(){
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
	if(deviceWellUse==""){
		deviceWellUse = "灌溉";
	}
	var beginTime = $("#query_beginTime").val();
	var endTime = $("#query_endTime").val();
	params = {
			nodeIds : areaIds.toString(),
			deviceCode : deviceCode,
			deviceName : deviceName,
			deviceWellUse : deviceWellUse,
			beginTime : beginTime,
			endTime : endTime
		};
	loadDataInfo(params);
}
/**
 * 数据请求公共方法
 */
function loadDataInfo(params){
	showMark();
	debugger
	$.post("personalWaterInfo/waterInfoList.do", params, function(data){
		debugger
		$("#waterInfoContent").html(data);
		hideMark();
	});
}

/**
 * 重置按钮
 */
function waterReset(){
	$("#deviceCode").val();
	$("#deviceName").val();
	$("select[name='deviceWellUse'] option:selected").val();
}
/**
 * 报表导出
 */
function waterReport(){
	var deviceWellUse = $("select[name='deviceWellUse'] option:selected").val();
	if(deviceWellUse==""){
		deviceWellUse = "灌溉";
	}
	debugger
	if($("#waterInfoList").val()=="0"){
		alert("请先查询相关数据...");
	}else{
		window.location.href="personalWaterInfo/personalWaterExcel.do?wellUse="+deviceWellUse;
	}
	
}
/**
 * 分页方法
 * @param page
 */
function changeDistWaterPage(page){
	params["pageNo"] = page;
	loadDataInfo(params);
}



