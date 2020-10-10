<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/core/style.css">
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/zingchart.min.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/zingchart.jquery.js"></script> 
<div class="right_user" style="left:5px;top:40px">
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead >
			<tr>
					<td>机井名称</td>
					<td>机井编码</td>
				<c:if test="${disn=='0'}">
					<td>异常类型</td>
					<td>异常状态</td>
					<td>上报时间</td>
				</c:if>
				<c:if test="${disn=='1'}">
					<td>跳闸类型</td>
					<td>跳闸时间</td>
					<td>上报时间</td>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty unusualList}">
				<tr>
					<td colspan="5">没有相关数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty unusualList}">
				<c:forEach var="item" items="${unusualList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.devicename}</td>
						<td>${item.devicecode}</td>
						<c:if test="${disn=='0'}">
							<td>${item.abnormalname == null || item.abnormalname == '' ? '--' : item.abnormalname}</td>
							<td>
								<c:if test="${item.abnormalstate==0}">正常</c:if>
								<c:if test="${item.abnormalstate==1}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img><span style="color:red;">异常</span></c:if>
							</td>
							<td>
								<c:if test="${item.uptime==null || item.uptime == ''}">--</c:if>
								<c:if test="${item.uptime!=null|| item.uptime != ''}">
									<fmt:formatDate value="${item.uptime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</c:if>
							</td>
						</c:if>
						<c:if test="${disn=='1'}">
							<td>${item.tripsname == null || item.tripsname == '' ? '--' : item.tripsname}</td>
							<td>
								<c:if test="${item.tripstime==null || item.tripstime == ''}">--</c:if>
								<c:if test="${item.tripstime!=null|| item.tripstime != ''}">
									<fmt:formatDate value="${item.tripstime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</c:if>
							</td>
							<td>
								<c:if test="${item.uptime==null || item.uptime == ''}">--</c:if>
								<c:if test="${item.uptime!=null|| item.uptime != ''}">
									<fmt:formatDate value="${item.uptime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</c:if>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>