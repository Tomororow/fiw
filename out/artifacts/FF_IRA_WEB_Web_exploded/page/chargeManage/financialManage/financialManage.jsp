<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/content/skin/adapters/vue/vue.js"></script>
<script src="${ctx}/content/skin/adapters/elementui/1.4.3/index.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/js/financialManage.js"></script>
<link rel="stylesheet" href="${ctx}/content/skin/adapters/elementui/1.4.3/theme-default/index.css">
<link rel="stylesheet" href="${ctx}/content/skin/css/recharge.css"/>
<script type="text/javascript" src="${ctx}/content/skin/adapters/vue/moment.js"></script>
<style>
 .list-page{text-align: right;padding: 15px 30px 20px 0}
</style>

<!-- 左侧树形菜单栏 -->
<div class="left" id="ETree">
	<div class="leftTree" style="padding-left:2px;padding-top:5px;overflow-y:auto; overflow-x:hidden;">
		<ul id="ztree" class="ztree"></ul>
	</div>
</div>
<!-- 左侧树形菜单与右边显示界面之间的开/关左侧树形菜单栏按钮 -->
<div class="leftnav">
	<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
</div>
<div class="right_user" id="financial">
	<div class="operate">
		<div style="line-height:32px;">
			<span style="margin-left:10px;">机井名称：</span>
	   		<input type="text" style="color:#adadad" v-model="deviceName" placeholder="请输入机井名称" value="${deviceName==null ? '请输入机井名称' : deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井名称'" />
		    <span style="margin-left:10px;">起始时间：</span>
			<input id="startTime" class="Wdate" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
			onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\') || \'%y-%M-%d %H:%m:%s\' }'})"/>
			<span>&nbsp;结束时间：</span>
			<input id="endTime" class="Wdate" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
			onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d %H:%m:%s'})"/>
			<span>&nbsp;配水年份：</span>
			<input id="distYear"  value="" name="distYear" v-model="distYear" type="text" style="color:#adadad;width: 100px" placeholder="请输入配水年份"  /> 
			<span>&nbsp;配水轮次：</span>
			<input id="distRound"  value="" name="distRound" v-model="distRound" type="text" style="color:#adadad;width: 100px" placeholder="请输入配水轮次"  /> 
		    <input type="button" class="btn-search" value="查 询" @click="baseWater();">
		    <!-- <input type="button" class="btn-reset"  value="重置" @click="baseWater(2);"> -->
		    <input type="button" class="btn-export-report" value="报表导出" @click="dialogVis">
	   		<!-- <el-button id="reportBtu" @click="dialogVis" size="small" ><i class="el-icon-document" style="color:burlywood;"></i>报表导出</el-button>  -->
			<span id="loginUser"></span>
		</div>
	</div>
	<!-- 列表显示 -->
	<div id="baseWaterChargeContent"></div>
	<div  style="position: relative;margin-top: 50px;"></div>
		<table cellspacing='0'  cellpadding='3' class='list-table' id="list-table">
		<thead style="font-size: 14px;font-weight: bold;">
		<tr>
			<td>卡号</td>
			<!-- <td>订单编号</td> -->
			<td>机井编码</td>
			<td>机井名称</td>
			<td>配水年份</td>
			<td>配水轮次</td>
			<td>水方单价 (元)</td>
			<td>总用水量</td>
			<td>总金额 (元)</td>
			<td>时间</td>
		</tr>
		</thead>
		<tbody style="height:35px;background-color: #ffffff;" >
		<tr v-for="fl in financList.slice((currentPage-1)*pagesize,currentPage*pagesize)">
			<td>{{fl.cardcode}}</td>
			<!-- <td>{{fl.ordercode}}</td> -->
			<td>{{fl.devicecode}}</td>
			<td>{{fl.devicename}}</td>
			<td>{{fl.distyear}}</td>
			<td>{{fl.distround}}</td>
			<td>{{fl.distprice}}</td>
			<td>{{fl.sumWater}}</td>
			<td>{{fl.sumPrice}}</td>
			<td id="createtime" >{{dateFormat(fl.createtime)}}</td>
		</tr>
		</tbody>
	</table>
	<div class="list-page" align="right">
			<el-pagination background  @size-change="handleSizeChange" :page-size="pagesize"  :current-page="currentPage"    @current-change="handleCurrentChange" layout="prev, pager, next" :total="financList.length"></el-pagination>
	</div>
	<!-- <el-dialog :modal = "false" id="fnTop" :visible.sync="dialogVisible" style="margin:auto;height:auto;min-width:250px;" >
		左侧选择栏
		<div style="height:auto;width:auto;border:solid 1px #ada6a6;min-height:240px;">
			<el-collapse v-model="activeName" accordion>
			 按水管区域选择
			  <el-collapse-item title="按水管区域选择" name="1">
			 		<ul id="ztreeTwo" class="ztree"></ul>
			  </el-collapse-item>
			  按月份选择
			  <el-collapse-item title="按月份选择" name="2">
			  	  <el-date-picker id="pickerOne"  v-model="datePickerMonth" type="month" placeholder="点击选择月份" style=""></el-date-picker>
			  </el-collapse-item>
			  按年份选择
			  <el-collapse-item title="按年份选择" name="3">
			  		<el-date-picker id="pickerTwo"  v-model="datePickerYear" type="year" placeholder="点击选择年份" style=""></el-date-picker>
			  </el-collapse-item>
			  按季度选择
			  <el-collapse-item title="按季度选择" name="4">
			  	<template>
				  <el-tabs v-model="quarterName" align="center">
				    <el-tab-pane label="第一季度" name="1">1月 ~ 3月</el-tab-pane>
				    <el-tab-pane label="第二季度" name="2">4月 ~ 6月</el-tab-pane>
				    </el-tabs>
				 </template>
				 <template>
				  <el-tabs v-model="quarterName" align="center">
				    <el-tab-pane label="第三季度" name="3">7月 ~ 9月</el-tab-pane>
				    <el-tab-pane label="第四季度" name="4">10月 ~ 12月</el-tab-pane>
				    </el-tabs>
				 </template>
			  </el-collapse-item>
	  		</el-collapse>
		</div>
		<el-button type="success" @click="tueExport(activeName);" size="mini" style="position:relative;top:10px;left:70%;background:#18B399;">确定导出</el-button>
	</el-dialog> -->
	
	<el-dialog
	  title="提示"
	  :visible.sync="dialogVisible"  style="margin:auto;height:auto;width:600px;">
	  <span>确定要导出该报表？</span>
	  <span slot="footer" class="dialog-footer">
	    <el-button @click="dialogVisible = false">取 消</el-button>
	    <el-button type="primary" @click="tueExport(activeName);">确 定</el-button>
	  </span>
	</el-dialog>
</div>
 <ul id="ztreeTwo" class="ztree"></ul>
<script type="text/javascript">
	var myDate = new Date();//获取系统当前时间
	myDate.getFullYear(); //获取完整的年份(4位,1970-????)
	$(function(){
		var myDate = new Date();//获取系统当前时间
		var year=myDate.getFullYear(); //获取完整的年份(4位,1970-????)
		$("#distYear").val(year);
	});
	
	
</script>
