<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="baseWaterChargeForm">
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="waterAreaId"><span style="color:red">*</span>缴费机井：</label></td>
			<td class="table-right" colspan="3">
				<select class="pop-select" id="waterAreaId" style="display: inline-block;width:150px;" name="waterAreaId" onchange="getSubWaterAreaId(this)">
					<option value=''>--请选择水管区域--</option>
					<c:forEach var="item"  items="${sysWaterAreaList}">
	                	<option value="${item.id}">${item.waterAreaName}</option>
	                </c:forEach> 
				</select>
				<input type="hidden" id="waterAreaNum" name="waterAreaNum" value="0"/>
				<span id="subWaterArea_0"></span>
				<input type="button" value="确定" style="background-color:#00BCD4;color:#fff;border:none;width: 60px;height: 31px;line-height:5px;" onclick="getWaterArea(this)">
				<span id="waterAreaMsg"></span>
			</td>
		</tr>
	</table>
</form>