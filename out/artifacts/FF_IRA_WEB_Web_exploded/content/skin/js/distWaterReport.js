// 初始进入设备机井基础信息加载行政树
var distWaterInfoList = [];
$(function() {
	getGroupTree();
});

//获取行政树
function getGroupTree() {
	$(".tree").height($(".left").outerHeight()- 10);
	ztreeFun($("#ztree"),"sysWaterArea/getAllArea.do",distWaterList);
}

/**
 * 配水信息查询
 */
function distWaterList(node) {
	var params = {};
	var wellUse = $("select[name=wellUse] option:selected").val();
	if(wellUse==""){
		wellUse = "灌溉";
	}
	var distYearStart = $("#distYearStart").val();
	var distYearEnd = $("#distYearEnd").val();
	params['wellUse'] = wellUse;
	params['distYearStart'] = distYearStart;
	params['distYearEnd'] = distYearEnd;
	var id = node.id;
	params['id'] = id;
	// 调用加载设备井信息方法
	loadDistWaterInfo(params, false);
}

/**
 * 加载设备机井统计信息
 * @param params
 * @param flag
 */
function loadDistWaterInfo(params, flag) {
	// 显示加载圈圈
	showMark();
	debugger
	$.post("distWaterInfo/distWaterList.do", params, function(data){
		$("#distWaterContent").html(data);
		// 如果flag状态没有改变，则表示无异常，将加载圈圈给取消掉
		if(flag==undefined || !flag) {
			hideMark();
		} else {
			// 否则还会继续加载
			hideMarkLoading();
		}
	});
}

/**
 * 重置查询信息
 */
function distWaterReset() {
	var wellUse = "";
	var distYearStart = new Date();
	var distYearEnd = new Date();
	distYearStart.setDate(distYearStart.getDate()-365);
	distYearEnd.setDate(distYearEnd.getDate());
	$("#wellUse").val(wellUse);
	$("#distYearStart").val(distYearStart.format("yyyy"));
	$("#distYearEnd").val(distYearEnd.format("yyyy"));
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
	distWaterList(sNodes[0]);
}

/**
 * 分页按钮执行方法
 * @param page
 */
function changeDistWaterPage(page) {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
	// 获取权限树
	var aryIds = getNodeIdsByTreeId("ztree");
	var params = {
		id : id,
		pageNo : page,
		nodeIds : aryIds.toString(),
		wellUse : $("select[name=wellUse] option:selected").val(),
		distYearStart : $("#distYearStart").val(),
		distYearEnd : $("#distYearEnd").val()
	};
	loadDistWaterInfo(params);
}

/**
 * 按照条件查询配水信息
 */
function distWaterLists() {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	distWaterList(sNodes[0]);
}
/*
 *配水信息的导出信息waterdistributionReport
 * */
function waterdistributionReport(){
	debugger
	if(distWaterInfoList=='[]'){
		alert("配水信息为空，不可导出...");
		return;
	}
	var wellUse = $("#wellUse").val();
	var distType = $("#distType").val();
	window.location.href="distWaterInfo/exportdistribution.do?wellUse="+wellUse;
}

