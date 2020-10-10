<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<form id="powerForm">
	<table cellspacing="1" cellpadding="3" class="pop-table">
		<tr>
			<td class="table-left"><label for="powerPercentUp"><span style="color:red">*</span>上行百分比：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="powerPercentUp" name="powerPercentUp" maxlength="30">
				<span id="powerPercentUpMsg"></span>
			</td>
			<td class="table-left"><label for="powerPercentDown"><span style="color:red">*</span>下行百分比：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="powerPercentDown" name="powerPercentDown" maxlength="30">
				<span id="powerPercentDownMsg"></span>
			</td>
		</tr>
	</table>
</form>