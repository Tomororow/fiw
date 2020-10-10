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
			ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", distanceUpdateList);
			/*if(data == "success"){
				iden_flag = data;
				// 只配置水管区域   则按照水管区域查询平台机井信息
				ztreeFun($("#ztree"), "sysWaterArea/getAllArea.do?isSearchDevice=true&showOnLineDevice=false", distanceUpdateList);
			}else if(data == "failed"){
				iden_flag = data;
				// 水管区域和行政区域同时配置  则按照行政区域查询
				ztreeFun($("#ztree"), "sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false", distanceUpdateList);
			}*/
		}
	});
});

function distanceUpdateList(){
	
}
