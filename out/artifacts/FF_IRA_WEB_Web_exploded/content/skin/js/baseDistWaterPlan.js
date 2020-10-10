/**********************************水权管理脚本***************************************************/
/**
 * 初始化页面
 */
var waterAreaId;
$(function(){
	$("#checkAll").live("click",function(){
		var check = $(this).attr("checked");
		var ckList = $("#baseDistWaterPlanList tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	});
});

/**
 * 分页查询
 * @param page
 */
function changePage(page,distType){
	var params = {
		pageNo:page
	};
	loadPlanList(params,null,distType);
}

/**
 * 重新加载
 */
function loadPlanList(params,flag,distType){
	var url = "";
	if(distType==2){
		url = "waterRightManage/industry.do";
	}else if(distType==3){
		url = "waterRightManage/life.do";
	}else{
		url = "waterRightManage/farming.do";
	}
	showMark();
	$.ajax({
		url : url,
		data : params,
		success :function(data){
			$("#twoContain").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		}
	});
}

/**
 * 新增配水计划弹窗
 */
function addBaseDistWaterPlan(url,biaoshi){
	var contentMsg = {
		title: "新增配水计划",
		url: url,
		width:"700",
		requestMethod: 'ajax',
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				var addUrl = "";
				var distType = 0;
				if(biaoshi=="1"){
					addUrl = "waterRightManage/addFarming.do";
					distType = 1;
				}else if(biaoshi=="2"){
					addUrl = "waterRightManage/addFarming.do";
					distType = 2;
				}else if(biaoshi=="3"){
					addUrl = "waterRightManage/addFarming.do";
					distType = 3;
				}else {
					addUrl = "waterRightManage/addFarming.do";
					distType = 4;
				}
				addDistWaterPlan(thisPop,addUrl,distType);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/*
 * 新增农业用水
 */
function addFarming(dom){
	var url = $(dom).data('src');
	var biaoshi = "1";
	addBaseDistWaterPlan(url,biaoshi);
}

/*
 * 新增工业用水
 */
function addIndustry(dom){
	var url = $(dom).data('src');
	var biaoshi = "2";
	addBaseDistWaterPlan(url,biaoshi);
}

/*
 * 新增生活用水
 */
function addLife(dom){
	var url = $(dom).data('src');
	var biaoshi = "3";
	addBaseDistWaterPlan(url,biaoshi);
}

/*
 * 新增绿化用水
 */
function addVirest(dom){
	var url = $(dom).data('src');
	var biaoshi = "4";
	addBaseDistWaterPlan(url,biaoshi);
}

/**
 * 删除
 */
function delPlan(dom){
	var id = $(dom).data('id');
	var distType = $(dom).data('fl');
	var delIds = [];
	if (id) {
		delIds.push(id);
    }else{
    	var selectRow = $("#baseDistWaterPlanList tbody input[type='checkbox']:checked");
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
					"waterRightManage/delPlan.do",
					{items: delIds.toString()},
					"json",
					"POST",
					function (data) {
						debugger
						fnDSuccess(data,thisPop);
						if(data.success){
							loadPlanList(null,true,distType);
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
 * 新增方法
 */
function addDistWaterPlan(thisPop,addUrl,distType){
	var isFlag = "";
	$("#waterAreaMsg").empty();
	$("#distYearMsg").empty();
	$(".pop-select").attr("disabled", false);
	$("#distMode").attr("disabled", false);
	if(null==$("select[name=waterAreaId] option:selected").val() || ""==$("select[name=waterAreaId] option:selected").val()) {
		$("#waterAreaMsg").html("<font style='color:red;line-height:32px;'>请选择机井信息</font>");
		isFlag += "false,";
	}
	if(null==$("#distYear").val() || ""==$("#distYear").val()) {
		$("#distYearMsg").html("<font style='color:red;line-height:32px;'>请选择配水年份</font>");
		isFlag += "false,";
	}
	var flag = true;
	var isRight = isFlag.split(",");
	if(""!=isFlag) {
		for(var i=0;i<isRight.length;i++) {
			if(isRight=="false");
			flag = false;
			break;
		}
	}
	var param = $("#baseDistWaterPlanForm").serialize();
  	if(validRoleForm() && flag){
  		$(".pop-select").attr("disabled", false);
  		fnAjaxRequest(
  			addUrl,
			param,
			"json",
			"POST",
			function(data){
		    	fnDSuccess(data,thisPop);
		    	if(data.success){
		    		loadPlanList(null,true,distType);
				} 
			}
  		);
  	} else {
  		if(null==$("select[name=waterAreaId] option:selected").val() || ""==$("select[name=waterAreaId] option:selected").val()) {
  			$("#waterAreaMsg").html("<font style='color:red;line-height:32px;'>请选择机井信息</font>");
  			$(".pop-select").attr("disabled", false);
  		} else {
  			$(".pop-select").attr("disabled", true);
  		}
  	}
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
        		number: true
        	},
        	distPrice:  {
        		required: true,
        		number: true
        	}
  		},
        messages: {
        	distWater: {
        		required: "必填项",
        		number: "数字"
        	},
        	distPrice:  {
        		required: "正整数",
        		number: "数字"
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
	debugger
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
				waterAreaId = $(_this).val();
				var htmltv = "<select class='pop-select' style='display: inline-block;width:100px;' name='waterAreaId' onchange='getSubWaterAreaId(this)'>";
				htmltv += "<option value=''>--请选择水管区域--</option>";
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
	debugger
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
				waterAreaId = $(_this).val();
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
			debugger
			$("#distYear")['0'].value = '';
			$("#distRound")['0'].value = '';
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

/**
 * 配水年份选择完后，筛选系统数据库
 * 	1、如果当前配水方式，年份不存在（第一次添加），则轮次默认选择1，不能为其他轮次
 * 	2、如果当前配水方式，年份存在（已经配过一次或多次），则轮次将读取数据库轮次+1
 */
function getDistRound(dist) {
	debugger
	var isAppendWater = $("#isAppendWater").val();
	var distType = $("#distType").val();
	$("#distYearMsg").empty();
	$.post("waterRightManage/getDistRoundInfo.do", {
		//distMode : $("#distMode").val(),
		distYear : $("#distYear").val(),
		distType : $("#distType").val(),
		waterAreaId:waterAreaId
	},function(data) {
		if(dist=='distYear') {
			debugger
			// 如果data不为空，则表示已经通过该配水方式、配水年份添加过配水轮次，则在轮次基础上增加1
			if(null!=data && ""!=data) {
				if(isAppendWater==0)
					$("#distRound").val(parseInt(data.distRound)+1);
				else
					$("#distRound").val(parseInt(data.distRound));
				$("#roundUserName").text(data.userName+",配制第【"+(parseInt(data.distRound)+1)+"】轮次");
				$("#distMode").val(data.distMode);
				$("#distMode").removeAttr("onchange");
				$("#distMode").attr("disabled", true);
				$("#distMode").css("background-color","#cccccc");
			} else {// 如果data为空，则表示没有配过该配水方式和配水年份，则直接默认为1
				$("#distMode").attr("disabled", false);
				$("#distMode").css("background-color","#FFFFFF");
				if(distType==1)
					//$("#roundUserName").text(data.userName+"配制第1轮次");
					$("#distMode").val("");
				$("#distRound").val(1);
			}
		}
	}, "json");
	// 恢复异步
	$.ajaxSetup({
		async: true
	});
}

/**
 * 点击配水类型，则弹出该配水信息下所有的机井设备信息
 * @param id
 */
function showDistWaterDeviceInfo(id, distType) {
	$.post("waterRightManage/showDistWaterDeviceInfo.do", {
		id : id,
		distType : distType
	},function(data) {
		var content2 = "<div style='float:right;border:2px solid gray;cursor: pointer;' onclick='closeTinybox(this)'>X</div><br/>";
		// 获取到配水轮次信息
		if(data[0].distRound==0) {
			content2 += "<div><center><div><h1>阶梯性水量追加表</h1></div></center>";
		} else {
			content2 += "<div><center><div><h1>"+data[0].distYear+"年度第"+data[0].distRound+"轮次配水计划表</h1></div></center>";
		}
		content2 += "<div>机井名称搜索：<input id='txtName' class='pop-input-common' maxlength='50' type='text' placeholder='请输入机井名称模糊搜索，例：沙井'/>&nbsp;<input type='button' value='查询' style='background-color:#00BCD4;color:#fff;border:none;width: 60px;height: 21px;line-height:5px;' onclick='getDeviceInfo()'></div><br/><br/>";
		content2 += "<div style='overflow-x: auto; overflow-y: auto; width:900px;height:500px;'><table class='list-table' id='deviceInfo' border='1' cellspacing='0' cellpadding='1' align='center'>";
		content2 += "<th style='width:426px;height:27px;'>机井名称</th><th style='width:151px;height:27px;'>配水年份</th><th style='width:152px;height:27px;'>配水轮次</th><th style='width:152px;height:27px;'>水量</th>";
		for(var i=0;i<data.length;i++) {
			content2 += "<tr align='center'>";
			content2 += "<td style='width:426px;height:27px;'>"+data[i].deviceName+"</td>";
			content2 += "<td style='width:151px;height:27px;'>"+data[i].distYear+"</td>";
			content2 += "<td style='width:152px;height:27px;'>"+data[i].distRound+"</td>";
			if(distType==2) {
				content2 += "<td style='width:152px;height:27px;'>"+parseInt(data[i].industryApprovedWater)*parseInt(data[i].sjArea)+"</td>";
			} else {
				content2 += "<td style='width:152px;height:27px;'>"+parseInt(data[i].distWater)*parseInt(data[i].sjArea)+"</td>";
			}
			content2 += "</tr>";
		}
		content2 += "</table></div></div>";
		// 将图片的信息以弹出框形式展现出来
		TINY.box.show(content2,0,0,0,1);
	}, "json");
	// 恢复异步
	$.ajaxSetup({
		async: true
	});
}

/**
 * 配水计划按区域查询
 */
function waterPlanSearch(){
	// 水管区域下拉框取值
	var waterAreaId = $("select[name='waterAreaId'] option:selected").val();
	
	var url = "";
	if(distType==2){
		url = "waterRightManage/industry.do";
	}else if(distType==3){
		url = "waterRightManage/life.do";
	}else{
		url = "waterRightManage/farming.do";
	}
	
	var params = {
		waterAreaId: waterAreaId,
		distType: distType
	}
	
	loadPlanList(params, null, distType);
}

function closeTinybox(_this) {
	TINY.box.hide();
}

function getDeviceInfo() {
	$("#deviceInfo tr:gt(0)").hide();
    var $d = $("#deviceInfo tr:gt(0)").filter(":contains('"+$.trim($("#txtName").val())+"')");
    $d.show();
}