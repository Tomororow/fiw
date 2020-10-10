<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!-- 末级渠系费率list page -->

<html>
<head>
<title>末级渠系费率设置</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/paramMaintainPrice.js"></script>

</head>
<body>
	<div id="maintainPriceDiv">
		<div class="operate">
			<ul>
				<li class="add" onclick="addMaintainPricePage()">新增</li>
				<li class="del" onclick="delMaintainPriceById()">删除</li>
			</ul>
		</div>
		<div class="device">
			<table cellspacing="1" cellpadding="3" class='list-table'>
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll"/></th>
						<th>征收年份</th>
	              		<th>末级渠系(维修养护)费率(m³/元)</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty maintainPriceList}">
						<tr>
							<td colspan="6">没有相关数据</td>
						</tr>
					</c:if>
					<c:if test="${!empty maintainPriceList}">
						<c:forEach var="item" items="${maintainPriceList}" varStatus="vs">
					 		<tr class="${vs.index%2==0?'singular':'double'}">
								<td><input type="checkbox" id="maintainPricePriceId" value="${item.id}" /></td>
								<td>${item.inYear}</td>
								<td>${item.maintainPrice}</td>
								<td>
									<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td style="width:80px; padding-right:20px;">
			                 		<ul style="width:80px;">
			                 			<li class="edit" title="编辑" onclick="editMaintainPricePage('${item.id}')"></li>
			                        	<li class="del" title="删除" onclick="delMaintainPriceById('${item.id}')"></li>
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
					changeMaintainPricePage(n);
					return false;
				}
			});
		});
	</script>
</body>
</html>