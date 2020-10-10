<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/content/skin/js/curveAnaly.js"></script>

<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<style>
#__echarts_download_wrap__ img{
	margin-top:80px
}
</style>
<script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/echarts.js"></script>
<script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'content/skin/adapters/echarts',
                'echarts/theme/macarons' : 'content/skin/adapters/echarts/theme/macarons',
                'echarts/theme/blue' : 'content/skin/adapters/echarts/theme/blue'
            }
        });
     	// 使用
        /* require(
            [
                'echarts',
                'echarts/chart/line' // 按需加载,如：使用柱状图就加载bar模块，
            ]) */
            
    </script>

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

<div class="right_user" id="caDiv">
	<div class="operate">
		<div class="" style="text-align: left;margin:5px 0 0 15px;">
			<div style="float: left;">
				<span style="margin-left:10px;">监测量：</span>
				
				<select id="factorType" class="select" style="float:none">
					<c:forEach items="${factorMap}" var="item">
						<option value="${item.key}" unit="${factorUnitMap[item.key]}">${item.value}</option>
					</c:forEach>
				</select>
				<select id="factorType_disable" class="select" style="display:none;float:none;color:#72777A" disabled="disabled">
					<option>请选择测站</option>
				</select>
			</div>
			
			<div style="float: left;">
				<span style="margin-left:10px;">查询类型：</span>
				<select id="searchType" class="select" onchange="showArea(this)" style="float:none">
					<option value="0" area="rawSearchArea">原始曲线</option>
					<option value="1" area="timeSearchArea">时段曲线</option>
					<option value="2" area="daySearchArea">日曲线</option>
					<option value="3" area="monthSearchArea">月曲线</option>
					<option value="4" area="yearSearchArea">年曲线</option>
				</select>
			</div>
			
			<div id="rawSearchArea" style="float: left;">
				<span style="margin-left:10px;">起始时间：</span>
				<input id="raw_query_beginTime" class="Wdate" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 00:00" />" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'raw_query_endTime\') ||\'%y-%M-%d %H:%m\' }'})" />
				<span>&nbsp;&nbsp;结束时间：</span> <input id="raw_query_endTime" class="Wdate"
				value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd HH:mm" />" type="text"
				onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'raw_query_beginTime\');}',maxDate:'%y-%M-%d %H:%m'})"/>
			</div>
			<div id="timeSearchArea" style="float: left;display:none">
				<span style="margin-left:10px;">起始时间：</span>
				<input id="query_beginTime" class="Wdate" value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd 00:00" />" type="text"
					onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:00',maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H\' }'})" />
				<span>&nbsp;&nbsp;结束时间：</span> <input id="query_endTime" class="Wdate"
				value="<fmt:formatDate value="${defaultTime}" pattern="yyyy-MM-dd HH:00" />" type="text"
				onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:00',minDate:'#F{$dp.$D(\'query_beginTime\');}',maxDate:'%y-%M-%d %H'})"/>
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
			
			<input type="button" class="btn-search" value="查 询" onclick="searchCurve()">
			<!-- border: none;height:26px;width: 50px; background-color: #40B5D6; color: #FFF;cursor: pointer;font-weight: bold; -->
		</div>

	</div>
	<div id="caContent" style="position: absolute; left: 0px; right: 0px; top: 35px;bottom: 5px;">
	
		<div id="eChartsMain" style="min-height:400px; padding-top:20px; position: absolute;  right: 0px; top: 0px; bottom: 20px; left: 0px;"></div>
		<div id="nodata" style="min-height:400px; padding-top:0px; position: absolute;  right: 0px; top: 0px; bottom: 0px; left: 0px;font-size:14px;font-family: '微软雅黑';color:#8C8C8C;text-align: center">
			暂无数据
		</div>
	
	</div>
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
