<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>三相电压设置</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/paramThreeVoltage.js"></script>
</head>
<body>
	<div id="paramThreeVoltageDiv">
		<div class="operate">
			<ul>
				<li class="add" onclick="addThreeVoltageInfo()">新增</li>
				<li class="del" onclick="delThreeVoltage()">删除</li>
			</ul>
		</div>
		<div class="device">
			<table cellspacing="1" cellpadding="3" class='list-table'>
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll"/></th>
	              		<th>A相电压（上/下限）</th>
						<th>B相电压（上/下限）</th>
						<th>C相电压（上/下限）</th>
						<th width="80">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty paramThreeVoltageList}">
						<tr>
							<td colspan="5">没有相关数据</td>
						</tr>
					</c:if>
					<c:if test="${!empty paramThreeVoltageList}">
						<c:forEach var="item"  items="${paramThreeVoltageList}" varStatus="vs">
					 		<tr class="${vs.index%2==0?'singular':'double'}">
								<td><input type="checkbox" id="threeVoltageId" value="${item.id}" /></td>
								<td>${item.aPhaseVoltageUp}&nbsp;~&nbsp;${item.aPhaseVoltageDown}</td>
								<td>${item.bPhaseVoltageUp}&nbsp;~&nbsp;${item.bPhaseVoltageDown}</td>
								<td>${item.cPhaseVoltageUp}&nbsp;~&nbsp;${item.cPhaseVoltageDown}</td>
								<td style="width:60px; padding-left:40px;">
			                 		<ul>
			                  			<li class="edit" title="编辑" onclick="editThreeVoltage('${item.id}')"></li>
			                        	<li class="del" title="删除" onclick="delThreeVoltageById('${item.id}')"></li>
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
					changeThreeVoltagePage(n);
					return false;
				}
			});
		});
	</script>
</body>
</html>