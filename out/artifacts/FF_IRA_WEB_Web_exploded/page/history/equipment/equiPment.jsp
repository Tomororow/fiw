<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/content/skin/js/equiPment.js"></script>
<div class="right_user">
	<div class="operate">
		<div class="" style="text-align: left;">
		  	<div id="timeSearchArea" style="float: left;">
		  		<span style="margin-left:10px;">机井名称：</span>
	   	        <input type="text" style="color:#adadad; width:120px;" value="${deviceName==null?'请输入机井名称':deviceName}" id="deviceName" value="请输入机井名称" 
	   	        	onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井名称';" />
				<span style="margin-left:10px;">机井编码：</span> 
				<input type="text" style="color:#adadad;width:120px;"  value="${deviceCode==null ? '请输入机井编码' : deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井编码'" />
			   <span style="margin-left:10px;">起始时间：</span>
				<input id="query_startTime" class="Wdate" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 00:00:00" />" type="text"
					onfocus="WdatePicker({isShowClear:false, readOnly:true, dateFmt:'yyyy-MM-dd HH:mm', maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H:%m\' }'})" />
				
				<span>&nbsp;&nbsp;结束时间：</span>
				<input id="query_endTime" class="Wdate" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
					onfocus="WdatePicker({isShowClear:false, readOnly:true, dateFmt:'yyyy-MM-dd HH:mm', maxDate:'%y-%M-%d %H:%m'})"/>
			
				    <span style="margin-left:10px;">告警类型：</span>
	 	        <select class="pop-select" id="alarmType" name="alarmType" style="display:inline-block; width:118px;">
		   			<option value="">--请选择--</option>
		   			<c:forEach var="item" items="${TypeList}">
		   				<option value="${item.getKey()}">${item.getValue()}</option>
		   			</c:forEach>
		   		</select>
			    <input type="button" class="btn-search" value="查 询" onclick="equiPmentList(1)">
				<!-- <input type="button" class="btn-reset"  value="重置" onclick="intelligentDealReset()"> -->
				<input type="button" class="btn-export-report" value="信息导出" onclick="equipmentexport()">
			</div>
		</div>
	</div>
	<div id="equiPmentContent"></div>
</div>