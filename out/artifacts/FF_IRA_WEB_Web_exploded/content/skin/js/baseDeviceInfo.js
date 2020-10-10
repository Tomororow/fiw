/**********************************区域功能脚本***************************************************/
var iden_flag;

/**
 *  当中间按钮点击时
 *	 1、左侧树形菜单如果显示为none，则让左侧树形菜单栏显示
 *	 2、左侧树形菜单栏如果显示为block，则让左侧树形菜单栏不显示，并将图标改为往右的箭头
 */
$(function () {
    $(".leftnav").click(function () {
        if ($(".left").css("display") == "none") {
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
    
    // 根据用户分配水管区域或者行政区域  获取不同树形菜单
	$.ajax({
		type:"get",
		async:false,
		cache:false,
		url:"monitor/areaChoose.do",
		success:function(data){
			if(data == "success"){
				iden_flag = data;
				// 只配置水管区域   则按照水管区域查询平台机井信息
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", baseDeviceInfoList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", baseDeviceInfoList);
			}
		}
	});
});

/**
 * 加载区域树菜单和机井信息列表页
 * @param node
 */
function baseDeviceInfoList(node){
	var params = {
		id:node.id
	};
	loadBaseDeviceInfoList(params,false);
}

/**
 * 初始化页面
 */
$(function(){
	$(".leftTree").height($(".left").outerHeight() - 10);
	$("#checkAll").live("click",function(){
		var check = $(this).attr("checked");
		var ckList = $("#baseDeviceInfoDiv tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	});
});

/**
 * 点击区域，加载右侧机井设备列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadBaseDeviceInfoList(params, flag){
	showMark();
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.get("baseDeviceInfo/listByWaterArea.do",params,function(data){
			$("#baseDeviceInfoContent").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		});
	}else if(iden_flag == "failed"){
		// 行政区域
		$.get("baseDeviceInfo/list.do",params,function(data){
			$("#baseDeviceInfoContent").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		});
	}
}

/**
 * 修改所属区域
 */
function updateArea(){
	$("#waterDiv").css("display","block");
	$("#waterSet").css("display","none");
	$("#updateVtnu").css("display","none");
	$("#areaDiv").css("display","block");
	$("#areaSet").css("display","none");
	
}

/**
 * 修改图片信息
 */
function editPicture(id) {
	var contentMsg = {
		title : "查看或修改图片信息",
		url : "baseDeviceInfo/updateImgPage.do",
		width : "530",
		height : "210",
		paraData:{id:id},
		requestMethod: 'ajax'
	};
	$.Popup.create(contentMsg);
}
/**
 * 根据条件查询设备信息
 */
function getDeviceInfoByCondition(){
	showMark();
	// 获取按条件查询的机井编码及名称
	var deviceCode = $("#deviceCode").val();
	if(deviceCode=='请输入机井编码'){
		deviceCode = '';
	}
	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称'){
		deviceName = '';
	}
	// 获取选中区域节点的id
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	
  	// 水管区域  机井列表显示
  	if(iden_flag == "success"){
  		$.ajax({
  			url : "baseDeviceInfo/listByWaterArea.do",
  			data : {id:id, deviceCode:deviceCode, deviceName:deviceName},
  			success : function(data){
  				$("#baseDeviceInfoContent").html(data);
  				hideMark();
  		    },
  		    error: function (xhr, error, thrown) {
  		    	hideMark();
  	   	    }
  		});
  	}else{
  		$.ajax({
  			url : "baseDeviceInfo/list.do",
  			data : {id:id, deviceCode:deviceCode, deviceName:deviceName},
  			success : function(data){
  				$("#baseDeviceInfoContent").html(data);
  				hideMark();
  		    },
  		    error: function (xhr, error, thrown) {
  		    	hideMark();
  	   	    }
  		});
  	}
}

/**
 * 选择水井用途时对其它方式设定的参数进行disable
 * @param obj
 */
function handleChange(obj){
	debugger
	switch(obj){
	case '生活':
		$("#sJSupplyWaterPeople").attr("disabled", false);//实际供水人口
		$("#sJArea").attr("disabled", true);//实际灌溉面积
		$("#kZArea").attr("disabled", true);//控制面积
		$("#industryApprovedWater").attr("disabled", true);//年核定水量
		break;
	case '工业':
		$("#industryApprovedWater").attr("disabled", false);//年核定水量
		$("#sJArea").attr("disabled", true);//实际灌溉面积
		$("#kZArea").attr("disabled", true);//控制面积
		$("#sJSupplyWaterPeople").attr("disabled", true);//实际供水人口
		break;
	case '灌溉':
		$("#sJArea").attr("disabled", false);//实际灌溉面积
		$("#kZArea").attr("disabled", false);//控制面积
		$("#industryApprovedWater").attr("disabled", true);//年核定水量
		$("#sJSupplyWaterPeople").attr("disabled", true);//实际供水人口
		break;
	case '绿化':
		$("#btn").attr("disabled", true); 
		break;
	}
}

/**
 * 新增机井设备
 */
function addBaseDeviceInfo(){
	debugger
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var name = "";
  	var id = "";
  	if(sNodes==""){
  		 $.Popup.create({ title: "提示", content: "请选择行政区域"});
  		 return false;
  	}else{
  		id = sNodes[0].id;
  		name = sNodes[0].name;
  		areaLevel = sNodes[0].areaLevel;
  	}
  	var contentMsg = {
		id:"addBaseDeviceInfo",
        title: "新增机井设备-所属区域【"+name+"】",   
        url:"baseDeviceInfo/addPage.do",
        width:"1150",
        paraData:{areaId:id},
        requestMethod: 'ajax',
        tbar: [{
        	text: "确定",
            clsName: "homebg popup-confirm",
            handler: function (thisPop) {
            	addAndEditDevice(thisPop,"baseDeviceInfo/addBaseDeviceInfo.do");
            }
        }]
  	};
  	$.Popup.create(contentMsg);
 
}

/**
 * 编辑机井设备
 */
function editBaseDeviceInfo(id){
	var contentMsg = {
	   id:"editBaseDeviceInfo",//为了调用共同的方法
       title: "修改机井设备信息",   
       url:"baseDeviceInfo/editPage.do",
       width:"1150",
       paraData:{id:id},
       requestMethod: 'ajax',
       tbar: [{
           text: "确定",
           clsName: "homebg popup-confirm",
           handler: function (thisPop) {
        	   addAndEditDevice(thisPop,"baseDeviceInfo/editBaseDeviceInfo.do");
           }
       }]
   };
   $.Popup.create(contentMsg);
}

/**
 * 保存/编辑公用方法
 */
function addAndEditDevice(thisPop,url){
	var installTimeStr = $("#installTimeStr").val();
	var makeTimeStr =  $("#makeTimeStr").val();
	var param = $("#baseDeviceInfoForm").serialize();
	param = param+"&installTimeStr="+installTimeStr+"&makeTimeStr="+makeTimeStr;
	var showConfirm = true;
	if(validateBDIForm()){
		if($("#operateType").val()=="edit") {
			var ins = $("#sitType").find("input");
			var checkTy = false;
			for(var i=0;i<ins.length;i++){
				if(ins[i].checked==true){
					checkTy = true;
				}
			}
			if(checkTy == false){
				alert("请选择机井类型...");
			}
			$(".pop-select").attr("disabled", false);
			fnAjaxRequest(url, param, "json", "POST", function(data){
			    	fnDSuccess(data,thisPop);
			    	if(data.success){
			    		refreshAreaTreeAndDeviceList();
			    		$.Popup.close(thisPop);
						$.Popup.create({title: "温馨提示", content: "修改成功"});
					}else{
						$.Popup.close(thisPop);
					}
				}
	  		);
		} else {
			debugger
			var siteType = $("#siteType").val();
			if(siteType=="--请选择--"){
				alert("请选择站点类型...");
			}else{
			$("#baseDeviceInfoForm").ajaxSubmit({
				type: 'post',
				url: url,
				success: function(data) {
					fnDSuccess(data,thisPop,showConfirm);
					if(data.success==undefined){
						refreshAreaTreeAndDeviceList();
						$.Popup.close(thisPop);
						$.Popup.create({title: "温馨提示", content: "添加成功"});
					}else{
						$.Popup.close(thisPop);
					}
				}
			});
			}
		}
	}
}

/**
 * 删除机井设备信息
 */
function delBaseDeviceInfo(id){
	var ids = [];
    if (id) {
    	ids.push(id);
    }else{
    	var selectRow = $("#baseDeviceInfoDiv tbody input[type='checkbox']:checked");
	    for (var i = 0; i < selectRow.length; i++) {
	    	ids.push(selectRow[i].value);
	    }
    }
    if (ids.length == 0) {
    	$.Popup.create({ title: "提示", content: "请选择机井设备"});
  	  	return false;
    }
    var showConfirm = true;
    var confirmMsg = {
		title: "提示",
		content: "确定要删除选中的机井设备?",
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				fnAjaxRequest(
					"baseDeviceInfo/delBaseDeviceInfo.do", {ids:ids.toString()}, "json", "POST", function(data){
						fnDSuccess(data,thisPop,showConfirm);
     			    	if(data.success){
     			    		refreshAreaTreeAndDeviceList();
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
 * 刷新区域树和机井设备列表
 * 添加 删除 编辑后刷新页面
 */
function refreshAreaTreeAndDeviceList(){
	// 添加 删除 编辑后刷新页面 水管区域方式
	if(iden_flag == "success"){
		ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", baseDeviceInfoList);
	}else{
		ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", baseDeviceInfoList);
	}
}

/**
 * 属性校验
 * @returns
 */
function validateBDIForm(){ 
	return $("#baseDeviceInfoForm").validate({
		rules:{
			deviceCode : {
			    required: true,
			    maxlength: 32
			},
			deviceName : {
				required: true,
				maxlength: 128
			},
			deviceNo : {
				maxlength: 32,
				digits: true
			},
			cezhanID : {
				maxlength: 50,
				specialCharValidate: true
			},
			simCard : {
				required: true,
				number: true
			},
			latitude: {
				required: true,
				number: true
			},
			longitude: {
				required: true,
				number: true
			},
			pipeDiameter: {
				number: true
			},
			wellDiameter: {
				number: true
			},
			wellDepth: {
				number: true
			},
			groundWaterDepth: {
				number: true
			},
			ratedHead: {
				number: true
			},
			ratedFlow: {
				number: true
			},
			ratedPower: {
				number: true
			},
			kZArea: {
				number: true
			},
			sJArea: {
				number: true
			},
			sJSupplyWaterPeople: {
				digits: true
			},
			yearWaterSum: {
				number: true
			},
			industryApprovedWater: {
				number: true
			},
			industryProductionCapacity: {
				number: true
			},
			industryArea: {
				number: true
			},
			productionCapacity: {
				number: true
			},
			miningArea: {
				number: true
			},
			meterCaliber: {
				number: true
			},
			meterSerialNo: {
				number: true
			}
			/*photoBefores: {
				required: true
			},
			不需要必填*/
			/*photoAfters: {
				required: true
			}*/
		},
		messages:{
			deviceCode : {
				required: "必填项",
				maxlength:"不超过32个字"
            },
            deviceName : {
            	required: "必填项",
				maxlength:"不超过128个字"
			},
			deviceNo : {
				maxlength:"不超过32个字",
				digits: "整数"
			},
			cezhanID : {
				maxlength:"不超过50个字",
				specialCharValidate: "不能包含特殊字符"
			},
			simCard : {
				required: "必填项",
				number: "数字"
			},
			latitude: {
				required: "必填项",
				number: "数字"
			},
			longitude: {
				required: "必填项",
				number: "数字"
			},
			pipeDiameter: {
				number: "数字"
			},
			wellDiameter: {
				number: "数字"
			},
			wellDepth: {
				number: "数字"
			},
			groundWaterDepth: {
				number: "数字"
			},
			ratedHead: {
				number: "数字"
			},
			ratedFlow: {
				number: "数字"
			},
			ratedPower: {
				number: "数字"
			},
			kZArea: {
				number: "数字"
			},
			sJArea: {
				number: "数字"
			},
			sJSupplyWaterPeople: {
				digits: "整数"
			},
			yearWaterSum: {
				number: "数字"
			},
			industryApprovedWater: {
				number: "数字"
			},
			industryProductionCapacity: {
				number: "数字"
			},
			industryArea: {
				number: "数字"
			},
			productionCapacity: {
				number: "数字"
			},
			miningArea: {
				number: "数字"
			},
			meterCaliber: {
				number: "数字"
			},
			meterSerialNo: {
				number: "数字"
			}
			/*photoBefores: {
				required: "必填项"
			},
			photoAfters: {
				required: "必填项"
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

/***
 * ajax 验证机井编码
 */
$.validator.addMethod("deviceCodeExist",function(value,element){
	var deviceCode = value.trim();
	var result= false;
	// 设置同步
	$.ajaxSetup({
		async: false
	});
	$.post("baseDeviceInfo/checkDeviceCodeExist.do",{
		deviceCode : deviceCode
	},function(data){
		if(data.success){
			result = false;
		}else{
			result = true;
		} 
	},"json");
	// 恢复异步
	$.ajaxSetup({
		async: true
	});
	return result;
},"机井编码，已经存在了!");

/**
 * 分页查询(不同区域)
 * @param page
 */
function changePage(page){
	showMark();
	// 获取按条件查询的机井编码及名称
	var deviceCode = $("#deviceCode").val();
	if(deviceCode=='请输入机井编码'){
		deviceCode = "";
	}
	var deviceName = $("#deviceName").val();
	if(deviceName=='请输入机井名称'){
		deviceName = "";
	}
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	
  	// 水管区域方式 机井分页显示
  	if(iden_flag == "success"){
  		$.ajax({
  			url : "baseDeviceInfo/listByWaterArea.do",
  			data : {id:id, deviceCode:deviceCode, deviceName:deviceName, pageNo:page},
  			success : function(data){
  				$("#baseDeviceInfoContent").html(data);
  				hideMark();
  		    },
  		    error: function (xhr, error, thrown) {
  		    	hideMark();
  	   	    }
  		});
  	}else{
  		$.ajax({
  			url : "baseDeviceInfo/list.do",
  			data : {id:id, deviceCode:deviceCode, deviceName:deviceName, pageNo:page},
  			success : function(data){
  				$("#baseDeviceInfoContent").html(data);
  				hideMark();
  		    },
  		    error: function (xhr, error, thrown) {
  		    	hideMark();
  	   	    }
  		});
  	}
}

/**
 * 获取水管区域子区域级联操作
 * 	1、通过最高一级选择，如果该选择的有子节点（子区域），则将在旁边重新生成一个子区域下拉框集合
 * 	2、当选择了--请选择水管区域--选项，则将该下拉框后面的子下拉框全部清除
 */
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
function getWaterAreaCode(sign) {
	debugger
	var waterAreaCodes = "";
	// 如果选择的水管区域不为空
	if(null!=$("select[name='waterAreaId'] option:selected").val() && ""!=$("select[name='waterAreaId'] option:selected").val()) {
		// 获取到所有的水管区域编码
		$("select[name='waterAreaId'] option:selected").each(function(){
			// 如果有选择为空的下拉框选项，则点击确定按钮后，将该为空的下拉框整个移出
			if(null==$(this).val() || ""==$(this).val()) {
				$(this).parent().remove();
			} else { // 否则将每个下拉框的key按照逗号拼接起来
				waterAreaCodes += $(this).val() + ",";
			}
		});
		// 去掉拼接后最后一个逗号
		waterAreaCodes = waterAreaCodes.substring(0, waterAreaCodes.length-1);
		$(".pop-select[name='waterAreaId']").attr("disabled", true);
		$(".pop-select[name='waterAreaId']").css("background-color","#cccccc");
		$.post("baseDeviceInfo/getWaterAreaCode.do",{
			waterAreaIds : waterAreaCodes
		},function(data){
			/**
			 * 1、判定拼接起来的水管码长度是否小于8，如果小于8，则将用0来拼接到水管码后面，补足8位数字
			 * 2、如果有8位，则直接将返回的data值赋值到水管码对应的标签中去
			 */
			if(sign==0){
				$("#deviceWaterAreaCode").val(data);
			}
			$("#waterAreaId").val($($(".pop-select[name='waterAreaId']")[$(".pop-select[name='waterAreaId']").length-1]).val());//TODO
			
			/**
			 * 根据水管码，查询数据库，然后获取到当前水管区域最大的井编号，然后自增1
			 * 	例如：当前水管区域编码下，有水管码为：61010101001（说明该水管区域下有一口井），
			 * 		则调用getMaxWaterAreaCode()方法，将自动生成第二口井，则编码为：61010101002
			 */ 
			getMaxWaterAreaCode($("#deviceWaterAreaCode").val(),sign);
		},"json");
		// 恢复异步
		$.ajaxSetup({
			async: true
		});
	} else { // 当没有选择水管区域时，则提醒用户要至少选择一级水管区域
		alert("请选择水管区域");
	}
}

/**
 * 生成同地区的最大水井码
 * 根据水管码，查询数据库，然后获取到当前水管区域最大的井编号，然后自增1
 * 	例如：当前水管区域编码下，有水管码为：61010101001（说明该水管区域下有一口井），
 * 		则调用getMaxWaterAreaCode()方法，将自动生成第二口井，则编码为：61010101002
 * @param code
 */
function getMaxWaterAreaCode(code,sign) {
	debugger
	$.post("baseDeviceInfo/getMaxWaterAreaCode.do",{
		deviceWaterAreaCode : code
	},function(data){
		$("#deviceWaterAreaCode").empty();
		if(sign==0){
			$("#deviceWaterAreaCode").val(data);
		}
		$("#waterAreaId").val($($(".pop-select[name='waterAreaId']")[$(".pop-select[name='waterAreaId']").length-1]).val());//TODO
		
	},"json");
	// 恢复异步
	$.ajaxSetup({
		async: true
	});
}

/**
 * 获取行政区域子区域级联操作
 * 	同上面水管区域操作
 * @param _this
 */
function getSubAreaId(_this) {
	var num = $(_this).next("#areaNum").val();
	if(""!=$(_this).val()) {
		var subNum = $(_this).next("#areaNum").val();
		$("#subArea_"+parseInt(subNum)).empty();
		$.post("baseDeviceInfo/getChildDeviceAreaInfo.do",{
			id : $(_this).val()
		},function(data){
			if(data!=null && data!=''){
				var htmltv = "<select class='pop-select' style='display: inline-block;width:90px;' name='areaId' onchange='getSubAreaId(this)'>";
				htmltv += "<option value=''>--请选择行政区域--</option>";
				for(var i=0;i<data.length;i++) {
					htmltv += "<option value='"+data[i].id+"'>"+data[i].areaName+"</option>";
				}
				htmltv += "</select>";
				htmltv += "<input type='hidden' id='areaNum' name='areaNum' value='"+(parseInt(num)+1)+"'/>";
				htmltv += "<span id='subArea_"+(parseInt(num)+1)+"'></span>";
				$("#subArea_"+parseInt(num)).append(htmltv);
			} else {
				alert("该行政区域再无子一级信息");
			}
		},"json");
		// 恢复异步
		$.ajaxSetup({
			async: true
		});
	} else {
		var subNum = $(_this).next("#areaNum").val();
		$("#subArea_"+parseInt(subNum)).empty();
	}
}

/**
 * 根据已选择的行政区域，自动获取行政码
 * 	同上面水管区域操作
 */
function getAreaCode(sign) {
	debugger
	var areaCodes = "";
	if(null!=$("select[name='areaId'] option:selected").val() && ""!=$("select[name='areaId'] option:selected").val()) {
		$("select[name='areaId'] option:selected").each(function(){
			if(null==$(this).val() || ""==$(this).val()) {
				$(this).parent().remove();
			} else {
				areaCodes += $(this).val() + ",";
			}
		});
		areaCodes = areaCodes.substring(0, areaCodes.length-1);
		$(".pop-select[name='areaId']").attr("disabled", true);
		$(".pop-select[name='areaId']").css("background-color","#cccccc");
		$.post("baseDeviceInfo/getAreaCode.do",{
			areaIds : areaCodes
		},function(data){
			if(sign==0){
				$("#deviceAreaCode").val(data);
			}
			$("#areaId").val($($(".pop-select[name='areaId']")[$(".pop-select[name='areaId']").length-1]).val());//TODO
			
			getMaxAreaCode($("#deviceAreaCode").val(),sign);
		},"json");
		// 恢复异步
		$.ajaxSetup({
			async: true
		});
	} else {
		alert("请选择行政区域");
	} 
}

/**
 * 生成同地区的最大水井码
 * 	同上面水管区域操作
 * @param code
 */
function getMaxAreaCode(code,sign) {
	$.post("baseDeviceInfo/getMaxAreaCode.do",{
		deviceAreaCode : code
	},function(data){
		$("#deviceAreaCode").empty();
		if(sign==0){
			$("#deviceAreaCode").val(data);
		}
		$("#areaId").val($($(".pop-select[name='areaId']")[$(".pop-select[name='areaId']").length-1]).val());//TODO
		
		
	},"json");
	// 恢复异步
	$.ajaxSetup({
		async: true
	});
}

/**
 * 机井编码 唯一性校验
 */
function uniqueDevCode() {
	$("#msgCodeInfo").empty();
	var Code = $("#deviceCode").val();
	var flag = true;
	if(null!=Code && ""!=Code) {
		if(!isNaN(Code)){
			$.post("baseDeviceInfo/uniqueDevCode.do", {
				deviceCode : Code
			}, function(data) {
				if (data=="success") {
					if(undefined==$("#msgCodeInfo").attr("id")) {
						$("#deviceCode").next('span').remove();//移除span
						$("#deviceCode").after("<span id='msgCodeInfo'></span>");
						$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以用</font>");
					} else {
						$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以用</font>");
					}
				} else {
					if(undefined==$("#msgCodeInfo").attr("id")) {
						$("#deviceCode").next('span').remove();//移除span
						$("#deviceCode").after("<span id='msgCodeInfo'></span>");
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
		// 判断是否为数字  分支
		}else{
			$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>数字</font>");
			flag = false;
		}	
	} else {
		$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>必填项</font>");
		flag = false;
	}
	return flag;
}

/**
 * 机井号牌 唯一性校验
 */
function uniquePlateCode() {
	$("#msgPlateInfo").empty();
	var devicePlate = $("#devicePlate").val();
	var flag = true;
	if(null!=devicePlate && ""!=devicePlate) {
		if(!isNaN(devicePlate)){
			$.post("baseDeviceInfo/uniquePlateCode.do", {
				devicePlate : devicePlate
			}, function(data) {
				if (data=="success") {
					if(undefined==$("#msgPlateInfo").attr("id")) {
						$("#devicePlate").next('span').remove();//移除span
						$("#devicePlate").after("<span id='msgPlateInfo'></span>");
						$("#msgPlateInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以用ss</font>");
					} else {
						$("#msgPlateInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以用</font>");
					}
				} else {
					if(undefined==$("#msgPlateInfo").attr("id")) {
						$("#devicePlate").next('span').remove();//移除span
						$("#devicePlate").after("<span id='msgPlateInfo'></span>");
						$("#msgPlateInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>已存在</font>");
						flag = false;
					} else {
						$("#msgPlateInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>已存在</font>");
						flag = false;
					}
				}
			}, "json");
			$.ajaxSetup({
				async : false
			});
		// 判断是否为数字  分支
		}else{
			$("#msgPlateInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>数字</font>");
			flag = true;
		}
	}
	return flag;
}

/**
 * 设备号 唯一性校验
 */
function uniqueDevno() {
	$("#msgNoInfo").empty();
	var deviceNo = $("#deviceNo").val();
	var flag = true;
	if(null!=deviceNo && ""!=deviceNo) {
		if(!isNaN(deviceNo)){
			$.post("baseDeviceInfo/uniqueDevno.do", {
				deviceNo : deviceNo
			}, function(data) {
				if (data=="success") {
					if(undefined==$("#msgNoInfo").attr("id")) {
						$("#deviceNo").next('span').remove();//移除span
						$("#deviceNo").after("<span id='msgNoInfo'></span>");
						$("#msgNoInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以用</font>");
					} else {
						$("#msgNoInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以用</font>");
					}
				} else {
					if(undefined==$("#msgNoInfo").attr("id")) {
						$("#deviceNo").next('span').remove();//移除span
						$("#deviceNo").after("<span id='msgNoInfo'></span>");
						$("#msgNoInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>已存在</font>");
						flag = false;
					} else {
						$("#msgNoInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>已存在</font>");
						flag = false;
					}
				}
			}, "json");
			$.ajaxSetup({
				async : false
			});
		// 判断是否为数字  分支
		}else{
			$("#msgNoInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>数字</font>");
			flag = false;
		}
	}
	return flag;
}

/**
 * DTU号 唯一性校验
 */
function uniqueDTUNo() {
	$("#msgDTUInfo").empty();
	var dtuNo = $("#cezhanID").val();
	var flag = true;
	if(null!=dtuNo && ""!=dtuNo) {
		if(!isNaN(dtuNo)){
			$.post("baseDeviceInfo/uniqueDTUNo.do", {
				dtuNo : dtuNo
			}, function(data) {
				if (data=="success") {
					if(undefined==$("#msgDTUInfo").attr("id")) {
						$("#cezhanID").next('span').remove();//移除span
						$("#cezhanID").after("<span id='msgDTUInfo'></span>");
						$("#msgDTUInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以用</font>");
					} else {
						$("#msgDTUInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以用</font>");
					}
				} else {
					if(undefined==$("#msgDTUInfo").attr("id")) {
						$("#cezhanID").next('span').remove();//移除span
						$("#cezhanID").after("<span id='msgDTUInfo'></span>");
						$("#msgDTUInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>已存在</font>");
						flag = false;
					} else {
						$("#msgDTUInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>已存在</font>");
						flag = false;
					}
				}
			}, "json");
			$.ajaxSetup({
				async : false
			});
		}else{
			$("#msgDTUInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>数字</font>");
			flag = false;
		}
	}
	return flag;
}