<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>其他费用(基本水费-计量水费-水资源费-末级渠系水费)-首页</title>
	<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/waterSourceChargeInfo.js"></script>
</head>

<body>
	<!-- 左侧树形菜单栏 -->
	<div class="left">
		<div class="leftTree" style="padding-left:2px;padding-top:5px;overflow-y:auto; overflow-x:hidden;">
			<ul id="ztree" class="ztree"></ul>
		</div>
	</div>
	
	<!-- 左侧树形菜单与右边显示界面之间的开/关左侧树形菜单栏按钮 -->
	<div class="leftnav">
		<a href="javascript:void(0)">
			<img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" />
		</a>
	</div>
	
	<!-- 右侧内容显示界面div -->
	<div class="right_user">
		<div class="operate">
			<div class="" style="text-align: left;">
		    	<div id="timeSearchArea" style="float: left;">
					<span style="margin-left:10px;">机井编码：</span>
			        <input type="text" style="color:#adadad; width:110px;" value="${deviceCode==null ? '请输入机井编码' : deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井编码'" />
					
					<span style="margin-left:10px;">机井名称：</span>
			   		<input type="text" style="color:#adadad; width:110px;" value="${deviceName==null ? '请输入机井名称' : deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井名称'" />

				    <span style="margin-left:10px;">起始时间：</span>
					<input id="startTime" class="Wdate" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
						onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\') || \'%y-%M-%d %H:%m:%s\' }'})"/>
					
					<span style="margin-left:10px;">&nbsp;结束时间：</span>
					<input id="endTime" class="Wdate" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
						onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d %H:%m:%s'})"/>
				    
				    <span style="margin-left:10px;">用水类型：</span>
	  	            <select class="pop-select" id="deviceWellUse" name="deviceWellUse" style="display:inline-block; width:118px;">
			   			<option value="">--请选择--</option>
			   			<c:forEach var="item" items="${wellList}">
			   				<option value="${item.wellUse}">${item.wellUse}</option>
			   			</c:forEach>
			   		</select>
				    
				    <input type="button" class="btn-search" style="width:62px;margin-bottom:10px;" value="查 询" onclick="waterSourceChargeList()">
					<input type="button" class="btn-reset"  value="重置" onclick="waterSourceChargeReset()">
					<input type="button" class="btn-export-report" value="信息报表" onclick="waterAmountReport()">
				</div>
			</div>
		</div>
		<div id="waterSourceChargeInfoContent"></div>
	</div>
</body>
</html>