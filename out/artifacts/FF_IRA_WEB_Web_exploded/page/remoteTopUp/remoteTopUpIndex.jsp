<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>收费管理-计量水费首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/remoteTopUpInfo.js"></script>
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
		<a href="javascript:void(0)">
			<img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" />
		</a>
	</div>
	<!-- 右侧内容显示界面div -->
	<div class="right_user">
		<div class="operate">
			<span style="padding-left:20px;">征收年份：</span>
			<input id="inYear" class="Wdate" value="<fmt:formatDate value='${inYear}' pattern='yyyy' />" 
	   			type="text" onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy'})" />&nbsp;&nbsp;&nbsp;
			<span style="margin-bottom:10px;">用水类型：</span>
	   		<select class="pop-select" id="distType" style="display: inline-block;width:150px;height:27px;" name="distType">
	   			<c:if test="${distType==1 || null==distType}">
	   				<option value="1" selected>灌溉</option>
		   			<option value="2">工业</option>
		   			<option value="3">生活</option>
	   			</c:if>
	   			<c:if test="${distType==2}">
	   				<option value="1">灌溉</option>
		   			<option value="2" selected>工业</option>
		   			<option value="3">生活</option>
	   			</c:if>
	   			<c:if test="${distType==3}">
	   				<option value="1">灌溉</option>
		   			<option value="2">工业</option>
		   			<option value="3" selected>生活</option>
	   			</c:if>
	   		</select>
		    <input type="button" class="btn-search" style="width:62px;margin-bottom:10px;" value="查 询" onclick="measureTypeChargeList()">
		</div>
		<div id="measureTypeChargeInfoContent"></div>
	</div>
</body>
</html>