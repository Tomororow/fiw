<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>历史查询首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/history.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/device.js"></script>
</head>

<body>
	<input type="hidden" value="${stcd}" id="turnStcd">
	<input type="hidden" value="${tag}" id="turnTag">
	<!--二级头部  -->
	<div class="header">
		<jsp:include page="/twoHeader.jsp"></jsp:include>
	</div>
	<!-- ztree -->
	<div class="left">
		<div class="tree">
			<ul id="ztree" class="ztree"></ul>
		</div>
	</div>

	<div class="leftnav">
		<a href="javascript:void(0)"> <img
			src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
	</div>

	<!--内容  -->
	<div id="twoContain"></div>

</body>
<script type="text/javascript">
	$(function() {
		$(".leftnav").click(function() {
			if ($(".left").css("display") == "none") {
				$(".leftnav").css("left", "237px");
				$(".leftnav img")
						.attr("src",
								"content/skin/css/images/botton-closeLeft.gif");
				$(".right_user").css("left", "275px");
			} else {
				$(".leftnav").css("left", "0px");
				$(".leftnav img")
						.attr("src",
								"content/skin/css/images/botton-closeRight.gif");
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

		//默认设备
		var turnStcd = $("#turnStcd").val();
		if (turnStcd != undefined && turnStcd != "") {
			debugger
			var turnTag = $("#turnTag").val();
			var m;
			if (turnTag == "history") {
				m = $(".top .top_nav #two_nav li[onclick*=history\\/history]");
			} else if (turnTag == "intelligentAnalysis") {
				m = $(".top .top_nav #two_nav li[onclick*=intelligentAnalysis\\/index]");
			}else if(turnTag == "device"){
				m = $(".top .top_nav #two_nav li[onclick*=device\\/device]");
			}else if(turnTag == "intelligentDeal"){
				m = $(".top .top_nav #two_nav li[onclick*=intelligentDeal\\/intelligentDeal]");
			}else {
				m = $(".top .top_nav #two_nav li[onclick*=history\\/alarm]");
			}
			if (m != undefined && m.length > 0) {
				var s = m.siblings();
				m.addClass("sell");
				s.removeClass("sell");
			}
			if (turnTag == "history") {
				showContent('history/history.do?stcd=' + turnStcd, 'twoContain');
			} else if (turnTag == "intelligentAnalysis") {
				showContent('intelligentAnalysis/index.do?stcd=' + turnStcd,
						'twoContain');
			}else if(turnTag == "device"){
				showContent('device/device.do?stcd=' + turnStcd,
						'twoContain');
			}else if(turnTag=="intelligentDeal"){
				showContent('intelligentDeal/intelligentDeal.do?stcd=' + turnStcd,
				'twoContain');
			}else {
				showContent('history/alarm.do?stcd=' + turnStcd, 'twoContain');
			}
		} else {
			//默认载入第一个菜单
			if (${fn:length(menuList)>1 }) {
				$("#two_nav li").eq(0).click();
			} else {
				$("#two_nav li:first").click();
			}
		}

	});
</script>
</html>