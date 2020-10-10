<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/core/style.css">
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/zingchart.min.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/zingchart.jquery.js"></script>

<div class="right_user" style="left:15px; top:35px">
	<div>
		<h1>用水量总指标：1.5亿</h1>
		<div>
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<tr>
					<td class="table-left" style="width:30%;">城区用水量：</td>
					<td class="table-right">
						<fmt:formatNumber type="number" value="${gzqUseWaterSum}" pattern="0.00" maxFractionDigits="2"/>吨
					</td>
				</tr>
				<tr>
					<td class="table-left">沙井镇用水量：</td>
					<td class="table-right">
						<fmt:formatNumber type="number" value="${sjzUseWaterSum}" pattern="0.00" maxFractionDigits="2"/>吨
					</td>
				</tr>
				<tr>
					<td class="table-left">明永乡用水量：</td>
					<td class="table-right">
						<fmt:formatNumber type="number" value="${myxUseWaterSum}" pattern="0.00" maxFractionDigits="2"/>吨
					</td>
				</tr>
				<tr>
					<td class="table-left">乌江镇用水量：</td>
					<td class="table-right">
						<fmt:formatNumber type="number" value="${wjzUseWaterSum}" pattern="0.00" maxFractionDigits="2"/>吨
					</td>
				</tr>
				<tr>
					<td class="table-left">用水合计：</td>
					<td class="table-right">
						<fmt:formatNumber type="number" value="${useWaterSum}" pattern="0.00" maxFractionDigits="2"/>吨
					</td>
				</tr>
			</table>
		</div>
	</div>
	<HR style="FILTER:alpha(opacity=100, finishopacity=0, style=3); margin-top:10px; margin-bottom:10px;" width="100%" color=#987cb9 SIZE=3>
	
	<div>
		<h1>地下水位监测</h1>
		<div>
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<tr>
					<td class="table-left" style="width:30%;">站点1：</td>
					<td class="table-right">540.47</td>
				</tr>
				<tr>
					<td class="table-left">站点2：</td>
					<td class="table-right">720.44</td>
				</tr>
				<tr>
					<td class="table-left">站点3：</td>
					<td class="table-right">220.68</td>
				</tr>
			</table>
		</div>
	</div>
	<HR style="FILTER:alpha(opacity=100, finishopacity=0, style=3); margin-top:10px; margin-bottom:10px;" width="100%" color=#987cb9 SIZE=3>
	
	<div>
		<h1>指标统计图</h1>
		<table style="width:1; height:100; solid:#eee;">
			<tr>
				<td style="padding-left:50px;">
					<div id="ex_1" class="zingchart" style="height:500px;width:700px;"></div>
				</td>
				<td>
					<div id="ex_2" class="zingchart" style="height:500px;width:700px;"></div>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" value="${useWaterSum}" id="useWaterSum" />
</div>
<script type="text/javascript">
	$(function () {
		var useWaterSum = ${useWaterSum};
		var ex1 = {
	   		"title" : {text: "用水指标比较图表"},  
			"type": "bar",
			"legend":{},  
	        "tooltip" : {text : "%t %k 用水量：%v" },//%t系列，%k横轴，%v值  
			"source" : {text : "张掖金志信息技术有限公司技术提供"},//右下角说明 
			"scaleX" : {label : { text : "水量描述" }, values : ["用水总量"]},
			"scaleY" : {label : { text : "水量指标" }},
			"fullscreen":true,  
               "hideprogresslogo":true,
			"series": [  
                { text: "用水总量（万吨）", values:[useWaterSum/10000]},  
                { text: "指标水量（万吨）", values:[15000]}
           	]  
		};
		var monthData = ${useWaterMonthList};
		var ex2 = {
	   		"title" : {text: "用水指标比较图表"},  
			"type": "line",
			"legend":{},  
	        "tooltip" : {text : "%t %k 用水量：%v" },//%t系列，%k横轴，%v值  
			"source" : {text : "张掖金志信息技术有限公司技术提供"},//右下角说明 
			"scaleX" : {label : { text : "月份" }, values : ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"]},
			"scaleY" : {label : { text : "水量指标" }},
			"fullscreen":true,  
               "hideprogresslogo":true,
			"series": [
		         {text:"用水合计（万吨）", "values":[monthData[0]/10000,monthData[1]/10000,monthData[2]/10000,monthData[3]/10000,monthData[4]/10000,monthData[5]/10000,monthData[6]/10000,monthData[7]/10000,monthData[8]/10000,monthData[9]/10000,monthData[10]/10000,monthData[11]/10000]},
	      	]
		};
 		// render example one
   	 	$('#ex_1').zingchart({
        	data:ex1
    	});
    	$('#ex_2').zingchart({
        	data:ex2
    	});
	});
</script>