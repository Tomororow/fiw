<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<form id="powerForm">
	<input type="hidden" value="${paramPower.id}" name="id" />
	<table cellspacing="1" cellpadding="3" class="pop-table">
		<tr>
			<td class="table-left"><label for="areaName"><span style="color:red">*</span>上行百分比：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="powerPercentUp" value="${paramPower.powerPercentUp}" name="powerPercentUp" maxlength="30">
				<span id="powerPercentUpMsg"></span>
			</td>
			<td class="table-left"><label for="areaName"><span style="color:red">*</span>下行百分比：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="powerPercentDown" value="${paramPower.powerPercentDown}" name="powerPercentDown" maxlength="30">
				<span id="powerPercentDownMsg"></span>
			</td>
		</tr>
	</table>
</form>