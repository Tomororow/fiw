<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<title>其他费用统计(基本水费-计量水费-水资源费)</title>
		<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/echarts.common.min.js"></script>
		<style>
			.biankuang{
				margin: 0 auto;
				width: 96%;
				height: 180px;
				padding-top: 6px;
				font-weight: bold;
			}
		</style>
	</head>
	
	<body>
		<div class="right_user" style="left:15px; top:50px;">
			<!-- 其他费用统计 文字+图表 -->
			<div class="biankuang">
				<div style="width:20%; float:left;">
					<p style="margin-bottom:2%; font-size:17px;">统计指标</p>
					<p style="margin-top:12px; font-size:16px;"> 
						购水总金额：
						<fmt:formatNumber type="number" value="${chargeAmountSum/10000}" pattern="0.00" maxFractionDigits="2"/> 万元
					</p>
					<p style="margin-top:20px; font-size:14px; line-height:22px;">
						其中：<br>
						计量水费：
						<fmt:formatNumber type="number" value="${measureTypeAmountSum/10000}" pattern="0.00" maxFractionDigits="2"/> 万元<br>
						 末级渠系水费：
						<fmt:formatNumber type="number" value="${maintainPriceAmountSum/10000}" pattern="0.00" maxFractionDigits="2"/> 万元<br>
						水资源费：
						<fmt:formatNumber type="number" value="${waterSourcePriceAmountSum/10000}" pattern="0.00" maxFractionDigits="2"/> 万元
					</p>
				</div>
				
				<!-- 柱状图容器Dom -->
		    	<div id="waterAmount_ZZT" style="width:49%; height:180px; float:left;"></div>
		    	
		    	<!-- 饼状图容器Dom -->
		    	<div id="waterAmount_BZT" style="width:30%; height:180px; float:left;"></div>
			</div>
			
			<!-- 华丽的分割线 -->
			<hr style="color:#E1EAF0; border-style:double;">
			
			<table cellspacing='0' cellpadding='3' class='list-table' style="margin-top:6px;">
				<thead>
					<tr>
						<th>机井编码</th>
	                    <th>机井名称</th>
						<th>已购水量<br>(m³)</th>
						<th>购水单价<br>(元/m³)</th>
						<th>购水总金额<br>(元)</th>
						<th>计量水费<br>(元/m³)</th>
						<th>计量水费金额<br>(元)</th>
						 <th>末级渠系水费<br>(元/m³)</th>
						<th>末级渠系水费金额<br>(元)</th> 
						<th>水资源费<br>(元/m³)</th>
						<th>水资源费金额<br>(元)</th>
						<th>缴费时间</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty wscList}">
						<tr>
							<td colspan="12">没有相关数据</td>
						</tr>
					</c:if>
					<c:if test="${!empty wscList}">
						<c:forEach var="item" items="${wscList}" varStatus="vs">
							<tr class="${vs.index%2 == 0 ? 'singular' : 'double'}">
								<td>${item.deviceCode == null || item.deviceCode == '' ? '--' : item.deviceCode}</td>
								<td>${item.deviceName == null || item.deviceName == '' ? '--' : item.deviceName}</td>
								<td>${item.waterAmount == null || item.waterAmount == '' ? '--' : item.waterAmount}</td>
								<td>${item.unitPrice == null || item.unitPrice == '' ? '--' : item.unitPrice}</td>
								<td>${item.chargeAmount == null || item.chargeAmount == '' ? '--' : item.chargeAmount}</td>
								<td><fmt:formatNumber type="number" value="${measureTypePrice}" pattern="0.00" maxFractionDigits="2"/></td>
								<td>
									<fmt:formatNumber type="number" value="${item.measureTypeAmount == null || item.measureTypeAmount == '' ? '--' : item.measureTypeAmount}" pattern="0.00" maxFractionDigits="2"/>
								</td>
								 <td><fmt:formatNumber type="number" value="${maintainPrice}" pattern="0.00" maxFractionDigits="2"/></td>
								<td>
									<fmt:formatNumber type="number" value="${item.maintainAmount == null || item.maintainAmount == '' ? '--' : item.maintainAmount}" pattern="0.00" maxFractionDigits="2"/>
								</td> 
								<td><fmt:formatNumber type="number" value="${waterSourcePrice}" pattern="0.00" maxFractionDigits="2"/></td>
								<td>
									<fmt:formatNumber type="number" value="${item.waterSourceAmount == null || item.waterSourceAmount == '' ? '--' : item.waterSourceAmount}" pattern="0.00" maxFractionDigits="2"/>
								</td>
								<td>
									<c:if test="${item.createTime == null || item.createTime == ''}">
										--
									</c:if>
									<c:if test="${item.createTime != null || item.createTime != ''}">
										<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<!-- 分页导航条DIV -->
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
						// 分页执行方法
						changeWaterSourcePage(n);
						return false;
					}
				});
			});
			
			// 初始化要显示柱状图实例
		    var waterChart_ZZT = echarts.init(document.getElementById('waterAmount_ZZT'));
		    // 指定柱状图的配置项和数据
			var zzt_option = {
				color: ['#3398DB'],
	            tooltip: {
	            	trigger: 'axis',
	                axisPointer: {
	                	type: 'shadow'
	                }
	            },
	            grid: {
	                left: '3%',
	                right: '3%',
	                bottom: '3%',
	                top: '20%',
	                containLabel: true
	            },
	            xAxis: [
	            	{
	            		type : 'category',
	                	data: ['总水费', '计量水费',  '末级渠系费',  '水资源费'],
	                	axisTick: {
	                        alignWithLabel: true
	                    }
	            	}
	            ],
	            yAxis: [
                	{
	       	            type: 'value',
	       	            name: '收费金额 (万元)',
	       	            axisLabel: {
	       	                formatter: '{value} 万元'
	       	            }
            		}
	            ],
	            series: [
	            	{
		                name: '收费金额(万元)',
		                type: 'bar',
		                barWidth: '50%',
		                data: [${chargeAmountSum/10000}, 
		                       ${measureTypeAmountSum/10000}, 
		                       ${maintainPriceAmountSum/10000},  
		                       ${waterSourcePriceAmountSum/10000}]
	            	}
	        	]
			};
		    // 使用指定的配置项和数据显示柱状图
		    waterChart_ZZT.setOption(zzt_option);
		    
		 	// 初始化要显示饼状图实例
		    var waterChart_BZT = echarts.init(document.getElementById('waterAmount_BZT'));
		 	// 指定饼状图的配置项和数据
		 	var bzt_option = {
	    	    tooltip: {
	    	        trigger: 'item',
	    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    	    },
	    	    legend: {
	    	        orient: 'vertical',
	    	        left: 'right',
	    	        data: ['计量水费',  '末级渠系费',  '水资源费']
	    	    },
	    	    series: [
	    	        {
	    	            name: '收费金额(万元)',
	    	            type: 'pie',
	    	            radius: '85%',
	    	            center: ['50%', '52%'],
	    	            data: [
	    	                {value: ${measureTypeAmountSum/10000}, name: '计量水费'},
	    	                {value: ${maintainPriceAmountSum/10000}, name: '末级渠系费'}, 
	    	                {value: ${waterSourcePriceAmountSum/10000}, name: '水资源费'}
	    	            ],
	    	            itemStyle: {
	    	                emphasis: {
	    	                    shadowBlur: 10,
	    	                    shadowOffsetX: 0,
	    	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	    	                }
	    	            }
	    	        }
	    	    ]
	    	};
		 	// 使用指定的配置项和数据显示饼状图
		    waterChart_BZT.setOption(bzt_option);
		</script>
	</body>
</html>