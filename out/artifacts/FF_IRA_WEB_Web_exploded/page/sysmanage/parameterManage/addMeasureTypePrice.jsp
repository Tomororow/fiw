<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!-- 计量水费征收费 add page -->

<style>
	.Wdate {
		border: #ccc 1px solid;
		width: 200px;
		height: 31px;
	}
</style>
<form id="measureTypePriceForm">
	<table cellspacing="1" cellpadding="3" class="pop-table">
		<tr>
			<td class="table-left"><label for="inYears"><span style="color:red">*</span>征收年份：</label></td>
			<td class="table-right">
				<input id="inYear" name="inYear" class="Wdate" type="text" value="${currentYear}" 
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy'})" />
				<span id="inYearsMsg"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="measureTypePrice"><span style="color:red">*</span>计量水费征收费(m³/元)：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="measureTypePrice" name="measureTypePrice">
				<span id="measureTypePriceMsg"></span>
			</td>
		</tr>
	</table>
</form>