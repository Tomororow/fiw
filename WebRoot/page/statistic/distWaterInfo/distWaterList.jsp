<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<div class="right_user" style="left:15px;top:50px">
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>配水区域</th>
				<th>配水名称</th>
				<th>配水年份</th>
				<th>配水轮次</th>
				<th>用水类型</th>
				<c:if test="${wellUse=='灌溉'||wellUse=='绿化'}">
					<th>实际灌溉面积(m³)</th>
					<th>每亩地分配水量(m³)</th>
				</c:if>
				<c:if test="${wellUse=='工业'}">
					<th>年核定水量(m³)</th>
				</c:if>
				<c:if test="${wellUse=='生活'}">
					<th>总人口(人)</th>
					<th>人均分配水量(m³)</th>
				</c:if>
				<c:if test="${wellUse=='生活'||wellUse=='灌溉'||wellUse=='绿化'}">
					<th>总配水量(m³)</th>
				</c:if>
				<th>配水价格(元/m³)</th>
				<th>配水时间</th>
				<th>操作人员</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty distWaterInfoList}">
				<tr>
					<td colspan="11">没有相关数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty distWaterInfoList}">
				<c:forEach var="item" items="${distWaterInfoList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.waterAreaName}</td>
						<td>
							<c:if test="${item.wellUse!='工业 ' && item.wellUse!='生活'}">
								<c:if test="${item.distMode==1}">实际灌溉面积配水</c:if>
								<c:if test="${item.distMode==2}">额定灌溉面积配水</c:if>
								<c:if test="${item.distMode==3}">人口配水</c:if>
							</c:if>
							<c:if test="${item.wellUse=='工业'}">按核定水量配水</c:if>
							<c:if test="${item.wellUse=='生活'}">按人口配水</c:if>
						</td>
						<td>${item.distYear}</td>
						<td>${item.distRound}</td>
						<td>
							<c:if test="${item.distType==1}">农业灌溉用水</c:if>
							<c:if test="${item.distType==2}">工业用水</c:if>
							<c:if test="${item.distType==3}">生活用水</c:if>
						</td>
						<c:if test="${wellUse=='灌溉'||wellUse=='绿化'}">
							<td>${item.sJArea}</td>
						</c:if>
						<c:if test="${wellUse=='生活'}">
							<td>${item.sJSupplyWaterPeople}</td>
						</c:if>
						<c:if test="${wellUse=='生活'||wellUse=='灌溉'||wellUse=='绿化'}">
							<td>${item.distWater}</td>
						</c:if>
						<c:if test="${wellUse=='灌溉'||wellUse=='绿化'}">
							<td>
								<fmt:formatNumber type="number" pattern="0.00" value="${item.sJArea*item.distWater}" maxFractionDigits="2"/>
							</td>
							
						</c:if>
						<c:if test="${wellUse=='生活'}">
							<td>
								<fmt:formatNumber type="number" pattern="0.00" value="${item.sJSupplyWaterPeople*item.distWater}" maxFractionDigits="2"/>
							</td>
						</c:if>
						<c:if test="${wellUse=='工业'}">
							<td>${item.industryApprovedWater}</td>
						</c:if>
						
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
		distWaterInfoList = '${distWaterInfoList}';
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