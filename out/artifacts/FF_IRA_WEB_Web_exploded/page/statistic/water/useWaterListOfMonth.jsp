<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/core/style.css">
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/zingchart.min.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/zingchart.jquery.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/echarts.common.min.js"></script>
<style>
	.biankuang{
		width: 98.9%;
		height: 180px;
		border: 1px solid #E3E3E3;
		padding: 6px 0px 0px 15px;
	}
</style>
<div class="right_user" style="left:15px; top:50px;">
	<div class="biankuang">
	<div style="width:20%; float:left;">
		<p style="margin-bottom:1%; font-weight:bold; font-size:18px;">统计指标</p>
		<h1 style="margin-top:20px; margin-bottom:1%;">总用水量：
			<fmt:formatNumber type="number" value="${useWaterSum}" pattern="0.00" maxFractionDigits="4"/> m³
		</h1>
		<p style="font-size:14px; font-weight:bold;">查询月份：${startTime} 月</p>
		</div>
		<div id='daybar' style="width:70%; height:180px; float:left;"></div>
	</div>
	<!-- 华丽的分割线 -->
	<hr style="color:#E1EAF0; border-style:double;">
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>机井编码</th>
				<th>机井名称</th>
				<th>水卡卡号</th>
				<th>联系电话</th>
				<th>采集时间</th>
				<th>月用水量(m³)</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty uwdList}">
				<tr>
					<td colspan="6">没有相关数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty uwdList}">
				<c:forEach var="item" items="${uwdList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.deviceCode}</td>
						<td>${item.deviceName}</td>
						<td>${item.cardCode}</td>
						<td>${item.ownerTelphone}</td>
						<td>
							<c:if test="${item.createTime == null || item.createTime == ''}">
								--
							</c:if>
							<c:if test="${item.createTime != null || item.createTime != ''}">
								<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM" />
							</c:if>
						</td>
						<td>${item.useWaterOfMonth}</td>
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
//饼状图的数组
var len = new Array();
var lendata = new Array();
var seleted= {};
var dayList = [];
var sumdaywaterList = [];
	$(function() {
		var totalPage = ${pagingBean.pageNum};
		var totalRecords = ${pagingBean.totalItems};
		var pageNo = ${pagingBean.pageNo};
		if (!pageNo) {
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
			pointWidth : 3,
			click : function(n) {
				//分页执行方法
				changeUseWaterPage(n);
				return false;
			}
		});
		dayList = "${dayList}";
		sumdaywaterList = "${sumdaywaterList}";
		if(dayList!=""&&sumdaywaterList!=""){
			sumdaywaterList = JSON.parse(sumdaywaterList);
			dayList = JSON.parse(dayList);
		}
		  for(var i=0;i<dayList.length;i++){
			  debugger
				  len.push(dayList[i]);
				  lendata.push({value:sumdaywaterList[i],name:dayList[i]+'日'});
				  seleted[dayList[i]]=i<6;
		 	}
	});
	
	// 初始化要显示柱状图实例
    var waterChart_ZZT = echarts.init(document.getElementById('daybar'));
    // 指定柱状图的配置项和数据
		var zzt_option =  {
			color: ['#3398DB'],
            tooltip: {
            	trigger: 'axis',
                axisPointer: {
                	type: 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '7%',
                bottom: '3%',
                top: '20%',
                containLabel: true
            },
            xAxis: [
            	{
            		type : 'category',
                	data: dayList,
                	 name: '天/d',
                	axisTick: {
                        alignWithLabel: true
                    }
            	}
            ],
            yAxis: [
            	{
       	            type: 'value',
       	            name: '用水量(m³)',
       	            axisLabel: {
       	                formatter: '{value} m³'
       	            }
        		}
            ],
            series: [
            	{
	                name: '天累计用水量(m³)',
	                type: 'bar',
	                data: sumdaywaterList
            	}
        	]
		};


    // 使用指定的配置项和数据显示柱状图
    waterChart_ZZT.setOption(zzt_option);
</script>