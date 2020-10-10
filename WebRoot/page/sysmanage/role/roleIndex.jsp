<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色管理首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysRole.js"></script>
</head>

<body>
	<div class="right_user" style="left:15px;">
		<div class="operate">
			<ul>
				<li class="add" id="show" onclick="addSysRole()">新增</li>
				<li class="remote" onclick="editSysRole()">修改</li>
				<li class="del" onclick="delSysRole()">删除</li>
			</ul>
			<div class="search">
				<input type="text" value="${roleName==null?'请输入角色名称':roleName}" id="roleName" value="请输入角色名称" onfocus="if(this.value == '请输入角色名称') this.value = '';" onblur="if(this.value =='') this.value = '请输入角色名称';" /><a id="btnSearch" onclick="searchSysRole()"></a>
			</div>
		</div>
		<div class="device" id="sysRoleList">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll" /></th>
						<th>角色编码</th>
						<th>角色名称</th>
						<th>角色权限</th>
						<th>修改时间</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${sysRoleList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td><input type="checkbox" id="${item.id}" name="${item.roleCode}"  value="${item.id}" /></td>
							<td>${item.roleCode}</td>
							<td>${item.roleName}</td>
							<td><a href="javascript:void(0)" style="color:#122bc1" onclick="setPermissionInfo('${item.id}');return false;"> 配置 </a></td>
							<td><fmt:formatDate value="${item.editTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${item.remark}</td>
						</tr>
					</c:forEach>
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
			//this.selectPage(n);
			return false;
		}
	});
});
</script>
</body>
</html>
