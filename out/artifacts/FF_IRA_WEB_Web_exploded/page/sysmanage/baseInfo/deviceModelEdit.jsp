<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="baseInfoForm">
	<input type="hidden" id="id" name="id" value="${sysDeviceModel.id}"></input>
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="deviceModel"><span style="color:red">*</span>机井设备型号：</label></td>
			<td class="table-right"><input class="pop-input-common" type="text" id="deviceModel" name="deviceModel" maxlength="32" value="${sysDeviceModel.deviceModel}"><span id="sysMsg"></span></td>
		</tr>
	</table>
</form>