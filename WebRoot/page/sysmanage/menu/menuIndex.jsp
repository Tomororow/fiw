<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>菜单管理首页</title>
		<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysMenu.js"></script>
	</head>
  
	<body>
	<div class="right_user" style="left:15px;">
		<div class="operate">
			<ul>
				<li class="remote">修改 </li>
			</ul>
			<div class="search">
				<input type="text" value="请输入菜单名称" id="menuName" name="menuName" onfocus="if(this.value == '请输入菜单名称') this.value = '';" onblur="if(this.value =='') this.value = '请输入菜单名称';" /><a id="btnSearch" onclick="searchMenu()"></a>
			</div>
		</div>
		<div class="device" id="sysMenuList">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr style="border-bottom: 1px solid #d0d9de;">
						<th><input type="checkbox" id="checkAll"/></th>
                        <th>菜单编码</th>
				 	    <th>菜单名称</th>
				 	    <th>菜单排序</th>
				 	    <th>菜单链接</th>
				 	    <th>菜单图标</th>
				 	    <th>子菜单管理</th>
				 	    <th>备注</th>
				 	    <th>更新时间</th>
					</tr>
				</thead>
				<tbody id="list-content">
					<c:forEach var="item"  items="${menuList}" varStatus="vs">
				 		 <tr class="${vs.index%2==0?'singular':'double'}">
				             <td><input type="checkbox" id="${item.id}" name="chk" value="${item.id}"></td>
				 			 <td>${item.menucode}</td>
				 			 <td>${item.menuname}</td>
				 			 <td>${item.menuorder}</td>
				 			 <td>${item.menuurl}</td>
				 			 <td>${item.menuicon}</td>
				 			 <td><a href="javascript:void(0)" onclick="menuManager('${item.id}','${item.menuname}','${item.menucode}')"><font color="blue">管理</font></a></td>
				 			 <td>${item.menuremark}</td>
				 			 <td><fmt:formatDate value="${item.menuupdatetime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
				    </tr>
				</c:forEach> 
				</tbody>

			</table>
			<div class="list-page"><div id="pagination"></div></div>
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