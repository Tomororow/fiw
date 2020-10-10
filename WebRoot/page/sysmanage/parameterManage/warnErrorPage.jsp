<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<html>
<head>
<title>设备异常类型设置</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/warneErrorPage.js"></script>
<style type="text/css">

</style>
</head>
<body>
	<div class="operate">
		<ul>
			<li class="add" onclick="addWarnInfo()">新增</li>
			<!-- <li class="del" onclick="delWarnInfo()">删除</li> -->
		</ul>
	</div>
	<div class="device" id="baseDeviceInfoDiv">
		<table cellspacing='0' cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th>异常类型</th>
					<th>异常编码</th>
					<th>异常详情</th>
					<th>关联预警通知人员</th>
					<th>短信预警</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${warnList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.abnormaltype}</td>
						<td>${item.abnormalcode}</td>
						<td>${item.abnormaldetail==null?'--':item.abnormaldetail}</td>
						<td>${item.backone}</td>
						<td>${item.ismess=='0'?'关闭':'开启'}</td>
						<td style="width:100px; padding-left:5px;">
	                    	<ul style="width:100px;">
	                    		<li class="edit" title="编辑" onclick="editWarnInfo(${item.id},${item.abnormalcode})" data-id='${item.id}'></li>
	                    		<li class="del" title="删除" onclick="delWarnInfo(${item.id},${item.abnormalcode})"></li>
	                    	</ul>
	                    </td>
						<%-- <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td style="width:100px; padding-left:5px;">
	                    	<ul style="width:100px;">
	                    		<li class="edit" title="编辑" onclick="editAll(this)" data-id='${item.id}' data-src="baseInfo/serviceManEdit.do" data-fl="serviceMan"></li>
	                        	<li class="del" title="删除" onclick="delAll(this)" data-id='${item.id}' data-fl="serviceMan"></li>
	                    	</ul>
                        </td> --%>
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