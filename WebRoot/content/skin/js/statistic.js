/**********************************用水信息脚本***************************************************/
var iden_flag;
var dateType;

//获取行政树
function getGroupTree(type) {
	$(".tree").height($(".left").outerHeight()-10);
	// 日月年类型
	dateType = type;
	
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
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", useWaterList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", useWaterList);
			}
		}
	});
}

/**
 * 用水统计查询
 */
function useWaterList(){
	debugger
	params = {};
	var aryIds = getNodeIdsByTreeId("ztree");
	var wellUse = $("select[name='deviceWellUse'] option:selected").val();
	var deviceType = $("select[name='deviceType'] option:selected").val();//设备类型val
	var query_startTime = $("#query_startTime").val();
	//var query_endTime = $("#query_endTime").val();
	var dCode = $("#dCode").val();
	if(dCode=='请输入机井编码'){
		dCode = "";
	}
	var dName = $("#dName").val();
	if(dName=='请输入机井名称') {
		dName = "";
	}
	var waterAreaId = $("select[name='waterAreaId'] option:selected").val();
	if('--水管区域--' == waterAreaId){
		waterAreaId = "";
	}
	params['nodeIds'] = aryIds.toString();
	params['query_startTime'] = query_startTime;
	//params['query_endTime'] = query_endTime;
	params['dCode_query'] = dCode;
	params['dName_query'] = dName;
	params['waterAreaId'] = waterAreaId;
	params['wellUse'] = wellUse;
	params['deviceType'] = deviceType;//设备类型
	// 加载信息
	loadUseWaterInfo(params,false);
}

/**
 * 加载用水统计数据列表
 * 日 月 年
 */
function loadUseWaterInfo(params, flag){
	showMark();
	// 根据类型不一样 请求url不同
	var url;
	
	// 日统计
	if(dateType == "day"){
		url = "useWater/useWaterList.do";
	// 月统计
	}else if(dateType == "month"){
		url = "useWater/useWaterListOfMonth.do";
	// 年统计
	}else if(dateType == "year"){
		url = "useWater/useWaterListOfYear.do";
	}
	
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		params['areaType'] = "waterArea";
		$.post(url, params, function(data){
			$("#useWaterContent").html(data);
			// 如果flag状态没有改变，则表示无异常
			if(flag==undefined || !flag) {
				hideMark();
			} else {
				// 否则还会继续加载
				//hideMarkLoading();
			}
		});
	}else if(iden_flag == "failed"){
		// 行政区域
		params['areaType'] = "area";
		$.post(url, params, function(data){
			$("#useWaterContent").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				//hideMarkLoading();
			}
		});
	}
}

/**
 * 用水统计信息报表导出
 * 水管区域显示方式 树菜单为水管区域 没有条件查询的下拉区域信息
 * 行政区域显示方式 树菜单为行政区域 有水管区域的条件查询区域信息
 */
function useWaterReport(type){
	// 初始化区域变量
	var areaIds;
	var waterAreaId;
	var query_endTime;
	// 条件查询参数
	var dName = $("#dName").val();
	if(null==dName || ""==dName || '请输入机井名称'==dName) {
		dName = "";
	}
	var dCode = $("#dCode").val();
	if(null==dCode || ""==dCode || '请输入机井编码'==dCode) {
		dCode = "";
	}
	var wellUse = $("select[name='deviceWellUse'] option:selected").val();
	// 记录开始时间 月 年报表只有此查询参数
	var query_startTime = $("#query_startTime").val();
	/*// 日统计 有结束时间
	if(type == "day"){
		query_endTime = $("#query_endTime").val();
	}else{
		query_endTime = "";
	}*/
	debugger
	// 水管区域方式 获取水管区域树菜单id
	if(iden_flag == "success"){
		debugger
		waterAreaId = getNodeIdsByTreeId("ztree");
		var url = "useWater/exportUseWaterInfo.do?type="+type+"&waterAreaId="+waterAreaId+"&dName="+dName+"&dCode="+dCode+"&query_startTime="+query_startTime+"&query_endTime="+query_endTime+"&wellUse="+wellUse;
		window.open(url);
	}else{
		areaIds = getNodeIdsByTreeId("ztree");
		waterAreaId = $("select[name='waterAreaId'] option:selected").val();
		if('--水管区域--' == waterAreaId){
			waterAreaId = "";
		}
		var url = "useWater/exportUseWaterInfo.do?type="+type+"&areaIds="+areaIds+"&waterAreaId="+waterAreaId+"&dName="+dName+"&dCode="+dCode+"&query_startTime="+query_startTime+"&query_endTime="+query_endTime+"&wellUse="+wellUse;
		window.open(url);
	}
}

/**
 * 分页
 * @param page
 */
function changeUseWaterPage(page){
	var aryIds = getNodeIdsByTreeId("ztree");
	var code = $("#dCode").val();
	if(code=='请输入机井编码'){
		code = "";
	}
	var name = $("#dName").val();
	if(name=='请输入机井名称') {
		name = "";
	}
	var wellUse = $("select[name='deviceWellUse'] option:selected").val();
	var params = {
		pageNo:page,
		waterAreaId : $("select[name='waterAreaId'] option:selected").val(),
		nodeIds:aryIds.toString(),
		dCode_query:code,
		dName_query:name,
		wellUse:wellUse,
		query_startTime:$("#query_startTime").val(),
		query_endTime:$("#query_endTime").val()
	};
	loadUseWaterInfo(params);
}

/**
 * 用水统计查询重置
 */
function useWaterReset(){
	var sTime = new Date();
	sTime.setDate(sTime.getDate()-7);
	var eTime = new Date();
	$("#waterAreaId").val("");
	$("#dCode").val("请输入机井编码");
	$("#dName").val("请输入机井名称");
	$("#query_startTime").val(sTime.format("yyyy-MM-dd 00:00"));
	$("#query_endTime").val(eTime.format("yyyy-MM-dd hh:mm"));
	useWaterList();
}

Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth()+1,                  
	    "d+" : this.getDate(),                   
	    "h+" : this.getHours(),                     
	    "m+" : this.getMinutes(),                  
	    "s+" : this.getSeconds(),                   
	    "q+" : Math.floor((this.getMonth()+3)/3),    
	    "S"  : this.getMilliseconds()              
	};   
	if(/(y+)/.test(fmt))   
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	return fmt;   
};

Array.prototype.groupBy = function(field){
	var result = {};
	for(var i =0 ;i<this.length;i++){
		var row = this[i];
		var fieldValue=  row[field];
		if(result.length==0){
			result[fieldValue] = [];
    		result[fieldValue].push(row);
		}
		var find = false;
		for(key in result){
			if(fieldValue==key){
				find = true;
				result[key].push(row);
				break;
			}
		}
		if(find){
			continue;
		}
		result[fieldValue] = [];
		result[fieldValue].push(row);
	}
	return result;
};

Array.prototype.sorted = function(field,direct){
	var result = this;
	result = result.sort(
        function(a, b)
        {
        	var aValue = a[field];
        	var bValue = b[field]; 
        	if(direct==null ||direct=="asc"){
        		if(aValue < bValue) {
                	return -1;
                }
                if(aValue > bValue){
                	return 1;
                }
        	}
        	if(direct=="desc"){
        		if(aValue < bValue) {
                	return 1;
                }
                if(aValue > bValue){
                	return -1;
                }
        	}
            return 0;
        }
	);
	return result;
};