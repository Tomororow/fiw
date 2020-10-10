<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/content/skin/js/manageCard.js"></script>
<div class="right_user">
	<div class="operate">
		<div class="" style="text-align: left;">
			<span style="margin-left:10px;">发卡人姓名：</span>
	   		<input type="text" style="color:#adadad; width:125px;" value="${ownerName==null ? '请输入发卡人姓名' : ownerName}" id="ownerName" value="请输入发卡人姓名" 
	   			onfocus="if(this.value == '请输入发卡人姓名') this.value = '';" onblur="if(this.value == '') this.value = '请输入发卡人姓名'" />
	   		
		    <input type="button" class="btn-search" value="查询" onclick="manageCardInfoSearch()">
		</div>
	</div>
	<div id="manageCardContent"></div>
</div>