<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%--<meta http-equiv="content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=9"/>--%>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit"/>
	<meta name="force-rendering" content="webkit"/>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
<title> 机井灌溉云平台</title>
<link rel="icon" type="image/x-icon" href="${ctx}/content/skin/css/images/jzlogo.ico" />
<link href="${ctx}/content/skin/css/core/core.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/content/skin/css/core/popup.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/content/skin/adapters/pagination/pagination_blue.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/content/skin/adapters/bootstrap/bootstrap-navTabs.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/content/skin/adapters/ztree/css/zTreeStyle.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/content/skin/adapters/kindeditor/themes/default/default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/content/skin/adapters/kindeditor/plugins/code/prettify.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/content/skin/adapters/element-ui/theme-chalk/index.css">
<!-- http://webapi.amap.com/maps?v=1.3&key= https://webapi.amap.com/maps?v=1.4.10&key= -->
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=b6c49003c13af335bfa5f923966cbde1"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery-1.8.0.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/UI/jquery.SLWeb.MapHelper.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/UI/jquery.SLWeb.PopupHelper.js" charset="utf-8" ></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/ztree/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/ztree/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="${ctx}/content/skin/js/common.js" charset="utf-8" ></script>
<script type="text/javascript" src="${ctx}/content/skin/js/ztree.js" charset="utf-8" ></script>
<script type="text/javascript" src="${ctx}/content/skin/js/validate.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/pagination/pagination.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/js/ajaxFontFun.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/kindeditor/kindeditor.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/kindeditor/zh_CN.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/kindeditor/plugins/code/prettify.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/vue/vue.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/moment/moment.js"></script>
<script src="${ctx}/content/skin/adapters/element-ui/index.js"></script>
<style type="text/css">
   .deviceIcon_ico_docu{
		margin-right:2px; 
		background-position:-110px -33px; 
		vertical-align:top; 
		vertical-align:middle
	}
	.leftTree::-webkit-scrollbar {
    display: none;
}
</style>

</head>
<body>
	<div class="popup"></div>
    <div class="mask"></div>
    <div>
    	<div class="header">
			<jsp:include page="/header.jsp"></jsp:include> 
	    </div>
	    <!--内容  -->
	    <div id="contain"></div>
    </div>
    <!-- =====报警声音====== -->
	<audio id="audio" src="${ctx}/content/skin/voice/warnVoice.mp3" preload="auto" loop hidden="true"/>
	<script type="text/javascript">
	    $(function () {
	        $(".leftnav").click(function () {
	            if ($(".left").css("display") == "none") {
	                $(".leftnav").css("left", "237px"); $(".leftnav img").attr("src", "${ctx}/content/skin/images/botton-closeLeft.gif");
	                $(".right").css("left", "275px");
	            } else {
	                $(".leftnav").css("left", "0px"); $(".leftnav img").attr("src", "${ctx}/content/skin/images/botton-closeRight.gif");
	                $(".right").css("left", "20px");
	            }
	            $(".left").toggle();
	            $(".left-bottom").toggle();
	        });
	    });
	</script>
</body>
</html>