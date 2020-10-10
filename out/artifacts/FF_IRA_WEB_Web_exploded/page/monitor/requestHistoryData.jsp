<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<input class="eign" value="${eign}" hidden="hidden"/>
<div class="right_user" style="left:15px;top:50px">
	<div id="dataHistoryDiv"><!-- cardList -->
		<div class="operate">
			<div style="text-align: left; margin-top: 5px;">
			   	<div id="timeSearchArea" style="float: left;">
					<span style="margin-left:10px;">起始时间：</span> 
					<input id="query_beginTime" class="Wdate" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H:%m:%s\' }'})" />
					
					<span>&nbsp;结束时间：</span> 
					<input id="query_endTime" class="Wdate" value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd HH:mm:ss" />"  type="text"
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d %H:%m:%s'})"/>
					<c:if test="${eign=='0'}">
						<span>&nbsp;卡号：</span>
						<select class="pop-select" id="query_cardCode" name="query_cardCode" style="display:inline-block; width:145px;height: 26px;">
			   				<option value="">--请选择--</option>
				   			<c:forEach var="item" items="${cardList}">
				   				<option value="${item.cardCode}">${item.cardCode}</option>
				   			</c:forEach>
			   			</select>
					</c:if>
					<input type="button" id="historyConditionBtn" class="btn-search" value="查询" onclick="requestHistoryDataList('${deviceCode}','${eign}')">
					<c:if test="${eign!='0'}">
						<input type="button" class="btn-reset" value="刷新"	onclick="breakConditionList('${deviceCode}')">
					</c:if>
				</div>
			</div>
		</div>
		<div id="requestHistoryDataContent"></div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		debugger
		var vityu = $(".eign").val();
		if(vityu=='0'){//上下卡历史记录查询
			// 加载全部信息
			requestHistoryDataList('${deviceCode}','${eign}');
		}
	});
</script>