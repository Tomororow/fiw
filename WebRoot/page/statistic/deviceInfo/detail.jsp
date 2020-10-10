<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<div style="width:100%; height:430px; overflow-y:scroll;">
	<table border="0" cellspacing="1" cellpadding="3" class="list-table" id="baseDeviceInfoTb" style="width:100%">
		<tr>
			<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">所属水管区域</label>：</td>
			<td class="table-left" style="width:200px;">
				<c:forEach var="item" items="${sysWaterAreaList}">
					<c:if test="${baseDeviceInfo.waterAreaId == item.id}">
						${item.waterAreaName}
					</c:if>
				</c:forEach>
			</td>
			<td class="table-left"><label style="font-weight: bolder;">所属行政区域</label>：</td>
			<td class="table-right">
				<c:forEach var="item" items="${sysAreaList}">
					<c:if test="${baseDeviceInfo.areaId == item.id}">
						${item.areaName}
					</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">机井水管码</label>：</td>
			<td class="table-right">${baseDeviceInfo.deviceWaterAreaCode}</td>
			<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">机井行政码</label>：</td>
			<td class="table-right">${baseDeviceInfo.deviceAreaCode}</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">机井编码</label>：</td>
			<td class="table-right">${baseDeviceInfo.deviceCode}</td>
			<td class="table-left"><label style="font-weight: bolder;">机井号牌</label>：</td>
			<td class="table-right">${baseDeviceExpandInfo.devicePlate}</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">机井名称</label>：</td>
			<td class="table-right">${baseDeviceInfo.deviceName}</td>
			<td class="table-left"><label style="font-weight: bolder;">管道口径</label>：</td>
			<td class="table-right">${baseDeviceExpandInfo.pipeDiameter}</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">设备型号</label>：</td>
			<td class="table-right">
				<c:forEach var="item" items="${sysDeviceModelList}">
					<c:if test="${baseDeviceInfo.deviceModel == item.deviceModel}">
		 				${item.deviceModel}
		 			</c:if>
				</c:forEach></td>

			<td class="table-left"><label style="font-weight: bolder;">井管内径(mm)</label>：</td>
			<td class="table-right">${baseDeviceExpandInfo.wellDiameter}</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">设备号</label>：</td>
			<td class="table-right">${baseDeviceInfo.deviceNo}</td>
			<td class="table-left"><label style="font-weight: bolder;">泵管材质</label>：</td>
			<td class="table-right">
				<c:forEach var="item" items="${sysPumpMaterialList}">
					<c:if test="${baseDeviceExpandInfo.pumpMaterial == item.pumpMaterial}">
			 			${item.pumpMaterial}
			 		</c:if>
				</c:forEach></td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">DTU号码</label>：</td>
			<td class="table-right">${baseDeviceInfo.cezhanID}</td>

			<td class="table-left"><label style="font-weight: bolder;">成井时间</label>：</td>
			<td class="table-right">
				<fmt:formatDate value="${baseDeviceExpandInfo.makeTime}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">手机卡号</label>：</td>
			<td class="table-right">${baseDeviceInfo.simCard}</td>

			<td class="table-left"><label style="font-weight: bolder;">井深(m)</label>：</td>
			<td class="table-right">${baseDeviceExpandInfo.wellDepth}</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">手机卡运营商</label>：
			</td>
			<td class="table-right">
				<c:forEach var="item" items="${sysOperatorList}">
					<c:if test="${baseDeviceInfo.simOperator == item.operator}">
			 			${item.operator}
			 		</c:if>
				</c:forEach>
			</td>
			<td class="table-left"><label style="font-weight: bolder;">地下水埋深(m)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.groundWaterDepth}
			</td>
		</tr>
		<tr>

			<td class="table-left"><label style="font-weight: bolder;">取水类型</label>：</td>
			<td class="table-right">
				<c:forEach var="item" items="${sysUseWaterTypeList}">
					<c:if test="${baseDeviceExpandInfo.useWaterType == item.useWaterType}">
			 			${item.useWaterType}
			 		</c:if>
				</c:forEach></td>
			<td class="table-left"><label style="font-weight: bolder;">水泵型号</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.waterPumpNo}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">纬度</label>：</td>
			<td class="table-right">
				${baseDeviceInfo.latitude}
			</td>

			<td class="table-left"><label style="font-weight: bolder;">水泵额定扬程(m)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.ratedHead}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">经度</label>：</td>
			<td class="table-right">
				${baseDeviceInfo.longitude}
			</td>

			<td class="table-left"><label style="font-weight: bolder;">水泵额定流量(m³)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.ratedFlow}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">安装时间</label>：</td>
			<td class="table-right">
				<fmt:formatDate value="${baseDeviceInfo.installTime}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td class="table-left"><label style="font-weight: bolder;">水泵额定功率(KW)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.ratedPower}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">应用状况</label>：</td>
			<td class="table-right">
				<c:forEach var="item" items="${sysApplyStatusList}">
					<c:if test="${baseDeviceExpandInfo.applyStatus == item.applyStatus}">
			 			${item.applyStatus}
			 		</c:if>
				</c:forEach>
			</td>
			<td class="table-left"><label style="font-weight: bolder;">水井用途分类</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.wellUse}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">灌溉模式</label>：</td>
			<td class="table-right">
				<c:forEach var="item" items="${sysIrrigationModeList}">
					<c:if test="${baseDeviceExpandInfo.irrigationMode == item.irrigationMode}">
			 			${item.irrigationMode}
			 		</c:if>
				</c:forEach>
			</td>
			<td class="table-left"><label style="font-weight: bolder;">控制面积(亩)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.kZArea}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">实际灌溉面积(亩)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.sJArea}
			</td>

			<td class="table-left"><label style="font-weight: bolder;">实际供水人口</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.sJSupplyWaterPeople}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">是否办理取水许可证</label>：</td>
			<td class="table-right">
				<c:if test="${baseDeviceExpandInfo.isHandleDocument==0}">
					否
				</c:if> 
				<c:if test="${baseDeviceExpandInfo.isHandleDocument==1}">
					是
				</c:if>
			</td>
			<td class="table-left"><label style="font-weight: bolder;">取水许可证编号</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.waterIntakeNo}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">年许可取水(m³)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.yearWaterSum}
			</td>

			<td class="table-left"><label style="font-weight: bolder;">是否办理工业取水手续</label>：</td>
			<td class="table-right">
				<c:if test="${baseDeviceExpandInfo.isIndustryProcedure==0}">
					否
				</c:if> 
				<c:if test="${baseDeviceExpandInfo.isIndustryProcedure==1}">
					是
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">年核定水量(m³)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.industryApprovedWater}
			</td>

			<td class="table-left"><label style="font-weight: bolder;">生产规模(万元)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.industryProductionCapacity}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">工业区面积(亩)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.industryArea}
			</td>

			<td class="table-left"><label style="font-weight: bolder;">是否安装水量计量设施</label>：</td>
			<td class="table-right">
				<c:if test="${baseDeviceExpandInfo.waterMeterMeasurement==0}">
					否
				</c:if> 
				<c:if test="${baseDeviceExpandInfo.waterMeterMeasurement==1}">
					是
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">水量计量设施类型</label>：</td>
			<td class="table-right">
				<c:forEach var="item" items="${sysMeasureTypeList}">
					<c:if test="${baseDeviceExpandInfo.waterMeterMeasurementType == item.measureType}">
			 			${item.measureType}
			 		</c:if>
				</c:forEach>
			</td>
			<td class="table-left"><label style="font-weight: bolder;">水表型号</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.meterType}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">水表口径(mm)</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.meterCaliber}
			</td>

			<td class="table-left"><label style="font-weight: bolder;">水表编号</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.meterSerialNo}
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">有无水表防护罩</label>：</td>
			<td class="table-right">
				<c:if test="${baseDeviceExpandInfo.meterGuard==0}">
					无
				</c:if> 
				<c:if test="${baseDeviceExpandInfo.meterGuard==1}">
					有
				</c:if>
			</td>

			<td class="table-left"><label style="font-weight: bolder;">所在地貌类型区</label>：</td>
			<td class="table-right">
				<c:forEach var="item" items="${sysLandformTypeList}">
					<c:if test="${baseDeviceExpandInfo.landformType == item.landformType}">
						${item.landformType}
					</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">水资源区域</label>：</td>
			<td class="table-right">
				${baseDeviceExpandInfo.waterArea}
			</td>
			<td class="table-left"><label style="font-weight: bolder;">所在灌区类型</label>：</td>
			<td class="table-right">
				<c:forEach var="item" items="${sysIrrigationAreaTypeList}">
					<c:if test="${baseDeviceExpandInfo.irrigationAreaType == item.irrigationAreaType}">
			 			${item.irrigationAreaType}
			 		</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">备注</label>：</td>
			<td class="table-right" colspan="3">
				${baseDeviceExpandInfo.note}
			</td>
		</tr>
	</table>
</div>
