<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<div class="right_user" style="left:15px;top:50px">
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>卡物理地址</th>
				<th>管理卡卡号</th>
				<th>发卡人姓名</th>
				<th>发卡人电话</th>
				<th>发卡日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty manageCardList}">
				<tr>
					<td colspan="6">没有相关数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty manageCardList}">
				<c:forEach var="item" items="${manageCardList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.cardSerialNo}</td>
						<td>${item.cardCode}</td>
						<td>${item.ownerName == null || item.ownerName == '' ? '--' : item.ownerName}</td>
						<td>${item.ownerTelphone == null || item.ownerTelphone == '' ? '--' : item.ownerTelphone}</td>
						<td>
							<c:if test="${item.createTime == null || item.createTime == ''}">
								--
							</c:if>
							<c:if test="${item.createTime != null || item.createTime != ''}">
								<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</c:if>
						</td>
						<td width="30">
                            <ul style="width:60px; padding-left:20px;">
                                <li class="del" title="删除" onclick="delManageCardInfo('${item.id}')"></li>
                            </ul>
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
				changeManageCardPage(n);
				return false;
			}
		});
	});
</script>