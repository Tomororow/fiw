/**
 * @description: 短信模板
 * @author: zhaojx
 * @date: 2017/04/18
 */
 
/**
 * @description: 多选框选中
 */
$(function() {
	$(".leftTree").height($(".left").outerHeight() - 10);
	$("#checkAll").live("click", function() {
		var check = $(this).attr("checked");
		var ckList = $("#sysManageInfoContent tbody input[type='checkbox']");
		if (check) {
			ckList.attr("checked", true);
		} else {
			ckList.removeAttr("checked");
		}
	});
});

/**
 * 重新加载短信模板列表页
 * @param params
 * @param flag
 */
function loadMsgList(params, flag) {
	showMark();
	$.get("sysShortMsg/list.do", params, function(data) {
		$("#sysManageInfoContent").html(data);
		if (flag == undefined || !flag) {
			hideMark();
		} else {
			hideMarkLoading();
		}
	});
}

/**
 * @description: 分页
 * @param page
 */
function changeMsgInfoPage(page) {
	var params = {
		pageNo : page
	};
	loadMsgList(params);
}

/**
 * @description: 新增短信模板面板
 */
function add() {
	var contentMsg = {
		title : "新增短信模板",
		url : "sysShortMsg/addPage.do",
		width : "500",
		requestMethod : 'ajax',
		tbar : [{
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addMsg(thisPop);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * @description: 新增短信模板
 * @param thisPop
 */
function addMsg(thisPop) {
	var url = "sysShortMsg/add.do";
	var param = $("#sysShortMsgForm").serialize();
	
	// 短息编码 验证
	var checkCode = uniqueMsgCode();
	if (validateBDIForm() && checkCode) {
		$("#msgCodeInfo").empty();
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadMsgList(param, false);
			}
		});
	}
}

/**
 * @description: 删除多个模板
 */
function delAllMsg() {
	var ids = [];
	var selectRow = $("#sysManageInfoContent tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		ids.push(selectRow[i].value);
	}
	if (ids.length == 0) {
		$.Popup.create({
			title : "提示",
			content : "请选择要删除的短信模板"
		});
		return false;
	}
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择短信模板吗？",
		tbar : [{
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("sysShortMsg/delMsg.do", {
					ids : ids.toString()
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadMsgList();
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
		}]
	};
	$.Popup.create(confirmMsg);
}

/**
 * @description: 删除单个短信模板
 * @param id
 */
function delMsgById(id) {
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择短信模板吗？",
		tbar : [{
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("sysShortMsg/delMsg.do", {
					ids : id
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadMsgList();
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
		}]
	};
	$.Popup.create(confirmMsg);
}

/**
 * @description: 跳转短息模板窗口
 * @param id
 */
function edit(id) {
	var contentMsg = {
		title : "修改短信模板",
		url : "sysShortMsg/editPage.do",
		paraData : {
			id : id
		},
		width : "500",
		requestMethod : 'ajax',
		tbar : [{
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				editMsg(thisPop);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * @description: 编辑短息模板
 * @param thisPop
 */
function editMsg(thisPop) {
	var url = "sysShortMsg/edit.do";
	var param = $("#sysShortMsgForm").serialize();
	
	if (validateBDIForm()) {
		$("#msgCodeInfo").empty();
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadMsgList(param, false);
			}
		});
	}
}

/**
 * @description: 属性校验
 * @returns
 */
function validateBDIForm() {
	return $("#sysShortMsgForm").validate({
		rules : {
			MsgCode : {
				required : true,
				maxlength : 32,
				specialCharValidate: true
			},
			MsgContent : {
				required : true,
				maxlength : 300
			}
		},
		messages : {
			MsgCode : {
				required : "&nbsp;&nbsp;<font style='color:red;line-height:32px;'>必填项</font>",
				maxlength : "&nbsp;&nbsp;<font style='color:red;line-height:32px;'>不超过32个字</font>",
				specialCharValidate : "&nbsp;&nbsp;<font style='color:red;line-height:32px;'>不能包含特殊字符</font>"
			},
			MsgContent : {
				required : "&nbsp;&nbsp;<font style='color:red;line-height:32px;'>必填项</font>",
				maxlength : "&nbsp;&nbsp;<font style='color:red;line-height:32px;'>不超过300个字</font>"
			}
		},
		showErrors : showErrors,
		onkeyup : function(element, event) {
			if (event.which === 9 && this.elementValue(element) === "") {
				return;
			} else if (element.name in this.submitted || element === this.lastElement) {
				this.element(element);
				$(element).next('span').remove();// 移除span
			}
		}
	}).form();
}

/**
 * @description: 短信编码 唯一检查
 */
function uniqueMsgCode() {
	$("#msgCodeInfo").empty();
	var MsgCode = $("#MsgCode").val();
	var flag = true;
	// 执行jquery ajax post请求
	if (null != MsgCode && "" != MsgCode) {
		$.post("sysShortMsg/uniqueMsgCode.do", {
			msgCode : MsgCode
		}, function(data) {
			if (data == "success") {
				if (undefined == $("#msgCodeInfo").attr("id")) {
					$("#MsgCode").next('span').remove();
					$("#MsgCode").after("<span id='msgCodeInfo'></span>");
					$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以使用</font>");
				} else {
					$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以使用</font>");
				}
			} else {
				if (undefined == $("#msgCodeInfo").attr("id")) {
					$("#MsgCode").next('span').remove();
					$("#MsgCode").after("<span id='msgCodeInfo'></span>");
					$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>已存在</font>");
					flag = false;
				} else {
					$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>已存在</font>");
					flag = false;
				}
			}
		}, "json");
		$.ajaxSetup({
			async : false
		});
	} else {
		$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>必填项</font>");
		flag = false;
	}
	return flag;
}