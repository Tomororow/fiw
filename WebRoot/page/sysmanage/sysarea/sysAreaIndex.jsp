<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>站点管理首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysArea.js"></script>
</head>

<body>
	<div class="left">
		<div class="leftTree" style="padding-left:2px;padding-top:5px;overflow-y:auto; overflow-x:hidden;">
			<ul id="ztree" class="ztree"></ul>
		</div>
    </div>
	<div class="leftnav">
		<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
	</div>
	<div class="right_user" id="deviceContent"></div> 
	<script type="text/javascript">
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
        ztreeFun($("#ztree"),"sysArea/getAllArea.do?isSearchDevice=false&showOnLineDevice=false",sysAreaList);
    });
   function sysAreaList(node){
	    var params = {
	  			id:node.id
		};
	  	loadSysAreaList(params,false);
   }
</script>
</body>
</html>