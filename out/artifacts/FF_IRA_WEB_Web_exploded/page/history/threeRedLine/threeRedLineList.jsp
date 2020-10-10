<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<div class="right_user" style="left:15px; top:50px">
	<div>
		<h1>水资源开发利用控制红线</h1>
		<div>
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th>所属区域</th>
						<th>三条红线指标</th>
						<th>已用水量</th>
						<th>差额</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>甘州区</td>
						<td>1.5亿吨</td>
						<td>
							<fmt:formatNumber type="number" value="${useWaterSum}" pattern="0.00" maxFractionDigits="2"/>吨</td>
						<td>
							<fmt:formatNumber type="number" value="${usedWaterNum}" pattern="0.00" maxFractionDigits="2"/>吨
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<HR style="FILTER:alpha(opacity=100, finishopacity=0, style=3); margin-top:10px; margin-bottom:10px;" width="100%" color=#987cb9 SIZE=3>
	
	<div style="margin-top:50px">
		<h1>水效率控制红线</h1>
		<div>
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th>当前效率达标指数</th>
						<th>效率达标下限</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>0.58</td>
						<td>0.55</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<HR style="FILTER:alpha(opacity=100, finishopacity=0, style=3); margin-top:10px; margin-bottom:10px;" width="100%" color=#987cb9 SIZE=3>
	
	<div style="margin-top:50px">
		<h1>水功能区限制纳污红线</h1>
		<div>
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th>当前排污总量</th>
						<th>排污总量上限</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>125</td>
						<td>5500</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<HR style="FILTER:alpha(opacity=100, finishopacity=0, style=3); margin-top:10px; margin-bottom:10px;" width="100%" color=#987cb9 SIZE=3>
</div>