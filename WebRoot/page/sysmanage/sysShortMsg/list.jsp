<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/content/skin/js/sysShortMsg.js"></script>
<style>
.Wdate {
	border: #ccc 1px solid;
	width: 200px;
	height: 31px;
}
</style>
<body>
	<div class="operate">
		<ul>
			<li class="add" id="show" onclick="add()">新增</li>
			<li class="del" onclick="delAllMsg(this)">删除</li>
		</ul>
	</div>
	<div style="margin-top:4%;" id="sysManageInfoContent">
		<table  cellspacing='1' cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll" /></th>
					<th>短信编码</th>
					<th>短信内容</th>
					<th style="width:30">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty msgList}">
				<tr>
					<td colspan="4">没有相关数据</td>
				</tr>
				</c:if>
				<c:if test="${!empty msgList}">
				<c:forEach var="item" items="${msgList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td><input type="checkbox" id="statusId" value="${item.id}" /></td>
						<td>${item.msgCode}</td>
						<td>${item.msgContent}</td>
						<td style="width:30px;">
							<ul style="width:80px;">
								<li class="edit" title="编辑" onclick="edit('${item.id}')"></li>
								<li class="del" title="删除" onclick="delMsgById('${item.id}')"></li>
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
</body>
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
			changeMsgInfoPage(n);
			return false;
		}
	});
});
</script>