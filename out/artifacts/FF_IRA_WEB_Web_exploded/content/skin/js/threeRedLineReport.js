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
	ztreeFun($("#ztree"),"sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", threeRedLineList);
}

/**
 * 机井设备信息查询
 */
function threeRedLineList(node) {
	var params = {};
	var aryIds = getNodeIdsByTreeId("ztree");
	var id = node.id;
	params['nodeIds'] = aryIds.toString();
	params['id'] = id;
	// 调用加载设备井信息方法
	loadThreeRedLineInfo(params, false);
}

/**
 * 加载设备机井统计信息
 * 点击区域，加载右侧区域列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadThreeRedLineInfo(params, flag) {
	// 显示加载圈圈
	showMark();
	$.post("threeRedLineAnalysis/threeRedLineList.do", params, function(data){
		$("#threeRedLineContent").html(data);
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
function threeRedLineReset() {
	$("#deviceName").val("请输入机井名称");
	$("#deviceCode").val("请输入机井编码");
	loadThreeRedLineInfo();
}

/**
 * 查询按钮查询信息
 */
function threeRedLineLists() {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	loadThreeRedLineInfo(sNodes[0]);
}

