<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/core/style.css">
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/zingchart.min.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/zingchart.jquery.js"></script> 

<div class="right_user" style="left:5px;top:40px">
	<input type="hidden" value="${id}" id="id" />
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<c:if test="${eign=='0'}"><!-- 历史数据查询 -->
					<th>卡号</th>
					<th>本次运行时长</th>
				<!-- 	<th>单价</th> -->
					<th>备份通道本次流量</th>
					<th>备份通道本次时长</th>
				</c:if>
				<th>用水量<br>(m³)</th>
				<!-- <th>用电量</th> -->
				<%-- <c:if test="${eign!='0'}"><!-- 遥测历史数据 -->
					<th>消费金额</th>
				</c:if> --%>
				<th>剩余水量<br>(m³)</th>
				<!-- <th>剩余金额</th> -->
				<th>上卡时间</th>
				<th>下卡时间</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty dataHistoryList}">
				<tr>
					<c:if test="${eign=='0'}">
						<td colspan="11">没有相关数据</td>
					</c:if>
					<c:if test="${eign!='0'}">
						<td colspan="7">没有相关数据</td>
					</c:if>
				</tr>
			</c:if>
			<c:if test="${!empty dataHistoryList}">
				<c:forEach var="item" items="${dataHistoryList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<c:if test="${eign=='0'}">
							<td>${item.cardcode == null || item.cardcode == '' ? '--' : item.cardcode}</td>
							<%-- <td>${item.cursec/3600}时${item.cursec%3600/60}分${item.cursec%3600/60%60}秒</td> --%>
							<td><fmt:parseNumber value="${item.cursec/3600}" integerOnly="true"/>时
							<fmt:parseNumber value="${(item.cursec%3600)/60}" integerOnly="true"/>分
							<fmt:parseNumber value="${(item.cursec%3600)%60}" integerOnly="true"/>秒</td>
							<%-- <td>${item.curprice}</td> --%>
							<td>${item.backupflow}</td>
							<td>${item.backuptime}</td>
						</c:if>
						<td>${item.useWt}</td>
						<%-- <td>${item.usePw/10}</td> --%>
						<%-- <c:if test="${eign!='0'}">
							<td>${item.useMn == null || item.useMn == '' ? '--' : item.useMn/100}</td>
						</c:if> --%>
						<td>${item.leftWt == null || item.leftWt == '' ? '--' : item.leftWt}</td>
						<%-- <td>${item.leftMn == null || item.leftMn == '' ? '--' : item.leftMn/100}</td> --%>
						<td>
							<c:if test="${item.openPumpTime==null}">--</c:if>
							<c:if test="${item.openPumpTime!=null}">
								<fmt:formatDate value="${item.openPumpTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</c:if>
						</td>
						<td>
							<c:if test="${item.stopPumpTime==null}">--</c:if>
							<c:if test="${item.stopPumpTime!=null}">
								<fmt:formatDate value="${item.stopPumpTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>