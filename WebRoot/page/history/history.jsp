<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<input type="hidden" value="${pStcd}" id="pStcd">
<div class="right_user">
<div class="operate">
	<div class="" style="text-align: left;">
	   	<div id="timeSearchArea" style="float: left;">
	   	        <span style="margin-left:10px;">测站编码：</span>
	   	        <input type="text" style="color:#adadad" value="${stcd==null?'请输入测站编码':stcd}" id="dstcd" value="请输入测站编码" onfocus="if(this.value == '请输入测站编码') this.value = '';" onblur="if(this.value =='') this.value = '请输入测站编码';" /></a>
				<span style="margin-left:10px;">起始时间：</span>
				<input id="query_beginTime" class="Wdate" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 00:00" />" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H:%m\' }'})" />
				<span>&nbsp;&nbsp;结束时间：</span> <input id="query_endTime" class="Wdate"
				value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd HH:mm" />" type="text"
				onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'query_beginTime\');}',maxDate:'%y-%M-%d %H:%m'})"/>
			    
			    <input type="button" class="btn-search" value="查 询" onclick="historyList()">
				<input type="button" class="btn-reset" value="重置" onclick="historyReset()">
				<input type="button" class="btn-export" value="导出" onclick="historyExport()">
		</div>
	</div>
</div>
<div id="historyContent"></div>
</div>

<script type="text/javascript">
	$(function() {
		 getGroupTree();
	});
</script>