<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/content/skin/adapters/vue/vue.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/moment/moment.js"></script>
<script src="${ctx}/content/skin/adapters/elementui/1.4.3/index.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/js/distanceChargeIndex.js"></script>
<link rel="stylesheet" href="${ctx}/content/skin/css/recharge.css"/>
<link rel="stylesheet" href="${ctx}/content/skin/adapters/elementui/1.4.3/theme-default/index.css">

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
<div class="right_user" id="distance">
	<div class="operate">
		<div>
		<span style="margin-left:10px;" >机井编码：</span>
	   		<input type="text" style="color:#adadad" placeholder="请输入机井编码" name="deviceCode" value="${deviceCode==null ? '请输入机井编码' : deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井编码'" /> 
			<span style="margin-left:10px;" >机井名称：</span>
	   		<input type="text" style="color:#adadad" placeholder="请输入机井名称" name="deviceName" value="${deviceName==null ? '请输入机井名称' : deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井名称'" />
		    <span style="margin-left:10px;">用水类型：</span>
  	          <select class="pop-select" id="deviceWellUse" name="deviceWellUse" style="display:inline-block; width:118px;">
	   			<option value="">--请选择--</option>
	   			<c:forEach var="item" items="${wellList}">
	   				<option value="${item.wellUse}">${item.wellUse}</option>
	   			</c:forEach>
		   	  </select>
		    <input type="button" class="btn-search" value="查 询" onclick="distanData('');">
			<!-- <input type="button" class="btn-reset"  value="重置" @click="baseWater(2);"> -->
			<span id="loginUser"></span>
		</div>
	</div>
	<!-- 列表显示 -->
	<div id="baseWaterChargeContent"></div>
</div>

		
		