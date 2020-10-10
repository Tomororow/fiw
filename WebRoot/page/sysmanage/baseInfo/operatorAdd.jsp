<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="baseInfoForm">
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="operator"><span style="color:red">*</span>运营商：</label></td>
			<td class="table-right"><input class="pop-input-common" type="text" id="operator" name="operator" maxlength="32"><span id="sysMsg"></span></td>
		</tr>
	</table>
</form>