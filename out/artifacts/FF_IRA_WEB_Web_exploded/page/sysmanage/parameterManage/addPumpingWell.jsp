<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
	.confirmBtn{background-color: #18B399;border: solid 1px #ccc;color: #fff;height: 25px;border-radius: 2px;width:100px;}
	.confirmBtn:HOVER {cursor: pointer;position: relative;top:1px;left:1px;}
	input[type="checkbox"] {
			    width: 100%;
			    height: 30px;
			    display: inline-block;
			    text-align: center;
			    vertical-align: middle;
			    margin-right: 10px;
			    position: relative;
			}
			input[type="checkbox"]::before:hover{background: #9cc0da;cursor: pointer;}
			input[type="checkbox"]::before {
			    content: "";
			    position: absolute;
			    top: 3px;
			    left: -4px;
			    background: #fff;
			    width: 20px;
			    height: 20px;
			    border: 1px solid #d9d9d9;
			}
			input[type="checkbox"]:checked::before {
			    content: "\2713";
			    background-color: #409eff;
			    position: absolute;
			    top: 3px;
			    left: -4px;
			    width: 20px;
			    height: 20px;
			    color: #fff;
			    font-weight: bold;
			}
</style>
<form id="pumpingWellForm">
	<table cellspacing="1" cellpadding="3" class="pop-table">
		<tr>
			<td class="table-left"><label for="waterAreaId"><span style="color:red">*</span>水管区域：</label></td>
			<td colspan="3" class="table-right">
				<select class="pop-select" id="waterAreaId" style="display:inline-block; width:120px;" name="waterAreaId" onchange="getSubWaterAreaId(this)">
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
			<td class="table-left"><span style="color:red">*</span>用水类型：</td>
			<td class="table-right">
	 	        <select class="pop-selecte" id="deviceWellUse" name="deviceWellUse"  style="display:inline-block; width:120px;">
		   			<option value="">--请选择--</option>
		   			<c:forEach var="item" items="${wellList}">
		   				<option value="${item.wellUse}">${item.wellUse}</option>
		   			</c:forEach>
		   		</select>
			</td>
		<!-- 	<td class="table-left"><span style="color:red">*</span>剩余水量不得低于：</td> -->
		<td class="table-left"><span style="color:red"></span>充值后水量无变化时间（/天）：</td>
			<td class="table-right">
				<!-- <input class="pop-input-common" type="text" id="backone" placeHolder="最低剩余水量"  name="backone" maxlength="30" style="width:100px;height: 23px;"> -->
				<input type="checkbox" onchange="handleMessChange(this)"/>
				<!--   -->
				<!--  <span id="isMess" style="position: relative;top: 8px;left: 20px;font-size: 14px;"></span> -->
				 <input class="pop-input-common" type="number" id="backone" placeHolder="请输入设置天数"  name="backone" maxlength="30" style="width:100px;height: 23px;display:none">
			</td>
		</tr>
		
		<tr>
			<td class="table-left"><label for="startTimes"><span style="color:red">*</span>起始时间：</label></td>
			<td class="table-right">
				<input id="startTimes" name="startTimes" style="width:auto;" class="Wdate" type="text" value="${defaultTime}" 
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</td>
			<td class="table-left"><label for="endTimes"><span style="color:red">*</span>结束时间：</label></td>
			<td class="table-right">
				<input id="endTimes" name="endTimes" style="width:auto;" class="Wdate" type="text" value="${defaultTime}" 
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="onnetstate"><span style="color:red">*</span>最长不在线（天）：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="onnetstate" placeHolder="不在线天数" name="onnetstate" maxlength="30">
			</td>
			<!-- <td class="table-left"><label for="waterlow"><span style="color:red">*</span>高峰期内每天用水量不得低于（m³）：</label></td> -->
			<td class="table-left"><label for="waterlow"><span style="color:red"></span>高峰期用水量无变化时间（/天）：</label></td>
			<td class="table-right">
				<!-- <input class="pop-input-common" type="text" id="waterlow" placeHolder="每天最低用水量" name="waterlow" maxlength="30"> -->
				<input class="pop-input-common" type="number" id="waterlow" placeHolder="请输入设置天数" name="waterlow" maxlength="30" value="">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="table-left">
				<span style="color:#bda942;font-size:13px;">注：如果选择到水管区域，系统将对该区域下的每一眼机井进行设置</span>
			</td>
		</tr>
	</table>
</form>