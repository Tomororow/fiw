<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!-- 机井预警信息列表页 -->
<style>
	.data-operate {
		background: url(${ctx}/content/skin/css/images/botton-list-operate.png)
			center center no-repeat;
	}
	
	/* 设备配置项 */
	.operate-batch {
		position: relative;
		width: 154px;
		z-index: 200;
		font-size: 12px;
		font-weight: normal;
		display: none
	}
	
	.operate-batch span {
		float: left;
		display: block;
		width: 154px;
		height: 5px;
		background: url(${ctx}/content/skin/css/images/icon-arrow-white-up.png)
			no-repeat;
	}
	
	.operate-batch dl {
		float: left;
		margin: 0;
		background-color: #ededed;
		width: 152px;
		border-left: 1px solid #c5d9df;
		border-right: 1px solid #c5d9df;
		border-bottom: 1px solid #c5d9df;
	}
	
	.operate-batch dl dt {
		float: left;
		width: 124px;
		text-align: left;
		padding: 8px 0 8px 30px;
		border-bottom: 1px solid #e6e6e6;
		background: url(${ctx}/content/skin/css/images/icon-arrow-gray-left.jpg)
			left center no-repeat;
	}
	
	.operate-one {
		margin: 30px 0 0 -110px;
	}
	
	.operate-more {
		margin-left: -120px;
	}
</style>
<input type="hidden" id="warnList" value="${fn:length(warnList)}">

<div class="right_user" style="left:15px;top:50px">
	<div style="height:20%">
		<p style="margin-bottom:1%; font-weight:bold; font-size:18px;">统计指标</p>
		<h2 style="margin-top:8px; margin-bottom:10px;">总用水量：
			<fmt:formatNumber type="number" value="${Indty}" pattern="0.00" maxFractionDigits="4"/> m³
		</h2>
	</div>
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>机井名称</th>
				<th>机井编码</th>
				<th>端口号</th>
				<th>上期累积值</th>
				<th>当前累积值</th>
				<th>累计用水量</th>
				<th>最新一次上报时间</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty Inderlist}">
				<tr>
					<td colspan="7">没有相关数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty Inderlist}">
				<c:forEach var="item" items="${Inderlist}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.devicename==null?'--':item.devicename}</td>
						<td>${item.devicecode==null?'--':item.devicecode}</td>
						<td>${item.deviceport==null?'--':item.deviceport}</td>
						<td>${item.maxPosite==null?'--':item.maxPosite}</td>
						<td>${item.minPosite==null?'--':item.minPosite}</td>
						<td>${item.positry==null?'--':item.positry}</td>
						<td>
							<c:if test="${item.createtime!=null}">
								<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
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
						changeIntelligentDealPage(n);
						//this.selectPage(n);
						return false;
					}
				});
	});

</script>
