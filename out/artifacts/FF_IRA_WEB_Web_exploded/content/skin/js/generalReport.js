
var greportTreeObj;
var listDataCache;
$(function() {
	getGroupTree();
});

//获取行政树
function getGroupTree() {
	$(".tree").height($(".left").outerHeight()- 10);
	greportTreeObj = ztreeFun($("#ztree"),"stAddvcdD/addvcdDInfo.do?isSearchDevice=true",treeOnClick);
}

function treeOnClick() {
	searchList();
}

/**
 * 查询
 */
function searchList(params) {

	if (params == undefined) {
		params = {};
	}
	var searchType = $("#searchType").val();
	if(searchType==1){
		//时段
		var query_beginTime = $("#query_beginTime").val();
		var query_endTime = $("#query_endTime").val();

		if (query_beginTime != "" || query_endTime != "") {
			if (query_beginTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入开始时间!"
				});
				return;
			}
			if (query_endTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入结束时间!"
				});
				return;
			}
		}
		params['query_beginTime'] = query_beginTime;
		params['query_endTime'] = query_endTime;
	}else{
		//日月年
		var query_Time = $(".query_Time:visible").val();
		if (query_Time == "") {
			$.Popup.create({
				title : "提示",
				content : "请输入查询时间"
			});
			return;
		}
		params['query_Time'] = query_Time;
	}
	var aryIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	params['searchType'] = searchType;

	showMark();
	$.post("generalReport/list.do", params, function(data) {
		hideMark();
		$("#genReportContent").html(data);
		// loadFactorList();
	});
}

/**
* 分页查询
* @param page
*/
function changePage(page){
	var params = {
			pageNo:page
	};
	searchList(params);
}

function showArea(obj){
	var m = $("#" + $(obj).find("option:selected").attr("area")).show();
	var s = m.siblings("div").hide();
}

var popOpt;
function showDetail(stcd){
	var contentMsg = {
			title: "查看详情",   
			url:"generalReport/detailList.do",
			width:"1200",
			height:"500",
			paraData:{
				stcd: stcd,
				query_beginTime:$("#hidden_query_beginTime").val(),
				query_endTime:$("#hidden_query_endTime").val()
				},
			requestMethod: 'ajax'
	};
	popOpt = $.Popup.create(contentMsg);
}

function changeDtPage(page){
	var params = {
		pageNo:page,
		stcd:$("#hidden_stcd").val(),
		query_beginTime:$("#hidden_query_beginTime").val(),
		query_endTime:$("#hidden_query_endTime").val()
	};
	
	showMark();
	$.get("generalReport/detailList.do", params, function(data) {
		hideMarkLoading();
		popOpt.popObj.find("div.popup-content").html(data);
	});
}

/**
 * 导出报表
 */
function exportReportForm(obj){
	$(obj).attr("disabled","disabled");
	$(obj).removeAttr("onclick");
	var params = {
			listDataCache:JSON.stringify(listDataCache),
			beginTime : $("#hidden_query_beginTime").val(),
			endTime : $("#hidden_query_endTime").val()
	};
	
	$.post("generalReport/exportExcelData.do", params, function(data){
		if(data.success){
			window.location.href="generalReport/download.do?fileName=" + data.fileName;
		}else{
			alert("数据导出失败!");
		}
		$(obj).attr("onclick","exportReportForm(this)");
		$(obj).removeAttr("disabled");
	},"json")
}
