<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<html>
<head>
<title>水资源费设置</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/paramMeasureTypePrice.js"></script>
</head>
<body>
	<div id="paramMeasureTypePriceDiv">
		<div class="operate">
			<ul>
				<li class="add" onclick="addMeasureTypePriceInfo()">新增</li>
				<li class="del" onclick="delMeasureTypePrice()">删除</li>
			</ul>
		</div>
		<div class="device">
			<table cellspacing="1" cellpadding="3" class='list-table'>
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll"/></th>
						<th>征收年份</th>
	              		<th>计量水费征收费率(m³/元)</th>
						<th>操作时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty paramMeasureTypePriceList}">
						<tr>
							<td colspan="5">没有相关数据</td>
						</tr>
					</c:if>
					<c:if test="${!empty paramMeasureTypePriceList}">
						<c:forEach var="item"  items="${paramMeasureTypePriceList}" varStatus="vs">
					 		<tr class="${vs.index%2==0?'singular':'double'}">
								<td><input type="checkbox" id="waterSourcePriceId" value="${item.id}" /></td>
								<td>${item.inYear}</td>
								<td>${item.measureTypePrice}</td>
								<td>
									<fmt:formatDate value="${item.editTime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td style="width:80px; padding-right:20px;">
			                 		<ul style="width:80px;">
			                 			<li class="edit" title="编辑" onclick="editMeasureTypePricePage('${item.id}')"></li>
			                        	<li class="del" title="删除" onclick="delMeasureTypePriceById('${item.id}')"></li>
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
					changeMeasureTypePricePage(n);
					return false;
				}
			});
		});
	</script>
</body>
</html>