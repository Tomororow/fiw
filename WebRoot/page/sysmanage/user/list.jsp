<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
</head>
<body>
	<div class="right_user" style="left:15px;">
	<div class="operate">
		<ul>
			<li class="add" onclick="addUser()">新增</li>
			<li class="del" onclick="deleteUser()">删除</li>
			<li class="remote" onclick="initPwdUser()" style="width:75px;">密码初始化</li>
		</ul>
		<div class="search">
			<input type="text" value="${query_username==null||query_username==''?'请输入用户名称':query_username}" id="query_username"
				onfocus="if(this.value == '请输入用户名称') this.value = '';" onblur="if(this.value =='') this.value = '请输入用户名称';" />
			<a id="btnSearch" onclick="searchUserList()"></a>
		</div>
	</div>
	<div class="device" id="sysUserDiv">
		<table cellspacing='0' cellpadding='3' class='list-table' 
			style=" border:1px solid #d0d9de; border-right:0px; border-bottom:0px;">
			<thead>
				<tr style="border-bottom: 1px solid #d0d9de;">
					<th><input type="checkbox" id="checkAll" /></th>
					<th>用户编码</th>
					<th>登录名称</th>
					<th>用户姓名</th>
					<th>用户电话</th>
					<th>所属行政区域</th>
					<th>所属水管区域</th>
					<th>角色名称</th>
					<th width="80px;">操作</th>
				</tr>
			</thead>
			<tbody id="list-content">
				<c:if test="${empty userList}">
					<tr>
						<td colspan="9">没有相关数据</td>
					</tr>
				</c:if>
			
				<c:if test="${!empty userList}">
					<c:forEach var="item" items="${userList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td><input type="checkbox" id="${item.id}" value="${item.id}" /></td>
							<td>${item.userCode}</td>
							<td>${item.userName}</td>
							<td>${item.fullName}</td>
							<td>${item.mobile}</td>
							<td>${item.areaName}</td>
							<td>${item.waterAreaName}</td>
							<td>${item.roleName}</td>
							<td style="width:80px; padding-left:10px;">
	                 			<ul style="width:80px;">
	                       			<li class="edit" title="编辑" onclick="editUserInfo('${item.id}')"></li>
	                        		<li class="del" title="删除" onclick="delUserInfo('${item.id}')"></li>
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
					changePage(n);
					return false;
				}
			});
		});
	</script>
</body>
</html>