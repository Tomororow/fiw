/**********************************区域功能脚本***************************************************/
/**
 * 初始化页面
 */
$(function() {
	$(".leftTree").height($(".left").outerHeight() - 10);
	$("#checkAll").live("click", function() {
		var check = $(this).attr("checked");
		var ckList = $("#sysAreaDiv tbody input[type='checkbox']");
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
		url : "sysArea/list.do",
		data : {
			id : id,
			pageNo : page
		},
		success : function(data) {
			$("#deviceContent").html(data);
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
function loadSysAreaList(params, flag) {
	showMark();
	$.get("sysArea/list.do", params, function(data) {
		$("#deviceContent").html(data);
		if (flag == undefined || !flag) {
			hideMark();
		} else {
			hideMarkLoading();
		}
	});
}

/**
 * 新增区域
 */
function addSysArea() {
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var sNodes = treeObj.getSelectedNodes();
	var name = "";
	var id = "";
	if (sNodes == "") {
		$.Popup.create({
			title : "提示",
			content : "请选择行政区域"
		});
		return false;
	} else {
		id = sNodes[0].id;
		name = sNodes[0].name;
		areaLevel = sNodes[0].areaLevel;
	}
	var contentMsg = {
		id : "addSysArea",
		title : "新增区域-所属区域【" + name + "】",
		url : "sysArea/addPage.do",
		width : "500",
		paraData : {
			parentAreaId : id,
			areaLevel : parseInt(areaLevel) + 1
		},
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				if(waterAreaCodes==""||waterAreaCodes==null){
					alert("请选择水管区域...");
				}else{
					addAndEditSysArea(thisPop, "sysArea/addSysArea.do");
				}
			}
		}]
	};
	$.Popup.create(contentMsg);
}
//水管区选择
function getSubWaterAreaId(_this) {
	// 获取到对应下拉框同一级的id=num的标签内容
	var num = $(_this).next("#num").val();
	// 下拉框选择内容不为空，则开始关联到子节点信息
	if(""!=$(_this).val()) {
		// 获取到子节点下拉框同一级的id=num的标签内容
		var subNum = $(_this).next("#num").val();
		$("#subWaterArea_"+parseInt(subNum)).empty();
		$.post("baseDeviceInfo/getChildAreaInfo.do",{
			id : $(_this).val()
		},function(data){
			// 有子区域信息集合，则开始拼接子区域下拉框信息
			if(data!=null && data!=''){
				var htmltv = "<select class='pop-select' id='waterAreaId'  style='display: inline-block;width:90px;' name='waterAreaId' onchange='getSubWaterAreaId(this)'>";
				htmltv += "<option value=''>--请选择水管区域--</option>";
				for(var i=0;i<data.length;i++) {
					htmltv += "<option value='"+data[i].id+"'>"+data[i].waterAreaName+"</option>";
				}
				htmltv += "</select>";
				htmltv += "<input type='hidden' id='num' name='num' value='"+(parseInt(num)+1)+"'/>";
				htmltv += "<span id='subWaterArea_"+(parseInt(num)+1)+"'></span>";
				$("#subWaterArea_"+parseInt(num)).append(htmltv);
			} else { // 该下拉框下没有子区域信息，则给出提示
				alert("该水管区域再无子一级信息");
			}
		},"json");
		// 恢复异步
		$.ajaxSetup({
			async: true
		});
	} else { // 下拉框内容为空，则将下拉框后面的信息置为空
		var subNum = $(_this).next("#num").val();
		$("#subWaterArea_"+parseInt(subNum)).empty();
	}
}
/**
 * 点击确定，给出提示，确定后就不能够再修改
 * @param _this
 */
var waterAreaCodes = "";
function getWaterAreaCode(_this) {
	waterAreaCodes = "";
	// 判断是否有选择水管区域，如果没有则提示至少选择一级水管区域
	if(null!=$("select[name='waterAreaId'] option:selected").val() && ""!=$("select[name='waterAreaId'] option:selected").val()) {
		if($(_this).val()=="修改") {
			$(_this).attr("value","确定");
			$(".pop-select").attr("disabled", false);
			$(".pop-select").css("background-color","#FFFFFF");
		} else {
			var confirmMsg = {
				title: "提示",
				content: "请确定是否选择该水管区域？确定后将不能再更改！",
				tbar: [{
					text: "确定",
					clsName: "homebg popup-confirm",
					handler: function (thisObj) {
						// 关闭弹窗
						$.Popup.close(thisObj);
						$(".pop-select").attr("disabled", true);
						$(".pop-select").css("background-color","#cccccc");
						$(_this).attr("value","修改");
						// 获取到所有的水管区域编码
						$("select[name='waterAreaId'] option:selected").each(function(){
							// 如果有选择为空的下拉框选项，则点击确定按钮后，将该为空的下拉框整个移出
							if(null==$(this).val() || ""==$(this).val()) {
								$(this).parent().remove();
							} else { // 否则将每个下拉框的key按照逗号拼接起来
								waterAreaCodes = $(this).val();
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
	} else {
		alert("请选择水管区域");
	}
}

/**
 * 编辑区域
 */
function editSysArea(areaId) {
	var contentMsg = {
		id : "editSysArea",//为了调用共同的方法
		title : "修改区域",
		url : "sysArea/editPage.do",
		width : "500",
		paraData : {
			id : areaId
		},
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditSysArea(thisPop, "sysArea/editSysArea.do");
			}
		} ]
	};
	$.Popup.create(contentMsg);
}
/**
 * 保存/编辑公用方法
 */
function addAndEditSysArea(thisPop, url) {
	$("#areaCode").next('span').remove();
	var htmltv = "<span id='areaCodeMsg'></span>";
	$("#areaCode").after(htmltv);
	$("#areaCodeMsg").empty();
	var flag = true;
	var param = $("#sysAreaForm").serialize();
	if(param.indexOf("waterAreaId")==-1){
		param = param+"&waterAreaId="+waterAreaCodes;
	}
	var areaCode = $("#areaCode").val();
	var reg = /^\d+$/g;
	if(!reg.test(areaCode)) {
		flag = false;
		$("#areaCodeMsg").html("<font style='color:red;line-height:32px;'>请输入10位数字</font>");
	}
	var areaId = $("#id").val();
	var areaName = $("#areaName").val();
	var parentAreaId = $("#parentAreaId").val();
	if (validateSysAreaForm() && flag && uniqueArea(parentAreaId, areaId, areaCode, areaName)) {
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
 * 检验新增，修改时编码、名称唯一性校验
 * @param parentAreaId
 * @param areaId
 * @param areaCode
 * @param areaName
 * @returns {Boolean}
 */
function uniqueArea(parentAreaId, areaId, areaCode, areaName) {
	var result = true;
	// 设置同步
	$.ajaxSetup({
		async : false
	});
	$.post("sysArea/uniqueArea.do", {
		parentAreaId : parentAreaId,
		areaId : areaId,
		areaCode : areaCode,
		areaName : areaName
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
	if(!result)
		alert("行政区域编码或名称有重复！");
	return result;
}
/**
 * 删除区域
 */
function delSysArea(areaId) {
	var firstAreaId = $("#firstAreaId").val();
	var areaIds = [];
	if (areaId) {
		if (areaId == firstAreaId) {
			$.Popup.create({
				title : "提示",
				content : "第一级区域不能删除"
			});
			return false;
		}
		areaIds.push(areaId);
	} else {
		var selectRow = $("#sysAreaDiv tbody input[type='checkbox']:checked");
		for (var i = 0; i < selectRow.length; i++) {
			if (selectRow[i].value == firstAreaId) {
				$.Popup.create({
					title : "提示",
					content : "第一级区域不能删除"
				});
				return false;
			}
			areaIds.push(selectRow[i].value);
		}
	}
	if (areaIds.length == 0) {
		$.Popup.create({
			title : "提示",
			content : "请选择区域"
		});
		return false;
	}
	for (index in areaIds) {
		if (checkDeviceExist(areaIds[index])) {
			$.Popup.create({
				title : "提示",
				content : "所选择区域下有设备存在，不允许删除"
			});
			return false;
		}
		if (checkChildAreaExist(areaIds[index])) {
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
				fnAjaxRequest("sysArea/delSysArea.do", {
					ids : areaIds.toString()
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
 * 刷新区域树和区域列表
 */
function refreshAreaTreeAndAreaList() {
	ztreeFun(
			$("#ztree"),
			"sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false",
			sysAreaList);
	function sysAreaList(node) {
		var params = {
			id : node.id
		};
		loadSysAreaList(params, false);
	}
}

/**
 * 属性校验
 * @returns
 */
function validateSysAreaForm() {
	return $("#sysAreaForm").validate({
		rules : {
			areaCode : {
				required : true,
				maxlength : 10
			},
			areaName : {
				required : true,
				specialCharValidate : true,
				maxlength : 32
			},
			remark : {
				specialCharValidate : true
			}
		},
		messages : {
			areaCode : {
				required : "必填项",
				maxlength : "不超过10个字"
			},
			areaName : {
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
 * 检查区域下是否有机井设备
 * @returns
 */
function checkDeviceExist(areaId) {
	var result = true;
	// 设置同步
	$.ajaxSetup({
		async : false
	});
	$.post("sysArea/checkDeviceExist.do", {
		areaId : areaId
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
 * 检查区域下是否有子区域
 * @returns
 */
function checkChildAreaExist(areaId) {
	var result = true;
	// 设置同步
	$.ajaxSetup({
		async : false
	});
	$.post("sysArea/checkChildAreaExist.do", {
		areaId : areaId
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