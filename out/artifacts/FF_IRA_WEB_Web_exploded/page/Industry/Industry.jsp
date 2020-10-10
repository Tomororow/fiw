<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/content/skin/js/Industry.js"></script>
<div class="right_user">
	<div class="operate">
		<div class="" style="text-align: left;">
		  	<div id="timeSearchArea" style="float: left;">
		  		<span style="margin-left:10px;">机井名称：</span>
	   	        <input type="text" style="color:#adadad; width:120px;" value="${deviceName==null?'请输入机井名称':deviceName}" id="deviceName" value="请输入机井名称" 
	   	        	onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井名称';" />
		   		<%-- <span style="margin-left:10px;">水卡卡号：</span>
				<input type="text" style="color:#adadad" value="${cardCode==null ? '请输入水卡卡号' : cardCode}" id="cardCode" value="请输入水卡卡号" onfocus="if(this.value == '请输入水卡卡号') this.value = '';" onblur="if(this.value == '') this.value = '请输入水卡卡号'" /> --%>

			   <span style="margin-left:10px;">起始时间：</span>
				<input id="query_startTime" class="Wdate" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd 00:00:00" />" type="text"
					onfocus="WdatePicker({isShowClear:false, readOnly:true, dateFmt:'yyyy-MM-dd HH:mm', maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H:%m\' }'})" />
				
				<span>&nbsp;&nbsp;结束时间：</span>
				<input id="query_endTime" class="Wdate" value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
					onfocus="WdatePicker({isShowClear:false, readOnly:true, dateFmt:'yyyy-MM-dd HH:mm', maxDate:'%y-%M-%d %H:%m'})"/>
			    

			    <input type="button" class="btn-search" value="查 询" onclick="industryinfoList()">
				<!-- <input type="button" class="btn-reset"  value="重置" onclick="intelligentDealReset()"> -->
				<input type="button" class="btn-export-report" value="信息导出" onclick="voltageReport()">
			</div>
		</div>
	</div>
	<div id="IndustryContent"></div>
</div>