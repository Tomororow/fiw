<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/content/skin/js/deviceReport.js"></script>
<div class="right_user">
<div class="operate">
	<div class="" style="text-align: left;">
	   	<div id="timeSearchArea" style="float: left;">
	   		<span style="margin-left:10px;">所属水管区域：</span>
	   		<select class="pop-select" id="waterAreaId" style="display: inline-block;width:118px;" name="waterAreaId">
	   			<option value="">--水管区域--</option>
	   			<c:forEach var="item" items="${sysWaterAreaList}">
	   				<option value="${item.id}">${item.waterAreaName}</option>
	   			</c:forEach>
	   		</select>
	   		<span style="margin-left:10px;">机井编码：</span>
   	        <input type="text" style="color:#adadad; width:118px;" value="${deviceCode==null ? '请输入机井编码' : deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井编码'" />
	   		<span style="margin-left:10px;">机井名称：</span>
	   		<input type="text" style="color:#adadad; width:118px;" value="${deviceName==null ? '请输入机井名称' : deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井名称'" />
			
		 	<span style="margin-left:10px;">用水类型：</span>
 	        <select class="pop-select" id="deviceWellUse" name="deviceWellUse" style="display:inline-block; width:118px;">
	   			<option value="">--请选择--</option>
	   			<c:forEach var="item" items="${wellList}">
	   				<option value="${item.wellUse}">${item.wellUse}</option>
	   			</c:forEach>
	   		</select>
			<input type="button" class="btn-search" value="查询" onclick="deviceInfoLists()">
			<input type="button" class="btn-reset"  value="重置" onclick="deviceReset()">
			<input type="button" class="btn-export-report" value="信息导出" onclick="deviceinfoReport()">
		</div>
	</div>
</div>
<div id="deviceContent"></div>
</div>