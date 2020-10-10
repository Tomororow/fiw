/*
 * 管理卡js函数
 */
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
			if(data == "success"){
				iden_flag = data;
				// 只配置水管区域   则按照水管区域查询平台机井信息
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", manageCardInfoList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", manageCardInfoList);
			}
		}
	});
}

/**
 * 管理卡信息列表
 * @param node
 */
function manageCardInfoList(node) {
	var params = {};
	// 发卡人
	var ownerName = $("#ownerName").val();
	if("请输入发卡人姓名"==ownerName || ""==ownerName) {
		ownerName = "";
	}
	params['ownerName'] = ownerName;
	// 选中节点ID
	var id = node.id;
	params['id'] = id;
	// 调用加载水卡信息方法
	loadManageCardInfo(params, false);
}

/**
 * 管理卡条件查询
 */
function manageCardInfoSearch() {
	var params = {};
	// 树菜单ID
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	params['id'] = id;
  	// 发卡人
	var ownerName = $("#ownerName").val();
	if("请输入发卡人姓名"==ownerName || ""==ownerName) {
		ownerName = "";
	}
	params['ownerName'] = ownerName;
	// 调用加载水卡信息方法
	loadManageCardInfo(params, false);
}

/**
 * 点击区域，加载右侧管理卡信息列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadManageCardInfo(params, flag) {
	// 显示加载图片
	showMark();
	debugger
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.post("waterCardInfo/manageCardList.do", params, function(data){
			$("#manageCardContent").html(data);
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
		$.post("waterCardInfo/manageCardList.do", params, function(data){
			$("#manageCardContent").html(data);
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
 * 管理卡分页按钮执行方法
 * @param page
 */
function changeManageCardPage(page) {
	var ownerName = $("#ownerName").val();
	if("请输入发卡人姓名"==ownerName || ""==ownerName) {
		ownerName = "";
	}
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
	params['ownerName'] = ownerName;
	// 条件查询搜索
	loadManageCardInfo(params);
}

/**
 * 管理卡信息删除
 * @param id
 */
function delManageCardInfo(id){
	var showConfirm = true;
    var confirmMsg = {
    	title: "提示",
		content: "确定删除该管理卡信息?",
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				fnAjaxRequest("waterCardInfo/delCardInfo.do", {id:id}, "json", "POST", function(data){
					fnDSuccess(data,thisPop,showConfirm);
     			    if(data.success){
     			    	// 刷新管理卡信息列表
     			    	manageCardInfoSearch();
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