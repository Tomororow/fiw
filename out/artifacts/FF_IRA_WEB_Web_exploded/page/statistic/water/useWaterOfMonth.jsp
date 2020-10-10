<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/statistic.js" ></script>
<input type="hidden" value="">
<div class="right_user">
	<div class="operate">
		<div class="" style="text-align: left;">
		   	<div id="timeSearchArea" style="float: left;">
		   		<span style="margin-left:10px;">水管区域：</span>
		   		<select class="pop-select" id="waterAreaId" style="display:inline-block; width:108px;" name="waterAreaId">
		   			<option value="">--水管区域--</option>
		   			<c:forEach var="item" items="${sysWaterAreaList}">
		   				<option value="${item.id}">${item.waterAreaName}</option>
		   			</c:forEach>
		   		</select>
		   		
		   		<span style="margin-left:10px;">机井编码：</span>
	   	        <input type="text" style="color:#adadad; width:98px;" value="${deviceCode==null?'请输入机井编码':deviceCode}" id="dCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井编码';" /></a>
		   		
		   		<span style="margin-left:10px;">机井名称：</span>
	   	        <input type="text" style="color:#adadad; width:98px;" value="${deviceName==null?'请输入机井名称':deviceName}" id="dName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井名称';" /></a>
				
				<span style="margin-left:10px;">选择月份：</span>
				<input id="query_startTime" class="Wdate" style="width:80px;" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM" />" type="text"
					onfocus="WdatePicker({isShowClear:false, readOnly:true, dateFmt:'yyyy-MM'})" />
				<span style="margin-left:10px;">用水类型：</span>
				<select class="pop-select" id="deviceWellUse" name="deviceWellUse" style="display:inline-block; width:118px;">
					<option value="">--请选择--</option>
					<c:forEach var="item" items="${wellList}">
						<option value="${item.wellUse}">${item.wellUse}</option>
					</c:forEach>
				</select>
				<span style="margin-left:10px;">设备类型：</span>
				<select class="pop-select" id="deviceType" name="deviceType" style="display:inline-block; width:118px;">
					<option value="">--请选择--</option>
					<option value="1">金志</option>
					<option value="2">金讯润泽</option>
				</select>
			    <input type="button" class="btn-search" value="查询" onclick="useWaterList()">
				<input type="button" class="btn-export-report" value="用水报表" onclick="useWaterReport('month')">
			</div>
		</div>
	</div>
	<div id="useWaterContent"></div>
</div>

<script type="text/javascript">
	$(function() {
		getGroupTree("month");
	});
</script>