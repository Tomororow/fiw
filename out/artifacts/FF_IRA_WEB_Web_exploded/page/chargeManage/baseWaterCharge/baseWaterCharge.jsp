<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<title>基本水费收缴list页面</title>
	<style>
		.biankuang{
			margin: 0 auto;
			width: 96%;
			height: 100px;
			font-weight: bold;
		}
	</style>
</head>

<body>
	<div class="right_user" style="left:15px; top:50px">
		<!-- 基本水费统计 -->
		<div class="biankuang">
			<div style="width:22%; margin-top:6px; float:left;">
				<p style="margin-bottom:1%; font-weight:bold; font-size:17px;">统计指标</p>
				<p style="margin-top:18px; margin-bottom:1%; font-size:16px;">基本水费：
					<fmt:formatNumber type="number" value="${baseWaterPriceSUM/10000}" pattern="0.00" maxFractionDigits="2"/> 万元
				</p>
				<p style="font-size:14px; margin-top:10px; font-weight:bold;">基本水费征收年份：${currentYear} 年</p>
			</div>
		</div>
		
		<!-- 华丽的分割线 -->
		<hr style="color:#E1EAF0; width:100%; margin-bottom:10px; border-style:double;">
		<table cellspacing='0' cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"/></th>
                    <th>机井编码</th>
                    <th>机井名称</th>
					<th>实灌面积(亩)</th>
					<th>基本水费<br>(元/亩)</th>
					<th>收费金额(元)</th>
					<th>是否缴费</th>
					<th>缴费方式</th>
					<th>缴费时间</th>
					<th width="80">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty baseWaterChargeList}">
					<tr>
						<td colspan="10">没有相关数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty baseWaterChargeList}">
					<c:forEach var="item" items="${baseWaterChargeList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td><input type="checkbox" id="${item.id}" name="${item.id}" value="${item.id}" /></td>
							<td>${item.deviceCode == null || item.deviceCode == '' ? '--' : item.deviceCode}</td>
							<td>${item.deviceName == null || item.deviceName == '' ? '--' : item.deviceName}</td>
							<td>${item.sjArea}</td>
							<td>${standardPrice}</td>
							<td>${item.chargeAmount == null || item.chargeAmount == '' ? '--' : item.chargeAmount}</td>
							<td>${item.isCharge == 0 ? '未缴费' : '已缴费'}</td>
							<td>
								<c:if test="${item.isCharge==0}">
									--
								</c:if>
								<c:if test="${item.isCharge==1}">
									${item.chargeMode == 1 ? '现金' : '银行卡'}
								</c:if>
							</td>
							<td>
								<c:if test="${item.isCharge==0}">
									--
								</c:if>
								<c:if test="${item.isCharge==1}">
									<c:if test="${item.editTime == null || item.editTime == ''}">
										--
									</c:if>
									<c:if test="${item.editTime != null || item.editTime != ''}">
										<fmt:formatDate value="${item.editTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</c:if>
								</c:if>
							</td>
							<td>
								<ul style="width:60px; padding-left:20px;">
		                            <li class="report" title="收费" onclick="baseWaterChargeOperator('${item.id}')"></li>
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
					changeBaseDeviceWaterPricePage(n);
					return false;
				}
			});
			$(".leftTree").height($(".left").outerHeight() - 10);
			$("#checkAll").live("click",function(){
				var check = $(this).attr("checked");
				var ckList = $(".right_user tbody input[type='checkbox']");
				if(check){
					ckList.attr("checked",true);
				}else{
					ckList.removeAttr("checked");
				}
			});
		});
	</script>
</body>
</html>