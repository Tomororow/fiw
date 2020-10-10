<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>售水记录信息-水管区域方式</title>
		<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/saleWaterReport.js"></script>
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
		
		<div class="right_user">
			<div class="operate">
				<div class="" style="text-align: left;">
				   	<div id="timeSearchArea" style="float: left;">
				   		<span style="margin-left:10px">机井名称：</span>
				   		<input type="text" style="color:#adadad; width:110px;" value="${deviceName==null ? '请输入机井名称' : deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井名称'" />
			   	        
			   	        <span style="margin-left:10px;">水卡卡号：</span>
			   	        <input type="text" style="color:#adadad; width:120px;" value="${cardCode==null ? '请输入水卡卡号' : cardCode}" id="cardCode" value="请输入水卡卡号" onfocus="if(this.value == '请输入水卡卡号') this.value = '';" onblur="if(this.value == '') this.value = '请输入水卡卡号'" />
					    
					    <span style="margin-left:10px;">起始时间：</span>
						<input id="startTime" class="Wdate" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
							onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\') || \'%y-%M-%d %H:%m:%s\' }'})"/>
						
						<span>&nbsp;结束时间：</span>
						<input id="endTime" class="Wdate" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
							onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d %H:%m:%s'})"/>
					    
					    <input type="button" class="btn-search" value="查询" onclick="saleWaterLists()">
						<input type="button" class="btn-reset"  value="重置" onclick="saleWaterReset()">
						<input type="button" class="btn-export-report" value="信息导出" onclick="saleWaterReport()">
					</div>
				</div>
			</div>
			<div id="saleWaterContent"></div>
		</div>
	</body>
</html>