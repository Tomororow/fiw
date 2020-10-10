<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!-- 基本水费缴费方式page -->
<form id="baseWaterChargeForm">
	<input type="hidden" value="${baseWaterCharge.id}" name="id" />
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="chargeMode">缴费方式</label>：</td>
			<td class="table-right" colspan="3">
				<select class="pop-select" id="chargeMode" style="display: inline-block;width:100px;" name="chargeMode">
					<option value=''>--请选择缴费方式--</option>
					<option value="1">现金</option>
					<option value="2">银行卡</option>
				</select>
			</td>
		</tr>
	</table>
</form>