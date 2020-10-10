var iden_flag;

// 初始进入设备机井基础信息加载行政树
$(function() {
	getGroupTree();
});

//获取行政树
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
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", intelligentAnalysisList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", intelligentAnalysisList);
			}
		}
	});
}

/**
 * 机井设备信息查询
 */
function intelligentAnalysisList(node) {
	var params = {};
	var aryIds = getNodeIdsByTreeId("ztree");
	
	var deviceCode = $("#deviceCode").val();
	if(deviceCode == '请输入机井编码') {
		deviceCode = '';
	}
	
	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	params['deviceCode'] = deviceCode;
	params['deviceName'] = deviceName;
	params['nodeIds'] = aryIds.toString();
	var id = node.id;
	params['id'] = id;
	// 调用加载设备井信息方法
	loadIntelligentAnalysisInfo(params, false);
}

/**
 * 加载智能预警信息
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadIntelligentAnalysisInfo(params, flag) {
	// 显示加载圈圈
	showMark();
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.post("intelligentAnalysis/intelligentAnalysisListByArea.do", params, function(data){
			$("#intelligentAnalysisContent").html(data);
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
		$.post("intelligentAnalysis/intelligentAnalysisList.do", params, function(data){
			$("#intelligentAnalysisContent").html(data);
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
 * 分页按钮执行方法
 * @param page
 */
function changeIntelligentAnalysisPage(page) {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
	
	var deviceCode = $("#deviceCode").val();
	if(deviceCode == '请输入机井编码') {
		deviceCode = '';
	}
	
	var deviceName = $("#deviceName").val();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	
	var aryIds = getNodeIdsByTreeId("ztree");
	var params = {
		id : id,
		pageNo : page,
		nodeIds : aryIds.toString(),
		deviceCode : deviceCode,
		deviceName : deviceName,
	};
	loadIntelligentAnalysisInfo(params);
}

/**
 * 重置查询信息
 */
function intelligentAnalysisReset() {
	$("#deviceName").val('请输入机井名称');
	$("#deviceCode").val('请输入机井编码');
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	intelligentAnalysisList(sNodes[0]);
}

/**
 * 查询按钮查询信息
 */
function intelligentAnalysisLists() {
	// 左侧树形菜单获取选择的节点id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	intelligentAnalysisList(sNodes[0]);
}

/**
 * 预警信息处理页面
 * @param id
 * @param name
 */
function dealInfo(id, name) {
	var contentMsg = {
		id:"dealInfo",
        title: name+"智能预警信息处理页",   
        url:"intelligentAnalysis/dealExceptionPage.do",
        width:"600",
        paraData:{id:id},
        requestMethod: 'ajax',
        tbar: [{
        	text: "确定",
            clsName: "homebg popup-confirm",
            handler: function (thisPop) {
            	dealExceptionInfo(thisPop,"intelligentAnalysis/dealExceptionInfo.do");
            }
        }]
  	};
  	$.Popup.create(contentMsg);
}

/**
 * 预警信息增加
 * @param thisPop
 * @param url
 */
function dealExceptionInfo(thisPop, url) {
	// 通过表单验证
	var checkForm = validateForm();
	if(checkForm){
		$.ajax({
			url:url,
			type:"post",
            dataType:"json",
			data:{
				intelligentAnalysisId:$("#intelligentAnalysisId").val(),
				dealType:$("input[name='dealType']:checked").val(), 
				serviceManId:$("select[name='serviceManId'] option:selected").val(), 
				phone:$("#phone").val(), 
				fixTime:$("#fixTime").val(),
				handleTime:$("#handleTime").val(), 
				dealExceptionRemark:$("#dealExceptionRemark").val()
			},
			success:function(data){
				if(data.success){
					$.Popup.close(thisPop);
					$.Popup.create({title: "温馨提示", content: "预警信息处理成功！"});
					getGroupTree();
				}
			}
		});
	}
}

/**
 * 报修信息处理反馈
 * @param id
 */
function repairFeedBack(id, name) {
	var contentMsg = {
		title : name+"预警报修信息处理",
		url : "intelligentAnalysis/repairFeedBackPage.do",
		width : "550",
		paraData : {id : id},
		requestMethod : 'ajax',
		tbar : [{
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				saveFeedBackInfo(thisPop,"intelligentAnalysis/repairFeedBackSave.do");
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 反馈信息保存
 * @param thisPop
 * @param url
 */
function saveFeedBackInfo(thisPop, url) {
	// 通过表单验证
	$.ajax({
		url:url,
		type:"post",
        dataType:"json",
		data:{
			intelligentAnalysisId:$("#intelligentAnalysisId").val(),
			dealResult:$("input[name='dealResult']:checked").val(), 
			dealRemark:$("#dealRemark").val(), 
			isOverTime:$("input[name='isOverTime']:checked").val(), 
			overTimeRemark:$("#overTimeRemark").val()
		},
		success:function(data){
			if(data.success){
				$.Popup.close(thisPop);
				$.Popup.create({title: "温馨提示", content: "预警信息反馈成功！"});
				getGroupTree();
			}
		}
	});
}

/**
 * 表单非空、非法验证
 * @returns
 */
function validateForm(){
	return $("#intelligentDealForm").validate({
		rules: {
			phone: {
	    		required: true
	    	},
	    	dealTime: {
	    		required: true,
	    		number: true
	    	}
		},
	    messages: {
	    	phone: {
	    		required: "不能为空"
	    	},
	    	dealTime: {
	    		required: "不能为空",
	    		number: "必须为数字"
	    	}
		},
		showErrors:showErrors,
		onkeyup:function(element, event) {
			if(event.which === 9 && this.elementValue(element) === "") {
				return;
			}else if(element.name in this.submitted || element === this.lastElement) {
				this.element(element);
				$(element).next('span').remove();	//移除span
			}
		}
    }).form();
}

/**
 * 根据技术员获取电话
 */
function getServiceManPhone() {
	/* 获取维修技术员ID */
	var serviceManId = $("select[name='serviceManId'] option:selected").val();
	if(null!=serviceManId && ""!=serviceManId && undefined!=serviceManId) {
		$.post("intelligentAnalysis/getServiceManPhone.do", {id:serviceManId}, function(data) {
			$("#phone").val(data.phone);
		}, "json");
		$.ajaxSetUp({
			async : true
		});
	}
}

/**
 * 点击解决方式显示不同信息
 * @param _this
 */
function dealTypeInfos() {
	if($("input[name='dealType']:checked").val()==1) {
		$(".dealPersonInfo").show();
		$(".dealResultInfo").show();
	} else {
		$(".dealPersonInfo").hide();
		$(".dealResultInfo").hide();
	}
}

/**
 * 点击已解决，则显示解决方式描述，反之不显示
 */
function getDealRemarkInfo() {
	if($("input:radio[name='dealResult']:checked").val()==0) {
		$("#dealRemarkInfo").show();
	} else {
		$("#dealRemarkInfo").hide();
	}
}