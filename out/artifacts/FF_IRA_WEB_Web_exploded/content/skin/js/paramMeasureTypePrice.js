$(function(){
	$("#checkAll").live("click", function() {
		var check = $(this).attr("checked");
		var ckList = $("#paramMeasureTypePriceDiv tbody input[type='checkbox']");
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
function changeMeasureTypePricePage(page) {
	showMark();
	$.ajax({
		url : "parameterManage/MeasureTypePrice.do",
		data : {
			pageNo : page
		},
		success : function(data) {
			$("#paramMeasureTypePriceDiv").html(data);
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
function loadMeasureTypePriceList(params, flag) {
	showMark();
	$.get("parameterManage/measureTypePrice.do", params, function(data) {
		$("#paramMeasureTypePriceDiv").html(data);
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
function addMeasureTypePriceInfo() {
	var contentMsg = {
		id : "addParamMeasureTypePrice",
		title : "新增计量水费信息",
		url : "parameterManage/addMeasureTypePricePage.do",
		width : "800",
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditMeasureTypePrice(thisPop, "parameterManage/addMeasureTypePrice.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}

/**
 * 保存新增后的计量水费值
 */
function addAndEditMeasureTypePrice(thisPop, url) {
	// 清空信息提示范围标签
	$("#inYearsMsg").empty();
	$("#measureTypePriceMsg").empty();
	// 获取不能为空的控件的值
	var inYears = $("#inYear").val();
	var measureTypePrice = $("#measureTypePrice").val();
	// js序列化表单
	var param = $("#measureTypePriceForm").serialize();
	// 增加数据输入校验
	var flag = true;
	var reg = /^-?\d+\.?\d{0,2}$/;
	// 判断必填项不为空
	if(null==inYears || ""==inYears) {
		flag = false;
		$("#inYearsMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} 
	if(null==measureTypePrice || ""==measureTypePrice) {
		flag = false;
		$("#measureTypePriceMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else if(!reg.test(measureTypePrice)) {
		flag = false;
		$("#measureTypePriceMsg").html("<font style='color:red;line-height:32px;'>请输入两位小数正数</font>");
	} 
	
	if(flag){
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadMeasureTypePriceList();
			} else {
				$.Popup.close(thisPop);
			}
		});
	} else {
		return;
	}
}

/**
 * 修改计量水费信息
 * @param id
 */
function editMeasureTypePricePage(id) {
	var contentMsg = {
		id : "editMeasureTypePricePage",
		title : "修改计量水费信息",
		url : "parameterManage/editMeasureTypePricePage.do",
		width : "800",
		paraData : {
			id : id
		},
		requestMethod : 'ajax',
		tbar : [{
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditMeasureTypePrice(thisPop, "parameterManage/editMeasureTypePrice.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}

/**
 * 删除计量水费
 */
function delMeasureTypePrice() {
	var ids = [];
	var selectRow = $("#paramMeasureTypePriceDiv tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		ids.push(selectRow[i].value);
	}
	if (ids.length == 0) {
		$.Popup.create({
			title : "提示",
			content : "请选择要删除的计量水费信息！"
		});
		return false;
	}
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择计量水费信息？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("parameterManage/delMeasureTypePrice.do", {
					ids : ids.toString()
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadMeasureTypePriceList();
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
function delMeasureTypePriceById(id) {
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择计量水费信息？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("parameterManage/delMeasureTypePrice.do", {
					ids : id
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadMeasureTypePriceList();
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

function baseMeasureTypePriceList() {
	showMark();
  	
	$.ajax({
		url : "parameterManage/measureTypePrice.do",
		success : function(data){
			$("#paramMeasureTypePriceDiv").html(data);
			hideMark();
	    },
	    error: function (xhr, error, thrown) {
	    	hideMark();
   	    }
	});
}