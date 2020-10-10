<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<title>售水信息list页面</title>
		<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/echarts.common.min.js"></script>
		<style>
			.biankuang{
				margin: 0 auto;
				width: 96%;
				height: 180px;
				margin-top: 6px;
				font-weight: bold;
			}
		</style>
	</head>
	<body>
		<div class="right_user" style="left:15px; top:50px">
			<!-- 售水信息统计 -->
			<div class="biankuang">
				<div style="width:22%; margin-top:1%; float:left;">
					<p style="margin-bottom:2%; font-size:17px;">统计指标</p>
					<p style="margin-top:12px; font-size:16px; line-height:26px;">  
						购水总金额：
						<fmt:formatNumber type="number" value="${chargeAmount/10000}" pattern="0.00" maxFractionDigits="2"/> 万元
						<br>购水总量：
						<fmt:formatNumber type="number" value="${waterAmount/10000}" pattern="0.00" maxFractionDigits="2"/> 万m³
					</p>
					<p style="font-size:14px; margin-top:16px;">统计时间：<br>
					${startTime} ~ ${endTime}</p>
				</div>
				
				<!-- 购水量-水费 柱状图容器Dom -->
		    	<div id="saleWater_ZZT" style="width:75%; height:100%; float:left;"></div>
			</div>
			
			<!-- 华丽的分割线 -->
			<hr style="color:#E1EAF0; width:100%; margin-bottom:10px; border-style:double;">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th>机井编码</th>
						<th>机井名称</th>
						<th>水卡卡号</th>
						<th>购买水量(m³)</th>
						<th>充值总金额(元)</th>
						<th>充值单价(元/m³)</th>
						<th>充值时间</th>
						<th>充值方式</th>
						<th>Ip地址</th>
						<th>所属行政区域</th>
						<th>所属水管区域</th>
						<th>操作员</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty saleWaterList}">
						<tr>
							<td colspan="10">没有相关数据</td>
						</tr>
					</c:if>
					<c:if test="${!empty saleWaterList}">
						<c:forEach var="item" items="${saleWaterList}" varStatus="vs">
							<tr class="${vs.index%2==0?'singular':'double'}">
								<td>${item.deviceCode == null || item.deviceCode == '' ? '--' : item.deviceCode}</td>
								<td>${item.deviceName == null || item.deviceName == '' ? '--' : item.deviceName}</td>
								<td>${item.cardCode == null || item.cardCode == '' ? '--' : item.cardCode}</td>
								<td>${item.waterAmount == null || item.waterAmount == '' ? '--' : item.waterAmount}</td>
								<td>${item.chargeAmount == null || item.chargeAmount == '' ? '--' : item.chargeAmount}</td>
								<td>${item.unitPrice == null || item.unitPrice == '' ? '--' : item.unitPrice}</td>
								<td>
									<c:if test="${item.createTime==null || item.createTime == '' }">--</c:if>
									<c:if test="${item.createTime != null}">
										<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</c:if>
								</td>
								<td>
									<c:if test="${item.chargeType==null || item.chargeType == ''|| item.chargeType == 0 }">水卡充值</c:if>
									<c:if test="${item.chargeType==1||item.chargeType==2||item.chargeType==3}">远程充值</c:if>
								</td>
								<td>${item.ipToken == null || item.ipToken == '' ? '--' : item.ipToken}</td>
								<td>${item.areaName == null || item.areaName == '' ? '--' : item.areaName}</td>
								<td>${item.waterAreaName == null || item.waterAreaName == '' ? '--' : item.waterAreaName}</td>
								<td>${item.operator == null || item.operator == '' ? '--' : item.operator}</td>
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
			$(function() {
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
						changeSaleWaterPage(n);
						return false;
					}
				});
			});
			
			// 初始化要显示柱状图实例
		    var saleWater_ZZT = echarts.init(document.getElementById('saleWater_ZZT'));
		    // 指定柱状图的配置项和数据
			var zzt_option = {
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'none'
			        }
			    },
			    legend: {
			        data: ['购水金额','购水量']
			    },
			    grid: {
	                left: '3%',
	                right: '3%',
	                bottom: '1%',
	                top: '15%',
	                containLabel: true
	            },
			    xAxis: [
			        {
			            type: 'category',
			            axisPointer: {
			                type: 'shadow'
			            },
			            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '购水金额 (万元)',
			            position: 'left',
			            splitLine: {show: true},  	// 去掉背景水平网格线
			            splitNumber: 4,
			            axisLabel: {
			                formatter: '{value} 万元'
			            }
			        },
			        {
			        	type: 'value',
			            name: '购水量 (万m³)',
			            position: 'right',
			            splitLine: {show: false},  	// 去掉背景水平网格线
			            splitNumber: 4,
			            axisLabel: {
			            	formatter: '{value} 万m³'
			            }
			        }
			    ],
			    series: [
			        {
			            name: '购水金额(万元)',
			            type: 'bar',
			            color: '#5793f3',
			            yAxisIndex: 0,
			            data: [${charge_1}, ${charge_2}, ${charge_3}, ${charge_4}, ${charge_5}, ${charge_6}, ${charge_7}, ${charge_8}, ${charge_9}, ${charge_10}, ${charge_11}, ${charge_12}]
			        },
			        {
			            name: '购水量(万m³)',
			            type: 'bar',
			            yAxisIndex: 1,
			            data: [${water_1}, ${water_2}, ${water_3}, ${water_4}, ${water_5}, ${water_6}, ${water_7}, ${water_8}, ${water_9}, ${water_10}, ${water_11}, ${water_12}]
			        }
			    ]
		    };
			// 使用指定的配置项和数据显示柱状图
		    saleWater_ZZT.setOption(zzt_option);
		</script>
	</body>
</html>