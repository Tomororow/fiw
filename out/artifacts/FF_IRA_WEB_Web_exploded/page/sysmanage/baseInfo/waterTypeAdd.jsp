<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="baseInfoForm">
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="useWaterType"><span style="color:red">*</span>取水类型：</label></td>
			<td class="table-right"><input class="pop-input-common" type="text" id="useWaterType" name="useWaterType" maxlength="32"><span id="sysMsg"></span></td>
		</tr>
	</table>
</form>