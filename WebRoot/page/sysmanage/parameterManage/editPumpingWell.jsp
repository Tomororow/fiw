<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
	.confirmBtn{background-color: #18B399;border: solid 1px #ccc;color: #fff;height: 25px;border-radius: 2px;width:100px;}
	.confirmBtn:HOVER {cursor: pointer;position: relative;top:1px;left:1px;}
</style>
<div>	
<form id="editWellForm">
	<input id="id" name="id" value="${ppw.id}" hidden/>
	<table>
		<tr>
			<td class="table-left"><label for="startTimes"><span style="color:red">*</span>起始时间：</label></td>
			<td class="table-right">
				<input id="startTimes" name="startTimes" style="width:auto;" class="Wdate" type="text" value="${startTime}" 
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</td>
			<td class="table-left"><label for="endTimes"><span style="color:red">*</span>结束时间：</label></td>
			<td class="table-right">
				<input id="endTimes" name="endTimes" style="width:auto;" class="Wdate" type="text" value="${endTime}" 
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</td>
		

		</tr>
		<tr>
			<td class="table-left"><label for="onlineDay"><span style="color:red">*</span>最长不在线（天）：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" value="${ppw.onnetstate}" id="onlineDay" name="onlineDay" maxlength="30">
			</td>
			<td class="table-left"><span style="color:red">*</span>剩余水量不得低于：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" value="${ppw.backone}" id="backone" placeHolder="最低剩余水量"  name="backone" maxlength="30" style="width:154px;height: 23px;">
				</td>
			
		</tr>
		<tr>
			<td class="table-left"><label for="useWater"><span style="color:red">*</span>高峰期内每天用水量不得低于（m³）：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" value="${ppw.waterlow}" id="useWater" name="useWater" maxlength="30">
			</td>
		</tr>
	</table>
	</form>
</div>