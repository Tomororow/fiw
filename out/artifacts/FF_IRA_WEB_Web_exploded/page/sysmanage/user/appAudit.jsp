<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
    
<form id="appUserAuditForm">
	<input type="hidden" id="id" name="id" value="${id}"/>
	
	<table class="pop-table" style="width:700px">
		<!-- 为app用户注册审核时分配行政区域和水管区域 -->
		<tr>
			<td class="table-left"><label for="waterAreaId"><span style="color:red">*</span>所属水管区域</label>：</td>
			<td class="table-right" colspan="3">
				<select class="pop-select" id="waterAreaId" style="display: inline-block;width:100px;" name="waterAreaId" onchange="getAuditWaterAreaId(this)">
					<option value=''>--请选择水管区域--</option>
					<c:forEach var="item" items="${sysWaterAreaList}">
						<option value="${item.id}">${item.waterAreaName}</option>
					</c:forEach>
				</select> 
				<input type="hidden" id="num" name="num" value="0" /> 
				<span id="subWaterArea_0"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="areaId"><span style="color:red">*</span>所属行政区域</label>：</td>
			<td class="table-right" colspan="3">
				<select class="pop-select" id="areaId" style="display: inline-block;width:100px;" name="areaId" onchange="getAuditAreaId(this)">
					<option value=''>--请选择行政区域--</option>
					<c:forEach var="item" items="${sysAreaList}">
						<option value="${item.id}">${item.areaName}</option>
					</c:forEach>
				</select> 
				<input type="hidden" id="areaNum" name="areaNum" value="0" /> 
				<span id="subArea_0"></span> 
			</td>
		</tr>
		
		<tr>
			<td class="table-left"><label for="isAppUser"><span style="color:red;">*</span>是否为App用户：</label></td>
			<td class="table-right">
				<input type="radio" id="isAppUser0" name="isAppUser" value="0" <c:if test="${userInfo.isAppUser == 0}">checked="checked"</c:if>><label style="float:left;">否</label>
				<input type="radio" id="isAppUser1" name="isAppUser" value="1" <c:if test="${userInfo.isAppUser == 1}">checked="checked"</c:if>><label style="float:left;">是</label>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="IsBuyWater"><span style="color:red;">*</span>审核状态：</label></td>
			<td class="table-right">
				<input type="radio" id="auditFlag0" name="auditFlag" value="0" <c:if test="${userInfo.auditFlag == 0}">checked="checked"</c:if>><label style="float:left;">未审核</label>
				<input type="radio" id="auditFlag1" name="auditFlag" value="1" <c:if test="${userInfo.auditFlag == 1}">checked="checked"</c:if>><label style="float:left;">审核通过</label>
				<input type="radio" id="auditFlag2" name="auditFlag" value="2" <c:if test="${userInfo.auditFlag == 2}">checked="checked"</c:if>><label style="float:left;">审核未通过</label>
			</td>
		</tr>
	</table>
</form>