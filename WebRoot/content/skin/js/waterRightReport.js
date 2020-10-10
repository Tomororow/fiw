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
	ztreeFun($("#ztree"),"sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false",waterRightInfoList);
}

/**
 * 水卡信息查询
 */
function waterRightInfoList(node) {
	var params = {};
	var aryIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	var id = node.id;
	params['id'] = id;
	// 调用加载水权交易信息方法
	loadWaterRightInfo(params, false);
}

/**
 * 加载水权交易统计信息
 * 点击区域，加载右侧区域列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadWaterRightInfo(params, flag) {
	// 显示加载圈圈
	//showMark();
	$.post("waterRightInfo/waterRightInfoList.do", params, function(data){
		$("#waterRightContent").html(data);
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
function waterRightReset() {
	waterRightInfoList();
}

/**
 * 分页按钮执行方法
 * @param page
 */
function changeWaterRightPage(page) {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
	var aryIds = getNodeIdsByTreeId("ztree");
	var params = {
		id : id,
		pageNo : page,
		nodeIds : aryIds.toString()
	};
	loadWaterRightInfo(params);
}