<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
	#handleImg:HOVER {cursor: pointer;}
</style>
<input type="hidden" id="warnList" value="${fn:length(warnList)}">
<div class="right_user" style="left:15px;top:50px">
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<!-- <th>序号</th> -->
				<th>机井名称</th>
				<th>机井编码</th>
				<th>机井负责人</th>
				<th>负责人电话</th>
				<th>告警类型</th>
				<th>告警详细信息</th>
				<th>告警状态</th>
				<th>告警时间</th>
				<th>短信预警</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty warnList}">
				<tr>
					<td colspan="10">没有相关数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty warnList}">
				<c:forEach var="item" items="${warnList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<%-- <td>${(vs.count+pageCurrent*pageSize)-pageSize}</td> --%>
						<td>${item.deviceName}</td>
						<td>${item.deviceCode}</td>
						<td>${item.ownerName==''?'--':item.ownerName}</td>
						<td>${item.ownerTelphone==''?'--':item.ownerTelphone}</td>
						 <td>
		                   <span style="color: red">${item.wabnormaltype}</span>   
						</td>
						<td>${item.warnDetail}</td>
						<td>
							<c:if test="${item.warnState==0}">
		                        <img id="handleImg" alt=""  src="${ctx}/content/skin/css/images/icon_warnpub.png">
		                        <span style="color: red">正在预警...</span>            
		                    </c:if>
						</td>
						<td><fmt:formatDate value="${item.warnTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<c:if test="${item.backupOne eq '0'}">
		                        <img id="handleImg" alt=""  src="${ctx}/content/skin/css/images/icon_warnpub.png">
		                        <span style="color: red">未发送</span>            
		                    </c:if>
		                    <c:if test="${item.backupOne eq '1'}">
		                        <img id="handleImg" alt=""  src="${ctx}/content/skin/css/images/online.png">
		                        <span style="color: #000">已发送</span>            
		                    </c:if>
						</td>
						<td>
							<img id="handleImg" onclick="handleWarning('${item.id}')" alt=""  src="${ctx}/content/skin/css/images/botton-list-operate.png" title="关闭预警">
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
						//分页执行方fa//
						changeDevicePage(n);
						return false;
					}
				});
	}); 
</script>
