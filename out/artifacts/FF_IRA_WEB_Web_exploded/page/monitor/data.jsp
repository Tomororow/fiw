<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/content/skin/js/realDataReport.js"></script>
<div id="vity" class="right_user">
	<div class="operate">
		<div class="" style="text-align: left;">
			<div id="timeSearchArea"  v-if="activeName=='first'"  style="float: left;">
				<span style="margin-left:10px;">机井名称：</span>
				<input type="text" style="color:#adadad" id="deviceName" value="请输入机井名称" placeholder="请输入机井名称" />

				<span style="margin-left:10px;">机井编码：</span>
   	            <input type="text" style="color:#adadad" value="${deviceCode==null?'请输入机井编码':deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井编码';" />
	            
   	            <span style="margin-left:10px;">用水类型：</span>
  	            <select class="pop-select" id="deviceWellUse" name="deviceWellUse" style="display:inline-block; width:118px;">
		   			<option value="">--请选择--</option>
		   			<c:forEach var="item" items="${wellList}">
		   				<option value="${item.wellUse}">${item.wellUse}</option>
		   			</c:forEach>
		   		</select>
	            <input type="button" class="btn-search" value="查 询" onclick="search()">
				<input type="button" class="btn-reset" value="重置" onclick="reset()">
	        </div>
			<div id="timeSearchArea"  v-if="activeName=='second'"  style="float: left;">
				<span style="margin-left:10px;">设备名称：</span>
				<input type="text" style="color:#adadad"  id="deviceNamg"  placeholder="请输入设备名称" />
				<span style="margin-left:10px;">设备状态：</span>
				<select class="pop-select" id="backtwo" name="backtwo" style="display:inline-block; width:118px;">
					<option value="">--请选择--</option>
					<option value="1">在线</option>
					.<option value="0">离线</option>
				</select>
				<input type="button" class="btn-search" value="查 询" onclick="IndustSearch(1,0,0)">
				<input type="button" class="btn-export-report" onclick="IndustReport(0,0)" value="报表导出" >
			</div>
		</div>
	</div>
	<div v-if="userType==true" style="margin-top: 40px;">
		<template>
		  <el-tabs  v-model="activeName" type="card" @tab-click="handleClick">
		    <el-tab-pane label="地下水计量" name="first"></el-tab-pane>
		    <el-tab-pane label="工业机井计量" name="second"></el-tab-pane>
		  </el-tabs>
		</template>
	</div>
	<div id="waterContent"></div>
</div>