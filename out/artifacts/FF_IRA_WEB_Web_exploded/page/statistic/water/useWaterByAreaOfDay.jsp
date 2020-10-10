<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/statistic.js" ></script>
<input type="hidden" value="">
<div class="right_user">
	<div class="operate">
		<div class="" style="text-align: left;">
		   	<div id="timeSearchArea" style="float: left;">
		   		<span style="margin-left:10px;">机井编码：</span>
	   	        <input type="text" style="color:#adadad; width:98px;" value="${deviceCode==null?'请输入机井编码':deviceCode}" id="dCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井编码';" /></a>
		   	
		   		<span style="margin-left:10px;">机井名称：</span>
	   	        <input type="text" style="color:#adadad; width:98px;" value="${deviceName==null?'请输入机井名称':deviceName}" id="dName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井名称';" /></a>
				
				<span style="margin-left:10px;">起始时间：</span>
				<input id="query_startTime" class="Wdate" style="width:100px;" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd" />" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d\' }'})" />
				
				<span>&nbsp;结束时间：</span>
				<input id="query_endTime" class="Wdate" style="width:100px;" value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd" />" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
			    
			     <input type="button" class="btn-search" value="查询" onclick="useWaterList()">
				<input type="button" class="btn-export-report" value="用水报表" onclick="useWaterReport('day')">
			</div>
		</div>
	</div>
	<div id="useWaterContent"></div>
</div>

<script type="text/javascript">
	$(function() {
		getGroupTree("day");
	});
</script>