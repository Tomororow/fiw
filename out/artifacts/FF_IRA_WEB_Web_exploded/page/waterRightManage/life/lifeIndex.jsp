<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>生活用水配水计划</title>
	<style type="text/css">
		#tinybox{position:absolute; display:none; padding:10px; background:#ffffff url(../image/preload.gif) no-repeat 50% 50%; border:10px solid #e3e3e3; z-index:2000;}
		#tinymask{position:absolute; display:none; top:0; left:0; height:100%; width:100%; background:#000000; z-index:1500;}
		#tinycontent{background:#ffffff; font-size:1.1em;}
	</style>
	
	<!-- 引用弹出框的js -->
	<script type="text/javascript" src="${ctx}/content/skin/js/tinybox.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/baseDistWaterPlan.js"></script>
</head>

<body>
	<div class="right_user" style="left:15px;">
		<div class="operate">
			<ul>
				<c:if test="${sysWaterArea.waterAreaLevel<2}">
					<li class="add" id="show" onclick="addLife(this)" style="line-height:20px;" data-src="waterRightManage/addLifePage.do?isAppendWater=0"><font style="font-size: 15px;">新增配水计划</font></li>
					<li class="del" onclick="delPlan(this)" style="line-height:20px;" data-fl="3"><font style="font-size: 15px;">删除</font></li>
				</c:if>
				<%-- <c:if test="${sysWaterArea.waterAreaLevel<2}">
					<li class="addAppendWater" id="show" onclick="addLife(this)" style="line-height:20px;" data-src="waterRightManage/addLifePage.do?isAppendWater=1"><font style="font-size: 15px;">阶梯性追加水量</font></li>
				</c:if> --%>
			</ul>
		</div>
		<div class="device" id="baseDistWaterPlanList">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll" /></th>
						<th>配水区域</th>
						<th>配水方式(点击查看)</th>
						<th>年份</th>
						<th>轮次</th>
						<th>人均分配水量(m³/人)</th>
						<th>配水价格(元/m³)</th>
						<th>配水时间</th>
						<th>操作人员</th>
						<th width="30">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty industryList}">
						<tr>
							<td colspan="10">没有相关数据</td>
						</tr>
					</c:if>
					
					<c:if test="${!empty industryList}">
						<c:forEach var="item" items="${industryList}" varStatus="vs">
							<tr class="${vs.index%2==0?'singular':'double'}">
								<td><input type="checkbox" id="${item.id}" name="${item.id}"  value="${item.id}" /></td>
								<td>${item.waterAreaName}</td>
								<td><a onclick="showDistWaterDeviceInfo('${item.id}',${item.distType})" style="cursor: pointer;">按人口配水</a></td>
								<td>${item.distYear}</td>
								<td>${item.distRound}</td>
								<td>${item.distWater}</td>
								<td>${item.distPrice}</td>
								<td>
									<c:if test="${item.createTime == null || item.createTime == ''}">
										--
									</c:if>
									<c:if test="${item.createTime != null || item.createTime != ''}">
										<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</c:if>
								</td>
								<td>${item.userName}</td>
								<td width="30">
		                            <ul style="width:60px; padding-left:15px;">
		                                <li class="del" title="删除" onclick="delPlan(this)" data-fl="3" data-id='${item.id}'></li>
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
			var distType = 3;
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
					changePage(n,distType);
					return false;
				}
			});
		});
	</script>
</body>
</html>