<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">
	$(function() {
		hideMark();
		var mapObj = $.Map.Init();
		var devList = null;
		var devList = eval("(" + '${devList}' + ")");
		debugger
		$.Map.SetMarker(mapObj, devList);
		var bTop = $(".map_button").position().top;
		var bHeight = $(".map_button").height();
		$(".map_right").css("top", bTop + bHeight - 10);
		$(".show-type li").click(function() {
			var m = $(this), s = m.siblings();
			m.addClass("sell");
			s.removeClass("sell");
			var type = m.attr("data");
			switch (type) {
				case "2d":
					$.Map.SetLayers(mapObj, type);
					break;
				case "satellite":
					$.Map.SetLayers(mapObj, type);
				break;
			}
		});
	})
</script>

<div id="map" >
<ul class="show-type">
    <li class="sell" data="2d">地图</li>
    <li data="satellite">卫星</li>
</ul>

<div class="map-legend">
    <span class="legend-title">图例介绍</span>
    <ul class="legend-icon">
        <li class="map-legend-online">
            <span>在线</span>
        </li>
        <li class="map-legend-onlineAlarm">
            <span>在线报警</span>
        </li>
        <li class="map-legend-offline">
            <span>离线</span>
        </li>
    </ul>
</div>

</div>
<div class="map_button" onclick="changeListPage()">
	<!-- 实<br/>时<br/>数<br/>据 -->
</div>
<div class="map_right" style="display:none; z-index:500; height: auto;">
	<div class="map_right_top">
		<table border="0" >
			<tr>
				<td width="98%" style="font-size:14px; font-weight:bold">实时监控</td>
				<td width="2%"><img src="${ctx}/content/skin/css/images/map-close.png" width="15" onclick="changeListPage()" style="cursor: pointer;"
					height="15" /></td>
			</tr>
		</table>
	</div>
	<div class="operate" style="margin-left: 15px; margin-right: 15px;">
		<div id="timeSearchArea" style="height:35px; background-color:#D1EBFB; ">
			<span style="margin-left:10px;line-height: 35px;">机井编码：</span>
			<input style="line-height:15px;" type="text" value="${deviceCode==null?'请输入机井编码':deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井编码';" />
			<span style="margin-left:10px;">机井名称：</span>
			<input type="text" value="${deviceName==null?'请输入机井名称':deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value =='') this.value = '请输入机井名称';" />
			<input type="button" class="btn-search" value="查 询" onclick="searchDeviceCode()">
		</div>
	</div>
	<div class="map_right_content" style="margin-top: 20px;">
	  
	</div>
</div>