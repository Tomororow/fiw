var fleg;
var params;
$(function(){
	$("#checkAll").live("click", function() {
		var check = $(this).attr("checked");
		var ckList = $("#paramPumpingWellDiv tbody input[type='checkbox']");
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
	debugger
	showMark();
	var beginTime = $("#query_beginTime").val();
	var endTime = $("#query_endTime").val();
	if(fleg!=1){
		params={pageNo:page,beginTime:beginTime,endTime:endTime};
	}else{
		params["pageNo"] = page;
		params["beginTime"] = beginTime;
		params["endTime"] = endTime;
	}
	
	loadPumpingWellList(params,false);
}

/**
 * 点击区域，加载右侧区域列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadPumpingWellList(params, flag) {
	debugger
	showMark();
	$.get("parameterManage/pumpingWell.do", params, function(data) {
		$("#paramPumpingWellDiv").html(data);
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
function addPumpingWellInfo() {
	var contentMsg = {
		id : "addParamPumpingWell",
		title : "新增机井异常参数",
		url : "parameterManage/addPumpingWellPage.do",
		width : "900",
		requestMethod : 'ajax',
		tbar : [{
			text : "确定",
			clsName : "confirmBtn",
			handler : function(thisPop) {
				addAndEditPumpingWell(thisPop, "parameterManage/addPumpingWell.do");
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 保存新增后的异常机井参数
 */
function addAndEditPumpingWell(thisPop, url) {
	
	$(".pop-select").attr("disabled", false);
	//TODO
	$("#waterlow").val() ==""?$("#waterlow").attr("value",0):$("#waterlow").val();
	var param = $("#pumpingWellForm").serialize();
	/*var nodeIds = $('#waterAreaNum option:selected').text();//水管区域
	var deviceCode = $("#deviceName").val();//机井名称
	var deviceCode = $("#deviceCode").val();//机井编码
	var nodeIds = $('#deviceWellUse option:selected').text();//用水类型
	var deviceCode = $("#query_beginTime").val();//起始时间
	var deviceCode = $("#query_endTime").val();//结束时间
*/	//检查水管区域下拉框选中状况
	var ckWaterAreaID = checkWaterAreaID();
	if(validPumpingWellForm() && ckWaterAreaID){
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			debugger
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadPumpingWellList();
			} else {
				$.Popup.close(thisPop);
			}
		});
		hideMark();
	}
}
/**
 * 修改当前异常数据
 */
function editPumpingWellById(id){
	var contentMsg = {
			id : "addParamPumpingWell",
			title : "修改机井异常参数",
			url : "parameterManage/editPumpingWell.do?id="+id,
			width : "900",
			requestMethod : 'ajax',
			tbar : [{
				text : "确定",
				clsName : "confirmBtn",
				handler : function(thisPop) {
					editPumpingWell(thisPop);
				}
			}]
		};
		$.Popup.create(contentMsg);
}
function editPumpingWell(thisPop){
	var param = $("#editWellForm").serialize();
	var url="parameterManage/editPumpingData.do";
	fnAjaxRequest(url, param, "json", "POST", function(data) {
		if(data==1){
			$.Popup.create({ title: "提示", content: "修改成功" });
			$.Popup.close(thisPop);
			loadPumpingWellList();
		
		}
	});
}


/**
 * 查询当前异常数据
 */

function queryInfoparam(){
	debugger
	fleg = 1;
	var deviceCode = $("#deviceCode").val().trim();
	if (deviceCode == '请输入机井编码') {
		deviceCode = "";
	}
	var deviceName = $("#deviceName").val().trim();
	if (deviceName == '请输入机井名称') {
		deviceName = "";
	}
	var deviceWellUse = $("select[name='deviceWellUse'] option:selected").val().trim();
	if(deviceWellUse==""){
		deviceWellUse = "灌溉";
	}
	var beginTime = $("#query_beginTime").val();
	var endTime = $("#query_endTime").val();
	params = {
			deviceCode : deviceCode,
			deviceName : deviceName,
			deviceWellUse : deviceWellUse,
			beginTime : beginTime,
			endTime : endTime,
			
		};
	loadPumpingWellList(params,false);
}

//充值后水量无变化时间
function handleMessChange(sign){
	if(sign.checked){
		$("#backone").show();
		
	}else{
		$("#backone").hide();
		
	}
	
}
/**
 * 删除多个功率值
 */
function delPumpingWell() {
	var ids = [];
	var selectRow = $("#paramPumpingWellDiv tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		ids.push(selectRow[i].value);
	}
	if (ids.length == 0) {
		$.Popup.create({
			title : "提示",
			content : "请选择要删除的异常机井参数信息"
		});
		return false;
	}
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择异常机井参数信息吗？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("parameterManage/delPumpingWell.do", {
					ids : ids.toString()
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadPumpingWellList();
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
function delPumpingWellById(id) {
	var confirmMsg = {
		title : "提示",
		content : "确认删除所选择异常机井参数信息吗？",
		tbar : [ {
			text : "确定",
			clsName : "homebg popup-confirm",
			handler : function(thisPop) {
				fnAjaxRequest("parameterManage/delPumpingWell.do", {
					ids : id
				}, "json", "POST", function(data) {
					fnDSuccess(data, thisPop);
					if (data.success) {
						loadPumpingWellList();
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
 * 获取水管区域子区域级联操作
 * 	1、通过最高一级选择，如果该选择的有子节点（子区域），则将在旁边重新生成一个子区域下拉框集合
 * 	2、当选择了--请选择水管区域--选项，则将该下拉框后面的子下拉框全部清除
 */
function getSubWaterAreaId(_this) {
	debugger
	$("#waterAreaMsg").empty();
	// 获取到对应下拉框同一级的id=num的标签内容
	var num = $(_this).next("#waterAreaNum").val();
	// 下拉框选择内容不为空，则开始关联到子节点信息
	if(""!=$(_this).val()) {
		var subNum = $(_this).next("#waterAreaNum").val();
		$("#subWaterArea_"+parseInt(subNum)).empty();
		// 获取到子节点下拉框同一级的id=num的标签内容
		var subNum = $(_this).next("#waterAreaNum").val();
		$("#subArea_"+parseInt(subNum)).empty();
		$.post("baseDeviceInfo/getChildAreaInfo.do",{
			id : $(_this).val()
		},function(data){
			debugger
			// 有子区域信息集合，则开始拼接子区域下拉框信息
			if(data!=null && data!=''){
				var htmltv = "<select class='pop-select' style='display: inline-block;width:100px;' name='waterAreaId' onchange='getSubWaterAreaId(this)'>";
				htmltv += "<option value=''>--请选择行政区域--</option>";
				for(var i=0;i<data.length;i++) {
					htmltv += "<option value='"+data[i].id+"'>"+data[i].waterAreaName+"</option>";
				}
				htmltv += "</select>";
				htmltv += "<input type='hidden' id='waterAreaNum' name='waterAreaNum' value='"+(parseInt(num)+1)+"'/>";
				htmltv += "<span id='subWaterArea_"+(parseInt(num)+1)+"'></span>";
				$("#subWaterArea_"+parseInt(num)).append(htmltv);
			} else {// 该下拉框下没有子区域信息，则说明到达最后一级，开始查询设备机井信息
				getLastBaseDeviceId(_this,$("#wellUse").val());
				//alert("该区域下无子区域");
			}
		},"json");
		// 恢复异步
		$.ajaxSetup({
			async: true
		});
	} else {// 下拉框内容为空，则将下拉框后面的信息置为空
		var subNum = $(_this).next("#waterAreaNum").val();
		$("#subWaterArea_"+parseInt(subNum)).empty();
	}
}

/**
 * 根据最下一级的Id来获取机井基本表中机井信息
 * @param _this
 */
function getLastBaseDeviceId(_this,wellUse) {
	// 获取到对应下拉框同一级的id=num的标签内容
	var num = $(_this).next("#waterAreaNum").val();
	// 下拉框选择内容不为空
	if(""!=$(_this).val()) {
		// 获取到子节点下拉框同一级的id=num的标签内容
		var subNum = $(_this).next("#waterAreaNum").val();
		$("#subWaterArea_"+parseInt(subNum)).empty();
		$.post("baseDeviceInfo/getLastDeviceWaterAreaInfo.do", {
			waterAreaId : $(_this).val(),
			wellUse : wellUse
		},function(data) {
			debugger
			// 如果查询到的机井设备信息不为空，则开始拼接机井设备信息下拉框
			if(null!=data && ""!=data) {
				var htmltv = "<select class='pop-select' style='display: inline-block;width:100px;' name='baseDeviceId' onchange='getLastBaseDeviceId(this)'>";
				htmltv += "<option value=''>--请选择机井信息--</option>";
				for(var i=0;i<data.length;i++) {
					htmltv += "<option value='"+data[i].id+"'>"+data[i].deviceName+"</option>";
				}
				htmltv += "</select>";
				htmltv += "<input type='hidden' id='waterAreaNum' name='waterAreaNum' value='"+(parseInt(num)+1)+"'/>";
				htmltv += "<span id='subWaterArea_"+(parseInt(num)+1)+"'></span>";
				$("#subWaterArea_"+parseInt(num)).append(htmltv);
				$("#deviceWellUse").attr("disabled",true);
			} else {
				if($(_this).attr("name")!="baseDeviceId") {
					alert("该水管区域区域下无机井设备信息...");
					//$(_this).val("");
				}
			}
		}, "json");
		// 恢复异步
		$.ajaxSetup({
			async: true
		});
	} else {// 下拉框内容选择为空，则将该下拉框一级给直接清空
		var subNum = $(_this).next("#waterAreaNum").val();
		$("#subWaterArea_"+(parseInt(subNum)-1)).empty();
	}
}

/**
 * 点击确定，给出提示，确定后就不能够再修改
 * @param _this
 */
function getWaterArea(_this) {
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
							// 获取到所有的水管区域编码
							$("select[name='waterAreaId'] option:selected").each(function(){
								// 如果有选择为空的下拉框选项，则点击确定按钮后，将该为空的下拉框整个移出
								if(null==$(this).val() || ""==$(this).val()) {
									$(this).parent().remove();
								}
							});
							// 如果到最后一级，没有选择就将最后一级未选择下拉框移出
							var waterAreaInfo = $("select[name='baseDeviceId'] option:selected").val();
							if(null==waterAreaInfo || ""==waterAreaInfo) {
								$("select[name='baseDeviceId'] option:selected").parent().remove();
								$("#deviceWellUse").attr("disabled",false);
							}
							// 点击确定后，下拉框将不能再选择
							$(".pop-select").attr("disabled", true);
							$(".pop-select").css("background-color","#cccccc");
							$(_this).attr("value","修改");
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
 * 检查行政区域必选项
 */
function checkWaterAreaID(){
	var waterAreaID = $('#waterAreaId option:selected').text();
	var flag = true;
	if(waterAreaID == "--请选择水管区域--"){ 
       	$("#waterAreaId").next('span').remove();
		$("#waterAreaId").after("<span id='msgAreaInfo'></span>");
    	$("#msgAreaInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>必选项</font>");  
        flag = false; 
    }else{
    	$("#waterAreaId").next('span').remove();
    }
    return flag;
}
/**
 * 表单验证
 * @returns boolean
 */
function validPumpingWellForm(){
	return $("#pumpingWellForm").validate({
		rules: {
			startTimes: {
	    		required: true
	    	},
	    	endTimes: {
	    		required:true
	    	},
	    	onnetstate: {
                required: true,
                specialCharValidate: true,
                number: true
            },
            deviceWellUse:{
            	required:true
            },
            backone:{
            	required: true,
            	specialCharValidate: true,
            	number: true
            },
           /* waterlow:{
            	required: true,
            	specialCharValidate: true,
            	number: true
            }*/
		},
	    messages: {
	    	startTimes: {
	    		required: "必填项"
	    	},
	    	endTimes: {
	    		required:"必填项"
	    	},
	    	deviceWellUse: {
	    		required:"必填项"
	    	},
	    	backone:{
	    		required:"必填项"
	    	},
	    	onnetstate: {
                required: "必填项",
                specialCharValidate: "不能包含特殊字符",
                number: "数字"
            },
           /* waterlow:{
            	required: "必填项",
            	specialCharValidate: "不能包含特殊字符",
            	number: "数字"
            }*/
		},
		showErrors:showErrors,
		onkeyup: function( element, event ) {
			if ( event.which === 9 && this.elementValue( element ) === "" ) {
				return;
			} else if ( element.name in this.submitted || element === this.lastElement ) {
				this.element( element );
				$(element).next('span').remove();//移除span
			}
		}
    }).form();
}