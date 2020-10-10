<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/content/skin/js/waterCardReport.js"></script>
<div class="right_user">
	<div class="operate">
		<div class="" style="text-align: left;">
		   	<div id="timeSearchArea" style="float: left;">
		   		<span style="margin-left:10px;">机井名称：</span>
		   		<input type="text" style="color:#adadad; width:135px;" value="${deviceName==null ? '请输入机井名称' : deviceName}" id="deviceName" value="请输入机井名称" 
		   			onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井名称'" />
		   	
		   		<span style="margin-left:10px;">持卡人姓名：</span>
		   		<input type="text" style="color:#adadad; width:125px;" value="${ownerName==null ? '请输入持卡人姓名' : ownerName}" id="ownerName" value="请输入持卡人姓名" 
		   			onfocus="if(this.value == '请输入持卡人姓名') this.value = '';" onblur="if(this.value == '') this.value = '请输入持卡人姓名'" />
		   		
			    <input type="button" class="btn-search" value="查询" onclick="waterCardInfo()">
				<input type="button" class="btn-reset"  value="重置" onclick="waterCardInfoSet()">
				<input type="button" class="btn-export-report" value="信息导出" onclick="waterCardReport()">
			</div>
		</div>
	</div>
	<div id="waterCardContent"></div>
</div>