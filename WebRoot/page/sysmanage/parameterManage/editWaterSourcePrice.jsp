<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!-- 水资源费edit page -->

<style>
.Wdate {
	border: #ccc 1px solid;
	width: 200px;
	height: 31px;
}
</style>
<form id="waterSourcePriceForm">
	<input type="hidden" value="${paramWaterSourcePrice.id}" id="id" name="id" />
	<table cellspacing="1" cellpadding="3" class="pop-table">
		<tr>
			<td class="table-left"><label for="inYears"><span style="color:red">*</span>征收年份：</label></td>
			<td class="table-right">
				<input id="inYear" name="inYear" class="Wdate" type="text" value="${paramWaterSourcePrice.inYear}" 
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy'})" onblur="validateDateInfo(this)"/>
				<span id="inYearsMsg"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="waterSourcePrice"><span style="color:red">*</span>水资源征收费率(m³/元)：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="waterSourcePrice" name="waterSourcePrice" value="${paramWaterSourcePrice.waterSourcePrice}" onblur="validateWaterSource(this)" />
				<span id="waterSourcePriceMsg"></span>
			</td>
		</tr>
	</table>
</form>