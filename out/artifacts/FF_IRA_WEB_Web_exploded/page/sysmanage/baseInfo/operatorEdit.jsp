<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="baseInfoForm">
	<input type="hidden" id="id" name="id" value="${sysOperator.id}"/>
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="operator"><span style="color:red">*</span>供应商：</label></td>
			<td class="table-right"><input class="pop-input-common" type="text" id="operator" name="operator" maxlength="32" value="${sysOperator.operator}"><span id="sysMsg"></span></td>
		</tr>
	</table>
</form>