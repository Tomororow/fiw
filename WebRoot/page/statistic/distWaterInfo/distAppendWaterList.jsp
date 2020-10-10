<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<div class="right_user" style="left:15px;top:50px">
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>配水名称</th>
				<th>配水机井</th>
				<th>配水年份</th>
				<th>轮次</th>
				<th>用水类型</th>
				<th>每亩地分配水量(m³)</th>
				<th>配水价格(元/m³)</th>
				<th>配水时间</th>
				<th>操作人员</th>
				<th>是否为阶梯性追加水量</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty distAppendWaterInfoList}">
				<tr>
					<td colspan="10">没有相关数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty distAppendWaterInfoList}">
				<c:forEach var="item" items="${distAppendWaterInfoList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>
							<c:if test="${item.wellUse!='工业 ' && item.wellUse!='生活'}">
								<c:if test="${item.distMode==1}">实际灌溉面积配水</c:if>
								<c:if test="${item.distMode==2}">额定灌溉面积配水</c:if>
								<c:if test="${item.distMode==3}">人口配水</c:if>
							</c:if>
							<c:if test="${item.wellUse=='工业'}">按核定水量配水</c:if>
							<c:if test="${item.wellUse=='生活'}">按人口配水</c:if>
						</td>
						<td>${item.deviceName}</td>
						<td>${item.distYear}</td>
						<td>${item.distRound}</td>
						<td>
							<c:if test="${item.distType==1}">农业灌溉用水</c:if>
							<c:if test="${item.distType==2}">工业用水</c:if>
							<c:if test="${item.distType==3}">生活用水</c:if>
						</td>
						<td>${item.distWater}</td>
						<td>${item.distPrice}</td>
						<td>
							<c:if test="${item.createTime == null || item.createTime == ''}">
								--
							</c:if>
							<c:if test="${item.createTime != null || item.createTime != ''}">
								<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</c:if>
						</td>
						<td>${item.userName}</td>
						<td>
							<c:if test="${item.isAppendWater==0}">
								否
							</c:if>
							<c:if test="${item.isAppendWater==1}">
								是
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
					changeAppendDistWaterPage(n);
					return false;
				}
			});
		});
	</script>
</div>