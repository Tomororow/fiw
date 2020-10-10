<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<form id="sysAreaForm">
	<input type="hidden" id="parentAreaId" name="parentAreaId" value="${parentAreaId}"></input> 
	<input type="hidden" id="areaLevel"	name="areaLevel" value="${areaLevel}"></input>
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="areaName"><span style="color:red">*</span>所属水管区域</label>：</td>
			<td class="table-right" colspan="3">
				<select class="pop-select" id="waterAreaId" name="waterAreaId" style="display:inline-block; width:100px;" onchange="getSubWaterAreaId(this)">
					<option value=''>--请选择水管区域--</option>
					<c:forEach var="item" items="${sysWaterAreaList}">
						<option value="${item.id}">${item.waterAreaName}</option>
					</c:forEach>
				</select> 
				<input type="hidden" id="num" name="num" value="0" /> 
				<span id="subWaterArea_0"></span>
				<input type="button" value="确定" style="background-color:#00BCD4; color:#fff; border:none; width: 60px; height: 31px; line-height:5px;" onclick="getWaterAreaCode(this)"
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="areaCode"><span style="color:red">*</span>区域编码</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="areaCode" name="areaCode" maxlength="30"> 
				<span id="areaCodeMsg"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="areaName"><span style="color:red">*</span>区域名称</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="areaName" name="areaName" maxlength="30">
			</td>
		</tr>
		
		<tr>
			<td class="table-left"><label for="remark">备注</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="remark" name="remark" maxlength="100">
			</td>
		</tr>
	</table>
</form>