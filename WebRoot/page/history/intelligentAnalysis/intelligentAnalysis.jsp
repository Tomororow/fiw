<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/content/skin/js/intelligentAnalysisReport.js"></script>
<div class="right_user">
	<div class="operate">
		<div class="" style="text-align: left;">
		  	<div id="timeSearchArea" style="float: left;">
		   		<span style="margin-left:10px;">机井编码：</span>
   	            <input type="text" style="color:#adadad" value="${deviceCode==null?'请输入机井编码':deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井编码';" />
	            
	            <span style="margin-left:10px;">机井名称：</span>
   	            <input type="text" style="color:#adadad" value="${deviceName==null?'请输入机井名称':deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井名称';" />
		   		
			    <input type="button" class="btn-search" value="查 询" onclick="intelligentAnalysisLists()">
				<input type="button" class="btn-reset"  value="重置" onclick="intelligentAnalysisReset()">
			</div>
		</div>
	</div>
	<div id="intelligentAnalysisContent"></div>
</div>
