<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<body>
	<div class="operate">
		<ul>
			<li class="add" id="show" onclick="addAll(this)" data-src="baseInfo/serviceManAdd.do" data-fl="serviceMan">新增</li>
			<li class="del" onclick="delAll(this)" data-fl="serviceMan">删除</li>
		</ul>
	</div>
    <div style="margin-top: 4%;" id="baseInfoDiv">
		<table cellspacing="1" cellpadding="3" class='list-table'>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"/></th>
					<th>序号</th>
					<th>人员姓名</th>
					<th>联系电话</th>
					<th>职务</th>
					<th>所属水管区域</th>
					<th>修改时间</th>
					<th style="width:50px">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${sysServiceManList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td><input type="checkbox" id="deviceModelId" value="${item.id}" /></td>
						<td>${(vs.count+pageCurrent*pageSize)-pageSize}</td>
						<td>${item.serviceMan}</td>
						<td>${item.phone}</td>
						<td>${item.duty}</td>
						<td>${item.waterAreaName}</td>
						<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td style="width:100px; padding-left:5px;">
	                    	<ul style="width:100px;">
	                    		<li class="edit" title="编辑" onclick="editAll(this)" data-id='${item.id}' data-src="baseInfo/serviceManEdit.do" data-fl="serviceMan"></li>
	                        	<li class="del" title="删除" onclick="delAll(this)" data-id='${item.id}' data-fl="serviceMan"></li>
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
</body>
<script type="text/javascript">
	$(function(){
		var sbh = "serviceMan";
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
				changePage(n,sbh);
				return false;
			}
		});
	});
</script>