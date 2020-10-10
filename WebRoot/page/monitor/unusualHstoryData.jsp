<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<div class="right_user" style="left:15px;top:50px">
	<div id="dataHistoryDiv">
		<div class="operate">
			<div style="text-align: left; margin-top: 5px;">
			   	<div id="timeSearchArea" style="float: left;">
					<span style="margin-left:10px;">起始时间：</span> 
					<input id="query_beginTime" class="Wdate" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H:%m:%s\' }'})" />
					
					<span>&nbsp;结束时间：</span> 
					<input id="query_endTime" class="Wdate" value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd HH:mm:ss" />"  type="text"
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d %H:%m:%s'})"/>
					<!-- <input type="text" id="query_cardCode" placeholder="请输入卡号"/> -->
					<input type="button" class="btn-search" value="查询"	onclick="unusualHstoryList('${deviceCode}','${disn}')">
				</div>
			</div>
		</div>
		<div id="unusualHstoryDataContent"></div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		// 加载全部信息
		unusualHstoryList("${deviceCode}","${disn}");
	});
</script>