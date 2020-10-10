<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>地图实时数据列表</title>
</head>
<body>
	<table cellspacing='0' cellpadding='3' style="width:100%;" class='list-table'>
		<thead>
			<tr style="height:0px;">
				<th>机井编码</th>
				<th>机井名称</th>
				<th>采集时间</th>
				<th>已用水量<br>(m³)</th>
				<th>瞬时流速<br>(m/s)</th>
				<th>剩余水量<br>(m³)</th>
				<th>累计用水量<br>(m³)</th>
				<th>网络状态</th>
				<th>水泵状态</th>
				<th>A相电压<br>(V)</th>
				<th>B相电压<br>(V)</th>
				<th>C相电压<br>(V)</th>
				<th>A相电流<br>(A)</th>
				<th>B相电流<br>(A)</th>
				<th>C相电流<br>(A)</th>
			</tr>
		</thead>
		<tbody id="list-content">
		<c:if test="${empty baseDeviceInfoList}">
		<tr height="40">
			<td colspan="14">没有相关数据</td>
		</tr>
		</c:if>
		<c:if test="${!empty baseDeviceInfoList}">
		<c:forEach var="item" items="${baseDeviceInfoList}" varStatus="vs">
			<tr class="${vs.index%2==0?'singular':'double'}">
				<td>${item.deviceCode}</td>
				<td>${item.deviceName}</td>
				<td>
					<c:if test="${item.deviceType==1}">
						<c:if test="${item.collectTimek==null}">--</c:if>
						<c:if test="${item.collectTimek!=null}">
							<fmt:formatDate value="${item.collectTimek}" pattern="yyyy-MM-dd HH:mm:ss" />
						</c:if>
					</c:if>
					<c:if test="${item.deviceType==2}">
						<c:if test="${item.collectTimeq==null}">--</c:if>
						<c:if test="${item.collectTimeq!=null}">
							<fmt:formatDate value="${item.collectTimeq}" pattern="yyyy-MM-dd HH:mm:ss" />
						</c:if>
					</c:if>
				</td>
				<td>
					<c:if test="${item.deviceType==1}">
						${empty item.useWater ? "--" : item.useWater}
					</c:if>
					<c:if test="${item.deviceType==2}">
						${empty item.useWt ? "--" : item.useWt}
					</c:if>
				</td>
				<td>
					<c:if test="${empty item.instantSpeed}">
						--
					</c:if>
					<c:if test="${!empty item.instantSpeed }">
						<fmt:formatNumber type="number" value="${item.instantSpeed/1000}" pattern="0.00" maxFractionDigits="2"/>
					</c:if>
				</td>
				<td>
					<c:if test="${item.deviceType==1}">
						${empty item.remainWater ? "--" : item.remainWater}
					</c:if>
					<c:if test="${item.deviceType==2}">
						${empty item.leftWt ? "--" : item.leftWt}
					</c:if>
				</td>
				<td>
					${empty item.totalWater ? "--" : item.totalWater}
				</td>
				<td>
					<c:if test="${item.deviceType==1}">
						<c:choose>
							<c:when test="${item.netStatek==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>离线</c:when>
							<c:when test="${item.netStatek==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>在线</c:when>
							<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img></c:otherwise>
					   	</c:choose>
					</c:if>
				   	<c:if test="${item.deviceType==2}">
				   		<c:choose>
							<c:when test="${item.netStateq==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>离线</c:when>
							<c:when test="${item.netStateq==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>在线</c:when>
							<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img></c:otherwise>
					   	</c:choose>
				   	</c:if>
				</td>
				<td>
					<c:if test="${item.deviceType==1}">
						<c:choose>
							<c:when test="${item.pumpState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>关泵</c:when>
							<c:when test="${item.pumpState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>开泵</c:when>
							<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img>无数据</c:otherwise>
					   	</c:choose>
					</c:if>
				   	<c:if test="${item.deviceType==2}">
						<c:choose>
							<c:when test="${item.openState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>关泵</c:when>
							<c:when test="${item.openState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>开泵</c:when>
							<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img>无数据</c:otherwise>
					   	</c:choose>
					</c:if>
				</td>
				<td>
					<fmt:formatNumber maxFractionDigits="0" value="${empty item.aPhaseVoltage ? '--' : item.aPhaseVoltage}"/>V
				</td>
				<td>
					<fmt:formatNumber maxFractionDigits="0" value="${empty item.bPhaseVoltage ? '--' : item.bPhaseVoltage}"/>V
				</td>
				<td>
					<fmt:formatNumber maxFractionDigits="0" value="${empty item.cPhaseVoltage ? '--' : item.cPhaseVoltage}"/>V
				</td>
				<td>
					<fmt:formatNumber maxFractionDigits="0" value="${empty item.aPhaseCurrent ? '--' : item.aPhaseCurrent}"/>A
				</td>
				<td>
					<fmt:formatNumber maxFractionDigits="0" value="${empty item.bPhaseCurrent ? '--' : item.bPhaseCurrent}"/>A
				</td>
				<td>
					<fmt:formatNumber maxFractionDigits="0" value="${empty item.cPhaseCurrent ? '--' : item.cPhaseCurrent}"/>A
				</td>
			</tr>
		</c:forEach>
		</c:if>
		</tbody>
	</table>
	<div class="list-page">
		<div id="pagination"></div>
	</div>

	<script type="text/javascript">
		$(function() {
			var totalPage = ${pagingBean.pageNum};
			var totalRecords = ${pagingBean.totalItems};
			var pageNo = ${pagingBean.pageNo};
			if (!pageNo) {
				pageNo = 1;
			}
			//生成分页
			pagination.generPageHtml({
				//填充的id
				pagerid : "pagination",
				//当前页
				pno : pageNo,
				//总页码
				total : totalPage,
				//总数据条数
				totalRecords : totalRecords,
				mode : 'click',
				click : function(n) {
					//分页执行方法
					changeMapDataPage(n);
					return false;
				}
			});
		});
	</script>
	
</body>
</html>