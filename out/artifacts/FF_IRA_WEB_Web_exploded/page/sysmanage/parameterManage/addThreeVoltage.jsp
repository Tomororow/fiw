<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<form id="threeVoltageForm">
	<table cellspacing="1" cellpadding="3" class="pop-table">
		<tr>
			<td class="table-left"><label for="voltage"><span style="color:red">*</span>电压值：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="voltage" name="voltage" maxlength="30" onblur="getAllVoltage()"> 
				<span id="voltageMsg"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="aPhaseVoltageUp"><span style="color:red">*</span>A相电压上限：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="aPhaseVoltageUp" name="aPhaseVoltageUp" maxlength="30">
				<span id="aPhaseVoltageUpMsg"></span>
			</td>
			<td class="table-left"><label for="aPhaseVoltageDown"><span style="color:red">*</span>A相电压下限：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="aPhaseVoltageDown" name="aPhaseVoltageDown" maxlength="30">
				<span id="aPhaseVoltageDownMsg"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="bPhaseVoltageUp"><span style="color:red">*</span>B相电压上限：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="bPhaseVoltageUp" name="bPhaseVoltageUp" maxlength="30">
				<span id="bPhaseVoltageUpMsg"></span>
			</td>
			<td class="table-left"><label for="bPhaseVoltageDown"><span style="color:red">*</span>B相电压下限：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="bPhaseVoltageDown" name="bPhaseVoltageDown" maxlength="30">
				<span id="bPhaseVoltageDownMsg"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="cPhaseVoltageUp"><span style="color:red">*</span>C相电压上限：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="cPhaseVoltageUp" name="cPhaseVoltageUp" maxlength="30">
				<span id="cPhaseVoltageUpMsg"></span>
			</td>
			<td class="table-left"><label for="cPhaseVoltageDown"><span style="color:red">*</span>C相电压下限：</label></td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="cPhaseVoltageDown" name="cPhaseVoltageDown" maxlength="30">
				<span id="cPhaseVoltageDownMsg"></span>
			</td>
		</tr>
	</table>
</form>