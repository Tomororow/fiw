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
	$(".tree").height($(".left").outerHeight()- 10);
	
	// 根据用户分配水管区域或者行政区域  获取不同树形菜单
	$.ajax({
		type:"get",
		async:false,
		cache:false,
		url:"monitor/areaChoose.do",
		success:function(data){
			debugger
			if(data == "success"){
				iden_flag = data;
				// 只配置水管区域   则按照水管区域查询平台机井信息
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", waterCardInfoList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", waterCardInfoList);
			}
		}
	});
}

/**
 * 水卡信息查询
 */
function waterCardInfoList(node) {
	var params = {};
	var aryIds = getNodeIdsByTreeId("ztree");
	var id = node.id;
	
	var ownerName = $("#ownerName").val();
	if("请输入持卡人姓名"==ownerName || ""==ownerName) {
		ownerName = "";
	}
	var deviceName = $("#deviceName").val();
	if("请输入机井名称"==deviceName || ""==deviceName) {
		deviceName = "";
	}
	
	params['deviceName'] = deviceName;
	params['ownerName'] = ownerName;
	params['nodeIds'] = aryIds.toString();
	params['id'] = id;
	// 调用加载水卡信息方法
	loadWaterCardInfo(params, false);
}

/**
 * 加载水卡统计信息
 * 点击区域，加载右侧区域列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadWaterCardInfo(params, flag) {
	debugger
	// 显示加载圈圈
	showMark();
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.post("waterCardInfo/waterCardInfoList.do", params, function(data){
			$("#waterCardContent").html(data);
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
		$.post("waterCardInfo/waterCardInfoList.do", params, function(data){
			$("#waterCardContent").html(data);
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
function waterCardInfoSet() {
	$("#ownerName").val("请输入持卡人姓名");
	$("#deviceName").val("请输入机井名称");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
	waterCardInfoList(sNodes[0]);
}

/**
 * 分页按钮执行方法
 * @param page
 */
function changeWaterCardPage(page) {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
	var aryIds = getNodeIdsByTreeId("ztree");
	var params = {
		id : id,
		pageNo : page,
		nodeIds : aryIds.toString(),
		ownerName : $("#ownerName").val() == "请输入持卡人姓名" ? "" : $("#ownerName").val(),
		deviceName : $("#deviceName").val() == "请输入机井名称" ? "" : $("#deviceName").val(),
	};
	loadWaterCardInfo(params);
}

/**
 * 水卡信息条件查询
 */
function waterCardInfo() {
	var params = {};
	var ownerName = $("#ownerName").val();
	if("请输入持卡人姓名"==ownerName || ""==ownerName) {
		ownerName = "";
	}
	params['ownerName'] = ownerName;
	
	var deviceName = $("#deviceName").val();
	if("请输入机井名称"==deviceName || ""==deviceName) {
		deviceName = "";
	}
	params['deviceName'] = deviceName;
	
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	params['id'] = id;
	// 调用加载水卡信息方法
	loadWaterCardInfo(params, false);
}

/**
 * 水卡信息导出
 */
function waterCardReport(){
	// 查询条件
	var ownerName = $("#ownerName").val();
	var deviceName = $("#deviceName").val();
	// 获取选中树菜单ID
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	// 水管区域方式 传入树菜单水管ID
	if(iden_flag == "success"){
		window.location.href="waterCardInfo/waterCardInfoExport.do?waterAreaId="+id+"&ownerName=" + ownerName + "&deviceName=" + deviceName;
	// 行政区域方式 传入树菜单行政ID
	}else{
		window.location.href="waterCardInfo/waterCardInfoExport.do?areaIds="+id+"&ownerName=" + ownerName + "&deviceName=" + deviceName;
	}
}

/**
 * 水卡信息删除(水卡已在控制器上卡 需要删除信息后补同一张卡)
 * @param id
 */
function delWaterCardInfo(id){
	var showConfirm = true;
    var confirmMsg = {
    	title: "提示",
		content: "确定删除该水卡信息?",
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				fnAjaxRequest("waterCardInfo/delCardInfo.do", {id:id}, "json", "POST", function(data){
					fnDSuccess(data,thisPop,showConfirm);
     			    if(data.success){
     			    	// 刷新水卡信息列表
     			    	waterCardInfo();
      				}else{
      					$.Popup.close(thisPop);
      				}
				});
			}
		}, {
			text: "取消",
			clsName: "homebg popup-cancel",
			handler: function (thisObj) {
				$.Popup.close(thisObj);
			}
		}]
	};
	$.Popup.create(confirmMsg);
}