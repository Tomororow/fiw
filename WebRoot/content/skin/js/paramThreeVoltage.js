
$(function(){
	$("#checkAll").live("click", function() {
		var check = $(this).attr("checked");
		var ckList = $("#paramThreeVoltageDiv tbody input[type='checkbox']");
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
function changeThreeVoltagePage(page) {
	showMark();
	$.ajax({
		url : "parameterManage/threeVoltage.do",
		data : {
			id : id,
			pageNo : page
		},
		success : function(data) {
			$("#paramThreeVoltageDiv").html(data);
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
function loadThreeVoltageList(params, flag) {
	showMark();
	$.get("parameterManage/threeVoltage.do", params, function(data) {
		$("#paramThreeVoltageDiv").html(data);
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
function addThreeVoltageInfo() {
	var contentMsg = {
		id : "addParamThreeVoltage",
		title : "新增三相电压信息",
		url : "parameterManage/addThreeVoltagePage.do",
		width : "800",
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditThreeVoltage(thisPop, "parameterManage/addThreeVoltage.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}

/**
 * 保存新增后的电压值
 */
function addAndEditThreeVoltage(thisPop, url) {
	var reg = /^-?\d+\.?\d{0,2}$/;
	var aPhaseVoltageUp = $("#aPhaseVoltageUp").val();
	var aPhaseVoltageDown = $("#aPhaseVoltageDown").val();
	var bPhaseVoltageUp = $("#bPhaseVoltageUp").val();
	var bPhaseVoltageDown = $("#bPhaseVoltageDown").val();
	var cPhaseVoltageUp = $("#cPhaseVoltageUp").val();
	var cPhaseVoltageDown = $("#cPhaseVoltageDown").val();
	var param = $("#threeVoltageForm").serialize();
	if(null==aPhaseVoltageUp || ""==aPhaseVoltageUp) {
		$("#aPhaseVoltageUpMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(aPhaseVoltageUp)) {
		$("#aPhaseVoltageUpMsg").html("<font style='color:red;line-height:32px;'>保留两位小数的正数</font>");
	} else {
		$("#aPhaseVoltageUpMsg").empty();
	}
	if(null==aPhaseVoltageDown || ""==aPhaseVoltageDown) {
		$("#aPhaseVoltageDownMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(aPhaseVoltageDown)) {
		$("#aPhaseVoltageDownMsg").html("<font style='color:red;line-height:32px;'>保留两位小数的正数</font>");
	} else {
		$("#aPhaseVoltageDownMsg").empty();
	}
	if(null==bPhaseVoltageUp || ""==bPhaseVoltageUp) {
		$("#bPhaseVoltageUpMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(bPhaseVoltageUp)) {
		$("#bPhaseVoltageUpMsg").html("<font style='color:red;line-height:32px;'>保留两位小数的正数</font>");
	} else {
		$("#bPhaseVoltageUpMsg").empty();
	}
	if(null==bPhaseVoltageDown || ""==bPhaseVoltageDown) {
		$("#bPhaseVoltageDownMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(bPhaseVoltageDown)) {
		$("#bPhaseVoltageDownMsg").html("<font style='color:red;line-height:32px;'>保留两位小数的正数</font>");
	} else {
		$("#bPhaseVoltageDownMsg").empty();
	}
	if(null==cPhaseVoltageUp || ""==cPhaseVoltageUp) {
		$("#cPhaseVoltageUpMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(cPhaseVoltageUp)) {
		$("#cPhaseVoltageUpMsg").html("<font style='color:red;line-height:32px;'>保留两位小数的正数</font>");
	} else {
		$("#cPhaseVoltageUpMsg").empty();
	}
	if(null==cPhaseVoltageDown || ""==cPhaseVoltageDown) {
		$("#cPhaseVoltageDownMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(cPhaseVoltageDown)) {
		$("#cPhaseVoltageDownMsg").html("<font style='color:red;line-height:32px;'>保留两位小数的正数</font>");
	} else {
		$("#cPhaseVoltageDownMsg").empty();
	}
	if(reg.test(aPhaseVoltageUp) && reg.test(aPhaseVoltageDown) && reg.test(bPhaseVoltageUp) && reg.test(bPhaseVoltageDown)
			&& reg.test(cPhaseVoltageUp) && reg.test(cPhaseVoltageDown)) {
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadThreeVoltageList();
			} else {
				$.Popup.close(thisPop);
			}
		});
	}
}

/**
 * 总电压失去焦点后，将其他三相电压分别赋值
 */
function getAllVoltage() {
	$("#aPhaseVoltageUpMsg").empty();
	$("#aPhaseVoltageDownMsg").empty();
	$("#bPhaseVoltageUpMsg").empty();
	$("#bPhaseVoltageDownMsg").empty();
	$("#cPhaseVoltageUpMsg").empty();
	$("#cPhaseVoltageDownMsg").empty();
	$("#voltageMsg").empty();
	var voltage = $("#voltage").val();
	var reg = /^-?\d+\.?\d{0,2}$/;
	if(null==voltage || ""==voltage) {
		$("#voltageMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(voltage)) {
		$("#voltageMsg").html("<font style='color:red;line-height:32px;'>保留两位小数的正数</font>");
	} else {
		$("#aPhaseVoltageUp").val((parseFloat(voltage)*0.8).toFixed(2));
		$("#bPhaseVoltageUp").val((parseFloat(voltage)*0.8).toFixed(2));
		$("#cPhaseVoltageUp").val((parseFloat(voltage)*0.8).toFixed(2));
		$("#aPhaseVoltageDown").val((parseFloat(voltage)*1.2).toFixed(2));
		$("#bPhaseVoltageDown").val((parseFloat(voltage)*1.2).toFixed(2));
		$("#cPhaseVoltageDown").val((parseFloat(voltage)*1.2).toFixed(2));
	}
}

/**
 * 修改三相电压信息
 * @param id
 */
function editThreeVoltage(id) {
	var contentMsg = {
		id : "editParamThreeVoltage",
		title : "修改三相电压信息",
		url : "parameterManage/editThreeVoltagePage.do",
		width : "800",
		paraData : {
			id : id
		},
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditThreeVoltage(thisPop, "parameterManage/editThreeVoltage.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}

/**
 * 删除三相电压
 */
function delThreeVoltage() {
	var ids = [];
	var selectRow = $("#paramThreeVoltageDiv tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		ids.push(selectRow[i].value);
	}
	if (ids.length == 0) {
		$.Popup.create({
			title : "提示",
			content : "请选择要删除的三相电压"
		});
		return false;
	}
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择三相电压信息吗？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("parameterManage/delThreeVoltage.do", {
					ids : ids.toString()
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadThreeVoltageList();
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

function delThreeVoltageById(id) {
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择三相电压信息吗？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("parameterManage/delThreeVoltage.do", {
					ids : id
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadThreeVoltageList();
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