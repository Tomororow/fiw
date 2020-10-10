<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="baseInfoForm">
	<input type="hidden" id="id" name="id" value="${sysIrrigationMode.id}"></input>
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="irrigationMode"><span style="color:red">*</span>灌溉模式：</label></td>
			<td class="table-right"><input class="pop-input-common" type="text" id="irrigationMode" name="irrigationMode" maxlength="32" value="${sysIrrigationMode.irrigationMode}"><span id="sysMsg"></span></td>
		</tr>
	</table>
</form>