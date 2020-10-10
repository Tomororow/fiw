<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<html>
<head>
<title>机井管理首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/baseDeviceInfo.js"></script>
</head>

<body>
	<!-- 左侧树形菜单栏 -->
	<div class="left">
		<div class="leftTree" style="padding-left:2px;padding-top:5px;overflow-y:auto; overflow-x:hidden;">
			<ul id="ztree" class="ztree"></ul>
		</div>
    </div>
    
    <!-- 左侧树形菜单与右边显示界面之间的开/关左侧树形菜单栏按钮 -->
	<div class="leftnav">
		<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
	</div>
	
	<!-- 右侧内容显示界面div -->
	<div class="right_user" id="baseDeviceInfoContent"></div>
</body>
</html>