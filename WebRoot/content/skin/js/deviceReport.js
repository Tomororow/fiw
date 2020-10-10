// 行政区域和水管区域划分
var iden_flag;

// 初始进入设备机井基础信息加载行政树
$(function() {
	getGroupTree();
});

// 获取行政树
function getGroupTree() {
    $(".leftnav").click(function () {
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
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", deviceInfoList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false",deviceInfoList);
			}
		}
	});
}

/**
 * 机井设备信息查询
 */
function deviceInfoList(node) {
	var params = {};
	var waterAreaId = $("select[name=waterAreaId] option:selected").val();
	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称') {
		deviceName = '';
	}
	var deviceCode = $("#deviceCode").val();
	if(deviceCode=='请输入机井编码') {
		deviceCode = '';
	}
	
	var deviceWellUse = $("select[name='deviceWellUse'] option:selected").val();
	params['waterAreaId'] = waterAreaId;
	params['deviceName'] = deviceName;
	params['deviceCode'] = deviceCode;
	params['deviceWellUse'] = deviceWellUse;
	var aryIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	var id = node.id;
	params['id'] = id;
	// 调用加载设备井信息方法
	loadDeviceInfo(params, false);
}

/**
 * 加载设备机井统计信息
 * 点击区域，加载右侧区域列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadDeviceInfo(params, flag) {
	// 显示加载圈圈
	showMark();
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.post("deviceInfo/deviceInfoListByWaterArea.do", params, function(data){
			$("#deviceContent").html(data);
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
		$.post("deviceInfo/deviceInfoList.do", params, function(data){
			$("#deviceContent").html(data);
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
/*
 *机井信息的导出信息deviceinfoReport
 * */
function deviceinfoReport(){
	window.location.href="deviceInfo/exportDeviceInfo.do";
}
/**
 * 重置查询信息
 */
function deviceReset() {
	var waterAreaId = "";
	$("#waterAreaId").val(waterAreaId);
	$("#deviceName").val("请输入机井名称");
	$("#deviceCode").val("请输入机井编码");
	deviceInfoList();
}

/**
 * 分页按钮执行方法
 * @param page
 */
function changeDevicePage(page) {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
	// 获取机井编码
	var deviceCode = $("#deviceCode").val();
	if(deviceCode=='请输入机井编码') {
		deviceCode = "";
	}
	// 获取机井名称
	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称') {
		deviceName = "";
	}
	var aryIds = getNodeIdsByTreeId("ztree");
	var params = {
		id : id,
		pageNo : page,
		nodeIds : aryIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName,
		waterAreaId : $("select[name=waterAreaId] option:selected").val()
	};
	loadDeviceInfo(params);
}

/**
 * 查询按钮查询信息
 */
function deviceInfoLists() {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	deviceInfoList(sNodes[0]);
}

function detailBaseDeviceInfo(id) {
	var contentMsg = {
		title: "查看机井设备信息",   
		url:"deviceInfo/detailPage.do",
		width:"950",
		paraData:{id:id},
		requestMethod: 'ajax',
		tbar: [{
			text: "关闭",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				detailDevice(thisPop, "deviceInfo/detailDeviceInfo.do");
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 关闭弹出对话框
 * @param thisPop
 * @param url
 */
function detailDevice(thisPop,url){
	$.Popup.close(thisPop);
}