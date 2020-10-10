<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>详细信息</title>
</head>

<body>
	<div class="div">
		<div class="list">
			<div class="title">基础信息</div>
			<div class="content">
				<div class="content_title">通讯状态</div>
				<div class="content_info">
			        <c:choose>
						<c:when test="${StDeviceFactor.commstates==0}">异常</c:when>
						<c:when test="${StDeviceFactor.commstates==1}">正常</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose>
				</div>
				<div class="content_title">设备状态</div>
				<div class="content_info">
				     <c:choose>
						<c:when test="${StDeviceFactor.dsfl==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>离线</c:when>
						<c:when test="${StDeviceFactor.dsfl==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>在线</c:when>
						<c:when test="${StDeviceFactor.dsfl==2}"><img src='${ctx}/content/skin/css/images/icon/icon-dormant.png'></img>休眠</c:when>
						<c:when test="${StDeviceFactor.dsfl==3}"><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img>升级中</c:when>
						<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img></c:otherwise>
					</c:choose>
				</div>
				<div class="content_title">更新时间</div>
				<div class="content_info"><c:if test="${StDeviceFactor.tm!=null}"><fmt:formatDate value="${StDeviceFactor.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></c:if></div>

			</div>
		</div>
		<div class="list">
			<div class="title">监测数据</div>
			<div class="content">
				<div class="content_title1">电源电压</div>
				<div class="content_info1"><c:if test="${StDeviceFactor.voltage==null}">无数据</c:if><c:if test="${StDeviceFactor.voltage!=null}"><fmt:formatNumber value="${StDeviceFactor.voltage}" maxFractionDigits="2"/>&nbsp;V</c:if></div>
				<div class="content_title1">水位</div>
				<div class="content_info1">${empty StDeviceFactor.water ? "无数据" : StDeviceFactor.water}${empty StDeviceFactor.water ? "" : " m"}</div>
				<div class="content_title1">每秒流量</div>
				<div class="content_info1">${empty StDeviceFactor.flowratepers ? "无数据" : StDeviceFactor.flowratepers}${empty StDeviceFactor.flowratepers ? "" : " m³/s"}</div>
				<div class="content_title1">每小时流量</div>
				<div class="content_info1">${empty StDeviceFactor.flowrateperh ? "无数据" : StDeviceFactor.flowrateperh}${empty StDeviceFactor.flowrateperh ? "" : " m³/h"}</div>
				<div class="content_title1">累计流量</div>
				<div class="content_info1">${empty StDeviceFactor.flowrateadd ? "无数据" : StDeviceFactor.flowrateadd}${empty StDeviceFactor.flowrateadd ? "" : " m³"}</div>
				<div class="content_title1">信号强度</div>
				<div class="content_info1"><c:if test="${StDeviceFactor.signalinten==null}">无数据</c:if><c:if test="${StDeviceFactor.signalinten!=null}"><fmt:formatNumber value="${StDeviceFactor.signalinten}" maxFractionDigits="0"/></c:if></div>
				<div class="content_title1">流量计状态</div>
				<div class="content_info1">
				   <c:choose>
						<c:when test="${StDeviceFactor.flowmeterstatus==0}">异常</c:when>
						<c:when test="${StDeviceFactor.flowmeterstatus==1}">正常</c:when>
						<c:otherwise>无数据</c:otherwise>
					</c:choose>
				</div>
				<div class="content_title1">柜门状态</div>
				<div class="content_info1">
				     <c:choose>
						<c:when test="${StDeviceFactor.doorstates==0}">关</c:when>
						<c:when test="${StDeviceFactor.doorstates==1}">开</c:when>
						<c:otherwise>无数据</c:otherwise>
					</c:choose>
				</div>
				<div class="content_title1">电池状态</div>
				<div class="content_info1">
		            <c:choose>
						<c:when test="${StDeviceFactor.voltage<=10.8 || StDeviceFactor.voltage>36}">异常</c:when>
						<c:when test="${StDeviceFactor.voltage>10.8 && StDeviceFactor.voltage<=36}">正常</c:when>
						<c:otherwise>--</c:otherwise>
					</c:choose>
				</div>
				<div class="content_title1">G网状态</div>
				<div class="content_info1">
		   	        <c:choose>
						<c:when test="${StDeviceFactor.signalinten<1 || StDeviceFactor.signalinten>31}">异常</c:when>
						<c:when test="${StDeviceFactor.signalinten>=1 && StDeviceFactor.signalinten<=10 }">极弱</c:when>
						<c:when test="${StDeviceFactor.signalinten>=11 && StDeviceFactor.signalinten<=20 }">良好</c:when>
						<c:when test="${StDeviceFactor.signalinten>=20 && StDeviceFactor.signalinten<=31 }">极强</c:when>
						<c:otherwise>--</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
