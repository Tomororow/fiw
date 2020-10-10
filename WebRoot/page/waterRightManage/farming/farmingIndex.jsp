<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>农业用水配水计划-列表页</title>
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
					<li class="add" id="show" onclick="addFarming(this)" style="line-height:20px;" data-src="waterRightManage/addFarmingPage.do?isAppendWater=0"><font style="font-size: 15px;">新增配水计划</font></li>
					<li class="del" onclick="delPlan(this)" style="line-height:20px;" data-fl="1"><font style="font-size: 15px;">删除</font></li>
				</c:if>
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
						<th>每亩地分配水量(m³)</th>
						<th>配水比例</th>
						<th>配水价格(元/m³)</th>
						<th>配水时间</th>
						<th>操作人员</th>
						<th width="30">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty farmingList}">
						<tr>
							<td colspan="11">没有相关数据</td>
						</tr>
					</c:if>
				
					<c:if test="${!empty farmingList}">
						<c:forEach var="item" items="${farmingList}" varStatus="vs">
							<tr class="${vs.index%2==0?'singular':'double'}">
								<td><input type="checkbox" id="${item.id}" name="${item.id}"  value="${item.id}" /></td>
								<td>${item.waterAreaName}</td>
								<td>
									<a onclick="showDistWaterDeviceInfo('${item.id}',${item.distType})" style="cursor: pointer;">
										<c:choose>
											<c:when test="${item.distMode==1}">实际灌溉面积配水</c:when>
											<c:when test="${item.distMode==2}">额定灌溉面积配水</c:when>
											<c:otherwise>--</c:otherwise>
									    </c:choose>
								    </a>
								</td>
								<td>${item.distYear}</td>
								<td>${item.distRound}</td>
								<td>${item.distWater}</td>
								<td>${item.distRatio}</td>
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
								<td>
		                            <ul style="width:60px; padding-left:15px;">
		                                <li class="del" title="删除" onclick="delPlan(this)" data-fl="1" data-id='${item.id}'></li>
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
			var distType = 1;
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