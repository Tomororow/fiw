<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<style>
	.select {
	    float: left;
	    height: 26px;
	    margin: 0px;
	    width: 120px;
	    font-size: 12px;
	    color: #555;
	    vertical-align: middle;
	    background-color: #fff;
	    background-image: none;
	    border: 1px solid #ccc;
	    border-radius: 3px;
	    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
	    box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
	    -webkit-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	}
</style>
	
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/adapters/echarts/echarts1.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/statistic.js" ></script>

<!--二级头部  -->
<div class="header">
	<jsp:include page="/twoHeader.jsp"></jsp:include>
</div>

<!-- ztree -->
   <div class="left">
  <div class="tree"><ul id="ztree" class="ztree"></ul></div>
</div>

<div class="leftnav">
	<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
</div>
<!--内容  -->
<div id="twoContain"></div>

<script type="text/javascript">
	$(function() {
		$(".leftnav").click(function() {
			if ($(".left").css("display") == "none") {
				$(".leftnav").css("left", "237px");
				$(".leftnav img").attr("src", "content/skin/css/images/botton-closeLeft.gif");
				$(".right_user").css("left", "275px");
			} else {
				$(".leftnav").css("left", "0px");
				$(".leftnav img").attr("src", "content/skin/css/images/botton-closeRight.gif");
				$(".right_user").css("left", "20px");
			}
			$(".left").toggle();
			$(".left-bottom").toggle();
		});
		
		$("#two_nav li").click(function() {
			var m = $(this), s = m.siblings();
			m.addClass("sell");
			s.removeClass("sell");
		});
		//默认载入第一个菜单
		if (${fn:length(menuList)>1 }) {
			$("#two_nav li").eq(1).click();
		} else {
			$("#two_nav li:first").click();
		}
	});
</script>