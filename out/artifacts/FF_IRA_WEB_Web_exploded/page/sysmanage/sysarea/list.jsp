<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设备列表</title>
</head>

<body>
	<div class="operate">
		<ul>
			<li class="add" onclick="addSysArea()">新增</li>
            <li class="del" onclick="delSysArea()">删除</li>
		</ul>
	</div>
	<div class="device" id="sysAreaDiv">
		<input type="hidden" id="firstAreaId" name="firstAreaId" value="${firstAreaId}"></input>
		<table cellspacing="1" cellpadding="3" class='list-table'>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"/></th>
              		<th>行政区域编码</th>
					<th>行政区域名称</th>
					<th>行政区域级别</th>
					<th>父行政区域</th>
					<th>添加时间</th>
					<th>备注</th>
					<th width="30">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item"  items="${sysAreaList}" varStatus="vs">
			 		<tr class="${vs.index%2==0?'singular':'double'}" <c:if test="${item.parentAreaId==''}">style="color:#029F89"</c:if>>
						<td><input type="checkbox" id="areaId" value="${item.id}" /></td>
						<td>${item.areaCode}</td>
						<td>${item.areaName}</td>
						<td>${item.areaLevel}</td>
						<td>${item.parentAreaName}</td>
						<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${item.remark}</td>
						<td width="30">
                            <ul style="width:80px;">
                                <li class="edit" title="编辑" onclick="editSysArea('${item.id}')"></li>
                                <li class="del" title="删除" onclick="delSysArea('${item.id}')"></li>
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
					changePage(n);
					return false;
				}
			});
		});
	</script>
</body>
</html>