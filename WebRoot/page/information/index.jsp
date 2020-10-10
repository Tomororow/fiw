<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>信息发布首页</title>
<script charset="utf-8" type="text/javascript" src="${ctx}/content/skin/adapters/kindeditor/kindeditor.js"></script>
<script charset="utf-8" type="text/javascript" src="${ctx}/content/skin/adapters/kindeditor/zh_CN.js"></script>
<script charset="utf-8" type="text/javascript" src="${ctx}/content/skin/adapters/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/introduction.js"></script>
</head>

<body>
      <!--二级头部  -->
	   <div class="header">
		 <jsp:include page="/twoHeader.jsp"></jsp:include> 
	   </div>
	   <!--内容  -->
	   <div id="introduction" style="margin: 10% 0px 0% 25%;">
	   
	   </div>
</body>
</html>
<script type="text/javascript">
	$(function () {
	    $("#two_nav  li").click(function () {
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
	
</script>
