// 获取行政树
function getGroupTree() {
    $(".leftnav").click(function () {
        if($(".left").css("display") == "none") {
            $(".leftnav").css("left", "237px"); 
            $(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeLeft.gif");
            $(".right_user").css("left", "275px");
        } else {
            $(".leftnav").css("left", "0px"); 
            $(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeRight.gif");
            $(".right_user").css("left", "20px");
        }
        $(".left").toggle();
        $(".left-bottom").toggle();
    });
	$(".tree").height($(".left").outerHeight()- 10);
	ztreeFun($("#ztree"),"sysWaterArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false",baseWaterChargeList);
}

/**
 * 基本水费查询
 */
function baseWaterChargeList(node) {
	var params = {};
	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称') {
		deviceName = null;
	}
	var deviceCode = $("#deviceCode").val();
	if(deviceCode=='请输入机井编码') {
		deviceCode = null;
	}
	
	params['deviceName'] = deviceName;
	params['deviceCode'] = deviceCode;
	var aryIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	var id = node.id;
	params['waterAreaId'] = id;
	// 调用加载基本水费信息方法
	loadBaseWaterChargeInfoList(params, false);
}

/**
 * 点击区域，加载右侧机井设备列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadBaseWaterChargeInfoList(params, flag){
	showMark();
	$.get("baseWaterCharge/baseWaterCharge.do",params,function(data){
		debugger
		$("#baseWaterChargeContent").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}

/**
 * 分页查询
 * @param page
 */
function changeBaseDeviceWaterPricePage(page) {
	showMark();
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	
  	var deviceName = $("#deviceName").val();
  	if(deviceName=="请输入机井名称") {
  		deviceName = null;
  	}
  	var deviceCode = $("#deviceCode").val();
	if(deviceCode=='请输入机井编码') {
		deviceCode = null;
	}
  	
	$.ajax({
		url : "baseWaterCharge/baseWaterCharge.do",
		data : {waterAreaId:id , pageNo:page, deviceCode:deviceCode, deviceName:deviceName},
		success : function(data){
			$("#baseWaterChargeContent").html(data);
			hideMark();
	    },
	    error: function (xhr, error, thrown) {
	    	hideMark();
   	    }
	});
}

/**
 * 重置查询条件
 */
function baseWaterChargeReset() {
	$("#deviceName").val("请输入机井名称");
	$("#deviceCode").val("请输入机井编码");
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	baseWaterChargeList(sNodes[0]);
}

/**
 * 进入基本收费新增页面
 */
function addBaseWaterCharge() {
	var contentMsg = {
		title: "新增机井基本收费",   
		url: "baseWaterCharge/addBaseWaterChargePage.do",
		width:"800",
		requestMethod: 'ajax',
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				var addUrl = "baseWaterCharge/addBaseWaterCharge.do";
				addBaseWaterChargeInfo(thisPop,addUrl);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 保存收费机井
 * @param thisPop
 * @param url
 */
function addBaseWaterChargeInfo(thisPop, url) {
	$(".pop-select").attr("disabled", false);
	var param = $("#baseWaterChargeForm").serialize();
	
	// 检查缴费机井必选项
	var ckWaterArea = checkWaterAreaID();
	if(ckWaterArea){
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				$("#deviceName").val("请输入机井名称");
				$("#deviceCode").val("请输入机井编码");
				var treeObj = $.fn.zTree.getZTreeObj("ztree");
				var sNodes = treeObj.getSelectedNodes();
				baseWaterChargeList(sNodes[0]);
			}
		});
	}
}

/**
 * 批量删除
 */
function delBaseWaterCharge(id) {
	var ids = [];
    if (id) {
    	ids.push(id);
    }else{
    	var selectRow = $("#baseWaterChargeContent tbody input[type='checkbox']:checked");
	    for (var i = 0; i < selectRow.length; i++) {
	    	ids.push(selectRow[i].value);
	    }
    }
    if (ids.length == 0) {
    	$.Popup.create({ title: "提示", content: "请选择一条信息"});
  	  	return false;
    }
    var showConfirm = true;
    var confirmMsg = {
			title: "提示",
			content: "确定要删除选中的机井缴费信息?",
			tbar: [{
				text: "确定",
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					fnAjaxRequest(
						"baseWaterCharge/delBaseWaterCharge.do",
						{ids:ids.toString()},
						"json",
						"POST",
						function(data){
							fnDSuccess(data,thisPop,showConfirm);
         			    	if(data.success){
         			    		$("#deviceName").val("请输入机井名称");
         			    		var treeObj = $.fn.zTree.getZTreeObj("ztree");
         			    	  	var sNodes = treeObj.getSelectedNodes();
         			    	  	baseWaterChargeList(sNodes[0]);
          					}else{
          						$.Popup.close(thisPop);
          					}
						}
					);
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
 * 表单验证
 * @returns boolean
 */
function validRoleForm(){
	return $("#baseDistWaterPlanForm").validate({
        rules: {
        	distWater: {
        		required: true,
        	},
        	distPrice:  {
        		required: true,
        	}
  		},
        messages: {
        	distWater: {
        		required: "必填项",
        	},
        	distPrice:  {
        		required: "正整数",
        	}
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

/**
 * 获取水管区域子区域级联操作
 * 	1、通过最高一级选择，如果该选择的有子节点（子区域），则将在旁边重新生成一个子区域下拉框集合
 * 	2、当选择了--请选择水管区域--选项，则将该下拉框后面的子下拉框全部清除
 */
function getSubWaterAreaId(_this) {
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
			} else {
				if($(_this).attr("name")!="baseDeviceId") {
					alert("该水管区域区域下无机井设备信息,请重新选择水管区域");
					$(_this).val("");
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

function baseWaterChargeOperator(id) {
	var contentMsg = {
			title: "基本水费收费",   
			url: "baseWaterCharge/baseWaterChargeOperatorPage.do",
			width:"400",
			paraData:{id:id},
			requestMethod: 'ajax',
			tbar: [{
				text: "确定",
				clsName: "homebg popup-confirm",
				handler: function (thisPop) {
					var addUrl = "baseWaterCharge/baseWaterChargeOperator.do";
					addBaseWaterChargeOperatorInfo(thisPop,addUrl);
				}
			}]
		};
		$.Popup.create(contentMsg);
}

function addBaseWaterChargeOperatorInfo(thisPop,url) {
	showMark();
	var param = $("#baseWaterChargeForm").serialize();
	fnAjaxRequest(
		url,
		param,
		"json",
		"POST",
		function(data){
	    	fnDSuccess(data,thisPop);
	    	if(data.success){
	    		$("#deviceName").val("请输入机井名称");
	    		var treeObj = $.fn.zTree.getZTreeObj("ztree");
	    	  	var sNodes = treeObj.getSelectedNodes();
	    	  	baseWaterChargeList(sNodes[0]);
			} 
		}
	);
  	hideMark();
}

/**
 * 缴费机井必选项
 */
function checkWaterAreaID(){
	var waterAreaID = $('#waterAreaId option:selected').text();
	var flag = true;
	if(waterAreaID == "--请选择水管区域--"){ 
       	$("#waterAreaId").next('span').remove();
		$("#waterAreaId").after("<span id='msgWaterAreaInfo'></span>");
    	$("#msgWaterAreaInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>必选项</font>");  
        flag = false; 
    }else{
    	$("#waterAreaId").next('span').remove();
    }
    return flag;
}

/**
 * 基本水费信息报表导出
 */
function baseWaterPriceReport(){
	// 条件查询参数
	var deviceName = $("#deviceName").val();
	if(null == deviceName || "" == deviceName || '请输入机井名称' == deviceName) {
		deviceName = "";
	}
	var deviceCode = $("#deviceCode").val();
	if(null == deviceCode || "" == deviceCode || '请输入机井编码' == deviceCode) {
		deviceCode = "";
	}
	
	// 水管区域方式
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var waterAreaId = sNodes[0].id;
	window.location.href="baseWaterCharge/exportBasePriceInfo.do?waterAreaId="+waterAreaId+"&deviceName="+deviceName+"&deviceCode="+deviceCode;
}