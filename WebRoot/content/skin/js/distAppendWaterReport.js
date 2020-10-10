// 初始进入设备机井基础信息加载行政树
$(function() {
	getGroupTree();
});

//获取行政树
function getGroupTree() {
	$(".tree").height($(".left").outerHeight()- 10);
	ztreeFun($("#ztree"),"sysWaterArea/getAllArea.do",distAppendWaterList);
}

/**
 * 配水信息查询
 */
function distAppendWaterList(node) {
	var params = {};
	var wellUse = $("select[name=wellUse] option:selected").val();
	var distYearStart = $("#distYearStart").val();
	var distYearEnd = $("#distYearEnd").val();
	params['wellUse'] = wellUse;
	params['distYearStart'] = distYearStart;
	params['distYearEnd'] = distYearEnd;
	var id = node.id;
	params['id'] = id;
	// 调用加载设备井信息方法
	loadDistAppendWaterInfo(params, false);
}

/**
 * 加载设备机井统计信息
 * @param params
 * @param flag
 */
function loadDistAppendWaterInfo(params, flag) {
	// 显示加载圈圈
	showMark();
	$.post("distAppendWaterInfo/distAppendWaterList.do", params, function(data){
		$("#distAppendWaterContent").html(data);
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
function distAppendWaterReset() {
	var wellUse = "";
	var distYearStart = new Date();
	var distYearEnd = new Date();
	distYearStart.setDate(distYearStart.getDate()-365);
	distYearEnd.setDate(distYearEnd.getDate());
	$("#wellUse").val(wellUse);
	$("#distYearStart").val(distYearStart.format("yyyy"));
	$("#distYearEnd").val(distYearEnd.format("yyyy"));
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
	distAppendWaterList(sNodes[0]);
}

/**
 * 分页按钮执行方法
 * @param page
 */
function changeAppendDistWaterPage(page) {
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
	loadDistAppendWaterInfo(params);
}

/**
 * 按照条件查询是否为增加水量的配水信息
 */
function distAppendWaterLists() {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	distAppendWaterList(sNodes[0]);
}