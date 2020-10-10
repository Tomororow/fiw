<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>水卡管理首页</title>
<script type="text/javascript" src="${ctx}/content/skin/js/waterCardReport.js"></script>
</head>

<body>
	<!-- 二级菜单头部  -->
	<div class="header">
		<jsp:include page="/twoHeader.jsp"></jsp:include> 
	</div>
	
	<!-- 左侧区域ztree -->
	<div class="left">
		<div class="tree">
			<ul id="ztree" class="ztree"></ul>
		</div>
	</div>

	<!-- 左侧区域ztree隐藏/显示 -->
	<div class="leftnav">
		<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
	</div>
	
	<!-- 内容显示div -->
	<div id="twoContain"></div>
</body>

<script type="text/javascript">
	$(function () {
	    $("#two_nav li").click(function () {
	        var m = $(this), s = m.siblings();
	        m.addClass("sell");
	        s.removeClass("sell");
	    }); 
	    //默认载入第一个菜单
	    if(${fn:length(menuList)>1 }){
	    	$("#two_nav li").eq(1).click();
	    }else{
	    	$("#two_nav li:first").click();
	    } 
	});

    $(function () {
        $(".leftnav").click(function () {
            if ($(".left").css("display") == "none") {
                $(".leftnav").css("left", "237px"); $(".leftnav img").attr("src", "content/skin/css/images/botton-closeLeft.gif");
                $(".right").css("left", "275px");
            } else {
                $(".leftnav").css("left", "0px"); $(".leftnav img").attr("src", "content/skin/css/images/botton-closeRight.gif");
                $(".right").css("left", "20px");
            }
            $(".left").toggle();
            $(".left-bottom").toggle();
        });
    });
</script>

</html>