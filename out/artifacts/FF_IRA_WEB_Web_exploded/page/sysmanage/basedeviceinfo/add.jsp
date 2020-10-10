<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
.isCamera {display: none}
.isdvrCamera {display: none}
table.pop-table input,table.pop-table select,table.pop-table textarea {
	margin-left: 0px;
}
table.pop-table select.pop-select {
	width: 148px;
}
table.pop-table input.pop-input-common {
	width: 140px;
}
table.pop-table textarea.pop-textarea {
	width: 300px;
	max-width: 300px;
	margin-top: 2px;
}
</style>
<script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery-form.js"></script>
<!-- 引用弹出框的js -->
<script type="text/javascript" src="${ctx}/content/skin/js/tinybox.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/mapDialog.css">
<!-- 当点击地图上的机井点，选择里面图片预览的按钮，弹出框的样式设置 -->
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/common.css"/>
<style type="text/css">
	#tinybox{position:absolute; display:none; padding:10px; background:#ffffff url(../image/preload.gif) no-repeat 50% 50%; border:10px solid #e3e3e3; z-index:2000;}
	#tinymask{position:absolute; display:none; top:0; left:0; height:100%; width:100%; background:#000000; z-index:1500;}
	#tinycontent{background:#ffffff; font-size:1.1em;}
</style>
<form id="baseDeviceInfoForm" enctype="multipart/form-data">
	<input type="hidden" id="operateType" value="add" />
	<input type="hidden" id="areaId" name="areaId" value="${areaId}"></input>
	<input type="hidden" id="waterAreaId" name="waterAreaId" value=""></input>
	
	<div style="width:690px; height:430px; overflow-y:scroll; float:left;"> <!-- 原始width：640px -->
		<table class="pop-table" id="baseDeviceInfoTb" style="width:670px;">
			<tr>
				<td class="table-left"><label for="waterAreaId"><span style="color:red">*</span>所属水管区域</label>：</td>
				<td class="table-right" colspan="3">
					<select class="pop-select" id="waterAreaId" style="display:inline-block; width:100px;" name="waterAreaId" onchange="getSubWaterAreaId(this)">
						<option value=''>--请选择水管区域--</option>
						<c:forEach var="item" items="${sysWaterAreaList}">
							<option value="${item.id}">${item.waterAreaName}</option>
						</c:forEach>
					</select> 
					<input type="hidden" id="num" name="num" value="0" /> 
					<span id="subWaterArea_0"></span>
					<input type="button" value="确定" style="background-color:#00BCD4; color:#fff; border:none; width: 60px; height: 31px; line-height:5px;" onclick="getWaterAreaCode('0')">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="areaId"><span style="color:red">*</span>所属行政区域</label>：</td>
				<td class="table-right" colspan="3">
					<select class="pop-select" id="areaId" style="display:inline-block; width:100px;" name="areaId" onchange="getSubAreaId(this)">
						<option value=''>--请选择行政区域--</option>
						<c:forEach var="item" items="${sysAreaList}">
							<option value="${item.id}">${item.areaName}</option>
						</c:forEach>
					</select> 
					<input type="hidden" id="areaNum" name="areaNum" value="0" /> 
					<span id="subArea_0"></span> 
					<input type="button" value="确定" style="background-color:#00BCD4; color:#fff; border:none; width: 60px; height: 31px; line-height:5px;" onclick="getAreaCode('0')">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="deviceWaterAreaCode"><span style="color:red">*</span>机井水管码</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="deviceWaterAreaCode" name="deviceWaterAreaCode" maxlength="50">
				</td>
				<td class="table-left"><label for="deviceAreaCode"><span style="color:red">*</span>机井行政码</label>：</td> 
				<td class="table-right">
					<input class="pop-input-common" type="text" id="deviceAreaCode" name="deviceAreaCode" maxlength="32">
				</td>
			</tr>
			<tr style="border: 1px soild red;">
				<td class="table-left"><label for="deviceCode"><span style="color:red">*</span>机井编码</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="deviceCode" name="deviceCode" maxlength="32" onblur="uniqueDevCode()">
					<span id="msgCodeInfo"></span>
				</td>
				<td class="table-left"><label for="siteType"><span style="color:red">*</span>站点类型</label>：</td>
				<td class="table-right">
					<select class="pop-select" id="siteType" name="siteType">
						<option>--请选择--</option>
						<option value="0">地下水计量</option>
						<option value="1">管道计量</option>
					</select>
				</td>
				<!-- <td class="table-left"><label for="devicePlate">机井号牌</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="devicePlate" name="devicePlate" maxlength="50">
				</td> -->
			</tr>
			<tr>
				<td class="table-left"><label for="deviceName"><span style="color:red">*</span>机井名称</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="deviceName" name="deviceName" maxlength="64">
				</td>
				<td class="table-left"><label for="pipeDiameter">管道口径</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="pipeDiameter" name="pipeDiameter" maxlength="10">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="deviceModel"><span style="color:red">*</span>设备型号</label>：</td>
				<td class="table-right">
					<select class="pop-select" id="deviceModel" name="deviceModel">
						<c:forEach var="item" items="${sysDeviceModelList}">
							<option value="${item.deviceModel}">${item.deviceModel}</option>
						</c:forEach>
					</select>
				</td>
				<td class="table-left"><label for="wellDiameter">井管内径(mm)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="wellDiameter" name="wellDiameter" maxlength="10">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="deviceNo">设备号</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="deviceNo" name="deviceNo" maxlength="32" onblur="uniqueDevno()">
					<span id="msgNoInfo"></span>
				</td>
				<td class="table-left"><label for="pumpMaterial">泵管材质</label>：</td>
				<td class="table-right">
					<select class="pop-select" id="pumpMaterial" name="pumpMaterial">
						<c:forEach var="item" items="${sysPumpMaterialList}">
							<option value="${item.pumpMaterial}">${item.pumpMaterial}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="cezhanID">DTU号码</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="cezhanID" name="cezhanID" maxlength="50" onblur="uniqueDTUNo()">
					<span id="msgDTUInfo"></span>
				</td>
				<td class="table-left"><label for="makeTimeStr">成井时间</label>：</td>
				<td class="table-right">
					<input id="makeTimeStr" class="Wdate" name="makeTimeStr" type="text" value="${defaultTime}" 
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="simCard"><span style="color:red">*</span>手机卡号</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="simCard" name="simCard" maxlength="20">
				</td>
				<td class="table-left"><label for="wellDepth">井深(m)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="wellDepth" name="wellDepth" maxlength="10">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="simOperator"><span style="color:red">*</span>手机卡运营商</label>：</td>
				<td class="table-right">
					<select class="pop-select"
					id="simOperator" name="simOperator">
						<c:forEach var="item" items="${sysOperatorList}">
							<option value="${item.operator}">${item.operator}</option>
						</c:forEach>
					</select>
				</td>
				<td class="table-left"><label for="groundWaterDepth">地下水埋深(m)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="groundWaterDepth" name="groundWaterDepth" maxlength="10">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="useWaterType">取水类型</label>：</td>
				<td class="table-right">
					<select class="pop-select" id="useWaterType" name="useWaterType">
						<c:forEach var="item" items="${sysUseWaterTypeList}">
							<option value="${item.useWaterType}">${item.useWaterType}</option>
						</c:forEach>
					</select>
				</td>
				<td class="table-left"><label for="waterPumpNo">水泵型号</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="waterPumpNo" name="waterPumpNo" maxlength="50">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="latitude"><span style="color:red">*</span>纬度</label>：</td>
				<td class="table-right">
					<input type="text" class="pop-input-common" id="latitude" name="latitude" maxlength="18" />
				</td>
				<td class="table-left"><label for="ratedHead">水泵额定扬程(m)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="ratedHead" name="ratedHead" maxlength="10">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="longitude"><span style="color:red">*</span>经度</label>：</td>
				<td class="table-right">
					<input type="text" class="pop-input-common" id="longitude" name="longitude" maxlength="18" />
				</td>
				<td class="table-left"><label for="ratedFlow">水泵额定流量(m³)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="ratedFlow" name="ratedFlow" maxlength="10">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="installAddress">安装地点</label>：</td>
				<td class="table-right" colspan="3">
					<input class="pop-input-common" type="text" id="installAddress" name="installAddress" style="width:300px">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="ownerName">业主姓名</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="ownerName" name="ownerName">
				</td>
				<td class="table-left"><label for="ownerTelphone">业主电话</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="ownerTelphone" name="ownerTelphone">
				</td>
			</tr>			
			<tr>
				<td class="table-left"><label for="installTimeStr">安装时间</label>：</td>
				<td class="table-right">
					<input id="installTimeStr" name="installTimeStr" class="Wdate" type="text" value="${defaultTime}"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				</td>
				<td class="table-left"><label for="ratedPower">水泵额定功率(KW)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="ratedPower" name="ratedPower" maxlength="10">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="applyStatus">应用状况</label>：</td>
				<td class="table-right">
					<select class="pop-select" id="applyStatus" name="applyStatus">
						<c:forEach var="item" items="${sysApplyStatusList}">
							<option value="${item.applyStatus}">${item.applyStatus}</option>
						</c:forEach>
					</select>
				</td>
				<td class="table-left"><label for="wellUse">水井用途分类</label>：</td>
				<td class="table-right">
					<select class="pop-select" onchange="handleChange(this.value)" id="wellUse" name="wellUse">
						<c:forEach var="item" items="${sysWellUseList}">
							<option value="${item.wellUse}">${item.wellUse}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="irrigationMode">灌溉模式</label>：</td>
				<td class="table-right">
					<select class="pop-select" id="irrigationMode" name="irrigationMode">
						<c:forEach var="item" items="${sysIrrigationModeList}">
							<option value="${item.irrigationMode}">${item.irrigationMode}</option>
						</c:forEach>
					</select>
				</td>

				<td class="table-left"><label for="kZArea">控制面积(亩)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="kZArea" name="kZArea" maxlength="10">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="sJArea">实际灌溉面积(亩)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="sJArea" name="sJArea" maxlength="10">
				</td>
				<td class="table-left"><label for="sJSupplyWaterPeople">实际供水人口</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="sJSupplyWaterPeople" name="sJSupplyWaterPeople" maxlength="10">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="isHandleDocument">是否办理取水许可证</label>：</td>
				<td class="table-right">
					<input type="radio" id="isHandleDocument0" name="isHandleDocument" value="0" checked="checked"><label style="float:left;">否</label>
					<input type="radio" id="isHandleDocument1" name="isHandleDocument" value="1"><label style="float:left;">是</label>
				</td>

				<td class="table-left"><label for="waterIntakeNo">取水许可证编号</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="waterIntakeNo" name="waterIntakeNo" maxlength="100">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="yearWaterSum">年许可取水(m³)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="yearWaterSum" name="yearWaterSum" maxlength="10">
				</td>
				<td class="table-left"><label for="isIndustryProcedure">是否办理工业取水手续</label>：</td>
				<td class="table-right">
					<input type="radio" id="isIndustryProcedure0" name="isIndustryProcedure" value="0" checked="checked"><label style="float:left;">否</label> 
					<input type="radio" id="isIndustryProcedure1" name="isIndustryProcedure" value="1"><label style="float:left;">是</label>
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="industryApprovedWater">年核定水量(m³)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="industryApprovedWater" name="industryApprovedWater" maxlength="100">
				</td>
				<td class="table-left"><label for="industryProductionCapacity">生产规模(万元)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="industryProductionCapacity" name="industryProductionCapacity" maxlength="10">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="industryArea">工业区面积(亩)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="industryArea" name="industryArea" maxlength="10">
				</td>
				<td class="table-left"><label for="waterMeterMeasurement">是否安装水量计量设施</label>：</td>
				<td class="table-right">
					<input type="radio" id="waterMeterMeasurement0" name="waterMeterMeasurement" value="0" checked="checked"><label style="float:left;">否</label> 
					<input type="radio" id="waterMeterMeasurement1" name="waterMeterMeasurement" value="1"><label style="float:left;">是</label>
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="waterMeterMeasurementType">水量计量设施类型</label>：</td>
				<td class="table-right">
					<select class="pop-select" id="waterMeterMeasurementType" name="waterMeterMeasurementType">
						<c:forEach var="item" items="${sysMeasureTypeList}">
							<option value="${item.measureType}">${item.measureType}</option>
						</c:forEach>
					</select>
				</td>
				<td class="table-left"><label for="meterType">水表型号</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="meterType" name="meterType" maxlength="50">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="meterCaliber">水表口径(mm)</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="meterCaliber" name="meterCaliber" maxlength="10">
				</td>
				<td class="table-left"><label for="meterSerialNo">水表编号</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="meterSerialNo" name="meterSerialNo" maxlength="50">
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="meterGuard">有无水表防护罩</label>：</td>
				<td class="table-right">
					<input type="radio" id="meterGuard0" name="meterGuard" value="0" checked="checked"><label style="float:left;">无</label> 
					<input type="radio" id="meterGuard1" name="meterGuard" value="1"><label style="float:left;">有</label>
				</td>
				<td class="table-left"><label for="landformType">所在地貌类型</label>：</td>
				<td class="table-right">
					<select class="pop-select" id="landformType" name="landformType">
						<c:forEach var="item" items="${sysLandformTypeList}">
							<option value="${item.landformType}">${item.landformType}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="waterArea">水资源区域</label>：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="waterArea" name="waterArea" maxlength="50">
				</td>
				<td class="table-left"><label for="irrigationAreaType">所在灌区类型</label>：</td>
				<td class="table-right">
					<select class="pop-select" id="irrigationAreaType" name="irrigationAreaType">
						<c:forEach var="item" items="${sysIrrigationAreaTypeList}">
							<option value="${item.irrigationAreaType}">${item.irrigationAreaType}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="table-left"><span style="color:red">*</span>机井安装前照片：</td>
				<td class="table-right">
					<input class="pop-input-common" type="file" id="photoBefores" name="photoBefores" />
				</td>
				<td class="table-left"><span style="color:red"></span>机井安装后照片：</td>
				<td class="table-right">
					<input class="pop-input-common" type="file" id="photoAfters" /><!-- name="photoAfters" --> 
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="note">备注</label>：</td>
				<td class="table-right" colspan="3">
					<textarea rows="5" cols="30" class="pop-textarea" id="note" name="note" maxlength="100"></textarea>
				</td>
			</tr>
		</table>
	</div>
</form>

<div id="mapContainer" style="float:right; height:365px; width:450px; border: 1px solid #dcdddd; position: relative;">
	<input type="hidden" id="localLng"> <input type="hidden" id="localLat"> <input type="hidden" id="localAddress">
	<!-- 关键字查询 -->
	<div id="searchArea" style="z-index:901;top:0px;min-height:55px;min-width:385px;">
		<b>请输入关键字：</b> 
		<input type="text" id="keyword" name="keyword" value="" onkeydown='keydown(event)' style="width: 60%;" /> 
		<input type="button" value="查 询" onclick="keywordSearch()">
		<div id="result1" name="result1"></div>
	</div>
</div>

<div>
	<p style="color:#B44">&nbsp;&nbsp;(请在地图选择安装地点)</p>
</div>

<script type="text/javascript" src="${ctx}/content/skin/adapters/amap/ZMap.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/amap/dialogMap.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//ie下需要设置div为固定高，否则不显示
		 if(isIE()){ 
			$("#mapContainer").css("height","365px");
		 }
		handleChange($("#wellUse").val());
	});
</script>