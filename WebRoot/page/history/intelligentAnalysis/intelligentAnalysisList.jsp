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
<div class="right_user" style="left:15px;top:50px">
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>机井编码</th>
				<th>机井名称</th>
				<th>预警信息</th>
				<th>维修技术员</th>
				<th>技术员电话</th>
				<th>报修开始时间</th>
				<th>故障解决时间</th>
				<th>预计所用工时</th>
				<th>处理状态</th>
				<th>预警时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty intelligentAnalysisList}">
				<tr>
					<td colspan="11">没有相关数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty intelligentAnalysisList}">
				<c:forEach var="item" items="${intelligentAnalysisList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.deviceCode}</td>
						<td>${item.deviceName}</td>
						<td>${item.intelligentException==""?"--":item.intelligentException}</td>
						<td>${item.serviceMan==null?"--":item.serviceMan}</td>
						<td>${item.phone==null?"--":item.phone}</td>
						<td>
							<c:if test="${item.fixTime == null || item.fixTime == ''}">
								--
							</c:if>
							<c:if test="${item.fixTime != null || item.fixTime != ''}">
								<fmt:formatDate value="${item.fixTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</c:if>
						</td>
						<td>
							<c:if test="${item.dealTime == null || item.dealTime == ''}">
								--
							</c:if>
							<c:if test="${item.dealTime != null || item.dealTime != ''}">
								<fmt:formatDate value="${item.dealTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</c:if>
						</td>
						<td>${item.handleTime==null?"--":item.handleTime}</td>
						<td>${item.dealResult==0 ? "已解决" : item.dealResult==1 ? "未解决" : "解决中"}</td>
						<td>
							<c:if test="${item.createTime == null || item.createTime == ''}">
								--
							</c:if>
							<c:if test="${item.createTime != null || item.createTime != ''}">
								<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</c:if>
						</td>
						<td style="width:80px;">
							<c:if test="${item.dealResult==1}">
								<a style="cursor: pointer; font-size: 14px;" onclick="dealInfo('${item.id}','${item.deviceName}')">处理</a>
							</c:if>
							<c:if test="${item.dealResult==2}">
								<a style="cursor: pointer; font-size: 14px;" onclick="repairFeedBack('${item.id}','${item.deviceName}')">反馈</a>
							</c:if>
							<c:if test="${item.dealResult==0}">
								<a style="cursor: pointer; font-size: 14px;" onclick="detailAuditingInfo('${item.id}')">详情</a>
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
				changeIntelligentAnalysisPage(n);
				return false;
			}
		});
	});
	
	
	$(".list-table tbody tr td ul li.data-operate").each(
		function() {
			$(this).click(
				function() {
					if ($(this).children("div.operate-batch").is(":hidden")) {
						// 先把同胞隐藏在显示
						$(".list-table tbody tr td ul li.data-operate").children("div.operate-batch").hide();
						$(this).children("div.operate-batch").show();
					} else {
						$(this).children("div.operate-batch").hide();
					}
				});
			$(this).children("div.operate-batch").bind("mouseleave",
				function() {
					$(this).hide();
				}
			);
		}
	);
</script>