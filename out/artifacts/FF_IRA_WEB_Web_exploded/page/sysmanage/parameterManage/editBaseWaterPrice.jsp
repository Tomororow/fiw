<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!-- 基本水费费率edit page -->

<style>
	.Wdate {
		border: #ccc 1px solid;
		width: 200px;
		height: 60px;
	}
</style>
<form id="baseWaterPriceForm">
	<input type="hidden" value="${baseWaterPrice.id}" id="id" name="id" />
	<table cellspacing="1" cellpadding="3" class="pop-table">
		<tr>
			<td class="table-left"><label for="inYear"><span style="color:red">*</span>征收年份：</label></td>
			<td class="table-right">
				<input id="inYear" name="inYear" class="Wdate" type="text" value="${baseWaterPrice.inYear}" 
					onfocus="WdatePicker({isShowClear:false, readOnly:true, dateFmt:'yyyy'})"/>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="standardPrice"><span style="color:red">*</span>基本水费费率(亩/元)：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="standardPrice" name="standardPrice" value="${baseWaterPrice.standardPrice}" />
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="exceedPrice"><span style="color:red">*</span>超出额定水量单价(亩/元)：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="exceedPrice" name="exceedPrice" value="${baseWaterPrice.exceedPrice}"/>
			</td>
		</tr>
	</table>
</form>