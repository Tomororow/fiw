<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="baseInfoForm">
	<input type="hidden" id="id" name="id" value="${sysMeasureType.id}"></input>
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="measureType"><span style="color:red">*</span>计量设施类型：</label></td>
			<td class="table-right"><input class="pop-input-common" type="text" id="measureType" name="measureType" maxlength="32" value="${sysMeasureType.measureType}"><span id="sysMsg"></span></td>
		</tr>
	</table>
</form>