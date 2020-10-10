<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
	.isCamera{display: none}
	.isdvrCamera{display: none}
	.cameraParm{}
	.dvrParm{}
	table.pop-table input, table.pop-table select, table.pop-table textarea{
  		margin-left: 0px;
	}
</style>
<link rel="stylesheet" type="text/css"  href="${ctx}/content/skin/css/mapDialog.css">
<form id="sysWaterAreaForm">
	<input type="hidden" id="id" name="id" value="${sysWaterArea.id}"></input>
	<input type="hidden" id="parentWaterAreaId" name="parentWaterAreaId" value="${sysWaterArea.parentWaterAreaId}"></input> 
	<input type="hidden" id="waterAreaLevel" name="waterAreaLevel" value="${sysWaterArea.waterAreaLevel}"></input>
	<table class="pop-table" id="sysWaterAreaTb" style="width:450px">
	  <tr>
	     <td class="table-left"><label for="waterAreaCode"><span style="color:red">*</span>水管区域编码</label>： </td>
	     <td class="table-right">
	         <input class="pop-input-common" type="text" id="waterAreaCode" name="waterAreaCode" value="${sysWaterArea.waterAreaCode}" maxlength="30">
	         <span id="waterAreaCodeMsg"></span>
	     </td>
	  </tr>
      <tr>
      	<td class="table-left"><label for="waterAreaName"><span style="color:red">*</span>水管区域名称</label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="waterAreaName" name="waterAreaName" value="${sysWaterArea.waterAreaName}" maxlength="30">
          </td>
      </tr>
      <tr>
      	<td class="table-left"><label for="areaName">备注</label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="remark" name="remark" value="${sysWaterArea.remark}" maxlength="100">
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