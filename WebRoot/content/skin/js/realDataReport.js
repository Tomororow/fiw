// 行政区域和水管区域  切换全局变量
var iden_flag;
var vity = new Vue({
	el:'#vity',
	data:{
		activeName:'first',
		userType:false,
		isActive: false,
		active:'active',
		noactive:'noactive'
	},
	methods:{
		handleClick:function(tab, event){
			var _this = this;
			$("#deviceNamg").val("");
			$("#deviceName").val("");
			$("#query_startTime").val("");
			$("#query_endTime").val("");
			$("#deviceport").val("");
			debugger
			if(_this.activeName=="first"){
				getGroupTree();

				//loadDataInfo(params,false);
			}else if(_this.activeName=="second"){
				loadIndustrialDevice("",1,0);//工业机井查询和展示
			}
		},

	},
	created:function(){
		var _this = this;
		debugger
		getGroupTree();
		$.post("monitor/login_user.do",{"userId":""},function(data){
			debugger
			if(data.search("1") != -1){
				_this.userType = true;
				_this.isActive = true;
			}
		});
	}
});

// 获取行政树
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
	}else {
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
 * 实时数据列表
 * @param node
 */
function dataList(node) {
	params = {};
	var areaIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = areaIds.toString();
	var deviceType = $("select[name='deviceType'] option:selected").val();
	if(undefined==deviceType) {
		deviceType = "1";
	}
	params['deviceType'] = deviceType;
	loadDataInfo(params,false);
	//autoDataRefresh();
}

/**
 * 加载实时数据列表
 */
function loadDataInfo(params, flag) {
	showMark();
	debugger
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		if(vity.activeName=="first"){
			params['siteType'] = "0";//机井设备
		}else if(vity.activeName=="second"){
			params['siteType'] = "1";//管道计量的井
		}
		// 水管区域
		$.post("monitor/dataListByWaterArea.do", params, function(data) {
			$("#waterContent").html(data);
			if (flag == undefined || !flag) {
				hideMark();
			} else {
				hideMarkLoading();
			}
		});
	}else if(iden_flag == "failed"){
		if(vity.activeName=="first"){
			params['siteType'] = "0";//机井设备
		}else if(vity.activeName=="second"){
			params['siteType'] = "1";//管道计量的井
		}
		// 行政区域
		$.post("monitor/dataList.do", params, function(data) {
			$("#waterContent").html(data);
			if (flag == undefined || !flag) {
				hideMark();
			} else {
				hideMarkLoading();
			}
		});
	}
}

/**
 * 额济纳旗工业机井的数据展示
 */
function loadIndustrialDevice(params,pageNo,type){
	debugger
	params.pageNo = pageNo;params.type = type;
	$.post("/monitor/loadIndustrial.do",params,function(result){
	    debugger
		if(type==0){
			$("#waterContent").html(result);
		}else{
			$("#waterContentqwer").html(result);

		}
	});
}

/**
 * 修改工业机井名称
 */
function handleBlur(sign,ids){
	debugger
	var deviceName = $("#IndusDeviceName"+sign).val();
	$.post("/monitor/updateByKeyName.do",{deviceName:deviceName,ids:ids},function(data){
		if(data==1){
			vitu.$message({
				message: '设备名称修改成功',
				type: 'success',
				duration:1000
			});
		}
	});
}

/**
 *
 * 工业机井的报表导出
 */
function IndustReport(type,sign){
	debugger
	var deviceName = '';
	var deviceport = '';
	var startTime = '';
	var endTime = '';
	var backtwo = '';
	var type = type;
	backtwo = backtwo=='请选择'?'':backtwo;
	if(sign==0){
		deviceName = $("#deviceNamg").val();
		deviceport = $("#deviceport").val();
		backtwo = $("select[name='backtwo'] option:selected").val();
	}else{
		deviceName = $("#deviceNamg").val();
		startTime = $("#query_beginTime").val();
		endTime = $("#query_endTime").val();
		deviceport = $("#deviceport").val();
	}
	if(typeof (deviceport)=='undefined'){
		deviceport = "";
	}
	window.location.href = "/monitor/IndustReport.do?deviceName="+deviceName+"&deviceport="+deviceport+"&startTime="+startTime+"&endTime="+endTime+"&backtwo="+backtwo+"&type="+type;
}

/**
 * 查询方法
 * @constructor
 */
function IndustSearch(pageNo,type,sign){
	debugger
	var result = {};
	var backtwo = $("select[name='backtwo'] option:selected").val();
	backtwo = backtwo=='请选择'?'':backtwo;
	if(sign==0){
		result = {"deviceName":$("#deviceNamg").val(),"deviceport":$("#deviceport").val(),"backtwo":backtwo};
	}else{
		var result = {
			"deviceName":$("#deviceNamg").val(),
			"startTime":$("#query_beginTime").val(),
			"endTime":$("#query_endTime").val(),
			"deviceport":$("#deviceport").val(),
			"type":type,
		};
	}
	loadIndustrialDevice(result,pageNo,type);
}

function IndustrialList(pageNo,type,deviceName,deviceport){
	debugger
	var contentMsgw = {
		title : "工业机井历史数据查询",
		url : 'monitor/loadIndustrial.do',
		width : "1200",
		height : "560",
		paraData:{pageNo:pageNo,type:type,deviceName:deviceName,deviceport:deviceport},
		requestMethod: 'ajax',
		tbar: [{
			text: "关闭",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				closeDetailInfo(thisPop, "monitor/loadIndustrial.do");
			}
		}]
	};
	$.Popup.create(contentMsgw);
}

/**
 * 1分钟自动刷新实时数据
 */
function autoDataRefresh() {
	var className = $(".button").find("li a:eq(0)").attr("class");
	var className2 = $("#two_nav").find("li:eq(2)").attr("class");
	if (className == "sell" && className2 == "sell") {
		clearInterval(timeDataTicket);
		var aryIds = getNodeIdsByTreeId("ztree");
		var deviceCode = $("#deviceCode").val();
		if (deviceCode == '请输入机井编码') {
			deviceCode = "";
		}
		var deviceName = $("#deviceName").val();
		if (deviceName == '请输入机井名称') {
			deviceName = "";
		}
		var pageNo = $("#pageNos").val();
		params['nodeIds'] = aryIds.toString();
		params['deviceCode'] = deviceCode;
		params['deviceName'] = deviceName;
		params['pageNo'] = pageNo;
		var sNodes = monitorTreeObj.getSelectedNodes()[0];
		if (sNodes != undefined) {
			// 判断是否存在弹窗
			var pop1 = $(".popup-mask");
			if (pop1.length == 0) {
				loadDataInfo(params);
			} else {
				loadDataInfo(params, true);
			}
		}
		/*timeDataTicket = setInterval(function() {
			autoDataRefresh();
		}, 60000);*/
	} else {
		// 其他nav选项，不做操作
		clearInterval(timeDataTicket);
	}
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
	
	var deviceWellUse = $("select[name='deviceWellUse'] option:selected").val();
	
	var deviceType = $("select[name=deviceType] option:selected").val();
	var aryIds = getNodeIdsByTreeId("ztree");
	var params = {
		nodeIds : aryIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName,
		deviceWellUse:deviceWellUse,
		deviceType : deviceType
	};
	loadDataInfo(params);
}

/**
 * 实时数据分页查询
 * @param page
 */
function changeDataPage(page,type) {
	debugger
	if(vity.activeName=="first"){
		var aryIds = getNodeIdsByTreeId("ztree");
		var deviceCode = $("#deviceCode").val();
		if (deviceCode == '请输入机井编码') {
			deviceCode = "";
		}
		var deviceName = $("#deviceName").val();
		if (deviceName == '请输入机井名称') {
			deviceName = "";
		}
		var params = {
			pageNo : page,
			nodeIds : aryIds.toString(),
			deviceType : $("select[name='deviceType'] option:selected").val(),
			deviceCode : deviceCode,
			deviceName : deviceName
		};
		loadDataInfo(params);
	}else if(vity.activeName=="second"){
		IndustSearch(page,type);
	}
}

/**
 * 远程监控列表获取左侧行政区域树形栏所有信息
 */
function remoteList(node) {
	params = {};
	var areaIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = areaIds.toString();
	loadRemoteDataInfo(params);
}

/**
 * 远程监控列表查询
 * @param params
 */
function loadRemoteDataInfo(params, flag) {
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