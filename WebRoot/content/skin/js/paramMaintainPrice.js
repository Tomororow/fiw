/************************* 末级渠系费率js *************************/
$(function(){
	$("#checkAll").live("click", function() {
		var check = $(this).attr("checked");
		var ckList = $("#maintainPriceDiv tbody input[type='checkbox']");
		if (check) {
			ckList.attr("checked", true);
		} else {
			ckList.removeAttr("checked");
		}
	});
});

/**
 * 重新加载数据列表
 * @param params
 * @param flag
 */
function loadMaintainPriceList(params, flag) {
	showMark();
	$.get("parameterManage/maintainPrice.do", params, function(data) {
		$("#maintainPriceDiv").html(data);
		if (flag == undefined || !flag) {
			hideMark();
		} else {
			hideMarkLoading();
		}
	});
}

/**
 * 分页查询
 * @param page
 */
function changeMaintainPricePage(page) {
	showMark();
	$.ajax({
		url: "parameterManage/maintainPrice.do",
		data: {
			pageNo: page
		},
		success: function(data) {
			$("#maintainPriceDiv").html(data);
			hideMark();
		},
		error: function(xhr, error, thrown) {
			hideMark();
		}
	});
}

/**
 * 跳转到末级渠系费率新增页面
 */
function addMaintainPricePage() {
	var contentMsg = {
		id : "addMaintainPricePage",
		title : "新增末级渠系费率",
		url : "parameterManage/addMaintainPricePage.do",
		width : "800",
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditMaintainPrice(thisPop, "parameterManage/saveMaintainPrice.do");
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 修改末级渠系费率信息页面
 * @param id
 */
function editMaintainPricePage(id){
	var contentMsg = {
		id:"editMaintainPricePage",
        title: "修改末级渠系费率",   
        url:"parameterManage/editMaintainPricePage.do",
        width:"800",
        paraData:{id:id},
        requestMethod: 'ajax',
        tbar: [{
        	text: "确定",
        	clsName: "homebg popup-confirm",
        	handler: function (thisPop) {
        		addAndEditMaintainPrice(thisPop,"parameterManage/editMaintainPrice.do");
        	}
        }]
	};
	$.Popup.create(contentMsg);
}

/**
 * 保存新增的末级渠系费率
 * @param thisPop
 * @param url
 */
function addAndEditMaintainPrice(thisPop, url) {
	// 获取页面参数
	var params = $("#maintainPriceForm").serialize();
	
	if(validateMaintainPriceForm()){
		fnAjaxRequest(url, params, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadMaintainPriceList();
				$.Popup.close(thisPop);
			} else {
				$.Popup.close(thisPop);
			}
		});
	}
}

/**
 * 删除信息(单选/多选)
 * @param id
 */
function delMaintainPriceById(id) {
	var ids = [];
    if (id) {
    	ids.push(id);
    }else{
    	var selectRow = $("#maintainPriceDiv tbody input[type='checkbox']:checked");
	    for (var i = 0; i < selectRow.length; i++) {
	    	ids.push(selectRow[i].value);
	    }
    }
    if (ids.length == 0) {
    	$.Popup.create({ title: "提示", content: "请选择需要删除的信息！"});
  	  	return false;
    }
    var showConfirm = true;
    var confirmMsg = {
		title: "提示",
		content: "确定删除所选择的末级渠系费率信息?",
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				fnAjaxRequest("parameterManage/deleteMaintainPrice.do", {ids:ids.toString()}, "json", "POST", function(data){
					fnDSuccess(data,thisPop,showConfirm);
 			    	if(data.success){
 			    		loadMaintainPriceList();
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

/**
 * 添加修改表单验证
 * @returns
 */
function validateMaintainPriceForm(){
	return $("#maintainPriceForm").validate({
		rules: {
			inYear: {
	    		required: true
	    	},
	    	maintainPrice: {
	    		required: true,
	    		number: true
	    	}
		},
	    messages: {
	    	inYear: {
	    		required: "不能为空"
	    	},
	    	maintainPrice: {
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