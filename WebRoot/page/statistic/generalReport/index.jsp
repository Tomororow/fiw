<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/content/skin/js/generalReport.js"></script>

<div class="left" style="">
	<div class="tree">
		<ul id="ztree" class="ztree"></ul>
	</div>
</div>

<div class="leftnav" id="leftnav">
	<a href="javascript:void(0)"> 
		<img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" />
	</a>
</div>

<div class="right_user">
	<div class="operate">
		<div class="" style="text-align: left;margin:5px 0 0 15px;">
			
			<select id="searchType" class="select" onchange="showArea(this)">
				<option value="1" area="timeSearchArea">按时段查询</option>
				<option value="2" area="daySearchArea">按日查询</option>
				<option value="3" area="monthSearchArea">按月查询</option>
				<option value="4" area="yearSearchArea">按年查询</option>
			</select>
			
			<div id="timeSearchArea" style="float: left;">
				<span style="margin-left:10px;">起始时间：</span>
				<input id="query_beginTime" class="Wdate" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 00:00" />" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H:%m\' }'})" />
				<span>&nbsp;&nbsp;结束时间：</span> <input id="query_endTime" class="Wdate"
				value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd HH:mm" />" type="text"
				onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'query_beginTime\');}',maxDate:'%y-%M-%d %H:%m'})"/>
			</div>
			<div id="daySearchArea" style="float: left;display:none">
				<span style="margin-left:10px;">日期：</span>
				<input class="Wdate query_Time" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd" />" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" />
			</div>
			<div id="monthSearchArea" style="float: left;display:none">
				<span style="margin-left:10px;">月份：</span>
				<input class="Wdate query_Time" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM" />" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM',maxDate:'%y-%M'})" />
			</div>
			<div id="yearSearchArea" style="float: left;display:none">
				<span style="margin-left:10px;">年份：</span>
				<input class="Wdate query_Time" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy" />" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy',maxDate:'%y'})" />
			</div>
			
			<input type="button" class="btn-search" value="查 询" onclick="searchList()">
				
			<input type="button" class="btn-export" value="导 出" onclick="exportReportForm(this)">
			
			<!-- border: none;height:26px;width: 50px; background-color: #40B5D6; color: #FFF;cursor: pointer;font-weight: bold; -->
		</div>

	</div>
	<div id="genReportContent"></div>
</div>

<script type="text/javascript">
	$(function() {
		$(".leftnav").unbind('click').click(function() {
			if ($(".left").css("display") == "none") {
				$(".leftnav").css("left", "237px");
				$(".leftnav img").attr("src", "content/skin/css/images/botton-closeLeft.gif");
				$(".right_user").css("left", "275px");
			} else {
				$(".leftnav").css("left", "0px");
				$(".leftnav img").attr("src", "content/skin/css/images/botton-closeRight.gif");
				$(".right_user").css("left", "30px");
			}
			$(".left").toggle();
			$(".left-bottom").toggle();
		});
		
	});
</script>
