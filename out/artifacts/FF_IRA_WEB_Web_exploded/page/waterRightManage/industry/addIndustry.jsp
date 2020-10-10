<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<style type="text/css">
	.Wdate {
		border: #ccc 1px solid;
		width: 180px;
		height: 28px;
	}
	#roundUserName{color: #c75c33;margin: -5px 0 0 20px;position: absolute;}
</style>

<form id="baseDistWaterPlanForm">
	<input type="hidden" name="wellUse" id="wellUse" value="工业" />
	<input type="hidden" name="distType" id="distType" value="2" />
	<input type="hidden" name="isAppendWater" id="isAppendWater" value="${plan.isAppendWater}" />
	<table cellspacing="1" cellpadding="3" class="pop-table" style="line-height:40px;">
		<tr>
			<td class="table-left"><span style="color:red">*</span>配水机井：</td>
			<td class="table-right" colspan="3">
				<select class="pop-select" id="waterAreaId" style="display: inline-block;width:120px;" name="waterAreaId" onchange="getSubWaterAreaId(this)">
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
		<tr>
			<td class="table-left"><label for="distYear"><span style="color:red">*</span>配水年份：</label></td>
			<td class="table-right">
				<input class="Wdate" type="text" id="distYear" name="distYear" maxlength="32" onfocus="WdatePicker({minDate:'%y',isShowClear:false,readOnly:true,dateFmt:'yyyy'})" onchange="getDistRound('distYear')" >
				<span id="distYearMsg"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="distRound"><span style="color:red">*</span>配水轮次：</label></td>
			<td class="table-right">
				<c:if test="${plan.isAppendWater==0}">
					<input type="text" id="distRound" style="width:180px;" class="pop-input-common" name="distRound" placeholder="请输入1-10的正整数" readonly="readonly"/>
					<span id="roundUserName"></span>
				</c:if>
				<c:if test="${plan.isAppendWater==1}">
					<input type="text" id="distRound" style="width:180px;" class="pop-input-common" name="distRound" placeholder="请输入1-10的正整数" readonly="readonly"/>
					<span id="roundUserName"></span>
				</c:if>
			</td>
		</tr>
		<c:if test="${plan.isAppendWater==1}">
		<tr>
			<td class="table-left"><label for="distWater"><span style="color:red">*</span>增加水量(m³)：</label></td>
			<td class="table-right"><input type="text" style="width:180px;" class="pop-input-common" id="distWater" name="distWater" maxlength="32"></td>
		</tr>
		</c:if>
		<tr>
			<td class="table-left"><label for="distPrice">
			<span style="color:red">*</span><label>
			<c:if test="${plan.isAppendWater==0}">
				此轮配水价格(元/m³)
			</c:if>
			<c:if test="${plan.isAppendWater==1}">
				阶梯性水价(元/m³)
			</c:if>
			：</label></td>
			<td class="table-right"><input class="pop-input-common" style="width:180px;" type="text" id="distPrice" name="distPrice" maxlength="32"></td>
		</tr>
	</table>
</form>