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
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>机井名称</th>
				<th>机井编码</th>
				<th>机井负责人</th>
				<th>负责人电话</th>
				<th>告警类型</th>
				<th>告警详细信息</th>
				<th>告警状态</th>
				<th>告警时间</th>
				<th>短信预警</th>
				<th>处理人员</th>
				<th>处理时间</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty warnList}">
				<tr>
					<td colspan="11">没有相关数据</td>
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
							<c:if test="${item.warnState==1}">
		                        <img id="handleImg" alt=""  src="${ctx}/content/skin/css/images/online.png">
		                        <span>已处理</span>            
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
						<td>${item.warnHandler}</td>
						<td><fmt:formatDate value="${item.warnHandleTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
	$(".list-table tbody tr td ul li.data-operate").each(function () {
		   $(this).click(function () {
	           if ($(this).children("div.operate-batch").is(":hidden")) {
	           	// 先把同胞隐藏在显示
	           	$(".list-table tbody tr td ul li.data-operate").children("div.operate-batch").hide();
	               $(this).children("div.operate-batch").show();
	           } else {
	               $(this).children("div.operate-batch").hide();
	           }
	        });
	        $(this).children("div.operate-batch").bind("mouseleave", function () {
	            $(this).hide();
	        });
	 });
</script>
