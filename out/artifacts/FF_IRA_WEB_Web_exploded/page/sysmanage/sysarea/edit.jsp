<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
	table.pop-table input,table.pop-table select,table.pop-table textarea {
		margin-left: 0px;
	}
</style>
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/mapDialog.css">
<form id="sysAreaForm">
	<input type="hidden" id="id" name="id" value="${sysArea.id}"></input> 
	<input type="hidden" id="parentAreaId" name="parentAreaId"value="${sysArea.parentAreaId}"></input> 
	<input type="hidden" id="areaLevel" name="areaLevel" value="${sysArea.areaLevel}"></input>
	<table class="pop-table" id="sysAreaTb" style="width:450px">
		<tr>
			<td class="table-left"><label for="areaCode"><span style="color:red">*</span>区域编码</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="areaCode" name="areaCode" value="${sysArea.areaCode}" maxlength="30"> 
				<span id="areaCodeMsg"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="areaName"><span style="color:red">*</span>区域名称</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="areaName" name="areaName" value="${sysArea.areaName}" maxlength="30">
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="areaName">备注</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="remark" name="remark" value="${sysArea.remark}" maxlength="100">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	$(document).ready(function(){
		//ie下需要设置div为固定高，否则不显示
		 if(isIE()){ 
			$("#mapContainer").css("height","365px");
		 }
	});
</script>