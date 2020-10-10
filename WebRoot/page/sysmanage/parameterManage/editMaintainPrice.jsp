<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!-- 末级渠系费率add page -->

<style>
	.Wdate {
		border: #ccc 1px solid;
		width: 200px;
		height: 60px;
	}
</style>
<form id="maintainPriceForm">
	<input type="hidden" value="${maintainPrice.id}" id="id" name="id" />
	<table cellspacing="1" cellpadding="3" class="pop-table">
		<tr>
			<td class="table-left"><label for="inYear"><span style="color:red">*</span>征收年份：</label></td>
			<td class="table-right">
				<input id="inYear" name="inYear" class="Wdate" type="text" value="${maintainPrice.inYear}" 
					onfocus="WdatePicker({isShowClear:false, readOnly:true, dateFmt:'yyyy'})"/>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="maintainPrice"><span style="color:red">*</span>末级渠系(维修养护)费率(m³/元)：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="maintainPrice" name="maintainPrice" value="${maintainPrice.maintainPrice}"/>
			</td>
		</tr>
	</table>
</form>