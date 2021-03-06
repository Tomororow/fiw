<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
	#waterDiv{display: none;}
</style>
<form id="baseInfoForm">
	<input type="hidden" id="id" name="id" value="${sysServiceMan.id}"></input>
	<input type="hidden" id="waterAreaId" name="waterAreaId" value="${sysServiceMan.waterAreaId}"></input>
	<table class="pop-table">
		<tr>
			<td class="table-left"><label for="waterAreaId"><span style="color:red">*</span>所属水管区域：</label></td>
			<td class="table-right" colspan="3">
				<div id="waterSet" style="display:flex">
					<span style="margin-top: 5px;font-size: 15px;color: #1b7bce;margin-right: 15%;">${sysServiceMan.waterAreaName}</span>
					<input type="button" value="修改归属区域" style="background-color:#00BCD4; color:#fff; border:none; width: auto; height: 31px; line-height:5px;" onclick="updateArea()">
				</div>
				<div id="waterDiv">
		             <select class="pop-select" id="waterAreaId" style="display:inline-block; width:100px;" name="waterAreaId" onchange="getSubWaterAreaId(this)">
						<option value=''>--请选择水管区域--</option>
						<c:forEach var="item" items="${sysWaterAreaList}">
							<option value="${item.id}">${item.waterAreaName}</option>
						</c:forEach>
					 </select> 
					<input type="hidden" id="num" name="num" value="0" /> 
					<span id="subWaterArea_0"></span>
					<input type="button" value="确定" style="background-color:#00BCD4; color:#fff; border:none; width: 60px; height: 31px; line-height:5px;" onclick="getWaterAreaCode(this)">
				</div>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="serviceMan"><span style="color:red">*</span>人员姓名：</label></td>
			<td class="table-right"><input class="pop-input-common" type="text" id="serviceMan" name="serviceMan" maxlength="32" value="${sysServiceMan.serviceMan}"><span id="sysMsg"></span></td>
		</tr>
		
		<tr>
			<td class="table-left"><label for="phone"><span style="color:red">*</span>联系电话：</label></td>
			<td class="table-right"><input class="pop-input-common" type="text" id="phone" name="phone" maxlength="32" value="${sysServiceMan.phone}"><span id="sysMsg"></span></td>
		</tr>
		<tr>
			<td class="table-left"><label for="duty"><span style="color:red">*</span>职务：</label></td>
			<td class="table-right"><input class="pop-input-common" type="text" value="${sysServiceMan.duty}" id="duty" name="duty" maxlength="32"><span id="sysMsg"></span></td>
		</tr>
	</table>
</form>