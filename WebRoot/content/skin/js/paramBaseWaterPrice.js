/************************* 基本水费费率js *************************/
$(function(){
	$("#checkAll").live("click", function() {
		var check = $(this).attr("checked");
		var ckList = $("#baseWaterPriceDiv tbody input[type='checkbox']");
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
function loadBaseWaterPriceList(params, flag) {
	showMark();
	$.get("parameterManage/baseWaterPrice.do", params, function(data) {
		$("#baseWaterPriceDiv").html(data);
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
function changeBaseWaterPricePage(page) {
	showMark();
	$.ajax({
		url: "parameterManage/baseWaterPrice.do",
		data: {
			pageNo: page
		},
		success: function(data) {
			$("#baseWaterPriceDiv").html(data);
			hideMark();
		},
		error: function(xhr, error, thrown) {
			hideMark();
		}
	});
}

/**
 * 跳转到基本水费费率新增页面
 */
function addBaseWaterPricePage() {
	var contentMsg = {
		id : "addBaseWaterPricePage",
		title : "新增基本水费费率",
		url : "parameterManage/addBaseWaterPricePage.do",
		width : "800",
		requestMethod : 'ajax',
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				addAndEditBaseWaterPrice(thisPop, "parameterManage/saveBaseWaterPrice.do");
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 修改基本费率信息页面
 * @param id
 */
function editBaseWaterPricePage(id){
	var contentMsg = {
		id:"editBaseWaterPricePage",
        title: "修改基本水费费率",   
        url:"parameterManage/editBaseWaterPricePage.do",
        width:"800",
        paraData:{id:id},
        requestMethod: 'ajax',
        tbar: [{
        	text: "确定",
        	clsName: "homebg popup-confirm",
        	handler: function (thisPop) {
        		addAndEditBaseWaterPrice(thisPop,"parameterManage/editBaseWaterPrice.do");
        	}
        }]
	};
	$.Popup.create(contentMsg);
}

/**
 * 保存新增的基本水费费率值
 * @param thisPop
 * @param url
 */
function addAndEditBaseWaterPrice(thisPop, url) {
	// 获取页面参数
	var params = $("#baseWaterPriceForm").serialize();
	
	if(validateBaseWaterPriceForm()){
		fnAjaxRequest(url, params, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadBaseWaterPriceList();
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
function delBaseWaterPriceById(id) {
	var ids = [];
    if (id) {
    	ids.push(id);
    }else{
    	var selectRow = $("#baseWaterPriceDiv tbody input[type='checkbox']:checked");
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
		content: "确定删除所选择的基本水费率信息?",
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				fnAjaxRequest("parameterManage/deleteBaseWater.do", {ids:ids.toString()}, "json", "POST", function(data){
					fnDSuccess(data,thisPop,showConfirm);
 			    	if(data.success){
 			    		loadBaseWaterPriceList();
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
function validateBaseWaterPriceForm(){
	return $("#baseWaterPriceForm").validate({
		rules: {
			inYear: {
	    		required: true
	    	},
	    	standardPrice: {
	    		required: true,
	    		number: true
	    	},
	    	exceedPrice: {
                required: true,
	    		number: true
            }
		},
	    messages: {
	    	inYear: {
	    		required: "不能为空"
	    	},
	    	standardPrice: {
	    		required: "不能为空",
	    		number: "必须为数字"
	    	},
	    	exceedPrice: {
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