<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>计量水费统计</title>
</head>

<body>
	<div class="right_user" style="left:15px; top:50px">
		<table cellspacing='0' cellpadding='3' class='list-table'>
			<thead>
				<tr>
                    <th>机井名称</th>
                    <th>已购水量</th>
					<th>计量水费收费金额</th>
					<th>是否缴费</th>
					<th>缴费时间</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty bwcList}">
					<tr>
						<td colspan="5">没有相关数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty bwcList}">
					<c:forEach var="item" items="${bwcList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td>${item.deviceName == null || item.deviceName == '' ? '--' : item.deviceName}</td>
							<td>${item.waterAmount == null || item.waterAmount == '' ? '--' : item.waterAmount}</td>
							<td>${item.chargeAmount == null || item.chargeAmount == '' ? '--' : item.chargeAmount}</td>
							<td>${item.isCharge == 0 ? '未缴费' : '已缴费'}</td>
							<td>${item.payTime == null || item.payTime == '' ? '--' : item.payTime}</td>
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
		$(function(){
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
					changeMeasureTypeChargePage(n);
					return false;
				}
			});
		});
	</script>
</body>
</html>