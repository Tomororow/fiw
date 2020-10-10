<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!-- 基本水费收缴信息头部 -->

<script type="text/javascript" src="${ctx}/content/skin/js/baseWaterChargeInfo.js" charset="utf-8"></script>

<!-- 左侧树形菜单栏 -->
<div class="left">
	<div class="leftTree" style="padding-left:2px;padding-top:5px;overflow-y:auto; overflow-x:hidden;">
		<ul id="ztree" class="ztree"></ul>
	</div>
</div>
<!-- 左侧树形菜单与右边显示界面之间的开/关左侧树形菜单栏按钮 -->
<div class="leftnav">
	<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
</div>

<div class="right_user">
	<div class="operate">
		<ul>
			<li class="add" onclick="addBaseWaterCharge(this)" style="line-height:20px;"><font style="font-size: 15px;">新增</font></li>
			<li class="del" onclick="delBaseWaterCharge()">删除</li>
		</ul>
		<div style="line-height:32px;">
			<span style="margin-left:10px;">机井编码：</span>
	        <input type="text" style="color:#adadad; width:110px;" value="${deviceCode==null ? '请输入机井编码' : deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井编码'" />
			
			<span style="margin-left:10px;">机井名称：</span>
	   		<input type="text" style="color:#adadad; width:110px;" value="${deviceName==null ? '请输入机井名称' : deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井名称'" />
		    
		    <input type="button" class="btn-search" value="查 询" onclick="baseWaterChargeList(this)">
			<input type="button" class="btn-reset"  value="重置" onclick="baseWaterChargeReset()">
			<input type="button" class="btn-export-report" value="信息报表" onclick="baseWaterPriceReport()">
		</div>
	</div>
	<div id="baseWaterChargeContent"></div>
</div>
<script type="text/javascript">
	$(function() {
		getGroupTree();
	});
</script>