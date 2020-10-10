<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>功率设置</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/paramPower.js"></script>
</head>
<body>
	<div id="paramPowerDiv">
	<div class="operate">
		<ul>
			<li class="add" onclick="addPowerInfo()">新增</li>
			<li class="del" onclick="delPower()">删除</li>
		</ul>
	</div>
	<div class="device">
		<table cellspacing="1" cellpadding="3" class='list-table'>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"/></th>
              		<th>下行百分比</th>
					<th>上行百分比</th>
					<th width="80">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty paramPowerList}">
					<tr>
						<td colspan="4">没有相关数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty paramPowerList}">
					<c:forEach var="item"  items="${paramPowerList}" varStatus="vs">
				 		<tr class="${vs.index%2==0?'singular':'double'}">
							<td><input type="checkbox" id="powerId" value="${item.id}" /></td>
							<td>${item.powerPercentUp}</td>
							<td>${item.powerPercentDown}</td>
							<td width="80">
		                 		<ul style="width:120px;">
		                  			<li class="edit" title="编辑" onclick="editPower('${item.id}')"></li>
		                        	<li class="del" title="删除" onclick="delPowerById('${item.id}')"></li>
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
					changePowerPage(n);
					//this.selectPage(n);
					return false;
				}
			});
		});
	</script>
</body>
</html>