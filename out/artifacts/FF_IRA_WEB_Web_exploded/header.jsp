<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit"/>
	<meta name="force-rendering" content="webkit"/>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
</head>
<style>
	#header .el-badge__content{height: 12px;line-height: 12px;padding:0 3px;}
	#header #top_user_med li:HOVER{position: relative;top:1px;left:1px;}
	.popup-box{position: relative;left:0;margin: auto;}
	.cancelWarnBtn{margin-right: 10px;height: 25px;}
	.confirmWarnBtn{background-color: #18B399;border: solid 1px #ccc;color: #fff;height: 25px;border-radius: 2px;}
	.cancelWarnBtn:HOVER {cursor: pointer;position: relative;top:1px;left:1px;}
	.confirmWarnBtn:HOVER {cursor: pointer;position: relative;top:1px;left:1px;}
</style>
<div class="top">
	<div class="top_content">
		<div class="logo">
			<div id="imgPic">
				<img src="${ctx}/content/skin/css/images/logoWater.png">
			</div>
			<div id="tertuy">
				<span>${headAreaName}农业水价</span><br>
				<span>综合改革试点项目</span>
			</div>
		</div>
		<ul class="button">
			<c:forEach items="${menuList}" var="item" varStatus="vs">
				<li class='<c:if test="${vs.index==1?'sell':''}" ></c:if>' style="cursor: pointer;">
					<a href="javascript:void(0)" onclick="showContent('${item.menuUrl}?menuId=${item.id}','contain')">
						<img src="${ctx}/content/skin/css/images/menu/nav/${item.menuImage}.png" width="45" height="45" /><br/>${item.menuName}
					</a>
				</li>
			</c:forEach>
		</ul>
		<div class="top_user" id="header" style="width:20%; height:60px;">
			<div class="top_user_left"></div>
			<div class="top_user_med" id="top_user_med">
				<ul style="margin: 0;">
					<li>
						<a href="javascript:void(0);">
							<img src="${ctx}/content/skin/css/images/user.png" width="10" height="12" />&nbsp;${login_user.userName}
						</a><!-- #18B399 -->
					</li>
					<li>
						<a href="javascript:void(0);" onclick="changePwd('${login_user.id}')">
							<img src="${ctx}/content/skin/css/images/password.png" width="11" height="14" />&nbsp;修改密码
						</a>
					</li>
					<li>
						<a href="javascript:void(0);" @click="warnChange();">
							<el-badge v-bind:value="warnCount" :max="99" class="item">
								<img src="${ctx}/content/skin/css/images/warn.png" width="11" height="14" />&nbsp;预警
							</el-badge>
						</a>
					</li>
					<li style="border-right:0px;">
						<a href="javascript:void(0);" onclick="logout()">
							<img src="${ctx}/content/skin/css/images/exit.png" width="12" height="13" />&nbsp;安全退出
						</a>
					</li>	
				</ul>
			</div>
			<div class="top_user_right"></div>
			<div style="color:#fff; padding-left:14%; padding-top:8%; font-weight:bold;">技术支持：张掖金志信息技术有限公司</div>
			<%-- <!-- 音频（报警声） -->
			<audio id="audio" src="${ctx}/content/skin/voice/warnVoice.mp3" preload="auto" loop hidden="true"/> --%>
		</div>
	</div>
</div>

<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/header.js"></script>
<script>
	var issupermanager = $("#issupermanager").val();
	$(function() {
		$(".button  li a").click(function() {
			var m = $(this);
			m.addClass("sell");
			m.parent().siblings().children("a").removeClass("sell");
		});
		//默认载入第一个菜单
		if (${fn:length(menuList)>1 }) {
			$(".button li a").eq(0).click();
		} else {
			$(".button li a:first").click();
		}
	});
	function logout() {
		$.get("logout.do", function(data) {
			top.location.reload();
		}, "json");
	}
	function changePwd(userId) {
		if (issupermanager == 'true') {
			$.Popup.create({
				title : "提示",
				content : "超级管理员密码禁止修改!"
			});
		} else {
			var contentMsg = {
				title : "修改密码",
				url : "sysUser/changePwd.do",
				width : "500",
				paraData : {
					userId : userId
				},
				requestMethod : 'ajax',
				tbar : [ {
					text : "确定",
					clsName : "homebg popup-confirm",
					handler : function(thisPop) {
						userFnChangePwdSubmit(thisPop);
					}
				} ]
			};
			$.Popup.create(contentMsg);
		}
	}
</script>