<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!-- 基本水费费率list page -->

<html>
<head>
<title>基本水费费率设置</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/paramBaseWaterPrice.js"></script>

</head>
<body>
	<div id="baseWaterPriceDiv">
		<div class="operate">
			<ul>
				<li class="add" onclick="addBaseWaterPricePage()">新增</li>
				<li class="del" onclick="delBaseWaterPriceById()">删除</li>
			</ul>
		</div>
		<div class="device">
			<table cellspacing="1" cellpadding="3" class='list-table'>
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll"/></th>
						<th>征收年份</th>
	              		<th>基本水费费率(亩/元)</th>
	              		<th>超出额定水量单价(亩/元)</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty baseWaterPriceList}">
						<tr>
							<td colspan="6">没有相关数据</td>
						</tr>
					</c:if>
					<c:if test="${!empty baseWaterPriceList}">
						<c:forEach var="item" items="${baseWaterPriceList}" varStatus="vs">
					 		<tr class="${vs.index%2==0?'singular':'double'}">
								<td><input type="checkbox" id="baseWaterPriceId" value="${item.id}" /></td>
								<td>${item.inYear}</td>
								<td>${item.standardPrice}</td>
								<td>${item.exceedPrice}</td>
								<td>
									<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td style="width:80px; padding-right:20px;">
			                 		<ul style="width:80px;">
			                 			<li class="edit" title="编辑" onclick="editBaseWaterPricePage('${item.id}')"></li>
			                        	<li class="del" title="删除" onclick="delBaseWaterPriceById('${item.id}')"></li>
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
					changeBaseWaterPricePage(n);
					return false;
				}
			});
		});
	</script>
</body>
</html>