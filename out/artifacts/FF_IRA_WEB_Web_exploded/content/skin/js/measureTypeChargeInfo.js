/**----------------------- 计量水费js脚本 -----------------------*/
var iden_flag;

/**
 *  当中间按钮点击时
 *	 1、左侧树形菜单如果显示为none，则让左侧树形菜单栏显示
 *	 2、左侧树形菜单栏如果显示为block，则让左侧树形菜单栏不显示，并将图标改为往右的箭头
 */
$(function() {
	$(".leftnav").click(function() {
		if ($(".left").css("display") == "none") {
			$(".leftnav").css("left", "237px");
			$(".leftnav img").attr("src","${ctx}/content/skin/css/images/botton-closeLeft.gif");
			$(".right_user").css("left", "275px");
		} else {
			$(".leftnav").css("left", "0px");
			$(".leftnav img").attr("src","${ctx}/content/skin/css/images/botton-closeRight.gif");
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
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", measureTypeChargeList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", measureTypeChargeList);
			}
		}
	});
});

/**
 * 计量水费列表
 */
function measureTypeChargeList() {
	var inYear = $("#inYear").val();
	var distType = $("select[name='distType'] option:selected").val();
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
	var param = {
		inYear : inYear,
		distType : distType,
		id : sNodes[0].id
	};
	loadMeasureTypeChargeList(param, false);
}

/**
 * 点击区域，加载右侧机井设备列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadMeasureTypeChargeList(params, flag){
	showMark();
	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.get("measureTypeCharge/measureTypeChargeListByWaterArea.do",params,function(data){
			$("#measureTypeChargeInfoContent").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		});
	}else if(iden_flag == "failed"){
		// 行政区域
		$.get("measureTypeCharge/measureTypeChargeList.do",params,function(data){
			$("#measureTypeChargeInfoContent").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		});
	}
}

/**
 * 计量水费分页查询
 * @param page
 */
function changeMeasureTypeChargePage(page) {
	showMark();
	// 获取页面参数  用于分页查询获取数量和详细数据信息
	var inYear = $("#inYear").val();
	var distType = $("select[name='distType'] option:selected").val();
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	
  	// 根据全局变量参数  区分根据行政区域还是水管区域
	if(iden_flag == "success"){
		// 水管区域
		$.ajax({
			url : "measureTypeCharge/measureTypeChargeListByWaterArea.do",
			data : {inYear:inYear, distType:distType, id:id, pageNo:page},
			success : function(data){
				$("#measureTypeChargeInfoContent").html(data);
				hideMark();
		    },
		    error: function (xhr, error, thrown) {
		    	hideMark();
	   	    }
		});
	}else if(iden_flag == "failed"){
		// 行政区域
		$.ajax({
			url : "measureTypeCharge/measureTypeChargeList.do",
			data : {inYear:inYear, distType:distType, id:id, pageNo:page},
			success : function(data){
				$("#measureTypeChargeInfoContent").html(data);
				hideMark();
		    },
		    error: function (xhr, error, thrown) {
		    	hideMark();
	   	    }
		});
	}
}