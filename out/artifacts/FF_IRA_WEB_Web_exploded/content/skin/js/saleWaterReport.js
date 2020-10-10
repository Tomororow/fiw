// 水管区域和行政区域区分  全局变量
var iden_flag;

// 初始进入加载行政树
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
	$(".tree").height($(".left").outerHeight() - 10);
	
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
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", saleWaterInfoList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", saleWaterInfoList);
			}
		}
	});
}

/**
 * 售水信息查询
 */
function saleWaterInfoList(node) {
	var params = {};
	var aryIds = getNodeIdsByTreeId("ztree");
	var id = node.id;
	
	var waterAreaId = $("select[name=waterAreaId] option:selected").val();
	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称') {
		deviceName = '';
	}
	var cardCode = $("#cardCode").val();
	if(cardCode=='请输入水卡卡号') {
		cardCode = '';
	}
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	
	params['nodeIds'] = aryIds.toString();
	params['id'] = id;
	params['waterAreaId'] = waterAreaId;
	params['deviceName'] = deviceName;
	params['cardCode'] = cardCode;
	params['startTime'] = startTime;
	params['endTime'] = endTime;
	// 调用加载设备井信息方法
	loadSaleWaterInfo(params, false);
}

/**
 * 加载售水信息统计信息
 * 点击区域，加载右侧区域列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadSaleWaterInfo(params, flag) {
	// 显示加载圈圈
	showMark();
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.post("saleWaterInfo/saleWaterInfoListByArea.do", params, function(data){
			$("#saleWaterContent").html(data);
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
		$.post("saleWaterInfo/saleWaterInfoList.do", params, function(data){
			$("#saleWaterContent").html(data);
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

/**
 * 重置查询信息
 */
function saleWaterReset() {
	var waterAreaId = "";
	$("#waterAreaId").val(waterAreaId);
	$("#cardCode").val("请输入水卡卡号");
	$("#deviceName").val("请输入机井名称");
	saleWaterInfoList();
}

/**
 * 分页按钮执行方法
 * @param page
 */
function changeSaleWaterPage(page) {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
	var aryIds = getNodeIdsByTreeId("ztree");
	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称') {
		deviceName = '';
	}
	var cardCode = $("#cardCode").val();
	if(cardCode=='请输入水卡卡号') {
		cardCode = '';
	}
	var waterAreaId = $("select[name=waterAreaId] option:selected").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	
	var params = {
		id : id,
		pageNo : page,
		nodeIds : aryIds.toString(),
		deviceName : deviceName,
		cardCode : cardCode,
		waterAreaId : waterAreaId,
		startTime : startTime,
		endTime : endTime
	};
	loadSaleWaterInfo(params);
}

/**
 * 查询按钮查询信息
 */
function saleWaterLists() {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	saleWaterInfoList(sNodes[0]);
}

/**
 * 售水信息导出
 */
function saleWaterReport(){
	// 获取区域树菜单ID
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	
  	// 查询条件
  	var deviceName = $("#deviceName").val();
  	if(null == deviceName || "" == deviceName || '请输入机井名称' == deviceName) {
		deviceName = "";
	}
  	var cardCode = $("#cardCode").val();
  	if(null == cardCode || "" == cardCode || '请输入水卡卡号' == cardCode) {
  		cardCode = "";
	}
  	
	// 开始 结束时间
  	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
  	
  	// 水管区域方式 传入树菜单水管ID
	if(iden_flag == "success"){
		window.location.href="saleWaterInfo/exportSaleWater.do?waterAreaId="+id+"&deviceName="+deviceName+"&cardCode="+cardCode+"&startTime="+startTime+"&endTime="+endTime;
	// 行政区域方式 传入树菜单行政ID 和按照水管区域查询ID
	}else{
		// 查询条件 行政区域方式  水管区域下拉框
	  	var waterAreaId = $("select[name=waterAreaId] option:selected").val();
	  	if(waterAreaId == "--水管区域--"){
	  		waterAreaId = null;
	  	}
		window.location.href="saleWaterInfo/exportSaleWater.do?areaId="+id+"&selWaterAreaId="+waterAreaId+"&deviceName="+deviceName+"&cardCode="+cardCode+"&startTime="+startTime+"&endTime="+endTime;
	}
}