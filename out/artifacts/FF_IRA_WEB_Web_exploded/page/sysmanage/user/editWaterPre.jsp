<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
    
<form id="sysUserWaterForm">
	<input type="hidden" id="UserCode" name="UserCode" value="${userCode}"/>
	<table class="pop-table" style="width:500px">
		<tr>
			<td class="table-left"><label for="IsBuyWater">是否开通售水权限</label>：</td>
			<td class="table-right">
				<input type="radio" id="IsBuyWater0" name="IsBuyWater" value="0" <c:if test="${sysUserWater.isBuyWater == 0}">checked="checked"</c:if>><label style="float:left;">否</label>
				<input type="radio" id="IsBuyWater1" name="IsBuyWater" value="1" <c:if test="${sysUserWater.isBuyWater == 1}">checked="checked"</c:if>><label style="float:left;">是</label>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="BuyWaterRemark">售水权限描述</label>：</td>
			<td class="table-right">
				<textarea class="pop-textarea" id="BuyWaterRemark" name="BuyWaterRemark" maxlength="300">${sysUserWater.buyWaterRemark}</textarea>
			</td>
		</tr>
	</table>
</form>
