var historyTreeObj;

//获取行政树
function getGroupTree() {
	$(".tree").height($(".left").outerHeight()- 10);
	if($(".button").find("li a:eq(1)").attr("class")=="sell" && $("#two_nav").find("li:eq(0)").attr("class")=="sell"){
		//历史查询
		historyTreeObj = ztreeFun($("#ztree"),"stAddvcdD/addvcdDInfo.do?isSearchDevice=true&showOnLineDevice=false",historyList,treeInitComplete);
    }else{
    	//报警查询
    	historyTreeObj = ztreeFun($("#ztree"),"stAddvcdD/addvcdDInfo.do?isSearchDevice=true&showOnLineDevice=false",alarmList,treeInitComplete);
	}
	
}

function treeInitComplete(event, treeId, treeNode){
	var pStcd = $("#pStcd").val();
	
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var node = treeObj.getNodeByParam("id", pStcd);
	treeObj.selectNode(node);
	
	if(pStcd != undefined && pStcd != ""){
		if($("#two_nav").find("li:eq(0)").attr("class")=="sell"){
			historyList();
		}else{
			alarmList();
		}
	}else{
	   //获取节点
	   var nodes = treeObj.getNodes();
	   if (nodes.length>0) 
	   {
		   treeObj.selectNode(nodes[0]);
		   //默认点击第一个节点
		   treeObj.setting.callback.onClick(null, treeObj.setting.treeId, nodes[0]);
	   }
	}
}

/**
 * 历史查询
 * @param node
 */
function historyList(){
	 params = {};
	 var query_beginTime = $("#query_beginTime").val();
	 var query_endTime = $("#query_endTime").val();
	 var stcd = $("#dstcd").val();
	 if(stcd=='请输入测站编码'){
		 stcd = "";
	 }
	 params['query_beginTime'] = query_beginTime;
	 params['query_endTime'] = query_endTime;
	 params['stcd_query'] = stcd;
	 var aryIds = getNodeIdsByTreeId("ztree");
	 params['nodeIds'] = aryIds.toString();
	 loadDataInfo(params);
}

/**
 * 历史查询分页
 */
function changeHistoryPage(page){
	var aryIds = getNodeIdsByTreeId("ztree");
	 var stcd = $("#dstcd").val();
	 if(stcd=='请输入测站编码'){
		 stcd = "";
	 }
	var params = {
			pageNo:page,
			nodeIds:aryIds.toString(),
			stcd_query:stcd,
			query_beginTime:$("#query_beginTime").val(),
			query_endTime:$("#query_endTime").val()
	};
	loadDataInfo(params);
}

/**
 * 历史查询重置
 */
function historyReset(){
	$("#dstcd").val("");
	$("#query_beginTime").val("");
	$("#query_endTime").val("");
	historyList();
}

/**
 * 加载历史数据
 */
function loadDataInfo(params, flag){
	showMark();
	$.post("history/historyList.do",params,function(data){
		$("#historyContent").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}
/**
 * 历史查询导出
 */
function historyExport(){
	 var aryIds = getNodeIdsByTreeId("ztree");
	 var stcd = $("#dstcd").val();
	 var query_beginTime = $("#query_beginTime").val();
	 var query_endTime = $("#query_endTime").val();
	 if(stcd=='请输入测站编码'){
		 stcd = "";
	 }
	window.location.href="history/historyExport.do?nodeIds="+aryIds.toString()+"&stcd_query="+stcd+
	"&query_beginTime="+query_beginTime+"&query_endTime="+query_endTime;
}

/**
 * 报警查询
 */
function alarmList(){
	 params = {};
	 var query_beginTime = $("#query_beginTime").val();
	 var query_endTime = $("#query_endTime").val();
	 var type = $("#type").val();
	
	 var stcd = $("#dstcd").val();
	 if(stcd=='请输入测站编码'){
		 stcd = "";
	 }
	 params['query_beginTime'] = query_beginTime;
	 params['query_endTime'] = query_endTime;
	 params['stcd_query'] = stcd;
	 params['type'] = type;
	 var aryIds = getNodeIdsByTreeId("ztree");
	 params['nodeIds'] = aryIds.toString();
	 loadAlarmInfo(params);
}

/**
 * 加载报警数据
 */
function loadAlarmInfo(params, flag){
	showMark();
	$.post("history/alarmList.do",params,function(data){
		$("#alarmContent").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}

/**
 * 报警查询重置
 */
function alarmReset(){
	$("#dstcd").val("");
	$("#query_beginTime").val("");
	$("#query_endTime").val("");
	$("#type").val("");
	alarmList();
}

/**
 * 报警查询分页
 */
function changeAlarmPage(page){
	var aryIds = getNodeIdsByTreeId("ztree");
	 var stcd = $("#dstcd").val();
	 var type = $("#type").val();
	 if(stcd=='请输入测站编码'){
		 stcd = "";
	 }
	var params = {
			pageNo:page,
			nodeIds:aryIds.toString(),
			stcd_query:stcd,
			type:type,
			query_beginTime:$("#query_beginTime").val(),
			query_endTime:$("#query_endTime").val()
	};
	loadAlarmInfo(params);
}

/**
 * 报警查询导出
 */
function alarmExport(){
	 var aryIds = getNodeIdsByTreeId("ztree");
	 var stcd = $("#dstcd").val();
	 var type = $("#type").val();
	 var query_beginTime = $("#query_beginTime").val();
	 var query_endTime = $("#query_endTime").val();
	 if(stcd=='请输入测站编码'){
		 stcd = "";
	 }
	window.location.href="history/alarmExport.do?nodeIds="+aryIds.toString()+"&stcd_query="+stcd+
	"&query_beginTime="+query_beginTime+"&query_endTime="+query_endTime+"&type="+type;
}