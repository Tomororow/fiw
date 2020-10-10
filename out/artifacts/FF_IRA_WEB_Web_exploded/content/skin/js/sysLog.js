/**********************************日志功能脚本***************************************************/
/**
 * 条件查询
 */
function searchLogList(){
	var query_beginTime = $("#query_beginTime").val();
	var query_endTime = $("#query_endTime").val();
	var query_keyword = $("#query_keyword").val();
	if(query_keyword=='请输入内容关键字'){
		query_keyword = "";
	}
	var params = {
			byenterpriseid:$("#currentEnterpriseId").val(),
			query_beginTime:query_beginTime,
			query_endTime:query_endTime,
			query_keyword:query_keyword
			
	};
	loadList(params);
}
/**
 * 加载列表
 * @param params
 */
function loadList(params){
	showMark();
	$.get("sysLog/index.do",params,function(data){
		$("#twoContain").html(data);
	});
	hideMark();
}

/**
 * 分页查询
 * @param page
 */
function changePage(page){
	var query_beginTime = $("#query_beginTime").val();
	var query_endTime = $("#query_endTime").val();
	var query_keyword = $("#query_keyword").val();
	if(query_keyword=='请输入内容关键字'){
		query_keyword = "";
	}
	var params = {
			byenterpriseid:$("#currentEnterpriseId").val(),
			query_beginTime:query_beginTime,
			query_endTime:query_endTime,
			query_keyword:query_keyword,
			pageNo:page
	};
	loadList(params);
}

/**
 * 设置开始时间
 */
function setHiddenBeginTime(dp){
	var date =  $dp.cal.date.y +"-"+ $dp.cal.date.M +"-"+ $dp.cal.date.d +" "+ $dp.cal.date.H +":"+ $dp.cal.date.m +":"+ $dp.cal.date.s;
	$("#hidden_beginTime").val(date);
	
}
	
/**
 * 设置结束时间
 */
function setHiddenEndTime(dp){
	var date =  $dp.cal.date.y +"-"+ $dp.cal.date.M +"-"+ $dp.cal.date.d +" "+ $dp.cal.date.H +":"+ $dp.cal.date.m +":"+ $dp.cal.date.s;
	$("#hidden_endTime").val(date);
}