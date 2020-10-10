<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>远程充值列表</title>
		<style>
			.distPay{cursor: pointer; }
			.distPay:HOVER {cursor: pointer;margin-top: 7px;margin-left: 1px;}
		</style>
</head>
<body>
	<div style="margin-top: 50px;left:15px; top:10px;" class="right_user">
		<table class='list-table'>
			<thead>
				<tr>
					<th>机井编码</th>
					<th>机井名称</th>
					<th>已用水量<br>(m³)</th>
					<th>剩余水量<br>(m³)</th>
					<th>网络状态</th>
					<th>水泵状态</th>
					<th>用水类型</th>
					<th>最后一次通讯时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty distanceList}">
					<tr>
						<td colspan="9">没有相关数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty distanceList}">
					<c:forEach var="item" items="${distanceList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td>${item.deviceCode}</td>
							<td>${item.deviceName}</td>
							<td>${empty item.useWater ? "--" : item.useWater}</td>
							<td>
								<c:if test="${item.deviceType==1}">
									${empty item.remainWater ? "--" : item.remainWater}
								</c:if>
								<c:if test="${item.deviceType==2}">
									${empty item.leftWt ? "--" : item.leftWt}
								</c:if>
							</td>
							<td>
								<c:choose>
									<c:when test="${item.netStatek==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>离线</c:when>
									<c:when test="${item.netStatek==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>在线</c:when>
									<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img></c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${item.pumpState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>关泵</c:when>
									<c:when test="${item.pumpState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>开泵</c:when>
									<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img>无数据</c:otherwise>
								</c:choose>
							</td>
							<td>${item.wellUse}</td>
							<td>
								<c:if test="${item.commTime==null}">--</c:if>
								<c:if test="${item.commTime!=null}">
									<fmt:formatDate value="${item.commTime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</c:if>
							</td>
							<td> 
								<div class="distPay" style="height: 100%;" onclick="distanPay('${item.id}','${item.deviceCode}','${item.deviceName}','${item.netStatek}','${item.pumpState}','${item.wellUse}','${item.sJArea}','${item.sJSupplyWaterPeople}','${item.industryApprovedWater}')">
									<img title="远程充值" src="${ctx}/content/skin/css/images/pay.png" style="margin-top: 6px;">
								</div>
	                        </td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="list-page">
			<div id="pagination"></div>
		</div>
		<input type="hidden" value="${pagingBean.pageNo}" id="pageNos" />
	</div>
</body>
<script type="text/javascript">
	var totalPage = ${pagingBean.pageNum};
	var totalRecords = ${pagingBean.totalItems};
	var pageNo = ${pagingBean.pageNo};
	if(!pageNo){
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
		click : function(n){
			//分页执行方法
			changeDataPage(n);
			return false;
		}
	});
</script>
</html>