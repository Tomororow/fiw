<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!-- 当点击地图上的机井点，选择里面图片预览的按钮，弹出框的样式设置 -->
<style type="text/css">
	#tinybox{position:absolute; display:none; padding:10px; background:#ffffff url(../image/preload.gif) no-repeat 50% 50%; border:10px solid #e3e3e3; z-index:2000;}
	#tinymask{position:absolute; display:none; top:0; left:0; height:100%; width:100%; background:#000000; z-index:1500;}
	#tinycontent{background:#ffffff; font-size:1.1em;}
</style>
<!-- 引用弹出框的js -->
<script type="text/javascript" src="${ctx}/content/skin/js/tinybox.js"></script>
<div class="right_user" id="mapContent" style="top:115px;right:0px;left:237px;overflow-y:hidden;">

</div>
<script>
	$(function() {
		 getGroupTree();
	});
</script>