<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<html>
<head>
<title>基本水费设置</title>
</head>
<body>
	<form id="baseWaterPriceForm">
		<input type="hidden" id="waterId" value="${baseWaterPrice.id}">
	    <div style="width:800px; height:430px; margin-top:50px; text-align:center;">
	    	<table cellspacing="1" cellpadding="3" class='list-table' id="baseWaterPriceTb" style="width:800px;">
		        <tr style="font-size: 15px; height: 50px;">
					<td class="table-left" style="width:130px;"><label for="deviceCode">用水基准单价：</label></td>
					<td class="table-right"><input class="pop-input-common" type="text" id="standardPrice" name="standardPrice" maxlength="32" value="${baseWaterPrice.standardPrice}"></td>
					<td class="table-left"><label for="devicePlate">超出额定水量单价：</label></td>
					<td class="table-right"><input class="pop-input-common" type="text" id="exceedPrice" name="exceedPrice" maxlength="50" value="${baseWaterPrice.exceedPrice}"></td>
				</tr>
				<tr style="font-size: 15px; height: 50px;">
					<td class="table-left"><label for="createTime">创建时间：</label></td>
					<td class="table-right">
						<input id="createTime" class="Wdate" type="text"
						value="<fmt:formatDate value="${baseWaterPrice.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />"
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
					
					<td class="table-left"><label for="editTime">编辑时间：</label></td>
					<td class="table-right">
						<input id="editTime" class="Wdate" type="text" value="<fmt:formatDate value="${baseWaterPrice.editTime}" pattern="yyyy-MM-dd HH:mm:ss" />" onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
				</tr>
			</table>
			<input type="button" value="保存" style="background-color:#00BCD4;color:#fff;border:none;width: 19%;height: 7%;margin-top: 20px;" onclick="addAndEditDevice()">
		</div>
	</form>
</body>
</html>

