/**----------------------- 水资源费js脚本 -----------------------*/
var iden_flag;

/**
 * 当中间按钮点击时
 * 1、左侧树形菜单如果显示为none，则让左侧树形菜单栏显示
 * 2、左侧树形菜单栏如果显示为block，则让左侧树形菜单栏不显示，并将图标改为往右的箭头
 */
$(function() {
	$(".leftnav").click(function() {
		if ($(".left").css("display") == "none") {
			$(".leftnav").css("left", "237px");
			$(".leftnav img").attr("src","${ctx}/content/skin/css/images/botton-closeLeft.gif");
			$(".right_user").css("left", "275px");
		} else {
			$(".leftnav").css("left", "0px");
			$(".leftnav img").attr("src","${ctx}/content/skin/css/images/botton-closeRight.gif");
			$(".right_user").css("left", "20px");
		}
		$(".left").toggle();
		$(".left-bottom").toggle();
	});
	
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
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false",waterSourceChargeList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", waterSourceChargeList);
			}
		}
	});
});

/**
 * 水资源费列表
 */
function waterSourceChargeList() {
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	
  	var deviceCode = $("#deviceCode").val();
	if(deviceCode=='请输入机井编码') {
		deviceCode = null;
	}
  	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称') {
		deviceName = null;
	}
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var deviceWellUse = $("select[name='deviceWellUse'] option:selected").val();
	
	var param = {
		id : sNodes[0].id,
		deviceCode : deviceCode,
		deviceName : deviceName,
		startTime : startTime,
		endTime : endTime,
		deviceWellUse:deviceWellUse
	};
	loadWaterSourceChargeList(param, false);
}

/**
 * 点击区域，加载右侧机井设备列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadWaterSourceChargeList(params, flag){
	showMark();
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.get("waterSourceCharge/waterSourceChargeListByWaterArea.do",params,function(data){
			$("#waterSourceChargeInfoContent").html(data);
			if(flag == undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		});
	}else if(iden_flag == "failed"){
		// 行政区域
		$.get("waterSourceCharge/waterSourceChargeList.do",params,function(data){
			$("#waterSourceChargeInfoContent").html(data);
			if(flag == undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		});
	}
}

/**
 * 水资源费分页查询
 * @param page
 */
function changeWaterSourcePage(page) {
	showMark();
	// 获取页面参数  用于分页查询获取数量和详细数据信息
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	
  	var deviceCode = $("#deviceCode").val();
	if(deviceCode=='请输入机井编码') {
		deviceCode = null;
	}
  	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称') {
		deviceName = null;
	}
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
  	
  	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.ajax({
			url : "waterSourceCharge/waterSourceChargeListByWaterArea.do",
			data : {deviceCode:deviceCode, deviceName:deviceName, id:id, pageNo:page, startTime:startTime, endTime:endTime},
			success : function(data){
				$("#waterSourceChargeInfoContent").html(data);
				hideMark();
		    },
		    error: function (xhr, error, thrown) {
		    	hideMark();
	   	    }
		});
	}else if(iden_flag == "failed"){
		// 行政区域
		$.ajax({
			url : "waterSourceCharge/waterSourceChargeList.do",
			data : {deviceCode:deviceCode, deviceName:deviceName, id:id, pageNo:page, startTime:startTime, endTime:endTime},
			success : function(data){
				$("#waterSourceChargeInfoContent").html(data);
				hideMark();
		    },
		    error: function (xhr, error, thrown) {
		    	hideMark();
	   	    }
		});
	}
}

/**
 * 重置查询条件
 */
function waterSourceChargeReset() {
	$("#deviceName").val("请输入机井名称");
	$("#deviceCode").val("请输入机井编码");
}

/**
 * 其他费用统计(计量水费-水资源费-末级渠系水费)统计报表
 */
function waterAmountReport(){
	// 条件查询参数
	var deviceName = $("#deviceName").val();
	if(null == deviceName || "" == deviceName || '请输入机井名称' == deviceName) {
		deviceName = "";
	}
	var deviceCode = $("#deviceCode").val();
	if(null == deviceCode || "" == deviceCode || '请输入机井编码' == deviceCode) {
		deviceCode = "";
	}
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	
	var areaIds;
	var waterAreaId;
	// 水管区域方式 获取水管区域树菜单id
	if(iden_flag == "success"){
		waterAreaId = getNodeIdsByTreeId("ztree");
		window.location.href="waterSourceCharge/exportWaterAmountInfo.do?waterAreaId="+waterAreaId+"&deviceName="+deviceName+"&deviceCode="+deviceCode+"&startTime="+startTime+"&endTime="+endTime;
	}else{
		areaIds = getNodeIdsByTreeId("ztree");
		window.location.href="waterSourceCharge/exportWaterAmountInfo.do?areaIds="+areaIds+"&deviceName="+deviceName+"&deviceCode="+deviceCode+"&startTime="+startTime+"&endTime="+endTime;
	}
}