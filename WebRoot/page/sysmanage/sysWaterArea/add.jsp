<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<form id="sysWaterAreaForm">
	<input type="hidden" id="parentWaterAreaId" name="parentWaterAreaId" value="${parentWaterAreaId}"></input> 
	<input type="hidden" id="waterAreaLevel" name="waterAreaLevel" value="${waterAreaLevel}"></input>
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="waterAreaCode"><span style="color:red">*</span>水管区域编码</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="waterAreaCode" name="waterAreaCode" maxlength="30">
				<span id="waterAreaCodeMsg"></span></td>
		</tr>
		<tr>
			<td class="table-left"><label for="waterAreaName"><span style="color:red">*</span>水管区域名称</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="waterAreaName" name="waterAreaName" maxlength="30">
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="remark">备注</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="remark" name="remark" maxlength="100">
			</td>
		</tr>
	</table>
</form>