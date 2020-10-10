<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>水管区域列表</title>
</head>

<body>
	<div class="operate">
		<ul>
			<li class="add" onclick="addSysWaterArea()">新增</li>
			<li class="del" onclick="delSysWaterArea()">删除</li>
		</ul>
		<%-- <div class="search">
			<input type="text" value="${waterAreaCode==null?'请输入水管区域编码':waterAreaCode}" id="waterAreaCode" value="请输入水管区域编码" onfocus="if(this.value == '请输入水管区域编码') this.value = '';" onblur="if(this.value =='') this.value = '请输入水管区域编码';" /><a id="btnSearch" onclick="searchWaterAreaCode()"></a>
		</div> --%>
	</div>
	<div class="device" id="sysWaterAreaDiv">
		<input type="hidden" id="firstAreaId" name="firstAreaId"
			value="${firstAreaId}"></input>
		<table cellspacing="1" cellpadding="3" class='list-table'>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll" /></th>
					<th>水管区域编码</th>
					<th>水管区域名称</th>
					<th>水管区域级别</th>
					<th>父水管区域</th>
					<th>添加时间</th>
					<th>备注</th>
					<th width="30">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${sysWaterAreaList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}"
						<c:if test="${item.parentWaterAreaId==''}">style="color:#029F89"</c:if>>
						<td><input type="checkbox" id="id" value="${item.id}" /></td>
						<td>${item.waterAreaCode}</td>
						<td>${item.waterAreaName}</td>
						<td>${item.waterAreaLevel}</td>
						<td><c:if test="${item.parentWaterAreaId==''}">无</c:if>
							<c:if test="${item.parentWaterAreaId!=''}">${item.parentWaterAreaName}</c:if></td>
						<td><fmt:formatDate value="${item.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${item.remark}</td>
						<td width="30">
							<ul style="width:80px;">
								<li class="edit" title="编辑" onclick="editSysWaterArea('${item.id}')"></li>
								<li class="del" title="删除" onclick="delSysWaterArea('${item.id}')"></li>
							</ul>
						</td>
					</tr>
				</c:forEach>
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
					changePage(n);
					return false;
				}
			});
		});
	</script>
</body>
</html>