/**********************************水管区域功能脚本***************************************************/
/**
 * 初始化页面
 */
$(function() {
	$(".leftTree").height($(".left").outerHeight() - 10);
	$("#checkAll").live("click", function() {
		var check = $(this).attr("checked");
		var ckList = $("#sysWaterAreaDiv tbody input[type='checkbox']");
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
function changePage(page) {
	showMark();

	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var id = sNodes[0].id;

	$.ajax({
		url : "sysWaterArea/list.do",
		data : {
			id : id,
			pageNo : page
		},
		success : function(data) {
			$("#sysWaterAreaContent").html(data);
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
function loadSysWaterAreaList(params, flag) {
	showMark();
	$.get("sysWaterArea/list.do", params, function(data) {
		$("#sysWaterAreaContent").html(data);
		if (flag == undefined || !flag) {
			hideMark();
		} else {
			hideMarkLoading();
		}
	});
}

/**
 * 新增水管区域
 */
function addSysWaterArea() {
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var name = "";
	var id = "";
	if (sNodes == "") {
		$.Popup.create({
			title : "提示",
			content : "请选择水管区域"
		});
		return false;
	} else {
		id = sNodes[0].id;
		name = sNodes[0].name;
		waterAreaLevel = sNodes[0].areaLevel;
	}
	var contentMsg = {
		id : "addSysWaterArea",
		title : "新增水管区域-所属水管区域【" + name + "】",
		url : "sysWaterArea/addPage.do",
		width : "500",
		paraData : {
			parentWaterAreaId : id,
			waterAreaLevel : parseInt(waterAreaLevel) + 1
		},
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditSysWaterArea(thisPop,
						"sysWaterArea/addSysWaterArea.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}
/**
 * 编辑区域
 */
function editSysWaterArea(id) {
	var contentMsg = {
		id : "editSysWaterArea",//为了调用共同的方法
		title : "修改水管区域",
		url : "sysWaterArea/editPage.do",
		width : "500",
		paraData : {
			id : id
		},
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditSysWaterArea(thisPop,
						"sysWaterArea/editSysWaterArea.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}
/**
 * 保存/编辑公用方法
 */
function addAndEditSysWaterArea(thisPop, url) {
	$("#waterAreaCode").next('span').remove();
	var htmltv = "<span id='waterAreaCodeMsg'></span>";
	$("#waterAreaCode").after(htmltv);
	$("#waterAreaCodeMsg").empty();
	var flag = true;
	var param = $("#sysWaterAreaForm").serialize();
	var waterAreaCode = $("#waterAreaCode").val();
	var reg = /^\d+$/g;
	if(!reg.test(waterAreaCode)) {
		flag = false;
		$("#waterAreaCodeMsg").html("<font style='color:red;line-height:32px;'>请输入8位数字</font>");
	}
	var waterAreaId = $("#id").val();
	var waterAreaName = $("#waterAreaName").val();
	var parentWaterAreaId = $("#parentWaterAreaId").val();
	if (validateSysWaterAreaForm() && flag && uniqueWaterArea(parentWaterAreaId, waterAreaId, waterAreaCode, waterAreaName)) {
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				refreshAreaTreeAndAreaList();
			} else {
				$.Popup.close(thisPop);
			}
		});
	}
}

/**
 * 检验新增、修改时编码、名称唯一性
 * @param parentWaterAreaId
 * @param waterAreaId
 * @param waterAreaCode
 * @param waterAreaName
 */
function uniqueWaterArea(parentWaterAreaId, waterAreaId, waterAreaCode, waterAreaName) {
	var result = true;
	// 设置同步
	$.ajaxSetup({
		async : false
	});
	$.post("sysWaterArea/uniqueWaterArea.do", {
		parentWaterAreaId : parentWaterAreaId,
		waterAreaId : waterAreaId,
		waterAreaCode : waterAreaCode,
		waterAreaName : waterAreaName
	}, function(data) {
		if (data.success) {
			result = true;
		} else {
			result = false;
		}
	}, "json");
	// 恢复异步
	$.ajaxSetup({
		async : true
	});
	if (!result)
		alert("水管区域编码或名称有重复！");
	return result;
}

/**
 * 删除区域
 */
function delSysWaterArea(id) {
	var firstAreaId = $("#firstAreaId").val();
	var ids = [];
	if (id) {
		if (id == firstAreaId) {
			$.Popup.create({
				title : "提示",
				content : "第一级区域不能删除"
			});
			return false;
		}
		ids.push(id);
	} else {
		var selectRow = $("#sysWaterAreaDiv tbody input[type='checkbox']:checked");
		for (var i = 0; i < selectRow.length; i++) {
			if (selectRow[i].value == firstAreaId) {
				$.Popup.create({
					title : "提示",
					content : "第一级区域不能删除"
				});
				return false;
			}
			ids.push(selectRow[i].value);
		}
	}
	if (ids.length == 0) {
		$.Popup.create({
			title : "提示",
			content : "请选择区域"
		});
		return false;
	}
	for (index in ids) {
		if (checkChildAreaExist(ids[index])) {
			$.Popup.create({
				title : "提示",
				content : "所选择区域下有子区域存在，不允许删除"
			});
			return false;
		}
	}
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择区域吗？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("sysWaterArea/delSysWaterArea.do", {
					ids : ids.toString()
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						refreshAreaTreeAndAreaList();
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
 * 刷新水管区域树和水管区域列表
 */
function refreshAreaTreeAndAreaList() {
	ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do", sysWaterAreaList);
	function sysWaterAreaList(node) {
		var params = {
			id : node.id
		};
		loadSysWaterAreaList(params, false);
	}
}

/**
 * 属性校验
 * @returns
 */
function validateSysWaterAreaForm() {
	return $("#sysWaterAreaForm").validate({
		rules : {
			waterAreaCode : {
				required : true,
				maxlength : 8
			},
			waterAreaName : {
				required : true,
				specialCharValidate : true,
				maxlength : 32
			},
			remark : {
				specialCharValidate : true
			}
		},
		messages : {
			waterAreaCode : {
				required : "必填项",
				maxlength : "不超过8个字"
			},
			waterAreaName : {
				required : "必填项",
				specialCharValidate : "不能包含特殊字符",
				maxlength : "不超过32个字"
			},
			remark : {
				specialCharValidate : "不能包含特殊字符"
			}
		},
		showErrors : showErrors,
		onkeyup : function(element, event) {
			if (event.which === 9
					&& this.elementValue(element) === "") {
				return;
			} else if (element.name in this.submitted
					|| element === this.lastElement) {
				this.element(element);
				$(element).next('span').remove();//移除span
			}
		}
	}).form();
}

/**
 * 检查区域下是否有子区域
 * @returns
 */
function checkChildAreaExist(id) {
	var result = true;
	// 设置同步
	$.ajaxSetup({
		async : false
	});
	$.post("sysWaterArea/checkChildAreaExist.do", {
		id : id
	}, function(data) {
		if (data.success) {
			result = true;
		} else {
			result = false;
		}
	}, "json");
	// 恢复异步
	$.ajaxSetup({
		async : true
	});
	return result;
}

/**
 * 分页查询
 * @param page
 */
function changePage(page) {
	showMark();

	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var id = sNodes[0].id;

	$.ajax({
		url : "sysWaterArea/list.do",
		data : {
			id : id,
			pageNo : page
		},
		success : function(data) {
			$("#sysWaterAreaContent").html(data);
			hideMark();
		},
		error : function(xhr, error, thrown) {
			hideMark();
		}
	});
}

/**
 * 查询水管区域编码
 */
/*function searchWaterAreaCode() {
	showMark();
	var waterAreaCode = $("#waterAreaCode").val();
	if (waterAreaCode == '请输入水管区域编码') {
		waterAreaCode = "";
	}
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var id = sNodes[0].id;

	$.ajax({
		url : "sysWaterArea/list.do",
		data : {
			id : id,
			searchWaterAreaCode : waterAreaCode
		},
		success : function(data) {
			$("#sysWaterAreaContent").html(data);
			hideMark();
		},
		error : function(xhr, error, thrown) {
			hideMark();
		}
	});
}*/