$(function(){
	$("#checkAll").live("click", function() {
		var check = $(this).attr("checked");
		var ckList = $("#paramWaterSourcePriceDiv tbody input[type='checkbox']");
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
function changeWaterSourcePricePage(page) {
	showMark();
	$.ajax({
		url : "parameterManage/waterSourcePrice.do",
		data : {
			pageNo : page
		},
		success : function(data) {
			$("#paramWaterSourcePriceDiv").html(data);
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
function loadWaterSourcePriceList(params, flag) {
	showMark();
	$.get("parameterManage/waterSourcePrice.do", params, function(data) {
		$("#paramWaterSourcePriceDiv").html(data);
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
function addWaterSourcePriceInfo() {
	var contentMsg = {
		id : "addParamWaterSourcePrice",
		title : "新增水资源费信息",
		url : "parameterManage/addWaterSourcePricePage.do",
		width : "800",
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditWaterSourcePrice(thisPop, "parameterManage/addWaterSourcePrice.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}

/**
 * 保存新增/修改后的水资源费值
 */
function addAndEditWaterSourcePrice(thisPop, url) {
	$("#inYearsMsg").empty();
	$("#waterSourcePriceMsg").empty();
	var param = $("#waterSourcePriceForm").serialize();
	// 增加数据输入校验
	var flag = true;
	var reg = /^-?\d+\.?\d{0,2}$/;
	var inYears = $("#inYear").val();
	var waterSourcePrice = $("#waterSourcePrice").val();
	if(null==inYears || ""==inYears) {
		flag = false;
		$("#inYearsMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} 
	if(null==waterSourcePrice || ""==waterSourcePrice) {
		flag = false;
		$("#waterSourcePriceMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(waterSourcePrice)) {
		flag = false;
		$("#waterSourcePriceMsg").html("<font style='color:red;line-height:32px;'>请输入两位小数正数</font>");
	} 
	if(flag) {
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadWaterSourcePriceList();
			} else {
				$.Popup.close(thisPop);
			}
		});
	} else {
		return;
	}
}

/**
 * 时间控件失去焦点后的判断
 * @param _this
 */
function validateDateInfo(_this) {
	$("#inYearsMsg").empty();
	if(null==$(_this).val() || ""==$(_this).val()) {
		$("#inYearsMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} 
}

/**
 * 资源费失去焦点后判断
 * @param _this
 */
function validateWaterSource(_this) {
	$("#waterSourcePriceMsg").empty();
	var reg = /^-?\d+\.?\d{0,2}$/;
	if(null==$(_this).val() || ""==$(_this).val()) {
		$("#waterSourcePriceMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test($(_this).val())) {
		$("#waterSourcePriceMsg").html("<font style='color:red;line-height:32px;'>请输入两位小数正数</font>");
	} 
}

/**
 * 修改水资源费率信息
 * @param id
 */
function editWaterSourcePricePage(id) {
	var contentMsg = {
		id : "editWaterSourcePricePage",
		title : "修改水资源费率信息",
		url : "parameterManage/editWaterSourcePricePage.do",
		width : "800",
		paraData : {
			id : id
		},
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditWaterSourcePrice(thisPop, "parameterManage/editWaterSourcePrice.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}

/**
 * 删除水资源费信息
 */
function delWaterSourcePrice() {
	var ids = [];
	var selectRow = $("#paramWaterSourcePriceDiv tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		ids.push(selectRow[i].value);
	}
	if (ids.length == 0) {
		$.Popup.create({
			title : "提示",
			content : "请选择要删除的水资源费信息！"
		});
		return false;
	}
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择水资源费信息？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("parameterManage/delWaterSourcePrice.do", {
					ids : ids.toString()
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadWaterSourcePriceList();
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
 * 根据Id单一删除信息
 * @param id
 */
function delWaterSourcePriceById(id) {
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择水资源费信息？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("parameterManage/delWaterSourcePrice.do", {
					ids : id
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadWaterSourcePriceList();
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

function baseWaterSourcePriceList() {
	showMark();
  	
	$.ajax({
		url : "parameterManage/waterSourcePrice.do",
		success : function(data){
			$("#paramWaterSourcePriceDiv").html(data);
			hideMark();
	    },
	    error: function (xhr, error, thrown) {
	    	hideMark();
   	    }
	});
}