<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<div id="grdtTableDiv" style="margin:0px 5px 5px 5px">
	<input type="hidden" id="hidden_stcd" value="${stcd}">
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>测站名称 </th>
				<th>采集时间 </th>
				<th>电源电压(V)</th>
				<th>水位(m) </th>
				<th>每秒流量(m3/s) </th>
				<th>每小时流量(m3/h) </th>
				<th>累计流量(m3) </th>
				<th>信号强度</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(detList)==0}">
					<tr height="40">
						<td colspan="8">
							<font color="#a8a8a8">
								<label style="float:left;padding-left:15px">暂无数据</label></font>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${detList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td>${item.stnm}</td>
							<td><c:if test="${item.tm==null}">--</c:if><c:if test="${item.tm!=null}"><fmt:formatDate value="${item.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></c:if></td>
							<td><c:if test="${item.voltage==null}">--</c:if><c:if test="${item.voltage!=null}"><fmt:formatNumber value="${item.voltage}" maxFractionDigits="2"/></c:if></td>
							<td><c:if test="${item.water==null}">--</c:if><c:if test="${item.water!=null}"><fmt:formatNumber value="${item.water}" maxFractionDigits="3"/></c:if></td>
							<td><c:if test="${item.flowratepers==null}">--</c:if><c:if test="${item.flowratepers!=null}"><fmt:formatNumber value="${item.flowratepers}" maxFractionDigits="3"/></c:if></td>
							<td><c:if test="${item.flowrateperh==null}">--</c:if><c:if test="${item.flowrateperh!=null}"><fmt:formatNumber value="${item.flowrateperh}" maxFractionDigits="3"/></c:if></td>
							<td><c:if test="${item.flowrateadd==null}">--</c:if><c:if test="${item.flowrateadd!=null}"><fmt:formatNumber value="${item.flowrateadd}" maxFractionDigits="3"/></c:if></td>
							<td><c:if test="${item.signalinten==null}">--</c:if><c:if test="${item.signalinten!=null}"><fmt:formatNumber value="${item.signalinten}" maxFractionDigits="0"/></c:if></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<div class="list-page">
		<div id="dtPage"></div>
	</div>
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
			pagerid : "dtPage",
			//当前页
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			mode : 'click',
			click : function(n) {
				//分页执行方法
				//this.selectPage(n);
				changeDtPage(n);
				return false;
			}
		});
	});
</script>