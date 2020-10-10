<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>远程监控列表-首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/monitor.js"></script>
</head>

<body>
	<!-- 远程监控列表 右侧内容显示界面div -->
	<div class="right_user">
		<div class="operate">
			<div class="" style="text-align:left;">
				<div id="timeSearchArea" style="float:left;">
					<span style="margin-left:10px;">机井编码：</span>
					<input type="text" style="color:#adadad; width:110px;" value="${deviceCode==null?'请输入机井编码':deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井编码';" />
					
					<span style="margin-left:10px;">机井名称：</span>
					<input type="text" style="color:#adadad; width:130px;" value="${deviceName==null?'请输入机井名称':deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井名称';" />
					
					<span style="margin-left:10px;">设备版本：</span>
					<select class="pop-select" id="deviceVersion" name="deviceVersion" style="display:inline-block; width:118px;">
			   			<option value="">--请选择--</option>
			   			<c:forEach var="item" items="${versionList}">
			   				<option value="${item}">${item}</option>
			   			</c:forEach>
			   		</select>
			   		
					<span style="margin-left:10px;">网络状态：</span>
					<select class="pop-select" id="netState" name="netState" style="display:inline-block; width:98px;">
			   			<option value="">--请选择--</option>
			   			<option value="1">在线</option>
			   			<option value="0">离线</option>
			   		</select>
			   		
			   		<span style="margin-left:10px;">水泵状态：</span>
					<select class="pop-select" id="pumpState" name="pumpState" style="display:inline-block; width:98px;">
						<option value="">--请选择--</option>
			   			<option value="1">开泵</option>
			   			<option value="0">关泵</option>
			   		</select>
			   		<span style="margin-left:10px;">用水类型：</span>
	  	            <select class="pop-select" id="deviceWellUse" name="deviceWellUse" style="display:inline-block; width:118px;">
			   			<option value="">--请选择--</option>
			   			<c:forEach var="item" items="${wellList}">
			   				<option value="${item.wellUse}">${item.wellUse}</option>
			   			</c:forEach>
			   		</select>
					<input type="button" class="btn-search" value="查 询" onclick="remoteDeviceInfo()">
					<input type="button" class="btn-reset" value="重置" onclick="remoteReset()">
				</div>
			</div>
		</div>
		<div id="remoteContent"></div>
	</div>
</body>
</html>