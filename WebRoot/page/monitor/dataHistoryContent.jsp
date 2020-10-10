<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/core/style.css">
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/zingchart.min.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/zingchart.jquery.js"></script> 

<div class="right_user" style="left:5px;top:40px">
	<input type="hidden" value="${id}" id="id" />
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>机井编码</th>
				<th>机井名称</th>
				<th style="width:150px;">卡号</th>
				<c:if test="${sign =='0'}">
					<th>已用水量(m³)</th>
					<th>本次用水量(m³)</th>
				</c:if>
				<c:if test="${sign =='1'}">
					<th>本次用水量</th>
					<!-- <th>本次使用金额</th>
					<th>剩余金额</th> -->
				</c:if>
				<th>剩余水量(m³)</th>
				<c:if test="${sign =='0'}">
					<th>采集时间</th>
				</c:if>
				<c:if test="${sign =='1'}">
					<th>开泵时间</th>
					<th>关泵时间</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty dataHistoryList}">
				<tr>
					<c:if test="${sign =='1'}">
						<td colspan="9">没有相关数据</td>
					</c:if>
					<c:if test="${sign =='0'}">
						<td colspan="7">没有相关数据</td>
					</c:if>
				</tr>
			</c:if>
			<c:if test="${!empty dataHistoryList}">
				<c:forEach var="item" items="${dataHistoryList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.deviceCode == null || item.deviceCode == '' ? '--' : item.deviceCode}</td>
						<td>${item.deviceName == null || item.deviceName == '' ? '--' : item.deviceName}</td>
						<td>${item.relCardCode == null || item.relCardCode == '' ? '--' : item.relCardCode}</td>
						<%-- <td>${item.relOwnerName == null || item.relOwnerName == '' ? '--' : item.relOwnerName}</td> --%>
						<c:if test="${sign =='0'}">
							<td>${item.totalwater == null || item.totalwater == '' ? '--' : item.totalwater}</td>
							<td>${item.useWt == null || item.useWt == '' ? '--' : item.useWt}</td>
						</c:if>
						<c:if test="${sign =='1'}">
							<td>${item.userdwater == null || item.userdwater == '' ? '--' : item.userdwater}</td>
							<%-- <td>${item.userdmoney == null || item.userdmoney == '' ? '--' : item.userdmoney}</td>
							<td>${item.leavemoney == null || item.leavemoney == '' ? '--' : item.leavemoney/100}</td> --%>
						</c:if>
						<td>${item.leftWt == null || item.leftWt == '' ? '--' : item.leftWt}</td>
						<c:if test="${sign =='0'}">
							<td>
								<c:if test="${item.upTime==null || item.upTime == ''}">--</c:if>
								<c:if test="${item.upTime!=null|| item.upTime != ''}">
									<fmt:formatDate value="${item.upTime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</c:if>
							</td>
						</c:if>
						<c:if test="${sign =='1'}">
							<td>
								<c:if test="${item.starttime==null || item.starttime == ''}">--</c:if>
									<c:if test="${item.starttime!=null|| item.starttime != ''}">
										<fmt:formatDate value="${item.starttime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</c:if>
							</td>
							<td>
								<c:if test="${item.stoptime==null || item.stoptime == ''}">--</c:if>
									<c:if test="${item.stoptime!=null|| item.stoptime != ''}">
										<fmt:formatDate value="${item.stoptime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</c:if>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>