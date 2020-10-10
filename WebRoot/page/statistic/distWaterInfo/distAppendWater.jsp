<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/content/skin/js/distAppendWaterReport.js"></script>
<div class="right_user">
<div class="operate">
	<div class="" style="text-align: left;">
	   	<div id="timeSearchArea" style="float: left;">
	   		<span style="margin-left:10px;">用水类型：</span>
	   		<select class="pop-select" id="wellUse" style="display: inline-block;width:118px;" name="wellUse">
				<option value="">--请选择--</option>
	   			<c:forEach var="item" items="${wellList}">
	   				<option value="${item.wellUse}">${item.wellUse}</option>
	   			</c:forEach>
	   		</select>
	   		
	   		<span style="margin-left:10px;">配水年份：</span>
	   		<input id="distYearStart" class="Wdate" value="<fmt:formatDate value='${distYearStart}' pattern='yyyy' />" 
	   			type="text" onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy',maxDate:'#F{$dp.$D(\'distYearEnd\') ||\'%y\' }'})" />
		    -
		    <input id="distYearEnd" class="Wdate" value="<fmt:formatDate value='${distYearEnd}' pattern='yyyy' />" 
		    	type="text" onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy',minDate:'#F{$dp.$D(\'distYearStart\') ||\'%y\' }'})" />
		    
		    <input type="button" class="btn-search" value="查询" onclick="distAppendWaterLists()">
			<input type="button" class="btn-reset"  value="重置" onclick="distAppendWaterReset()">
		</div>
	</div>
</div>
<div id="distAppendWaterContent"></div>
</div>