<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<div class="right_user" style="left:15px;top:50px">
<input id="waterInfoList" value="${fn:length(waterInfoList)}" hidden/>
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>机井名称</th>
				<th>机井编码</th>
				<th>用水类型</th>
				<th>已用水量</th>
				<th>剩余水量</th>
				<th>上报时间</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty waterInfoList}">
				<tr>
					<td colspan="9">没有相关数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty waterInfoList}">
				<c:forEach var="item" items="${waterInfoList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.deviceName}</td>
						<td>${item.deviceCode}</td>
						<td>${item.wellUse}</td>
						<td>${item.useWater}</td>
						<td>${item.remainWater}</td>
						<td>
							<c:if test="${item.createTime!=null}">
									<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="list-page">
		<div id="pagination"></div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
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
				changeDistWaterPage(n);
				return false;
			}
		});
	});
</script>