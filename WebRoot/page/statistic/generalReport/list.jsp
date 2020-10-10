<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<style>
<!--
#grTableDiv table.list-table tbody td {
    height: 25px;
}

#grTableDiv table.list-table tbody tr.singular:hover { background-color: #ffffff; }
#grTableDiv table.list-table tbody tr.double:hover { background-color: #e6eff5; }

-->
</style>

<div class="device" id="grTableDiv">
	<input type="hidden" id="hidden_query_beginTime" value="${query_beginTimeStr}">
	<input type="hidden" id="hidden_query_endTime" value="${query_endTimeStr}">
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>测站名称 </th>
				<th colspan="2">电源电压(V)</th>
				<th colspan="2">水位(m) </th>
				<th colspan="2">每秒流量(m3/s) </th>
				<th colspan="2">每小时流量(m3/h) </th>
				<th colspan="2">累计流量(m3) </th>
				<th colspan="2">信号强度</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(detList)==0}">
					<tr height="40">
						<td colspan="14">
							<font color="#a8a8a8">
								<label style="float:left;padding-left:15px">暂无数据</label></font>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${detList}" varStatus="vs">
							<tr class="${vs.index%2==0?'singular':'double'}">
								<td rowspan="3">${item.stnm}</td>
								<td>最大值</td>
								<td>
									<c:choose>
										<c:when test="${item.voltagemax==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.voltagemax}" maxFractionDigits="2"/></c:otherwise>
									</c:choose>
								</td>
								<td>最大值</td>
								<td>
									<c:choose>
										<c:when test="${item.watermax==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.watermax}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>最大值</td>
								<td>
									<c:choose>
										<c:when test="${item.flowratepersmax==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.flowratepersmax}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>最大值</td>
								<td>
									<c:choose>
										<c:when test="${item.flowrateperhmax==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.flowrateperhmax}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>起始值</td>
								<td>
									<c:choose>
										<c:when test="${item.flowrateaddmin==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.flowrateaddmin}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>最大值</td>
								<td>
									<c:choose>
										<c:when test="${item.signalintenmax==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.signalintenmax}" maxFractionDigits="0"/></c:otherwise>
									</c:choose>
								</td>
								<td rowspan="3"><a href="javascript:void(0)" onclick="showDetail('${item.stcd}')" style="">查看详情</a></td>
							</tr>
							<tr class="${vs.index%2==0?'singular':'double'}">
								<td>最小值</td>
								<td>
									<c:choose>
										<c:when test="${item.voltagemin==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.voltagemin}" maxFractionDigits="2"/></c:otherwise>
									</c:choose>
								</td>
								<td>最小值</td>
								<td>
									<c:choose>
										<c:when test="${item.watermin==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.watermin}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>最小值</td>
								<td>
									<c:choose>
										<c:when test="${item.flowratepersmin==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.flowratepersmin}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>最小值</td>
								<td>
									<c:choose>
										<c:when test="${item.flowrateperhmin==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.flowrateperhmin}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>终止值</td>
								<td>
									<c:choose>
										<c:when test="${item.flowrateaddmax==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.flowrateaddmax}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>最小值</td>
								<td>
									<c:choose>
										<c:when test="${item.signalintenmin==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.signalintenmin}" maxFractionDigits="0"/></c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr class="${vs.index%2==0?'singular':'double'}">
								<td>平均值</td>
								<td>
									<c:choose>
										<c:when test="${item.voltageavg==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.voltageavg}" maxFractionDigits="2"/></c:otherwise>
									</c:choose>
								</td>
								<td>平均值</td>
								<td>
									<c:choose>
										<c:when test="${item.wateravg==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.wateravg}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>平均值</td>
								<td>
									<c:choose>
										<c:when test="${item.flowratepersavg==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.flowratepersavg}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>平均值</td>
								<td>
									<c:choose>
										<c:when test="${item.flowrateperhavg==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.flowrateperhavg}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>增 量</td>
								<td>
									<c:choose>
										<c:when test="${item.flowrateaddmax==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.flowrateaddmax-item.flowrateaddmin}" maxFractionDigits="3"/></c:otherwise>
									</c:choose>
								</td>
								<td>平均值</td>
								<td>
									<c:choose>
										<c:when test="${item.signalintenavg==null}">--</c:when>
										<c:otherwise><fmt:formatNumber value="${item.signalintenavg}" maxFractionDigits="0"/></c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<div class="list-page">
		<div id="pagination"></div>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		listDataCache = eval("(" + '${detListJson}' + ")");
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
				changePage(n);
				//this.selectPage(n);
				return false;
			}
		});
	});
</script>