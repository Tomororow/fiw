<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
.popup-content{padding:0}
.el-tabs__header{margin: 0}
.table-left{width:200px;}
.table-right{width:200px;}
</style>
<div style="width:100%; height:100%;" id="detail">
<div>
	<template>
	  <el-tabs  v-model="activeName" type="card" @tab-click="handleClick">
	    <el-tab-pane label="设备运行状态" name="first">
	    	<table border="0" cellspacing="1" cellpadding="3" class="list-table" style="width:100%">
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">箱门状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.doorState==0}">关闭</c:if>
					<c:if test="${abnormalstate.doorState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">开启</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">上下卡状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.cardState==0}">下卡</c:if>
					<c:if test="${abnormalstate.cardState==1}">上卡</c:if>
				</td>
			</tr>
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">无水检测状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.notFlow==0}">正常</c:if>
					<c:if test="${abnormalstate.notFlow==1}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">被触发</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">无电检测状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.notElect==0}">正常</c:if>
					<c:if test="${abnormalstate.notElect==1}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">被触发</span></c:if>
				</td>
			</tr>
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">主控状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.manctrlStatus==0}">关闭</c:if>
					<c:if test="${abnormalstate.manctrlStatus==1}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">开启</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">备用通道</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.bckchnnaState==0}">未触发</c:if>
					<c:if test="${abnormalstate.bckchnnaState==1}">无流量检测触发</c:if>
					<c:if test="${abnormalstate.bckchnnaState==2}">水表通讯异常触发</c:if>
					<c:if test="${abnormalstate.bckchnnaState==3}">流量监测异常</c:if>
					<c:if test="${abnormalstate.bckchnnaState==4}">状态超界</c:if>
				</td>
			</tr>
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">A相运行功率</label>：</td>
				<td class="table-left" style="width:200px;">${abnormalstate.aPhasePower}</td>
				<td class="table-left"><label style="font-weight: bolder;">B相运行功率</label>：</td>
				<td class="table-right">${abnormalstate.bPhasePower}</td>
			</tr>
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">C相运行功率</label>：</td>
				<td class="table-left" style="width:200px;">${abnormalstate.cPhasePower}</td>
				<td class="table-left"><label style="font-weight: bolder;">总功率</label>：</td>
				<td class="table-right">${abnormalstate.tTPhasePower}</td>
			</tr>
			</table>
	    </el-tab-pane>
	    <el-tab-pane label="设备异常状态" name="second">
	    	<table border="0" cellspacing="1" cellpadding="3" class="list-table" style="width:100%">
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">水泵状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.yxPmmpErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxPmmpErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">水表通讯状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.yxWaterErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxWaterErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
			</tr>
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">电表通讯状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.yxElectErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxElectErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">语音模块状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.yxTtsErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxTtsErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
			</tr>
		
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">箱门状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.yxDoorOpen==false}">正常</c:if>
					<c:if test="${abnormalstate.yxDoorOpen==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">电流状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.yxElectUnbalance==false}">正常</c:if>
					<c:if test="${abnormalstate.yxElectUnbalance==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
			</tr>
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">NFC模块状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.yxNfcErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxNfcErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">A项电流状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.yxPhaseEleaErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxPhaseEleaErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
			</tr>
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">B项电流状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.yxPhaseElebErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxPhaseElebErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">C项电流状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.yxPhaseElecErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxPhaseElecErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
			</tr>
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">A项电压状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.yxPhaseVolaErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxPhaseVolaErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">B项电压状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.yxPhaseVolbErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxPhaseVolbErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
			</tr>
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">C项电压状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.yxPhaseVolcErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxPhaseVolcErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">电源电压状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.yxPowerVolErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxPowerVolErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
			</tr>
			<tr>
				<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">流量检测状态</label>：</td>
				<td class="table-left" style="width:200px;">
					<c:if test="${abnormalstate.yxTotalflowErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxTotalflowErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
				<td class="table-left"><label style="font-weight: bolder;">电量检测状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.yxTotalelectErr==false}">正常</c:if>
					<c:if test="${abnormalstate.yxTotalelectErr==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
			</tr>
			<tr>
				<td class="table-left"><label style="font-weight: bolder;">电压状态</label>：</td>
				<td class="table-right">
					<c:if test="${abnormalstate.yxVolageUnbalance==false}">正常</c:if>
					<c:if test="${abnormalstate.yxVolageUnbalance==true}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
				</td>
			</tr>
		</table>
	    </el-tab-pane>
	  </el-tabs>
	</template>
</div>
	
		<%-- <tr>
			<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">运行状态</label>：</td>
			<td class="table-right">
				<c:choose>
					<c:when test="${baseDeviceDynamicInfo.pumpState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>关机</c:when>
					<c:when test="${baseDeviceDynamicInfo.pumpState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>开机</c:when>
					<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img>无数据</c:otherwise>
				</c:choose>
			</td>
			<td class="table-left"><label style="font-weight: bolder;">网络状态</label>：</td>
			<td class="table-right">
				<c:choose>
					<c:when test="${baseDeviceDynamicInfo.netState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>离线</c:when>
					<c:when test="${baseDeviceDynamicInfo.netState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>在线</c:when>
					<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img></c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">当前用水量</label>：</td>
			<td class="table-right">
				${empty baseDeviceDynamicInfo.useWater ? "--" : baseDeviceDynamicInfo.useWater}m³
			</td>
			<td class="table-left"><label style="font-weight: bolder;">A相电压</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.aPhaseVoltage ? '--' : baseDeviceDynamicInfo.aPhaseVoltage}"/>V
			</td>
			
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">B相电压</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.bPhaseVoltage ? '--' : baseDeviceDynamicInfo.bPhaseVoltage}"/>V
			</td>
			<td class="table-left"><label style="font-weight: bolder;">C相电压</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.cPhaseVoltage ? '--' : baseDeviceDynamicInfo.cPhaseVoltage}"/>V
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">A相电流</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.aPhaseCurrent ? '--' : baseDeviceDynamicInfo.aPhaseCurrent}"/>A
			</td>
			<td class="table-left"><label style="font-weight: bolder;">B相电流</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.bPhaseCurrent ? '--' : baseDeviceDynamicInfo.bPhaseCurrent}"/>A
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">C相电流</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.cPhaseCurrent ? '--' : baseDeviceDynamicInfo.cPhaseCurrent}"/>A
			</td>
			<td class="table-left"><label style="font-weight: bolder;">A相功率</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.aPhasePower ? '--' : baseDeviceDynamicInfo.aPhasePower}"/>W
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">B相功率</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.bPhasePower ? '--' : baseDeviceDynamicInfo.bPhasePower}"/>W
			</td>
			<td class="table-left"><label style="font-weight: bolder;">C相功率</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.cPhasePower ? '--' : baseDeviceDynamicInfo.cPhasePower}"/>W
			</td>
		</tr> --%>
	
	<%-- <table border="0" cellspacing="1" cellpadding="3" class="list-table" style="width:100%">
		<tr>
			<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">瞬时流速</label>：</td>
			<td class="table-left" style="width:200px;">
				<fmt:formatNumber type="number" value="${baseDeviceDynamicInfo.instantSpeed/1000}" pattern="0.00" maxFractionDigits="2"/>m/s
			</td>
			<td class="table-left"><label style="font-weight: bolder;">累计用水量</label>：</td>
			<td class="table-right">
				<c:if test="${empty baseDeviceDynamicInfo.totalWater}">
					${empty baseDeviceDynamicInfo.totalWater ? "--" : baseDeviceDynamicInfo.leftWt}m³
				</c:if>
				<c:if test="${!empty baseDeviceDynamicInfo.totalWater}">
					<fmt:formatNumber type="number" value="${baseDeviceDynamicInfo.totalWater}" pattern="0.00" maxFractionDigits="2"/>m³
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="table-left" style="width:200px;"><label style="font-weight: bolder;">运行状态</label>：</td>
			<td class="table-right">
				<c:choose>
					<c:when test="${baseDeviceDynamicInfo.pumpState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>关机</c:when>
					<c:when test="${baseDeviceDynamicInfo.pumpState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>开机</c:when>
					<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img>无数据</c:otherwise>
				</c:choose>
			</td>
			<td class="table-left"><label style="font-weight: bolder;">网络状态</label>：</td>
			<td class="table-right">
				<c:choose>
					<c:when test="${baseDeviceDynamicInfo.netState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>离线</c:when>
					<c:when test="${baseDeviceDynamicInfo.netState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>在线</c:when>
					<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img></c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">当前用水量</label>：</td>
			<td class="table-right">
				${empty baseDeviceDynamicInfo.useWater ? "--" : baseDeviceDynamicInfo.useWater}m³
			</td>
			<td class="table-left"><label style="font-weight: bolder;">A相电压</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.aPhaseVoltage ? '--' : baseDeviceDynamicInfo.aPhaseVoltage}"/>V
			</td>
			
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">B相电压</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.bPhaseVoltage ? '--' : baseDeviceDynamicInfo.bPhaseVoltage}"/>V
			</td>
			<td class="table-left"><label style="font-weight: bolder;">C相电压</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.cPhaseVoltage ? '--' : baseDeviceDynamicInfo.cPhaseVoltage}"/>V
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">A相电流</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.aPhaseCurrent ? '--' : baseDeviceDynamicInfo.aPhaseCurrent}"/>A
			</td>
			<td class="table-left"><label style="font-weight: bolder;">B相电流</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.bPhaseCurrent ? '--' : baseDeviceDynamicInfo.bPhaseCurrent}"/>A
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">C相电流</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.cPhaseCurrent ? '--' : baseDeviceDynamicInfo.cPhaseCurrent}"/>A
			</td>
			<td class="table-left"><label style="font-weight: bolder;">A相功率</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.aPhasePower ? '--' : baseDeviceDynamicInfo.aPhasePower}"/>W
			</td>
		</tr>
		<tr>
			<td class="table-left"><label style="font-weight: bolder;">B相功率</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.bPhasePower ? '--' : baseDeviceDynamicInfo.bPhasePower}"/>W
			</td>
			<td class="table-left"><label style="font-weight: bolder;">C相功率</label>：</td>
			<td class="table-right">
				<fmt:formatNumber maxFractionDigits="0" value="${empty baseDeviceDynamicInfo.cPhasePower ? '--' : baseDeviceDynamicInfo.cPhasePower}"/>W
			</td>
		</tr>
	</table> --%>
</div>
<script type="text/javascript">
	var detail = new Vue({
		el:'#detail',
		data:{
			activeName:'first',
		},
		methods:{
			handleClick:function(tab, event){
				debugger
				var _this = this;
			},
		},
		created:function(){
			var _this = this
		}
	});
</script>
