<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<html>
<head>
<title>机井管理首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/distanceUpdate.js"></script>
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
	
	<div class="right_user">
		<div class="operate">
			<div class="" style="text-align: left;">
				<div id="timeSearchArea" style="float: left;">
	   	            <span style="margin-left:10px;">机井编码：</span>
	   	            <input type="text" style="color:#adadad" value="${deviceCode==null?'请输入机井编码':deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井编码';" />
		            
		            <span style="margin-left:10px;">机井名称：</span>
	   	            <input type="text" style="color:#adadad" value="${deviceName==null?'请输入机井名称':deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井名称';" />
	   	            
	   	            <span style="margin-left:10px;">版本选择：</span>
	  	            <select class="pop-select" id="deviceVersion" name="deviceVersion" style="display:inline-block; width:118px;">
			   			<option value="">--请选择--</option>
			   			<c:forEach var="item" items="${versionList}">
			   				<option value="${item}">${item}</option>
			   			</c:forEach>
			   		</select>
		            
		            <input type="button" class="btn-search" value="查 询" onclick="search()">
					<input type="button" class="btn-reset" value="重置" onclick="reset()">
		        </div>
			</div>
		</div>
			<!-- 右侧底部内容显示界面div -->
		<div id="distanceUpdateContent"></div>
	</div>
</body>
</html>