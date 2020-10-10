<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>实时监测首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/monitor.js"></script>
</head>

<body>
<input type="hidden" value="${INTELANALYSISCOUNT}" id="intelAnalysisCount" />
<!--二级头部  -->
<div class="header">
	<jsp:include page="/twoHeader.jsp"></jsp:include>
</div>
    
<!-- ztree -->
<div class="left">
  <div class="tree"><ul id="ztree" class="ztree"></ul></div>
</div>

<div class="leftnav">
	<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
</div>

<!--内容  -->
<div id="twoContain"></div>
	
</body>
</html>
<script type="text/javascript">
	$(function () {
		$(".leftnav").click(function() {
			if ($(".left").css("display") == "none") {
				$(".leftnav").css("left", "237px");
				$(".leftnav img").attr("src", "content/skin/css/images/botton-closeLeft.gif");
				$(".right_user").css("left", "275px");
			} else {
				$(".leftnav").css("left", "0px");
				$(".leftnav img").attr("src", "content/skin/css/images/botton-closeRight.gif");
				$(".right_user").css("left", "20px");
			}
			$(".left").toggle();
			$(".left-bottom").toggle();
		});
		
		
	    $("#two_nav  li").click(function () {
	        var m = $(this), s = m.siblings();
	        m.addClass("sell");
	        s.removeClass("sell");
	    });
	    
	    //默认载入第一个菜单
	    if(${fn:length(menuList)>1 }){
	    	$("#two_nav li").eq(0).click();
	    }else{
	    	$("#two_nav li:first").click();
	    }  
	});
</script>
