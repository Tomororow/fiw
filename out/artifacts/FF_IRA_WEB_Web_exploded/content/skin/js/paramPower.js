
$(function(){
	$("#checkAll").live("click", function() {
		var check = $(this).attr("checked");
		var ckList = $("#paramPowerDiv tbody input[type='checkbox']");
		if (check) {
			ckList.attr("checked", true);
		} else {
			ckList.removeAttr("checked");
		}
	});
});

/**
 * 分页查询
 * @param page
 */
function changePowerPage(page) {
	showMark();
	$.ajax({
		url : "parameterManage/powerInfo.do",
		data : {
			id : id,
			pageNo : page
		},
		success : function(data) {
			$("#paramPowerDiv").html(data);
			hideMark();
		},
		error : function(xhr, error, thrown) {
			hideMark();
		}
	});
}

/**
 * 点击区域，加载右侧区域列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadPowerList(params, flag) {
	showMark();
	$.get("parameterManage/powerInfo.do", params, function(data) {
		$("#paramPowerDiv").html(data);
		if (flag == undefined || !flag) {
			hideMark();
		} else {
			hideMarkLoading();
		}
	});
}

/**
 * 跳转到新增页面
 */
function addPowerInfo() {
	var contentMsg = {
		id : "addParamPower",
		title : "新增功率信息",
		url : "parameterManage/addPowerPage.do",
		width : "800",
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditPower(thisPop, "parameterManage/addPower.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}

/**
 * 保存新增后的功率值
 */
function addAndEditPower(thisPop, url) {
	var reg = /^-?\d+\.?\d{0,2}$/;
	var powerPercentUp = $("#powerPercentUp").val();
	var powerPercentDown = $("#powerPercentDown").val();
	var param = $("#powerForm").serialize();
	if(null==powerPercentUp || ""==powerPercentUp) {
		$("#powerPercentUpMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(powerPercentUp)) {
		$("#powerPercentUpMsg").html("<font style='color:red;line-height:32px;'>两位小数的正数</font>");
	} else {
		$("#powerPercentUpMsg").empty();
	}
	if(null==powerPercentDown || ""==powerPercentDown) {
		$("#powerPercentDownMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(powerPercentDown)) {
		$("#powerPercentDownMsg").html("<font style='color:red;line-height:32px;'>两位小数的正数</font>");
	} else {
		$("#powerPercentDownMsg").empty();
	}
	if(reg.test(powerPercentUp) && reg.test(powerPercentDown)) {
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadPowerList();
			} else {
				$.Popup.close(thisPop);
			}
		});
	}
}

/**
 * 修改三相电压信息
 * @param id
 */
function editPower(id) {
	var contentMsg = {
		id : "editParamPower",
		title : "修改三相电压信息",
		url : "parameterManage/editPowerPage.do",
		width : "800",
		paraData : {
			id : id
		},
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditPower(thisPop, "parameterManage/editPower.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}

/**
 * 删除多个功率值
 */
function delPower() {
	var ids = [];
	var selectRow = $("#paramPowerDiv tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		ids.push(selectRow[i].value);
	}
	if (ids.length == 0) {
		$.Popup.create({
			title : "提示",
			content : "请选择要删除的功率"
		});
		return false;
	}
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择功率信息吗？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("parameterManage/delPower.do", {
					ids : ids.toString()
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadPowerList();
					} else {
						$.Popup.close(thisPop);
					}
				});
			}
		}, {
			text : "取消",
			clsName : "homebg popup-cancel",
			handler : function(thisObj) {
				$.Popup.close(thisObj);
			}
		} ]
	};
	$.Popup.create(confirmMsg);
}

/**
 * 删除单个功率值
 * @param id
 */
function delPowerById(id) {
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择功率信息吗？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("parameterManage/delPower.do", {
					ids : id
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadPowerList();
					} else {
						$.Popup.close(thisPop);
					}
				});
			}
		}, {
			text : "取消",
			clsName : "homebg popup-cancel",
			handler : function(thisObj) {
				$.Popup.close(thisObj);
			}
		} ]
	};
	$.Popup.create(confirmMsg);
}