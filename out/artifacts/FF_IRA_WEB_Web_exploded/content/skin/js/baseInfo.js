/**********************************区域功能脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	$(".leftTree").height($(".left").outerHeight()- 10);
	$("#checkAll").live("click",function(){
		var check = $(this).attr("checked");
		var ckList = $("#baseInfoDiv tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	});
});

function getSelectStyleInfo(dom) {
	$(dom).parent().parent().find("a").removeAttr("style");
	$(dom).css("backgroundColor","#66c3ec");
	$(dom).css("height","31px");
	$(dom).css("line-height","31px");
	$(dom).css("border-top","1px solid #83ceed");
	$(dom).css("border-bottom","1px solid #83ceed");
	$(dom).css("border-right","3px solid #f8881c");
	$(dom).css("border-left","3px solid #66c3ec");
	$(dom).css("text-align","center");
}

/**
 * 分页查询
 * @param page
 */
function changePage(page,sbh){
	var params = {
		pageNo:page
	};
	loadInfo(null,params,sbh);
}

/**
 * 点击区域，加载右侧参数设置列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadInfo(dom,params,sbh){
	debugger
	getSelectStyleInfo(dom);
	var url;
	if(sbh == "operator"){
		url = "/baseInfo/operator.do";
	}else if(sbh == "pumpMaterial"){
		url = "/baseInfo/pumpMaterial.do";
	}else if(sbh == "useWaterType"){
		url = "/baseInfo/waterType.do";
	}else if(sbh == "applyStatus"){
		url = "/baseInfo/applyStatus.do";
	}else if(sbh == "wellUse"){
		url = "/baseInfo/wellUse.do";
	}else if(sbh == "irrigationMode"){
		url = "/baseInfo/irrigationMode.do";
	}else if(sbh == "irrigationAreaType"){
		url = "/baseInfo/irrigationAreaType.do";
	}else if(sbh == "landformType"){
		url = "/baseInfo/landformType.do";
	}else if(sbh == "measureType"){
		url = "/baseInfo/measureType.do";
	}else if(sbh == "deviceModel"){
		url = "/baseInfo/deviceModel.do";
	}else if(sbh == "serviceMan"){
		url = "/baseInfo/serviceMan.do";
	}else{
		url = $(dom).data('src');
	}
	var flag = false;
	showMark();
	$.get(url,params,function(data){
		$("#baseInfoContent").empty().html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}

/**
 * 通用新增基础信息
 * @param dom
 */
function addAll(dom){
	var url = $(dom).data('src');
	var sbh = $(dom).data('fl');
	addBaseInfoPage(url,sbh);
}

/**
 * 新增基础信息页面
 * @param url
 * @param sbh
 */
function addBaseInfoPage(url,sbh){
	var contentMsg = {
		title: "新增基础信息",   
		url: url,
		width:"600",
		requestMethod: 'ajax',
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				var addUrl = "";
				if(sbh == "operator"){
					addUrl = "baseInfoAdd/addOperator.do";
				}else if(sbh == "pumpMaterial"){
					addUrl = "baseInfoAdd/addPumpMaterial.do";
				}else if(sbh == "useWaterType"){
					addUrl = "baseInfoAdd/addWaterType.do";
				}else if(sbh == "applyStatus"){
					addUrl = "baseInfoAdd/addApplyStatus.do";
				}else if(sbh == "wellUse"){
					addUrl = "baseInfoAdd/addWellUse.do";
				}else if(sbh == "irrigationMode"){
            		addUrl = "baseInfoAdd/addIrrigationMode.do";
            	}else if(sbh == "irrigationAreaType"){
            		addUrl = "baseInfoAdd/addIrrigationAreaType.do";
            	}else if(sbh == "landformType"){
            		addUrl = "baseInfoAdd/addLandformType.do";
            	}else if(sbh == "measureType"){
            		addUrl = "baseInfoAdd/addMeasureType.do";
            	}else if(sbh == "deviceModel"){
            		addUrl = "baseInfoAdd/addDeviceModel.do";
            	}else if(sbh == "serviceMan"){
            		addUrl = "baseInfoAdd/addServiceMan.do";
            	}
				addEndEditBaseInfo(thisPop,addUrl,sbh,'add');
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 通用编辑基础信息
 * @param dom
 */
function editAll(dom){
	var id = $(dom).data('id');
	var url = $(dom).data('src');
	var sbh = $(dom).data('fl');
	editBaseInfoPage(url,id,sbh);
}

/**
 * 编辑基础信息页面
 * @param url
 * @param id
 * @param sbh
 */
function editBaseInfoPage(url,id,sbh){
	var contentMsg = {
       title: "修改基础信息",   
       url:url,
       width:"600",
       paraData:{id:id},
       requestMethod: 'ajax',
       tbar: [{
           text: "确定",
           clsName: "homebg popup-confirm",
           handler: function (thisPop) {
        	   var addUrl = "";
        	   if(sbh == "operator"){
					addUrl = "baseInfoEdit/editOperator.do";
        	   }else if(sbh == "pumpMaterial"){
        		   addUrl = "baseInfoEdit/editPumpMaterial.do";
        	   }else if(sbh == "useWaterType"){
        		   addUrl = "baseInfoEdit/editWaterType.do";
        	   }else if(sbh == "applyStatus"){
        		   addUrl = "baseInfoEdit/editApplyStatus.do";
        	   }else if(sbh == "wellUse"){
        		   addUrl = "baseInfoEdit/editWellUse.do";
        	   }else if(sbh == "irrigationMode"){
        		   addUrl = "baseInfoEdit/editIrrigationMode.do";
        	   }else if(sbh == "irrigationAreaType"){
        		   addUrl = "baseInfoEdit/editIrrigationAreaType.do";
        	   }else if(sbh == "landformType"){
        		   addUrl = "baseInfoEdit/editLandformType.do";
        	   }else if(sbh == "measureType"){
        		   addUrl = "baseInfoEdit/editMeasureType.do";
        	   }else if(sbh == "deviceModel"){
        		   addUrl = "baseInfoEdit/editDeviceModel.do";
        	   }else if(sbh == "serviceMan"){
           		   addUrl = "baseInfoEdit/editServiceMan.do";
           	}
        	   addEndEditBaseInfo(thisPop,addUrl,sbh,'edit');
           }
       }]
   };
   $.Popup.create(contentMsg);
}

/**
 * 新增与编辑 保存公用
 * @param thisPop
 * @param addUrl
 * @param sbh
 */
var RegTel = /^1[345789]\d{9}$/;
function addEndEditBaseInfo(thisPop,addUrl,sbh,sign){
	var param = $("#baseInfoForm").serialize();
	if(sbh=="serviceMan"){
		debugger
		var waterAreaId = sign=="add"?waterAreaCodes:$("#waterAreaId").val();
		var serviceMan = $("#serviceMan").val();
		var phone = $("#phone").val();
		var duty = $("#duty").val();
		if(waterAreaId==null||waterAreaId==""){
			alert("水管区域不能为空...");
			return;
		}
		if(serviceMan==null||serviceMan==""){
			alert("人员姓名不能为空...");
			return;
		}
		if(phone==null||phone==""){
			alert("联系电话不能为空...");
			return;
		}
		if(!RegTel.test(phone)){
			alert("电话号码格式不正确...");
			return;
		}
		if(duty==null||duty==""){
			alert("人员职务不能为空...");
			return;
		}
		param = param+"&waterAreaId="+waterAreaId;
	}
	
	// 将提示语句初始置为空
	$("#sysMsg").empty();
	// 如果输入为空，则给出不能为空的提示
	if(null==$("input[type='text']").val() || ""==$("input[type='text']").val()) {
		$("#sysMsg").html("<font style='color:red;line-height:32px;'>不能为空</font>");
	} else {
		fnAjaxRequest(addUrl, param, "json", "POST",
			function(data){
  				// 操作提示
				fnDSuccess(data,thisPop);
		    	if(data.success){
		    		loadInfo(null,true,sbh);
				} 
			}
  		);
	}
}

/**
 * 修改所属区域
 */
function updateArea(){
	$("#waterDiv").css("display","block");
	$("#waterSet").css("display","none");
	$("#updateVtnu").css("display","none");
}

/**
 * 基础信息通用删除
 * @param dom
 */
function delAll(dom){
	var id = $(dom).data('id');
	var distType = $(dom).data('fl');
	var delIds = [];
	if (id) {
		delIds.push(id);
    }else{
    	var selectRow = $("#baseInfoDiv tbody input[type='checkbox']:checked");
    	for (var i = 0; i < selectRow.length; i++) {
    		delIds.push(selectRow[i].value);
    	}
    }
	if (delIds.length == 0) {
		$.Popup.create({ title: "提示", content: "请选择要删除的数据"});
		return;
	}
	var confirmMsg = {
		title: "提示",
		content: "确定要删除选中的数据?",
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				fnAjaxRequest(
					"baseInfo/delBaseInfo.do",
					{items: delIds.toString(),distType:distType},
					"json",
					"POST",
					function (data) {
						fnDSuccess(data,thisPop);
						if(data.success){
							loadInfo(null,true,distType);
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
 * 获取水管区域子区域级联操作
 * 	1、通过最高一级选择，如果该选择的有子节点（子区域），则将在旁边重新生成一个子区域下拉框集合
 * 	2、当选择了--请选择水管区域--选项，则将该下拉框后面的子下拉框全部清除
 */
var waterAreaCodes = "";
function getSubWaterAreaId(_this) {
	debugger
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
				var htmltv = "<select class='pop-select' style='display: inline-block;width:90px;' name='waterAreaId' onchange='getSubWaterAreaId(this)'>";
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
 * 根据已选择的水管区域，自动获取水管码
 * 	1、所属水管区域选择完成后，点击确定按钮，将获取到水管码
 * 	2、如果重新选择了水管区域，再次点击确定，将会把之前选择好的水管码给重新赋值
 */
function getWaterAreaCode(_this) {
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
						waterAreaCodes = "";
						// 关闭弹窗
						$.Popup.close(thisObj);
						// 获取到所有的水管区域编码
						$("select[name='waterAreaId'] option:selected").each(function(){
							debugger
							// 如果有选择为空的下拉框选项，则点击确定按钮后，将该为空的下拉框整个移出
							if(null==$(this).val() || ""==$(this).val()) {
								$(this).parent().remove();
							}else{
								waterAreaCodes += $(this).val() + ",";
							}
						});
						// 去掉拼接后最后一个逗号
						waterAreaCodes = waterAreaCodes.substring(0, waterAreaCodes.length-1);
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
		waterAreaCodes = "";
		alert("请选择水管区域");
	}
}